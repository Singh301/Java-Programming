package com.java.collections;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * ============================================================
 * 47. REMOVE DUPLICATE ELEMENTS FROM A LIST
 * ============================================================
 *
 * CONCEPT:
 * Remove duplicate values from a List while (optionally)
 * preserving the original order.
 *
 * Example:
 *   Input  : [1, 2, 2, 3, 4, 3, 5]
 *   Output : [1, 2, 3, 4, 5]
 *
 * Best Approach:
 * Use LinkedHashSet → removes duplicates + preserves insertion order.
 */
public class RemoveDuplicatesList {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void removeWithoutMethod() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(3);
        list.add(5);

        System.out.println("Original : " + list);

        Set<Integer> set = new LinkedHashSet<>(list);
        List<Integer> unique = new ArrayList<>(set);

        System.out.println("Unique   : " + unique);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Removes duplicates while preserving order.
     */
    public static <T> List<T> removeDuplicates(List<T> list) {
        if (list == null) return null;
        return new ArrayList<>(new LinkedHashSet<>(list));
    }

    /**
     * Manual way without using Set (for understanding).
     */
    public static List<Integer> removeDuplicatesManual(List<Integer> list) {
        List<Integer> result = new ArrayList<>();
        for (Integer num : list) {
            if (!result.contains(num)) {
                result.add(num);
            }
        }
        return result;
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void removeWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        List<Integer> list = new ArrayList<>();
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }

        System.out.println("Original : " + list);
        System.out.println("Unique   : " + removeDuplicates(list));
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        removeWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        List<Integer> test = new ArrayList<>();
        test.add(5);
        test.add(2);
        test.add(5);
        test.add(1);
        test.add(2);
        test.add(3);
        System.out.println("Original: " + test);
        System.out.println("Unique  : " + removeDuplicates(test));

        System.out.println("\n===== VERSION 3: With User Input =====");
        removeWithUserInput();
    }
}
