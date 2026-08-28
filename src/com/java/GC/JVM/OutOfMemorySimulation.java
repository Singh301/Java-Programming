package com.java.GC.JVM;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * 139. SIMULATE OUT-OF-MEMORY ERROR
 * ============================================================
 *
 * CONCEPT:
 * OutOfMemoryError is thrown when JVM cannot allocate more memory.
 *
 * Types:
 * - Java heap space
 * - GC overhead limit exceeded
 * - Metaspace
 * - Direct buffer memory
 *
 * WARNING:
 * This demo is intentionally LIMITED so it does not crash
 * the entire environment. In real life OOM can kill the JVM.
 *
 * To actually see OOM you would run with low -Xmx, e.g.:
 *   java -Xmx16m com.java.GC.JVM.OutOfMemorySimulation
 */
public class OutOfMemorySimulation {

    // ============================================================
    // VERSION 1: WITHOUT METHOD (limited safe demo)
    // ============================================================
    public static void demoWithoutMethod() {
        List<byte[]> list = new ArrayList<>();
        Runtime rt = Runtime.getRuntime();

        System.out.println("Max heap: " + rt.maxMemory() / 1024 / 1024 + " MB");
        System.out.println("Allocating until we approach the limit (safe demo)...");

        try {
            while (true) {
                list.add(new byte[1024 * 1024]); // 1 MB
                long used = (rt.totalMemory() - rt.freeMemory()) / 1024 / 1024;
                if (used > rt.maxMemory() / 1024 / 1024 * 0.7) {
                    System.out.println("Approaching heap limit (" + used + " MB used). Stopping demo safely.");
                    break;
                }
            }
        } catch (OutOfMemoryError e) {
            System.out.println("Caught OutOfMemoryError: " + e.getMessage());
            list.clear();
            System.gc();
        }
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void allocateUntilPressure(int maxMB) {
        List<byte[]> list = new ArrayList<>();
        Runtime rt = Runtime.getRuntime();

        try {
            for (int i = 0; i < maxMB; i++) {
                list.add(new byte[1024 * 1024]);
                if (i % 10 == 0) {
                    long used = (rt.totalMemory() - rt.freeMemory()) / 1024 / 1024;
                    System.out.println("Allocated ~" + (i + 1) + " MB | Used: " + used + " MB");
                }
            }
            System.out.println("Successfully allocated " + maxMB + " MB (no OOM)");
        } catch (OutOfMemoryError e) {
            System.out.println("OutOfMemoryError occurred after allocating some memory");
            System.out.println("Message: " + e);
            list.clear();
            System.gc();
        }
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        Runtime rt = Runtime.getRuntime();
        System.out.println("Current max heap: " + rt.maxMemory() / 1024 / 1024 + " MB");
        System.out.print("How many MB to try allocating (keep small)? ");
        int mb = sc.nextInt();

        allocateUntilPressure(mb);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        System.out.println("NOTE: Demo stops before actual crash for safety.\n");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        allocateUntilPressure(20);

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
