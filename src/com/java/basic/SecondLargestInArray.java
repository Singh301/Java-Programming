package com.java.basic;

import java.util.Scanner;

/**
 * ============================================================
 * 10. SECOND LARGEST NUMBER IN AN ARRAY
 * ============================================================
 *
 * TASK:
 * Find the second largest element in an array of integers.
 *
 * Example:
 *   Array: {12, 35, 1, 10, 34, 1}
 *   Largest = 35
 *   Second Largest = 34
 *
 * Edge cases we must handle:
 * - Array with less than 2 elements
 * - All elements are same
 * - Negative numbers
 *
 * Efficient approach (O(n) time, single pass):
 * Keep track of largest and secondLargest while traversing.
 */
public class SecondLargestInArray {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void findSecondLargestWithoutMethod() {
        int[] arr = {12, 35, 1, 10, 34, 1};

        // Assume first two elements
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int num : arr) {
            if (num > largest) {
                secondLargest = largest;
                largest = num;
            } else if (num > secondLargest && num != largest) {
                secondLargest = num;
            }
        }

        System.out.print("Array: ");
        for (int n : arr) System.out.print(n + " ");
        System.out.println();

        if (secondLargest == Integer.MIN_VALUE) {
            System.out.println("No second largest element found (all elements may be same)");
        } else {
            System.out.println("Largest        : " + largest);
            System.out.println("Second Largest : " + secondLargest);
        }
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Returns the second largest element in the array.
     * Returns Integer.MIN_VALUE if not found.
     */
    public static int findSecondLargest(int[] arr) {
        if (arr == null || arr.length < 2) {
            throw new IllegalArgumentException("Array must have at least 2 elements");
        }

        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int num : arr) {
            if (num > largest) {
                secondLargest = largest;
                largest = num;
            } else if (num > secondLargest && num != largest) {
                secondLargest = num;
            }
        }

        return secondLargest;
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void findSecondLargestWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of array: ");
        int size = sc.nextInt();

        if (size < 2) {
            System.out.println("Array must have at least 2 elements");
            return;
        }

        int[] arr = new int[size];
        System.out.println("Enter " + size + " integers:");
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }

        int second = findSecondLargest(arr);

        if (second == Integer.MIN_VALUE) {
            System.out.println("No second largest element (all elements are equal)");
        } else {
            System.out.println("Second Largest number is: " + second);
        }
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        findSecondLargestWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        int[] test = {10, 5, 20, 8, 20, 15};
        System.out.println("Second largest = " + findSecondLargest(test));

        System.out.println("\n===== VERSION 3: With User Input =====");
        findSecondLargestWithUserInput();
    }
}
