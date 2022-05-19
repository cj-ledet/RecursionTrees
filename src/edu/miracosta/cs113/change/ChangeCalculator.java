package edu.miracosta.cs113.change;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;

/**
 * ChangeCalculator : Class containing the recursive method calculateChange, which determines and prints all
 * possible coin combinations representing a given monetary value in cents.
 *
 * Problem derived from Koffman & Wolfgang's Data Structures: Abstraction and Design Using Java (2nd ed.):
 * Ch. 5, Programming Project #7, pg. 291.
 *
 * NOTE: An additional method, printCombinationsToFile(int), has been added for the equivalent tester file to
 * verify that all given coin combinations are unique.
 */
public class ChangeCalculator {

    //Note: Using HashSet as the combination order should not matter
    private static HashSet<String> combinations = new HashSet<>();
    public static File outputFile = new File("src/edu/miracosta/cs113/change/CoinCombinations.txt");

    /**
     * Wrapper method for determining all possible unique combinations of quarters, dimes, nickels, and pennies that
     * equal the given monetary value in cents.
     *
     * In addition to returning the number of unique combinations, this method will print out each combination to the
     * console. The format of naming each combination is up to the user, as long as they adhere to the expectation
     * that the coins are listed in descending order of their value (quarters, dimes, nickels, then pennies). Examples
     * include "1Q 2D 3N 4P", and "[1, 2, 3, 4]".
     *
     * @param cents a monetary value in cents
     * @return the total number of unique combinations of coins of which the given value is comprised
     */

    public static int calculateChange(int cents) {
        combinations.clear(); //reset our combos stored for new call

        int q = 0, d = 0, n = 0, p = cents;

        return calculateChange(q, d, n, p);
    }

    public static int calculateChange(int q, int d, int n, int p) {
        // TODO:
        // Implement a recursive solution following the given documentation.

        String currentCombination = "[" + q + ", " + d + ", " + n + ", " + p + "]";

        if (!combinations.contains(currentCombination)) {
            //System.out.println(currentCombination); Uncomment to print the combinations to console AND output file
            combinations.add(currentCombination);
        }

        if (p == 0)
            return combinations.size();

        if (p >= 5)
            calculateChange(q, d, n + 1, p - 5);

        if (p >= 10)
            calculateChange(q, d + 1, n, p - 10);

        if (p >= 25)
            calculateChange(q + 1, d, n, p - 25);

        return combinations.size();
    }

    /**
     * Calls upon calculateChange(int) to calculate and print all possible unique combinations of quarters, dimes,
     * nickels, and pennies that equal the given value in cents.
     *
     * Similar to calculateChange's function in printing each combination to the console, this method will also
     * produce a text file named "CoinCombinations.txt", writing each combination to separate lines.
     *
     * @param cents a monetary value in cents
     */
    public static void printCombinationsToFile(int cents) {
        calculateChange(cents);
        // TODO:
        // This when calculateChange is complete. Note that the text file must be created within this directory.

        try {
            PrintWriter writer = new PrintWriter(outputFile);
            Iterator<String> iterator = combinations.iterator();

            while (iterator.hasNext()) {
                writer.append(iterator.next());
                writer.append("\n");
            }

            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error locating file: " + e);
        }
    }

} // End of class ChangeCalculator