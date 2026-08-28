package com.java.strings;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * ============================================================
 * 33. LONGEST SUBSTRING WITHOUT REPEATING CHARACTERS
 * ============================================================
 *
 * CONCEPT:
 * Find the length of the longest substring that contains
 * all unique characters (no repeats).
 *
 * Example:
 *   "abcabcbb" → "abc" → length 3
 *   "bbbbb"    → "b"   → length 1
 *   "pwwkew"   → "wke" → length 3
 *
 * Efficient Approach: Sliding Window + HashSet
 * - Expand the window to the right
 * - When a duplicate is found, shrink from the left
 * - Keep track of maximum window size
 *
 * Time Complexity: O(n)
 */
public class LongestUniqueSubstring {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void findLongestWithoutMethod() {
        String s = "abcabcbb";

        Set<Character> set = new HashSet<>();
        int left = 0;
        int maxLength = 0;
        int startIndex = 0;

        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);

            // Shrink window from left until no duplicate
            while (set.contains(ch)) {
                set.remove(s.charAt(left));
                left++;
            }

            set.add(ch);

            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                startIndex = left;
            }
        }

        String longest = s.substring(startIndex, startIndex + maxLength);
        System.out.println("String          : " + s);
        System.out.println("Longest unique  : \"" + longest + "\"");
        System.out.println("Length          : " + maxLength);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Returns the length of the longest substring without repeating characters.
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) return 0;

        Set<Character> set = new HashSet<>();
        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(right));
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    /**
     * Returns the actual longest substring (not just length).
     */
    public static String longestUniqueSubstring(String s) {
        if (s == null || s.isEmpty()) return "";

        Set<Character> set = new HashSet<>();
        int left = 0, maxLen = 0, start = 0;

        for (int right = 0; right < s.length(); right++) {
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(right));
            if (right - left + 1 > maxLen) {
                maxLen = right - left + 1;
                start = left;
            }
        }
        return s.substring(start, start + maxLen);
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void findLongestWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        System.out.println("Longest unique substring: \"" + longestUniqueSubstring(input) + "\"");
        System.out.println("Length: " + lengthOfLongestSubstring(input));
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        findLongestWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        System.out.println("abcabcbb → " + longestUniqueSubstring("abcabcbb") +
                " (len=" + lengthOfLongestSubstring("abcabcbb") + ")");
        System.out.println("pwwkew   → " + longestUniqueSubstring("pwwkew") +
                " (len=" + lengthOfLongestSubstring("pwwkew") + ")");

        System.out.println("\n===== VERSION 3: With User Input =====");
        findLongestWithUserInput();
    }
}
