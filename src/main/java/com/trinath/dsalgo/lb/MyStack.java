package com.trinath.dsalgo.lb;

import java.util.Stack;

public class MyStack {
    static int[] arr = new int[100];
    static int top =-1;


    static void push(int i) throws Exception{
        if(isFull()) throw new Exception("Stack is full");
        arr[++top] = i;
    }
    static int pop() throws Exception{
        int result = peek();
        top--;
        return result;

    }

    static int peek() throws Exception{
        if(!isEmpty()) {
            return arr[top];
        }
        else throw new Exception("Stack is empty");
    }

    static boolean isEmpty(){
        if(top==-1){
            return true;
        }
        else{
            return false;
        }
    }

    static boolean isFull(){
        return top==99;
    }

    static void printStack(){
        for(int i=0; i<=top; i++){
            System.out.println(arr[i]);
        }
    }

    public static void main(String args[]) throws Exception {
        push(100);
        push(200);
        push(300);
        printStack();
        System.out.println("peek .."+peek());
        pop();
        printStack();


    }
//    static Stack reverse(Stack<Integer> stack){
//        int a = (int) stack.pop();
//        //reverse_helper(stack, a);
//    }


}
