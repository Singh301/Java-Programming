package com.java.multithreading.concurrency;

/**
 * ============================================================
 * 74. DEADLOCK DEMONSTRATION
 * ============================================================
 *
 * CONCEPT:
 * Deadlock occurs when two or more threads are blocked forever,
 * each waiting for a lock held by the other.
 *
 * Classic example:
 * Thread-1 locks Resource-A and waits for Resource-B
 * Thread-2 locks Resource-B and waits for Resource-A
 *
 * Conditions for deadlock (Coffman conditions):
 * 1. Mutual Exclusion
 * 2. Hold and Wait
 * 3. No Preemption
 * 4. Circular Wait
 *
 * This program intentionally creates a deadlock so you can observe it.
 * (It will hang – that is expected behavior)
 */
public class DeadlockDemo {

    private static final Object LOCK_A = new Object();
    private static final Object LOCK_B = new Object();

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        Thread t1 = new Thread(() -> {
            synchronized (LOCK_A) {
                System.out.println("Thread-1: Locked A");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                System.out.println("Thread-1: Waiting for B...");
                synchronized (LOCK_B) {
                    System.out.println("Thread-1: Locked B");
                }
            }
        }, "Thread-1");

        Thread t2 = new Thread(() -> {
            synchronized (LOCK_B) {
                System.out.println("Thread-2: Locked B");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                System.out.println("Thread-2: Waiting for A...");
                synchronized (LOCK_A) {
                    System.out.println("Thread-2: Locked A");
                }
            }
        }, "Thread-2");

        t1.start();
        t2.start();

        // Note: Program will hang due to deadlock
        // In real code we would avoid this by acquiring locks in same order
    }

    // ============================================================
    // VERSION 2: WITH METHOD (safe version + deadlock version)
    // ============================================================
    public static void createDeadlock() {
        System.out.println("Creating deadlock scenario (program will hang)...");
        demoWithoutMethod();
    }

    /**
     * Prevention: Always acquire locks in the same global order.
     */
    public static void avoidDeadlock() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (LOCK_A) {
                System.out.println("Safe-T1: Locked A");
                synchronized (LOCK_B) {
                    System.out.println("Safe-T1: Locked B");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (LOCK_A) {          // same order as t1
                System.out.println("Safe-T2: Locked A");
                synchronized (LOCK_B) {
                    System.out.println("Safe-T2: Locked B");
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("No deadlock – both threads finished.");
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() throws InterruptedException {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.println("1. Create Deadlock (will hang)");
        System.out.println("2. Avoid Deadlock (safe order)");
        System.out.print("Choice: ");
        int choice = sc.nextInt();

        if (choice == 1) {
            createDeadlock();
            // Give some time then inform user
            Thread.sleep(2000);
            System.out.println("\n(If you see this, deadlock may not have occurred yet)");
        } else {
            avoidDeadlock();
        }
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) throws InterruptedException {
        System.out.println("===== VERSION 1: Deadlock Demo =====");
        System.out.println("(This will intentionally hang – press Ctrl+C to stop)");
        // Uncomment next line to see actual deadlock
        // demoWithoutMethod();

        System.out.println("\n===== VERSION 2: Avoid Deadlock =====");
        avoidDeadlock();

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
