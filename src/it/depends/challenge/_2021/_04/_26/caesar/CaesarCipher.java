package it.depends.challenge._2021._04._26.caesar;

/***
 * The challenge initially posted here:
 * <link>https://www.reddit.com/r/dailyprogrammer/comments/myx3wn/20210426_challenge_387_easy_caesar_cipher/</link>
 *<br>
 * Given a lowercase letter and a number between 0 and 26, return that letter Caesar shifted by that number.
 * To Caesar shift a letter by a number, advance it in the alphabet by that many steps,
 * wrapping around from z back to a:
 *<br>
 *<br>
 * <pre>
 *     warmup('a', 0) => 'a'
 *     warmup('a', 1) => 'b'
 *     warmup('a', 5) => 'f'
 *     warmup('a', 26) => 'a'
 *     warmup('d', 15) => 's'
 *     warmup('z', 1) => 'a'
 *     warmup('q', 22) => 'm'
 * </pre>
 * <br>
 * <br>
 * Hint: taking a number modulo 26 will wrap around from 25 back to 0.
 * This is commonly represented using the modulus operator %. For example, 29 % 26 = 3.
 * Finding a way to map from the letters a-z to the numbers 0-25 and back will help.
 *
 * <h2>Challenge:</h2>
 *
 * Given a string of lowercase letters and a number,
 * return a string with each letter Caesar shifted by the given amount.</br>
 * <pre>
 *      caesar("a", 1) => "b"
 *      caesar("abcz", 1) => "bcda"
 *      caesar("irk", 13) => "vex"
 *      caesar("fusion", 6) => "layout"
 *      caesar("dailyprogrammer", 6) => "jgorevxumxgsskx"
 *      caesar("jgorevxumxgsskx", 20) => "dailyprogrammer"
 * </pre>
 *
 * <h2>Optional bonus 1</h2>
 *
 * Correctly handle capital letters and non-letter characters.
 * Capital letters should also be shifted like lowercase letters,
 * but remain capitalized. Leave non-letter characters, such as spaces and punctuation, un-shifted.
 * <pre>
 *     caesar("Daily Programmer!", 6) => "Jgore Vxumxgsskx!"
 * </pre>
 *
 * If you speak a language that doesn't use the 26-letter A-Z alphabet that English does,
 * handle strings in that language in whatever way makes the most sense to you! In English,
 * if a string is encoded using the number N, you can decode it using the number 26 - N.
 * Make sure that for your language, there's some similar way to decode strings.
 *
 * <h2>Optional bonus 2</h2>
 * Given a string of English text that has been Caesar shifted by some number between 0 and 26,
 * write a function to make a best guess of what the original string was.
 * You can typically do this by hand easily enough,
 * but the challenge is to write a program to do it automatically.
 * Decode the following strings:
 *
 * <pre>
 * Zol abyulk tl puav h ulda.
 *
 * Tfdv ef wlikyvi, wfi uvrky rnrzkj pfl rcc nzky erjkp, szx, gfzekp kvvky.
 *
 * Qv wzlmz bw uiqvbiqv iqz-axmml dmtwkqbg, i aeittwe vmmla bw jmib qba eqvoa nwzbg-bpzmm bquma mdmzg amkwvl, zqopb?
 * </pre>
 *
 * One simple way is by using a letter frequency table.
 * Assign each letter in the string a score, with 3 for a, -1 for b, 1 for c, etc., as follows:
 * <pre>
 *     3,-1,1,1,4,0,0,2,2,-5,-2,1,0,2,3,0,-6,2,2,3,1,-1,0,-5,0,-7
 * </pre>
 *
 * The average score of the letters in a string will tell you how its letter
 * distribution compares to typical English. Higher is better.
 * Typical English will have an average score around 2,
 * and strings of random letters will have an average score around 0.
 * Just test out each possible shift for the string, and take the one with the highest score.
 * There are other good ways to do it, though.
 *
 */

public class CaesarCipher {
    public static void main(String[] args) {
        //noinspection SpellCheckingInspection
        System.out.println(unshift("ABCDEFGHIKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwzyz", 1));
        System.out.println(shift("Daily Programmer!", 6));
        System.out.println(unshift("Jgore Vxumxgsskx!", 6));
    }

    private static char warm(char character, int shift) {

        if(!Character.isAlphabetic(character)) {
            return character;
        }

        int min = 'a';
        int max = 'z';

        if(Character.isUpperCase(character)) {
            min = 'A';
            max = 'Z';
        }

        int shiftedCharAscii = character + (shift % 26);
        if(shiftedCharAscii > max) {
            shiftedCharAscii = min + (shiftedCharAscii - max - 1);
        }
        return (char) shiftedCharAscii;
    }

    private static char cool(char character, int shift){
        if(!Character.isAlphabetic(character)) {
            return character;
        }

        int min = 'a';
        int max = 'z';

        if(Character.isUpperCase(character)) {
            min = 'A';
            max = 'Z';
        }

        int shiftedCharAscii = character - (shift % 26);
        if(shiftedCharAscii < min) {
            shiftedCharAscii = max - (min - shiftedCharAscii - 1);
        }

        return (char) shiftedCharAscii;
    }

    public static String shift(String plain, int shift) {
        char[] characters = plain.toCharArray();
        StringBuilder shiftedString = new StringBuilder();
        for(char character: characters) {
            shiftedString.append(warm(character, shift));
        }

        return shiftedString.toString();
    }

    public static String unshift(String plain, int shift) {
        char[] characters = plain.toCharArray();
        StringBuilder shiftedString = new StringBuilder();
        for(char character: characters) {
            shiftedString.append(cool(character, shift));
        }

        return shiftedString.toString();
    }

}
