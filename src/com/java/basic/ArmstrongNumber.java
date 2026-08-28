package com.java.basic;

import java.util.Scanner;

/**
 * ============================================================
 * 6. ARMSTRONG NUMBER
 * ============================================================
 *
 * WHAT IS AN ARMSTRONG NUMBER?
 * ----------------------------
 * An Armstrong number (also called narcissistic number) is a
 * number that is equal to the sum of its own digits each raised
 * to the power of the number of digits.
 *
 * Examples:
 *   153  → 1³ + 5³ + 3³ = 1 + 125 + 27 = 153   → Armstrong
 *   370  → 3³ + 7³ + 0³ = 27 + 343 + 0 = 370   → Armstrong
 *   371  → 3³ + 7³ + 1³ = 27 + 343 + 1 = 371   → Armstrong
 *   407  → 4³ + 0³ + 7³ = 64 + 0 + 343 = 407   → Armstrong
 *   1634 → 1⁴ + 6⁴ + 3⁴ + 4⁴ = 1 + 1296 + 81 + 256 = 1634
 *
 * 0, 1, 2, ..., 9 are also Armstrong numbers (single digit).
 */
public class ArmstrongNumber {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void checkArmstrongWithoutMethod() {
        int number = 153;
        int original = number;

        // Count number of digits
        int digits = 0;
        int temp = number;
        while (temp > 0) {
            digits++;
            temp = temp / 10;
        }

        // Calculate sum of each digit raised to power of digits
        int sum = 0;
        temp = number;
        while (temp > 0) {
            int digit = temp % 10;
            sum += Math.pow(digit, digits);   // digit^digits
            temp = temp / 10;
        }

        if (sum == original) {
            System.out.println(number + " is an Armstrong Number");
        } else {
            System.out.println(number + " is NOT an Armstrong Number");
        }
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Returns true if the number is an Armstrong number.
     */
    public static boolean isArmstrong(int number) {
        if (number < 0) {
            return false;
        }

        int original = number;
        int digits = String.valueOf(number).length();  // easy way to count digits

        int sum = 0;
        while (number > 0) {
            int digit = number % 10;
            sum += Math.pow(digit, digits);
            number /= 10;
        }

        return sum == original;
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void checkArmstrongWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number to check Armstrong: ");
        int number = sc.nextInt();

        if (isArmstrong(number)) {
            System.out.println(number + " is an Armstrong Number");
        } else {
            System.out.println(number + " is NOT an Armstrong Number");
        }
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        checkArmstrongWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        int[] tests = {153, 370, 371, 407, 123, 1634};
        for (int n : tests) {
            System.out.println(n + " → " + (isArmstrong(n) ? "Armstrong" : "Not Armstrong"));
        }

        System.out.println("\n===== VERSION 3: With User Input =====");
        checkArmstrongWithUserInput();
    }
}
