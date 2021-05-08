package it.depends.challenge._2019._07._15.taxation;

/**
 * <h1>[2019-07-15] Challenge #379 [Easy] Progressive taxation
 * Challenge
 *
 * <p>The nation of Examplania has the following income tax brackets:
 *
 * <pre>
 * income cap      marginal tax rate
 * ----------------------------------
 *    ¤10,000              0.00 (0%)
 *    ¤30,000             0.10 (10%)
 *   ¤100,000             0.25 (25%)
 *         --             0.40 (40%)
 * </pre>
 *
 * <p>
 * If you're not familiar with how tax brackets work, see the section below for an explanation.
 *
 * <p>
 * Given a whole-number income amount up to ¤100,000,000, find the amount of tax owed in Examplania.
 * Round down to a whole number of ¤.
 *
 * <p><p><b>Examples</b>
 *
 * <pre>
 * tax(0) => 0
 * tax(10000) => 0
 * tax(10009) => 0
 * tax(10010) => 1
 * tax(12000) => 200
 * tax(56789) => 8697
 * tax(1234567) => 473326
 *  </pre>
 *
 * <p> <h2>Optional improvement</h2>
 *
 * <p>
 * One way to improve your code is to make it easy to swap out different tax brackets,
 * for instance by having the table in an input file.
 * If you do this, you may assume that both the income caps and marginal tax rates are in increasing order,
 * the highest bracket has no income cap,
 * and all tax rates are whole numbers of percent (no more than two decimal places).
 *
 * <p>
 * However, because this is an Easy challenge, this part is optional,
 * and you may hard code the tax brackets if you wish.
 *
 * <p><h2>How tax brackets work</h2>
 *
 * <p>
 * A tax bracket is a range of income based on the income caps,
 * and each tax bracket has a corresponding marginal tax rate, which applies to income within the bracket.
 * In our example, the tax bracket for the range ¤10,000 to ¤30,000 has a marginal tax rate of 10%.
 * Here's what that means for each bracket:
 *
 * <p>
 * If your income is less than {@code ¤10,000}, you owe {@code 0} income tax.
 *
 * <p>
 * If your income is between {@code ¤10,000} and {@code ¤30,000},
 * you owe 10% income tax on the income that exceeds {@code ¤10,000}.
 * For instance, if your income is {@code ¤18,000}, then your income in the {@code 10%} bracket is {@code ¤8,000}.
 * So your income tax is {@code 10% of ¤8,000}, or {@code ¤800}.
 *
 * <p>
 * If your income is between {@code ¤30,000} and {@code ¤100,000}, then you owe {@code 10%} of your income
 * between {@code ¤10,000} and {@code ¤30,000}, plus {@code 25%} of your income over {@code ¤30,000}.
 *
 * <p>
 * And finally, if your income is over {@code ¤100,000}, then you owe {@code 10%} of your income
 * from {@code ¤10,000} to {@code ¤30,000}, plus {@code 25%} of your income from {@code ¤30,000} to {@code ¤100,000},
 * plus {@code 40%} of your income above {@code ¤100,000}.
 *
 * <p>
 * One aspect of progressive taxation is that increasing your income will
 * never decrease the amount of tax that you owe, or your overall tax rate (except for rounding).
 *
 * <p><h2>Optional bonus</h2>
 *
 * <p>
 * The overall tax rate is simply the total tax divided by the total income.
 * For example, an income of {@code ¤256,250} has an overall tax of {@code ¤82,000},
 * which is an overall tax rate of exactly {@code 32%}:
 *
 * <p>
 * <pre>
 * 82000 = 0.00 × 10000 + 0.10 × 20000 + 0.25 × 70000 + 0.40 × 156250
 * 82000 = 0.32 × 256250
 * </pre>
 *
 * <p>
 * Given a target overall tax rate,
 * find the income amount that would be taxed at that overall rate in Examplania:
 *
 * <p><pre>
 * overall(0.00) => 0 // (or anything up to 10000)
 * overall(0.06) => 25000
 * overall(0.09) => 34375
 * overall(0.32) => 256250
 * overall(0.40) => NaN // (or anything to signify that no such income value exists)
 * </pre>
 *
 * <p>
 * You may get somewhat different answers because of rounding, but as long as it's close that's fine.
 *
 * <p>
 * The simplest possibility is just to iterate and check the overall tax rate for each possible income.
 * That works fine, but if you want a performance boost, check out binary search.
 * You can also use algebra to reduce the number of calculations needed;
 * just make it so that your code still gives correct answers if you swap out a different set of tax brackets.
 *
 */

public class ProgressiveTaxation {
    public static void main(String[] args) {

    }
}
