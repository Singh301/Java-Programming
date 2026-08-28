package com.java.oops;

/**
 * ============================================================
 * 182. this AND super KEYWORDS IN CONSTRUCTORS
 * ============================================================
 *
 * this KEYWORD:
 * - Refers to the current object
 * - Used to differentiate instance variables from parameters
 * - Can call another constructor of the same class (constructor chaining)
 *   → this(...)
 *
 * super KEYWORD:
 * - Refers to the immediate parent class
 * - Used to call parent class constructor
 *   → super(...)
 * - Used to access parent class methods/variables when overridden
 *
 * RULES:
 * - this() or super() must be the first statement in a constructor
 * - You cannot use both this() and super() in the same constructor
 */
public class ThisAndSuper {

    // ============================================================
    // PARENT CLASS
    // ============================================================
    static class Person {
        String name;
        int age;

        // Parent constructor
        Person(String name, int age) {
            this.name = name;   // this distinguishes parameter from field
            this.age = age;
            System.out.println("Person constructor called");
        }

        void display() {
            System.out.println("Name: " + name + ", Age: " + age);
        }
    }

    // ============================================================
    // CHILD CLASS
    // ============================================================
    static class Student extends Person {
        String course;
        int rollNo;

        // Constructor using super() and this()
        Student(String name, int age, String course, int rollNo) {
            super(name, age);          // calls parent constructor (must be first)
            this.course = course;      // this refers to current object
            this.rollNo = rollNo;
            System.out.println("Student constructor called");
        }

        // Constructor chaining with this()
        Student(String name, int age, String course) {
            this(name, age, course, 0);  // calls the other constructor of same class
            System.out.println("Student constructor (3 params) called");
        }

        @Override
        void display() {
            super.display();   // call parent version
            System.out.println("Course: " + course + ", Roll No: " + rollNo);
        }
    }

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        System.out.println("--- Creating Student with 4 parameters ---");
        Student s1 = new Student("Rahul", 20, "Computer Science", 101);
        s1.display();

        System.out.println("\n--- Creating Student with 3 parameters (this chaining) ---");
        Student s2 = new Student("Priya", 19, "Electronics");
        s2.display();
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void demonstrateThisAndSuper() {
        Student s = new Student("Amit", 21, "Mechanical", 205);
        s.display();
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        java.util.Scanner sc = new java.util.Scanner(System.in);

        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter course: ");
        String course = sc.nextLine();
        System.out.print("Enter roll no: ");
        int roll = sc.nextInt();

        Student s = new Student(name, age, course, roll);
        System.out.println("\nStudent Details:");
        s.display();
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        demonstrateThisAndSuper();

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
