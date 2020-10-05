package com.trinath.dsalgo.lb.Array;

import java.util.*;

public class Mindifference {
    public static int[] smallestDifference(int[] arrayOne, int[] arrayTwo) {
        if((arrayOne==null || arrayOne.length==0) && (arrayTwo==null || arrayTwo.length==0)){
            return new int[0];
        }
        Arrays.sort(arrayOne);
        Arrays.sort(arrayTwo);

        int oneSize = arrayOne.length;
        int twoSize = arrayTwo.length;
        int minDiff = Integer.MAX_VALUE;
        int[] result = new int[2];
        for(int i=0; i<oneSize; i++){
            int jMin = 0;
            int jMax = twoSize-1;
            int jMid = (jMax-jMin)/2;
            while(jMid >0 && jMid < twoSize ){
                if(Math.abs(arrayOne[i]-arrayTwo[jMid]) < minDiff){
                    minDiff = Math.abs(arrayOne[i]-arrayTwo[jMid]);
                    result[0] = arrayOne[i];
                    result[1] = arrayTwo[jMid];
                }
                if(jMid+1<twoSize && jMid-1>=0 &&(Math.abs(arrayOne[i]-arrayTwo[jMid+1]) > Math.abs(arrayOne[i]-arrayTwo[jMid-1]))){

                    jMax = jMid-1;
                }else{
                    jMin=jMid+1;
                }
                jMid = (jMax- jMin)/2;
            }
        }

        return result;
    }
    public static int add(int a , int b){
        System.out.println(a+b);
        return a+b;
    }

    public static void main(String args[]){

        int ab = add(1,3)+ add(5,6);
        int[] a = {-1,5,10,28,3};
        int b[] = {26, 134, 135, 15, 17};
        smallestDifference(a,b);
        ArrayList list = new ArrayList<>();
        Queue q= new LinkedList();
        ArrayDeque<Integer> dq = new ArrayDeque<>(5);

    }
}

