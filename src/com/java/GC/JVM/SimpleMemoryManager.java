package com.java.GC.JVM;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ============================================================
 * 134. SIMPLE MEMORY MANAGER (LRU CACHE STYLE)
 * ============================================================
 *
 * CONCEPT:
 * A simple memory manager that limits the number of objects
 * it holds. When capacity is exceeded, least-recently-used
 * entries are removed (simulating memory pressure handling).
 *
 * This is a teaching example of how caches manage memory.
 */
public class SimpleMemoryManager {

    static class MemoryManager<K, V> {
        private final int maxSize;
        private final Map<K, V> store;

        public MemoryManager(int maxSize) {
            this.maxSize = maxSize;
            // LinkedHashMap with access-order = true → LRU behavior
            this.store = new LinkedHashMap<>(16, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                    return size() > maxSize;
                }
            };
        }

        public void put(K key, V value) {
            store.put(key, value);
            System.out.println("Stored: " + key + " | Current size: " + store.size());
        }

        public V get(K key) {
            return store.get(key);
        }

        public int size() {
            return store.size();
        }

        public void display() {
            System.out.println("Memory Manager contents: " + store.keySet());
        }
    }

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        MemoryManager<String, byte[]> manager = new MemoryManager<>(3);

        manager.put("A", new byte[1024]);
        manager.put("B", new byte[1024]);
        manager.put("C", new byte[1024]);
        manager.display();

        manager.put("D", new byte[1024]); // should evict eldest
        manager.display();
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void runMemoryManagerDemo(int capacity) {
        MemoryManager<Integer, String> mm = new MemoryManager<>(capacity);

        for (int i = 1; i <= capacity + 2; i++) {
            mm.put(i, "Value-" + i);
        }
        mm.display();
        System.out.println("Final size (should be <= " + capacity + "): " + mm.size());
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Enter max capacity of memory manager: ");
        int capacity = sc.nextInt();

        runMemoryManagerDemo(capacity);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        runMemoryManagerDemo(4);

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
