package com.java.multithreading.concurrency;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ============================================================
 * 79. READ-WRITE LOCKS
 * ============================================================
 *
 * CONCEPT:
 * ReadWriteLock allows:
 * - Multiple threads to read simultaneously (shared lock)
 * - Only one thread to write at a time (exclusive lock)
 * - No read while writing and vice versa
 *
 * Useful when reads are much more frequent than writes.
 *
 * Interface: ReadWriteLock
 * Implementation: ReentrantReadWriteLock
 *   - readLock()
 *   - writeLock()
 */
public class ReadWriteLockDemo {

    static class SharedData {
        private String data = "Initial Data";
        private final ReadWriteLock lock = new ReentrantReadWriteLock();

        public String read() {
            lock.readLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + " reading: " + data);
                try { Thread.sleep(200); } catch (InterruptedException e) {}
                return data;
            } finally {
                lock.readLock().unlock();
            }
        }

        public void write(String newData) {
            lock.writeLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + " writing: " + newData);
                try { Thread.sleep(300); } catch (InterruptedException e) {}
                this.data = newData;
            } finally {
                lock.writeLock().unlock();
            }
        }
    }

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() throws InterruptedException {
        SharedData shared = new SharedData();

        // Multiple readers
        Thread r1 = new Thread(() -> shared.read(), "Reader-1");
        Thread r2 = new Thread(() -> shared.read(), "Reader-2");
        Thread r3 = new Thread(() -> shared.read(), "Reader-3");

        // One writer
        Thread w1 = new Thread(() -> shared.write("Updated by Writer-1"), "Writer-1");

        r1.start();
        r2.start();
        w1.start();
        r3.start();

        r1.join();
        r2.join();
        w1.join();
        r3.join();
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void runReadWriteDemo() throws InterruptedException {
        SharedData shared = new SharedData();

        Runnable reader = () -> {
            for (int i = 0; i < 3; i++) {
                shared.read();
            }
        };

        Runnable writer = () -> {
            shared.write("New Value from " + Thread.currentThread().getName());
        };

        Thread t1 = new Thread(reader, "Reader-A");
        Thread t2 = new Thread(reader, "Reader-B");
        Thread t3 = new Thread(writer, "Writer-X");

        t1.start();
        t2.start();
        Thread.sleep(100);
        t3.start();

        t1.join();
        t2.join();
        t3.join();
        System.out.println("Final data: " + shared.read());
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() throws InterruptedException {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        SharedData shared = new SharedData();

        System.out.print("Enter new data to write: ");
        String newData = sc.nextLine();

        Thread writer = new Thread(() -> shared.write(newData), "User-Writer");
        Thread reader = new Thread(() -> shared.read(), "User-Reader");

        writer.start();
        writer.join();
        reader.start();
        reader.join();
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) throws InterruptedException {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        runReadWriteDemo();

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
