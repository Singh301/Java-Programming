package com.java.GC.JVM;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * 133. USE SOFT REFERENCES
 * ============================================================
 *
 * CONCEPT:
 * SoftReference: The object is cleared by GC only when memory
 * is low (before OutOfMemoryError).
 *
 * Use cases:
 * - Memory-sensitive caches
 * - Image caches in applications
 *
 * Reference types in Java:
 * - Strong  : normal reference (default)
 * - Soft    : cleared under memory pressure
 * - Weak    : cleared on next GC cycle
 * - Phantom : for advanced cleanup tracking
 */
public class SoftReferenceDemo {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        // Create a large object
        byte[] data = new byte[1024 * 1024]; // 1 MB
        SoftReference<byte[]> softRef = new SoftReference<>(data);

        System.out.println("Soft reference created. Object available? " + (softRef.get() != null));

        // Remove strong reference
        data = null;

        System.out.println("Strong reference removed.");
        System.out.println("Object still available via soft ref? " + (softRef.get() != null));

        // Suggest GC
        System.gc();
        System.out.println("After System.gc() - Object available? " + (softRef.get() != null));
        System.out.println("(Soft refs are usually kept unless memory is low)");
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void softCacheDemo() {
        List<SoftReference<byte[]>> cache = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            byte[] block = new byte[512 * 1024]; // 512 KB
            cache.add(new SoftReference<>(block));
            System.out.println("Cached block " + i);
        }

        System.out.println("\nChecking cache before GC:");
        printCacheStatus(cache);

        System.gc();
        try { Thread.sleep(200); } catch (InterruptedException e) {}

        System.out.println("\nChecking cache after GC:");
        printCacheStatus(cache);
    }

    private static void printCacheStatus(List<SoftReference<byte[]>> cache) {
        int alive = 0;
        for (SoftReference<byte[]> ref : cache) {
            if (ref.get() != null) alive++;
        }
        System.out.println("Alive soft references: " + alive + " / " + cache.size());
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Number of soft-referenced blocks to create: ");
        int n = sc.nextInt();

        List<SoftReference<byte[]>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new SoftReference<>(new byte[256 * 1024]));
        }

        System.out.println("Created " + n + " soft references.");
        System.gc();
        printCacheStatus(list);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        softCacheDemo();

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
