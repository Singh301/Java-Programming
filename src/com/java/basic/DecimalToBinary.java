package com.java.basic;

import java.util.Scanner;

/**
 * ============================================================
 * 16. CONVERT DECIMAL TO BINARY
 * ============================================================
 *
 * WHAT IS DECIMAL AND BINARY?
 * ---------------------------
 * - Decimal (Base 10): Uses digits 0-9 (normal numbers we use)
 * - Binary  (Base 2) : Uses only 0 and 1
 *
 * Computers store everything in binary.
 *
 * HOW TO CONVERT DECIMAL → BINARY:
 * Repeatedly divide the number by 2 and record the remainders.
 * The remainders in reverse order form the binary number.
 *
 * Example: 13
 *   13 ÷ 2 = 6 remainder 1
 *    6 ÷ 2 = 3 remainder 0
 *    3 ÷ 2 = 1 remainder 1
 *    1 ÷ 2 = 0 remainder 1
 *   Binary (read remainders bottom to top) = 1101
 *
 * Java also provides Integer.toBinaryString() for quick conversion.
 */
public class DecimalToBinary {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void convertWithoutMethod() {
        int decimal = 13;
        int number = decimal;

        // We will build the binary string by collecting remainders
        String binary = "";

        if (number == 0) {
            binary = "0";
        } else {
            while (number > 0) {
                int remainder = number % 2;
                // Add remainder in front because we get remainders from bottom
                binary = remainder + binary;
                number = number / 2;
            }
        }

        System.out.println("Decimal : " + decimal);
        System.out.println("Binary  : " + binary);
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Converts a decimal number to its binary representation (as String).
     */
    public static String toBinary(int decimal) {
        if (decimal == 0) {
            return "0";
        }

        // Handle negative numbers (simple approach: convert absolute value)
        boolean isNegative = decimal < 0;
        decimal = Math.abs(decimal);

        StringBuilder binary = new StringBuilder();

        while (decimal > 0) {
            binary.insert(0, decimal % 2);  // insert at beginning
            decimal = decimal / 2;
        }

        if (isNegative) {
            return "-" + binary.toString();
        }
        return binary.toString();
    }

    /**
     * Using built-in Java method (for comparison / quick use)
     */
    public static String toBinaryBuiltIn(int decimal) {
        return Integer.toBinaryString(decimal);
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void convertWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a decimal number: ");
        int decimal = sc.nextInt();

        System.out.println("Binary (manual)   : " + toBinary(decimal));
        System.out.println("Binary (built-in) : " + toBinaryBuiltIn(decimal));
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        convertWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        System.out.println("13 in binary = " + toBinary(13));
        System.out.println("25 in binary = " + toBinary(25));
        System.out.println("0 in binary  = " + toBinary(0));

        System.out.println("\n===== VERSION 3: With User Input =====");
        convertWithUserInput();
    }
}
