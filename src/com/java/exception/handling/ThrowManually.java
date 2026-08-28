package com.java.exception.handling;

import java.util.Scanner;

/**
 * ============================================================
 * 89. THROW EXCEPTIONS MANUALLY
 * ============================================================
 *
 * CONCEPT:
 * We can explicitly throw an exception using the "throw" keyword.
 *
 * Syntax: throw new SomeException("message");
 *
 * Use cases:
 * - Validate method arguments
 * - Signal business rule violations
 * - Re-throw exceptions after partial handling
 *
 * Difference:
 * - throw  → used to throw an exception object
 * - throws → used in method signature to declare checked exceptions
 */
public class ThrowManually {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        int age = -5;

        try {
            if (age < 0) {
                throw new IllegalArgumentException("Age cannot be negative: " + age);
            }
            System.out.println("Age is valid: " + age);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught manually thrown exception: " + e.getMessage());
        }
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void validateScore(int score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException(
                    "Score must be between 0 and 100. Provided: " + score);
        }
        System.out.println("Score " + score + " is valid.");
    }

    public static void checkScore(int score) {
        try {
            validateScore(score);
        } catch (IllegalArgumentException e) {
            System.out.println("Validation error: " + e.getMessage());
        }
    }

    /**
     * Example of re-throwing an exception.
     */
    public static void processValue(String value) {
        try {
            int num = Integer.parseInt(value);
            if (num < 0) {
                throw new IllegalArgumentException("Negative numbers not allowed");
            }
            System.out.println("Processed: " + num);
        } catch (NumberFormatException e) {
            // Wrap and re-throw
            throw new IllegalArgumentException("Invalid number format: " + value, e);
        }
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a score (0-100): ");
        int score = sc.nextInt();
        checkScore(score);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        checkScore(85);
        checkScore(150);
        checkScore(-10);

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
