package com.java.multithreading.concurrency;

import java.util.concurrent.CountDownLatch;

/**
 * ============================================================
 * 80. COUNTDOWN LATCH
 * ============================================================
 *
 * CONCEPT:
 * CountDownLatch is a synchronization aid that allows one or more
 * threads to wait until a set of operations being performed in
 * other threads completes.
 *
 * - Created with a given count
 * - countDown() decreases the count
 * - await() blocks until count reaches zero
 *
 * Classic use cases:
 * - Start a race after all runners are ready
 * - Wait for several services to initialize before starting the app
 * - Divide a task into N parts and wait for all parts to finish
 */
public class CountDownLatchDemo {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() throws InterruptedException {
        int numberOfWorkers = 3;
        CountDownLatch latch = new CountDownLatch(numberOfWorkers);

        for (int i = 1; i <= numberOfWorkers; i++) {
            final int id = i;
            new Thread(() -> {
                try {
                    System.out.println("Worker-" + id + " is working...");
                    Thread.sleep(1000 * id);
                    System.out.println("Worker-" + id + " finished");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    latch.countDown();  // signal completion
                }
            }).start();
        }

        System.out.println("Main thread waiting for all workers...");
        latch.await();   // blocks until count == 0
        System.out.println("All workers finished. Main thread proceeds.");
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void runWithLatch(int workers) throws InterruptedException {
        CountDownLatch startLatch = new CountDownLatch(1);      // to start all together
        CountDownLatch doneLatch = new CountDownLatch(workers);  // to wait for finish

        for (int i = 1; i <= workers; i++) {
            final int id = i;
            new Thread(() -> {
                try {
                    startLatch.await();  // wait for the start signal
                    System.out.println("Runner-" + id + " started");
                    Thread.sleep(500 + id * 200);
                    System.out.println("Runner-" + id + " finished");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    doneLatch.countDown();
                }
            }).start();
        }

        Thread.sleep(500);
        System.out.println(">>> GO! <<<");
        startLatch.countDown();  // release all runners

        doneLatch.await();
        System.out.println("Race finished!");
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() throws InterruptedException {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Enter number of workers: ");
        int n = sc.nextInt();

        runWithLatch(n);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) throws InterruptedException {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        runWithLatch(4);

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
