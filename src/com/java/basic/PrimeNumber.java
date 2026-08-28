package com.java.basic;

import java.util.Scanner;

/**
 * ============================================================
 * 2. PRIME NUMBER
 * ============================================================
 *
 * WHAT IS A PRIME NUMBER?
 * -----------------------
 * A prime number is a natural number greater than 1 that has
 * no positive divisors other than 1 and itself.
 *
 * Examples of prime numbers: 2, 3, 5, 7, 11, 13, 17, 19, 23...
 * Examples of non-prime (composite): 4, 6, 8, 9, 10, 12, 15...
 *
 * IMPORTANT NOTES:
 * - 1 is NOT a prime number
 * - 2 is the only even prime number
 * - All other even numbers are divisible by 2, so not prime
 *
 * HOW TO CHECK:
 * We try to divide the number by all integers from 2 up to
 * the square root of the number. If any divides it evenly,
 * it is not prime.
 */
public class PrimeNumber {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void checkPrimeWithoutMethod() {
        int number = 17;

        boolean isPrime = true;

        // Numbers less than or equal to 1 are not prime
        if (number <= 1) {
            isPrime = false;
        } else {
            // We only need to check up to sqrt(number)
            // because if n = a * b, then one of them is <= sqrt(n)
            for (int i = 2; i * i <= number; i++) {
                if (number % i == 0) {
                    // Found a divisor → not prime
                    isPrime = false;
                    break;   // no need to check further
                }
            }
        }

        if (isPrime) {
            System.out.println(number + " is a Prime Number");
        } else {
            System.out.println(number + " is NOT a Prime Number");
        }
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Returns true if the given number is prime, false otherwise.
     */
    public static boolean isPrime(int number) {
        // Edge cases
        if (number <= 1) {
            return false;
        }
        if (number == 2) {
            return true;          // 2 is the only even prime
        }
        if (number % 2 == 0) {
            return false;         // all other even numbers are not prime
        }

        // Check only odd numbers up to sqrt(number)
        for (int i = 3; i * i <= number; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void checkPrimeWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number to check if it is prime: ");
        int number = sc.nextInt();

        if (isPrime(number)) {
            System.out.println(number + " is a Prime Number");
        } else {
            System.out.println(number + " is NOT a Prime Number");
        }
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        checkPrimeWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        int[] testNumbers = {1, 2, 4, 17, 25, 29};
        for (int n : testNumbers) {
            System.out.println(n + " → " + (isPrime(n) ? "Prime" : "Not Prime"));
        }

        System.out.println("\n===== VERSION 3: With User Input =====");
        checkPrimeWithUserInput();
    }
}
