package com.java.GC.JVM;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * ============================================================
 * 138. TRACK OBJECT ALLOCATION AND GC EVENTS
 * ============================================================
 *
 * CONCEPT:
 * We can query JVM MXBeans to get information about
 * Garbage Collectors that have run.
 *
 * GarbageCollectorMXBean provides:
 * - Collection count
 * - Collection time
 * - Name of the GC algorithm
 *
 * This is useful for monitoring and performance tuning.
 */
public class TrackGCEvents {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();

        System.out.println("Available Garbage Collectors:");
        for (GarbageCollectorMXBean bean : gcBeans) {
            System.out.println("  Name            : " + bean.getName());
            System.out.println("  Collection Count: " + bean.getCollectionCount());
            System.out.println("  Collection Time : " + bean.getCollectionTime() + " ms");
            System.out.println();
        }
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void printGCStats() {
        System.out.println("===== GC Statistics =====");
        for (GarbageCollectorMXBean bean : ManagementFactory.getGarbageCollectorMXBeans()) {
            System.out.printf("%-25s | Count: %-6d | Time: %d ms%n",
                    bean.getName(),
                    bean.getCollectionCount(),
                    bean.getCollectionTime());
        }
    }

    public static void allocateAndTrack() {
        printGCStats();

        System.out.println("\nAllocating objects...");
        for (int i = 0; i < 10000; i++) {
            byte[] b = new byte[1024]; // force some allocation
        }
        System.gc();

        try { Thread.sleep(200); } catch (InterruptedException e) {}
        System.out.println("\nAfter allocation + GC request:");
        printGCStats();
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Press Enter to view current GC stats...");
        sc.nextLine();

        printGCStats();

        System.out.print("\nAllocate how many 1KB objects? ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            byte[] b = new byte[1024];
        }
        System.gc();
        try { Thread.sleep(200); } catch (InterruptedException e) {}

        System.out.println("\nUpdated GC stats:");
        printGCStats();
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        allocateAndTrack();

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
