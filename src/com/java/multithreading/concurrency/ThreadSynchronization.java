package com.java.multithreading.concurrency;

/**
 * ============================================================
 * 72. THREAD SYNCHRONIZATION
 * ============================================================
 *
 * CONCEPT:
 * When multiple threads access shared data, race conditions can occur.
 * Synchronization ensures that only one thread can access the
 * critical section at a time.
 *
 * Ways to synchronize in Java:
 * 1. synchronized method
 * 2. synchronized block
 * 3. ReentrantLock (java.util.concurrent.locks)
 *
 * Example: Two threads incrementing a shared counter.
 */
public class ThreadSynchronization {

    // Shared resource
    static class Counter {
        private int count = 0;

        // Without synchronization → race condition
        public void incrementUnsafe() {
            count++;
        }

        // Synchronized method
        public synchronized void incrementSafe() {
            count++;
        }

        // Synchronized block
        public void incrementWithBlock() {
            synchronized (this) {
                count++;
            }
        }

        public int getCount() {
            return count;
        }
    }

    // ============================================================
    // VERSION 1: WITHOUT METHOD (shows race condition)
    // ============================================================
    public static void demoWithoutMethod() throws InterruptedException {
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) counter.incrementUnsafe();
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) counter.incrementUnsafe();
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Without synchronization → count = " + counter.getCount()
                + " (expected 20000, often less due to race condition)");
    }

    // ============================================================
    // VERSION 2: WITH METHOD (synchronized)
    // ============================================================
    public static void demoWithSynchronization() throws InterruptedException {
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) counter.incrementSafe();
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) counter.incrementSafe();
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("With synchronization   → count = " + counter.getCount()
                + " (always 20000)");
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() throws InterruptedException {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Enter number of increments per thread: ");
        int n = sc.nextInt();

        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < n; i++) counter.incrementSafe();
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < n; i++) counter.incrementSafe();
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Final count = " + counter.getCount() + " (expected " + (2 * n) + ")");
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) throws InterruptedException {
        System.out.println("===== VERSION 1: Without Method (Race Condition) =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method (Synchronized) =====");
        demoWithSynchronization();

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
