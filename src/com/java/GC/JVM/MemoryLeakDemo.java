package com.java.GC.JVM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ============================================================
 * 132. CREATE A MEMORY LEAK IN JAVA (DEMO)
 * ============================================================
 *
 * CONCEPT:
 * A memory leak in Java happens when objects are no longer needed
 * but still referenced, so GC cannot reclaim them.
 *
 * Common causes:
 * - Static collections that keep growing
 * - Unclosed resources
 * - Listeners not removed
 * - Inner classes holding outer references
 * - Caches without eviction
 *
 * This is a CONTROLLED demo. Do not run unbounded in production.
 */
public class MemoryLeakDemo {

    // Static list that keeps growing → classic leak pattern
    private static final List<byte[]> LEAKING_LIST = new ArrayList<>();

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        System.out.println("Simulating a small memory leak (limited)...");
        Runtime rt = Runtime.getRuntime();

        long before = rt.totalMemory() - rt.freeMemory();
        System.out.println("Used memory before: " + (before / 1024) + " KB");

        // Add a limited number of objects so we don't crash the demo
        for (int i = 0; i < 100; i++) {
            LEAKING_LIST.add(new byte[1024 * 10]); // 10 KB each
        }

        long after = rt.totalMemory() - rt.freeMemory();
        System.out.println("Used memory after : " + (after / 1024) + " KB");
        System.out.println("Leaking list size : " + LEAKING_LIST.size());
        System.out.println("(Objects are held by static list → cannot be GC'd)");
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void simulateLeak(int count, int sizeInKB) {
        List<byte[]> localLeak = new ArrayList<>();
        Runtime rt = Runtime.getRuntime();

        long before = usedMemory(rt);
        for (int i = 0; i < count; i++) {
            localLeak.add(new byte[sizeInKB * 1024]);
        }
        long after = usedMemory(rt);

        System.out.println("Added " + count + " blocks of " + sizeInKB + " KB");
        System.out.println("Memory increase ≈ " + ((after - before) / 1024) + " KB");
        System.out.println("List still holds references → GC cannot free them yet");

        // Clear to allow GC (in real leak we would forget this)
        localLeak.clear();
        System.gc();
    }

    private static long usedMemory(Runtime rt) {
        return rt.totalMemory() - rt.freeMemory();
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Number of objects to allocate: ");
        int count = sc.nextInt();
        System.out.print("Size of each object in KB: ");
        int sizeKB = sc.nextInt();

        simulateLeak(count, sizeKB);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        simulateLeak(50, 5);

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
