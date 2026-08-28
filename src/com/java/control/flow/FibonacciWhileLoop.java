package com.java.control.flow;

import java.util.Scanner;

/**
 * ============================================================
 * 14. FIBONACCI SERIES USING WHILE LOOP
 * ============================================================
 *
 * CONCEPT:
 * Fibonacci series: each number is the sum of the two previous ones.
 * 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, ...
 *
 * WHY WHILE LOOP?
 * Sometimes we don't know how many terms we need in advance,
 * or we want to print terms until a certain value is reached.
 * while loop gives us more flexible control.
 *
 * We keep generating terms until we have printed 'n' terms.
 */
public class FibonacciWhileLoop {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void fibonacciWithoutMethod() {
        int n = 10;          // number of terms
        int first = 0;
        int second = 1;
        int count = 1;

        System.out.print("Fibonacci Series (while loop): ");

        while (count <= n) {
            System.out.print(first + " ");

            int next = first + second;
            first = second;
            second = next;

            count++;         // increase the counter
        }
        System.out.println();
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Prints first 'n' Fibonacci numbers using while loop.
     */
    public static void printFibonacci(int n) {
        if (n <= 0) {
            System.out.println("Please enter a positive number");
            return;
        }

        int first = 0;
        int second = 1;
        int count = 1;

        System.out.print("Fibonacci Series: ");

        while (count <= n) {
            System.out.print(first + " ");
            int next = first + second;
            first = second;
            second = next;
            count++;
        }
        System.out.println();
    }

    /**
     * Prints Fibonacci numbers until the term exceeds a limit.
     * (Another common use of while loop)
     */
    public static void printFibonacciUntil(int limit) {
        int first = 0;
        int second = 1;

        System.out.print("Fibonacci numbers less than " + limit + ": ");

        while (first < limit) {
            System.out.print(first + " ");
            int next = first + second;
            first = second;
            second = next;
        }
        System.out.println();
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void fibonacciWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter how many Fibonacci terms you want: ");
        int n = sc.nextInt();

        printFibonacci(n);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        fibonacciWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        printFibonacci(12);
        printFibonacciUntil(100);

        System.out.println("\n===== VERSION 3: With User Input =====");
        fibonacciWithUserInput();
    }
}
