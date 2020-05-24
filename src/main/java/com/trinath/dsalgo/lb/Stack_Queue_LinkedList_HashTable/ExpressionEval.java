package com.trinath.dsalgo.lb.Stack_Queue_LinkedList_HashTable;

import java.util.*;

public class ExpressionEval {
    //Postfix expression is the input
    static double evaluatePostFix(String expr) {
        //TODO: Write - Your - Code
        //O(n) memory and time --
        List<String> tokens = getTokens(expr);
        Stack<String> operand = new Stack<String>();
        Queue<Character> operator = new LinkedList<Character>();// the operator will be operated from left to right but data right to left, draw a binary tree to understand

        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).equals("+") || tokens.get(i).equals("-") || tokens.get(i).equals("*") || tokens.get(i).equals("/")) {
                operator.add(tokens.get(i).charAt(0));
            } else {
                operand.push(tokens.get(i));
            }
        }

        //Process
        while (!operator.isEmpty()) {// we can replace this with just another for loop
            char op = operator.remove();
            evalNPush(operand, op);

        }

        double res = Double.valueOf(operand.pop());
        return res;
    }

    private static void evalNPush(Stack<String> operand, char op) {
        double right = Double.valueOf(operand.pop());
        double left = Double.valueOf(operand.pop());

        if (op == '+') operand.push(left + right + "");
        if (op == '*') operand.push(left * right + "");
        if (op == '-') operand.push(left - right + "");
        if (op == '/') operand.push(left / right + "");
    }

    static double evaluateInfix(String expr){
        List<String> tokens = getTokens(expr);
        Stack<String> operand = new Stack<String>();
        Stack<Character> operator = new Stack<>();
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).equals("+") || tokens.get(i).equals("-") || tokens.get(i).equals("*") || tokens.get(i).equals("/")) {
                if(!operator.isEmpty() && isGreaterPrecedence(tokens.get(i).charAt(0),operator.peek())){
                    evalNPush(operand, operator.pop());
                }else{
                    operator.push(tokens.get(i).charAt(0));
                }
                continue;
            }
            operand.push(tokens.get(i));
        }

        //evaluate rest
        while(!operator.isEmpty()){
            evalNPush(operand, operator.pop());
        }
        return Double.parseDouble(operand.pop());
    }
//same or higher precedence then return true
    private static boolean isGreaterPrecedence(char inputOp, char compareOp){
        if(compareOp=='+'||compareOp=='-'){
            if(inputOp=='+'||inputOp=='-'){
                return true;
            }
        }
        if(inputOp=='+'||inputOp=='-'){
            return true;
        }

        return false;
    }

    private static List<String> getTokens(String expr){
        List<String> tokens = new ArrayList<>();
        for(int i=0; i<expr.length(); i++){
            if(expr.charAt(i)=='+' || expr.charAt(i)=='-' || expr.charAt(i)=='*'||expr.charAt(i)=='/'){
                tokens.add(expr.charAt(i)+"");
            }
            else if(expr.charAt(i)>='0' &&expr.charAt(i)<='9'){
                if(i+1==expr.length()){//last char
                    tokens.add(expr.charAt(i)+"");
                }
                else if(expr.charAt(i+1)=='.'){
                    int start = i;
                    i++;
                    while(i<expr.length()){
                        i++;
                        if(expr.charAt(i)=='+' || expr.charAt(i)=='-' || expr.charAt(i)=='*'||expr.charAt(i)=='/'||expr.charAt(i)==' '){
                            tokens.add(expr.substring(start,i));
                            break;
                        }
                    }
                }
                else if(expr.charAt(i)==' '){
                    continue;
                }
                else{
                    tokens.add(expr.charAt(i)+"");
                }
            }
        }
        return tokens;
    }

    public static void main(String args[]){
        System.out.println(evaluatePostFix("3 2.45 8 / +"));
        System.out.println(getTokens("3 2.45 8 / +"));
        System.out.println(evaluateInfix("3 + 2.45 /8 "));
    }
}
