package com.java.oops;

/**
 * ============================================================
 * 189. ABSTRACT CLASS AND INTERFACES
 * ============================================================
 *
 * ABSTRACT CLASS:
 * - Can have abstract methods (no body) and concrete methods
 * - Can have constructors, fields, access modifiers
 * - A class can extend only ONE abstract class
 * - Used for "is-a" relationship with shared code
 *
 * INTERFACE:
 * - All methods are public abstract by default (before Java 8)
 * - From Java 8: can have default and static methods
 * - From Java 9: can have private methods
 * - A class can implement MULTIPLE interfaces
 * - Used for "can-do" capability / contract
 *
 * Key Difference:
 * Abstract class → partial implementation, single inheritance
 * Interface → pure contract (mostly), multiple inheritance of type
 */
public class AbstractAndInterface {

    // ============================================================
    // ABSTRACT CLASS
    // ============================================================
    static abstract class Vehicle {
        String brand;

        Vehicle(String brand) {
            this.brand = brand;
        }

        // Concrete method
        void displayBrand() {
            System.out.println("Brand: " + brand);
        }

        // Abstract method - must be implemented by subclasses
        abstract void start();
        abstract void stop();
    }

    static class Car extends Vehicle {
        Car(String brand) {
            super(brand);
        }

        @Override
        void start() {
            System.out.println(brand + " Car started with key");
        }

        @Override
        void stop() {
            System.out.println(brand + " Car stopped");
        }
    }

    static class Bike extends Vehicle {
        Bike(String brand) {
            super(brand);
        }

        @Override
        void start() {
            System.out.println(brand + " Bike started with kick/self");
        }

        @Override
        void stop() {
            System.out.println(brand + " Bike stopped");
        }
    }

    // ============================================================
    // INTERFACES
    // ============================================================
    interface Flyable {
        void fly();

        // Default method (Java 8+)
        default void land() {
            System.out.println("Landing safely...");
        }
    }

    interface Swimmable {
        void swim();
    }

    // A class implementing multiple interfaces
    static class Duck implements Flyable, Swimmable {
        @Override
        public void fly() {
            System.out.println("Duck is flying");
        }

        @Override
        public void swim() {
            System.out.println("Duck is swimming");
        }
    }

    static class Airplane implements Flyable {
        @Override
        public void fly() {
            System.out.println("Airplane is flying at high altitude");
        }
    }

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        System.out.println("--- Abstract Class Demo ---");
        Vehicle car = new Car("Toyota");
        car.displayBrand();
        car.start();
        car.stop();

        Vehicle bike = new Bike("Honda");
        bike.start();

        System.out.println("\n--- Interface Demo ---");
        Duck duck = new Duck();
        duck.fly();
        duck.swim();
        duck.land();

        Airplane plane = new Airplane();
        plane.fly();
        plane.land();
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void demonstrateAbstract() {
        Vehicle v = new Car("Tesla");
        v.start();
        v.stop();
    }

    public static void demonstrateInterface() {
        Flyable f = new Duck();
        f.fly();
        f.land();
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        java.util.Scanner sc = new java.util.Scanner(System.in);

        System.out.println("1.Car  2.Bike");
        System.out.print("Choose vehicle: ");
        int choice = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter brand: ");
        String brand = sc.nextLine();

        Vehicle v = (choice == 1) ? new Car(brand) : new Bike(brand);
        v.displayBrand();
        v.start();
        v.stop();
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        demonstrateAbstract();
        demonstrateInterface();

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
