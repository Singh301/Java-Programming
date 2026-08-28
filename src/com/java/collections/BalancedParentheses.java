package com.java.collections;

import java.util.Scanner;
import java.util.Stack;

/**
 * ============================================================
 * 45. CHECK BALANCED PARENTHESES USING STACK
 * ============================================================
 *
 * CONCEPT:
 * Check whether the brackets/parentheses in an expression
 * are balanced (properly opened and closed).
 *
 * Examples:
 *   "()"        → Balanced
 *   "()[]{}"    → Balanced
 *   "(]"        → Not Balanced
 *   "([)]"      → Not Balanced
 *   "{[]}"      → Balanced
 *
 * Algorithm using Stack:
 * - Push opening brackets
 * - When closing bracket comes, check if top of stack is matching opening
 * - At end stack should be empty
 */
public class BalancedParentheses {

    // ============================================================
    // VERSION 1: WITHOUT METHOD
    // ============================================================
    public static void checkWithoutMethod() {
        String expr = "{[()]}";
        Stack<Character> stack = new Stack<>();
        boolean balanced = true;

        for (int i = 0; i < expr.length(); i++) {
            char ch = expr.charAt(i);

            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else if (ch == ')' || ch == '}' || ch == ']') {
                if (stack.isEmpty()) {
                    balanced = false;
                    break;
                }
                char top = stack.pop();
                if ((ch == ')' && top != '(') ||
                    (ch == '}' && top != '{') ||
                    (ch == ']' && top != '[')) {
                    balanced = false;
                    break;
                }
            }
        }

        if (!stack.isEmpty()) balanced = false;

        System.out.println("Expression: " + expr);
        System.out.println(balanced ? "Balanced" : "Not Balanced");
    }

    // ============================================================
    // VERSION 2: WITH METHOD
    // ============================================================
    /**
     * Returns true if the expression has balanced parentheses.
     */
    public static boolean isBalanced(String expr) {
        if (expr == null) return false;

        Stack<Character> stack = new Stack<>();

        for (char ch : expr.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else if (ch == ')' || ch == '}' || ch == ']') {
                if (stack.isEmpty()) return false;

                char top = stack.pop();
                if ((ch == ')' && top != '(') ||
                    (ch == '}' && top != '{') ||
                    (ch == ']' && top != '[')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    // ============================================================
    // VERSION 3: WITH USER INPUT
    // ============================================================
    public static void checkWithUserInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter an expression: ");
        String expr = sc.nextLine();

        if (isBalanced(expr)) {
            System.out.println("Balanced");
        } else {
            System.out.println("Not Balanced");
        }
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("===== VERSION 1: Without Method =====");
        checkWithoutMethod();

        System.out.println("\n===== VERSION 2: With Method =====");
        String[] tests = {"()", "()[]{}", "(]", "([)]", "{[]}", "(((", "())"};
        for (String t : tests) {
            System.out.println(t + " → " + (isBalanced(t) ? "Balanced" : "Not Balanced"));
        }

        System.out.println("\n===== VERSION 3: With User Input =====");
        checkWithUserInput();
    }
}
