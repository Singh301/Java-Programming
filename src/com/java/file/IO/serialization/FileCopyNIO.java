package com.java.file.IO.serialization;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * ============================================================
 * 96. FILE COPY USING JAVA NIO
 * ============================================================
 *
 * CONCEPT:
 * Java NIO (New I/O) provides efficient file operations.
 * Files.copy() is the simplest and most efficient way to copy files.
 *
 * Options:
 * - StandardCopyOption.REPLACE_EXISTING
 * - StandardCopyOption.COPY_ATTRIBUTES
 * - StandardCopyOption.ATOMIC_MOVE (for move)
 *
 * NIO is generally faster and cleaner than traditional streams for file copy.
 */
public class FileCopyNIO {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() throws IOException {
        Path source = Files.createTempFile("source", ".txt");
        Files.writeString(source, "This is the source file content.\nCopy me using NIO!");

        Path target = Path.of(source.getParent().toString(), "copied_file.txt");

        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

        System.out.println("Source : " + source);
        System.out.println("Target : " + target);
        System.out.println("Copied content:\n" + Files.readString(target));

        Files.deleteIfExists(source);
        Files.deleteIfExists(target);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void copyFile(String sourcePath, String targetPath) throws IOException {
        Path source = Path.of(sourcePath);
        Path target = Path.of(targetPath);

        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("File copied from " + source + " to " + target);
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() throws IOException {
        Path source = Files.createTempFile("mysource", ".txt");
        Files.writeString(source, "User demo content for NIO file copy.");

        Path target = Path.of(source.getParent().toString(), "my_copied_file.txt");

        System.out.println("Source file created: " + source);
        copyFile(source.toString(), target.toString());

        System.out.println("Content of copied file:\n" + Files.readString(target));

        Files.deleteIfExists(source);
        Files.deleteIfExists(target);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) throws IOException {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        Path src = Files.createTempFile("src", ".txt");
        Files.writeString(src, "Hello NIO Copy");
        Path tgt = Path.of(src.getParent().toString(), "tgt.txt");
        copyFile(src.toString(), tgt.toString());
        Files.deleteIfExists(src);
        Files.deleteIfExists(tgt);

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
