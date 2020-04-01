package com.trinath.dsalgo.lb.Stack_Queue_LinkedList_HashTable;

import java.util.Stack;

public class NextGreaterElementInArray {
//https://leetcode.com/problems/next-greater-element-ii/
    //This problem is not for circular arry rest holds true
    static Stack<Integer> mainStack = new Stack();
    static Stack<Integer> greaterStack = new Stack<>();
    static int[] getNextGreaterElement(int[] array){
        int[] resStack = new int[array.length];
        int i =0;
        int len = mainStack.size();
        int prev = mainStack.pop();
        int next = -1;
        //resStack[len-1] = next; // last one

        while(!mainStack.isEmpty()){
            i++;
            resStack[len-i] = next;
            // prev is equal to  top of greater stack then update next and pop
            if(prev == greaterStack.peek()){
                next = greaterStack.pop();

            }
            prev = mainStack.pop(); // update prev
        }
        resStack[0] = next;
        return resStack;

    }

    static void printArray(int[] arr){
        for(int i=0; i<arr.length; i++){
            System.out.println(arr[i]);
        }
    }

    static void buildStack(int[] arr){
        mainStack.push(arr[0]);
        for(int i=1; i< arr.length; i++){
            if(arr[i] > mainStack.peek()){
                mainStack.push(arr[i]);
                greaterStack.push((arr[i]));
            }
            else{
                mainStack.push(arr[i]);
            }
        }
    }

    public static void main(String args[]){
        int[] input = {4, 5,2, 7,3,9,1};
        buildStack(input);
        printArray(getNextGreaterElement(input));
    }

}
