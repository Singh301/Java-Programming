package com.java.multithreading.concurrency;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ============================================================
 * 78. COUNT CHARACTER OCCURRENCES IN A FILE USING MULTITHREADING
 * ============================================================
 *
 * CONCEPT:
 * Split the file content into chunks and let multiple threads
 * count characters in parallel, then combine results.
 *
 * For simplicity we count total characters and also frequency
 * of a specific character using AtomicInteger for thread safety.
 */
public class FileCharCountMultithread {

    // ============================================================
    // VERSION 1: WITHOUT METHOD (single file demo)
    // ============================================================
    public static void demoWithoutMethod() throws Exception {
        // Create a temporary sample file
        Path tempFile = Files.createTempFile("sample", ".txt");
        String content = "Hello World! This is a multithreading demo file. "
                + "Counting characters using parallel threads is fun.";
        Files.writeString(tempFile, content);

        System.out.println("File content length: " + content.length());

        AtomicInteger totalChars = new AtomicInteger(0);
        AtomicInteger letterACount = new AtomicInteger(0);

        // Simple two-thread approach
        int mid = content.length() / 2;

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < mid; i++) {
                totalChars.incrementAndGet();
                if (Character.toLowerCase(content.charAt(i)) == 'a') {
                    letterACount.incrementAndGet();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = mid; i < content.length(); i++) {
                totalChars.incrementAndGet();
                if (Character.toLowerCase(content.charAt(i)) == 'a') {
                    letterACount.incrementAndGet();
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Total characters : " + totalChars.get());
        System.out.println("Count of 'a'/'A' : " + letterACount.get());

        Files.deleteIfExists(tempFile);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void countCharsInFile(String filePath, int numThreads) throws Exception {
        String content = Files.readString(Path.of(filePath));
        int length = content.length();
        int chunkSize = (length + numThreads - 1) / numThreads;

        AtomicInteger total = new AtomicInteger(0);
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < numThreads; i++) {
            final int start = i * chunkSize;
            final int end = Math.min(start + chunkSize, length);

            executor.submit(() -> {
                for (int j = start; j < end; j++) {
                    total.incrementAndGet();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("Total characters counted by " + numThreads + " threads: " + total.get());
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() throws Exception {
        // Create sample file for demo
        Path tempFile = Files.createTempFile("userdemo", ".txt");
        String sample = "Java multithreading is powerful. "
                + "We can process large files efficiently using parallel threads. "
                + "This is a sample text for character counting demonstration.";
        Files.writeString(tempFile, sample);

        System.out.println("Created sample file: " + tempFile);
        System.out.println("Content length: " + sample.length());

        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Enter number of threads: ");
        int threads = sc.nextInt();

        countCharsInFile(tempFile.toString(), threads);

        Files.deleteIfExists(tempFile);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) throws Exception {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        Path temp = Files.createTempFile("demo", ".txt");
        Files.writeString(temp, "ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz 0123456789");
        countCharsInFile(temp.toString(), 4);
        Files.deleteIfExists(temp);

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
