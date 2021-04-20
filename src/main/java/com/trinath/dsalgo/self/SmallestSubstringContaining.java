package com.trinath.dsalgo.self;

import java.util.HashMap;
import java.util.Map;

public class SmallestSubstringContaining {
    public static String smallestSubstringContaining(String bigString, String smallString) {
        Map<Character, Integer> charCountMap = new HashMap<Character, Integer>();
        for(int i=0; i< smallString.length(); i++){
            charCountMap.put(smallString.charAt(i),charCountMap.getOrDefault(smallString.charAt(i),0)+1 );
        }
        int left = 0;
        int right = 0;
        String smallesSubString = "";
        Map<Character,Integer> currMap = new HashMap<Character,Integer>();
        int count =0;
        while(left<bigString.length()-smallString.length()+1 && right<bigString.length()){
            right = getSmallestSubString(bigString,smallString,left,right,charCountMap, currMap,count);
            String currSmallest = bigString.substring(left, right+1);
            System.out.println("currSmallest "+currSmallest);
            if(smallesSubString.isEmpty() || (!currSmallest.isEmpty() && smallesSubString.length()>currSmallest.length())){
                smallesSubString = currSmallest;
            }
            count=charCountMap.size();
            char  c = bigString.charAt(left);
            if(currMap.containsKey(c)) {
                int num = currMap.get(c);
                --num;
                if (num == 0) {
                    currMap.remove(c);
                } else {
                    currMap.put(c, num);
                    if(num<charCountMap.get(c)){
                        count--;
                    }
                }

            }
            left++;
            c = bigString.charAt(left);
            while(!charCountMap.containsKey(c)) {
                left++;
                c = bigString.charAt(left);
            }

        }
        return smallesSubString;
    }

    public static int getSmallestSubString(String bigString, String smallString, int left, int right, Map<Character, Integer> charCountMap, Map<Character, Integer> currMap, int count){
        for(int i=right; i<bigString.length(); i++){
            char c = bigString.charAt(i);
            if(charCountMap.containsKey(c)){
                currMap.put(c,currMap.getOrDefault(c,0)+1 );
                if(currMap.get(c)==charCountMap.get(c)){
                    count++;
                }
                System.out.println("count "+count+" map size "+charCountMap.size());
                if(count==charCountMap.size()){
                    return i;
                }
            }
        }

        return bigString.length()-1;
    }

    public static void main(String args[]){
        System.out.println(smallestSubstringContaining("abcd$ef$axb$c$","$$abf"));
    }
}
