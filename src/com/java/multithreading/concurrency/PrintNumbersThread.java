package com.java.multithreading.concurrency;

/**
 * ============================================================
 * 71. THREAD THAT PRINTS NUMBERS FROM 1 TO 100
 * ============================================================
 *
 * CONCEPT:
 * A Thread is a lightweight process. Java provides two ways
 * to create a thread:
 * 1. Extend Thread class
 * 2. Implement Runnable interface (preferred)
 *
 * We will demonstrate both ways to print numbers 1 to 100.
 */
public class PrintNumbersThread {

    // ============================================================
    // VERSION 1: WITHOUT METHOD (Extend Thread)
    // ============================================================
    static class NumberThread extends Thread {
        @Override
        public void run() {
            for (int i = 1; i <= 100; i++) {
                System.out.print(i + " ");
                if (i % 10 == 0) System.out.println(); // new line every 10 numbers
            }
        }
    }

    public static void demoWithoutMethod() {
        System.out.println("Printing 1 to 100 using Thread subclass:");
        NumberThread t = new NumberThread();
        t.start();   // start() creates a new thread and calls run()
        try {
            t.join(); // wait for thread to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ============================================================
    // VERSION 2: WITH METHOD (Implement Runnable)
    // ============================================================
    static class NumberRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 100; i++) {
                System.out.print(i + " ");
                if (i % 10 == 0) System.out.println();
            }
        }
    }

    public static void printNumbersWithRunnable() {
        System.out.println("\nPrinting 1 to 100 using Runnable:");
        Thread t = new Thread(new NumberRunnable());
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Using Lambda (Java 8+)
    public static void printNumbersWithLambda() {
        System.out.println("\nPrinting 1 to 100 using Lambda:");
        Thread t = new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                System.out.print(i + " ");
                if (i % 10 == 0) System.out.println();
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void printWithUserInput() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Enter the upper limit (e.g. 100): ");
        int limit = sc.nextInt();

        Thread t = new Thread(() -> {
            for (int i = 1; i <= limit; i++) {
                System.out.print(i + " ");
                if (i % 10 == 0) System.out.println();
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        printNumbersWithRunnable();
        printNumbersWithLambda();

        System.out.println("\n===== VERSION 3: With User Input =====");
        printWithUserInput();
    }
}
