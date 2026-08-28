package com.java.arrays;

import java.util.Arrays;
import java.util.Scanner;

/**
 * ============================================================
 * 22. REMOVE DUPLICATES FROM AN ARRAY
 * ============================================================
 *
 * CONCEPT:
 * Remove duplicate elements and keep only unique values.
 *
 * Two common approaches:
 * 1. Using a temporary array (for sorted arrays - efficient)
 * 2. Using nested loops (works for unsorted too)
 *
 * Example:
 *   Input  : [1, 2, 2, 3, 4, 4, 5]
 *   Output : [1, 2, 3, 4, 5]
 *
 * Note: We will first sort the array so that duplicates become adjacent.
 */
public class RemoveDuplicates {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void removeDuplicatesWithoutMethod() {
        int[] arr = {1, 2, 2, 3, 4, 4, 5, 1, 3};

        // First sort the array so duplicates come together
        Arrays.sort(arr);

        System.out.print("Original (sorted): ");
        for (int n : arr) System.out.print(n + " ");
        System.out.println();

        // Create a temporary array for unique elements
        int[] temp = new int[arr.length];
        int j = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] != arr[i + 1]) {
                temp[j++] = arr[i];
            }
        }
        // Add the last element
        temp[j++] = arr[arr.length - 1];

        System.out.print("After removing duplicates: ");
        for (int i = 0; i < j; i++) {
            System.out.print(temp[i] + " ");
        }
        System.out.println();
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Removes duplicates from the array and returns a new array
     * containing only unique elements (sorted).
     */
    public static int[] removeDuplicates(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }

        // Sort first
        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        // Count unique elements
        int uniqueCount = 1;
        for (int i = 1; i < sorted.length; i++) {
            if (sorted[i] != sorted[i - 1]) {
                uniqueCount++;
            }
        }

        // Create result array
        int[] result = new int[uniqueCount];
        result[0] = sorted[0];
        int index = 1;

        for (int i = 1; i < sorted.length; i++) {
            if (sorted[i] != sorted[i - 1]) {
                result[index++] = sorted[i];
            }
        }

        return result;
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void removeDuplicatesWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of array: ");
        int size = sc.nextInt();
        int[] arr = new int[size];

        System.out.println("Enter " + size + " elements:");
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }

        int[] unique = removeDuplicates(arr);

        System.out.print("Array after removing duplicates: ");
        for (int n : unique) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        removeDuplicatesWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        int[] test = {4, 2, 1, 2, 3, 4, 5, 1};
        int[] result = removeDuplicates(test);
        System.out.print("Unique elements: ");
        for (int n : result) System.out.print(n + " ");
        System.out.println();

        System.out.println("\n===== VERSION 3: With User Input =====");
        removeDuplicatesWithUserInput();
    }
}
