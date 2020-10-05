package com.trinath.dsalgo.lb.Tree_Graph_BackTracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KSumSets {
    public static List<HashSet<Integer>> getKSumSubsets(List<Integer> v, Integer targetSum) {
        //TODO: Write - Your - Code
        List<HashSet<Integer>> subsets = new ArrayList<HashSet<Integer>>();
        List<Integer> partialSol = new ArrayList<>();
        int sum=0;
        for(int x: v){
            sum+=x;
        }
        kSumSubsetsRec(v,targetSum,0,sum,partialSol,subsets);
        return subsets;
    }

    private static void kSumSubsetsRec(List<Integer> v, int targetSum, int currentSum,int totalsum, List<Integer> partialSol,List<HashSet<Integer>> results){

        if(targetSum==currentSum && partialSol.size()>0){
            results.add(new HashSet<>(partialSol));
            return;
        }
        if(currentSum>targetSum || v.size()==0){
            return;
        }
        for(int i=0; i<v.size(); i++){
//            partialSol= new HashSet<>();
//            currentSum=0;
            List<Integer> v1 = new ArrayList<>(v);
            int c = v1.remove(0);
            List<Integer> partialSolNew = new ArrayList<>(partialSol);
            partialSolNew.add(c);
            kSumSubsetsRec(v1, targetSum,currentSum+c,totalsum,partialSolNew,results);
            kSumSubsetsRec(v1, targetSum,currentSum,totalsum,partialSol,results);
        }
    }
//    private static void kSumSubsetsRec(List<Integer> v, int targetSum,  List<Integer> partialSol,List<HashSet<Integer>> results){
//        int sum=0;
//        for(int x: partialSol){
//            sum+=x;
//        }
//
//        if(targetSum==sum && partialSol.size()>0){
//            results.add(new HashSet<>(partialSol));
//            return;
//        }
//        if(sum>targetSum || v.size()==0){
//            return;
//        }
//        for(int i=0; i<v.size(); i++){
//
//            List<Integer> partialSolNew = new ArrayList<>(partialSol);
//            partialSolNew.add(v.get(i));
//            List<Integer> newlist = v.subList(i+1,v.size());
//            kSumSubsetsRec(newlist, targetSum,partialSolNew,results);
//
//        }
//    }

    public static void main(String[] args) {
        // initializing vector
        //int[] myints = {8, 13, 3, 22, 17, 39, 87, 45, 36};
        int[] myints = {1,3,5,21,19,7,2};
        List<Integer> vec = new ArrayList<Integer> ();
        for (Integer i: myints) {
            vec.add(i);
        }

        // computing subsets
        List<HashSet<Integer>> subsets = new ArrayList<HashSet<Integer>> ();
        subsets = getKSumSubsets(vec, 8);

        System.out.print("[");
        // printing subsets
        for (int i = 0; i < subsets.size(); ++i) {
            System.out.print("{");
            for (Integer it: subsets.get(i)) {
                System.out.print(it + ", ");
            }
            System.out.print("} ");
        }
        System.out.print("]");
    }
}
