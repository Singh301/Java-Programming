package com.java.oops;

/**
 * ============================================================
 * 186. DECORATOR PATTERN - EXTENDING OBJECT BEHAVIOR
 * ============================================================
 *
 * CONCEPT:
 * Decorator Pattern allows us to add new behavior to objects
 * dynamically without modifying their structure.
 *
 * It is a flexible alternative to subclassing.
 *
 * Example: Coffee Shop
 * - Base: SimpleCoffee
 * - Decorators: Milk, Sugar, Whip
 * We can wrap a coffee with any combination of decorators.
 *
 * Structure:
 * Component ← ConcreteComponent
 *          ← Decorator ← ConcreteDecorators
 */
public class DecoratorPattern {

    // ============================================================
    // COMPONENT INTERFACE
    // ============================================================
    interface Coffee {
        String getDescription();
        double getCost();
    }

    // ============================================================
    // CONCRETE COMPONENT
    // ============================================================
    static class SimpleCoffee implements Coffee {
        @Override
        public String getDescription() {
            return "Simple Coffee";
        }

        @Override
        public double getCost() {
            return 50.0;
        }
    }

    // ============================================================
    // BASE DECORATOR
    // ============================================================
    static abstract class CoffeeDecorator implements Coffee {
        protected Coffee coffee;

        public CoffeeDecorator(Coffee coffee) {
            this.coffee = coffee;
        }

        @Override
        public String getDescription() {
            return coffee.getDescription();
        }

        @Override
        public double getCost() {
            return coffee.getCost();
        }
    }

    // ============================================================
    // CONCRETE DECORATORS
    // ============================================================
    static class MilkDecorator extends CoffeeDecorator {
        public MilkDecorator(Coffee coffee) {
            super(coffee);
        }

        @Override
        public String getDescription() {
            return coffee.getDescription() + " + Milk";
        }

        @Override
        public double getCost() {
            return coffee.getCost() + 15.0;
        }
    }

    static class SugarDecorator extends CoffeeDecorator {
        public SugarDecorator(Coffee coffee) {
            super(coffee);
        }

        @Override
        public String getDescription() {
            return coffee.getDescription() + " + Sugar";
        }

        @Override
        public double getCost() {
            return coffee.getCost() + 5.0;
        }
    }

    static class WhipDecorator extends CoffeeDecorator {
        public WhipDecorator(Coffee coffee) {
            super(coffee);
        }

        @Override
        public String getDescription() {
            return coffee.getDescription() + " + Whip";
        }

        @Override
        public double getCost() {
            return coffee.getCost() + 20.0;
        }
    }

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + " = ₹" + coffee.getCost());

        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription() + " = ₹" + coffee.getCost());

        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getDescription() + " = ₹" + coffee.getCost());

        coffee = new WhipDecorator(coffee);
        System.out.println(coffee.getDescription() + " = ₹" + coffee.getCost());
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void makeCoffee() {
        // Order: Simple + Milk + Sugar
        Coffee order = new SugarDecorator(new MilkDecorator(new SimpleCoffee()));
        System.out.println("Order: " + order.getDescription());
        System.out.println("Total: ₹" + order.getCost());
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        Coffee coffee = new SimpleCoffee();

        System.out.println("Base coffee: ₹50");
        System.out.print("Add Milk? (y/n): ");
        if (sc.next().equalsIgnoreCase("y")) {
            coffee = new MilkDecorator(coffee);
        }
        System.out.print("Add Sugar? (y/n): ");
        if (sc.next().equalsIgnoreCase("y")) {
            coffee = new SugarDecorator(coffee);
        }
        System.out.print("Add Whip? (y/n): ");
        if (sc.next().equalsIgnoreCase("y")) {
            coffee = new WhipDecorator(coffee);
        }

        System.out.println("\nYour order: " + coffee.getDescription());
        System.out.println("Total cost: ₹" + coffee.getCost());
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        makeCoffee();

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
