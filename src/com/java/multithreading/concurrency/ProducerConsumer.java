package com.java.multithreading.concurrency;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ============================================================
 * 73. PRODUCER-CONSUMER PROBLEM USING wait()/notify()
 * ============================================================
 *
 * CONCEPT:
 * Producer produces data and puts it into a shared buffer.
 * Consumer takes data from the buffer.
 *
 * Problem: Buffer has limited size.
 * - Producer must wait if buffer is full
 * - Consumer must wait if buffer is empty
 *
 * wait()  → releases the lock and waits
 * notify() → wakes up one waiting thread
 *
 * Both must be called inside synchronized block.
 */
public class ProducerConsumer {

    static class SharedBuffer {
        private final Queue<Integer> queue = new LinkedList<>();
        private final int capacity;

        public SharedBuffer(int capacity) {
            this.capacity = capacity;
        }

        public synchronized void produce(int value) throws InterruptedException {
            while (queue.size() == capacity) {
                System.out.println("Buffer full. Producer waiting...");
                wait();   // release lock and wait
            }
            queue.offer(value);
            System.out.println("Produced: " + value);
            notifyAll();  // wake up consumers
        }

        public synchronized int consume() throws InterruptedException {
            while (queue.isEmpty()) {
                System.out.println("Buffer empty. Consumer waiting...");
                wait();
            }
            int value = queue.poll();
            System.out.println("Consumed: " + value);
            notifyAll();  // wake up producers
            return value;
        }
    }

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() throws InterruptedException {
        SharedBuffer buffer = new SharedBuffer(5);

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    buffer.produce(i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    buffer.consume();
                    Thread.sleep(150);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void runProducerConsumer(int items, int capacity) throws InterruptedException {
        SharedBuffer buffer = new SharedBuffer(capacity);

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= items; i++) {
                    buffer.produce(i);
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer");

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= items; i++) {
                    buffer.consume();
                    Thread.sleep(80);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer");

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        System.out.println("Producer-Consumer finished.");
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() throws InterruptedException {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Enter number of items to produce: ");
        int items = sc.nextInt();
        System.out.print("Enter buffer capacity: ");
        int capacity = sc.nextInt();

        runProducerConsumer(items, capacity);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) throws InterruptedException {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        runProducerConsumer(8, 3);

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
