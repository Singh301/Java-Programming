package com.java.arrays;

import java.util.Scanner;

/**
 * ============================================================
 * 29. BINARY SEARCH ON A SORTED ARRAY
 * ============================================================
 *
 * CONCEPT:
 * Binary Search is an efficient algorithm to find an element
 * in a **sorted** array.
 *
 * How it works:
 * 1. Find the middle element
 * 2. If middle == target → found
 * 3. If target < middle → search in left half
 * 4. If target > middle → search in right half
 * 5. Repeat until found or search space is empty
 *
 * Time Complexity: O(log n)
 * Much faster than Linear Search (O(n)) for large arrays.
 *
 * Prerequisite: Array must be sorted.
 */
public class BinarySearch {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void binarySearchWithoutMethod() {
        int[] arr = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
        int target = 23;

        int left = 0;
        int right = arr.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;   // avoids overflow

            if (arr[mid] == target) {
                result = mid;
                break;
            } else if (arr[mid] < target) {
                left = mid + 1;     // search right half
            } else {
                right = mid - 1;    // search left half
            }
        }

        if (result != -1) {
            System.out.println("Element " + target + " found at index " + result);
        } else {
            System.out.println("Element " + target + " not found");
        }
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Performs Binary Search and returns the index of target.
     * Returns -1 if not found.
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;   // not found
    }

    /**
     * Recursive version of Binary Search.
     */
    public static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            return binarySearchRecursive(arr, target, mid + 1, right);
        } else {
            return binarySearchRecursive(arr, target, left, mid - 1);
        }
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void binarySearchWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of sorted array: ");
        int size = sc.nextInt();
        int[] arr = new int[size];

        System.out.println("Enter " + size + " elements in sorted order:");
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("Enter the element to search: ");
        int target = sc.nextInt();

        int index = binarySearch(arr, target);

        if (index != -1) {
            System.out.println("Element found at index: " + index);
        } else {
            System.out.println("Element not found in the array");
        }
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        binarySearchWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15};
        System.out.println("Search 7  → index " + binarySearch(arr, 7));
        System.out.println("Search 10 → index " + binarySearch(arr, 10));
        System.out.println("Search 1  (recursive) → index " +
                binarySearchRecursive(arr, 1, 0, arr.length - 1));

        System.out.println("\n===== VERSION 3: With User Input =====");
        binarySearchWithUserInput();
    }
}
