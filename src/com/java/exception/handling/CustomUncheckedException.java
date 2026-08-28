package com.java.exception.handling;

import java.util.Scanner;

/**
 * ============================================================
 * 87. CUSTOM UNCHECKED EXCEPTION
 * ============================================================
 *
 * CONCEPT:
 * Unchecked exceptions extend RuntimeException.
 * They do not need to be declared in throws clause
 * and compiler does not force you to handle them.
 *
 * Use when:
 * - The error is due to programming mistake
 * - Recovery is usually not possible / not expected
 * - You don't want to force callers to catch it
 *
 * Example: InvalidProductException, InsufficientBalanceException etc.
 */
public class CustomUncheckedException {

    // Custom Unchecked Exception
    static class InsufficientBalanceException extends RuntimeException {
        public InsufficientBalanceException(String message) {
            super(message);
        }
    }

    static class BankAccount {
        private double balance;

        public BankAccount(double balance) {
            this.balance = balance;
        }

        public void withdraw(double amount) {
            if (amount > balance) {
                throw new InsufficientBalanceException(
                        "Insufficient balance! Available: " + balance + ", Requested: " + amount);
            }
            balance -= amount;
            System.out.println("Withdrawn: " + amount + " | Remaining balance: " + balance);
        }

        public double getBalance() {
            return balance;
        }
    }

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void demoWithoutMethod() {
        BankAccount account = new BankAccount(1000);

        try {
            account.withdraw(500);
            account.withdraw(600);  // will throw
        } catch (InsufficientBalanceException e) {
            System.out.println("Caught unchecked exception: " + e.getMessage());
        }
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    public static void performWithdrawal(BankAccount account, double amount) {
        try {
            account.withdraw(amount);
        } catch (InsufficientBalanceException e) {
            System.out.println("Transaction failed: " + e.getMessage());
        }
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void demoWithUserInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter initial balance: ");
        double balance = sc.nextDouble();
        BankAccount account = new BankAccount(balance);

        System.out.print("Enter amount to withdraw: ");
        double amount = sc.nextDouble();

        performWithdrawal(account, amount);
        System.out.println("Current balance: " + account.getBalance());
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        demoWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        BankAccount acc = new BankAccount(2000);
        performWithdrawal(acc, 1500);
        performWithdrawal(acc, 1000);

        System.out.println("\n===== VERSION 3: With User Input =====");
        demoWithUserInput();
    }
}
