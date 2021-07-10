package com.trinath.dsalgo.self.subsets;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class SubsetsPracticeProblems {
    //1,2 - > [1,2], [2,1]
    private static List<List<Integer>> permutations(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> partial = new ArrayList<>();
        permutationRec(nums, 0,result, partial);
        return result;
    }
    private static List<List<Integer>> permutationsAlt(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> partial = new ArrayList<>();
        List<Integer> numsList = new ArrayList<>();
        for(int i=0; i<nums.length; i++){
            numsList.add(nums[i]);
        }
        permutationRecAlt(numsList, result, partial);
        return result;
    }

    private static void permutationRec(int[] nums, int index,  List<List<Integer>> result, List<Integer> partial){
    if(index==nums.length){
        result.add(partial);
        return;
    }else{
        for(int i=0; i<=partial.size(); i++){
            List<Integer> newPartial = new ArrayList<>(partial);
            newPartial.add(i,nums[index]);//important
            permutationRec(nums, index+1, result, newPartial);
        }
    }
    }
    //its iterative of above recursion
    private static List<List<Integer>> permuationsIterative(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.add(new ArrayList<>());//base

        for(int i=0;i< nums.length; i++ ){
            int n = queue.size();
            for(int j=0; j<n; j++){
                List<Integer> permutation = queue.poll();
                for(int k=0; k<=permutation.size();k++) {
                    List<Integer> curr = new ArrayList<>(permutation);//take each element
                    curr.add(k, nums[i]);
                    if (curr.size() == nums.length) {
                        result.add(curr);
                    } else {
                        queue.add(curr);
                    }
                }
            }
        }
        return result;
    }
    private static void permutationRecAlt(List<Integer> list,  List<List<Integer>> result, List<Integer> partial) {
    if(list.size()==0){
        result.add(new ArrayList<>(partial));
    }else{
        for(int i=0; i<list.size(); i++){
            List<Integer> newPartial = new ArrayList<>(partial);
            newPartial.add(list.get(i));
            List<Integer> list1 = new ArrayList<>(list);
            list1.remove(i);
            permutationRecAlt(list1,result,newPartial);
        }
    }
    }
    //[ab34] - [ab34], [Ab34], [aB34], [AB34]
    public static List<String> findLetterCaseStringPermutations(String str) {
        List<String> permutations = new ArrayList<>();
        permutationsCaseRec(str,0, new ArrayList<Character>(), permutations);
        return permutations;
    }

    private static void permutationsCaseRec(String str, int i, List<Character> characters, List<String> permutations) {
        if(i==str.length()){
            permutations.add(characters.stream().map(c->String.valueOf(c)).collect(Collectors.joining()));
        }else{
                List<Character> newChars = new ArrayList<>(characters);
                char c = str.charAt(i);
                if(c>='0' &&c<='9' ) {
                    newChars.add(c);
                    permutationsCaseRec(str, i+1, newChars, permutations);
                }else {
                    newChars.add(Character.toLowerCase(c));
                    List<Character> newChars1 = new ArrayList<>(characters);
                    newChars1.add(Character.toUpperCase(c));
                    permutationsCaseRec(str, i+1, newChars, permutations);
                    permutationsCaseRec(str, i+1, newChars1, permutations);
                }

            }
    }

    public static void main(String args[]){
        System.out.println(permutations(new int[]{1,2,3}));
        System.out.println(permutationsAlt(new int[]{1,2,3}));
        System.out.println(permuationsIterative(new int[]{1,2,3}));

        System.out.println(findLetterCaseStringPermutations("ab34"));
    }

}
