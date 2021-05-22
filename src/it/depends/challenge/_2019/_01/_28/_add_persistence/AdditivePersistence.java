package it.depends.challenge._2019._01._28._add_persistence;

import java.math.BigInteger;

/**
 * <h1>[2019-01-28] Challenge #374 [Easy] Additive Persistence
 * Description</h1>
 * <p>
 * Challenge originally posted here: <br>
 * https://www.reddit.com/r/dailyprogrammer/comments/akv6z4/20190128_challenge_374_easy_additive_persistence/
 * </p>
 * <p>
 * Inspired by this tweet (https://twitter.com/fermatslibrary/status/1089883307473543170),
 * today's challenge is to calculate the additive persistence of a number,
 * defined as how many loops you have to do summing its digits until you get a
 * single digit number. Take an integer N:
 *
 * <p>
 * Add its digits
 * <p>
 * Repeat until the result has 1 digit
 * <p>
 * The total number of iterations is the additive persistence of N.
 * <p>
 * Your challenge today is to implement a function that calculates
 * the additive persistence of a number.
 *
 * <p>
 * <h2>Examples</h2>
 * <pre>
 * 13 -> 1
 * 1234 -> 2
 * 9876 -> 2
 * 199 -> 3
 * </pre>
 * <p>
 * <h2>Bonus</h2>
 * <p>
 * The really easy solution manipulates the input to convert the number to a
 * string and iterate over it. Try it without making the number a strong,
 * decomposing it into digits while keeping it a number.
 * <p>
 * On some platforms and languages, if you try and find ever larger
 * persistence values you'll quickly learn about your platform's
 * big integer interfaces (e.g. 64 bit numbers).
 */

public class AdditivePersistence {
    private static final BigInteger TEN = BigInteger.valueOf(10);

    public static void main(String[] args) {
        assert additivePersistence(BigInteger.valueOf(13)) == 1;
        assert additivePersistence(BigInteger.valueOf(1234)) == 2;
        assert additivePersistence(BigInteger.valueOf(9876)) == 2;
        assert additivePersistence(BigInteger.valueOf(199)) == 3;
        assert additivePersistence(new BigInteger("19999999999999999999999")) == 4;
    }

    public static int additivePersistence(BigInteger n) {
        if (n.intValue() < 9) {
            return 0;
        }

        BigInteger sum = sumDigits(n);
        int pass = 1;
        while (sum.toString().length() > 1) {
            sum = sumDigits(sum);
            pass++;
        }

        return pass;
    }

    private static BigInteger sumDigits(BigInteger n) {
        if (n.toString().length() == 1) {
            return n;
        } else {
            return n.mod(TEN).add(sumDigits(n.divide(TEN)));
        }
    }
}
