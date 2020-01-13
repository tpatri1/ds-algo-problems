package com.trinath.dsalgo.self;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
/*
roblem def
https://www.hackerrank.com/challenges/ctci-balanced-brackets/problem
 */
public class BalancedParanthesis {
    public static boolean isBalanced(String expression) {
        Map<Character, Character> map = new HashMap<>();
        map.put('}', '{');
        map.put(']', '[');
        map.put(')', '(');
        Stack<Character> stack = new Stack<Character>();
        for (char c : expression.toCharArray()) {
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;

                } else {
                    char top = stack.pop();
                    if (c != ']' && top == '[' || c != ')' && top == '(' || c != '}' && top == '{') {
                        return false;

                    }

                }
            }
        }
        return stack.isEmpty() ? true : false;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            String expression = in.next();
            System.out.println( (isBalanced(expression)) ? "YES" : "NO" );


        }

    }
}
