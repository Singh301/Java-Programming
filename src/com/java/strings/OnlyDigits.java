package com.java.strings;

import java.util.Scanner;

/**
 * ============================================================
 * 37. CHECK IF A STRING CONTAINS ONLY DIGITS
 * ============================================================
 *
 * CONCEPT:
 * Verify whether every character in the string is a digit (0-9).
 *
 * Examples:
 *   "12345"  → true
 *   "12a45"  → false
 *   ""       → false (empty string)
 *   " 123"   → false (contains space)
 *
 * Approaches:
 * 1. Loop and check each character with Character.isDigit()
 * 2. Using regex: str.matches("\\d+")
 */
public class OnlyDigits {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void checkOnlyDigitsWithoutMethod() {
        String str = "12345";

        boolean onlyDigits = true;

        if (str.isEmpty()) {
            onlyDigits = false;
        } else {
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (ch < '0' || ch > '9') {
                    onlyDigits = false;
                    break;
                }
            }
        }

        System.out.println("\"" + str + "\" contains only digits? " + onlyDigits);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Returns true if the string contains only digits.
     */
    public static boolean containsOnlyDigits(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Using regex (short version).
     */
    public static boolean containsOnlyDigitsRegex(String str) {
        return str != null && str.matches("\\d+");
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void checkOnlyDigitsWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        if (containsOnlyDigits(input)) {
            System.out.println("The string contains only digits.");
        } else {
            System.out.println("The string does NOT contain only digits.");
        }
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        checkOnlyDigitsWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        System.out.println("12345 → " + containsOnlyDigits("12345"));
        System.out.println("12a45 → " + containsOnlyDigits("12a45"));
        System.out.println("\"\"    → " + containsOnlyDigits(""));
        System.out.println("987   → " + containsOnlyDigitsRegex("987"));

        System.out.println("\n===== VERSION 3: With User Input =====");
        checkOnlyDigitsWithUserInput();
    }
}
