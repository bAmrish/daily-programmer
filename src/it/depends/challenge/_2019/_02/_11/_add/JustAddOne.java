package it.depends.challenge._2019._02._11._add;

/**
 * <h1>[2019-02-11] Challenge #375 [Easy] Print a new number by adding one to each of its digit
 * Description</h1>
 * <p>
 * This challenge was originally posted here: <br>
 * <p>https://www.reddit.com/r/dailyprogrammer/comments/aphavc/20190211_challenge_375_easy_print_a_new_number_by/
 *
 * <p>
 * A number is input in computer then a new no should get printed by adding one to each of its digit.
 * If you encounter a 9, insert a 10 (don't carry over, just shift things around).
 * </p>
 *
 * <p>
 * For example, {@code 998} becomes {@code 10109}.
 * </p>
 * <p>
 * <h2>Bonus</h2>
 * </p>
 * <p>
 * This challenge is trivial to do if you map it to a string to iterate over the input, operate,
 * and then cast it back. Instead, try doing it without casting it as a string at any point,
 * keep it numeric (int, float if you need it) only.
 *
 * </p>
 * <p>
 * <h2>Credit</h2>
 * This challenge was suggested by user /u/chetvishal, many thanks!
 * If you have a challenge idea please share it in r/dailyprogrammer_ideas
 * and there's a good chance we'll use it.
 * </p>
 */
public class JustAddOne {
    public static void main(String[] args) {
        assert addOne(998) == 10109;
    }

    public static long addOne(int n) {
        long product = 0;
        int length = getTotalDigits(n);
        int quotient = n;
        for (int i = 0; i < length; i++) {
            int remainder = quotient % 10;
            if (i == 0) {
                product = (remainder + 1);
            } else if (remainder == 9) {
                product = ((remainder + 1) * (long) Math.pow(10, getTotalDigits(product))) + product;
            } else {
                product = ((remainder + 1) * (long) Math.pow(10, getTotalDigits(product))) + product;
            }
            quotient = (quotient - remainder) / 10;
        }
        return product;
    }

    private static int getTotalDigits(long n) {
        return (int) Math.floor(Math.log10(n)) + 1;
    }
}
