package com.java.multithreading.concurrency;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * ============================================================
 * 77. FORK-JOIN FRAMEWORK
 * ============================================================
 *
 * CONCEPT:
 * Fork/Join is designed for work that can be broken into smaller pieces
 * recursively (divide and conquer).
 *
 * - fork()  → split task and execute asynchronously
 * - join()  → wait for result of subtask
 * - RecursiveTask<V> → returns a result
 * - RecursiveAction  → no result
 *
 * Example: Compute sum of a large array using divide-and-conquer.
 */
public class ForkJoinDemo {

    static class SumTask extends RecursiveTask<Long> {
        private static final int THRESHOLD = 1000;
        private final int[] arr;
        private final int start;
        private final int end;

        public SumTask(int[] arr, int start, int end) {
            this.arr = arr;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            int length = end - start;

            // Base case: small enough to compute directly
            if (length <= THRESHOLD) {
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += arr[i];
                }
                return sum;
            }

            // Divide
            int mid = start + length / 2;
            SumTask left = new SumTask(arr, start, mid);
            SumTask right = new SumTask(arr, mid, end);

            left.fork();                 // async execute left
            long rightResult = right.compute(); // compute right in current thread
            long leftResult = left.join();      // wait for left

            return leftResult + rightResult;
        }
    }

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        int[] arr = new int[10000];
        for (int i = 0; i < arr.length; i++) arr[i] = i + 1;

        ForkJoinPool pool = new ForkJoinPool();
        long result = pool.invoke(new SumTask(arr, 0, arr.length));

        System.out.println("Fork/Join sum of 1..10000 = " + result);
        System.out.println("Expected                 = " + (10000L * 10001 / 2));
        pool.shutdown();
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static long forkJoinSum(int[] arr) {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        return pool.invoke(new SumTask(arr, 0, arr.length));
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Enter array size: ");
        int size = sc.nextInt();

        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = i + 1;

        long sum = forkJoinSum(arr);
        System.out.println("Fork/Join sum = " + sum);
        System.out.println("Expected      = " + (size * (size + 1L) / 2));
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        int[] data = new int[50000];
        for (int i = 0; i < data.length; i++) data[i] = i + 1;
        System.out.println("Sum = " + forkJoinSum(data));

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
