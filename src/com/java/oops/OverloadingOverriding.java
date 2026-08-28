package com.java.oops;

/**
 * ============================================================
 * 181. METHOD OVERLOADING AND METHOD OVERRIDING
 * ============================================================
 *
 * METHOD OVERLOADING (Compile-time Polymorphism):
 * - Same method name, different parameter list (type/number/order)
 * - Happens in the same class
 * - Return type can be different (but parameters must differ)
 *
 * METHOD OVERRIDING (Runtime Polymorphism):
 * - Same method signature in parent and child class
 * - Child class provides its own implementation
 * - Requires inheritance
 * - Access modifier cannot be more restrictive
 */
public class OverloadingOverriding {

    // ============================================================
    // METHOD OVERLOADING EXAMPLES
    // ============================================================
    static class Calculator {
        // Overloaded methods - different number of parameters
        int add(int a, int b) {
            return a + b;
        }

        int add(int a, int b, int c) {
            return a + b + c;
        }

        // Overloaded - different type of parameters
        double add(double a, double b) {
            return a + b;
        }

        // Overloaded - different order
        String add(String a, int b) {
            return a + b;
        }

        String add(int a, String b) {
            return a + b;
        }
    }

    // ============================================================
    // METHOD OVERRIDING EXAMPLES
    // ============================================================
    static class Animal {
        void sound() {
            System.out.println("Animal makes a sound");
        }

        void eat() {
            System.out.println("Animal is eating");
        }
    }

    static class Dog extends Animal {
        // Overriding the sound() method
        @Override
        void sound() {
            System.out.println("Dog barks: Woof Woof!");
        }

        // Overriding eat()
        @Override
        void eat() {
            System.out.println("Dog is eating bones");
        }
    }

    static class Cat extends Animal {
        @Override
        void sound() {
            System.out.println("Cat meows: Meow Meow!");
        }
    }

    // ============================================================
    // VERSION 1: WITHOUT METHOD (direct demonstration)
    // ============================================================
    public static void demoWithoutMethod() {
        System.out.println("--- Method Overloading ---");
        Calculator calc = new Calculator();
        System.out.println("add(5, 10)       = " + calc.add(5, 10));
        System.out.println("add(5, 10, 15)   = " + calc.add(5, 10, 15));
        System.out.println("add(2.5, 3.5)    = " + calc.add(2.5, 3.5));
        System.out.println("add(\"Num\", 10)  = " + calc.add("Num", 10));

        System.out.println("\n--- Method Overriding ---");
        Animal a1 = new Animal();
        Animal a2 = new Dog();   // parent reference, child object
        Animal a3 = new Cat();

        a1.sound();
        a2.sound();   // calls Dog's version (runtime polymorphism)
        a3.sound();   // calls Cat's version
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void demonstrateOverloading() {
        Calculator calc = new Calculator();
        System.out.println("Overloading demo:");
        System.out.println(calc.add(1, 2));
        System.out.println(calc.add(1, 2, 3));
        System.out.println(calc.add(1.5, 2.5));
    }

    public static void demonstrateOverriding() {
        System.out.println("Overriding demo:");
        Animal[] animals = {new Animal(), new Dog(), new Cat()};
        for (Animal animal : animals) {
            animal.sound();   // polymorphic call
        }
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT (simple menu)
    // ============================================================
    public static void demoWithUserInput() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.println("1. Overloading Demo  2. Overriding Demo");
        System.out.print("Choice: ");
        int choice = sc.nextInt();

        if (choice == 1) {
            demonstrateOverloading();
        } else {
            demonstrateOverriding();
        }
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        demonstrateOverloading();
        demonstrateOverriding();

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
