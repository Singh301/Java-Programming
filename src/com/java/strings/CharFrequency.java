package com.java.strings;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * ============================================================
 * 32. COUNT FREQUENCY OF CHARACTERS IN A STRING
 * ============================================================
 *
 * CONCEPT:
 * Count how many times each character appears in a string.
 *
 * Example:
 *   Input  : "programming"
 *   Output : p=1, r=2, o=1, g=2, a=1, m=2, i=1, n=1
 *
 * Approaches:
 * 1. Using array of size 256 (ASCII)
 * 2. Using HashMap / LinkedHashMap (preserves order)
 */
public class CharFrequency {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void countFrequencyWithoutMethod() {
        String str = "programming";

        int[] freq = new int[256];   // supports all ASCII characters

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            freq[ch]++;
        }

        System.out.println("Character frequencies in \"" + str + "\":");
        for (int i = 0; i < 256; i++) {
            if (freq[i] > 0) {
                System.out.println((char) i + " → " + freq[i]);
            }
        }
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Prints frequency of each character using array.
     */
    public static void countFrequency(String str) {
        if (str == null || str.isEmpty()) {
            System.out.println("Empty string");
            return;
        }

        int[] freq = new int[256];
        for (char ch : str.toCharArray()) {
            freq[ch]++;
        }

        System.out.println("Frequencies:");
        for (int i = 0; i < 256; i++) {
            if (freq[i] > 0) {
                System.out.println((char) i + " → " + freq[i]);
            }
        }
    }

    /**
     * Returns a map of character frequencies (preserves insertion order).
     */
    public static Map<Character, Integer> getFrequencyMap(String str) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        if (str == null) return map;

        for (char ch : str.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        return map;
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void countFrequencyWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        System.out.println("Frequency map: " + getFrequencyMap(input));
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        countFrequencyWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        countFrequency("hello");
        System.out.println("Map: " + getFrequencyMap("banana"));

        System.out.println("\n===== VERSION 3: With User Input =====");
        countFrequencyWithUserInput();
    }
}
