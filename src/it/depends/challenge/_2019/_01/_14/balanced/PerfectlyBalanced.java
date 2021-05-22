package it.depends.challenge._2019._01._14.balanced;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>[2019-01-14] Challenge #372 [Easy] Perfectly balanced</h1>
 * <p>
 * Given a string containing only the characters x and y, find whether there are the same number of xs and ys.
 * <p>
 * <pre>
 * balanced("xxxyyy") => true
 * balanced("yyyxxx") => true
 * balanced("xxxyyyy") => false
 * balanced("yyxyxxyxxyyyyxxxyxyx") => true
 * balanced("xyxxxxyyyxyxxyxxyy") => false
 * balanced("") => true
 * balanced("x") => false
 * </pre>
 * <p>
 * <h2>Optional bonus</h2>
 * <p>
 * Given a string containing only lowercase letters,
 * find whether every letter that appears in the string appears the same number of times.
 * Don't forget to handle the empty string ("") correctly!
 * <p>
 * <pre>
 * balanced_bonus("xxxyyyzzz") => true
 * balanced_bonus("abccbaabccba") => true
 * balanced_bonus("xxxyyyzzzz") => false
 * balanced_bonus("abcdefghijklmnopqrstuvwxyz") => true
 * balanced_bonus("pqq") => false
 * balanced_bonus("fdedfdeffeddefeeeefddf") => false
 * balanced_bonus("www") => true
 * balanced_bonus("x") => true
 * balanced_bonus("") => true
 * </pre>
 * <p>
 * <b>Note that balanced_bonus behaves differently than balanced for a few inputs, e.g. "x".</b>
 */
public class PerfectlyBalanced {
    @SuppressWarnings({"PointlessBooleanExpression", "SpellCheckingInspection"})
    public static void main(String[] args) {
        assert balanced("xxxyyy") == true;
        assert balanced("yyyxxx") == true;
        assert balanced("xxxyyyy") == false;
        assert balanced("yyxyxxyxxyyyyxxxyxyx") == true;
        assert balanced("xyxxxxyyyxyxxyxxyy") == false;
        assert balanced("") == true;
        assert balanced("x") == false;

        assert balanced_bonus("xxxyyyzzz") == true;
        assert balanced_bonus("abccbaabccba") == true;
        assert balanced_bonus("xxxyyyzzzz") == false;
        assert balanced_bonus("abcdefghijklmnopqrstuvwxyz") == true;
        assert balanced_bonus("pqq") == false;
        assert balanced_bonus("fdedfdeffeddefeeeefddf") == false;
        assert balanced_bonus("www") == true;
        assert balanced_bonus("x") == true;
        assert balanced_bonus("") == true;

    }

    public static boolean balanced(String input) {
        long x = Arrays.stream(input.split("")).filter(c -> c.equals("x")).count();
        long y = Arrays.stream(input.split("")).filter(c -> c.equals("y")).count();
        return x == y;
    }

    public static boolean balanced_bonus(String input) {
        final Map<String, Integer> frequency = new HashMap<>();

        Arrays.stream(input.split("")).forEach(c -> {
            Integer count = frequency.getOrDefault(c, 0);
            frequency.put(c, count + 1);
        });

        String[] keys = frequency.keySet().toArray(String[]::new);

        int count = frequency.get(keys[0]);
        boolean isBalanced = true;
        for (int i = 1; i < keys.length; i++) {
            if (count != frequency.get(keys[i])) {
                isBalanced = false;
                break;
            }
        }
        return isBalanced;
    }
}
