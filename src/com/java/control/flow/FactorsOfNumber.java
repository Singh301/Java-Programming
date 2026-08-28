package com.java.control.flow;

import java.util.Scanner;

/**
 * ============================================================
 * 15. FIND ALL FACTORS OF A NUMBER
 * ============================================================
 *
 * CONCEPT:
 * A factor of a number is an integer that divides the number
 * completely (remainder = 0).
 *
 * Example:
 *   Number = 12
 *   Factors = 1, 2, 3, 4, 6, 12
 *
 * HOW:
 * Loop from 1 to the number itself.
 * If number % i == 0, then i is a factor.
 *
 * Optimization:
 * We only need to loop till sqrt(number).
 * For every factor found below sqrt, the pair (number/i)
 * is also a factor.
 */
public class FactorsOfNumber {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void findFactorsWithoutMethod() {
        int number = 36;

        System.out.print("Factors of " + number + ": ");

        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Prints all factors of the given number (simple way).
     */
    public static void printFactors(int number) {
        if (number <= 0) {
            System.out.println("Please enter a positive number");
            return;
        }

        System.out.print("Factors of " + number + ": ");
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    /**
     * More efficient version - only loops till sqrt(n).
     */
    public static void printFactorsEfficient(int number) {
        if (number <= 0) {
            System.out.println("Please enter a positive number");
            return;
        }

        System.out.print("Factors of " + number + " (efficient): ");
        for (int i = 1; i * i <= number; i++) {
            if (number % i == 0) {
                System.out.print(i + " ");
                // the pair factor
                if (i != number / i) {
                    System.out.print((number / i) + " ");
                }
            }
        }
        System.out.println();
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void findFactorsWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a positive number: ");
        int number = sc.nextInt();

        printFactors(number);
        printFactorsEfficient(number);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        findFactorsWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        printFactors(28);
        printFactorsEfficient(28);

        System.out.println("\n===== VERSION 3: With User Input =====");
        findFactorsWithUserInput();
    }
}
