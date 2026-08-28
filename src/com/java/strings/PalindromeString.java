package com.java.strings;

import java.util.Scanner;

/**
 * ============================================================
 * 36. CHECK IF A STRING IS A PALINDROME
 * ============================================================
 *
 * CONCEPT:
 * A string is a palindrome if it reads the same forwards and backwards.
 *
 * Examples:
 *   "madam"   → Palindrome
 *   "racecar" → Palindrome
 *   "hello"   → Not Palindrome
 *
 * We usually ignore case and spaces for a fair check.
 *
 * Approach: Two-pointer technique
 * Compare characters from both ends moving towards the center.
 */
public class PalindromeString {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void checkPalindromeWithoutMethod() {
        String original = "Madam";
        String str = original.toLowerCase().replaceAll("\\s+", "");

        int left = 0;
        int right = str.length() - 1;
        boolean isPalindrome = true;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                isPalindrome = false;
                break;
            }
            left++;
            right--;
        }

        if (isPalindrome) {
            System.out.println("\"" + original + "\" is a Palindrome");
        } else {
            System.out.println("\"" + original + "\" is NOT a Palindrome");
        }
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Checks whether the string is a palindrome.
     * Ignores case and spaces.
     */
    public static boolean isPalindrome(String str) {
        if (str == null) return false;

        String cleaned = str.toLowerCase().replaceAll("\\s+", "");
        int left = 0;
        int right = cleaned.length() - 1;

        while (left < right) {
            if (cleaned.charAt(left) != cleaned.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void checkPalindromeWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        if (isPalindrome(input)) {
            System.out.println("\"" + input + "\" is a Palindrome");
        } else {
            System.out.println("\"" + input + "\" is NOT a Palindrome");
        }
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        checkPalindromeWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        System.out.println("madam   → " + isPalindrome("madam"));
        System.out.println("racecar → " + isPalindrome("racecar"));
        System.out.println("hello   → " + isPalindrome("hello"));
        System.out.println("A man a plan a canal Panama → " +
                isPalindrome("A man a plan a canal Panama"));

        System.out.println("\n===== VERSION 3: With User Input =====");
        checkPalindromeWithUserInput();
    }
}
