package com.java.multithreading.concurrency;

/**
 * ============================================================
 * 76. SUM OF ARRAY USING PARALLEL THREADS
 * ============================================================
 *
 * CONCEPT:
 * Divide the array into parts and let multiple threads
 * compute partial sums, then combine the results.
 *
 * This demonstrates data parallelism.
 */
public class ParallelArraySum {

    static class SumTask implements Runnable {
        private final int[] arr;
        private final int start;
        private final int end;
        private long partialSum;

        public SumTask(int[] arr, int start, int end) {
            this.arr = arr;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += arr[i];
            }
            partialSum = sum;
        }

        public long getPartialSum() {
            return partialSum;
        }
    }

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() throws InterruptedException {
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++) arr[i] = i + 1; // 1 to 1000

        int mid = arr.length / 2;

        SumTask task1 = new SumTask(arr, 0, mid);
        SumTask task2 = new SumTask(arr, mid, arr.length);

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        long total = task1.getPartialSum() + task2.getPartialSum();
        System.out.println("Sum of 1 to 1000 using 2 threads = " + total);
        System.out.println("Expected (n*(n+1)/2) = " + (1000L * 1001 / 2));
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static long parallelSum(int[] arr, int numThreads) throws InterruptedException {
        int length = arr.length;
        int chunkSize = (length + numThreads - 1) / numThreads;

        SumTask[] tasks = new SumTask[numThreads];
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            int start = i * chunkSize;
            int end = Math.min(start + chunkSize, length);
            tasks[i] = new SumTask(arr, start, end);
            threads[i] = new Thread(tasks[i]);
            threads[i].start();
        }

        long total = 0;
        for (int i = 0; i < numThreads; i++) {
            threads[i].join();
            total += tasks[i].getPartialSum();
        }
        return total;
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() throws InterruptedException {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Enter array size: ");
        int size = sc.nextInt();
        System.out.print("Enter number of threads: ");
        int threads = sc.nextInt();

        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = i + 1;

        long sum = parallelSum(arr, threads);
        System.out.println("Parallel sum = " + sum);
        System.out.println("Expected     = " + (size * (size + 1L) / 2));
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) throws InterruptedException {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        int[] data = new int[10000];
        for (int i = 0; i < data.length; i++) data[i] = i + 1;
        System.out.println("Sum with 4 threads = " + parallelSum(data, 4));

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
