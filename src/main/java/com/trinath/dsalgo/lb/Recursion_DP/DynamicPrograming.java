package com.trinath.dsalgo.lb.Recursion_DP;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 20 problem at least
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
    //Problem 5 - Longest Common SubSequence of String S1 and S2
    private static int longestCommonSubSequence(String s1, String s2){
        int[][] dp = new int[s1.length()+1][s2.length()+1];// we need to keep 0th index as 0 as they are differnt strings so table can have  amatch or no match in first cell, so starting with zero helps
        //Fill the 2nd  row as first row and colum is use less and have 0 only to match array index
        for(int col=1; col<=s2.length(); col++) {
            boolean flag = false;
            if (s1.charAt(1) == s2.charAt(col-1)) {// Compared row-1 and col-1 instead of row and col as dp array indexing started with a dummy row and column
                dp[1][col] = 1;
                flag = true;
            }
            if (flag == true) {
                dp[1][col] = dp[1][col - 1];
            }
        }
            //Populate the dp array row by row as for matching case it will come from prev row and for non matching it will prev column
            for(int row=1;row<=s1.length(); row++ ){
                for(int col=1; col<=s2.length(); col++){
                    if(s1.charAt(row-1)== s2.charAt(col-1)){ // Compared row-1 and col-1 instead of row and col as dp array indexing started with a dummy row and column
                        // add 1 to previous common sequence except last char
                        dp[row][col] = dp[row-1][col-1]+1; // as starting index with 1, so no issue
                    }
                    else{
                        //Max of left cell ot the cell above
                        dp[row][col] = Math.max(dp[row][col-1], dp[row-1][col]);
                    }
                }
            }
        return dp[s1.length()][s2.length()];
    }

    //Problem 6 -  Count Palindromic SubString(Contiguous) - DONE in StringProblems.getPalindromicSubStringsCountDP()

    //Problem 7- Longest Palindromic Sub Sequence : https://www.youtube.com/watch?v=_nCsPn7_OgI&t=75s
    private static int longestPalindromicSubSequence(String s){
        //As only one string so the dp matrix will start from zeroth index so of size of string
        int len = s.length();// Assumed valid string else handle
        int[][] dp = new int[len][len];
        //Algo:  If single char, it's palindromic length= 1, if first and last char mattters then len = 2+ palindromic len of middle substring(except last char T[row+1][col-1])
        //else take the best of adjacent left and below cell --> as ex- `bac` comes from `ba` and `ac' .. Note each cell represents the s.substr(row, col); row and col are index of same string written as a 2D array

        //Fill the single char cell size of substr = 1
        for(int row=0; row<len; row++){
            dp[row][row] =1;
        }
        //Iterate over size of substr 2, 3, 4.. etc, It will not be row or colum rather than parallel diagonals in upper half as lower half is mirror dp[i][j] = dp[j][i]
        for(int size=2; size<=len; size++){//substring size
            int row =0;
            int col =row+size-1;
            while(col<len){
                if(s.charAt(row)==s.charAt(col)){
                    dp[row][col] = 2+ dp[row+1][col-1];
                }
                else{
                    dp[row][col] = Math.max(dp[row][col-1], dp[row+1][col]);
                }
                row++;
                col++;
            }

        }
        return dp[0][s.length()-1];
    }


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

    //Problem 12 - 0/1(either select that weight or not , but dont repeat) - Knapsack`, If you can select multiple time , this problem turns tp greedy solution
    private static int getMaxProfitForMaxWeightKnapSack(int capacity, int[] weights, int[] profits) {
        Arrays.sort(weights);
        Arrays.sort(profits);
        int dp[][] = new int[weights.length][capacity + 1];// +1 for ease of index, dp[][0] = set to zero by default
        //Row wise - Either select or not, but cant repeat the weight(so if the target weight/capacity for each col>= weight[row], then select that weight's profit and move w[i] back to choose again, else select adjacent weight but maximum profit
        for (int row = 0; row < weights.length; row++) {
            for (int col = 1; col <= capacity; col++) {
                if (row == 0) {// Fill specially for first row as there is no prev row exists, we can't use max principle unless introduce a dummy 0th row
                    if(col>=weights[row]){
                        dp[row][col] = profits[row];
                    }
                } else {// any other row
                    if(col>=weights[row]) {
                        dp[row][col] = Math.max(profits[row] + dp[row - 1][col - weights[row]], dp[row - 1][col]);
                    }
                    else{
                        dp[row][col] = dp[row - 1][col];
                    }
                }
            }

        }
        printSelectedKnapsack(dp,weights,profits,capacity);
        return dp[weights.length - 1][capacity];
    }

    private static void printSelectedKnapsack(int[][] dp, int[] weights, int[] profits, int capacity){
        System.out.println("Print selected weights are: ");
        int row  = weights.length-1;
        int col = capacity;
        while(capacity>0 && row>=0 && col>=0) {
            if (dp[row][col] == dp[row - 1][col]) {
                row = row - 1;
            } else if(col - weights[row] >=0 && row-1>=0 && dp[row][col] == dp[row - 1][col - weights[row]] + profits[row]) {
                    col = col - weights[row];
                }
                System.out.println(weights[row]);
                capacity -= weights[row];
                row = row - 1;
            }
    }

    //Problem 13 - Rod Cutting -- This is not 0/1, you can choose multiple times for a size : https://www.youtube.com/watch?v=IRwVmTmN6go
    //Bottom up DP, in top down we memoize a recursive solution( given in Edu for 0/1 knapsack)
    private static int getMaxPriceFortheRodCutting(int targetLen, int[] lengths, int[] price) {
        Arrays.sort(lengths);
        Arrays.sort(price);
        int dp[][] = new int[lengths.length][targetLen + 1];
          for (int row = 0; row < lengths.length; row++) {
            for (int col = 1; col <= targetLen; col++) {
                if (row == 0) {// Fill specially for first row as there is no prev row exists, we can't use max principle unless introduce a dummy 0th row

                    dp[row][col] = (col/lengths[row])*price[row]; // Multiple choice allowed for a length

                } else {// any other row choose max of "col - lengths[row]" of same row and "row-1" of same column
                    if(col>=lengths[row]) {
                        dp[row][col] = Math.max(price[row] + dp[row][col - lengths[row]], dp[row -1][col]);
                    }
                    else{
                        dp[row][col] = dp[row - 1][col];
                    }
                }
            }
        }
        return dp[lengths.length - 1][targetLen];
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

        System.out.println("Longest common Subsequence  "+longestCommonSubSequence("abaeca","baeada"));
        System.out.println("Longest Palindromic Subsequence : 3 == "+longestPalindromicSubSequence("abacbdc"));
        System.out.println("Longest Palindromic Subsequence : 5 ==  "+longestPalindromicSubSequence("abdba"));

        int[] weights ={1,2,3,5};
        int[] profits={1,6,10,16};
        System.out.println("0/1 knapsack profit maximum for weights 7 = 22(expected) ::  actual "+getMaxProfitForMaxWeightKnapSack(7, weights,profits));

        int[] lengths = {1,2,3,4};
        int[] price ={2,5,7,8};
        System.out.println("Multi select rod cutting  knapsack profit maximum for length of rod 5 = 12(expected) ::  actual "+getMaxPriceFortheRodCutting(5, lengths,price));
    }
}
