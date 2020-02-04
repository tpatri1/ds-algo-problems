package com.trinath.dsalgo.lb;

import java.util.EmptyStackException;
import java.util.Stack;
//https://leetcode.com/problems/validate-stack-sequences/submissions/
public class StackOperationsSeq {

    static boolean checkStackOperationsSeq(int[] pushed, int[] popped){
        if(pushed.length==0 && popped.length==0) return true;//Base case
        Stack<Integer> stack = new Stack<>();
        try {
            int i=0;
            for (int j = 0; j < popped.length; j++) {

                while(i < pushed.length) {
                    if (!stack.isEmpty() && popped[j] == stack.peek()) {  // First time stack is empty so no pop; but later pop first if match before pushing
                        stack.pop();
                        break; // have to break to increment popped array
                    }
                    stack.push(pushed[i]);
                    i ++;

                }
                if(i==pushed.length && !stack.isEmpty() && popped[j]==stack.peek()) { //After pushed array is completed , pop by comparing
                    stack.pop();
                }
            }
        }
        catch(EmptyStackException e){
            return false;
        }
        return stack.empty();
    }

    public static void main(String[] s){
        int a[]={1,2,3,4,5};
        int b[]={4,5,3,2,1};
        int a1[] ={1,0};
        int b1[] = {1,0};
        int a2[] ={2,1,0};
        int b2[] = {1,2,0};
        System.out.println(checkStackOperationsSeq(a2,b2));
        System.out.println(checkStackOperationsSeq(a1,b1));
        System.out.println(checkStackOperationsSeq(a,b));


    }


}
