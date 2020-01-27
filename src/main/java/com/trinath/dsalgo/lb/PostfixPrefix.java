package com.trinath.dsalgo.lb;

import java.util.*;

public class PostfixPrefix {
    static Set<String> operators = new HashSet<>();
    static Set<String> ignores = new HashSet<>();

    /**
     * This evaluates postfix ex expression to a value
     * Algo: There is one stack and one varable to hold current token When encounter a operand token , push to operand stack and when encounter a  operator token , pop two operands and evaluate and push. Once all tokens are
     * finished check the stack size ==1(must be) and the last element in operand stack is the result
     * @param input postfix expression
     * @return value
     */
    static int evaluatePostfix(String input){
        int result = Integer.MIN_VALUE;
        Stack<Integer> operands = new Stack();
        String token="";
        String[] tokens = input.split(" ");
        try {
            for (int i = 0; i < tokens.length; i++) {
                token = tokens[i].trim();
                if (ignores.contains(token)) {
                    continue;
                }
                if (operators.contains(token) && !operands.isEmpty()) {
                    operands.push(evaluate(operands.pop(), operands.pop(), token));
                } else {
                    operands.push(Integer.valueOf(token));
                }
            }
            if(operands.size()==1){
                result =  operands.pop();
            }
            else{
                throw new Exception("Something worng in the expression"+operands);
            }
        }
        catch (Exception e){
            System.out.println("Not a valid expression: "+input+" : "+e);
        }
        return result;

    }

    static String infixToPostfix(String postfix){
        String infix ="";

       return infix;
    }

    static int evaluate(int top, int nextTop, String operator){
        switch(operator){
            case "/":
                return  nextTop / top;
            case "*":
                return  nextTop * top;
            case "+":
                return  nextTop + top;
            case "-":
                return  nextTop - top;
        }

        return 0;
    }

    public static void main(String args[]){
        operators.add("/");
        operators.add("*");
        operators.add("+");
        operators.add("-");
        ignores.add("(");
        ignores.add(")");
        ignores.add(" ");

        System.out.println(evaluatePostfix("5 10 2 * + 3 -"));
        System.out.println(evaluatePostfix("2 3 1 * + 9 -"));
    }
}
