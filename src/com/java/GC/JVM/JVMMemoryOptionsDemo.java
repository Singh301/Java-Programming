package com.java.GC.JVM;

/**
 * ============================================================
 * 140. DEMONSTRATE MEMORY USAGE WITH JVM OPTIONS
 * ============================================================
 *
 * CONCEPT:
 * JVM memory can be controlled with command-line options:
 *
 * -Xms<size>   Initial heap size
 * -Xmx<size>   Maximum heap size
 * -Xss<size>   Thread stack size
 * -XX:MetaspaceSize / MaxMetaspaceSize
 *
 * Examples:
 *   java -Xms64m -Xmx256m MyClass
 *   java -Xmx512m -XX:+PrintGCDetails MyClass
 *
 * This program prints current memory settings and explains
 * the important flags. Actual flags must be passed when
 * starting the JVM.
 */
public class JVMMemoryOptionsDemo {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        Runtime rt = Runtime.getRuntime();

        System.out.println("===== Current JVM Memory Settings =====");
        System.out.println("Max Heap   (-Xmx) : " + format(rt.maxMemory()));
        System.out.println("Total Heap        : " + format(rt.totalMemory()));
        System.out.println("Free Heap         : " + format(rt.freeMemory()));
        System.out.println("Used Heap         : " + format(rt.totalMemory() - rt.freeMemory()));
        System.out.println("Available Processors: " + rt.availableProcessors());
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void printJVMInfo() {
        Runtime rt = Runtime.getRuntime();

        System.out.println("--------------------------------------------------");
        System.out.println(" JVM Memory Information");
        System.out.println("--------------------------------------------------");
        System.out.printf(" Max Memory   (-Xmx) : %s%n", format(rt.maxMemory()));
        System.out.printf(" Total Memory        : %s%n", format(rt.totalMemory()));
        System.out.printf(" Free Memory         : %s%n", format(rt.freeMemory()));
        System.out.printf(" Used Memory         : %s%n", format(rt.totalMemory() - rt.freeMemory()));
        System.out.printf(" Processors          : %d%n", rt.availableProcessors());
        System.out.println("--------------------------------------------------");

        System.out.println("\nUseful JVM Options:");
        System.out.println("  -Xms128m              Initial heap size");
        System.out.println("  -Xmx512m              Maximum heap size");
        System.out.println("  -Xss1m                Thread stack size");
        System.out.println("  -XX:+UseG1GC          Use G1 Garbage Collector");
        System.out.println("  -XX:+PrintGCDetails   Print GC details (older Java)");
        System.out.println("  -Xlog:gc*             GC logging (Java 9+)");
    }

    private static String format(long bytes) {
        double mb = bytes / 1024.0 / 1024.0;
        return String.format("%.2f MB (%,d bytes)", mb, bytes);
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        printJVMInfo();

        System.out.print("\nPress Enter to request GC and reprint stats...");
        sc.nextLine();

        System.gc();
        try { Thread.sleep(300); } catch (InterruptedException e) {}
        printJVMInfo();
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        printJVMInfo();

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
