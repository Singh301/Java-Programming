package com.java.basic;

import java.util.Scanner;

/**
 * ============================================================
 * 11. GCD (Greatest Common Divisor) / HCF
 * ============================================================
 *
 * WHAT IS GCD?
 * ------------
 * The Greatest Common Divisor (GCD) of two numbers is the
 * largest positive integer that divides both numbers without
 * leaving a remainder.
 *
 * It is also called Highest Common Factor (HCF).
 *
 * Examples:
 *   GCD(12, 18) = 6
 *   GCD(100, 25) = 25
 *   GCD(17, 13) = 1   (coprime numbers)
 *
 * BEST ALGORITHM: Euclidean Algorithm
 * -----------------------------------
 * GCD(a, b) = GCD(b, a % b)
 * Repeat until b becomes 0. Then a is the GCD.
 *
 * This is very efficient (logarithmic time).
 */
public class GCD {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void findGCDWithoutMethod() {
        int a = 48;
        int b = 18;

        int x = a;
        int y = b;

        // Euclidean algorithm
        while (y != 0) {
            int remainder = x % y;
            x = y;
            y = remainder;
        }

        // Now x holds the GCD
        System.out.println("GCD of " + a + " and " + b + " is: " + x);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Calculates GCD using Euclidean algorithm (iterative).
     */
    public static int gcd(int a, int b) {
        // Make numbers positive
        a = Math.abs(a);
        b = Math.abs(b);

        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /**
     * Recursive version of Euclidean algorithm.
     */
    public static int gcdRecursive(int a, int b) {
        if (b == 0) {
            return Math.abs(a);
        }
        return gcdRecursive(b, a % b);
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void findGCDWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number : ");
        int a = sc.nextInt();
        System.out.print("Enter second number: ");
        int b = sc.nextInt();

        System.out.println("GCD of " + a + " and " + b + " is: " + gcd(a, b));
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        findGCDWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        System.out.println("GCD(48, 18) = " + gcd(48, 18));
        System.out.println("GCD(100, 25) = " + gcd(100, 25));
        System.out.println("GCD(17, 13) = " + gcd(17, 13));

        System.out.println("\n===== VERSION 3: With User Input =====");
        findGCDWithUserInput();
    }
}
