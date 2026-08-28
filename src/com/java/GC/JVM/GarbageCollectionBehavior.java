package com.java.GC.JVM;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * 136. SHOW GARBAGE COLLECTION BEHAVIOR
 * ============================================================
 *
 * CONCEPT:
 * Garbage Collection automatically reclaims memory of objects
 * that are no longer reachable.
 *
 * Ways an object becomes eligible for GC:
 * - Reference set to null
 * - Reference reassigned
 * - Object goes out of scope
 * - Island of isolation
 *
 * System.gc() only requests GC – it is not guaranteed to run.
 */
public class GarbageCollectionBehavior {

    static class BigObject {
        private byte[] data = new byte[1024 * 100]; // 100 KB
        private String name;

        public BigObject(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        Runtime rt = Runtime.getRuntime();
        System.out.println("Used memory before: " + usedMB(rt) + " MB");

        BigObject obj1 = new BigObject("Obj-1");
        BigObject obj2 = new BigObject("Obj-2");

        System.out.println("Created two big objects");
        System.out.println("Used memory after creation: " + usedMB(rt) + " MB");

        obj1 = null;
        obj2 = null;
        System.out.println("References set to null → eligible for GC");

        System.gc();
        try { Thread.sleep(300); } catch (InterruptedException e) {}

        System.out.println("Used memory after GC request: " + usedMB(rt) + " MB");
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void demonstrateGC() {
        Runtime rt = Runtime.getRuntime();
        List<BigObject> list = new ArrayList<>();

        System.out.println("Before allocation: " + usedMB(rt) + " MB");

        for (int i = 0; i < 50; i++) {
            list.add(new BigObject("Obj-" + i));
        }
        System.out.println("After allocating 50 objects: " + usedMB(rt) + " MB");

        list.clear(); // remove all references
        System.out.println("List cleared → objects eligible for GC");

        System.gc();
        try { Thread.sleep(300); } catch (InterruptedException e) {}

        System.out.println("After System.gc(): " + usedMB(rt) + " MB");
    }

    private static long usedMB(Runtime rt) {
        return (rt.totalMemory() - rt.freeMemory()) / (1024 * 1024);
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("How many big objects to create? ");
        int n = sc.nextInt();

        Runtime rt = Runtime.getRuntime();
        List<BigObject> list = new ArrayList<>();

        System.out.println("Before: " + usedMB(rt) + " MB");
        for (int i = 0; i < n; i++) {
            list.add(new BigObject("Temp-" + i));
        }
        System.out.println("After create: " + usedMB(rt) + " MB");

        list.clear();
        System.gc();
        try { Thread.sleep(300); } catch (InterruptedException e) {}
        System.out.println("After clear + GC: " + usedMB(rt) + " MB");
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        demonstrateGC();

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
