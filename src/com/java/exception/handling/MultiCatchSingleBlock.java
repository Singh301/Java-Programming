package com.java.exception.handling;

import java.util.Scanner;

/**
 * ============================================================
 * 86. CATCH MULTIPLE EXCEPTIONS IN A SINGLE CATCH BLOCK
 * ============================================================
 *
 * CONCEPT:
 * From Java 7 onwards, we can catch multiple exception types
 * in a single catch block using the pipe (|) operator.
 *
 * Syntax:
 * catch (IOException | SQLException | ArithmeticException e) { ... }
 *
 * Benefits:
 * - Avoids code duplication
 * - Cleaner when handling different exceptions the same way
 *
 * Note: The exceptions must not be in a parent-child relationship.
 */
public class MultiCatchSingleBlock {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        try {
            String str = null;
            System.out.println(str.length());          // NullPointerException
            int num = Integer.parseInt("abc");         // NumberFormatException
            int result = 10 / 0;                       // ArithmeticException
        } catch (NullPointerException | NumberFormatException | ArithmeticException e) {
            System.out.println("Caught one of the expected exceptions: "
                    + e.getClass().getSimpleName());
            System.out.println("Message: " + e.getMessage());
        }
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void process(String str, String number, int divisor) {
        try {
            System.out.println("String length: " + str.length());
            int n = Integer.parseInt(number);
            int result = n / divisor;
            System.out.println("Result: " + result);
        } catch (NullPointerException | NumberFormatException | ArithmeticException e) {
            System.out.println("Handled by multi-catch: " + e.getClass().getSimpleName()
                    + " → " + e.getMessage());
        }
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string (or leave empty for null demo): ");
        String str = sc.nextLine();
        if (str.isEmpty()) str = null;

        System.out.print("Enter a number as text: ");
        String number = sc.nextLine();

        System.out.print("Enter divisor: ");
        int divisor = sc.nextInt();

        process(str, number, divisor);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        process("Hello", "100", 5);     // success
        process(null, "100", 5);        // NullPointer
        process("Hello", "abc", 5);     // NumberFormat
        process("Hello", "100", 0);     // Arithmetic

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
