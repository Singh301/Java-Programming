package com.java.GC.JVM;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * ============================================================
 * 137. CACHE USING WEAK REFERENCES
 * ============================================================
 *
 * CONCEPT:
 * WeakReference: Object is eligible for GC as soon as no strong
 * references remain (even if weak references exist).
 *
 * WeakHashMap:
 * - Keys are weak references
 * - Entry is automatically removed when key is no longer
 *   strongly reachable
 *
 * Perfect for caches where you don't want to prevent GC of keys.
 */
public class WeakReferenceCache {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        Map<Object, String> weakCache = new WeakHashMap<>();

        Object key1 = new Object();
        Object key2 = new Object();

        weakCache.put(key1, "Value-1");
        weakCache.put(key2, "Value-2");

        System.out.println("Cache size before: " + weakCache.size());

        key1 = null; // remove strong reference
        System.gc();
        try { Thread.sleep(200); } catch (InterruptedException e) {}

        System.out.println("Cache size after nulling key1 + GC: " + weakCache.size());
        System.out.println("(key1 entry should be removed by WeakHashMap)");
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void weakCacheDemo() {
        Map<String, WeakReference<byte[]>> cache = new HashMap<>();

        String key = "image1";
        byte[] data = new byte[1024 * 50]; // 50 KB
        cache.put(key, new WeakReference<>(data));

        System.out.println("Cached data available? " + (cache.get(key).get() != null));

        data = null; // drop strong ref
        System.gc();
        try { Thread.sleep(200); } catch (InterruptedException e) {}

        WeakReference<byte[]> ref = cache.get(key);
        System.out.println("After GC, data still available? " + (ref != null && ref.get() != null));
        System.out.println("(Weak refs are cleared more aggressively than soft refs)");
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Number of entries in WeakHashMap: ");
        int n = sc.nextInt();

        Map<Object, String> map = new WeakHashMap<>();
        Object[] keys = new Object[n];

        for (int i = 0; i < n; i++) {
            keys[i] = new Object();
            map.put(keys[i], "Value-" + i);
        }

        System.out.println("Size before: " + map.size());

        // Drop half of the strong references
        for (int i = 0; i < n / 2; i++) {
            keys[i] = null;
        }

        System.gc();
        try { Thread.sleep(300); } catch (InterruptedException e) {}

        System.out.println("Size after dropping half keys + GC: " + map.size());
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        weakCacheDemo();

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
