package com.trinath.dsalgo.lb.Recursion_DP;

import java.util.*;

public class LongestChainString {
    public static List<String> longestStringChain(List<String> strings) {
        strings.sort((a,b)->a.length() - b.length());
        String maxKey = strings.get(0);
        Map<String, StringChain> chainMap = new HashMap<String, StringChain>();		//if we sorted then then we can say if shorter string chaain is already computed
        for(String str: strings){
            StringChain chain  = buildStringChain(chainMap, str);
            if(!maxKey.equals(str) && (chainMap.containsKey(maxKey) && chainMap.get(maxKey).count <chain.count)||maxKey.isEmpty() && chain.count>1) {
                maxKey = str;
            }
        }
        return getResult(chainMap,maxKey);
    }

    private static List<String> getResult(Map<String, StringChain> chainMap, String maxKey){
        List<String> res = new ArrayList<>();
        while(!maxKey.isEmpty()){
            res.add(maxKey);
            maxKey = chainMap.get(maxKey).nextString;
        }
        return res;
    }

    private static StringChain buildStringChain(Map<String, StringChain> chainMap, String str){
        StringChain  chain = null;
        for(int i=0; i<str.length(); i++){
            chain = chainMap.getOrDefault(str, new StringChain("", 1));
            chainMap.put(str, chain);
            String smallerStr = str.substring(0,i)+str.substring(i+1);
            if(!chainMap.containsKey(smallerStr)){
                continue;
            }else{
                StringChain smallerChain = chainMap.get(smallerStr);
                if(!chain.nextString.equals(smallerStr) && chain.count < smallerChain.count+1){
                    chain = new StringChain(smallerStr,smallerChain.count+1);
                    chainMap.put(str,chain);
                }
            }

        }
        return chain;
    }

    static class StringChain{
        public String nextString;
        public int count;

        public StringChain(String nextString, int count){
            this.nextString = nextString;
            this.count = count;
        }
    }

    public static void main(String args[]){
        String str = null;
        str = str==null? "":str.substring(0,Math.min(5, str.length()));
        System.out.println(str);
        List<String> res =  longestStringChain(Arrays.asList("abde", "abc", "abd", "abcde", "ade", "ae", "1abde", "abcdef"));
        System.out.println("done "+res.size());


    }
}
