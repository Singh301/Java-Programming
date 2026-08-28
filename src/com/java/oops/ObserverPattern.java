package com.java.oops;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ============================================================
 * 185. OBSERVER PATTERN - WEATHER APPLICATION
 * ============================================================
 *
 * CONCEPT:
 * Observer Pattern defines a one-to-many dependency between objects.
 * When one object (Subject) changes state, all its dependents
 * (Observers) are notified and updated automatically.
 *
 * Real-world example: Weather Station
 * - WeatherStation (Subject) has temperature, humidity etc.
 * - Display devices (Observers) automatically update when weather changes.
 *
 * Components:
 * - Subject interface (register, remove, notify)
 * - Concrete Subject (WeatherStation)
 * - Observer interface (update)
 * - Concrete Observers (PhoneDisplay, TVDisplay etc.)
 */
public class ObserverPattern {

    // ============================================================
    // OBSERVER INTERFACE
    // ============================================================
    interface Observer {
        void update(float temperature, float humidity, float pressure);
    }

    // ============================================================
    // SUBJECT INTERFACE
    // ============================================================
    interface Subject {
        void registerObserver(Observer o);
        void removeObserver(Observer o);
        void notifyObservers();
    }

    // ============================================================
    // CONCRETE SUBJECT - Weather Station
    // ============================================================
    static class WeatherStation implements Subject {
        private List<Observer> observers = new ArrayList<>();
        private float temperature;
        private float humidity;
        private float pressure;

        @Override
        public void registerObserver(Observer o) {
            observers.add(o);
        }

        @Override
        public void removeObserver(Observer o) {
            observers.remove(o);
        }

        @Override
        public void notifyObservers() {
            for (Observer o : observers) {
                o.update(temperature, humidity, pressure);
            }
        }

        public void setMeasurements(float temperature, float humidity, float pressure) {
            this.temperature = temperature;
            this.humidity = humidity;
            this.pressure = pressure;
            System.out.println("\n--- Weather Updated ---");
            notifyObservers();
        }
    }

    // ============================================================
    // CONCRETE OBSERVERS
    // ============================================================
    static class PhoneDisplay implements Observer {
        @Override
        public void update(float temperature, float humidity, float pressure) {
            System.out.println("📱 Phone Display → Temp: " + temperature +
                    "°C, Humidity: " + humidity + "%, Pressure: " + pressure);
        }
    }

    static class TVDisplay implements Observer {
        @Override
        public void update(float temperature, float humidity, float pressure) {
            System.out.println("📺 TV Display    → Temp: " + temperature +
                    "°C, Humidity: " + humidity + "%, Pressure: " + pressure);
        }
    }

    static class WindowDisplay implements Observer {
        @Override
        public void update(float temperature, float humidity, float pressure) {
            System.out.println("🪟 Window Display→ Temp: " + temperature +
                    "°C, Humidity: " + humidity + "%");
        }
    }

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        WeatherStation station = new WeatherStation();

        Observer phone = new PhoneDisplay();
        Observer tv = new TVDisplay();

        station.registerObserver(phone);
        station.registerObserver(tv);

        station.setMeasurements(28.5f, 65.0f, 1013.0f);
        station.setMeasurements(30.0f, 70.0f, 1010.0f);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void runWeatherDemo() {
        WeatherStation station = new WeatherStation();
        station.registerObserver(new PhoneDisplay());
        station.registerObserver(new TVDisplay());
        station.registerObserver(new WindowDisplay());

        station.setMeasurements(25.0f, 60.0f, 1012.0f);
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        Scanner sc = new Scanner(System.in);
        WeatherStation station = new WeatherStation();

        station.registerObserver(new PhoneDisplay());
        station.registerObserver(new TVDisplay());

        System.out.print("Enter temperature: ");
        float temp = sc.nextFloat();
        System.out.print("Enter humidity: ");
        float humidity = sc.nextFloat();
        System.out.print("Enter pressure: ");
        float pressure = sc.nextFloat();

        station.setMeasurements(temp, humidity, pressure);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        runWeatherDemo();

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
