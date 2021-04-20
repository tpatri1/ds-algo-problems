package com.trinath.dsalgo.lb.sliding_window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CharacterReplacement {
    public static int findLength(String str, int k) {
        int num = k;
        int maxLen = 1;
        int currLen = 1;
        char currChar = str.charAt(0);
        int startWindow = 0;
        Map<Character,Integer> charFreq = new HashMap<>();
        charFreq.put(currChar,charFreq.getOrDefault(currChar,0)+1);
        for(int i=1; i<str.length(); i++){
            char c = str.charAt(i);
            if(c==currChar){
                charFreq.put(c, charFreq.getOrDefault(c,0)+1);
                currLen++;
            }else{
                if(k>0){
                    charFreq.put(c, charFreq.getOrDefault(c,0)+1);
                    k--;
                    currLen++;
                }else{
                    while(charFreq.size()>num){
                        char c1= str.charAt(startWindow);
                        startWindow++;
                        int newFreq = Math.max(charFreq.get(c1)-1, 0);
                        charFreq.put(c1,newFreq);
                        if(newFreq==0){
                            charFreq.remove(c1);
                        }
                    }
                    //set new current char
                    int max = 0;
                    for(char c2 : charFreq.keySet()){
                        if(charFreq.get(c2)>max){
                            currChar = c2;
                            max = charFreq.get(c2);
                        }
                    }
                    k=num;
                    maxLen = Math.max(currLen, maxLen);
                    currLen=1;
                }

            }

        }
        return Math.max(maxLen, currLen+num);
    }
    public static int replaceOnesLength(int[] arr, int k) {
        int startWindow =0 ;
        int maxLen =0;
        int maxRepeatNums = 0;
        int currLen = 0;
        int num = k;
        Map<Integer, Integer> freqMap = new HashMap<>();
        for(int windowEnd=0; windowEnd<arr.length; windowEnd++){
            int curr = arr[windowEnd];
            // freqMap.put(curr,freqMap.getOrDefault(curr, 0)+1);
            // maxRepeatNums = Math.max(freqMap.get(curr),maxRepeatNums );

            // if(windowEnd-startWindow+1>k){
            //   int leftNum = arr[startWindow];
            //   freqMap.put(leftNum,freqMap.get(leftNum)-1);
            //   startWindow++;
            // }
            // maxLen = Math.max(maxLen,windowEnd-startWindow+1);
            if(curr!=1){
                if(k>0){
                    k--;
                    currLen++;
                }else{
                    if(arr[startWindow]==0){
                        startWindow++;
                        //k++;
                        currLen--;
                        //windowEnd--;
                    }else{
                        //reset
                        maxLen=Math.max(currLen,maxLen);
                        currLen = 1;
                        k=num;
                        startWindow =windowEnd;

                    }
                }
            }else{
                currLen++;
            }
            maxLen=Math.max(currLen,maxLen);
        }
        return maxLen;
    }
    private static int numSubArrayProduct(int[] arr, int k){
        int count = 0;
        int left =0; int product = 1;
        List<List<Integer>> result = new ArrayList<>();
        for(int right=0; right<arr.length; right++){
            product*=arr[right];
            while(product>=k){
                product/=arr[left++];
            }
//            count+=right-left+1;
            List<Integer> tempList = new ArrayList<>();
            for(int i=right; i>=left; i--){
            tempList.add(0,arr[i]);
            result.add(new ArrayList<>(tempList));
            }
        }
        return result.size();
    }

    public static int remove(int[] arr) {
//        int nextNonDuplicate = 1; // index of the next non-duplicate element
//        for (int i = 1; i < arr.length; i++) {
//            if (arr[nextNonDuplicate - 1] != arr[i]) {
//                arr[nextNonDuplicate] = arr[i];
//                nextNonDuplicate++;
//            }
//        }
        int curr =1, nonDuplicate = 0;
        while(curr<arr.length){
            if(arr[nonDuplicate]!=arr[curr]){
                ++nonDuplicate;
                if(curr-nonDuplicate >1){
                    arr[nonDuplicate]=arr[curr];
                }
                curr++;
            }else{
                curr++;
            }
        }

        return nonDuplicate+1;
    }
    public static void main(String args[]){
        int[] arr = new int[] { 2, 3, 3, 3, 6, 9, 9 };
        System.out.println(remove(arr));
        System.out.println(numSubArrayProduct(new int[]{2, 5, 3, 10 }, 30));
        System.out.println(replaceOnesLength(new int[]{0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1},3));
        System.out.println(findLength("aabccbb",2));
    }
}
