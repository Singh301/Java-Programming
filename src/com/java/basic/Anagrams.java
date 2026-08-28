package com.java.basic;

import java.util.Arrays;
import java.util.Scanner;

/**
 * ============================================================
 * 18. ANAGRAMS
 * ============================================================
 *
 * WHAT ARE ANAGRAMS?
 * ------------------
 * Two strings are anagrams of each other if they contain the
 * same characters with the same frequencies, but possibly in
 * different order.
 *
 * Examples:
 *   "listen" and "silent"     → Anagrams
 *   "triangle" and "integral" → Anagrams
 *   "hello" and "world"       → Not Anagrams
 *   "Debit Card" and "Bad Credit" → Anagrams (ignoring spaces & case)
 *
 * Common ways to check:
 * 1. Sort both strings and compare
 * 2. Count frequency of each character (using array or map)
 */
public class Anagrams {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void checkAnagramWithoutMethod() {
        String str1 = "listen";
        String str2 = "silent";

        // Convert to lowercase and remove spaces for fair comparison
        String s1 = str1.toLowerCase().replaceAll("\\s+", "");
        String s2 = str2.toLowerCase().replaceAll("\\s+", "");

        boolean isAnagram = false;

        if (s1.length() == s2.length()) {
            // Convert to char arrays and sort them
            char[] arr1 = s1.toCharArray();
            char[] arr2 = s2.toCharArray();

            Arrays.sort(arr1);
            Arrays.sort(arr2);

            // If sorted arrays are equal → anagrams
            isAnagram = Arrays.equals(arr1, arr2);
        }

        if (isAnagram) {
            System.out.println("\"" + str1 + "\" and \"" + str2 + "\" are Anagrams");
        } else {
            System.out.println("\"" + str1 + "\" and \"" + str2 + "\" are NOT Anagrams");
        }
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Checks if two strings are anagrams.
     * Ignores case and spaces.
     */
    public static boolean areAnagrams(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return false;
        }

        // Clean the strings
        String s1 = str1.toLowerCase().replaceAll("\\s+", "");
        String s2 = str2.toLowerCase().replaceAll("\\s+", "");

        if (s1.length() != s2.length()) {
            return false;
        }

        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        return Arrays.equals(arr1, arr2);
    }

    /**
     * Alternative method using character frequency count.
     * More efficient for very long strings (O(n) time).
     */
    public static boolean areAnagramsFrequency(String str1, String str2) {
        if (str1 == null || str2 == null) return false;

        String s1 = str1.toLowerCase().replaceAll("\\s+", "");
        String s2 = str2.toLowerCase().replaceAll("\\s+", "");

        if (s1.length() != s2.length()) return false;

        int[] count = new int[26];   // for a-z

        for (int i = 0; i < s1.length(); i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }

        for (int c : count) {
            if (c != 0) return false;
        }
        return true;
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void checkAnagramWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first string : ");
        String str1 = sc.nextLine();
        System.out.print("Enter second string: ");
        String str2 = sc.nextLine();

        if (areAnagrams(str1, str2)) {
            System.out.println("They are Anagrams");
        } else {
            System.out.println("They are NOT Anagrams");
        }
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        checkAnagramWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        System.out.println("listen & silent → " + areAnagrams("listen", "silent"));
        System.out.println("hello & world  → " + areAnagrams("hello", "world"));
        System.out.println("Debit Card & Bad Credit → " + areAnagrams("Debit Card", "Bad Credit"));

        System.out.println("\n===== VERSION 3: With User Input =====");
        checkAnagramWithUserInput();
    }
}
