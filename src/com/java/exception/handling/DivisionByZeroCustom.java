package com.java.exception.handling;

import java.util.Scanner;

/**
 * ============================================================
 * 81. HANDLE DIVISION BY ZERO USING CUSTOM EXCEPTION
 * ============================================================
 *
 * CONCEPT:
 * Instead of letting ArithmeticException occur, we create our own
 * meaningful exception and throw it when divisor is zero.
 *
 * This makes error messages clearer and allows custom handling logic.
 */
public class DivisionByZeroCustom {

    // Custom Exception
    static class DivisionByZeroException extends Exception {
        public DivisionByZeroException(String message) {
            super(message);
        }
    }

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        int numerator = 100;
        int denominator = 0;

        try {
            if (denominator == 0) {
                throw new DivisionByZeroException("Cannot divide by zero!");
            }
            int result = numerator / denominator;
            System.out.println("Result: " + result);
        } catch (DivisionByZeroException e) {
            System.out.println("Custom Exception Caught: " + e.getMessage());
        }
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static int divide(int a, int b) throws DivisionByZeroException {
        if (b == 0) {
            throw new DivisionByZeroException("Division by zero is not allowed. a=" + a + ", b=" + b);
        }
        return a / b;
    }

    public static void safeDivide(int a, int b) {
        try {
            int result = divide(a, b);
            System.out.println(a + " / " + b + " = " + result);
        } catch (DivisionByZeroException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter numerator: ");
        int a = sc.nextInt();
        System.out.print("Enter denominator: ");
        int b = sc.nextInt();

        safeDivide(a, b);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        safeDivide(50, 5);
        safeDivide(50, 0);

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
