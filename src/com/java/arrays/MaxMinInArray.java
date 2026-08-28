package com.java.arrays;

import java.util.Scanner;

/**
 * ============================================================
 * 21. MAXIMUM AND MINIMUM ELEMENT IN AN ARRAY
 * ============================================================
 *
 * CONCEPT:
 * Find the largest and smallest value present in an array.
 *
 * Approach:
 * - Assume first element is both max and min
 * - Traverse the rest of the array
 * - Update max if current element is larger
 * - Update min if current element is smaller
 *
 * Time Complexity: O(n) - single pass
 */
public class MaxMinInArray {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void findMaxMinWithoutMethod() {
        int[] arr = {25, 10, 45, 3, 78, 12};

        int max = arr[0];
        int min = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }

        System.out.print("Array: ");
        for (int num : arr) System.out.print(num + " ");
        System.out.println("\nMaximum: " + max);
        System.out.println("Minimum: " + min);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Returns an array of size 2: [0] = max, [1] = min
     */
    public static int[] findMaxMin(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array is empty or null");
        }

        int max = arr[0];
        int min = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
            if (arr[i] < min) min = arr[i];
        }

        return new int[]{max, min};
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void findMaxMinWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of array: ");
        int size = sc.nextInt();
        int[] arr = new int[size];

        System.out.println("Enter " + size + " elements:");
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }

        int[] result = findMaxMin(arr);
        System.out.println("Maximum: " + result[0]);
        System.out.println("Minimum: " + result[1]);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        findMaxMinWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        int[] test = {15, 8, 22, 3, 19, 7};
        int[] result = findMaxMin(test);
        System.out.println("Max = " + result[0] + ", Min = " + result[1]);

        System.out.println("\n===== VERSION 3: With User Input =====");
        findMaxMinWithUserInput();
    }
}
