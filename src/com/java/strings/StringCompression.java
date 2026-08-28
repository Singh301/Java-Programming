package com.java.strings;

import java.util.Scanner;

/**
 * ============================================================
 * 39. BASIC STRING COMPRESSION
 * ============================================================
 *
 * CONCEPT:
 * Compress a string by replacing consecutive repeated characters
 * with the character followed by the count.
 *
 * Example:
 *   Input  : "aabcccccaaa"
 *   Output : "a2b1c5a3"
 *
 * If the compressed string is not smaller than the original,
 * return the original string.
 *
 * This is a classic interview problem (from Cracking the Coding Interview).
 */
public class StringCompression {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void compressWithoutMethod() {
        String str = "aabcccccaaa";

        StringBuilder compressed = new StringBuilder();
        int count = 1;

        for (int i = 0; i < str.length(); i++) {
            // If next character is same, increase count
            if (i + 1 < str.length() && str.charAt(i) == str.charAt(i + 1)) {
                count++;
            } else {
                // Append character + count
                compressed.append(str.charAt(i));
                compressed.append(count);
                count = 1;   // reset count
            }
        }

        String result = compressed.length() < str.length()
                ? compressed.toString()
                : str;

        System.out.println("Original   : " + str);
        System.out.println("Compressed : " + result);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Compresses the string using character counts.
     * Returns original if compression does not help.
     */
    public static String compress(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        StringBuilder compressed = new StringBuilder();
        int count = 1;

        for (int i = 0; i < str.length(); i++) {
            if (i + 1 < str.length() && str.charAt(i) == str.charAt(i + 1)) {
                count++;
            } else {
                compressed.append(str.charAt(i));
                compressed.append(count);
                count = 1;
            }
        }

        return compressed.length() < str.length()
                ? compressed.toString()
                : str;
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void compressWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        System.out.println("Compressed: " + compress(input));
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        compressWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        System.out.println("aabcccccaaa → " + compress("aabcccccaaa"));
        System.out.println("abc         → " + compress("abc"));
        System.out.println("aaabb       → " + compress("aaabb"));

        System.out.println("\n===== VERSION 3: With User Input =====");
        compressWithUserInput();
    }
}
