package it.depends.challenge._2021._05._10.monty.hall;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

interface SelectionStrategy {
}

@FunctionalInterface
interface RoundBasedSelectionStrategy extends SelectionStrategy {
    int getDoor(Round round);
}

@FunctionalInterface
interface ContextBasedSelectionStrategy extends SelectionStrategy {
    int getDoor(Round round, boolean wonLastRound);
}

/**
 * <h1>[2021-05-10] Challenge #389 [Easy] The Monty Hall problem</h1>
 * Challenge Originally posted here:<br>
 * https://www.reddit.com/r/dailyprogrammer/comments/n94io8/20210510_challenge_389_easy_the_monty_hall_problem/
 *
 *
 * <p>
 * <h2>Background</h2>
 * For the purpose of today's challenge, the Monty Hall scenario goes like this:
 *
 * <p>
 * There are three closed doors, labeled #1, #2, and #3.
 * Monty Hall randomly selects one of the three doors and puts a prize behind it.
 * The other two doors hide nothing.
 *
 * <p>
 * A contestant, who does not know where the prize is, selects one of the three doors. This door is not opened yet.
 *
 * <p>
 * Monty chooses one of the three doors and opens it.
 * The door that Monty opens
 * <ol>
 * <li> does not hide the prize, and
 * <li> is not the door that the contestant selected.
 * </ol>
 * <p>There may be one or two such doors.If there are two, Monty randomly selects one or the other.
 *
 * <p>
 * There are now two closed doors, the one the contestant selected in step 2, and one they didn't select.
 * The contestant decides whether to keep their original choice, or to switch to the other closed door.
 *
 * <p>
 * The contestant wins if the door they selected in step 4 is the same as the one Monty put a prize behind in step 1.
 *
 * <p>
 * <h2>Challenge</h2>
 * <p>
 * A contestant's strategy is given by their choices in steps 2 and 4.
 * Write a program to determine the success rate of a contestant's strategy by simulating the game 1000 times
 * and calculating the fraction of the times the contestant wins. Determine the success rate for these two contestants:
 *
 * <p>
 * <h3>Scenario 1</h3>
 * Alice chooses door #1 in step 2, and always sticks with door #1 in step 4.
 *
 * <p>
 * <h3>Scenario 2</h3>
 * Bob chooses door #1 in step 2, and always switches to the other closed door in step 4.
 *
 *
 * <p>
 * <h2>Optional bonus</h2>
 * <p>
 * Find success rates for these other contestants:
 *
 * <p>
 * <h3>Scenario 3</h3>
 * Carol chooses randomly from the available options in both step 2 and step 4.
 *
 * <p>
 * <h3>Scenario 4</h3>
 * Dave chooses randomly in step 2, and always sticks with his door in step 4.
 *
 * <p>
 * <h3>Scenario 5</h3>
 * Erin chooses randomly in step 2, and always switches in step 4.
 *
 * <p>
 * <h3>Scenario 6</h3>
 * Frank chooses door #1 in step 2, and switches to door #2 if available in step 4.
 * If door #2 is not available because it was opened, then he stays with door #1.
 *
 * <p>
 * <h3>Scenario 7</h3>
 * Gina always uses either Alice's or Bob's strategy.
 * She remembers whether her previous strategy worked and changes it accordingly.
 * On her first game, she uses Alice's strategy. Thereafter, if she won the previous game,
 * then she sticks with the same strategy as the previous game.
 * If she lost the previous game, then she switches (Alice to Bob or Bob to Alice).
 *
 * <p>
 * It's possible to calculate all of these probabilities without doing any simulation, of course,
 * but today's challenge is to find the fractions through simulation.
 *
 * <p>
 * (This is a repost of Challenge #49 [easy], originally posted by u/oskar_s in May 2012.)
 */
public class MontyHallProblem {

    public static void main(String[] args) {

        RoundBasedSelectionStrategy alice2ndDoorStrategy = round -> 1;
        RoundBasedSelectionStrategy bob2ndDoorStrategy = round -> round.getMontySelection() == 2 ? 3 : 2;

        final RoundBasedSelectionStrategy[] lastStrategy = {null};
        ContextBasedSelectionStrategy frank2ndDoorStrategy = (round, wonLastRound) -> {

            if (lastStrategy[0] == null) {
                lastStrategy[0] = alice2ndDoorStrategy;
                return alice2ndDoorStrategy.getDoor(round);
            }

            if (wonLastRound) {
                return lastStrategy[0].getDoor(round);
            }

            // Switch strategy if you loose last round
            if (lastStrategy[0] == alice2ndDoorStrategy) {
                lastStrategy[0] = bob2ndDoorStrategy;
                return bob2ndDoorStrategy.getDoor(round);
            } else {
                lastStrategy[0] = alice2ndDoorStrategy;
                return alice2ndDoorStrategy.getDoor(round);
            }
        };

        new Scenario("Alice chooses door #1 in step 2, and always sticks with door #1 in step 4.")
                .setFirstSelectionStrategy(round -> 1)
                .setSecondSelectionStrategy(alice2ndDoorStrategy)
                .setTotalRounds(1000)
                .simulate();

        new Scenario("Bob chooses door #1 in step 2, and always switches to the other closed door in step 4.")
                .setFirstSelectionStrategy(round -> 1)
                .setSecondSelectionStrategy(bob2ndDoorStrategy)
                .setTotalRounds(1000)
                .simulate();

        new Scenario("Carol chooses randomly from the available options in both step 2 and step 4.")
                .setFirstSelectionStrategy(round -> Round.randomBetween(1, 3))
                .setSecondSelectionStrategy(round -> {
                    int randomChoice = Round.randomBetween(0, 1);
                    return Stream.of(1, 2, 3)
                            .filter(i -> round.getMontySelection() != i)
                            .collect(Collectors.toList())
                            .get(randomChoice);
                })
                .setTotalRounds(1000)
                .simulate();

        new Scenario("Dave chooses randomly in step 2, and always sticks with his door in step 4.")
                .setFirstSelectionStrategy(round -> Round.randomBetween(1, 3))
                .setSecondSelectionStrategy(Round::getPlayerSelection)
                .setTotalRounds(1000)
                .simulate();

        //noinspection OptionalGetWithoutIsPresent
        new Scenario("Erin chooses randomly in step 2, and always switches in step 4.")
                .setFirstSelectionStrategy(round -> Round.randomBetween(1, 3))
                .setSecondSelectionStrategy(round ->
                        Stream.of(1, 2, 3)
                                .filter(i -> i != round.getMontySelection())
                                .filter(i -> i != round.getPlayerSelection())
                                .findFirst().get()
                )
                .setTotalRounds(1000)
                .simulate();

        new Scenario("Frank chooses door #1 in step 2, and switches to door #2 if available in step 4.\n" +
                "If door #2 is not available because it was opened, then he stays with door #1.")
                .setFirstSelectionStrategy(round -> 1)
                .setSecondSelectionStrategy(round -> round.getMontySelection() == 2 ? 1 : 2)
                .setTotalRounds(1000)
                .simulate();

        new Scenario("Gina always uses either Alice's or Bob's strategy.\n" +
                "She remembers whether her previous strategy worked and changes it accordingly.\n" +
                "On her first game, she uses Alice's strategy. Thereafter, if she won the previous game,\n" +
                "then she sticks with the same strategy as the previous game.\n" +
                "If she lost the previous game, then she switches (Alice to Bob or Bob to Alice).")
                .setFirstSelectionStrategy(round -> 1)
                .setSecondSelectionStrategy(frank2ndDoorStrategy)
                .setTotalRounds(1000)
                .simulate();
    }
}

class Scenario {
    private final String description;
    private final Map<Integer, Boolean> results;
    private int totalRounds;
    private SelectionStrategy firstSelectionStrategy;
    private SelectionStrategy secondSelectionStrategy;

    public Scenario(String description) {
        this.description = description;
        this.results = new HashMap<>();
        this.totalRounds = 1;
    }

    public Scenario setFirstSelectionStrategy(RoundBasedSelectionStrategy strategy) {
        this.firstSelectionStrategy = strategy;
        return this;
    }

    public Scenario setFirstSelectionStrategy(ContextBasedSelectionStrategy strategy) {
        this.firstSelectionStrategy = strategy;
        return this;
    }

    public Scenario setSecondSelectionStrategy(RoundBasedSelectionStrategy strategy) {
        this.secondSelectionStrategy = strategy;
        return this;
    }

    public Scenario setSecondSelectionStrategy(ContextBasedSelectionStrategy strategy) {
        this.secondSelectionStrategy = strategy;
        return this;
    }

    public Scenario setTotalRounds(int totalRounds) {
        this.totalRounds = totalRounds;
        return this;
    }

    public void simulate() {
        boolean lastResult = true;

        for (int i = 0; i < totalRounds; i++) {

            // create a round;
            final Round round = new Round();
            int firstSelection;
            int secondSelection;

            // Player Makes the first Selection
            if (firstSelectionStrategy instanceof RoundBasedSelectionStrategy) {
                RoundBasedSelectionStrategy strategy = (RoundBasedSelectionStrategy) firstSelectionStrategy;
                firstSelection = strategy.getDoor(round);
            } else {
                ContextBasedSelectionStrategy strategy = (ContextBasedSelectionStrategy) firstSelectionStrategy;
                firstSelection = strategy.getDoor(round, lastResult);
            }

            round.setPlayerFirstSelection(firstSelection);

            // Monty Makes the selection
            round.setMontySelection();

            // Player makes second selection
            if (secondSelectionStrategy instanceof RoundBasedSelectionStrategy) {
                RoundBasedSelectionStrategy strategy = (RoundBasedSelectionStrategy) secondSelectionStrategy;
                secondSelection = strategy.getDoor(round);
            } else {
                ContextBasedSelectionStrategy strategy = (ContextBasedSelectionStrategy) secondSelectionStrategy;
                secondSelection = strategy.getDoor(round, lastResult);
            }

            round.setPlayerSecondSelection(secondSelection);

            results.put(i, round.didPlayerWin());
            lastResult = round.didPlayerWin();
        }

        printResults();

    }

    void printResults() {
        int totalWins = (int) results.entrySet().stream().filter(Map.Entry::getValue).count();
        double percentWin = ((double) totalWins * 100) / totalRounds;
        System.out.println("======================================================");
        System.out.println("Scenario: " + description);
        System.out.println("Total Rounds: " + totalRounds);
        System.out.println("Total Wins  : " + totalWins);
        System.out.println("% Wins      : " + percentWin + "%");
        System.out.println("======================================================");
        System.out.println();

    }
}

class Round {
    List<Door> doors = new ArrayList<>();
    private int prizeDoor;
    private int playerSelection;
    private int montySelection;

    public Round() {
        generateDoors();
    }

    /**
     * Generate random integer number between min (inclusive) and max (inclusive)
     */
    public static int randomBetween(int min, int max) {
        Random generator = new Random();

        return min + generator.nextInt(max - min + 1);
    }

    public int getPrizeDoor() {
        return prizeDoor;
    }

    public int getPlayerSelection() {
        return playerSelection;
    }

    public int getMontySelection() {
        return montySelection;
    }

    public void setPlayerFirstSelection(int playerSelection) {
        this.playerSelection = playerSelection;
    }

    public void setPlayerSecondSelection(int playerSelection) {
        if (montySelection == 0) {
            System.out.println("Player can't set second selection until Monty has done is selection.");
            return;
        }

        if (playerSelection == montySelection) {
            System.out.println("Player can't select the same door as Monty.");
            return;
        }

        this.playerSelection = playerSelection;
    }

    public void setMontySelection() {
        if (playerSelection == 0) {
            System.out.println("Monty cannot select unless player has selected first");
            return;
        }
        List<Door> remainingDoors = doors.stream()
                .filter(door -> !door.hasPrize() && door.getNumber() != playerSelection)
                .collect(Collectors.toList());

        if (remainingDoors.size() == 1) {
            montySelection = remainingDoors.get(0).getNumber();
        } else {
            montySelection = remainingDoors.get(randomBetween(0, 1)).getNumber();
        }
    }

    public boolean didPlayerWin() {
        return playerSelection == prizeDoor;
    }

    private void generateDoors() {
        prizeDoor = randomBetween(1, 3);
        for (int i = 1; i <= 3; i++) {
            Door door = new Door(i);
            if (i == prizeDoor) {
                door.setPrize(true);
            }
            doors.add(door);
        }
    }
}

class Door {
    final private int number;
    final private String name;
    private boolean hasPrize;

    public Door(int number) {
        this.number = number;
        this.name = "Door " + number;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public boolean hasPrize() {
        return hasPrize;
    }

    public void setPrize(boolean hasPrize) {
        this.hasPrize = hasPrize;
    }
}
