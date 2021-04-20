package com.trinath.dsalgo.lb.Array;

public class TargetSum {

    private static boolean twoNumberTargetSum(int[] arr, int target){
        int i=0;
        int j=arr.length-1;
        while(i<j){
            int sum  = arr[i]+arr[j];
            if(sum==target){
                return true;
            }
            if(sum<target){
                i++;
            }else{
                j--;
            }
        }
        return  false;
    }

    public static void main(String args[]){
        System.out.println("unsorted data result: "+twoNumberTargetSum(new int[]{20, 11, 3, 6,24, 26, 7, -12, 13, 2, 10}, 9));// but this will work on only in sorted data
        System.out.println("sorted data result: "+ twoNumberTargetSum(new int[]{-1, 2,3, 4, 6, 10, 11, 13 , 17, 20}, 9));
    }
}
