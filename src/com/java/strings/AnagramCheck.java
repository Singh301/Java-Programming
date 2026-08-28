package com.java.strings;

import java.util.Arrays;
import java.util.Scanner;

/**
 * ============================================================
 * 31. CHECK IF TWO STRINGS ARE ANAGRAMS
 * ============================================================
 *
 * CONCEPT:
 * Two strings are anagrams if they contain the same characters
 * with the same frequency, but possibly in different order.
 *
 * Examples:
 *   "listen" & "silent"     → Anagrams
 *   "triangle" & "integral" → Anagrams
 *   "hello" & "world"       → Not Anagrams
 *
 * Approaches:
 * 1. Sort both strings and compare
 * 2. Count character frequency
 */
public class AnagramCheck {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void checkAnagramWithoutMethod() {
        String str1 = "listen";
        String str2 = "silent";

        String s1 = str1.toLowerCase().replaceAll("\\s+", "");
        String s2 = str2.toLowerCase().replaceAll("\\s+", "");

        boolean isAnagram = false;

        if (s1.length() == s2.length()) {
            char[] arr1 = s1.toCharArray();
            char[] arr2 = s2.toCharArray();
            Arrays.sort(arr1);
            Arrays.sort(arr2);
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
     * Checks if two strings are anagrams (ignores case and spaces).
     */
    public static boolean areAnagrams(String str1, String str2) {
        if (str1 == null || str2 == null) return false;

        String s1 = str1.toLowerCase().replaceAll("\\s+", "");
        String s2 = str2.toLowerCase().replaceAll("\\s+", "");

        if (s1.length() != s2.length()) return false;

        char[] a1 = s1.toCharArray();
        char[] a2 = s2.toCharArray();
        Arrays.sort(a1);
        Arrays.sort(a2);

        return Arrays.equals(a1, a2);
    }

    /**
     * Frequency count approach (O(n) time).
     */
    public static boolean areAnagramsFrequency(String str1, String str2) {
        if (str1 == null || str2 == null) return false;

        String s1 = str1.toLowerCase().replaceAll("\\s+", "");
        String s2 = str2.toLowerCase().replaceAll("\\s+", "");

        if (s1.length() != s2.length()) return false;

        int[] count = new int[26];
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

        System.out.println("\n===== VERSION 3: With User Input =====");
        checkAnagramWithUserInput();
    }
}
