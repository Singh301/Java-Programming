package com.java.basic;

import java.util.Scanner;

/**
 * ============================================================
 * 12. EVEN OR ODD
 * ============================================================
 *
 * WHAT IS EVEN AND ODD?
 * ---------------------
 * - Even number: Divisible by 2 (remainder 0)
 *   Examples: 2, 4, 6, 8, 10, 100...
 *
 * - Odd number: Not divisible by 2 (remainder 1)
 *   Examples: 1, 3, 5, 7, 9, 101...
 *
 * In programming we use the modulus operator (%) to check:
 *   number % 2 == 0  → Even
 *   number % 2 != 0  → Odd
 *
 * This is one of the simplest and most fundamental programs.
 */
public class EvenOrOdd {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void checkWithoutMethod() {
        int number = 17;

        if (number % 2 == 0) {
            System.out.println(number + " is Even");
        } else {
            System.out.println(number + " is Odd");
        }
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Returns true if the number is even, false if odd.
     */
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    /**
     * Prints whether the number is even or odd.
     */
    public static void checkEvenOdd(int number) {
        if (isEven(number)) {
            System.out.println(number + " is Even");
        } else {
            System.out.println(number + " is Odd");
        }
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void checkWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int number = sc.nextInt();

        checkEvenOdd(number);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        checkWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        checkEvenOdd(24);
        checkEvenOdd(37);
        System.out.println("Is 10 even? " + isEven(10));

        System.out.println("\n===== VERSION 3: With User Input =====");
        checkWithUserInput();
    }
}
