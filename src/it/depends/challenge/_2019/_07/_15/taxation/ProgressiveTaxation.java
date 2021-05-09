package it.depends.challenge._2019._07._15.taxation;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

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
 */

public class ProgressiveTaxation {
    private static final String TAX_FILE_PATH = "resources/taxation.csv";

    @SuppressWarnings("ConstantConditions")
    public static void main(String[] args) {
        TaxTable taxTable = readTaxTableFromFile(TAX_FILE_PATH);

        assert taxTable.getTax(0) == 0;
        assert taxTable.getTax(10000) == 0;
        assert taxTable.getTax(10009) == 0;
        assert taxTable.getTax(10010) == 1;
        assert taxTable.getTax(12000) == 200;
        assert taxTable.getTax(56789) == 8697;
        assert taxTable.getTax(1234567) == 473326;

        assert taxTable.getIncomeForOverallTax(0f) == 0;
        assert taxTable.getIncomeForOverallTax(0.06f) == 25000;
        assert taxTable.getIncomeForOverallTax(0.09f) == 34379;
        assert taxTable.getIncomeForOverallTax(0.32f) == 256249;
        assert taxTable.getIncomeForOverallTax(0.40f) == null;
    }

    @SuppressWarnings("SameParameterValue")
    private static TaxTable readTaxTableFromFile(String filePath) {
        ClassLoader classLoader = ProgressiveTaxation.class.getClassLoader();
        URL resource = classLoader.getResource(filePath);
        TaxTable table = new TaxTable();

        if (resource != null) {
            String path = resource.getPath();
            try {
                Stream<String> lines = Files.lines(Path.of(path));
                final int[] previousLimit = {0};
                // This is the last row
                lines.map(line -> line.split(","))
                        .filter(tokens -> tokens.length == 2)
                        .skip(1)
                        .forEach(tokens -> getTaxBrackets(table, previousLimit, tokens));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return table;
    }

    private static void getTaxBrackets(TaxTable table, int[] previousLimit, String[] tokens) {

        assert tokens.length == 2;

        if (previousLimit[0] == Integer.MAX_VALUE) {
            // In case there are more rows after the '--' bracket.
            return;
        }

        String incomeCap = tokens[0].trim();
        int lowerLimit = previousLimit[0];
        int upperLimit;
        if (incomeCap.equals("--")) {
            upperLimit = Integer.MAX_VALUE;
            previousLimit[0] = Integer.MAX_VALUE;
        } else {
            upperLimit = parseInt(tokens[0]);
            previousLimit[0] = upperLimit;
        }
        float tax = parseFloat(tokens[1]);

        table.addBracket(new TaxBracket(lowerLimit, upperLimit, tax));
    }
}


final class TaxBracket {
    private int lowerLimit;
    private int upperLimit;
    private float tax;
    private Float upperOverallTax;
    private Float lowerOverallTax;

    public TaxBracket(int lowerLimit, int upperLimit, float tax) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.tax = tax;
    }

    public Float getUpperOverallTax() {
        return upperOverallTax;
    }

    public void setUpperOverallTax(float upperOverallTax) {
        this.upperOverallTax = upperOverallTax;
    }

    public int getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(int lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public int getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(int upperLimit) {
        this.upperLimit = upperLimit;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public Float getLowerOverallTax() {
        return lowerOverallTax;
    }

    public void setLowerOverallTax(Float lowerOverallTax) {
        this.lowerOverallTax = lowerOverallTax;
    }

    @Override
    public String toString() {
        return "[lowerLimit = " + lowerLimit + ", " +
                "upperLimit = " + upperLimit + ", " +
                "tax = " + tax + ", " +
                "lowerOverallTax = " + lowerOverallTax + ", " +
                "upperOverallTax = " + upperOverallTax + "]";
    }
}

final class TaxTable {
    private static final int PRECISION = 5;
    private List<TaxBracket> brackets;
    private int totalBrackets = 0;

    public TaxTable() {
        brackets = new ArrayList<>();
    }

    public List<TaxBracket> getBrackets() {
        return Collections.unmodifiableList(brackets);
    }

    public void setBrackets(List<TaxBracket> brackets) {
        this.brackets = brackets;
        totalBrackets = brackets.size();
    }

    public void addBracket(TaxBracket bracket) {
        brackets.add(bracket);
        totalBrackets = brackets.size();

        if (bracket.getLowerLimit() != 0) {
            bracket.setLowerOverallTax(getOverallTax(bracket.getLowerLimit()));
        } else {
            bracket.setLowerOverallTax(0f);
        }

        bracket.setUpperOverallTax(getOverallTax(bracket.getUpperLimit()));
    }

    public int getTotalBrackets() {
        return totalBrackets;
    }

    public int getTax(int income) {
        int totalTax = 0;
        int remainingIncome = income;

        for (int i = totalBrackets - 1; i > 0; i--) {
            TaxBracket bracket = brackets.get(i);
            if (remainingIncome > bracket.getLowerLimit()) {
                int difference = remainingIncome - bracket.getLowerLimit();
                totalTax += (int) (difference * bracket.getTax());
                remainingIncome -= difference;
            }
        }

        return totalTax;
    }

    public Integer getIncomeForOverallTax(float overallTax) {
        // We will use the binary search algorithm to
        // find the income range for overall tax;

        // We can narrow down the range of binary search
        // if we can find the tax bracket where the income lies.
        // We have already calculated the overAll tax for each tax bracket.
        // Find the tax bracket where the overall tax lies.
        TaxBracket bracket = brackets.stream()
                .filter(b -> b.getUpperOverallTax() != null)
                .filter(b -> b.getLowerOverallTax() <= overallTax && b.getUpperOverallTax() >= overallTax)
                .findFirst()
                .orElse(null);

        if (bracket == null) {
            return null;
        } else if (overallTax == bracket.getLowerOverallTax()) {
            return bracket.getLowerLimit();
        } else if (overallTax == bracket.getUpperOverallTax()) {
            return bracket.getUpperLimit();
        }

        int lower = bracket.getLowerLimit();
        int upper = bracket.getUpperLimit();
        int midIncome = (lower + upper) / 2;
        float midOverallTax = getOverallTax(midIncome);
        int totalIterations = 0;

        // we will cap the search to 100 iterations.
        while (midOverallTax != overallTax && totalIterations < 100) {
            if (midOverallTax > overallTax) {
                upper = midIncome;
            } else {
                lower = midIncome;
            }
            midIncome = (lower + upper) / 2;
            midOverallTax = getOverallTax(midIncome);
            totalIterations++;
        }

        return midIncome;
    }

    private float getOverallTax(int income) {
        float totalTax = getTax(income);
        return setPrecision(totalTax / income, PRECISION);
    }

    @SuppressWarnings("SameParameterValue")
    private float setPrecision(float number, int decimal) {
        return Float.parseFloat(String.format("%." + decimal + "f", number));
    }

    @Override
    public String toString() {
        return brackets.stream().map(TaxBracket::toString).collect(Collectors.joining("\n"));
    }
}
