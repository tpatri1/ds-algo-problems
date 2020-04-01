package com.trinath.dsalgo.lb.Math_Bit_String;

public class BitManipulate {
    static int getNumberOfBits(int num){
        int count=0;
        while(num!=0){
            num = num >> 1; // right shift does division by 2 and left shift does multiplication by 2.
            count++;
        }
        return count;
    }


    public static void main(String args[]){
        System.out.println("Bit man");
        System.out.println(getNumberOfBits(11));
        System.out.println(getNumberOfBits(32));
        //System.out.println(flipBitWise(0));
        //System.out.println(flipBitWise(3));
    }
}
