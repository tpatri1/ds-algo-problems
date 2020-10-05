package com.trinath.dsalgo.lb.Recursion_DP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class SumCombination{
    //Memo
    static Map<Integer, ArrayList<ArrayList<Integer>>> memo = new HashMap<>();

    static ArrayList<ArrayList<Integer>> printAllSum(int target) {

        ArrayList<ArrayList<Integer>> output = generateSubset(4);

        return output;
    }

    static  ArrayList<ArrayList<Integer>> generateSubset(int target){
        if(memo.containsKey(target)){
           return  memo.get(target);
        }
        if(target==1){
            ArrayList<ArrayList<Integer>> res = new ArrayList();
            ArrayList<Integer> lst = new ArrayList<>();
            lst.add(1);
            res.add(lst);
            memo.put(1,res);
           return  memo.get(1);
        }
        ArrayList<ArrayList<Integer>> output = new ArrayList();
        for(int i=1; i<target; i++){
            createAndAddFromPrevious(generateSubset(target-i),i,output);
        }
        memo.put(target,output);
        return output;
    }

    static void createAndAddFromPrevious(ArrayList<ArrayList<Integer>> prev,int i, ArrayList<ArrayList<Integer>> output){
        for(ArrayList<Integer> list : prev){
            ArrayList<Integer> temp  = new ArrayList<Integer>(list);
            temp.add(i);
            output.add(temp);
        }
    }

    public static void main(String args[]){
        System.out.println(printAllSum(5));
    }
}