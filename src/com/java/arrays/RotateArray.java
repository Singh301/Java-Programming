package com.java.arrays;

import java.util.Scanner;

/**
 * ============================================================
 * 30. ROTATE AN ARRAY BY A GIVEN NUMBER OF POSITIONS
 * ============================================================
 *
 * CONCEPT:
 * Rotate the array to the left or right by k positions.
 *
 * Example (Right rotate by 2):
 *   Original : [1, 2, 3, 4, 5]
 *   After    : [4, 5, 1, 2, 3]
 *
 * Example (Left rotate by 2):
 *   Original : [1, 2, 3, 4, 5]
 *   After    : [3, 4, 5, 1, 2]
 *
 * Efficient Approach (Reversal Algorithm):
 * For right rotate by k:
 * 1. Reverse the whole array
 * 2. Reverse first k elements
 * 3. Reverse the remaining elements
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class RotateArray {

    // ============================================================
    // VERSION 1: WITHOUT METHOD (Right rotate using temp array)
    // ============================================================
    public static void rotateWithoutMethod() {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 2;   // rotate right by 2

        System.out.print("Original: ");
        for (int n : arr) System.out.print(n + " ");
        System.out.println();

        int n = arr.length;
        k = k % n;   // handle k > n

        int[] temp = new int[n];

        // Copy last k elements to the beginning
        for (int i = 0; i < k; i++) {
            temp[i] = arr[n - k + i];
        }
        // Copy the remaining elements
        for (int i = 0; i < n - k; i++) {
            temp[k + i] = arr[i];
        }

        // Copy back
        for (int i = 0; i < n; i++) {
            arr[i] = temp[i];
        }

        System.out.print("After right rotate by " + k + ": ");
        for (int num : arr) System.out.print(num + " ");
        System.out.println();
    }

    // ============================================================
    // VERSION 2: WITH METHOD (Efficient Reversal Algorithm)
    // ============================================================
    /**
     * Helper method to reverse a portion of the array.
     */
    public static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * Rotates the array to the right by k positions (in-place).
     */
    public static void rotateRight(int[] arr, int k) {
        int n = arr.length;
        if (n == 0) return;
        k = k % n;
        if (k < 0) k += n;   // handle negative k

        // Reverse whole array
        reverse(arr, 0, n - 1);
        // Reverse first k elements
        reverse(arr, 0, k - 1);
        // Reverse remaining elements
        reverse(arr, k, n - 1);
    }

    /**
     * Rotates the array to the left by k positions (in-place).
     */
    public static void rotateLeft(int[] arr, int k) {
        int n = arr.length;
        if (n == 0) return;
        k = k % n;
        if (k < 0) k += n;

        reverse(arr, 0, k - 1);
        reverse(arr, k, n - 1);
        reverse(arr, 0, n - 1);
    }

    public static void printArray(int[] arr) {
        for (int n : arr) System.out.print(n + " ");
        System.out.println();
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void rotateWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of array: ");
        int size = sc.nextInt();
        int[] arr = new int[size];

        System.out.println("Enter " + size + " elements:");
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("Enter number of positions to rotate: ");
        int k = sc.nextInt();

        System.out.print("Enter direction (L for left / R for right): ");
        char dir = sc.next().toUpperCase().charAt(0);

        System.out.print("Original: ");
        printArray(arr);

        if (dir == 'R') {
            rotateRight(arr, k);
            System.out.print("After right rotate by " + k + ": ");
        } else {
            rotateLeft(arr, k);
            System.out.print("After left rotate by " + k + ": ");
        }
        printArray(arr);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        rotateWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        int[] test1 = {1, 2, 3, 4, 5, 6, 7};
        System.out.print("Original: ");
        printArray(test1);
        rotateRight(test1, 3);
        System.out.print("Right rotate by 3: ");
        printArray(test1);

        int[] test2 = {1, 2, 3, 4, 5, 6, 7};
        rotateLeft(test2, 2);
        System.out.print("Left rotate by 2 : ");
        printArray(test2);

        System.out.println("\n===== VERSION 3: With User Input =====");
        rotateWithUserInput();
    }
}
