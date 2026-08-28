package com.java.strings;

import java.util.Scanner;

/**
 * ============================================================
 * 38. REVERSE WORDS IN A SENTENCE
 * ============================================================
 *
 * CONCEPT:
 * Reverse the order of words in a sentence (not the characters).
 *
 * Example:
 *   Input  : "Java is fun"
 *   Output : "fun is Java"
 *
 * Steps:
 * 1. Split the sentence into words
 * 2. Reverse the array of words
 * 3. Join them back with spaces
 */
public class ReverseWords {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void reverseWordsWithoutMethod() {
        String sentence = "Java is fun to learn";

        String[] words = sentence.trim().split("\\s+");

        // Reverse the words array
        int left = 0;
        int right = words.length - 1;
        while (left < right) {
            String temp = words[left];
            words[left] = words[right];
            words[right] = temp;
            left++;
            right--;
        }

        // Join the words
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            result.append(words[i]);
            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        System.out.println("Original : " + sentence);
        System.out.println("Reversed : " + result.toString());
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Reverses the order of words in the given sentence.
     */
    public static String reverseWords(String sentence) {
        if (sentence == null || sentence.trim().isEmpty()) {
            return sentence;
        }

        String[] words = sentence.trim().split("\\s+");

        // Reverse the array
        int left = 0, right = words.length - 1;
        while (left < right) {
            String temp = words[left];
            words[left] = words[right];
            words[right] = temp;
            left++;
            right--;
        }

        return String.join(" ", words);
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void reverseWordsWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a sentence: ");
        String input = sc.nextLine();

        System.out.println("Reversed words: " + reverseWords(input));
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        reverseWordsWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        System.out.println(reverseWords("Hello World from Java"));
        System.out.println(reverseWords("one two three four"));

        System.out.println("\n===== VERSION 3: With User Input =====");
        reverseWordsWithUserInput();
    }
}
