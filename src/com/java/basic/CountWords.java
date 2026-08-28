package com.java.basic;

import java.util.Scanner;

/**
 * ============================================================
 * 14. COUNT THE NUMBER OF WORDS IN A STRING
 * ============================================================
 *
 * TASK:
 * Count how many words are present in a given string.
 *
 * A word is usually defined as a sequence of characters
 * separated by one or more spaces.
 *
 * Examples:
 *   "Hello World"           → 2 words
 *   "  Java   is  fun  "    → 3 words
 *   "One"                   → 1 word
 *   "" or "   "             → 0 words
 *
 * We must carefully handle multiple spaces and leading/trailing spaces.
 */
public class CountWords {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void countWordsWithoutMethod() {
        String text = "  Java Programming is awesome  ";

        // Trim leading and trailing spaces
        String trimmed = text.trim();

        int wordCount = 0;

        if (!trimmed.isEmpty()) {
            // Split by one or more whitespace characters
            String[] words = trimmed.split("\\s+");
            wordCount = words.length;
        }

        System.out.println("Text       : \"" + text + "\"");
        System.out.println("Word Count : " + wordCount);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Counts the number of words in the given string.
     * Handles multiple spaces and empty strings correctly.
     */
    public static int countWords(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }

        // \\s+ means one or more whitespace characters
        String[] words = text.trim().split("\\s+");
        return words.length;
    }

    /**
     * Alternative implementation without using split()
     * (good for understanding the logic)
     */
    public static int countWordsManual(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }

        int count = 0;
        boolean inWord = false;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (ch != ' ' && ch != '\t' && ch != '\n') {
                if (!inWord) {
                    count++;      // starting a new word
                    inWord = true;
                }
            } else {
                inWord = false;   // we are in whitespace
            }
        }
        return count;
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void countWordsWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a sentence: ");
        String input = sc.nextLine();

        int count = countWords(input);
        System.out.println("Number of words: " + count);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        countWordsWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        System.out.println("Words in \"Hello World\" = " + countWords("Hello World"));
        System.out.println("Words in \"  a  b   c  \" = " + countWords("  a  b   c  "));
        System.out.println("Words (manual) in \"Java is fun\" = " + countWordsManual("Java is fun"));

        System.out.println("\n===== VERSION 3: With User Input =====");
        countWordsWithUserInput();
    }
}
