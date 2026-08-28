package com.java.exception.handling;

import java.util.Scanner;

/**
 * ============================================================
 * 83. MULTIPLE CATCH BLOCKS
 * ============================================================
 *
 * CONCEPT:
 * A single try block can have multiple catch blocks to handle
 * different types of exceptions separately.
 *
 * Rules:
 * - More specific exceptions must come before general ones
 * - Only one catch block executes (the first matching one)
 * - Order matters: ArithmeticException before Exception
 *
 * Example scenarios:
 * - Division by zero
 * - Array index out of bounds
 * - Number format issues
 */
public class MultipleCatchBlocks {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        try {
            int[] arr = {10, 20, 30};
            System.out.println("Element: " + arr[5]);          // ArrayIndexOutOfBounds
            int result = 100 / 0;                              // ArithmeticException
            System.out.println(result);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array Error: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Math Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("General Error: " + e.getMessage());
        }
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void process(int[] arr, int index, int divisor) {
        try {
            System.out.println("Value at index " + index + " = " + arr[index]);
            int result = arr[index] / divisor;
            System.out.println("Result after division: " + result);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught ArrayIndexOutOfBoundsException: Invalid index " + index);
        } catch (ArithmeticException e) {
            System.out.println("Caught ArithmeticException: Division by zero");
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException: Array is null");
        } catch (Exception e) {
            System.out.println("Caught general Exception: " + e.getClass().getSimpleName());
        }
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        Scanner sc = new Scanner(System.in);
        int[] arr = {10, 20, 30, 40, 50};

        System.out.print("Enter index (0-4): ");
        int index = sc.nextInt();
        System.out.print("Enter divisor: ");
        int divisor = sc.nextInt();

        process(arr, index, divisor);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        int[] data = {100, 200, 300};
        process(data, 1, 10);    // success
        process(data, 10, 5);    // index error
        process(data, 0, 0);     // division by zero

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
