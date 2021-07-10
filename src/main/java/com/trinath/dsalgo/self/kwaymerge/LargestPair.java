package com.trinath.dsalgo.self.kwaymerge;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class LargestPair {
    public static List<int[]> findKLargestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> result = new ArrayList<>();
        PriorityQueue<List<Integer>> pq = new PriorityQueue<List<Integer>>((a, b)->((a.get(0)+a.get(1))-(b.get(0)+b.get(1))));
        int i=0, j=0;
        while(i<nums1.length){
            j=nums2.length-1;
            while(j>=0){
                List<Integer> pair = new ArrayList<Integer>();
                pair.add(nums1[i]);
                pair.add(nums2[j]);
                pq.add(pair);
                if(pq.size()>k){
                    pq.poll();
                }
                j--;
            }
            i++;

        }
        // System.out.println(pq.peek()[0]);
        while(!pq.isEmpty()){
            List<Integer> p = pq.poll();
            //System.out.println(p[0]+" "+p[1]);
            result.add(new int[]{p.get(0), p.get(1)});
        }
        return result;
    }

    public static void main(String[] args) {
        int[] l1 = new int[] { 9, 8, 2 };
        int[] l2 = new int[] { 6, 3, 1 };
        List<int[]> result = LargestPair.findKLargestPairs(l1, l2, 3);
        System.out.print("Pairs with largest sum are: ");
        for (int[] pair : result)
            System.out.print("[" + pair[0] + ", " + pair[1] + "] ");
    }
}
