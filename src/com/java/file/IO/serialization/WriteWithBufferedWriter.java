package com.java.file.IO.serialization;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * ============================================================
 * 92. WRITE TO A FILE USING BufferedWriter
 * ============================================================
 *
 * CONCEPT:
 * BufferedWriter buffers characters for efficient writing.
 * It is better than FileWriter for large data because it
 * reduces the number of actual I/O operations.
 *
 * Always close the writer (or use try-with-resources).
 */
public class WriteWithBufferedWriter {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() throws IOException {
        Path temp = Files.createTempFile("output", ".txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(temp.toFile()))) {
            writer.write("Hello, World!");
            writer.newLine();
            writer.write("This is written using BufferedWriter.");
            writer.newLine();
            writer.write("Java File I/O is powerful.");
        }

        System.out.println("Content written to: " + temp);
        System.out.println("File content:\n" + Files.readString(temp));

        Files.deleteIfExists(temp);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void writeToFile(String filePath, String... lines) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
        System.out.println("Successfully wrote " + lines.length + " lines to " + filePath);
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() throws IOException {
        Scanner sc = new Scanner(System.in);
        Path temp = Files.createTempFile("userwrite", ".txt");

        System.out.print("How many lines do you want to write? ");
        int n = sc.nextInt();
        sc.nextLine();

        String[] lines = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Line " + (i + 1) + ": ");
            lines[i] = sc.nextLine();
        }

        writeToFile(temp.toString(), lines);
        System.out.println("\nFile content:\n" + Files.readString(temp));

        Files.deleteIfExists(temp);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) throws IOException {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        Path p = Files.createTempFile("demo", ".txt");
        writeToFile(p.toString(), "First line", "Second line", "Third line");
        System.out.println(Files.readString(p));
        Files.deleteIfExists(p);

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
