package it.depends.challenge._2021._05._10.monty.hall;

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
 * <h3>Scenario 3</h3>
 * Find success rates for these other contestants:
 *
 * <p>
 * <h3>Scenario 4</h3>
 * Carol chooses randomly from the available options in both step 2 and step 4.
 *
 * <p>
 * <h3>Scenario 5</h3>
 * Dave chooses randomly in step 2, and always sticks with his door in step 4.
 *
 * <p>
 * <h3>Scenario 6</h3>
 * Erin chooses randomly in step 2, and always switches in step 4.
 *
 * <p>
 * <h3>Scenario 7</h3>
 * Frank chooses door #1 in step 2, and switches to door #2 if available in step 4.
 * If door #2 is not available because it was opened, then he stays with door #1.
 *
 * <p>
 * <h3>Scenario 8</h3>
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

    }

}
