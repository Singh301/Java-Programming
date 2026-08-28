package com.java.basic;

import java.util.Scanner;

/**
 * ============================================================
 * 3. FIBONACCI SERIES
 * ============================================================
 *
 * WHAT IS FIBONACCI SERIES?
 * -------------------------
 * Fibonacci series is a sequence of numbers where each number
 * is the sum of the two preceding ones.
 *
 * It usually starts with 0 and 1:
 * 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
 *
 * Mathematical formula:
 * F(0) = 0
 * F(1) = 1
 * F(n) = F(n-1) + F(n-2)   for n > 1
 *
 * This series appears in nature (flower petals, pinecones,
 * shells) and is widely used in computer science algorithms.
 */
public class FibonacciSeries {

    // ============================================================
    // VERSION 1: WITHOUT METHOD (Print first n terms)
    // ============================================================
    public static void printFibonacciWithoutMethod() {
        int n = 10;   // how many terms we want

        int first = 0;
        int second = 1;

        System.out.print("Fibonacci Series (first " + n + " terms): ");

        for (int i = 1; i <= n; i++) {
            System.out.print(first + " ");

            // Calculate the next term
            int next = first + second;

            // Move the values forward
            first = second;
            second = next;
        }
        System.out.println();
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Prints the Fibonacci series up to 'count' terms.
     */
    public static void printFibonacci(int count) {
        if (count <= 0) {
            System.out.println("Please enter a positive number");
            return;
        }

        int first = 0;
        int second = 1;

        System.out.print("Fibonacci Series: ");

        for (int i = 1; i <= count; i++) {
            System.out.print(first + " ");
            int next = first + second;
            first = second;
            second = next;
        }
        System.out.println();
    }

    /**
     * Returns the nth Fibonacci number (0-based index).
     * Example: fibonacci(0)=0, fibonacci(1)=1, fibonacci(5)=5
     */
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void fibonacciWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter how many Fibonacci terms you want: ");
        int count = sc.nextInt();

        printFibonacci(count);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        printFibonacciWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        printFibonacci(12);
        System.out.println("5th Fibonacci number (0-based) = " + fibonacci(5));

        System.out.println("\n===== VERSION 3: With User Input =====");
        fibonacciWithUserInput();
    }
}
