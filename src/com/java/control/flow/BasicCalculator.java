package com.java.control.flow;

import java.util.Scanner;

/**
 * ============================================================
 * 18. BASIC CALCULATOR USING SWITCH-CASE
 * ============================================================
 *
 * CONCEPT:
 * A simple calculator that performs +, -, *, / based on user choice.
 *
 * CONTROL FLOW FOCUS:
 * switch-case is perfect when we have multiple fixed options
 * (here the operators).
 *
 * switch (operator) {
 *     case '+': ... break;
 *     case '-': ... break;
 *     case '*': ... break;
 *     case '/': ... break;
 *     default:  ... 
 * }
 *
 * We also handle division by zero carefully.
 */
public class BasicCalculator {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void calculatorWithoutMethod() {
        double num1 = 20;
        double num2 = 5;
        char operator = '*';

        double result = 0;
        boolean valid = true;

        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    System.out.println("Error: Division by zero!");
                    valid = false;
                }
                break;
            default:
                System.out.println("Invalid operator!");
                valid = false;
        }

        if (valid) {
            System.out.println(num1 + " " + operator + " " + num2 + " = " + result);
        }
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Performs the calculation and returns the result.
     * Returns Double.NaN if operation is invalid.
     */
    public static double calculate(double num1, double num2, char operator) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 == 0) {
                    System.out.println("Error: Cannot divide by zero");
                    return Double.NaN;
                }
                return num1 / num2;
            case '%':
                if (num2 == 0) {
                    System.out.println("Error: Cannot modulo by zero");
                    return Double.NaN;
                }
                return num1 % num2;
            default:
                System.out.println("Invalid operator: " + operator);
                return Double.NaN;
        }
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void calculatorWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number : ");
        double num1 = sc.nextDouble();

        System.out.print("Enter an operator (+, -, *, /, %): ");
        char operator = sc.next().charAt(0);

        System.out.print("Enter second number: ");
        double num2 = sc.nextDouble();

        double result = calculate(num1, num2, operator);

        if (!Double.isNaN(result)) {
            System.out.println("Result: " + num1 + " " + operator + " " + num2 + " = " + result);
        }
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        calculatorWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        System.out.println("10 + 5 = " + calculate(10, 5, '+'));
        System.out.println("10 - 5 = " + calculate(10, 5, '-'));
        System.out.println("10 * 5 = " + calculate(10, 5, '*'));
        System.out.println("10 / 5 = " + calculate(10, 5, '/'));
        System.out.println("10 % 3 = " + calculate(10, 3, '%'));

        System.out.println("\n===== VERSION 3: With User Input =====");
        calculatorWithUserInput();
    }
}
