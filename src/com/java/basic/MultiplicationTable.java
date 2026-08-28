package com.java.basic;

import java.util.Scanner;

/**
 * ============================================================
 * 7. MULTIPLICATION TABLE
 * ============================================================
 *
 * WHAT IS A MULTIPLICATION TABLE?
 * -------------------------------
 * A multiplication table shows the product of a number with
 * consecutive integers (usually 1 to 10).
 *
 * Example for 5:
 *   5 × 1 = 5
 *   5 × 2 = 10
 *   5 × 3 = 15
 *   ...
 *   5 × 10 = 50
 *
 * This is one of the first programs every beginner writes
 * because it teaches loops very clearly.
 */
public class MultiplicationTable {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void printTableWithoutMethod() {
        int number = 7;

        System.out.println("Multiplication Table of " + number + ":");
        for (int i = 1; i <= 10; i++) {
            System.out.println(number + " × " + i + " = " + (number * i));
        }
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Prints the multiplication table of the given number
     * from 1 to the given limit (default usually 10).
     */
    public static void printTable(int number, int limit) {
        System.out.println("Multiplication Table of " + number + " (upto " + limit + "):");
        for (int i = 1; i <= limit; i++) {
            System.out.println(number + " × " + i + " = " + (number * i));
        }
    }

    // Overloaded method with default limit 10
    public static void printTable(int number) {
        printTable(number, 10);
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void printTableWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number for multiplication table: ");
        int number = sc.nextInt();

        System.out.print("Enter up to which multiple (e.g. 10): ");
        int limit = sc.nextInt();

        printTable(number, limit);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        printTableWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        printTable(5);
        System.out.println();
        printTable(12, 5);   // only up to 5

        System.out.println("\n===== VERSION 3: With User Input =====");
        printTableWithUserInput();
    }
}
