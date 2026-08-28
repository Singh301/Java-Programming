package com.java.file.IO.serialization;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * ============================================================
 * 97. LIST ALL FILES IN A DIRECTORY
 * ============================================================
 *
 * CONCEPT:
 * List files and subdirectories present in a given directory.
 *
 * Approaches:
 * 1. java.io.File.listFiles()
 * 2. java.nio.file.Files.newDirectoryStream() / list()
 *
 * We can filter by extension, size, etc.
 */
public class ListFilesInDirectory {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        // List files in current working directory
        File dir = new File(".");
        File[] files = dir.listFiles();

        if (files != null) {
            System.out.println("Files in current directory:");
            for (File f : files) {
                String type = f.isDirectory() ? "[DIR] " : "[FILE]";
                System.out.println(type + " " + f.getName());
            }
        }
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void listFiles(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("Invalid directory: " + dirPath);
            return;
        }

        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("Directory is empty.");
            return;
        }

        System.out.println("Contents of: " + dir.getAbsolutePath());
        for (File f : files) {
            System.out.printf("%-6s %s (%d bytes)%n",
                    f.isDirectory() ? "[DIR]" : "[FILE]",
                    f.getName(),
                    f.length());
        }
    }

    // NIO style
    public static void listFilesNIO(String dirPath) throws IOException {
        Path dir = Paths.get(dirPath);
        System.out.println("NIO listing of: " + dir.toAbsolutePath());
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path entry : stream) {
                System.out.println((Files.isDirectory(entry) ? "[DIR] " : "[FILE]") + " " + entry.getFileName());
            }
        }
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() throws IOException {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Enter directory path (or . for current): ");
        String path = sc.nextLine().trim();
        if (path.isEmpty()) path = ".";

        listFiles(path);
        System.out.println();
        listFilesNIO(path);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) throws IOException {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        listFiles(".");

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
