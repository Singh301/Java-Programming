package com.java.control.flow;

import java.util.Scanner;

/**
 * ============================================================
 * 17. FIND LCM OF TWO NUMBERS
 * ============================================================
 *
 * CONCEPT:
 * LCM (Least Common Multiple) is the smallest positive integer
 * that is divisible by both numbers.
 *
 * Relation with GCD:
 *   LCM(a, b) × GCD(a, b) = a × b
 *
 * Therefore:
 *   LCM(a, b) = (a × b) / GCD(a, b)
 *
 * Example:
 *   a = 12, b = 18
 *   GCD = 6
 *   LCM = (12 × 18) / 6 = 36
 *
 * This is the most efficient way to calculate LCM.
 */
public class LCM {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void lcmWithoutMethod() {
        int a = 12;
        int b = 18;

        // First find GCD using Euclidean algorithm
        int x = a, y = b;
        while (y != 0) {
            int temp = y;
            y = x % y;
            x = temp;
        }
        int gcd = x;

        // Now calculate LCM
        int lcm = (a * b) / gcd;

        System.out.println("LCM of " + a + " and " + b + " is: " + lcm);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Helper: Calculate GCD
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
     * Calculates LCM of two numbers.
     */
    public static int lcm(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        // Use long to avoid overflow during multiplication
        return Math.abs(a / gcd(a, b) * b);
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void lcmWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number : ");
        int a = sc.nextInt();
        System.out.print("Enter second number: ");
        int b = sc.nextInt();

        System.out.println("LCM of " + a + " and " + b + " is: " + lcm(a, b));
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        lcmWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        System.out.println("LCM(12, 18) = " + lcm(12, 18));
        System.out.println("LCM(15, 25) = " + lcm(15, 25));
        System.out.println("LCM(7, 5)   = " + lcm(7, 5));

        System.out.println("\n===== VERSION 3: With User Input =====");
        lcmWithUserInput();
    }
}
