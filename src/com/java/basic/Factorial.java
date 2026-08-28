package com.java.basic;

import java.util.Scanner;

/**
 * ============================================================
 * 5. FACTORIAL
 * ============================================================
 *
 * WHAT IS FACTORIAL?
 * ------------------
 * The factorial of a non-negative integer n is the product of
 * all positive integers less than or equal to n.
 *
 * It is denoted by n!
 *
 * Examples:
 *   5! = 5 × 4 × 3 × 2 × 1 = 120
 *   4! = 4 × 3 × 2 × 1 = 24
 *   0! = 1   (by definition)
 *   1! = 1
 *
 * Factorial grows very quickly, so for large numbers we need
 * BigInteger, but for basic programs int/long is enough.
 *
 * Two common ways:
 * 1. Iterative (using loop) → preferred for beginners
 * 2. Recursive (method calls itself)
 */
public class Factorial {

    // ============================================================
    // VERSION 1: WITHOUT METHOD (Iterative)
    // ============================================================
    public static void factorialWithoutMethod() {
        int number = 5;
        long result = 1;   // use long to handle larger factorials

        // Multiply all numbers from 1 to number
        for (int i = 1; i <= number; i++) {
            result = result * i;
        }

        System.out.println("Factorial of " + number + " is: " + result);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Calculates factorial using iteration (loop).
     * Preferred approach for most cases.
     */
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }

        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Calculates factorial using recursion.
     * Easy to understand but can cause StackOverflow for large n.
     */
    public static long factorialRecursive(int n) {
        // Base case
        if (n == 0 || n == 1) {
            return 1;
        }
        // Recursive case: n! = n × (n-1)!
        return n * factorialRecursive(n - 1);
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void factorialWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a non-negative number: ");
        int number = sc.nextInt();

        if (number < 0) {
            System.out.println("Factorial is not defined for negative numbers.");
            return;
        }

        System.out.println("Factorial of " + number + " (iterative) = " + factorial(number));
        System.out.println("Factorial of " + number + " (recursive) = " + factorialRecursive(number));
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        factorialWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        System.out.println("5! = " + factorial(5));
        System.out.println("7! = " + factorial(7));
        System.out.println("0! = " + factorial(0));

        System.out.println("\n===== VERSION 3: With User Input =====");
        factorialWithUserInput();
    }
}
