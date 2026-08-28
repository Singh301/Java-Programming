package com.java.basic;

import java.util.Scanner;

/**
 * ============================================================
 * 19. REVERSE THE DIGITS OF A NUMBER
 * ============================================================
 *
 * TASK:
 * Reverse the digits of a given integer.
 *
 * Examples:
 *   1234  → 4321
 *   100   → 1     (leading zeros disappear)
 *   -123  → -321  (handle is preserved)
 *
 * HOW:
 * Extract digits from right to left using % 10
 * and build the reverse number by multiplying by 10 each time.
 *
 * Careful with:
 * - Integer overflow (for very large numbers)
 * - Negative numbers
 * - Numbers ending with 0
 */
public class ReverseDigits {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void reverseWithoutMethod() {
        int number = 1234;
        int original = number;
        int reversed = 0;

        while (number != 0) {
            int digit = number % 10;           // extract last digit
            reversed = reversed * 10 + digit;  // append it to reverse
            number = number / 10;              // remove last digit
        }

        System.out.println("Original : " + original);
        System.out.println("Reversed : " + reversed);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Reverses the digits of the given number.
     * Handles negative numbers correctly.
     */
    public static int reverseDigits(int number) {
        boolean isNegative = number < 0;
        number = Math.abs(number);

        int reversed = 0;

        while (number > 0) {
            int digit = number % 10;

            // Optional: check for overflow
            // if (reversed > Integer.MAX_VALUE / 10) { ... }

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

        System.out.print("Enter a number: ");
        int number = sc.nextInt();

        int result = reverseDigits(number);
        System.out.println("Reversed number: " + result);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        reverseWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        System.out.println("1234 reversed = " + reverseDigits(1234));
        System.out.println("100 reversed  = " + reverseDigits(100));
        System.out.println("-123 reversed = " + reverseDigits(-123));
        System.out.println("0 reversed    = " + reverseDigits(0));

        System.out.println("\n===== VERSION 3: With User Input =====");
        reverseWithUserInput();
    }
}
