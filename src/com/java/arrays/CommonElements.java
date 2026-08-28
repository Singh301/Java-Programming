package com.java.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * ============================================================
 * 28. FIND COMMON ELEMENTS IN TWO ARRAYS
 * ============================================================
 *
 * CONCEPT:
 * Find elements that are present in both arrays.
 *
 * Approaches:
 * 1. Nested loops (brute force) - O(n * m)
 * 2. Using HashSet (efficient) - O(n + m)
 * 3. Two pointer technique (if both arrays are sorted)
 *
 * Example:
 *   arr1 = [1, 2, 3, 4, 5]
 *   arr2 = [3, 4, 5, 6, 7]
 *   Common = [3, 4, 5]
 */
public class CommonElements {

    // ============================================================
    // VERSION 1: WITHOUT METHOD (Nested loops)
    // ============================================================
    public static void findCommonWithoutMethod() {
        int[] arr1 = {1, 2, 3, 4, 5, 6};
        int[] arr2 = {4, 5, 6, 7, 8};

        System.out.print("Common elements: ");
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                if (arr1[i] == arr2[j]) {
                    System.out.print(arr1[i] + " ");
                    break;   // avoid printing same element multiple times
                }
            }
        }
        System.out.println();
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Finds common elements using HashSet (efficient).
     */
    public static List<Integer> findCommon(int[] arr1, int[] arr2) {
        Set<Integer> set = new HashSet<>();
        for (int num : arr1) {
            set.add(num);
        }

        List<Integer> common = new ArrayList<>();
        Set<Integer> added = new HashSet<>();   // to avoid duplicates in result

        for (int num : arr2) {
            if (set.contains(num) && !added.contains(num)) {
                common.add(num);
                added.add(num);
            }
        }
        return common;
    }

    /**
     * Two-pointer approach (both arrays must be sorted).
     */
    public static List<Integer> findCommonSorted(int[] arr1, int[] arr2) {
        List<Integer> common = new ArrayList<>();
        int i = 0, j = 0;

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] == arr2[j]) {
                // Avoid duplicates
                if (common.isEmpty() || common.get(common.size() - 1) != arr1[i]) {
                    common.add(arr1[i]);
                }
                i++;
                j++;
            } else if (arr1[i] < arr2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return common;
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void findCommonWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of first array: ");
        int size1 = sc.nextInt();
        int[] arr1 = new int[size1];
        System.out.println("Enter elements of first array:");
        for (int i = 0; i < size1; i++) arr1[i] = sc.nextInt();

        System.out.print("Enter size of second array: ");
        int size2 = sc.nextInt();
        int[] arr2 = new int[size2];
        System.out.println("Enter elements of second array:");
        for (int i = 0; i < size2; i++) arr2[i] = sc.nextInt();

        List<Integer> common = findCommon(arr1, arr2);

        System.out.print("Common elements: ");
        if (common.isEmpty()) {
            System.out.println("None");
        } else {
            for (int num : common) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        findCommonWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        int[] a = {1, 2, 3, 4, 5, 5};
        int[] b = {3, 4, 5, 6, 7, 5};
        System.out.println("Common (HashSet): " + findCommon(a, b));

        int[] sortedA = {1, 2, 3, 4, 5};
        int[] sortedB = {3, 4, 5, 6, 7};
        System.out.println("Common (Two Pointer): " + findCommonSorted(sortedA, sortedB));

        System.out.println("\n===== VERSION 3: With User Input =====");
        findCommonWithUserInput();
    }
}
