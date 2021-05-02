package it.depends.challenge._2020._03._09.necklace;

public class NecklaceMatching {
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
            if(rotated.equals(input)) {
                repetition++;
            }
            counter --;
        }

        return repetition;
    }

    private static String rotate(String input) {
        return input.substring(1) + input.charAt(0);
    }
}
