package com.java.collections;

import java.util.Scanner;

/**
 * ============================================================
 * 50. IMPLEMENT A HASH MAP FROM SCRATCH
 * ============================================================
 *
 * CONCEPT:
 * HashMap stores key-value pairs.
 * It uses hashing to achieve average O(1) time for put, get, remove.
 *
 * Internal structure:
 * - Array of buckets
 * - Each bucket is a linked list of nodes (to handle collisions)
 * - hash(key) % capacity → finds the bucket index
 *
 * We implement a simple HashMap with:
 * - put(key, value)
 * - get(key)
 * - remove(key)
 * - containsKey(key)
 * - size()
 * - display()
 */
public class CustomHashMap<K, V> {

    // Node for the linked list in each bucket
    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V>[] buckets;
    private int capacity;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public CustomHashMap() {
        this.capacity = DEFAULT_CAPACITY;
        this.buckets = (Node<K, V>[]) new Node[capacity];
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public CustomHashMap(int capacity) {
        this.capacity = capacity;
        this.buckets = (Node<K, V>[]) new Node[capacity];
        this.size = 0;
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    // ============================================================
    // VERSION 1: WITHOUT METHOD (simple demo concept)
    // ============================================================
    public static void demoWithoutMethod() {
        // Very simplified illustration of hashing idea
        String[] keys = {"apple", "banana", "cherry"};
        int[] values = {10, 20, 30};
        int capacity = 5;

        System.out.println("Simple hash illustration:");
        for (int i = 0; i < keys.length; i++) {
            int index = Math.abs(keys[i].hashCode()) % capacity;
            System.out.println(keys[i] + " → bucket " + index + " (value=" + values[i] + ")");
        }
    }

    // ============================================================
    // VERSION 2: WITH METHOD (full custom HashMap)
    // ============================================================
    public void put(K key, V value) {
        int index = getIndex(key);
        Node<K, V> head = buckets[index];

        // Check if key already exists → update value
        Node<K, V> current = head;
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        // Insert new node at beginning of bucket
        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = head;
        buckets[index] = newNode;
        size++;
    }

    public V get(K key) {
        int index = getIndex(key);
        Node<K, V> current = buckets[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;   // key not found
    }

    public void remove(K key) {
        int index = getIndex(key);
        Node<K, V> current = buckets[index];
        Node<K, V> prev = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public int size() {
        return size;
    }

    public void display() {
        System.out.println("HashMap contents:");
        for (int i = 0; i < capacity; i++) {
            Node<K, V> current = buckets[i];
            if (current != null) {
                System.out.print("Bucket " + i + ": ");
                while (current != null) {
                    System.out.print("[" + current.key + "=" + current.value + "] → ");
                    current = current.next;
                }
                System.out.println("null");
            }
        }
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        Scanner sc = new Scanner(System.in);
        CustomHashMap<String, Integer> map = new CustomHashMap<>();

        System.out.print("How many key-value pairs? ");
        int n = sc.nextInt();
        sc.nextLine();   // consume newline

        for (int i = 0; i < n; i++) {
            System.out.print("Enter key: ");
            String key = sc.nextLine();
            System.out.print("Enter value (integer): ");
            int value = sc.nextInt();
            sc.nextLine();
            map.put(key, value);
        }

        map.display();
        System.out.println("Size: " + map.size());

        System.out.print("Enter key to search: ");
        String searchKey = sc.nextLine();
        System.out.println("Value: " + map.get(searchKey));
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        CustomHashMap<String, Integer> map = new CustomHashMap<>();
        map.put("apple", 100);
        map.put("banana", 50);
        map.put("cherry", 75);
        map.put("apple", 120);   // update
        map.display();
        System.out.println("Get banana: " + map.get("banana"));
        map.remove("banana");
        System.out.println("After removing banana:");
        map.display();
        System.out.println("Size: " + map.size());

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
