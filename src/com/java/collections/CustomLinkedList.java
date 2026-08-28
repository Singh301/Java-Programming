package com.java.collections;

import java.util.Scanner;

/**
 * ============================================================
 * 41. CUSTOM LINKED LIST
 * ============================================================
 *
 * CONCEPT:
 * A Linked List is a linear data structure where each element
 * (node) contains data and a reference (link) to the next node.
 *
 * Advantages over Array:
 * - Dynamic size
 * - Easy insertion/deletion
 *
 * Disadvantages:
 * - No random access (must traverse from head)
 * - Extra memory for links
 *
 * We implement a Singly Linked List with:
 * - add (at end)
 * - addFirst
 * - delete
 * - display
 * - size
 */
public class CustomLinkedList {

    // Node class
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public CustomLinkedList() {
        this.head = null;
        this.size = 0;
    }

    // ============================================================
    // VERSION 1: WITHOUT METHOD (basic operations inline)
    // ============================================================
    public static void demoWithoutMethod() {
        // Manual creation of linked list
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);

        System.out.print("Linked List: ");
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " → ");
            current = current.next;
        }
        System.out.println("null");
    }

    // ============================================================
    // VERSION 2: WITH METHOD (full custom LinkedList)
    // ============================================================
    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public void addFirst(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void delete(int data) {
        if (head == null) return;

        if (head.data == data) {
            head = head.next;
            size--;
            return;
        }

        Node current = head;
        while (current.next != null && current.next.data != data) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
            size--;
        }
    }

    public void display() {
        Node current = head;
        System.out.print("List: ");
        while (current != null) {
            System.out.print(current.data + " → ");
            current = current.next;
        }
        System.out.println("null");
    }

    public int size() {
        return size;
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        Scanner sc = new Scanner(System.in);
        CustomLinkedList list = new CustomLinkedList();

        System.out.print("How many elements to add? ");
        int n = sc.nextInt();

        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }

        list.display();
        System.out.println("Size: " + list.size());

        System.out.print("Enter element to delete: ");
        int del = sc.nextInt();
        list.delete(del);
        list.display();
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        CustomLinkedList list = new CustomLinkedList();
        list.add(10);
        list.add(20);
        list.add(30);
        list.addFirst(5);
        list.display();
        list.delete(20);
        list.display();
        System.out.println("Size: " + list.size());

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
