package com.java.multithreading.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ============================================================
 * 75. THREAD POOL USING ExecutorService
 * ============================================================
 *
 * CONCEPT:
 * Creating a new Thread for every task is expensive.
 * Thread pools reuse a fixed number of threads to execute tasks.
 *
 * ExecutorService provides:
 * - newFixedThreadPool(n)
 * - newCachedThreadPool()
 * - newSingleThreadExecutor()
 * - newScheduledThreadPool(n)
 *
 * Benefits:
 * - Better resource management
 * - Controlled concurrency
 * - Easy task submission
 */
public class ThreadPoolDemo {

    static class Task implements Runnable {
        private final int taskId;

        public Task(int taskId) {
            this.taskId = taskId;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()
                    + " is executing Task-" + taskId);
            try {
                Thread.sleep(500); // simulate work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Task-" + taskId + " completed");
        }
    }

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 6; i++) {
            executor.submit(new Task(i));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All tasks finished.");
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void runTasksWithPool(int poolSize, int taskCount) {
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);

        for (int i = 1; i <= taskCount; i++) {
            final int id = i;
            executor.submit(() -> {
                System.out.println(Thread.currentThread().getName()
                        + " → Task " + id);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
        System.out.println("Thread pool completed all tasks.");
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Enter thread pool size: ");
        int poolSize = sc.nextInt();
        System.out.print("Enter number of tasks: ");
        int tasks = sc.nextInt();

        runTasksWithPool(poolSize, tasks);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        runTasksWithPool(4, 8);

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
