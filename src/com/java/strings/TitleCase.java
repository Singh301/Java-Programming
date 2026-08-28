package com.java.strings;

import java.util.Scanner;

/**
 * ============================================================
 * 35. CONVERT A STRING INTO TITLE CASE
 * ============================================================
 *
 * CONCEPT:
 * Title Case means the first letter of every word is capital
 * and the remaining letters are lowercase.
 *
 * Example:
 *   Input  : "java programming is fun"
 *   Output : "Java Programming Is Fun"
 *
 * Steps:
 * 1. Split the string into words
 * 2. Capitalize the first character of each word
 * 3. Make the rest of the word lowercase
 * 4. Join the words back with spaces
 */
public class TitleCase {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void toTitleCaseWithoutMethod() {
        String str = "java programming is fun";

        String[] words = str.trim().split("\\s+");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.length() > 0) {
                // First character uppercase + rest lowercase
                String titleWord = Character.toUpperCase(word.charAt(0))
                        + word.substring(1).toLowerCase();
                result.append(titleWord);
            }
            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        System.out.println("Original : " + str);
        System.out.println("Title    : " + result.toString());
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Converts the given string to Title Case.
     */
    public static String toTitleCase(String str) {
        if (str == null || str.trim().isEmpty()) {
            return str;
        }

        String[] words = str.trim().split("\\s+");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)));
                if (word.length() > 1) {
                    result.append(word.substring(1).toLowerCase());
                }
            }
            if (i < words.length - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void toTitleCaseWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a sentence: ");
        String input = sc.nextLine();

        System.out.println("Title Case: " + toTitleCase(input));
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        toTitleCaseWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        System.out.println(toTitleCase("hello world from java"));
        System.out.println(toTitleCase("tHIS iS a TeSt"));

        System.out.println("\n===== VERSION 3: With User Input =====");
        toTitleCaseWithUserInput();
    }
}
