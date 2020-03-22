package com.trinath.dsalgo.lb;

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

    public static void main(String s[]){
        System.out.println(getNthFib(1));
        System.out.println(getNthFib(2));
        System.out.println(getNthFib(3));
        System.out.println(getNthFib(4));
        System.out.println(getNthFib(5));
    }
}
