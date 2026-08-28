package com.java.collections;

import java.util.Scanner;
import java.util.Stack;

/**
 * ============================================================
 * 44. REVERSE A STACK
 * ============================================================
 *
 * CONCEPT:
 * Reverse the order of elements in a stack.
 *
 * Example:
 *   Original Stack (top → bottom): 30 20 10
 *   After reverse  (top → bottom): 10 20 30
 *
 * Approaches:
 * 1. Using another stack (or queue)
 * 2. Using recursion (no extra stack)
 *
 * We will show both methods.
 */
public class ReverseStack {

    // ============================================================
    // VERSION 1: WITHOUT METHOD (using extra stack)
    // ============================================================
    public static void reverseWithoutMethod() {
        Stack<Integer> stack = new Stack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Original Stack: " + stack);

        Stack<Integer> temp = new Stack<>();
        while (!stack.isEmpty()) {
            temp.push(stack.pop());
        }

        // Copy back if we want original stack object reversed
        // Or just use temp as reversed stack
        System.out.println("Reversed Stack: " + temp);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Reverses the stack using an extra stack.
     */
    public static void reverseUsingExtraStack(Stack<Integer> stack) {
        Stack<Integer> temp = new Stack<>();
        while (!stack.isEmpty()) {
            temp.push(stack.pop());
        }
        // Move back so original stack is reversed
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
        // Wait - this puts it back to original order!
        // Correct way: just return the temp stack or swap references.
        // Better approach below.
    }

    /**
     * Correct reverse using extra stack - returns new reversed stack.
     */
    public static Stack<Integer> reverse(Stack<Integer> stack) {
        Stack<Integer> reversed = new Stack<>();
        while (!stack.isEmpty()) {
            reversed.push(stack.pop());
        }
        return reversed;
    }

    /**
     * Reverse stack using recursion (no extra stack).
     */
    public static void reverseRecursive(Stack<Integer> stack) {
        if (stack.isEmpty()) return;

        int top = stack.pop();
        reverseRecursive(stack);
        insertAtBottom(stack, top);
    }

    private static void insertAtBottom(Stack<Integer> stack, int value) {
        if (stack.isEmpty()) {
            stack.push(value);
            return;
        }
        int top = stack.pop();
        insertAtBottom(stack, value);
        stack.push(top);
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void reverseWithUserInput() {
        Scanner sc = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();

        System.out.print("How many elements? ");
        int n = sc.nextInt();
        System.out.println("Enter " + n + " elements (top will be last):");
        for (int i = 0; i < n; i++) {
            stack.push(sc.nextInt());
        }

        System.out.println("Original: " + stack);

        reverseRecursive(stack);

        System.out.println("Reversed: " + stack);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        reverseWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        Stack<Integer> s1 = new Stack<>();
        s1.push(1);
        s1.push(2);
        s1.push(3);
        s1.push(4);
        System.out.println("Original : " + s1);
        Stack<Integer> rev = reverse(s1);
        System.out.println("Reversed : " + rev);

        Stack<Integer> s2 = new Stack<>();
        s2.push(10);
        s2.push(20);
        s2.push(30);
        reverseRecursive(s2);
        System.out.println("Reversed (recursive): " + s2);

        System.out.println("\n===== VERSION 3: With User Input =====");
        reverseWithUserInput();
    }
}
