package com.java.basic;

import java.util.Scanner;

/**
 * ============================================================
 * 9. LARGEST OF THREE NUMBERS
 * ============================================================
 *
 * TASK:
 * Find the largest (maximum) number among three given numbers.
 *
 * This is a classic beginner problem that teaches:
 * - if-else / nested if
 * - Comparison operators
 * - Ternary operator (optional advanced way)
 */
public class LargestOfThree {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void findLargestWithoutMethod() {
        int a = 25;
        int b = 78;
        int c = 45;

        int largest;

        if (a >= b && a >= c) {
            largest = a;
        } else if (b >= a && b >= c) {
            largest = b;
        } else {
            largest = c;
        }

        System.out.println("Numbers: " + a + ", " + b + ", " + c);
        System.out.println("Largest: " + largest);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Returns the largest of three integers.
     */
    public static int findLargest(int a, int b, int c) {
        // Using Math.max for clean code
        return Math.max(a, Math.max(b, c));
    }

    /**
     * Alternative implementation using only if-else
     * (good for understanding the logic)
     */
    public static int findLargestManual(int a, int b, int c) {
        if (a >= b && a >= c) {
            return a;
        } else if (b >= a && b >= c) {
            return b;
        } else {
            return c;
        }
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void findLargestWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number : ");
        int a = sc.nextInt();
        System.out.print("Enter second number: ");
        int b = sc.nextInt();
        System.out.print("Enter third number : ");
        int c = sc.nextInt();

        int largest = findLargest(a, b, c);
        System.out.println("The largest number is: " + largest);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        findLargestWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        System.out.println("Largest of 10, 25, 15 = " + findLargest(10, 25, 15));
        System.out.println("Largest of -5, -2, -9 = " + findLargest(-5, -2, -9));

        System.out.println("\n===== VERSION 3: With User Input =====");
        findLargestWithUserInput();
    }
}
