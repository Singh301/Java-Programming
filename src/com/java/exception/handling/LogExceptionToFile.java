package com.java.exception.handling;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * ============================================================
 * 88. LOG EXCEPTIONS INTO A FILE
 * ============================================================
 *
 * CONCEPT:
 * In real applications we log exceptions to a file instead of
 * (or in addition to) printing them on console.
 *
 * This helps in debugging production issues later.
 *
 * Simple approach: Write exception details + stack trace to a log file.
 */
public class LogExceptionToFile {

    private static final String LOG_FILE = "exception_log.txt";

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
                writer.println("==== Exception Log ====");
                writer.println("Time: " + LocalDateTime.now());
                writer.println("Exception: " + e.getClass().getName());
                writer.println("Message: " + e.getMessage());
                e.printStackTrace(writer);
                writer.println();
                System.out.println("Exception logged to " + LOG_FILE);
            } catch (IOException ioEx) {
                System.out.println("Failed to write log: " + ioEx.getMessage());
            }
        }
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void logException(Exception e) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            writer.println("--------------------------------------------------");
            writer.println("Timestamp : " + timestamp);
            writer.println("Exception : " + e.getClass().getName());
            writer.println("Message   : " + e.getMessage());
            writer.println("Stack Trace:");
            e.printStackTrace(writer);
            writer.println("--------------------------------------------------");
            writer.println();

            System.out.println("Exception successfully logged to " + LOG_FILE);
        } catch (IOException ioEx) {
            System.err.println("Could not write to log file: " + ioEx.getMessage());
        }
    }

    public static void riskyOperation(String input) {
        try {
            int num = Integer.parseInt(input);
            System.out.println("100 / " + num + " = " + (100 / num));
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            logException(e);
        }
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number (try 0 or non-number to generate exception): ");
        String input = sc.nextLine();
        riskyOperation(input);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        riskyOperation("50");
        riskyOperation("0");
        riskyOperation("abc");

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
