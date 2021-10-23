package com.trinath.dsalgo.self.sliding_window;

import java.util.*;

class StringPermutation {
    public static boolean findPermutation(String str, String pattern) {
        //One way find all permutations and contains search
        //Create a map
        Map<Character, Integer> freq = new HashMap<>();
        for(int i=0; i<pattern.length(); i++){
            freq.put(pattern.charAt(i), freq.getOrDefault(pattern.charAt(i), 0)+1);
        }
        boolean flag = false;
        Map<Character, Integer> freq1 = new HashMap<>(freq);
        //Iterate string and find
        System.out.println(" New tc" );
        int i=0;
        while(i<str.length()){
            char c = str.charAt(i);
            while(i<str.length() && freq1.containsKey(str.charAt(i))){
                c = str.charAt(i);
                int f = freq1.get(c)-1;
                System.out.println("c: "+c+ " freq "+f);
                freq1.put(c,f);
                if(f<=0){
                    freq1.remove(c);
                }
                if(freq1.size()==0){
                    return true;
                }
                i++;

            }
            freq1 = new HashMap<>(freq);
            i++;
        }

        return false;
    }

    public static List<Integer> findStringAnagrams(String str, String pattern) {
        List<Integer> resultIndices = new ArrayList<Integer>();
        //like previous problem, I am taking sliding window approach
        Map<Character, Integer> freqMap = new HashMap<Character, Integer>();
        for(int i=0; i<pattern.length(); i++){
            freqMap.put(pattern.charAt(i), freqMap.getOrDefault(pattern.charAt(i), 0)+1);
        }
        int start =0, matched=0;
        for(int end=0; end<str.length(); end++){
            char rightChar = str.charAt(end);
            if(freqMap.containsKey(rightChar)){
                int f = freqMap.get(rightChar);
                freqMap.put(rightChar, --f);
                if(f==0){
                    //dont need to remove from freq map
                    matched++;
                }
            }
            //if the bottom code reduces match count top portion adds so continues
            if(pattern.length()==matched){
                resultIndices.add(start);//or we can take pattern.lebgth() elements from start
            }
            //shrink, start shrinking it after pattern size
            if(end>=pattern.length()-1){
                char leftChar = str.charAt(start++);
                if(freqMap.containsKey(leftChar)){
                    if(freqMap.get(leftChar)==0){
                        matched--;
                    }
                    freqMap.put(leftChar,freqMap.get(leftChar)+1 );
                }
            }
        }
        return resultIndices;
    }

    public static void main(String args[]){
//        System.out.println(findPermutation("oidbcaf","abc"));
        //System.out.println(findPermutation("bcdxabcdy","bcdyabcdx"));

        List<Integer> list = findStringAnagrams("abbcabc","abc");
        list.stream().forEach(i->System.out.println(i));
        list.size();
    }
}
