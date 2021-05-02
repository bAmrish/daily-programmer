package it.depends.challenge._2020._03._09.necklace;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <h1>[2020-03-09] Challenge #383 [Easy] Necklace matching Challenge </h1>
 *
 * This challenge was initially posted here:
 * https://www.reddit.com/r/dailyprogrammer/comments/ffxabb/20200309_challenge_383_easy_necklace_matching/
 * <br> <br>
 * <h2>Challenge</h2>
 *
 * Imagine a necklace with lettered beads that can slide along the string. E.g: {@code "NICOLE"}
 * In this example, you could take the N off {@code "NICOLE"} and slide it around to the other
 * end to make {@code "ICOLEN"}. Do it again to get {@code "COLENI"}, and so on.
 * For the purpose of today's challenge, we'll say that the strings
 * {@code "nicole"}, {@code "icolen"}, and {@code "coleni"} describe the same necklace.
 *
 * Generally, two strings describe the same necklace if you can remove some
 * number of letters from the beginning of one, attach them to the end in their original ordering,
 * and get the other string. Reordering the letters in some other way does not,
 * in general, produce a string that describes the same necklace.
 *
 * Write a function that returns whether two strings describe the same necklace.
 * Examples:
 * <br> <br>
 * <pre>
 * same_necklace("nicole", "icolen") => true
 * same_necklace("nicole", "lenico") => true
 * same_necklace("nicole", "coneli") => false
 * same_necklace("aabaaaaabaab", "aabaabaabaaa") => true
 * same_necklace("abc", "cba") => false
 * same_necklace("xxyyy", "xxxyy") => false
 * same_necklace("xyxxz", "xxyxz") => false
 * same_necklace("x", "x") => true
 * same_necklace("x", "xx") => false
 * same_necklace("x", "") => false
 * same_necklace("", "") => true
 * </pre>
 * <br> <br>
 * <h2>Optional Bonus 1</h2>
 *
 * If you have a string of N letters and you move each letter one at a time from the start to the end,
 * you'll eventually get back to the string you started with, after N steps.
 * Sometimes, you'll see the same string you started with before N steps. For instance,
 * if you start with {@code "abcabcabc"}, you'll see the same string ({@code "abcabcabc"}) 3 times over
 * the course of moving a letter 9 times.
 *
 * Write a function that returns the number of times you encounter
 * the same starting string if you move each letter in the string from the start to the end, one at a time.
 * <br> <br>
 *<pre>
 * repeats("abc") => 1
 * repeats("abcabcabc") => 3
 * repeats("abcabcabcx") => 1
 * repeats("aaaaaa") => 6
 * repeats("a") => 1
 * repeats("") => 1
 *</pre>
 *<br>
 * <h2>Optional Bonus 2</h2>
 *
 * There is exactly one set of four words in the "enable1 word" <i>(see text file in the same dir)</i>
 * list that all describe the same necklace. Find the four words.
 *
 * */

public class NecklaceMatching {

    private static final String WORDS_FILE_PATH
            = "it/depends/challenge/_2020/_03/_09/necklace/enable1.txt";

    @SuppressWarnings("SpellCheckingInspection")
    public static void main(String[] args) {
        // Main Challanged
        assert is_same_necklace("nicole", "icolen");
        assert is_same_necklace("nicole", "lenico");
        assert !is_same_necklace("nicole", "coneli");
        assert is_same_necklace("aabaaaaabaab", "aabaabaabaaa");
        assert !is_same_necklace("abc", "cba");
        assert !is_same_necklace("xxyyy", "xxxyy");
        assert !is_same_necklace("xyxxz", "xxyxz");
        assert is_same_necklace("x", "x");
        assert !is_same_necklace("x", "xx");
        assert !is_same_necklace("x", "");
        assert is_same_necklace("", "");

        // Optional Bonus 1
        assert repeats("abc") == 1;
        assert repeats("abcabcabc") == 3;
        assert repeats("abcabcabcx") == 1;
        assert repeats("aaaaaa") == 6;
        assert repeats("a") == 1;
        assert repeats("") == 1;

        System.out.println("All test pass!");
        findMatchingNecklaces();
    }

    private static String rotate(String input) {
        return input.substring(1) + input.charAt(0);
    }

    public static boolean is_same_necklace(String input, String test) {

        int inputLength = input.length();
        int testLength = test.length();
        int counter = inputLength;
        String rotated = test;

        if (inputLength != testLength) {
            return false;
        }

        if (inputLength == 0) {
            return true;
        }

        while (counter > 0) {
            if (input.equals(rotated)) {
                return true;
            }
            rotated = rotate(rotated);
            counter--;
        }
        return false;
    }

    public static int repeats(String input) {
        int repetition = 1;
        int counter = input.length();
        String rotated = input;

        while (counter > 1) {
            rotated = rotate(rotated);
            if (rotated.equals(input)) {
                repetition++;
            }
            counter--;
        }

        return repetition;
    }

    public static void findMatchingNecklaces() {
        List<String> words = getWordsFromFile(WORDS_FILE_PATH);
        int totalWords = words.size();
        List<String> fourSimilarNecklaces = null;

        for (int i = 0; i < totalWords; i++) {
            String word = words.get(i);
            List<String> matches = new ArrayList<>();
            matches.add(word);
            for (int j = i + 1; j < totalWords; j++) {
                String test = words.get(j);
                if(is_same_necklace(word, test)) {
                    matches.add(test);
                }
            }

            if (matches.size() > 1) {
                System.out.print("[" + i + "/" + totalWords + "]");
                System.out.println(matches);
            }

            if (matches.size() >= 4) {
                fourSimilarNecklaces = matches;
            }
        }
        if (fourSimilarNecklaces != null) {
            System.out.println(fourSimilarNecklaces);
        }
     }

    private static List<String> getWordsFromFile(String filePath) {
        List<String> words = new ArrayList<>();
        URI fileUri;
        try {
            fileUri = Objects.requireNonNull(NecklaceMatching.class
                    .getClassLoader()
                    .getResource(filePath))
                    .toURI();

            Path path = Paths.get(fileUri);
            Stream<String> lines = Files.lines(path);
            words = lines.collect(Collectors.toList());
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        return words;
    }
}


