package com.java.collections;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * ============================================================
 * 48. FIND THE INTERSECTION OF TWO SETS
 * ============================================================
 *
 * CONCEPT:
 * Intersection of two sets = elements that are present in BOTH sets.
 *
 * Example:
 *   Set A = {1, 2, 3, 4, 5}
 *   Set B = {3, 4, 5, 6, 7}
 *   Intersection = {3, 4, 5}
 *
 * In Java we can use retainAll() method of Set.
 */
public class SetIntersection {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void intersectionWithoutMethod() {
        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set1.add(4);
        set1.add(5);

        Set<Integer> set2 = new HashSet<>();
        set2.add(3);
        set2.add(4);
        set2.add(5);
        set2.add(6);
        set2.add(7);

        System.out.println("Set 1: " + set1);
        System.out.println("Set 2: " + set2);

        // Create a copy so original set1 is not modified
        Set<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        System.out.println("Intersection: " + intersection);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Returns the intersection of two sets (new set, originals unchanged).
     */
    public static <T> Set<T> intersection(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.retainAll(set2);
        return result;
    }

    /**
     * Manual way without using retainAll.
     */
    public static <T> Set<T> intersectionManual(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>();
        for (T element : set1) {
            if (set2.contains(element)) {
                result.add(element);
            }
        }
        return result;
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void intersectionWithUserInput() {
        Scanner sc = new Scanner(System.in);

        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        System.out.print("Enter size of first set: ");
        int n1 = sc.nextInt();
        System.out.println("Enter elements of first set:");
        for (int i = 0; i < n1; i++) set1.add(sc.nextInt());

        System.out.print("Enter size of second set: ");
        int n2 = sc.nextInt();
        System.out.println("Enter elements of second set:");
        for (int i = 0; i < n2; i++) set2.add(sc.nextInt());

        System.out.println("Intersection: " + intersection(set1, set2));
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        intersectionWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        Set<Integer> a = new HashSet<>();
        a.add(10); a.add(20); a.add(30); a.add(40);
        Set<Integer> b = new HashSet<>();
        b.add(30); b.add(40); b.add(50); b.add(60);
        System.out.println("Intersection: " + intersection(a, b));

        System.out.println("\n===== VERSION 3: With User Input =====");
        intersectionWithUserInput();
    }
}
