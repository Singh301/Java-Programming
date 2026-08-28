package com.java.oops;

import java.util.Scanner;

/**
 * ============================================================
 * 183. SIMPLE FACTORY DESIGN PATTERN
 * ============================================================
 *
 * CONCEPT:
 * Factory Pattern provides an interface for creating objects
 * without specifying the exact class of object that will be created.
 *
 * Benefits:
 * - Loose coupling
 * - Centralized object creation logic
 * - Easy to add new types without changing client code
 *
 * Example: Shape Factory
 * Client asks for a "Circle" or "Rectangle" → Factory creates it.
 */
public class FactoryPattern {

    // ============================================================
    // PRODUCT INTERFACE
    // ============================================================
    interface Shape {
        void draw();
    }

    // ============================================================
    // CONCRETE PRODUCTS
    // ============================================================
    static class Circle implements Shape {
        @Override
        public void draw() {
            System.out.println("Drawing a Circle ⭕");
        }
    }

    static class Rectangle implements Shape {
        @Override
        public void draw() {
            System.out.println("Drawing a Rectangle ▭");
        }
    }

    static class Square implements Shape {
        @Override
        public void draw() {
            System.out.println("Drawing a Square ■");
        }
    }

    // ============================================================
    // FACTORY CLASS
    // ============================================================
    static class ShapeFactory {
        public Shape getShape(String shapeType) {
            if (shapeType == null) return null;

            switch (shapeType.toLowerCase()) {
                case "circle":
                    return new Circle();
                case "rectangle":
                    return new Rectangle();
                case "square":
                    return new Square();
                default:
                    System.out.println("Unknown shape: " + shapeType);
                    return null;
            }
        }
    }

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        ShapeFactory factory = new ShapeFactory();

        Shape s1 = factory.getShape("circle");
        s1.draw();

        Shape s2 = factory.getShape("rectangle");
        s2.draw();

        Shape s3 = factory.getShape("square");
        s3.draw();
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void createAndDraw(String type) {
        ShapeFactory factory = new ShapeFactory();
        Shape shape = factory.getShape(type);
        if (shape != null) {
            shape.draw();
        }
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        Scanner sc = new Scanner(System.in);
        ShapeFactory factory = new ShapeFactory();

        System.out.print("Enter shape (circle/rectangle/square): ");
        String type = sc.nextLine();

        Shape shape = factory.getShape(type);
        if (shape != null) {
            shape.draw();
        }
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        createAndDraw("circle");
        createAndDraw("square");

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
