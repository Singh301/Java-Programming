package com.java.file.IO.serialization;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * ============================================================
 * 91. READ A FILE AND COUNT THE NUMBER OF LINES
 * ============================================================
 *
 * CONCEPT:
 * Count how many lines exist in a text file.
 *
 * Approaches:
 * 1. BufferedReader + readLine() loop
 * 2. Files.lines() (Java 8+) – more concise
 *
 * We create a sample file for demonstration.
 */
public class CountLinesInFile {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() throws IOException {
        // Create a temporary sample file
        Path temp = Files.createTempFile("sample", ".txt");
        Files.writeString(temp, "Line 1\nLine 2\nLine 3\nLine 4\nLine 5");

        int lineCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(temp.toFile()))) {
            while (reader.readLine() != null) {
                lineCount++;
            }
        }

        System.out.println("File: " + temp);
        System.out.println("Number of lines: " + lineCount);

        Files.deleteIfExists(temp);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static long countLines(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            long count = 0;
            while (reader.readLine() != null) {
                count++;
            }
            return count;
        }
    }

    // Java 8+ style
    public static long countLinesNIO(String filePath) throws IOException {
        return Files.lines(Path.of(filePath)).count();
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() throws IOException {
        // Create sample file
        Path temp = Files.createTempFile("userfile", ".txt");
        Files.writeString(temp, "Hello\nWorld\nJava\nFile\nIO\nDemo");

        System.out.println("Sample file created: " + temp);
        System.out.println("Lines (BufferedReader): " + countLines(temp.toString()));
        System.out.println("Lines (NIO)           : " + countLinesNIO(temp.toString()));

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
        Files.writeString(p, "One\nTwo\nThree\nFour");
        System.out.println("Lines = " + countLines(p.toString()));
        Files.deleteIfExists(p);

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
