package com.java.exception.handling;

import java.util.Scanner;

/**
 * ============================================================
 * 90. NESTED TRY-CATCH BLOCKS
 * ============================================================
 *
 * CONCEPT:
 * A try-catch block can be nested inside another try or catch block.
 *
 * Useful when:
 * - Different parts of code can throw different exceptions
 * - You want finer-grained exception handling
 * - Handling exceptions that may occur while handling another exception
 *
 * Structure:
 * try {
 *     // outer code
 *     try {
 *         // inner code
 *     } catch (InnerException e) { ... }
 * } catch (OuterException e) { ... }
 */
public class NestedTryCatch {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        try {
            System.out.println("Outer try starts");
            int[] arr = {1, 2, 3};

            try {
                System.out.println("Inner try starts");
                System.out.println(arr[5]);           // ArrayIndexOutOfBounds
                int x = 10 / 0;                       // won't reach here
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Inner catch: " + e.getMessage());
            }

            System.out.println("Back to outer try");
            int result = 100 / 0;                     // ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("Outer catch: " + e.getMessage());
        }

        System.out.println("Program continues normally");
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void nestedDemo(int[] arr, int index, int divisor) {
        try {
            System.out.println("Processing array...");

            try {
                System.out.println("Accessing index " + index);
                int value = arr[index];
                System.out.println("Value = " + value);

                try {
                    int result = value / divisor;
                    System.out.println("Division result = " + result);
                } catch (ArithmeticException e) {
                    System.out.println("Innermost catch (Arithmetic): " + e.getMessage());
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Middle catch (ArrayIndex): " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Outer catch (General): " + e.getMessage());
        }
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        Scanner sc = new Scanner(System.in);
        int[] data = {10, 20, 30, 40, 50};

        System.out.print("Enter index (0-4): ");
        int index = sc.nextInt();
        System.out.print("Enter divisor: ");
        int divisor = sc.nextInt();

        nestedDemo(data, index, divisor);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        int[] arr = {100, 200, 300};
        nestedDemo(arr, 1, 10);   // success
        nestedDemo(arr, 10, 5);   // index error
        nestedDemo(arr, 0, 0);    // division by zero

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
