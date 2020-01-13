package com.trinath.dsalgo.self;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class QueueUsingStacks {
    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<Integer>();

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            int operation = scan.nextInt();
            if (operation == 1) { // enqueue
                queue.enqueue(scan.nextInt());
            } else if (operation == 2) { // dequeue
                queue.dequeue();
            } else if (operation == 3) { // print/peek
                System.out.println(queue.peek());
            }
        }
        scan.close();
    }

    public static class MyQueue<T> {
        public MyQueue(T t) {
            endStack = new Stack<T>();
            frontStack = new Stack<T>();
        }
        public MyQueue() {
            endStack = new Stack<T>();
            frontStack = new Stack<T>();
        }

        private Stack<T> endStack;
        private Stack<T> frontStack;

        public void enqueue(T t) {

            endStack.push(t);

        }


        public void dequeue() {
            // If frontstack is not empty move items from end stack to front stack and pop else just pop
            if(frontStack.isEmpty()){
                moveFromEndToFrontStack();
            }
        }

        public String  peek(){
            return String.valueOf(frontStack.peek());
        }


    private void moveFromEndToFrontStack(){
        while(!endStack.isEmpty()){
            frontStack.push(endStack.pop());
        }
    }

}
}