package com.java.control.flow;

import java.util.Scanner;

/**
 * ============================================================
 * 19. SUM OF ALL MULTIPLES OF 3 OR 5 BELOW A GIVEN NUMBER
 * ============================================================
 *
 * CONCEPT (Project Euler Problem 1 style):
 * Find the sum of all numbers below n that are multiples of 3 or 5.
 *
 * Example (n = 10):
 *   Multiples of 3 or 5 below 10 → 3, 5, 6, 9
 *   Sum = 3 + 5 + 6 + 9 = 23
 *
 * CONTROL FLOW:
 * for loop from 1 to n-1
 * if condition checks: number % 3 == 0 || number % 5 == 0
 *
 * Note: Numbers that are multiples of both 3 and 5 (like 15)
 * should be counted only once. The OR condition handles this naturally.
 */
public class SumOfMultiples {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void sumWithoutMethod() {
        int n = 10;
        int sum = 0;

        for (int i = 1; i < n; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                sum = sum + i;
            }
        }

        System.out.println("Sum of multiples of 3 or 5 below " + n + " = " + sum);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Returns the sum of all multiples of 3 or 5 below n.
     */
    public static int sumOfMultiples(int n) {
        int sum = 0;
        for (int i = 1; i < n; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                sum += i;
            }
        }
        return sum;
    }

    /**
     * More general version: sum of multiples of a or b below n.
     */
    public static int sumOfMultiples(int n, int a, int b) {
        int sum = 0;
        for (int i = 1; i < n; i++) {
            if (i % a == 0 || i % b == 0) {
                sum += i;
            }
        }
        return sum;
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void sumWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the upper limit (n): ");
        int n = sc.nextInt();

        int result = sumOfMultiples(n);
        System.out.println("Sum of multiples of 3 or 5 below " + n + " = " + result);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        sumWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        System.out.println("Below 10  → " + sumOfMultiples(10));
        System.out.println("Below 100 → " + sumOfMultiples(100));
        System.out.println("Below 1000 → " + sumOfMultiples(1000));

        System.out.println("\n===== VERSION 3: With User Input =====");
        sumWithUserInput();
    }
}
