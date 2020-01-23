package com.trinath.dsalgo.lb;

import java.util.Stack;

public class MyDynamicStack {
    static int[] arr = new int[100];
    static int top =-1;

    static void increaseSize(){
        int[] arr1 = new int[arr.length * 2];
        for(int i=0; i<arr.length; i++){
            arr1[i] = arr[i];
        }
        arr =  arr1;
    }

    static void push(int i) throws Exception{
        if(isFull()) {
            increaseSize();
        }
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
        return top==arr.length-1;
    }

    static void printStack(){
        for(int i=0; i<=top; i++){
            System.out.println(arr[i]);
        }
    }

    public static void main(String args[]) throws Exception {
        for(int i=0; i<200; i++){
            push(i);
        }
        push(100);
        push(200);
        push(300);
        printStack();
        System.out.println("size .."+arr.length);
        pop();
        printStack();


    }
//    static Stack reverse(Stack<Integer> stack){
//        int a = (int) stack.pop();
//        //reverse_helper(stack, a);
//    }


}
