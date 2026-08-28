package com.java.collections;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * ============================================================
 * 49. FIND THE UNION OF TWO LISTS
 * ============================================================
 *
 * CONCEPT:
 * Union of two lists = all unique elements from both lists combined.
 *
 * Example:
 *   List A = [1, 2, 3, 4]
 *   List B = [3, 4, 5, 6]
 *   Union  = [1, 2, 3, 4, 5, 6]
 *
 * We use LinkedHashSet to keep uniqueness + preserve order.
 */
public class ListUnion {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void unionWithoutMethod() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);

        List<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(4);
        list2.add(5);
        list2.add(6);

        System.out.println("List 1: " + list1);
        System.out.println("List 2: " + list2);

        Set<Integer> unionSet = new LinkedHashSet<>();
        unionSet.addAll(list1);
        unionSet.addAll(list2);

        List<Integer> union = new ArrayList<>(unionSet);
        System.out.println("Union : " + union);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Returns the union of two lists (unique elements, order preserved).
     */
    public static <T> List<T> union(List<T> list1, List<T> list2) {
        Set<T> set = new LinkedHashSet<>();
        if (list1 != null) set.addAll(list1);
        if (list2 != null) set.addAll(list2);
        return new ArrayList<>(set);
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void unionWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of first list: ");
        int n1 = sc.nextInt();
        List<Integer> list1 = new ArrayList<>();
        System.out.println("Enter elements of first list:");
        for (int i = 0; i < n1; i++) list1.add(sc.nextInt());

        System.out.print("Enter size of second list: ");
        int n2 = sc.nextInt();
        List<Integer> list2 = new ArrayList<>();
        System.out.println("Enter elements of second list:");
        for (int i = 0; i < n2; i++) list2.add(sc.nextInt());

        System.out.println("Union: " + union(list1, list2));
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        unionWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        List<Integer> a = List.of(10, 20, 30, 40);
        List<Integer> b = List.of(30, 40, 50, 60);
        System.out.println("Union: " + union(a, b));

        System.out.println("\n===== VERSION 3: With User Input =====");
        unionWithUserInput();
    }
}
