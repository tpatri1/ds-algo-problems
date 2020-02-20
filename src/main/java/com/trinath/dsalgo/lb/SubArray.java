package com.trinath.dsalgo.lb;

import java.util.HashMap;
import java.util.Map;

public class SubArray {
//https://leetcode.com/problems/maximum-subarray/ -- Kadane's algo
    private int maxSubArraySum(int[] nums){
        if(nums.length <1){
            return Integer.MIN_VALUE;
        }
        Map<Integer,Integer> maxSumMap = new HashMap();
        maxSumMap.put(0, nums[0]);
        int currMax = nums[0];
        for(int i=1; i<nums.length; i++){
            int prevSum = maxSumMap.get(i-1);
            int currSubArraySum = Math.max(nums[i],prevSum+nums[i]);
            maxSumMap.put(i, currSubArraySum);
            if(currMax<currSubArraySum){
                currMax = currSubArraySum;
            }
        }
        return currMax;
    }
}
