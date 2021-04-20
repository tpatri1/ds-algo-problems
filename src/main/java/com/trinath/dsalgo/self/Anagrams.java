package com.trinath.dsalgo.self;

import java.util.*;

public class Anagrams {
    private static List<List<String>> groupAnagrama(List<String> words){
        Map<String, List<Integer>> sortedWords = new HashMap<>();
        for(int i=0; i< words.size(); i++){
           String word = words.get(i);
           char[] chars = word.toCharArray();
            Arrays.sort(chars);
            word = new String(chars);
            List<Integer> indices = sortedWords.getOrDefault(word, new ArrayList<Integer>());
            indices.add(i);
            sortedWords.put(word,indices);

        }
        List<List<String>> results = new ArrayList<>();
        for(String word : sortedWords.keySet()){
            List<String> group = new ArrayList<>();
            for(int index : sortedWords.get(word)){
                group.add(words.get(index));
            }
            results.add(group);
        }
        return  results;
    }

    public static void main(String args[]){
        List<List<String>> res =  groupAnagrama(Arrays.asList("yo","act","flop","tac","foo","cat","oy","olfp"));
        System.out.println(res.size());
        System.out.println("cabcdab".indexOf("ab"));

    }
}
