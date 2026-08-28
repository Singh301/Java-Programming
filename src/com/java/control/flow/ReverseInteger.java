package com.java.control.flow;

import java.util.Scanner;

/**
 * ============================================================
 * 20. REVERSE AN INTEGER USING WHILE LOOP
 * ============================================================
 *
 * CONCEPT:
 * Reverse the digits of an integer.
 *
 * Example:
 *   1234 → 4321
 *   100  → 1
 *  -123  → -321
 *
 * HOW (using while loop):
 *   while (number != 0) {
 *       digit = number % 10;
 *       reversed = reversed * 10 + digit;
 *       number = number / 10;
 *   }
 *
 * CONTROL FLOW FOCUS:
 * while loop is ideal because the number of digits is unknown.
 */
public class ReverseInteger {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void reverseWithoutMethod() {
        int number = 1234;
        int original = number;
        int reversed = 0;

        while (number != 0) {
            int digit = number % 10;              // extract last digit
            reversed = reversed * 10 + digit;     // build reverse
            number = number / 10;                 // remove last digit
        }

        System.out.println("Original : " + original);
        System.out.println("Reversed : " + reversed);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Reverses the digits of an integer.
     * Handles negative numbers.
     */
    public static int reverse(int number) {
        boolean isNegative = number < 0;
        number = Math.abs(number);

        int reversed = 0;

        while (number > 0) {
            int digit = number % 10;
            reversed = reversed * 10 + digit;
            number /= 10;
        }

        return isNegative ? -reversed : reversed;
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void reverseWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter an integer: ");
        int number = sc.nextInt();

        int result = reverse(number);
        System.out.println("Reversed integer: " + result);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        reverseWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        System.out.println("1234 reversed = " + reverse(1234));
        System.out.println("100  reversed = " + reverse(100));
        System.out.println("-456 reversed = " + reverse(-456));
        System.out.println("0    reversed = " + reverse(0));

        System.out.println("\n===== VERSION 3: With User Input =====");
        reverseWithUserInput();
    }
}
