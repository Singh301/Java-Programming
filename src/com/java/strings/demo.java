package com.java.strings;

import java.util.Scanner;

public class demo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the String : ");
        String name = sc.next();
        System.out.println("Reverse String : "+ reverseString(name));
    }

    static String reverseString(String input) {
        char [] chars = input.toCharArray();
        int left = 0 , right = chars.length-1;
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left ++;
            right--;
        }
      return new String(chars);
    }
}
