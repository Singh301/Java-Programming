package com.java.basic;

import java.util.Scanner;

/**
 * ============================================================
 * 4. PALINDROME
 * ============================================================
 *
 * WHAT IS A PALINDROME?
 * ---------------------
 * A palindrome is a word, phrase, number, or sequence that
 * reads the same forwards and backwards.
 *
 * Examples:
 *   Strings : "madam", "racecar", "level", "kayak"
 *   Numbers : 121, 3443, 12321
 *
 * Note: Usually we ignore case and spaces when checking strings,
 * but in basic programs we often check exact characters.
 *
 * HOW TO CHECK:
 * Compare the original string/number with its reverse.
 * If both are equal → it is a palindrome.
 */
public class Palindrome {

    // ============================================================
    // VERSION 1: WITHOUT METHOD (String)
    // ============================================================
    public static void checkPalindromeWithoutMethod() {
        String original = "madam";

        // Convert to lowercase for case-insensitive check (optional)
        String str = original.toLowerCase();

        // Reverse the string using two-pointer technique
        char[] chars = str.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        boolean isPalindrome = true;

        while (left < right) {
            if (chars[left] != chars[right]) {
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
     * Checks whether a given string is a palindrome.
     * Ignores case by converting to lowercase.
     */
    public static boolean isPalindrome(String input) {
        if (input == null) {
            return false;
        }

        // Remove spaces and convert to lowercase for better checking
        String cleaned = input.replaceAll("\\s+", "").toLowerCase();

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

    /**
     * Checks whether a given number is a palindrome.
     */
    public static boolean isPalindromeNumber(int number) {
        // Negative numbers are not considered palindromes
        if (number < 0) {
            return false;
        }

        int original = number;
        int reversed = 0;

        while (number > 0) {
            int digit = number % 10;          // extract last digit
            reversed = reversed * 10 + digit; // build reverse
            number = number / 10;             // remove last digit
        }

        return original == reversed;
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void checkPalindromeWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string to check palindrome: ");
        String str = sc.nextLine();

        if (isPalindrome(str)) {
            System.out.println("\"" + str + "\" is a Palindrome");
        } else {
            System.out.println("\"" + str + "\" is NOT a Palindrome");
        }
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        checkPalindromeWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        System.out.println("madam → " + isPalindrome("madam"));
        System.out.println("Hello → " + isPalindrome("Hello"));
        System.out.println("121 → " + isPalindromeNumber(121));
        System.out.println("123 → " + isPalindromeNumber(123));

        System.out.println("\n===== VERSION 3: With User Input =====");
        checkPalindromeWithUserInput();
    }
}
