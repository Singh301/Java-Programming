package com.java.control.flow;

import java.util.Scanner;

/**
 * ============================================================
 * 16. GCD USING EUCLIDEAN ALGORITHM
 * ============================================================
 *
 * CONCEPT:
 * Greatest Common Divisor (GCD) is the largest number that
 * divides both given numbers without remainder.
 *
 * Euclidean Algorithm (very efficient):
 *   GCD(a, b) = GCD(b, a % b)
 *   Repeat until b becomes 0.
 *   Then a is the GCD.
 *
 * Example:
 *   GCD(48, 18)
 *   48 % 18 = 12  → GCD(18, 12)
 *   18 % 12 = 6   → GCD(12, 6)
 *   12 % 6  = 0   → GCD(6, 0)  → Answer = 6
 *
 * CONTROL FLOW:
 * while loop is perfect because we keep reducing until remainder is 0.
 */
public class GCDEuclidean {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void gcdWithoutMethod() {
        int a = 48;
        int b = 18;

        int x = a;
        int y = b;

        // Euclidean algorithm using while loop
        while (y != 0) {
            int remainder = x % y;
            x = y;
            y = remainder;
        }

        System.out.println("GCD of " + a + " and " + b + " is: " + x);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Calculates GCD using Euclidean algorithm (iterative).
     */
    public static int gcd(int a, int b) {
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
    public static void gcdWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number : ");
        int a = sc.nextInt();
        System.out.print("Enter second number: ");
        int b = sc.nextInt();

        System.out.println("GCD (" + a + ", " + b + ") = " + gcd(a, b));
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        gcdWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        System.out.println("GCD(48, 18)  = " + gcd(48, 18));
        System.out.println("GCD(100, 25) = " + gcd(100, 25));
        System.out.println("GCD(17, 13)  = " + gcd(17, 13));

        System.out.println("\n===== VERSION 3: With User Input =====");
        gcdWithUserInput();
    }
}
