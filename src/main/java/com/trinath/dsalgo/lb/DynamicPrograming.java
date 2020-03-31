package com.trinath.dsalgo.lb;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//Target 25 = 15 from AE and 10 from rest from edu problem set
public class DynamicPrograming {
    public static int getNthFib(int n) {
        // Write your code here.
        int[] memo = new int[n+1];
        return getNthFib(n, memo);

    }
//Problem 1
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

    //Problem 2- https://leetcode.com/problems/maximum-subarray/ -- Kadane's algo
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
    //Problem 3 - Max Sum sub sequence(not adjacent) of array
    static int findMaxSumNonadjacent(int[] a) {
        if(a==null || a.length==0){
            return 0;
        }
        else if(a.length==1){
            return a[0];
        }
        int lastMaxSum = a[1];
        int secondLastMaxSum =a[0];
        for(int i=2; i<a.length; i++){
            int currMaxSum =0;
            currMaxSum = Math.max(lastMaxSum, secondLastMaxSum+a[i]);//Main Algo,(TODO:: we can modify to return array of index as well if needed)
            secondLastMaxSum = lastMaxSum;
            lastMaxSum = currMaxSum;

        }
        return lastMaxSum;
    }
    //Prablem 4 - Longest Increasing SubSequence : https://leetcode.com/problems/longest-increasing-subsequence
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
    //Problem 5 - Longest Common SubSequence

    //Problem 6 -  Count Palindromic SubString(Contiguous) - DONE in StringProblems.getPalindromicSubStringsCountDP()

    //Problem 7- Longest Palindromic Sub Sequence

    //Problem 8 -This is actually permutation as order matters here even problem statemnt is: Combinations of Game Scoring -- How many ways we can we can write n using 1,2,4 ex- n=3 , 1,1,1 ; 1,2; 2,1
    private static int findCombinationSumN(int n, int[] choice){
        int count =0;
        if(n<=0){
            if(n==0){
                count++;
                return count;
            }
            return count; // if you are only use this return as in line 104
        }
        for(int c: choice){
            count+=findCombinationSumN(n-c,choice);
        }

//        count+=findCombinationSumN(n-1);
//        count+=findCombinationSumN(n-2);
//        count+=findCombinationSumN(n-4);
        return count;
    }
    private static int findCombinationSumNDP(int n, int[] memo) {
        if (n < 0) {
            return 0;
        }
        if(n==0){
            memo[0]=1;
            return  1;
        }
        if(memo[n]!=0){
            return memo[n];
        }
        memo[n] = findCombinationSumNDP(n-1,memo)+findCombinationSumNDP(n-2,memo)+findCombinationSumNDP(n-4,memo);
        return memo[n];

    }


    //Problem 9 - How many ways you can change a amount with array of coins
    private static  int coinChange(int amount, int[] coins, int[] memo){
        memo[0] =1;
        if(memo[amount]!=0){
            return memo[amount];
        }
        for(int c:coins){
            for(int i=c; i<=amount; i++) {
                memo[i] += memo[i - c];
            }
        }
        return memo[memo.length-1];

    }

    //Problem 10 - Levenstein edit distance

    //Problem 11 - Min number of ways you can change the amount using denominations  - also min coin to change
    //https://www.youtube.com/watch?v=Y0ZqKpToTic
    private static int minCoinsToChange(int amount, int[] coins){
        Arrays.sort(coins);
        int[][] dp = new int[coins.length][amount+1];// have a zero column just to make easy for array
        //Fill up first row for coin 1
        for(int col=0; col<=amount; col++){
            dp[0][col] = amount/coins[0] ; //Note:: there must be one coin that can satisfy all amount to populate first row
        }
        //Fill other row
        for(int col=1; col<=amount; col++){
            for(int row = 1; row<coins.length; row++){
                if(col-coins[row]>=0) {
                    dp[row][col] = Math.min(dp[row - 1][col], dp[row][col - coins[row]] + 1);
                }
                else{
                    dp[row][col] = dp[row - 1][col];
                }
            }
        }
        return dp[coins.length-1][amount];
    }

    //Problem 12 - Knapshak`

    //Problem 13 - Rod Cutting


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
        int[] a ={1, 6, 10, 14, 50, -20, -5, -10};
        System.out.println("Max non adjacent sub seq sum "+findMaxSumNonadjacent(a));
        int[] choice={1,2,5};
        System.out.println("Combination "+findCombinationSumN(7, choice));
        int[] memo = new int[10+1];
        System.out.println("Combination "+findCombinationSumNDP(10, memo));
        int[] coins ={1,2,5};
        int[] memo1 =new int[7+1];
        System.out.println("Number of way for coin change "+coinChange(7,coins, memo1));
        int[] coins1 ={1,5,6,8};
        System.out.println("Min number Coin change "+minCoinsToChange(11,coins1));
    }
}
