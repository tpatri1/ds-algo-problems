package com.trinath.dsalgo.lb.Recursion_DP;

import java.util.*;

class FindSubsets{
    //Order matters
    static void findAllPossibleSubsets(List<Integer> v, List<LinkedHashSet<Integer>> sets) {
        //List<Integer> result = new ArrayList<Integer>();
        findAllSubsetsHelper(v, new LinkedHashSet<>(), sets);

    }

    static void findAllSubsetsHelper(List<Integer> v, LinkedHashSet<Integer> slate, List<LinkedHashSet<Integer>> result){
        if(v.size()==0){
            result.add(slate);

            return;
        }
        for(int i= 0; i<v.size(); i++){
            LinkedHashSet<Integer> newSet = new LinkedHashSet<>(slate);
            newSet.add(v.get(i));
            result.add(slate);
            ArrayList<Integer> list = new ArrayList<Integer>(v);
            list.remove(i);


            findAllSubsetsHelper(list,newSet, result);
        }
    }
    //Find subsets where order does not matter

    public static void main(String args[]){
        List<LinkedHashSet<Integer>> sets = new ArrayList<>();
        List<Integer> v = new ArrayList<>();
        v.add(1);
        v.add(2);
        v.add(3);
        findAllPossibleSubsets(v,sets);
        System.out.println("hi");

    }
}
