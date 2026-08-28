package com.java.basic;

import java.util.Scanner;

/**
 * ============================================================
 * 17. SUM OF DIGITS OF A NUMBER
 * ============================================================
 *
 * TASK:
 * Calculate the sum of all digits of a given number.
 *
 * Example:
 *   Number = 1234
 *   Sum    = 1 + 2 + 3 + 4 = 10
 *
 * HOW:
 * Extract each digit from the right using % 10,
 * then remove that digit using / 10.
 * Keep adding the digits until the number becomes 0.
 *
 * This is a very common building block for many problems
 * (Armstrong, digital root, etc.).
 */
public class SumOfDigits {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void sumWithoutMethod() {
        int number = 1234;
        int original = number;
        int sum = 0;

        while (number > 0) {
            int digit = number % 10;   // get last digit
            sum = sum + digit;         // add it to sum
            number = number / 10;      // remove last digit
        }

        System.out.println("Number     : " + original);
        System.out.println("Sum of digits: " + sum);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Returns the sum of digits of the given number.
     * Works for positive numbers. For negative, we take absolute value.
     */
    public static int sumOfDigits(int number) {
        number = Math.abs(number);   // handle negative numbers
        int sum = 0;

        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    /**
     * Recursive version
     */
    public static int sumOfDigitsRecursive(int number) {
        number = Math.abs(number);
        if (number == 0) {
            return 0;
        }
        return (number % 10) + sumOfDigitsRecursive(number / 10);
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void sumWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int number = sc.nextInt();

        System.out.println("Sum of digits (iterative) = " + sumOfDigits(number));
        System.out.println("Sum of digits (recursive) = " + sumOfDigitsRecursive(number));
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        sumWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        System.out.println("Sum of digits of 1234 = " + sumOfDigits(1234));
        System.out.println("Sum of digits of 999  = " + sumOfDigits(999));
        System.out.println("Sum of digits of 0    = " + sumOfDigits(0));

        System.out.println("\n===== VERSION 3: With User Input =====");
        sumWithUserInput();
    }
}
