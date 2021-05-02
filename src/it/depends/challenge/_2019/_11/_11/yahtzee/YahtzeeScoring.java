package it.depends.challenge._2019._11._11.yahtzee;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>[2019-11-11] Challenge #381 [Easy] Yahtzee Upper Section Scoring</h1>
 *  This challenge was originally posted here:
 *  <br><br>
 *  https://www.reddit.com/r/dailyprogrammer/comments/dv0231/20191111_challenge_381_easy_yahtzee_upper_section/
 *
 * <br><br>
 * <h2>Description</h2>
 * <br>
 * The game of Yahtzee is played by rolling five 6-sided dice,
 * and scoring the results in a number of ways. You are given a Yahtzee dice roll,
 * represented as a sorted list of 5 integers, each of which is between 1 and 6 inclusive.
 * Your task is to find the maximum possible score
 * for this roll in the upper section of the Yahtzee score card. Here's what that means.
 *
 * <br> <br>
 * For the purpose of this challenge, the upper section of Yahtzee gives you six possible ways to score a roll.
 * {@code 1} times the number of {@code 1}'s in the roll, {@code 2} times the number of {@code 2}'s,
 * {@code 3} times the number of {@code 3}'s, and so on up to {@code 6} times the number of {@code 6}'s.
 * <br><br>
 * For instance, consider the roll {@code [2, 3, 5, 5, 6]}.
 * If you scored this as {@code 1}'s, the score would be {@code 0}, since there are no {@code 1}'s in the roll.
 * If you scored it as {@code 2}'s, the score would be {@code 2}, since there's {@code one} {@code 2} in the roll.
 * Scoring the roll in each of the six ways gives you the six possible scores:
 * <br><br>
 * <pre>
 * 0 2 3 0 10 6
 * </pre>
 * The maximum here is {@code 10 (2x5)}, so your result should be {@code 10}.
 * <br><br>
 * <b>Examples:</b>
 * <br><br>
 * <pre>
 * yahtzee_upper([2, 3, 5, 5, 6]) => 10
 * yahtzee_upper([1, 1, 1, 1, 3]) => 4
 * yahtzee_upper([1, 1, 1, 3, 3]) => 6
 * yahtzee_upper([1, 2, 3, 4, 5]) => 5
 * yahtzee_upper([6, 6, 6, 6, 6]) => 30
 * </pre>
 * <br>
 * <h2>Optional Bonus</h2>
 * <br>
 * Efficiently handle inputs that are unsorted and much larger, both in the number of dice and in the number of sides per die. (For the purpose of this bonus challenge, you want the maximum value of some number k, times the number of times k appears in the input.)
 * <br>
 * <pre>
 * yahtzee_upper([1654, 1654, 50995, 30864, 1654, 50995, 22747,
 *     1654, 1654, 1654, 1654, 1654, 30864, 4868, 1654, 4868, 1654,
 *     30864, 4868, 30864]) => 123456
 * </pre>
 * <br>
 * There's no strict limit on how fast it needs to run. That depends on your language of choice.
 * But for rough comparison, my Python solution on this challenge input,
 * consisting of 100,000 values between 1 and 999,999,999 takes about 0.2 seconds (0.06 seconds not counting input parsing).
 *
 * <br><br>https://gist.githubusercontent.com/cosmologicon/beadf49c9fe50a5c2a07ab8d68093bd0/raw/fb5af1a744faf79d64e2a3bb10973e642dc6f7b0/yahtzee-upper-1.txt
 * <br>
 * <br>
 * If you're preparing for a coding interview, this is a good opportunity to practice runtime complexity. Try to find a solution that's linear (O(N)) in both time and space requirements.
 */
public class YahtzeeScoring {
    public static void main(String[] args) {
        assert yahtzee_upper(new int[]{2, 3, 5, 5, 6}) == 10;
        assert yahtzee_upper(new int[]{1, 1, 1, 1, 3}) == 4;
        assert yahtzee_upper(new int[]{1, 1, 1, 3, 3}) == 6;
        assert yahtzee_upper(new int[]{1, 2, 3, 4, 5}) == 5;
        assert yahtzee_upper(new int[]{6, 6, 6, 6, 6}) == 30;
        assert yahtzee_upper(new int[]{1654, 1654, 50995, 30864, 1654, 50995, 22747,
                1654, 1654, 1654, 1654, 1654, 30864, 4868, 1654, 4868, 1654,
                30864, 4868, 30864}) == 123456;
    }

    public static int yahtzee_upper(int[] roll) {
        Map<Integer, Integer> rollValueFrequency = new HashMap<>();
        final int[] maxScore = {0};

        for (Integer roleValue : roll) {
            Integer frequency = rollValueFrequency.getOrDefault(roleValue, 0);
            rollValueFrequency.put(roleValue, ++frequency);
        }

        rollValueFrequency.forEach((rollValue, frequency) -> {
            int valueScore = rollValue * frequency;
            maxScore[0] = Math.max(maxScore[0], valueScore);
        });

        return maxScore[0];
    }
}
