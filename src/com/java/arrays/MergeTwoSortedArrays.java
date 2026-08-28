package com.java.arrays;

import java.util.Scanner;

/**
 * ============================================================
 * 26. MERGE TWO SORTED ARRAYS
 * ============================================================
 *
 * CONCEPT:
 * Given two sorted arrays, merge them into one single sorted array.
 *
 * Efficient Approach (Two Pointer Technique):
 * - Use two pointers, one for each array
 * - Compare elements pointed by the pointers
 * - Put the smaller one into the result array
 * - Move the pointer of the array from which we took the element
 *
 * Example:
 *   arr1 = [1, 3, 5, 7]
 *   arr2 = [2, 4, 6, 8]
 *   Result = [1, 2, 3, 4, 5, 6, 7, 8]
 *
 * Time Complexity: O(m + n)
 * Space Complexity: O(m + n)
 */
public class MergeTwoSortedArrays {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void mergeWithoutMethod() {
        int[] arr1 = {1, 3, 5, 7};
        int[] arr2 = {2, 4, 6, 8};

        int m = arr1.length;
        int n = arr2.length;
        int[] merged = new int[m + n];

        int i = 0, j = 0, k = 0;

        // Compare and merge
        while (i < m && j < n) {
            if (arr1[i] <= arr2[j]) {
                merged[k++] = arr1[i++];
            } else {
                merged[k++] = arr2[j++];
            }
        }

        // Copy remaining elements of arr1
        while (i < m) {
            merged[k++] = arr1[i++];
        }

        // Copy remaining elements of arr2
        while (j < n) {
            merged[k++] = arr2[j++];
        }

        System.out.print("Merged array: ");
        for (int num : merged) System.out.print(num + " ");
        System.out.println();
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Merges two sorted arrays and returns a new sorted array.
     */
    public static int[] merge(int[] arr1, int[] arr2) {
        int m = arr1.length;
        int n = arr2.length;
        int[] result = new int[m + n];

        int i = 0, j = 0, k = 0;

        while (i < m && j < n) {
            if (arr1[i] <= arr2[j]) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }

        while (i < m) result[k++] = arr1[i++];
        while (j < n) result[k++] = arr2[j++];

        return result;
    }

    public static void printArray(int[] arr) {
        for (int n : arr) System.out.print(n + " ");
        System.out.println();
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void mergeWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of first sorted array: ");
        int size1 = sc.nextInt();
        int[] arr1 = new int[size1];
        System.out.println("Enter elements of first sorted array:");
        for (int i = 0; i < size1; i++) arr1[i] = sc.nextInt();

        System.out.print("Enter size of second sorted array: ");
        int size2 = sc.nextInt();
        int[] arr2 = new int[size2];
        System.out.println("Enter elements of second sorted array:");
        for (int i = 0; i < size2; i++) arr2[i] = sc.nextInt();

        int[] merged = merge(arr1, arr2);

        System.out.print("Merged sorted array: ");
        printArray(merged);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        mergeWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        int[] a = {1, 4, 7, 10};
        int[] b = {2, 5, 6, 11, 15};
        System.out.print("Merged: ");
        printArray(merge(a, b));

        System.out.println("\n===== VERSION 3: With User Input =====");
        mergeWithUserInput();
    }
}
