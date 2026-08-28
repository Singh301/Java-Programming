package com.java.file.IO.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * ============================================================
 * 95. DESERIALIZE AN OBJECT FROM A FILE
 * ============================================================
 *
 * CONCEPT:
 * Deserialization is the reverse of serialization.
 * It reconstructs the object from the byte stream stored in a file.
 *
 * Classes used:
 * - ObjectInputStream
 * - FileInputStream
 *
 * Note: The class definition must be available and serialVersionUID
 * should match for successful deserialization.
 */
public class DeserializeObject {

    static class Student implements Serializable {
        private static final long serialVersionUID = 1L;

        private String name;
        private int rollNo;
        private String course;
        private transient String password;

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
    public static void demoWithoutMethod() throws Exception {
        Path file = Files.createTempFile("student", ".ser");

        // First serialize
        Student original = new Student("Amit", 303, "Mechanical", "mypass");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file.toFile()))) {
            oos.writeObject(original);
        }

        // Now deserialize
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file.toFile()))) {
            Student restored = (Student) ois.readObject();
            System.out.println("Original  : " + original);
            System.out.println("Restored  : " + restored);
            System.out.println("Note: password is null because it was transient");
        }

        Files.deleteIfExists(file);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static Object deserialize(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return ois.readObject();
        }
    }

    public static void serializeAndDeserializeDemo() throws Exception {
        Path file = Files.createTempFile("demo", ".ser");
        Student s = new Student("Neha", 404, "Civil", "secret");

        // Serialize
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file.toFile()))) {
            oos.writeObject(s);
        }

        // Deserialize
        Student restored = (Student) deserialize(file.toString());
        System.out.println("Deserialized object: " + restored);

        Files.deleteIfExists(file);
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() throws Exception {
        java.util.Scanner sc = new java.util.Scanner(System.in);

        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter roll: ");
        int roll = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter course: ");
        String course = sc.nextLine();

        Path file = Files.createTempFile("user", ".ser");
        Student s = new Student(name, roll, course, "temp");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file.toFile()))) {
            oos.writeObject(s);
        }

        Student restored = (Student) deserialize(file.toString());
        System.out.println("Deserialized: " + restored);

        Files.deleteIfExists(file);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) throws Exception {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        serializeAndDeserializeDemo();

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
