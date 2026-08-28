package com.java.arrays;

import java.util.Scanner;

/**
 * ============================================================
 * 23. BUBBLE SORT
 * ============================================================
 *
 * CONCEPT:
 * Bubble Sort repeatedly steps through the array, compares
 * adjacent elements and swaps them if they are in wrong order.
 *
 * After each pass, the largest remaining element "bubbles up"
 * to its correct position at the end.
 *
 * Example:
 *   [5, 3, 8, 4, 2]
 *   Pass 1 → [3, 5, 4, 2, 8]
 *   Pass 2 → [3, 4, 2, 5, 8]
 *   Pass 3 → [3, 2, 4, 5, 8]
 *   Pass 4 → [2, 3, 4, 5, 8]
 *
 * Time Complexity:
 *   Best    : O(n)     (already sorted + optimized version)
 *   Average : O(n²)
 *   Worst   : O(n²)
 *
 * Space Complexity: O(1) - in-place sorting
 */
public class BubbleSort {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void bubbleSortWithoutMethod() {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};

        System.out.print("Before sorting: ");
        for (int n : arr) System.out.print(n + " ");
        System.out.println();

        int n = arr.length;

        // Outer loop for passes
        for (int i = 0; i < n - 1; i++) {
            // Inner loop for comparisons
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        System.out.print("After sorting : ");
        for (int num : arr) System.out.print(num + " ");
        System.out.println();
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Sorts the array using Bubble Sort (optimized with swapped flag).
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            // If no two elements were swapped → array is already sorted
            if (!swapped) break;
        }
    }

    public static void printArray(int[] arr) {
        for (int n : arr) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void bubbleSortWithUserInput() {
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

        bubbleSort(arr);

        System.out.print("After : ");
        printArray(arr);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        bubbleSortWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        int[] test = {5, 1, 4, 2, 8};
        System.out.print("Before: ");
        printArray(test);
        bubbleSort(test);
        System.out.print("After : ");
        printArray(test);

        System.out.println("\n===== VERSION 3: With User Input =====");
        bubbleSortWithUserInput();
    }
}
