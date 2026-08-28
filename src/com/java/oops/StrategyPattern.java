package com.java.oops;

import java.util.Arrays;
import java.util.Scanner;

/**
 * ============================================================
 * 184. STRATEGY PATTERN FOR DIFFERENT SORTING ALGORITHMS
 * ============================================================
 *
 * CONCEPT:
 * Strategy Pattern defines a family of algorithms, encapsulates
 * each one, and makes them interchangeable.
 *
 * The client can choose which algorithm to use at runtime.
 *
 * Example: Different sorting strategies (Bubble, Selection, Insertion)
 * Context holds a reference to Strategy and delegates the work.
 */
public class StrategyPattern {

    // ============================================================
    // STRATEGY INTERFACE
    // ============================================================
    interface SortStrategy {
        void sort(int[] arr);
        String getName();
    }

    // ============================================================
    // CONCRETE STRATEGIES
    // ============================================================
    static class BubbleSortStrategy implements SortStrategy {
        @Override
        public void sort(int[] arr) {
            int n = arr.length;
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (arr[j] > arr[j + 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
        }

        @Override
        public String getName() {
            return "Bubble Sort";
        }
    }

    static class SelectionSortStrategy implements SortStrategy {
        @Override
        public void sort(int[] arr) {
            int n = arr.length;
            for (int i = 0; i < n - 1; i++) {
                int minIdx = i;
                for (int j = i + 1; j < n; j++) {
                    if (arr[j] < arr[minIdx]) minIdx = j;
                }
                int temp = arr[minIdx];
                arr[minIdx] = arr[i];
                arr[i] = temp;
            }
        }

        @Override
        public String getName() {
            return "Selection Sort";
        }
    }

    static class InsertionSortStrategy implements SortStrategy {
        @Override
        public void sort(int[] arr) {
            for (int i = 1; i < arr.length; i++) {
                int key = arr[i];
                int j = i - 1;
                while (j >= 0 && arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                }
                arr[j + 1] = key;
            }
        }

        @Override
        public String getName() {
            return "Insertion Sort";
        }
    }

    // ============================================================
    // CONTEXT
    // ============================================================
    static class Sorter {
        private SortStrategy strategy;

        public void setStrategy(SortStrategy strategy) {
            this.strategy = strategy;
        }

        public void sortArray(int[] arr) {
            if (strategy == null) {
                System.out.println("No strategy selected!");
                return;
            }
            System.out.println("Sorting using: " + strategy.getName());
            strategy.sort(arr);
            System.out.println("Result: " + Arrays.toString(arr));
        }
    }

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        int[] data = {64, 34, 25, 12, 22, 11, 90};
        Sorter sorter = new Sorter();

        sorter.setStrategy(new BubbleSortStrategy());
        sorter.sortArray(data.clone());

        sorter.setStrategy(new SelectionSortStrategy());
        sorter.sortArray(data.clone());
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void sortWithStrategy(int[] arr, SortStrategy strategy) {
        Sorter sorter = new Sorter();
        sorter.setStrategy(strategy);
        sorter.sortArray(arr);
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of array: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter elements:");
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        System.out.println("1.Bubble  2.Selection  3.Insertion");
        System.out.print("Choose strategy: ");
        int choice = sc.nextInt();

        SortStrategy strategy;
        switch (choice) {
            case 1: strategy = new BubbleSortStrategy(); break;
            case 2: strategy = new SelectionSortStrategy(); break;
            default: strategy = new InsertionSortStrategy();
        }

        sortWithStrategy(arr, strategy);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        int[] data = {5, 2, 9, 1, 5, 6};
        sortWithStrategy(data.clone(), new InsertionSortStrategy());

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
