package com.java.arrays;

import java.util.Scanner;

/**
 * ============================================================
 * 25. INSERTION SORT
 * ============================================================
 *
 * CONCEPT:
 * Insertion Sort builds the final sorted array one item at a time.
 * It is similar to how we sort playing cards in our hands.
 *
 * Algorithm:
 * - Take one element from the unsorted part
 * - Insert it into its correct position in the sorted part
 * - Shift larger elements one position to the right
 *
 * Example:
 *   [12, 11, 13, 5, 6]
 *   → [11, 12, 13, 5, 6]
 *   → [11, 12, 13, 5, 6]
 *   → [5, 11, 12, 13, 6]
 *   → [5, 6, 11, 12, 13]
 *
 * Time Complexity:
 *   Best    : O(n)     (already sorted)
 *   Average : O(n²)
 *   Worst   : O(n²)
 *
 * Space Complexity: O(1)
 * Very efficient for small or nearly sorted arrays.
 */
public class InsertionSort {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void insertionSortWithoutMethod() {
        int[] arr = {12, 11, 13, 5, 6};

        System.out.print("Before sorting: ");
        for (int n : arr) System.out.print(n + " ");
        System.out.println();

        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];       // element to be inserted
            int j = i - 1;

            // Shift elements greater than key to one position ahead
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;       // place key at correct position
        }

        System.out.print("After sorting : ");
        for (int num : arr) System.out.print(num + " ");
        System.out.println();
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Sorts the array using Insertion Sort.
     */
    public static void insertionSort(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void printArray(int[] arr) {
        for (int n : arr) System.out.print(n + " ");
        System.out.println();
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void insertionSortWithUserInput() {
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

        insertionSort(arr);

        System.out.print("After : ");
        printArray(arr);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        insertionSortWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        int[] test = {9, 5, 1, 4, 3};
        System.out.print("Before: ");
        printArray(test);
        insertionSort(test);
        System.out.print("After : ");
        printArray(test);

        System.out.println("\n===== VERSION 3: With User Input =====");
        insertionSortWithUserInput();
    }
}
