package it.depends.challenge._2019._08._05.morse;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
 * <p><h2>Optional bonus challenges </h2></p>
 *
 * <p>
 * For these challenges, use the enable1 word list. It contains 172,823 words.
 * If you encode them all, you would get a total of 2,499,157 dots and 1,565,081 dashes.
 * </p>
 *
 * <p>
 * <h2>Challenge 1</h2>
 * The sequence {@code -...-....-.--.} is the code for four different words
 * ({@code "needing"}, {@code "nervate"}, {@code "niding"}, tiling).
 * Find the only sequence that's the code for 13 different words.
 * </p>
 *
 * <p>
 * <h2>Challenge 2</h2>
 * {@code "autotomous"} encodes to {@code .-..--------------..-...}, which has 14 dashes in a row.
 * Find the only word that has 15 dashes in a row.
 * </p>
 *
 * <p>
 * <h2>Challenge 3</h2>
 * Call a word perfectly balanced if its code has the same number of dots as dashes.
 * {@code "counterdemonstrations"} is one of two 21-letter words that's perfectly balanced. Find the other one.
 * </p>
 *
 * <p>
 * <h2>Challenge 4</h2>
 * {@code "protectorate"} is 12 letters long and encodes to {@code .--..-.----.-.-.----.-..--.},
 * which is a palindrome (i.e. the string is the same when reversed).
 * Find the only 13-letter word that encodes to a palindrome.
 * </p>
 *
 * <p>
 * <h2>Challenge 5</h2>
 * {@code --.---.---.--} is one of five 13-character sequences that does not appear in the encoding of any word.
 * Find the other four.
 * </p>
 * <p>
 * Thanks to <b>u/Separate_Memory</b> for inspiring this challenge on <b>r/dailyprogrammer</b>_ideas!
 * </p>
 */

public class SmooshedMorse {
    private static final Map<Character, String> MORSE_MAP = new HashMap<>() {{
        put('a', ".-");
        put('b', "-...");
        put('c', "-.-.");
        put('d', "-..");
        put('e', ".");
        put('f', "..-.");
        put('g', "--.");
        put('h', "....");
        put('i', "..");
        put('j', ".---");
        put('k', "-.-");
        put('l', ".-..");
        put('m', "--");
        put('n', "-.");
        put('o', "---");
        put('p', ".--.");
        put('q', "--.-");
        put('r', ".-.");
        put('s', "...");
        put('t', "-");
        put('u', "..-");
        put('v', "...-");
        put('w', ".--");
        put('x', "-..-");
        put('y', "-.--");
        put('z', "--..");
    }};
    private static final String WORDS_FILE_PATH
            = "resources/enable1.txt";

    public static void main(String[] args) {
        assert smorse("sos").equals("...---...");
        assert smorse("daily").equals("-...-...-..-.--");
        assert smorse("programmer").equals(".--..-.-----..-..-----..-.");
        assert smorse("bits").equals("-.....-...");
        assert smorse("three").equals("-.....-...");

        List<String> words = getWordsFromFile(WORDS_FILE_PATH);
        Map<String, List<String>> smorsesToWordsMap = getSmorsesToWordsMap(words);

        challenge1(smorsesToWordsMap);
        challenge2(smorsesToWordsMap);
        challenge3(words);
        challenge4(words);

        // NoT a valid solution.
        // challenge5(smorsesToWordsMap);
    }

    private static String smorse(String message) {
        Stream<Character> messageStream = message.chars().mapToObj(c -> (char) c);
        return messageStream.map(MORSE_MAP::get).collect(Collectors.joining());
    }

    /**
     * Challenge:
     * The sequence {@code -...-....-.--.} is the code for four different words
     * ({@code "needing"}, {@code "nervate"}, {@code "niding"}, {@code "tiling"}).
     * Find the only sequence that's the code for 13 different words.
     */
    private static void challenge1(Map<String, List<String>> smorsesToWordsMap) {
        smorsesToWordsMap.forEach((code, wordList) -> {
            if (wordList.size() == 13) {
                System.out.println();
                System.out.println("Challenge 1");
                System.out.println("Only sequence that's the code for 13 different words");
                System.out.println("code: " + code);
                System.out.println("words: " + wordList);

            }
        });
    }

    /**
     * Challenge 2
     * {@code "autotomous"} encodes to {@code .-..--------------..-..}., which has 14 dashes in a row.
     * Find the only word that has 15 dashes in a row.
     */
    private static void challenge2(Map<String, List<String>> smorsesToWordsMap) {
        String fifteenDashes = "---------------";
        Optional<String> theCode = smorsesToWordsMap.keySet().stream()
                .filter(code -> code.contains(fifteenDashes))
                .findFirst();
        if (theCode.isPresent()) {
            System.out.println();
            System.out.println("Challenge 2");
            System.out.println("The only word that has 15 dashes in a row");
            System.out.println("code: " + theCode.get());
            System.out.println("word: " + smorsesToWordsMap.get(theCode.get()));

        } else {
            System.out.println("No code found with 15 dashes");
        }
    }

    /**
     * Call a word perfectly balanced if its code has the same number of dots as dashes.
     * {@code "counterdemonstrations"} is one of two 21-letter words that's perfectly balanced.
     * Find the other one.
     */
    private static void challenge3(List<String> words) {
        @SuppressWarnings("SpellCheckingInspection")
        Optional<String> theBalancedWord = words.stream()
                .filter(word -> word.length() == 21)
                .filter(word -> !word.equals("counterdemonstrations"))
                .filter(word -> isBalanced(smorse(word)))
                .findFirst();

        if (theBalancedWord.isPresent()) {
            System.out.println();
            System.out.println("Challenge 3");
            System.out.println("The Other 21-letter words that's perfectly balanced");
            System.out.println("word: " + theBalancedWord.get());
            System.out.println("code: " + smorse(theBalancedWord.get()));

        } else {
            System.out.println("No other 21-letter words that's perfectly balanced.");
        }
    }

    /**
     * {@code "protectorate" }is 12 letters long and encodes to {@code .--..-.----.-.-.----.-..--.},
     * which is a palindrome (i.e. the string is the same when reversed).
     * Find the only 13-letter word that encodes to a palindrome.
     */
    private static void challenge4(List<String> words) {
        Optional<String> palindromeWord = words.stream()
                .filter(word -> word.length() == 13)
                .filter(word -> isPalindrome(smorse(word))).findFirst();

        if (palindromeWord.isPresent()) {
            System.out.println();
            System.out.println("Challenge 4");
            System.out.println("The only 13-letter word that encodes to a palindrome");
            System.out.println("word: " + palindromeWord.get());
            System.out.println("code: " + smorse(palindromeWord.get()));

        } else {
            System.out.println("No other 13-letter letter word found that encodes to palindrome.");
        }
    }

    /**
     * Challenge 5
     * {@code --.---.---.--} is one of five 13-character sequences that does not appear in the encoding of any word.
     * Find the other four.*
     *
     * [WIP] = Not a valid solution yet.
     */
    private static void challenge5(Map<String, List<String>> smorsesToWordsMap) {
        // We use brute force approach here
        // we generate all possible combinations 13-character sequences for morse code
        // and check if any of the 13 letters code generated for existing words
        // match the combinations.

        List<String> allCombinations = generateMorseCombinations(13);

        List<String> encoded = smorsesToWordsMap.keySet().stream()
                .filter(code -> code.length() == 13)
                .collect(Collectors.toList());

        List<String> missingEncodings = allCombinations.stream()
                .filter(combination -> !encoded.contains(combination))
                .collect(Collectors.toList());

        if (!missingEncodings.isEmpty()) {
            System.out.println();
            System.out.println("Challenge 5");
            System.out.println("13-character sequences that does not appear in the encoding of any word");
            System.out.println(String.join("\n", missingEncodings));
        } else {
            System.out.println("No other 13-letter letter word found that encodes to palindrome.");
        }

    }

    private static boolean isPalindrome(String code) {
        String reverse =
                // convert the input code to stream of letters.
                Arrays.stream(code.split(""))
                        // and use the reduce function on the string to reverse it.
                        // we start with an empty string
                        // and append each letter to the beginning of accumulator,
                        // effectively reversing the original string
                        .reduce("", (reverseAcc, letter) -> letter + reverseAcc);
        return reverse.equals(code);
    }

    /**
     * A code is perfectly balanced if it has the same number of dots as dashes
     *
     * @param code - to check for balance.
     * @return true - if the code is balanced. else false.
     */
    private static boolean isBalanced(String code) {
        int dots = code.split("\\.").length;
        int dashes = code.split("-").length;
        return dots == dashes;
    }

    /**
     * This function generates all smorses possible combinations for given length
     * (irrespective of the validity).
     * <p>
     * Lets say {@code length = 3}, then there are {@code 2^3 = 8} possible combinations.
     * <p>
     * We do that by starting with a string with same character ({@code .})
     * <pre>
     *     ...
     * </pre>
     * Then we flip the last position and add that string to the array of current strings.
     * <pre>
     *     ...
     *     .._
     * </pre>
     * Then we flip the {@code last - 1}  position of all the strings and add them to the list
     * <pre>
     *     ...
     *     .._
     *     ._.
     *     .__
     * </pre>
     * Then we flip the {@code last - 2} position of all the current strings in the list
     * and add the result back to the list.
     * <pre>
     *     ...
     *     .._
     *     ._.
     *     .__
     *     _..
     *     _._
     *     __.
     *     ___
     * </pre>
     * <p>
     * Now we have reached the start and we are all done!
     * This is the same algorithm we use to flip string of the given length.
     *
     * @param length - length of the string.
     */
    private static List<String> generateMorseCombinations(int length) {

        // we need minimum length of 2 to generate this combinations.
        assert length > 1;

        // This variable will hold all the possible combinations
        List<String> combinations = new ArrayList<>();

        // lets generate the first combination which will be all dots.
        // and add it to the combinations.
        String first = ".".repeat(length);
        combinations.add(first);

        // lets hold the bit we are going to flip in this variable
        // starting with the last one.
        int flipPosition = length - 1;
        do {
            // we will hold the new combinations generated in this array list.
            List<String> newCombinations = new ArrayList<>();

            for (String combination : combinations) {
                // here we flip the char at "flipPosition"
                String[] characters = combination.split("");
                characters[flipPosition] = "-";
                newCombinations.add(String.join("", characters));
            }
            combinations.addAll(newCombinations);
            flipPosition--;
        } while (flipPosition >= 0);

        return combinations;
    }

    private static Map<String, List<String>> getSmorsesToWordsMap(List<String> words) {
        Map<String, List<String>> smorsesToWordsMap = new HashMap<>();
        for (String word : words) {
            String code = smorse(word);
            List<String> wordList = smorsesToWordsMap.getOrDefault(code, new ArrayList<>());
            wordList.add(word);
            smorsesToWordsMap.put(code, wordList);
        }
        return smorsesToWordsMap;
    }

    @SuppressWarnings("SameParameterValue")
    private static List<String> getWordsFromFile(String filePath) {
        List<String> words = new ArrayList<>();
        ClassLoader classLoader = SmooshedMorse.class.getClassLoader();
        URL resource = classLoader.getResource(filePath);
        try {
            @SuppressWarnings("ConstantConditions")
            Path path = Paths.get(resource.getPath());
            Stream<String> lines = Files.lines(path);
            words = lines.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return words;
    }
}
