package com.java.control.flow;

import java.util.Scanner;

/**
 * ============================================================
 * 11. SUM OF THE DIGITS OF A NUMBER
 * ============================================================
 *
 * CONCEPT:
 * Extract each digit of a number and add them together.
 *
 * Example:
 *   Number = 456
 *   Digits = 4, 5, 6
 *   Sum    = 4 + 5 + 6 = 15
 *
 * HOW IT WORKS (Control Flow focus):
 * We use a while loop because we don't know in advance
 * how many digits the number has.
 *
 *   while (number > 0) {
 *       digit = number % 10;   // get last digit
 *       sum  = sum + digit;
 *       number = number / 10;  // remove last digit
 *   }
 */
public class SumOfDigits {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void sumWithoutMethod() {
        int number = 456;
        int original = number;
        int sum = 0;

        // Loop until the number becomes 0
        while (number > 0) {
            int digit = number % 10;   // extract last digit
            sum = sum + digit;         // add digit to sum
            number = number / 10;      // remove the last digit
        }

        System.out.println("Number       : " + original);
        System.out.println("Sum of digits: " + sum);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Calculates the sum of digits of a given number.
     * Handles negative numbers by taking absolute value.
     */
    public static int sumOfDigits(int number) {
        number = Math.abs(number);   // make positive
        int sum = 0;

        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void sumWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int number = sc.nextInt();

        int result = sumOfDigits(number);
        System.out.println("Sum of digits of " + number + " is: " + result);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        sumWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        System.out.println("Sum of 456  = " + sumOfDigits(456));
        System.out.println("Sum of 9999 = " + sumOfDigits(9999));
        System.out.println("Sum of 0    = " + sumOfDigits(0));

        System.out.println("\n===== VERSION 3: With User Input =====");
        sumWithUserInput();
    }
}
