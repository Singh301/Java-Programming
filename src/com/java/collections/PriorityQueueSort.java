package com.java.collections;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * ============================================================
 * 46. SORT AN ARRAY USING PRIORITY QUEUE
 * ============================================================
 *
 * CONCEPT:
 * PriorityQueue in Java is a min-heap by default.
 * The smallest element is always at the front.
 *
 * We can use it to sort an array:
 * 1. Insert all elements into PriorityQueue
 * 2. Poll elements one by one (they come out in sorted order)
 *
 * For descending order we use a max-heap
 * (PriorityQueue with reverseOrder comparator).
 *
 * Time Complexity: O(n log n)
 */
public class PriorityQueueSort {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void sortWithoutMethod() {
        int[] arr = {15, 3, 9, 1, 27, 8};

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // Add all elements
        for (int num : arr) {
            pq.offer(num);
        }

        System.out.print("Sorted (ascending): ");
        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");
        }
        System.out.println();
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Sorts the array in ascending order using PriorityQueue (min-heap).
     */
    public static int[] sortAscending(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : arr) {
            pq.offer(num);
        }

        int[] sorted = new int[arr.length];
        int i = 0;
        while (!pq.isEmpty()) {
            sorted[i++] = pq.poll();
        }
        return sorted;
    }

    /**
     * Sorts the array in descending order using max-heap.
     */
    public static int[] sortDescending(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : arr) {
            pq.offer(num);
        }

        int[] sorted = new int[arr.length];
        int i = 0;
        while (!pq.isEmpty()) {
            sorted[i++] = pq.poll();
        }
        return sorted;
    }

    public static void printArray(int[] arr) {
        for (int n : arr) System.out.print(n + " ");
        System.out.println();
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void sortWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of array: ");
        int size = sc.nextInt();
        int[] arr = new int[size];

        System.out.println("Enter " + size + " elements:");
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("Ascending : ");
        printArray(sortAscending(arr));

        System.out.print("Descending: ");
        printArray(sortDescending(arr));
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        sortWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        int[] test = {12, 5, 7, 3, 19, 1};
        System.out.print("Ascending : ");
        printArray(sortAscending(test));
        System.out.print("Descending: ");
        printArray(sortDescending(test));

        System.out.println("\n===== VERSION 3: With User Input =====");
        sortWithUserInput();
    }
}
