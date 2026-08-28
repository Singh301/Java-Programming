package com.java.control.flow;

import java.util.Scanner;

/**
 * ============================================================
 * 13. PRINT STAR PATTERNS (Pyramid & Diamond)
 * ============================================================
 *
 * CONCEPT:
 * Patterns are printed using nested loops.
 * Outer loop controls rows, inner loop controls columns (spaces & stars).
 *
 * We will print two popular patterns:
 * 1. Pyramid (Right-angled / Centered)
 * 2. Diamond
 *
 * CONTROL FLOW:
 * - for loops (nested)
 * - if conditions for spaces and stars
 *
 * Example Pyramid (rows = 5):
 *     *
 *    ***
 *   *****
 *  *******
 * *********
 */
public class StarPatterns {

    // ============================================================
    // VERSION 1: WITHOUT METHOD - Simple Pyramid
    // ============================================================
    public static void printPyramidWithoutMethod() {
        int rows = 5;

        System.out.println("Pyramid Pattern:");
        for (int i = 1; i <= rows; i++) {
            // Print spaces
            for (int space = 1; space <= rows - i; space++) {
                System.out.print(" ");
            }
            // Print stars
            for (int star = 1; star <= (2 * i - 1); star++) {
                System.out.print("*");
            }
            System.out.println();   // move to next line
        }
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Prints a centered pyramid of given number of rows.
     */
    public static void printPyramid(int rows) {
        System.out.println("Pyramid (" + rows + " rows):");
        for (int i = 1; i <= rows; i++) {
            // spaces
            for (int j = 1; j <= rows - i; j++) {
                System.out.print(" ");
            }
            // stars
            for (int k = 1; k <= 2 * i - 1; k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /**
     * Prints a diamond pattern.
     */
    public static void printDiamond(int rows) {
        System.out.println("Diamond (" + rows + " rows):");

        // Upper half (including middle)
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= rows - i; j++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= 2 * i - 1; k++) {
                System.out.print("*");
            }
            System.out.println();
        }

        // Lower half
        for (int i = rows - 1; i >= 1; i--) {
            for (int j = 1; j <= rows - i; j++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= 2 * i - 1; k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void printPatternsWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of rows: ");
        int rows = sc.nextInt();

        System.out.println();
        printPyramid(rows);
        System.out.println();
        printDiamond(rows);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        printPyramidWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        printPyramid(4);
        System.out.println();
        printDiamond(4);

        System.out.println("\n===== VERSION 3: With User Input =====");
        printPatternsWithUserInput();
    }
}
