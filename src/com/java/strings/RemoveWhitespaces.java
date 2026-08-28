package com.java.strings;

import java.util.Scanner;

/**
 * ============================================================
 * 34. REMOVE ALL WHITESPACES FROM A STRING
 * ============================================================
 *
 * CONCEPT:
 * Remove all space characters (spaces, tabs, newlines etc.)
 * from a given string.
 *
 * Example:
 *   Input  : "  Java   Programming  is  Fun  "
 *   Output : "JavaProgrammingisFun"
 *
 * Approaches:
 * 1. Using replaceAll() with regex
 * 2. Manual way using StringBuilder (good for understanding)
 */
public class RemoveWhitespaces {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void removeWithoutMethod() {
        String str = "  Java   Programming  is  Fun  ";

        // Using built-in replaceAll
        String result = str.replaceAll("\\s+", "");

        System.out.println("Original : \"" + str + "\"");
        System.out.println("Result   : \"" + result + "\"");
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Removes all whitespaces using replaceAll.
     */
    public static String removeWhitespaces(String str) {
        if (str == null) return null;
        return str.replaceAll("\\s+", "");
    }

    /**
     * Manual implementation without using replaceAll.
     */
    public static String removeWhitespacesManual(String str) {
        if (str == null) return null;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            // Keep the character only if it is not a whitespace
            if (!Character.isWhitespace(ch)) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void removeWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        System.out.println("Without spaces (built-in): \"" + removeWhitespaces(input) + "\"");
        System.out.println("Without spaces (manual)  : \"" + removeWhitespacesManual(input) + "\"");
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        removeWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        String test = " Hello   World  ";
        System.out.println("Original: \"" + test + "\"");
        System.out.println("Result  : \"" + removeWhitespaces(test) + "\"");

        System.out.println("\n===== VERSION 3: With User Input =====");
        removeWithUserInput();
    }
}
