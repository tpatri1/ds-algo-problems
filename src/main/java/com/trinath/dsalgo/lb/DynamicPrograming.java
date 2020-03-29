package com.trinath.dsalgo.lb;

import java.util.HashMap;
import java.util.Map;

//Target 25 = 15 from AE and 10 from rest from edu problem set
public class DynamicPrograming {
    public static int getNthFib(int n) {
        // Write your code here.
        int[] memo = new int[n+1];
        return getNthFib(n, memo);

    }

    public static int getNthFib(int n, int[] memo){
        if(n==1){
            memo[1]=0;
            return memo[1];
        }
        else if(n==2){
            memo[2]=1;
            return memo[2];
        }
        if(memo[n]!=0) return memo[n];
        int res = getNthFib(n-1,memo) + getNthFib(n-2,memo);
        memo[n]=res;
        return res;
    }

    //https://leetcode.com/problems/maximum-subarray/ -- Kadane's algo
    private static int maxSubArraySum(int[] nums){
        if(nums.length <1){
            return Integer.MIN_VALUE;
        }
        Map<Integer,Integer> maxSumMap = new HashMap();
        maxSumMap.put(0, nums[0]);
        int currMax = nums[0];
        for(int i=1; i<nums.length; i++){
            int prevSum = maxSumMap.get(i-1);
            int currSubArraySum = Math.max(nums[i],prevSum+nums[i]); // This can be replaceed by just variable with out map if(prevSum< 0, then currSum=nums[i] else prevSum+ nums[i]
            maxSumMap.put(i, currSubArraySum);
            if(currMax<currSubArraySum){
                currMax = currSubArraySum;
            }
        }
        return currMax;
    }

    //Longest Increasing SubSequence : https://leetcode.com/problems/longest-increasing-subsequence
    private static int longestIncreasingSubSequenceLength(int[] arr){
        int globalLongest=0;
        if(arr.length>0){
            globalLongest=1;
        }
        int longestSubSeq[] = new int[arr.length]; // array to store longest subseq len corresponding to each element of original array
        for(int i=0; i<arr.length; i++){// for each element start from 0 to i
            longestSubSeq[i]=1;//default
            for(int j=0; j<i; j++){
                if(arr[i]>arr[j]){
                    longestSubSeq[i] = Math.max(longestSubSeq[i], 1+longestSubSeq[j]);// main algo
                    //update the longest
                    if(globalLongest<longestSubSeq[i]){
                        globalLongest = longestSubSeq[i];
                    }
                }
            }
        }
        return globalLongest;
    }

    public static void main(String s[]){
        System.out.println(getNthFib(1));
        System.out.println(getNthFib(2));
        System.out.println(getNthFib(3));
        System.out.println(getNthFib(4));
        System.out.println(getNthFib(5));

        int arr[] ={11,-4,2,-5,1,2,3,6,-5,1};
        System.out.println(maxSubArraySum(arr));
        int arr1[]={10,9,2,5,3,7,101,18};
        System.out.println("Longest Sub Seq "+longestIncreasingSubSequenceLength(arr1));
        int[] arr2 = {0};
        System.out.println("Longest Sub Seq "+longestIncreasingSubSequenceLength(arr2));
    }
}
