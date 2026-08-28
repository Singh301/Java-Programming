package com.java.exception.handling;

import java.util.Scanner;

/**
 * ============================================================
 * 82. CREATE A CUSTOM EXCEPTION CLASS
 * ============================================================
 *
 * CONCEPT:
 * Java allows us to create our own exception classes by extending
 * Exception (checked) or RuntimeException (unchecked).
 *
 * Why custom exceptions?
 * - More meaningful error messages
 * - Better error handling specific to business logic
 * - Cleaner code
 *
 * Example: InvalidAgeException for age validation.
 */
public class CustomExceptionClass {

    // Custom Checked Exception
    static class InvalidAgeException extends Exception {
        public InvalidAgeException(String message) {
            super(message);
        }

        public InvalidAgeException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        int age = 15;

        try {
            if (age < 18) {
                throw new InvalidAgeException("Age " + age + " is less than 18. Not eligible to vote.");
            }
            System.out.println("Eligible to vote.");
        } catch (InvalidAgeException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void validateAge(int age) throws InvalidAgeException {
        if (age < 0) {
            throw new InvalidAgeException("Age cannot be negative: " + age);
        }
        if (age < 18) {
            throw new InvalidAgeException("Age must be 18 or above. Provided: " + age);
        }
        System.out.println("Age " + age + " is valid. Eligible!");
    }

    public static void checkEligibility(int age) {
        try {
            validateAge(age);
        } catch (InvalidAgeException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your age: ");
        int age = sc.nextInt();
        checkEligibility(age);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        checkEligibility(25);
        checkEligibility(12);
        checkEligibility(-5);

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
