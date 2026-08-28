package com.java.control.flow;

import java.util.Scanner;

/**
 * ============================================================
 * 12. PRINT ALL PRIME NUMBERS FROM 1 TO N
 * ============================================================
 *
 * CONCEPT:
 * A prime number is greater than 1 and has no divisors other
 * than 1 and itself.
 *
 * We need to check every number from 2 to n and print only
 * those that are prime.
 *
 * CONTROL FLOW USED:
 * - Outer for loop  → goes from 2 to n
 * - Inner for loop  → checks if the number has any divisor
 * - if + break      → stops early if a divisor is found
 *
 * Example (n = 20):
 *   2 3 5 7 11 13 17 19
 */
public class PrimeNumbersFrom1ToN {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void printPrimesWithoutMethod() {
        int n = 20;

        System.out.print("Prime numbers from 1 to " + n + ": ");

        for (int num = 2; num <= n; num++) {
            boolean isPrime = true;

            // Check if num is divisible by any number from 2 to sqrt(num)
            for (int i = 2; i * i <= num; i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break;          // no need to check further
                }
            }

            if (isPrime) {
                System.out.print(num + " ");
            }
        }
        System.out.println();
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Helper method: returns true if the number is prime.
     */
    public static boolean isPrime(int num) {
        if (num <= 1) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;

        for (int i = 3; i * i <= num; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Prints all prime numbers from 1 to n.
     */
    public static void printPrimes(int n) {
        System.out.print("Prime numbers from 1 to " + n + ": ");
        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void printPrimesWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the value of n: ");
        int n = sc.nextInt();

        if (n < 2) {
            System.out.println("There are no prime numbers less than 2.");
            return;
        }

        printPrimes(n);
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        printPrimesWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        printPrimes(30);

        System.out.println("\n===== VERSION 3: With User Input =====");
        printPrimesWithUserInput();
    }
}
