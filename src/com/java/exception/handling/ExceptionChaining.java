package com.java.exception.handling;

/**
 * ============================================================
 * 85. EXCEPTION CHAINING
 * ============================================================
 *
 * CONCEPT:
 * Exception chaining is when one exception is caused by another.
 * We preserve the original exception as the "cause".
 *
 * Methods:
 * - initCause(Throwable)
 * - Constructor: new Exception(message, cause)
 * - getCause() to retrieve the original exception
 *
 * Useful when you catch a low-level exception and want to
 * throw a higher-level business exception while keeping
 * the original stack trace.
 */
public class ExceptionChaining {

    // High-level custom exception
    static class DataProcessingException extends Exception {
        public DataProcessingException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        try {
            try {
                int result = 10 / 0;  // original exception
            } catch (ArithmeticException original) {
                // Wrap it in a higher-level exception
                DataProcessingException dpe = new DataProcessingException(
                        "Failed to process data due to calculation error", original);
                throw dpe;
            }
        } catch (DataProcessingException e) {
            System.out.println("Caught: " + e.getMessage());
            System.out.println("Original cause: " + e.getCause());
            System.out.println("\nFull stack trace:");
            e.printStackTrace();
        }
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void processData(String input) throws DataProcessingException {
        try {
            int number = Integer.parseInt(input);  // may throw NumberFormatException
            int result = 100 / number;             // may throw ArithmeticException
            System.out.println("Processed result: " + result);
        } catch (NumberFormatException | ArithmeticException e) {
            // Chain the original exception
            throw new DataProcessingException("Data processing failed for input: " + input, e);
        }
    }

    public static void safeProcess(String input) {
        try {
            processData(input);
        } catch (DataProcessingException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Root cause: " + e.getCause().getClass().getSimpleName()
                    + " - " + e.getCause().getMessage());
        }
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Enter a number (try 0 or non-number): ");
        String input = sc.nextLine();
        safeProcess(input);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        safeProcess("50");
        safeProcess("0");
        safeProcess("abc");

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
