package com.trinath.dsalgo.lb.Stack_Queue_LinkedList_HashTable;

import java.util.*;

// https://leetcode.com/problems/maximum-frequency-stack/
public class MaxFreqStack {
//
//    static Map<Integer,Integer> freq= new HashMap<>();
//    static int maxFreq = Integer.MIN_VALUE;
//    static int maxFreqElem = Integer.MIN_VALUE;
//    static Map<Integer,Integer> topElemIndex = new HashMap<>();
//    static int[] freqStack  = new int[10];;
//    static int top =-1;
//
//    public MaxFreqStack() {
//
//
//
//    }
//
//    public static void push(int x) {
//        int freqOfElem = 0;
//        if(freq.containsKey(x)){
//            freqOfElem = freq.get(x);
//            freq.put(x, ++freqOfElem);
//        }
//        else{
//            freq.put(x, ++freqOfElem);
//        }
//        if(freqOfElem>maxFreq){
//            maxFreq = freqOfElem;
//            maxFreqElem = x;
//        }
//        //index of top elem to used to be popped
//        topElemIndex.put(x,++top); // Index in the array
//        freqStack[top] = x;
//
//
//    }
//
//    static void printStack(){
//        for(int i=0; i<=top; i++){
//            System.out.println(freqStack[i]);
//        }
//    }
////return the max freq element from the top
//    public static int pop() {
//        int result = -1;
//            if(top>=0){
//                result = maxFreqElem;
//                int index = freq.size();
//            }
//        freqStack[freq.get(maxFreqElem)] = Integer.MIN_VALUE;
//        freq.remove(maxFreqElem);
//        topElemIndex.put(maxFreqElem,topElemIndex)
//
//        return result;
//    }
//
//    static void resizeAndSetNextMaxFreq(){
//        for(int i=0; i<=top; i++){
//            if(freqStack[i]>maxFreqElem){
//                maxFreqElem = freqStack[i];
//            }
////            if(freqStack[i]==0){
////                freqStack[i] = freqStack[i+1];
////                freqStack[i+1]=0;
////            }
//        }
//    }

    // freqMap is to map element to its frequency
    static Map<Integer, Integer> freqMap = new HashMap<>();

    // setMap is to map frequency to the
    // element list with this frequency
    static Map<Integer, Stack<Integer>> freqElemsStackMap = new HashMap<>();

    // Variable which stores maximum frequency
    // of the stack element
    static int maxfreq = 0;

    // Function to insert x in the stack
    public static void push(int x)
    {

       //Freq of input
        int freq = freqMap.getOrDefault(x,0) +1;
        //Add elem and its freq
        freqMap.put(x, freq);
        //Set max
        if(freq>maxfreq){
            maxfreq = freq;
        }
        //Add to reverse relationship freq and Stack of element or add to tp so that we can pop
        freqElemsStackMap.computeIfAbsent(freq,z->new Stack()).push(x);
    }

    // Function to remove maximum frequency element (O(1) time and O(n) space
    public static int pop()
    {

       //find the top element for max freq
        int topElem = freqElemsStackMap.get(maxfreq).pop();

        //decrement freq from freqMap for the topElem
        freqMap.put(topElem, freqMap.get(topElem) -1);

        //All elements finiished from the maxFreq, then reduce maxFred
        if(freqElemsStackMap.get(maxfreq).size()==0){
            maxfreq--;
        }
        return topElem;
    }

    public static void main(String args[]){
        push(5);
        push(7);
        push(5);
        push(7);
        push(4);
        push(5);
      //  printStack();
        System.out.println(">>"+pop());
        System.out.println(">>"+pop());
        System.out.println(">>"+pop());
        System.out.println(">>"+pop());
        System.out.println(">>"+pop());

    }
}
