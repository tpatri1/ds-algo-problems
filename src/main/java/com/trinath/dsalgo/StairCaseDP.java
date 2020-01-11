package com.trinath.dsalgo;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
/*
Problem def-
https://www.hackerrank.com/challenges/ctci-recursive-staircase/problem
 */


public class StairCaseDP {
    public static int countSteps(int numSteps){
        long n = numSteps;
        int[] memo = new int[numSteps+1];
        memo[1] =1;
        memo[2] =2;
        memo[3] =4;
        if(n==1) return 1;
        else if(n==2) { return 2;}
        else if(n==3) { return 4;}
        else{
            for(int i=4; i<=numSteps; i++){
                memo[i] = memo[i-1] + memo[i-2]+memo[i-3];
            }
            //return countSteps(n-1)+ countSteps(n-2)+countSteps(n-3);

        }
        return memo[numSteps];
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
//        for (int a0 = 0; a0 < t; a0++) {
//            String expression = in.next();
//            System.out.println( (isBalanced(expression)) ? "YES" : "NO" );
//
//
//        }
        System.out.println(countSteps(t));
    }
}
