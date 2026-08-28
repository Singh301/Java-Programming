package com.java.file.IO.serialization;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * ============================================================
 * 100. CREATE A LOG FILE WITH TIMESTAMPS
 * ============================================================
 *
 * CONCEPT:
 * Logging is essential for debugging and auditing.
 * We write messages with timestamps to a log file.
 *
 * Simple custom logger that appends timestamped messages.
 *
 * Format example:
 * [2026-08-29 00:25:10] INFO  - Application started
 * [2026-08-29 00:25:12] ERROR - Something went wrong
 */
public class LogFileWithTimestamp {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() throws IOException {
        Path logFile = Files.createTempFile("app", ".log");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile.toFile(), true))) {
            String time = LocalDateTime.now().format(FORMATTER);
            writer.write("[" + time + "] INFO  - Application started");
            writer.newLine();
            writer.write("[" + time + "] DEBUG - Loading configuration");
            writer.newLine();
            writer.write("[" + time + "] ERROR - Sample error message");
            writer.newLine();
        }

        System.out.println("Log file created: " + logFile);
        System.out.println("Content:\n" + Files.readString(logFile));

        Files.deleteIfExists(logFile);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void log(String filePath, String level, String message) throws IOException {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        String logLine = "[" + timestamp + "] " + String.format("%-5s", level) + " - " + message;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(logLine);
            writer.newLine();
        }
    }

    public static void demonstrateLogger(String logFilePath) throws IOException {
        log(logFilePath, "INFO", "Application started");
        log(logFilePath, "DEBUG", "Connecting to database");
        log(logFilePath, "WARN", "Low disk space");
        log(logFilePath, "ERROR", "Failed to connect to server");
        log(logFilePath, "INFO", "Application shutting down");

        System.out.println("Log written to: " + logFilePath);
        System.out.println("\n--- Log Content ---");
        System.out.println(Files.readString(Path.of(logFilePath)));
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() throws IOException {
        Scanner sc = new Scanner(System.in);
        Path logFile = Files.createTempFile("userlog", ".log");

        System.out.println("Simple Logger (type 'exit' to stop)");
        System.out.println("Log file: " + logFile);

        while (true) {
            System.out.print("Enter log level (INFO/DEBUG/WARN/ERROR) or exit: ");
            String level = sc.nextLine().trim().toUpperCase();
            if (level.equals("EXIT")) break;

            System.out.print("Enter message: ");
            String message = sc.nextLine();

            log(logFile.toString(), level, message);
            System.out.println("Logged successfully.");
        }

        System.out.println("\nFinal log content:");
        System.out.println(Files.readString(logFile));

        Files.deleteIfExists(logFile);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) throws IOException {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        Path p = Files.createTempFile("demo", ".log");
        demonstrateLogger(p.toString());
        Files.deleteIfExists(p);

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
