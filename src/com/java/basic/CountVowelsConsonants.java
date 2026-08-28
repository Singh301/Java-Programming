package com.java.basic;

import java.util.Scanner;

/**
 * ============================================================
 * 8. COUNT VOWELS AND CONSONANTS
 * ============================================================
 *
 * WHAT ARE VOWELS AND CONSONANTS?
 * -------------------------------
 * In English alphabet:
 *   Vowels     : a, e, i, o, u  (and sometimes y)
 *   Consonants : all other letters (b, c, d, f, ...)
 *
 * We usually ignore case (treat 'A' and 'a' the same)
 * and ignore non-letter characters (spaces, digits, symbols).
 *
 * This program teaches:
 * - Character checking
 * - Loops over strings
 * - Conditional statements
 */
public class CountVowelsConsonants {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void countWithoutMethod() {
        String text = "Hello World";

        int vowels = 0;
        int consonants = 0;

        // Convert to lowercase so we don't worry about case
        String lower = text.toLowerCase();

        for (int i = 0; i < lower.length(); i++) {
            char ch = lower.charAt(i);

            // Check if it is a letter
            if (ch >= 'a' && ch <= 'z') {
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    vowels++;
                } else {
                    consonants++;
                }
            }
            // non-letters are ignored
        }

        System.out.println("Text        : " + text);
        System.out.println("Vowels      : " + vowels);
        System.out.println("Consonants  : " + consonants);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Counts vowels and consonants and prints the result.
     */
    public static void countVowelsAndConsonants(String text) {
        if (text == null) {
            System.out.println("Input is null");
            return;
        }

        int vowels = 0;
        int consonants = 0;

        for (char ch : text.toLowerCase().toCharArray()) {
            if (ch >= 'a' && ch <= 'z') {
                if ("aeiou".indexOf(ch) != -1) {   // nice short way
                    vowels++;
                } else {
                    consonants++;
                }
            }
        }

        System.out.println("Text        : " + text);
        System.out.println("Vowels      : " + vowels);
        System.out.println("Consonants  : " + consonants);
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void countWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        countVowelsAndConsonants(input);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        countWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        countVowelsAndConsonants("Java Programming is Fun");

        System.out.println("\n===== VERSION 3: With User Input =====");
        countWithUserInput();
    }
}
