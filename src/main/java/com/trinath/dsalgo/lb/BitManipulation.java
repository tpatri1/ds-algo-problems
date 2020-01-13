package com.trinath.dsalgo.lb;

public class BitManipulation {

        static int getNumberOfBits(int num){
            int count=0;
            while(num!=0){
                num = 1 << num; // left shit does diiviision by 2 and right shift does multimplication by 2.
                count++;
            }
            return count;
        }

        public static void main(String args[]){
            System.out.println(getNumberOfBits(11));
            System.out.println(getNumberOfBits(32));
        }
}
