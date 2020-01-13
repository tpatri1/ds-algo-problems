package com.trinath.dsalgo.self;

import sun.jvm.hotspot.utilities.Assert;

import java.util.ArrayList;

public class InsertionOfSortedArrays {
    // Give the list of matched elements of two sorted array
    private static ArrayList insertSortedArrays(int[] a1, int[] a2){
        ArrayList<Integer> result = new ArrayList<Integer>();
        int i=0;
        int j =0;
        while(i<a1.length && j<a2.length){
            if(a1[i] <a2[j]) i+=1;
            else if(a1[i]>a2[j])  j+=1;
            else if(result.get(result.size()-1)!=a1[i]){// last element in result is same as array, ignore
                result.add(Integer.valueOf(a1[i]));
                i++;
                j++;
            }

        }
        return result;
    }

    public static void main(String[] arg) {
        int[] a1 = {2, 4, 6, 6, 7};
        int[] a2 = {4, 6};
        ArrayList<Integer> res = insertSortedArrays(a1, a2);
        Assert.that(res.size() == 2, "fail1");
        Assert.that(res.get(0) == 4, "fail2");
        Assert.that(res.get(1) == 6, "fail2");
    }
}
