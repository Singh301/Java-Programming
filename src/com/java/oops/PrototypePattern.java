package com.java.oops;

/**
 * ============================================================
 * 188. PROTOTYPE PATTERN - CLONING OBJECTS
 * ============================================================
 *
 * CONCEPT:
 * Prototype Pattern is used when object creation is costly.
 * Instead of creating a new object from scratch, we clone
 * an existing object (prototype).
 *
 * Java provides Cloneable interface and Object.clone() method.
 *
 * Types of cloning:
 * - Shallow copy: copies fields, but nested objects are shared
 * - Deep copy: everything is copied independently
 *
 * Example: Cloning a Document or Shape object.
 */
public class PrototypePattern {

    // ============================================================
    // PROTOTYPE INTERFACE
    // ============================================================
    interface Prototype extends Cloneable {
        Prototype clone();
    }

    // ============================================================
    // CONCRETE PROTOTYPE
    // ============================================================
    static class Document implements Prototype {
        private String title;
        private String content;
        private String author;

        public Document(String title, String content, String author) {
            this.title = title;
            this.content = content;
            this.author = author;
            // Simulate costly creation
            System.out.println("Creating new Document (costly operation)...");
        }

        // Copy constructor style via clone
        @Override
        public Document clone() {
            try {
                return (Document) super.clone();   // shallow copy
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void display() {
            System.out.println("Document [Title=" + title +
                    ", Author=" + author + ", Content=" + content + "]");
        }
    }

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        Document original = new Document("Report", "Annual sales data", "Rahul");
        original.display();

        // Clone instead of creating new
        Document copy = original.clone();
        copy.setTitle("Report - Copy");
        copy.setContent("Modified content");

        System.out.println("\nAfter cloning and modifying:");
        original.display();
        copy.display();
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static Document createAndClone() {
        Document proto = new Document("Template", "Default content", "System");
        Document clone = proto.clone();
        clone.setTitle("My Document");
        return clone;
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        java.util.Scanner sc = new java.util.Scanner(System.in);

        System.out.print("Enter original title: ");
        String title = sc.nextLine();
        System.out.print("Enter content: ");
        String content = sc.nextLine();
        System.out.print("Enter author: ");
        String author = sc.nextLine();

        Document original = new Document(title, content, author);
        Document clone = original.clone();

        System.out.print("Enter new title for clone: ");
        clone.setTitle(sc.nextLine());

        System.out.println("\nOriginal:");
        original.display();
        System.out.println("Clone:");
        clone.display();
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        Document doc = createAndClone();
        doc.display();

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
