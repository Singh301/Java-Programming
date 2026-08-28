package com.java.arrays;

import java.util.Scanner;

/**
 * ============================================================
 * 24. SELECTION SORT
 * ============================================================
 *
 * CONCEPT:
 * Selection Sort divides the array into two parts:
 * - Sorted part (left)
 * - Unsorted part (right)
 *
 * In every pass:
 * - Find the minimum element in the unsorted part
 * - Swap it with the first element of the unsorted part
 *
 * Example:
 *   [64, 25, 12, 22, 11]
 *   Pass 1 → [11, 25, 12, 22, 64]
 *   Pass 2 → [11, 12, 25, 22, 64]
 *   Pass 3 → [11, 12, 22, 25, 64]
 *   Pass 4 → [11, 12, 22, 25, 64]
 *
 * Time Complexity: O(n²) in all cases
 * Space Complexity: O(1)
 *
 * Advantage: Minimum number of swaps (at most n-1 swaps)
 */
public class SelectionSort {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void selectionSortWithoutMethod() {
        int[] arr = {64, 25, 12, 22, 11};

        System.out.print("Before sorting: ");
        for (int n : arr) System.out.print(n + " ");
        System.out.println();

        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted part
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap the found minimum with the first element of unsorted part
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }

        System.out.print("After sorting : ");
        for (int num : arr) System.out.print(num + " ");
        System.out.println();
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Sorts the array using Selection Sort.
     */
    public static void selectionSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap only if needed
            if (minIndex != i) {
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
            }
        }
    }

    public static void printArray(int[] arr) {
        for (int n : arr) System.out.print(n + " ");
        System.out.println();
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void selectionSortWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of array: ");
        int size = sc.nextInt();
        int[] arr = new int[size];

        System.out.println("Enter " + size + " elements:");
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("Before: ");
        printArray(arr);

        selectionSort(arr);

        System.out.print("After : ");
        printArray(arr);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        selectionSortWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        int[] test = {29, 10, 14, 37, 13};
        System.out.print("Before: ");
        printArray(test);
        selectionSort(test);
        System.out.print("After : ");
        printArray(test);

        System.out.println("\n===== VERSION 3: With User Input =====");
        selectionSortWithUserInput();
    }
}
