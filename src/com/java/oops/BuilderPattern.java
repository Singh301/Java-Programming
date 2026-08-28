package com.java.oops;

/**
 * ============================================================
 * 187. BUILDER PATTERN - CONSTRUCTING COMPLEX OBJECTS
 * ============================================================
 *
 * CONCEPT:
 * Builder Pattern separates the construction of a complex object
 * from its representation. The same construction process can
 * create different representations.
 *
 * Useful when an object has many optional parameters.
 *
 * Example: Building a Computer / Pizza / House with many options.
 *
 * Benefits:
 * - Avoids telescoping constructors
 * - Readable and flexible object creation
 * - Immutable objects can be created easily
 */
public class BuilderPattern {

    // ============================================================
    // PRODUCT
    // ============================================================
    static class Computer {
        // Required
        private final String cpu;
        private final String ram;

        // Optional
        private final String storage;
        private final String graphicsCard;
        private final boolean wifi;
        private final boolean bluetooth;

        // Private constructor - only Builder can create
        private Computer(Builder builder) {
            this.cpu = builder.cpu;
            this.ram = builder.ram;
            this.storage = builder.storage;
            this.graphicsCard = builder.graphicsCard;
            this.wifi = builder.wifi;
            this.bluetooth = builder.bluetooth;
        }

        @Override
        public String toString() {
            return "Computer {" +
                    "\n  CPU: " + cpu +
                    "\n  RAM: " + ram +
                    "\n  Storage: " + storage +
                    "\n  Graphics: " + graphicsCard +
                    "\n  WiFi: " + wifi +
                    "\n  Bluetooth: " + bluetooth +
                    "\n}";
        }

        // ============================================================
        // BUILDER
        // ============================================================
        static class Builder {
            // Required
            private final String cpu;
            private final String ram;

            // Optional - default values
            private String storage = "256GB SSD";
            private String graphicsCard = "Integrated";
            private boolean wifi = false;
            private boolean bluetooth = false;

            public Builder(String cpu, String ram) {
                this.cpu = cpu;
                this.ram = ram;
            }

            public Builder storage(String storage) {
                this.storage = storage;
                return this;   // method chaining
            }

            public Builder graphicsCard(String graphicsCard) {
                this.graphicsCard = graphicsCard;
                return this;
            }

            public Builder wifi(boolean wifi) {
                this.wifi = wifi;
                return this;
            }

            public Builder bluetooth(boolean bluetooth) {
                this.bluetooth = bluetooth;
                return this;
            }

            public Computer build() {
                return new Computer(this);
            }
        }
    }

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        Computer gamingPC = new Computer.Builder("Intel i9", "32GB")
                .storage("1TB SSD")
                .graphicsCard("RTX 4080")
                .wifi(true)
                .bluetooth(true)
                .build();

        System.out.println(gamingPC);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static Computer createOfficePC() {
        return new Computer.Builder("Intel i5", "16GB")
                .storage("512GB SSD")
                .wifi(true)
                .build();
    }

    public static Computer createGamingPC() {
        return new Computer.Builder("AMD Ryzen 9", "64GB")
                .storage("2TB NVMe")
                .graphicsCard("RTX 4090")
                .wifi(true)
                .bluetooth(true)
                .build();
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        java.util.Scanner sc = new java.util.Scanner(System.in);

        System.out.print("Enter CPU: ");
        String cpu = sc.nextLine();
        System.out.print("Enter RAM: ");
        String ram = sc.nextLine();

        Computer.Builder builder = new Computer.Builder(cpu, ram);

        System.out.print("Enter Storage (or press Enter for default): ");
        String storage = sc.nextLine();
        if (!storage.isEmpty()) builder.storage(storage);

        System.out.print("Enter Graphics Card (or press Enter for default): ");
        String gpu = sc.nextLine();
        if (!gpu.isEmpty()) builder.graphicsCard(gpu);

        System.out.print("WiFi? (y/n): ");
        builder.wifi(sc.next().equalsIgnoreCase("y"));

        Computer pc = builder.build();
        System.out.println("\nYour Computer Configuration:");
        System.out.println(pc);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        System.out.println("Office PC:");
        System.out.println(createOfficePC());
        System.out.println("\nGaming PC:");
        System.out.println(createGamingPC());

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
