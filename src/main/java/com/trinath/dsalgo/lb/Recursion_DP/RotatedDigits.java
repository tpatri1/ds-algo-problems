package com.trinath.dsalgo.lb.Recursion_DP;

public class RotatedDigits {
    public static int getRotatedDigits(int n){
        int result =0;
        for(int i=0; i<n; i++){
            if(canRotate(i, false)){
                result++;
            }
        }
        return result;
    }

    static boolean canRotate(int n, boolean flag){
        if(n==0){
            return flag;
        }
        if(n%10==3 || n%10==4 ||n%10==7){
            return false;
        }
        if(n%10==0 || n%10==1 ||n%10==8)
        {
            return canRotate(n/10,false);
        }
        return canRotate(n/10, true);
    }

    public static void main(String args[]){
        System.out.println(getRotatedDigits(10));
    }
}
