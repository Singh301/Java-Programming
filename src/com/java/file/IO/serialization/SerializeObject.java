package com.java.file.IO.serialization;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * ============================================================
 * 94. SERIALIZE AN OBJECT TO A FILE
 * ============================================================
 *
 * CONCEPT:
 * Serialization converts an object into a byte stream so it can
 * be saved to a file or sent over a network.
 *
 * Requirements:
 * - Class must implement java.io.Serializable
 * - All non-transient fields are serialized
 *
 * Classes used:
 * - ObjectOutputStream
 * - FileOutputStream
 */
public class SerializeObject {

    // Student class must implement Serializable
    static class Student implements Serializable {
        private static final long serialVersionUID = 1L;

        private String name;
        private int rollNo;
        private String course;
        private transient String password; // will not be serialized

        public Student(String name, int rollNo, String course, String password) {
            this.name = name;
            this.rollNo = rollNo;
            this.course = course;
            this.password = password;
        }

        @Override
        public String toString() {
            return "Student{name='" + name + "', rollNo=" + rollNo
                    + ", course='" + course + "', password='" + password + "'}";
        }
    }

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() throws IOException {
        Student student = new Student("Rahul", 101, "Computer Science", "secret123");
        Path file = Files.createTempFile("student", ".ser");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file.toFile()))) {
            oos.writeObject(student);
            System.out.println("Object serialized successfully to: " + file);
            System.out.println("Original object: " + student);
        }

        Files.deleteIfExists(file);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void serialize(Object obj, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(obj);
            System.out.println("Serialized object to: " + filePath);
        }
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() throws IOException {
        java.util.Scanner sc = new java.util.Scanner(System.in);

        System.out.print("Enter student name: ");
        String name = sc.nextLine();
        System.out.print("Enter roll no: ");
        int roll = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter course: ");
        String course = sc.nextLine();

        Student s = new Student(name, roll, course, "defaultPass");
        Path file = Files.createTempFile("student", ".ser");

        serialize(s, file.toString());
        System.out.println("Object: " + s);

        Files.deleteIfExists(file);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) throws IOException {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        Student s = new Student("Priya", 202, "Electronics", "pass456");
        Path p = Files.createTempFile("demo", ".ser");
        serialize(s, p.toString());
        Files.deleteIfExists(p);

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
