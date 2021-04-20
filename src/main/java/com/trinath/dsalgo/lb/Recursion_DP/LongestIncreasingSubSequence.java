package com.trinath.dsalgo.lb.Recursion_DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestIncreasingSubSequence {

    public static List<Integer> longestIncreasingSubsequence(int[] array) {
        int[] sequences = new int[array.length];
        Arrays.fill(sequences, Integer.MIN_VALUE);
        int[] lengths = new int[array.length];
        Arrays.fill(lengths,1);
        //iterate for all the prev value till that index
        int maxLengthIdx = 0;
        for(int i=0; i<array.length; i++){
            int currNum = array[i];
            for(int j=0; j<i; j++){
                int otherNum = array[j];
                if(otherNum< currNum && lengths[j]+1 >= lengths[i]){
                    lengths[i] = lengths[j]+1;
                    sequences[i] = j;
                }
            }
            if(lengths[i] >= lengths[maxLengthIdx]){
                maxLengthIdx = i;
            }
        }
        return buildQequence(array, sequences, maxLengthIdx);
    }

    private static List<Integer> buildQequence(int[] array, int[] sequences, int currIdx){
        List<Integer> sequence =  new ArrayList<Integer>();
        while(currIdx != Integer.MIN_VALUE){
            sequence.add(0, array[currIdx]);
            currIdx = sequences[currIdx];
        }
        return sequence;
    }
public static void main(String args[]){
        int[] array =  {1,5, -1, 10};
        List<Integer> res = longestIncreasingSubsequence(array);
        String s =  "abc";
        String s1= s.replace('b','\0');
        System.out.println(s1);
}
}
