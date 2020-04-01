package com.trinath.dsalgo.lb.Stack_Queue_LinkedList_HashTable;

import java.util.Stack;

public class ReverseStack {

    //Reverse a stack using same stack and recursion - O(n2) mainly from insertItem
    static void  reverseStack(Stack<Integer> stack){
        if(stack.isEmpty()) return ;

            Integer item = stack.pop();
            reverseStack(stack); // This is a head recursion so pop all elements from 5, 4, 10, 2, 1 before callinng with first pop i.e : 1 to instert
            insertItem(stack, item); // calleds once everything is popped from last to first ; imagine the stack frame

    }

    static void insertItem(Stack stack,Integer item){

        if(stack.isEmpty()){
            stack.push(item); return;
        }
        else {
            Integer i = (Integer) stack.pop();
            insertItem(stack, item); // again a head recusion
            stack.push(i);
        }

    }

    static void print(Stack stack){
        while(!stack.isEmpty()){
            System.out.print(" "+stack.pop());
        }
    }

    public static void main(String args[]){
        // 1, 2, 10, 4, 5
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(10);
        stack.push(4);
        stack.push(5);
        //print(stack);
        System.out.println("\n Reversing stack--");
        reverseStack(stack);
        print(stack);

    }
}
