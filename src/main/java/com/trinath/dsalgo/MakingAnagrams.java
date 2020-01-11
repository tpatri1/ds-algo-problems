package com.trinath.dsalgo;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
/*

https://www.hackerrank.com/challenges/ctci-making-anagrams/problem
 */
public class MakingAnagrams {

    public static int NUMBER_LETTERS = 26;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s1= scanner.next();
        String s2= scanner.next();

        int[] count1 =getCharCount(s1);
        int[] count2 = getCharCount(s2);

        System.out.println(getDelta(count1, count2));




    }
    public static int[] getCharCount(String s) {
        int[] charCounts = new int[NUMBER_LETTERS];
        int offset = (int)'a';
        for(char c: s.toCharArray()) {
            int code = c-offset;
            charCounts[code] = ++charCounts[code];
        }
        return charCounts;
    }

    public static int getDelta(int[] arr1, int[] arr2) {
        int delta=0;
        for(int i=0; i< arr1.length; i++) {
            int diff = Math.abs(arr1[i]- arr2[i]);
            delta += diff;
        }
        return delta;
    }
}
