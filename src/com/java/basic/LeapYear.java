package com.java.basic;

import java.util.Scanner;

/**
 * ============================================================
 * 13. LEAP YEAR
 * ============================================================
 *
 * WHAT IS A LEAP YEAR?
 * --------------------
 * A leap year has 366 days instead of the usual 365 days.
 * An extra day (29 February) is added.
 *
 * RULES TO CHECK LEAP YEAR:
 * 1. Year is divisible by 4 → possibly leap
 * 2. BUT if the year is divisible by 100 → it is NOT leap
 * 3. EXCEPT if the year is also divisible by 400 → it IS leap
 *
 * Examples:
 *   2020 → divisible by 4 → Leap Year
 *   1900 → divisible by 100 but not by 400 → Not Leap
 *   2000 → divisible by 400 → Leap Year
 *   2023 → not divisible by 4 → Not Leap
 *
 * This logic comes from the Gregorian calendar.
 */
public class LeapYear {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void checkLeapWithoutMethod() {
        int year = 2024;

        boolean isLeap;

        if (year % 4 == 0) {
            if (year % 100 == 0) {
                // Century year → must also be divisible by 400
                isLeap = (year % 400 == 0);
            } else {
                isLeap = true;
            }
        } else {
            isLeap = false;
        }

        if (isLeap) {
            System.out.println(year + " is a Leap Year");
        } else {
            System.out.println(year + " is NOT a Leap Year");
        }
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Returns true if the given year is a leap year.
     */
    public static boolean isLeapYear(int year) {
        // Concise and clear version of the rules
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void checkLeapWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a year: ");
        int year = sc.nextInt();

        if (isLeapYear(year)) {
            System.out.println(year + " is a Leap Year");
        } else {
            System.out.println(year + " is NOT a Leap Year");
        }
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        checkLeapWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        int[] years = {1900, 2000, 2020, 2023, 2024, 2100};
        for (int y : years) {
            System.out.println(y + " → " + (isLeapYear(y) ? "Leap Year" : "Not Leap Year"));
        }

        System.out.println("\n===== VERSION 3: With User Input =====");
        checkLeapWithUserInput();
    }
}
