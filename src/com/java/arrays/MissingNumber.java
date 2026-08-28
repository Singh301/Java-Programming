package com.java.arrays;

import java.util.Scanner;

/**
 * ============================================================
 * 27. FIND THE MISSING NUMBER IN AN ARRAY OF 1 TO N
 * ============================================================
 *
 * CONCEPT:
 * You are given an array containing n distinct numbers
 * taken from 1 to n+1 (or 0 to n). One number is missing.
 * Find that missing number.
 *
 * Example:
 *   Array = [1, 2, 4, 6, 3, 7, 8]   (n = 8, missing = 5)
 *
 * Efficient Approaches:
 * 1. Sum Formula:
 *    Expected sum of 1 to n = n*(n+1)/2
 *    Missing = Expected sum - Actual sum
 *
 * 2. XOR method (avoids overflow):
 *    XOR all numbers from 1 to n and XOR with all array elements.
 *    The remaining value is the missing number.
 */
public class MissingNumber {

    // ============================================================
    // VERSION 1: WITHOUT METHOD (Sum Formula)
    // ============================================================
    public static void findMissingWithoutMethod() {
        int[] arr = {1, 2, 4, 6, 3, 7, 8};
        int n = 8;   // numbers should be from 1 to 8

        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;

        for (int num : arr) {
            actualSum += num;
        }

        int missing = expectedSum - actualSum;
        System.out.println("Missing number is: " + missing);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Finds the missing number using sum formula.
     * Array contains numbers from 1 to n with one missing.
     */
    public static int findMissingSum(int[] arr, int n) {
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;
        for (int num : arr) {
            actualSum += num;
        }
        return expectedSum - actualSum;
    }

    /**
     * Finds the missing number using XOR (safer against overflow).
     */
    public static int findMissingXOR(int[] arr, int n) {
        int xor = 0;

        // XOR all numbers from 1 to n
        for (int i = 1; i <= n; i++) {
            xor ^= i;
        }

        // XOR with all array elements
        for (int num : arr) {
            xor ^= num;
        }

        return xor;   // remaining value is the missing number
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void findMissingWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter n (range is 1 to n): ");
        int n = sc.nextInt();

        System.out.println("Enter " + (n - 1) + " numbers (one number is missing):");
        int[] arr = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println("Missing number (Sum method) = " + findMissingSum(arr, n));
        System.out.println("Missing number (XOR method) = " + findMissingXOR(arr, n));
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        findMissingWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        int[] test = {1, 2, 4, 5, 6};
        System.out.println("Missing (Sum) = " + findMissingSum(test, 6));
        System.out.println("Missing (XOR) = " + findMissingXOR(test, 6));

        System.out.println("\n===== VERSION 3: With User Input =====");
        findMissingWithUserInput();
    }
}
