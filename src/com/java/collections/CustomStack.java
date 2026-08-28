package com.java.collections;

import java.util.Scanner;

/**
 * ============================================================
 * 42. CUSTOM STACK
 * ============================================================
 *
 * CONCEPT:
 * Stack follows LIFO (Last In First Out) principle.
 * The last element added is the first one to be removed.
 *
 * Real-life examples:
 * - Stack of plates
 * - Browser back button
 * - Undo functionality
 *
 * Main operations:
 * - push()  : add element on top
 * - pop()   : remove element from top
 * - peek()  : look at top element without removing
 * - isEmpty()
 * - size()
 *
 * We implement it using an array.
 */
public class CustomStack {

    private int[] arr;
    private int top;
    private int capacity;

    public CustomStack(int size) {
        arr = new int[size];
        capacity = size;
        top = -1;
    }

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        int[] stack = new int[5];
        int top = -1;

        // push 10
        stack[++top] = 10;
        // push 20
        stack[++top] = 20;
        // push 30
        stack[++top] = 30;

        System.out.println("Top element: " + stack[top]);

        // pop
        System.out.println("Popped: " + stack[top--]);
        System.out.println("New top: " + stack[top]);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public void push(int value) {
        if (top == capacity - 1) {
            System.out.println("Stack Overflow! Cannot push " + value);
            return;
        }
        arr[++top] = value;
        System.out.println("Pushed: " + value);
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow! Cannot pop");
            return -1;
        }
        return arr[top--];
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return arr[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        System.out.print("Stack (top to bottom): ");
        for (int i = top; i >= 0; i--) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter stack capacity: ");
        int capacity = sc.nextInt();
        CustomStack stack = new CustomStack(capacity);

        while (true) {
            System.out.println("\n1.Push  2.Pop  3.Peek  4.Display  5.Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value: ");
                    stack.push(sc.nextInt());
                    break;
                case 2:
                    System.out.println("Popped: " + stack.pop());
                    break;
                case 3:
                    System.out.println("Top: " + stack.peek());
                    break;
                case 4:
                    stack.display();
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
        CustomStack stack = new CustomStack(5);
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.display();
        System.out.println("Peek: " + stack.peek());
        System.out.println("Popped: " + stack.pop());
        stack.display();

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
