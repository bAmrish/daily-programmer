package it.depends.challenge._2019._08._05.morse;

/**
 * <h1>[2019-08-05] Challenge #380 [Easy] Smooshed Morse Code 1</h1>
 *
 * <p>
 * For the purpose of this challenge, Morse code represents every letter as a sequence of 1-4 characters,
 * each of which is either {@code .} (dot) or {@code -} (dash).
 * The code for the letter {@code a} is {@code .-}, for {@code b} is {@code -..}., etc.
 * The codes for each letter {@code a} through {@code z} are:
 * </p>
 * <pre>
 * .- -... -.-. -.. . ..-. --. .... .. .--- -.- .-.. -- -. --- .--. --.- .-. ... - ..- ...- .-- -..- -.-- --..
 * </pre>
 * <p>
 * Normally, you would indicate where one letter ends and the next begins,
 * for instance with a space between the letters' codes, but for this challenge,
 * just smoosh all the coded letters together into a single string consisting of only dashes and dots.
 * Examples
 * </p>
 * <pre>
 * smorse("sos") => "...---..."
 * smorse("daily") => "-...-...-..-.--"
 * smorse("programmer") => ".--..-.-----..-..-----..-."
 * smorse("bits") => "-.....-..."
 * smorse("three") => "-.....-..."
 * </pre>
 * <p>
 * An obvious problem with this system is that decoding is ambiguous.
 * For instance, both bits and three encode to the same string,
 * so you can't tell which one you would decode to without more information.
 * </p>
 *
 * <h2>Optional bonus challenges </h2>
 *
 * <p>
 * For these challenges, use the enable1 word list. It contains 172,823 words.
 * If you encode them all, you would get a total of 2,499,157 dots and 1,565,081 dashes.
 * </p>
 * <p>
 * The sequence {@code -...-....-.--.} is the code for four different words
 * ({@code "needing"}, {@code "nervate"}, {@code "niding"}, tiling).
 * Find the only sequence that's the code for 13 different words.
 * </p>
 * <p>
 * {@code "autotomous"} encodes to {@code .-..--------------..-...}, which has 14 dashes in a row.
 * Find the only word that has 15 dashes in a row.
 * </p>
 *
 * <p>
 * Call a word perfectly balanced if its code has the same number of dots as dashes.
 * {@code "counterdemonstrations"} is one of two 21-letter words that's perfectly balanced. Find the other one.
 * </p>
 *
 * <p>
 * {@code "protectorate"} is 12 letters long and encodes to {@code .--..-.----.-.-.----.-..--.},
 * which is a palindrome (i.e. the string is the same when reversed).
 * Find the only 13-letter word that encodes to a palindrome.
 * </p>
 *
 * <p>
 * {@code --.---.---.--} is one of five 13-character sequences that does not appear in the encoding of any word.
 * Find the other four.
 *
 * <p>
 * Thanks to <b>u/Separate_Memory</b> for inspiring this challenge on <b>r/dailyprogrammer</b>_ideas!
 */

public class SmooshedMorse {
    public static void main(String[] args) {
        assert smorse("sos").equals("...---...");
        assert smorse("daily").equals("-...-...-..-.--");
        assert smorse("programmer").equals(".--..-.-----..-..-----..-.");
        assert smorse("bits").equals("-.....-...");
        assert smorse("three").equals("-.....-...");
    }

    private static String smorse(String message) {
        return null;
    }
}
