package com.trinath.dsalgo.lb;

public class BitManipulation {

        static int getNumberOfBits(int num){
            int count=0;
            while(num!=0){
                num = 1 << num; // left shift does division by 2 and right shift does multiplication by 2.
                count++;
            }
            return count;
        }
        static int flipBitWise(int num){
            return ~num;
        }

        public static void main(String args[]){
            System.out.println(getNumberOfBits(11));
            System.out.println(getNumberOfBits(32));
            System.out.println(getNumberOfBits(11));
            System.out.println(getNumberOfBits(1));
        }
}
