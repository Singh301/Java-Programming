package com.java.exception.handling;

import java.util.Scanner;

/**
 * ============================================================
 * 84. FINALLY BLOCK
 * ============================================================
 *
 * CONCEPT:
 * The finally block always executes, whether an exception
 * occurs or not (except in cases like System.exit()).
 *
 * Common uses:
 * - Closing resources (files, connections, scanners)
 * - Cleanup code
 * - Guaranteed execution of important statements
 *
 * Structure:
 * try { ... }
 * catch { ... }
 * finally { ... }   ← always runs
 *
 * Note: From Java 7 we also have try-with-resources which
 * automatically closes resources.
 */
public class FinallyBlockDemo {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        try {
            System.out.println("Inside try block");
            int result = 10 / 0;   // will throw exception
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Exception caught: " + e.getMessage());
        } finally {
            System.out.println("Finally block executed (cleanup happens here)");
        }
        System.out.println("Program continues after try-catch-finally");
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static int divideAndCleanup(int a, int b) {
        try {
            System.out.println("Trying to divide " + a + " by " + b);
            return a / b;
        } catch (ArithmeticException e) {
            System.out.println("Exception: " + e.getMessage());
            return -1;
        } finally {
            System.out.println("Finally: Resources cleaned up / log written");
        }
    }

    public static void demonstrateFinallyAlwaysRuns() {
        System.out.println("Case 1 - Exception occurs:");
        System.out.println("Result = " + divideAndCleanup(10, 0));

        System.out.println("\nCase 2 - No exception:");
        System.out.println("Result = " + divideAndCleanup(10, 2));
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter numerator: ");
        int a = sc.nextInt();
        System.out.print("Enter denominator: ");
        int b = sc.nextInt();

        int result = divideAndCleanup(a, b);
        System.out.println("Final returned value: " + result);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        demonstrateFinallyAlwaysRuns();

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
