package com.java.strings;

import java.util.Scanner;

/**
 * ============================================================
 * 40. STRING MATCHING WITHOUT BUILT-IN METHODS
 * ============================================================
 *
 * CONCEPT:
 * Check whether a pattern (substring) exists inside a text
 * without using built-in methods like indexOf(), contains(), etc.
 *
 * This is the classic Naive String Matching algorithm.
 *
 * Example:
 *   Text    : "hello world"
 *   Pattern : "world"
 *   Result  : Found at index 6
 *
 * Algorithm:
 * Slide the pattern over the text one by one and check
 * for a match at every position.
 *
 * Time Complexity: O((n-m+1) * m)  where n = text length, m = pattern length
 */
public class StringMatching {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void matchWithoutMethod() {
        String text = "hello world";
        String pattern = "world";

        int n = text.length();
        int m = pattern.length();
        int foundIndex = -1;

        // Slide pattern over text
        for (int i = 0; i <= n - m; i++) {
            int j;

            // Check for match at current position
            for (j = 0; j < m; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }

            // If we completed the inner loop → full match found
            if (j == m) {
                foundIndex = i;
                break;
            }
        }

        if (foundIndex != -1) {
            System.out.println("Pattern \"" + pattern + "\" found at index " + foundIndex);
        } else {
            System.out.println("Pattern \"" + pattern + "\" not found");
        }
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Returns the starting index of the first occurrence of pattern in text.
     * Returns -1 if not found.
     * Does not use any built-in string search methods.
     */
    public static int indexOf(String text, String pattern) {
        if (text == null || pattern == null) return -1;
        if (pattern.isEmpty()) return 0;
        if (pattern.length() > text.length()) return -1;

        int n = text.length();
        int m = pattern.length();

        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j == m) {
                return i;   // match found
            }
        }
        return -1;   // not found
    }

    /**
     * Returns true if pattern exists in text.
     */
    public static boolean contains(String text, String pattern) {
        return indexOf(text, pattern) != -1;
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void matchWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the text   : ");
        String text = sc.nextLine();
        System.out.print("Enter the pattern: ");
        String pattern = sc.nextLine();

        int index = indexOf(text, pattern);

        if (index != -1) {
            System.out.println("Pattern found at index: " + index);
        } else {
            System.out.println("Pattern not found");
        }
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        matchWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        System.out.println("indexOf(\"hello world\", \"world\") = " + indexOf("hello world", "world"));
        System.out.println("indexOf(\"hello world\", \"java\")  = " + indexOf("hello world", "java"));
        System.out.println("contains(\"abcdef\", \"cde\")      = " + contains("abcdef", "cde"));

        System.out.println("\n===== VERSION 3: With User Input =====");
        matchWithUserInput();
    }
}
