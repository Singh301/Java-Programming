package com.java.file.IO.serialization;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * ============================================================
 * 93. READ FROM A FILE USING BufferedReader
 * ============================================================
 *
 * CONCEPT:
 * BufferedReader reads text from a character-input stream,
 * buffering characters for efficient reading.
 *
 * Common methods:
 * - readLine() → reads one line at a time
 * - read()     → reads a single character
 *
 * Always prefer try-with-resources for automatic closing.
 */
public class ReadWithBufferedReader {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() throws IOException {
        Path temp = Files.createTempFile("input", ".txt");
        Files.writeString(temp, "Apple\nBanana\nCherry\nDate\nElderberry");

        System.out.println("Reading file: " + temp);
        try (BufferedReader reader = new BufferedReader(new FileReader(temp.toFile()))) {
            String line;
            int lineNo = 1;
            while ((line = reader.readLine()) != null) {
                System.out.println(lineNo++ + ": " + line);
            }
        }

        Files.deleteIfExists(temp);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void readFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            System.out.println("--- File Content ---");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("--- End of File ---");
        }
    }

    public static String readFileAsString(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() throws IOException {
        Path temp = Files.createTempFile("readdemo", ".txt");
        Files.writeString(temp, "Java\nPython\nC++\nJavaScript\nGo");

        System.out.println("Sample file created at: " + temp);
        readFile(temp.toString());

        System.out.println("\nAs single string:\n" + readFileAsString(temp.toString()));

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
        Files.writeString(p, "Hello BufferedReader\nThis is line 2\nThis is line 3");
        readFile(p.toString());
        Files.deleteIfExists(p);

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
