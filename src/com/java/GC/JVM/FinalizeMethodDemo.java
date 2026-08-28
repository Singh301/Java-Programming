package com.java.GC.JVM;

/**
 * ============================================================
 * 131. DEMONSTRATE THE USE OF finalize() METHOD
 * ============================================================
 *
 * CONCEPT:
 * finalize() was called by the Garbage Collector just before
 * reclaiming an object's memory.
 *
 * WARNING:
 * - finalize() is DEPRECATED since Java 9 and removed for removal
 * - It is unreliable (may never be called)
 * - Never rely on it for resource cleanup
 * - Prefer try-with-resources or Cleaner API instead
 *
 * This program only demonstrates the concept for learning.
 */
public class FinalizeMethodDemo {

    static class ResourceHolder {
        private String name;

        public ResourceHolder(String name) {
            this.name = name;
            System.out.println(name + " created");
        }

        @Override
        @SuppressWarnings("deprecation")
        protected void finalize() throws Throwable {
            try {
                System.out.println(name + " is being garbage collected (finalize called)");
            } finally {
                super.finalize();
            }
        }
    }

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        ResourceHolder obj1 = new ResourceHolder("Object-1");
        ResourceHolder obj2 = new ResourceHolder("Object-2");

        obj1 = null;  // make eligible for GC
        obj2 = null;

        System.out.println("Requesting Garbage Collection...");
        System.gc();          // suggestion only, not guaranteed
        System.runFinalization();

        try {
            Thread.sleep(1000); // give GC some time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Demo finished (finalize may or may not have run)");
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void createAndDropObjects(int count) {
        for (int i = 1; i <= count; i++) {
            ResourceHolder obj = new ResourceHolder("Temp-" + i);
            obj = null;
        }
        System.gc();
        System.runFinalization();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("How many objects to create and drop? ");
        int n = sc.nextInt();
        createAndDropObjects(n);
        System.out.println("Done. Check if finalize messages appeared.");
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        System.out.println("NOTE: finalize() is deprecated. This is for learning only.\n");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        createAndDropObjects(3);

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
