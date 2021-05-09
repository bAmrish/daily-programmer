package it.depends.challenge._2019._05._20.havel.hakimi;

/**
 * <h1>[2019-05-20] Challenge #378 [Easy] The Havel-Hakimi algorithm for graph realization</h1>
 * Original Challenge posted here:
 * <p>
 * https://www.reddit.com/r/dailyprogrammer/comments/bqy1cf/20190520_challenge_378_easy_the_havelhakimi/
 *
 * <p>
 * It was a dark and stormy night. Detective Havel and Detective Hakimi arrived at the scene of the crime.
 * <p>
 * Other than the detectives, there were 10 people present.
 * They asked the first person, "out of the 9 other people here, how many had you already met before tonight?"
 * The person answered "5". They asked the same question of the second person, who answered "3".
 * And so on. The 10 answers they got from the 10 people were:
 *
 * <pre>5 3 0 2 6 2 0 7 2 5</pre>
 *
 * <p>
 * The detectives looked at the answers carefully and deduced that there was an inconsistency,
 * and that somebody must be lying. (For the purpose of this challenge,
 * assume that nobody makes mistakes or forgets, and if X has met Y, that means Y has also met X.)
 *
 * <p>
 * Your challenge for today is, given a sequence of answers to the question
 * "how many of the others had you met before tonight?",
 * apply the Havel-Hakimi algorithm to determine whether or not it's possible that everyone was telling the truth.
 *
 * <p>
 * If you're feeling up to it, skip ahead to the Challenge section below.
 * Otherwise, try as many of the optional warmup questions as you want first, before attempting the full challenge.
 *
 *
 * <p>
 * <h2>Optional Warmup 1: eliminating 0's.</h2>
 * <p>
 * Given a sequence of answers, return the same set of answers with all the 0's removed.
 * <pre>
 * warmup1([5, 3, 0, 2, 6, 2, 0, 7, 2, 5]) => [5, 3, 2, 6, 2, 7, 2, 5]
 * warmup1([4, 0, 0, 1, 3]) => [4, 1, 3]
 * warmup1([1, 2, 3]) => [1, 2, 3]
 * warmup1([0, 0, 0]) => []
 * warmup1([]) => []
 * </pre>
 *
 * <p>
 * If you want to reorder the sequence as you do this, that's fine.
 * For instance, given {@code [4, 0, 0, 1, 3]}, then you may return {@code [4, 1, 3]} or {@code [1, 3, 4]}
 * or {@code [4, 3, 1]} or any other ordering of these numbers.
 *
 *
 * <p>
 * <h2>Optional Warmup 2: descending sort</h2>
 * Given a sequence of answers, return the sequence sorted in descending order,
 * so that the first number is the largest and the last number is the smallest.
 *
 * <pre>
 * warmup2([5, 1, 3, 4, 2]) => [5, 4, 3, 2, 1]
 * warmup2([0, 0, 0, 4, 0]) => [4, 0, 0, 0, 0]
 * warmup2([1]) => [1]
 * warmup2([]) => []
 * </pre>
 *
 * <p>
 * <h2>Optional Warmup 3: length check</h2>
 * <p>
 * Given a number N and a sequence of answers, return {@code true} if N is greater than the number of answers
 * (i.e. the length of the sequence), and {@code false} if N is less than or equal to the number of answers.
 * For instance, given {@code 7} and {@code [6, 5, 5, 3, 2, 2, 2]}, you would return {@code false},
 * because {@code 7} is less than or equal to {@code 7}.
 * <pre>
 * warmup3(7, [6, 5, 5, 3, 2, 2, 2]) => false
 * warmup3(5, [5, 5, 5, 5, 5]) => false
 * warmup3(5, [5, 5, 5, 5]) => true
 * warmup3(3, [1, 1]) => true
 * warmup3(1, []) => true
 * warmup3(0, []) => false
 * </per>
 *
 * <p>
 * <h2>Optional Warmup 4: front elimination</h2>
 * <p>
 * Given a number N and a sequence in descending order, subtract {@code 1}
 * from each of the first N answers in the sequence, and return the result.
 * For instance, given {@code N = 4} and the sequence {@code [5, 4, 3, 2, 1]},
 * you would subtract {@code 1} from each of the first {@code 4} answers {@code (5, 4, 3, and 2)}
 * to get {@code 4, 3, 2, and 1}. The rest of the sequence {@code (1)} would not be affected:
 * <pre>
 * warmup4(4, [5, 4, 3, 2, 1]) => [4, 3, 2, 1, 1]
 * warmup4(11, [14, 13, 13, 13, 12, 10, 8, 8, 7, 7, 6, 6, 4, 4, 2]) => [13, 12, 12, 12, 11, 9, 7, 7, 6, 6, 5, 6, 4, 4, 2]
 * warmup4(1, [10, 10, 10]) => [9, 10, 10]
 * warmup4(3, [10, 10, 10]) => [9, 9, 9]
 * warmup4(1, [1]) => [0]
 * </pre>
 * <p>
 * You may assume that N is greater than 0, and no greater than the length of the sequence.
 * <p>
 * Like in warmup 1, it's okay if you want to reorder the answers in your result.
 *
 * <p><h2>Challenge: the Havel-Hakimi algorithm</h2>
 *
 * Perform the Havel-Hakimi algorithm on a given sequence of answers.
 * This algorithm will return true if the answers are consistent
 * (i.e. it's possible that everyone is telling the truth) and
 * false if the answers are inconsistent (i.e. someone must be lying):
 * <p>
 * Remove all 0's from the sequence (i.e. warmup1).
 * <p>
 * If the sequence is now empty (no elements left), stop and return true.
 * <p>
 * Sort the sequence in descending order (i.e. warmup2).
 * <p>
 * Remove the first answer (which is also the largest answer, or tied for the largest)
 * from the sequence and call it N. The sequence is now 1 shorter than it was after the previous step.
 * <p>
 * If N is greater than the length of this new sequence (i.e. warmup3), stop and return false.
 * <p>
 * Subtract {@code 1} from each of the first N elements of the new sequence (i.e. warmup4).
 * <p>
 * Continue from step 1 using the sequence from the previous step.
 * <p>
 * Eventually you'll either return true in step 2, or false in step 5.
 * <p>
 * You don't have to follow these steps exactly: as long as you return the right answer, that's fine.
 * Also, if you answered the warmup questions, you may use your warmup solutions to build your challenge solution,
 * but you don't have to.
 *
 * <pre>
 * hh([5, 3, 0, 2, 6, 2, 0, 7, 2, 5]) => false
 * hh([4, 2, 0, 1, 5, 0]) => false
 * hh([3, 1, 2, 3, 1, 0]) => true
 * hh([16, 9, 9, 15, 9, 7, 9, 11, 17, 11, 4, 9, 12, 14, 14, 12, 17, 0, 3, 16]) => true
 * hh([14, 10, 17, 13, 4, 8, 6, 7, 13, 13, 17, 18, 8, 17, 2, 14, 6, 4, 7, 12]) => true
 * hh([15, 18, 6, 13, 12, 4, 4, 14, 1, 6, 18, 2, 6, 16, 0, 9, 10, 7, 12, 3]) => false
 * hh([6, 0, 10, 10, 10, 5, 8, 3, 0, 14, 16, 2, 13, 1, 2, 13, 6, 15, 5, 1]) => false
 * hh([2, 2, 0]) => false
 * hh([3, 2, 1]) => false
 * hh([1, 1]) => true
 * hh([1]) => false
 * hh([]) => true
 * </pre>
 *
 * <p>
 * <h2>Detailed example</h2>
 * <p>
 * Here's the first pass through the algorithm using the original example:
 * <p>
 * <pre>[5, 3, 0, 2, 6, 2, 0, 7, 2, 5] // Starting sequence </pre>
 * <p>
 * <pre>[5, 3, 2, 6, 2, 7, 2, 5] // After step 1, removing 0's.</pre>
 * <p>
 * Step 2: This sequence is not empty, so go on to step 3.
 * <p>
 * <pre>[7, 6, 5, 5, 3, 2, 2, 2] // After step 3, sorting in descending order.</pre>
 * <p>
 * <pre>[6, 5, 5, 3, 2, 2, 2] // After step 4, removing the first answer N = 7.</pre>
 * <p>
 * Step 5: N (7) is less than or equal to the number of answers remaining in the sequence (7), so go on to step 6.
 * <p>
 * <pre>
 *     // After step 6, subtracting 1 from each of the first 7 answers (which is all of them in this case).
 *     [5, 4, 4, 2, 1, 1, 1]
 * </pre>
 * <p>
 * At this point you would start over at step 1 with the sequence [5, 4, 4, 2, 1, 1, 1].
 * After your second pass through the algorithm, your sequence will be [3, 3, 1, 0, 0, 1],
 * so start back at step 1 with this sequence. After your third pass you'll have [2, 0, 0].
 * On your fourth pass, you'll stop at step 5,
 * because you'll have N = 2 and an empty sequence ([]), and 2 > 0, so you will return false.
 *
 */

public class HavelHakimi {
    public static void main(String[] args) {
        assert !check(new int[]{5, 3, 0, 2, 6, 2, 0, 7, 2, 5});
        assert !check(new int[]{4, 2, 0, 1, 5, 0});
        assert check(new int[]{3, 1, 2, 3, 1, 0});
        assert check(new int[]{16, 9, 9, 15, 9, 7, 9, 11, 17, 11, 4, 9, 12, 14, 14, 12, 17, 0, 3, 16});
        assert check(new int[]{14, 10, 17, 13, 4, 8, 6, 7, 13, 13, 17, 18, 8, 17, 2, 14, 6, 4, 7, 12});
        assert !check(new int[]{15, 18, 6, 13, 12, 4, 4, 14, 1, 6, 18, 2, 6, 16, 0, 9, 10, 7, 12, 3});
        assert !check(new int[]{6, 0, 10, 10, 10, 5, 8, 3, 0, 14, 16, 2, 13, 1, 2, 13, 6, 15, 5, 1});
        assert !check(new int[]{2, 2, 0});
        assert !check(new int[]{3, 2, 1});
        assert check(new int[]{1, 1});
        assert !check(new int[]{1});
        assert check(new int[]{});
    }

    private static boolean check(int[] answers) {
        return false;
    }
}
