package com.java.file.IO.serialization;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * ============================================================
 * 99. RANDOM ACCESS FILE I/O
 * ============================================================
 *
 * CONCEPT:
 * RandomAccessFile allows reading and writing at any position
 * in the file (not just sequentially).
 *
 * Useful for:
 * - Database-like file access
 * - Updating specific parts of a file
 * - Reading records of fixed length
 *
 * Key methods:
 * - seek(long pos)  → move file pointer
 * - readXXX() / writeXXX()
 * - getFilePointer()
 * - length()
 */
public class RandomAccessFileIO {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() throws IOException {
        Path file = Files.createTempFile("random", ".dat");

        try (RandomAccessFile raf = new RandomAccessFile(file.toFile(), "rw")) {
            // Write some data
            raf.writeUTF("Hello");
            raf.writeInt(100);
            raf.writeDouble(3.14159);

            // Go back to beginning
            raf.seek(0);

            // Read back
            System.out.println("String : " + raf.readUTF());
            System.out.println("Int    : " + raf.readInt());
            System.out.println("Double : " + raf.readDouble());

            // Move to position after the string and update the int
            raf.seek(0);
            raf.readUTF(); // skip string
            long intPos = raf.getFilePointer();
            raf.seek(intPos);
            raf.writeInt(999); // update

            raf.seek(intPos);
            System.out.println("Updated Int: " + raf.readInt());
        }

        Files.deleteIfExists(file);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void writeAndReadRandom(String filePath) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(filePath, "rw")) {
            raf.writeUTF("Java");
            raf.writeInt(2024);
            raf.writeBoolean(true);

            raf.seek(0);
            System.out.println("Read UTF     : " + raf.readUTF());
            System.out.println("Read Int     : " + raf.readInt());
            System.out.println("Read Boolean : " + raf.readBoolean());
            System.out.println("File length  : " + raf.length() + " bytes");
        }
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() throws IOException {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        Path file = Files.createTempFile("userdata", ".dat");

        System.out.print("Enter a text to store: ");
        String text = sc.nextLine();
        System.out.print("Enter an integer: ");
        int number = sc.nextInt();

        try (RandomAccessFile raf = new RandomAccessFile(file.toFile(), "rw")) {
            raf.writeUTF(text);
            raf.writeInt(number);

            raf.seek(0);
            System.out.println("\nStored data:");
            System.out.println("Text   : " + raf.readUTF());
            System.out.println("Number : " + raf.readInt());
        }

        Files.deleteIfExists(file);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) throws IOException {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        Path p = Files.createTempFile("demo", ".dat");
        writeAndReadRandom(p.toString());
        Files.deleteIfExists(p);

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
