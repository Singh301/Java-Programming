package com.java.basic;

import java.util.Scanner;

/**
 * ============================================================
 * 1. REVERSE A STRING
 * ============================================================
 *
 * WHAT IS STRING REVERSAL?
 * ------------------------
 * Reversing a string means arranging its characters in the opposite order.
 * Example:
 *   Input  : "hello"
 *   Output : "olleh"
 *
 * WHY IS THIS IMPORTANT?
 * ----------------------
 * - Helps understand String, char arrays, and loops
 * - Foundation for palindrome checking, string manipulation, etc.
 * - Common interview question
 *
 * APPROACHES WE WILL COVER:
 * 1. Without using any method (all logic inside main)
 * 2. Using a separate method
 * 3. Taking input from the user
 */
public class ReverseString {

    // ============================================================
    // VERSION 1: WITHOUT METHOD (Everything inside main)
    // ============================================================
    public static void reverseWithoutMethod() {
        String original = "hello";

        // Convert the string into a character array
        // because strings in Java are immutable (cannot be changed)
        char[] chars = original.toCharArray();

        // Two pointers technique
        int left = 0;                       // starts from beginning
        int right = chars.length - 1;       // starts from end

        // Keep swapping characters until left and right meet
        while (left < right) {
            // Temporary variable to hold one character during swap
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;

            // Move the pointers towards the center
            left++;
            right--;
        }

        // Create a new String from the reversed character array
        String reversed = new String(chars);

        System.out.println("Original : " + original);
        System.out.println("Reversed : " + reversed);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * This method takes a string and returns its reverse.
     * Using a method makes the code reusable and clean.
     */
    public static String reverse(String input) {
        // Handle null or empty string to avoid errors
        if (input == null || input.isEmpty()) {
            return input;
        }

        char[] chars = input.toCharArray();
        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            // Swap characters
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;

            left++;
            right--;
        }

        return new String(chars);
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void reverseWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string to reverse: ");
        // nextLine() reads the full line including spaces
        String userInput = sc.nextLine();

        String result = reverse(userInput);   // reusing the method

        System.out.println("Reversed string: " + result);

        // Note: We do not close Scanner here if System.in is used elsewhere
    }

    // ============================================================
    // MAIN METHOD - Run any version you want
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        reverseWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        String example = "Java Programming";
        System.out.println("Original : " + example);
        System.out.println("Reversed : " + reverse(example));

        System.out.println("\n===== VERSION 3: With User Input =====");
        reverseWithUserInput();
    }
}
