package com.java.basic;

import java.util.Scanner;

/**
 * ============================================================
 * 15. SWAP TWO NUMBERS WITHOUT USING A THIRD VARIABLE
 * ============================================================
 *
 * TASK:
 * Swap the values of two variables without using a temporary
 * (third) variable.
 *
 * Normal way (with temp):
 *   temp = a;
 *   a = b;
 *   b = temp;
 *
 * Without third variable (using arithmetic):
 *   a = a + b;
 *   b = a - b;   // now b becomes original a
 *   a = a - b;   // now a becomes original b
 *
 * Another popular way uses XOR (bitwise):
 *   a = a ^ b;
 *   b = a ^ b;
 *   a = a ^ b;
 *
 * Note: Arithmetic method can cause overflow for very large numbers.
 * XOR method is safer in that regard.
 */
public class SwapTwoNumbers {

    // ============================================================
    // VERSION 1: WITHOUT METHOD (Arithmetic)
    // ============================================================
    public static void swapWithoutMethod() {
        int a = 10;
        int b = 20;

        System.out.println("Before Swap → a = " + a + ", b = " + b);

        // Swapping without third variable
        a = a + b;   // a becomes 30
        b = a - b;   // b becomes 30 - 20 = 10
        a = a - b;   // a becomes 30 - 10 = 20

        System.out.println("After Swap  → a = " + a + ", b = " + b);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Swaps two numbers using arithmetic and prints the result.
     * Note: Java is pass-by-value, so we cannot change original
     * variables of the caller. We just demonstrate the logic.
     */
    public static void swapArithmetic(int a, int b) {
        System.out.println("Before → a = " + a + ", b = " + b);

        a = a + b;
        b = a - b;
        a = a - b;

        System.out.println("After  → a = " + a + ", b = " + b);
    }

    /**
     * Swaps using XOR (bitwise exclusive OR).
     * This is a classic interview trick.
     */
    public static void swapXOR(int a, int b) {
        System.out.println("Before → a = " + a + ", b = " + b);

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println("After  → a = " + a + ", b = " + b);
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void swapWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number (a): ");
        int a = sc.nextInt();
        System.out.print("Enter second number (b): ");
        int b = sc.nextInt();

        System.out.println("\n--- Using Arithmetic ---");
        swapArithmetic(a, b);

        System.out.println("\n--- Using XOR ---");
        swapXOR(a, b);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        swapWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        swapArithmetic(5, 9);
        System.out.println();
        swapXOR(100, 200);

        System.out.println("\n===== VERSION 3: With User Input =====");
        swapWithUserInput();
    }
}
