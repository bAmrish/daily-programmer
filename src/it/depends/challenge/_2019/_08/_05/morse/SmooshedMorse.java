package it.depends.challenge._2019._08._05.morse;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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

        challenge1(words, smorsesToWordsMap);
    }

    private static String smorse(String message) {
        Stream<Character> messageStream = message.chars().mapToObj(c -> (char) c);
        return messageStream.map(MORSE_MAP::get).collect(Collectors.joining());
    }

    /**
     * Challenge:
     * The sequence -...-....-.--. is the code for four different words
     * ("needing", "nervate", "niding", tiling).
     * Find the only sequence that's the code for 13 different words.
     */
    private static void challenge1(List<String> words, Map<String, List<String>> smorsesToWordsMap) {
        smorsesToWordsMap.forEach((code, wordList) -> {
            if (wordList.size() == 13) {
                System.out.println("Challenge 1");
                System.out.println("Only sequence that's the code for 13 different words");
                System.out.println("code: " + code);
                System.out.println(wordList);
            }
        });
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
