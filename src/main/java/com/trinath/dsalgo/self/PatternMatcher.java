package com.trinath.dsalgo.self;

import java.util.HashMap;
import java.util.Map;

public class PatternMatcher {
    //pattern is represented by x and y
    public static String[] patternMatcher(String pattern,String str) {
        String[] res = new String[]{};
        //all patterns
        char[] patternArray = pattern.toCharArray();
        Map<Character, Integer> patternCount = new HashMap<>();
        //counts and first y position
        int yFirstPos = -1;
        char yChar = 'y';
        char xChar ='x';
        if(pattern.charAt(0)!='x'){
            yChar ='x';
            xChar ='y';
        }
        for (int i = 0; i < patternArray.length; i++) {
            char c = patternArray[i];
            if (yFirstPos == -1 && c == yChar) {
                yFirstPos = i;
            }
            patternCount.put(c, patternCount.getOrDefault(c, 0) + 1);
        }
        //core logic
        int strLen = str.length();
        int lenX = 1;
        double lenY = -1;
        if (patternCount.containsKey(yChar)) {
            while (lenX < strLen) {
                lenY = (strLen - patternCount.get(xChar)*lenX) / patternCount.get(yChar);
                if (lenY % 1 > 0) {
                    lenX++;
                    continue;
                }else if(lenY>0){
                    res = match(lenX, (int)lenY,yFirstPos, str, patternArray,xChar, yChar);
                    if(res.length>0){
                        return res;
                    }else{
                        lenX++;
                    }
                }else{
                    return res;
                }
            }

        }else{//only X
            while(lenX<strLen){
                String x = str.substring(0,lenX);
                StringBuilder builder = new StringBuilder();
                for(char c: patternArray) {
                    builder.append(x);
                }
                if(builder.toString().equals(str)){
                    if(xChar=='x')
                    return new String[]{x,""};
                    else{
                        return new String[]{"",x};
                    }
                }
                lenX++;
            }
        }
        return res;
    }
    static  String[] match(int lenX, int lenY,int yFirstPos, String str, char[] chars, char xChar, char yChar){
        int yIdx = yFirstPos*lenX;
        String x = str.substring(0,lenX);
        String y = str.substring(yIdx,yIdx +lenY);
        StringBuilder builder = new StringBuilder();
        for(char c: chars){
            if(c==xChar){
                builder.append(x);
            }else if(c==yChar){
                builder.append(y);
            }
        }
        if(str.equals(builder.toString())){
            if(yChar=='y')
            return new String[]{x,y};
            else {
                return new String[]{y, x};
            }
        }else{
            return new String[] {};
        }
    }

    public static void main(String args[]){
        String pattern ="yxx";
        String str = "yomama";
        PatternMatcher matcher = new PatternMatcher();
         String[] res = matcher.patternMatcher(pattern, str);
         if(res.length==2) {
             System.out.println(res[0]+" "+res[1]);
         }else{
             System.out.println("None");
         }
    }
}
