package com.trinath.dsalgo.lb.Array;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindow {
    private static ArrayDeque<Integer> findMaxSlidingWindow(int[] arr, int windowSize){
        ArrayDeque<Integer> result = new ArrayDeque<>();
        Deque<Integer> deque =  new LinkedList<>();

        //populate first deque
        if(arr.length>0) {
            if (arr.length < windowSize) {
                return result;
            }
            for (int i = 0; i < windowSize; ++i) {
                if (arr.length < windowSize) // Invalid State
                    return result;
                }
                for(int i = 0; i < windowSize; ++i) {
                    // Removing last smallest element index
                    while(!deque.isEmpty() && arr[i] >= arr[deque.peekLast()]){
                        deque.removeLast();
                    }

                    // Adding newly picked element index
                    deque.addLast(i);
            }

            //
            for (int i = windowSize; i < arr.length; i++) {
                result.add(arr[deque.peek()]);

                // Removing all the elements indexes which are not in the current window
                while((!deque.isEmpty()) && deque.peek() <= i-windowSize)
                    deque.removeFirst();

                // Removing the smaller elements indexes which are not required
                while((!deque.isEmpty()) && arr[i] >= arr[deque.peekLast()])
                    deque.removeLast();

                // Adding newly picked element index
                deque.addLast(i);
            }
            result.add(arr[deque.peek()]);//same as peek first
            return result;
        }else{
            return result;
        }
    }

    public static void main(String args[]){
        ArrayDeque res = findMaxSlidingWindow(new int[]{-4,2,-5,1,-1,6}, 3);
        System.out.println(res);
    }
}
