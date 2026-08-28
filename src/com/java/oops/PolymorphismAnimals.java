package com.java.oops;

import java.util.Scanner;

/**
 * ============================================================
 * 190. POLYMORPHISM - ANIMAL HIERARCHY AND THEIR SOUNDS
 * ============================================================
 *
 * CONCEPT:
 * Polymorphism means "many forms".
 * The same method call can behave differently based on the
 * actual object type at runtime.
 *
 * We create a hierarchy:
 * Animal (parent)
 *   ├── Dog
 *   ├── Cat
 *   ├── Cow
 *   └── Lion
 *
 * Each animal overrides the makeSound() method.
 * Using parent reference we achieve runtime polymorphism.
 */
public class PolymorphismAnimals {

    // ============================================================
    // PARENT CLASS
    // ============================================================
    static class Animal {
        String name;

        Animal(String name) {
            this.name = name;
        }

        void makeSound() {
            System.out.println(name + " makes a generic sound");
        }

        void eat() {
            System.out.println(name + " is eating");
        }
    }

    // ============================================================
    // CHILD CLASSES
    // ============================================================
    static class Dog extends Animal {
        Dog(String name) {
            super(name);
        }

        @Override
        void makeSound() {
            System.out.println(name + " says: Woof Woof! 🐶");
        }
    }

    static class Cat extends Animal {
        Cat(String name) {
            super(name);
        }

        @Override
        void makeSound() {
            System.out.println(name + " says: Meow Meow! 🐱");
        }
    }

    static class Cow extends Animal {
        Cow(String name) {
            super(name);
        }

        @Override
        void makeSound() {
            System.out.println(name + " says: Moo Moo! 🐄");
        }
    }

    static class Lion extends Animal {
        Lion(String name) {
            super(name);
        }

        @Override
        void makeSound() {
            System.out.println(name + " says: Roarrr! 🦁");
        }
    }

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        Animal a1 = new Dog("Tommy");
        Animal a2 = new Cat("Kitty");
        Animal a3 = new Cow("Gauri");
        Animal a4 = new Lion("Simba");

        // Runtime polymorphism - method called depends on actual object
        a1.makeSound();
        a2.makeSound();
        a3.makeSound();
        a4.makeSound();
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void makeAllSounds(Animal[] animals) {
        for (Animal animal : animals) {
            animal.makeSound();   // polymorphic call
        }
    }

    public static Animal createAnimal(String type, String name) {
        switch (type.toLowerCase()) {
            case "dog":  return new Dog(name);
            case "cat":  return new Cat(name);
            case "cow":  return new Cow(name);
            case "lion": return new Lion(name);
            default:     return new Animal(name);
        }
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("How many animals? ");
        int n = sc.nextInt();
        sc.nextLine();

        Animal[] animals = new Animal[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter type (dog/cat/cow/lion): ");
            String type = sc.nextLine();
            System.out.print("Enter name: ");
            String name = sc.nextLine();
            animals[i] = createAnimal(type, name);
        }

        System.out.println("\n--- All Animals Making Sound ---");
        makeAllSounds(animals);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        Animal[] zoo = {
                new Dog("Bruno"),
                new Cat("Milo"),
                new Cow("Lakshmi"),
                new Lion("Sheru")
        };
        makeAllSounds(zoo);

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
