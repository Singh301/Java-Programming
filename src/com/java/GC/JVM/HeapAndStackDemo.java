package com.java.GC.JVM;

/**
 * ============================================================
 * 135. HEAP AND STACK MEMORY USAGE
 * ============================================================
 *
 * CONCEPT:
 *
 * STACK MEMORY:
 * - Stores method calls, local variables, partial results
 * - Each thread has its own stack
 * - Fast allocation/deallocation
 * - Limited size (StackOverflowError if exceeded)
 *
 * HEAP MEMORY:
 * - Stores objects and instance variables
 * - Shared among all threads
 * - Managed by Garbage Collector
 * - OutOfMemoryError if exhausted
 *
 * This program shows both concepts with simple examples.
 */
public class HeapAndStackDemo {

    // ============================================================
    // VERSION 1: WITHOUT METHOD - Stack demo (recursion)
    // ============================================================
    public static void demoWithoutMethod() {
        System.out.println("--- Stack Memory Demo (local variables & call stack) ---");
        int localVar = 42;  // stored on stack
        System.out.println("Local variable on stack: " + localVar);

        System.out.println("\n--- Heap Memory Demo (objects) ---");
        String obj = new String("Hello Heap"); // object on heap, reference on stack
        System.out.println("Object on heap: " + obj);

        Runtime rt = Runtime.getRuntime();
        System.out.println("\nHeap info:");
        System.out.println("Total Heap : " + rt.totalMemory() / 1024 / 1024 + " MB");
        System.out.println("Free Heap  : " + rt.freeMemory() / 1024 / 1024 + " MB");
        System.out.println("Used Heap  : " + (rt.totalMemory() - rt.freeMemory()) / 1024 / 1024 + " MB");
        System.out.println("Max Heap   : " + rt.maxMemory() / 1024 / 1024 + " MB");
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void printMemoryStats() {
        Runtime rt = Runtime.getRuntime();
        long total = rt.totalMemory();
        long free = rt.freeMemory();
        long used = total - free;
        long max = rt.maxMemory();

        System.out.println("===== JVM Heap Memory =====");
        System.out.printf("Used  : %,d bytes (%.2f MB)%n", used, used / 1024.0 / 1024);
        System.out.printf("Free  : %,d bytes (%.2f MB)%n", free, free / 1024.0 / 1024);
        System.out.printf("Total : %,d bytes (%.2f MB)%n", total, total / 1024.0 / 1024);
        System.out.printf("Max   : %,d bytes (%.2f MB)%n", max, max / 1024.0 / 1024);
    }

    public static void stackOverflowDemo(int depth) {
        // WARNING: This will cause StackOverflowError if depth is too large
        System.out.println("Recursion depth: " + depth);
        if (depth > 0) {
            stackOverflowDemo(depth); // infinite recursion risk
        }
    }

    // Safe limited recursion to show stack usage
    public static int factorialStack(int n) {
        if (n <= 1) return 1;
        return n * factorialStack(n - 1); // each call uses stack frame
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        printMemoryStats();

        System.out.print("\nEnter n for factorial (uses stack): ");
        int n = sc.nextInt();
        System.out.println("Factorial of " + n + " = " + factorialStack(n));

        printMemoryStats();
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        printMemoryStats();
        System.out.println("factorial(5) = " + factorialStack(5));

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
