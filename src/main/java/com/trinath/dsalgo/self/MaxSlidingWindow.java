package com.trinath.dsalgo.self;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

class MaxSlidingWindow{

    public static ArrayDeque<Integer> findMaxSlidingWindow(int[] arr, int windowSize) {

        ArrayDeque<Integer> result = new ArrayDeque<>(); // ArrayDeque for storing max values
        Deque<Integer> list = new LinkedList<Integer>(); // Linkedlist for storing indexes

//        int max =arr[0];
//        int tempMax = arr[0];
//        int counter = windowSize;
//        int temp[] = new int[windowSize];
//        boolean flag = false;
//        for(int i=0; i<arr.length; i++){
//            //for window size find max
//            counter --;
//            temp[3-counter] = arr[i];
//            if(counter==0){
//                counter = windowSize;
//                temp = new int[windowSize];
//                //flag = true;
//            }
//            if(arr[i]>max || arr[i]>tempMax){
//                max = arr[i];
//
//            }
//            if(i>windowSize )
//            {
//                result.add(max);
//            }
//        }
        if(arr.length>0){
        //base case
        if(arr.length<windowSize){
            return result;
        }

        int startingIndex =0;
        for(int i=startingIndex; i<windowSize; i++){
            //removing last smallest element
            while(!list.isEmpty() && arr[i]<=arr[list.peekLast()])
                list.removeLast();

            //add to list
            list.addLast(i);
            //repointing
            startingIndex = i+1;
        }
        //extra removal of non current index
        for(int i=startingIndex; i<arr.length; i++){
            result.add(list.peekLast());
            while(!list.isEmpty() && arr[i]>=arr[list.peekLast()])
                list.removeLast();

            while ((!list.isEmpty() && list.peek()>=i-windowSize))
                list.removeFirst();

            list.addLast(i);

        }
        result.add(arr[list.peek()]);
        return result; // returning result
    }

    else{
        return result;
    }
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 3, 2, 1, 2, 5};
        System.out.println(findMaxSlidingWindow(arr,4));
    }
}