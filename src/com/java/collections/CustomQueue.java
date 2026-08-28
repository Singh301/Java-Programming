package com.java.collections;

import java.util.Scanner;

/**
 * ============================================================
 * 43. CUSTOM QUEUE
 * ============================================================
 *
 * CONCEPT:
 * Queue follows FIFO (First In First Out) principle.
 * The first element added is the first one to be removed.
 *
 * Real-life examples:
 * - Queue at a ticket counter
 * - Print queue
 * - Task scheduling
 *
 * Main operations:
 * - enqueue() : add element at rear
 * - dequeue() : remove element from front
 * - peek()    : look at front element
 * - isEmpty()
 * - size()
 *
 * We implement a circular queue using an array for efficiency.
 */
public class CustomQueue {

    private int[] arr;
    private int front;
    private int rear;
    private int capacity;
    private int count;

    public CustomQueue(int size) {
        arr = new int[size];
        capacity = size;
        front = 0;
        rear = -1;
        count = 0;
    }

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        int[] queue = new int[5];
        int front = 0;
        int rear = -1;

        // enqueue
        queue[++rear] = 10;
        queue[++rear] = 20;
        queue[++rear] = 30;

        System.out.println("Front element: " + queue[front]);

        // dequeue
        System.out.println("Dequeued: " + queue[front++]);
        System.out.println("New front: " + queue[front]);
    }

    // ============================================================
    // VERSION 2: WITH METHOD (Circular Queue)
    // ============================================================
    public void enqueue(int value) {
        if (count == capacity) {
            System.out.println("Queue Overflow! Cannot enqueue " + value);
            return;
        }
        rear = (rear + 1) % capacity;
        arr[rear] = value;
        count++;
        System.out.println("Enqueued: " + value);
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue Underflow! Cannot dequeue");
            return -1;
        }
        int value = arr[front];
        front = (front + 1) % capacity;
        count--;
        return value;
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        return arr[front];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        System.out.print("Queue (front to rear): ");
        for (int i = 0; i < count; i++) {
            System.out.print(arr[(front + i) % capacity] + " ");
        }
        System.out.println();
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter queue capacity: ");
        int capacity = sc.nextInt();
        CustomQueue queue = new CustomQueue(capacity);

        while (true) {
            System.out.println("\n1.Enqueue  2.Dequeue  3.Peek  4.Display  5.Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value: ");
                    queue.enqueue(sc.nextInt());
                    break;
                case 2:
                    System.out.println("Dequeued: " + queue.dequeue());
                    break;
                case 3:
                    System.out.println("Front: " + queue.peek());
                    break;
                case 4:
                    queue.display();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        CustomQueue queue = new CustomQueue(5);
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.display();
        System.out.println("Peek: " + queue.peek());
        System.out.println("Dequeued: " + queue.dequeue());
        queue.display();

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
