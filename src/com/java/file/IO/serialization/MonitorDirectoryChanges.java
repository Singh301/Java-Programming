package com.java.file.IO.serialization;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**
 * ============================================================
 * 98. MONITOR CHANGES IN A DIRECTORY
 * ============================================================
 *
 * CONCEPT:
 * WatchService (Java NIO.2) allows you to monitor a directory
 * for changes such as file creation, modification, or deletion.
 *
 * Events:
 * - ENTRY_CREATE
 * - ENTRY_DELETE
 * - ENTRY_MODIFY
 *
 * Useful for log monitoring, auto-reload configs, etc.
 *
 * Note: This program runs for a limited time for demo purposes.
 */
public class MonitorDirectoryChanges {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() throws IOException, InterruptedException {
        Path dir = Files.createTempDirectory("watchdemo");
        System.out.println("Monitoring directory: " + dir);

        WatchService watcher = FileSystems.getDefault().newWatchService();
        dir.register(watcher,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        // Create a file to trigger event
        Path newFile = dir.resolve("test.txt");
        Files.writeString(newFile, "Hello WatchService");

        WatchKey key = watcher.poll(2, java.util.concurrent.TimeUnit.SECONDS);
        if (key != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                System.out.println("Event: " + event.kind() + " → " + event.context());
            }
            key.reset();
        }

        Files.deleteIfExists(newFile);
        Files.deleteIfExists(dir);
        watcher.close();
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void monitorDirectory(String dirPath, int seconds) throws IOException, InterruptedException {
        Path dir = Path.of(dirPath);
        if (!Files.isDirectory(dir)) {
            System.out.println("Not a valid directory: " + dirPath);
            return;
        }

        WatchService watcher = FileSystems.getDefault().newWatchService();
        dir.register(watcher,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        System.out.println("Watching " + dir + " for " + seconds + " seconds...");
        System.out.println("(Create/modify/delete files in this directory to see events)");

        long endTime = System.currentTimeMillis() + seconds * 1000L;

        while (System.currentTimeMillis() < endTime) {
            WatchKey key = watcher.poll(1, java.util.concurrent.TimeUnit.SECONDS);
            if (key != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    System.out.println("[" + event.kind() + "] " + event.context());
                }
                key.reset();
            }
        }

        watcher.close();
        System.out.println("Monitoring stopped.");
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() throws IOException, InterruptedException {
        Path dir = Files.createTempDirectory("usermonitor");
        System.out.println("Created temp directory for demo: " + dir);

        // Auto create a file after a short delay in another thread
        new Thread(() -> {
            try {
                Thread.sleep(1500);
                Path f = dir.resolve("auto_created.txt");
                Files.writeString(f, "Auto generated file");
                Thread.sleep(1000);
                Files.deleteIfExists(f);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        monitorDirectory(dir.toString(), 5);

        // Cleanup
        Files.walk(dir)
                .sorted(java.util.Comparator.reverseOrder())
                .forEach(p -> {
                    try { Files.deleteIfExists(p); } catch (IOException ignored) {}
                });
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) throws Exception {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        Path tempDir = Files.createTempDirectory("monitor");
        monitorDirectory(tempDir.toString(), 2);
        Files.deleteIfExists(tempDir);

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
