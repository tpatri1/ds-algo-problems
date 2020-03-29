package com.trinath.dsalgo.lb;

import java.util.*;
import java.util.stream.Collectors;

public class StringProblems {
//Reverse words in a string -- Time O(n) and Space O(n)
    private String reverseWords(String input){
        String result= "";
        int to=input.length()-1;
        for(int i=input.length()-1; i>=0; i--){
            if(Character.isWhitespace(input.charAt(i)) || i==0){
            result+=input.substring(i,to+1).trim()+" ";
            to=i;
            }
        }
        return result;
    }
  //Alt - In - place reverse T- O(N) space O(1)
  private static void reverseString(char[] chars, int start, int end){
      if(chars==null || chars.length==0){
          return ;
      }
      char temp;
      while(start<end){
          temp=chars[start];
          chars[start] = chars[end];
          chars[end]=temp;
          start++;
          end--;
      }
  }

    public static void reverseWords (char[] sentence) {
        //TODO: Write - Your - Code

        //Reverse entire string then words split by white space
        reverseString(sentence, 0, sentence.length-1);
        int start=0, end;
        for(int i=0; i<sentence.length; i++){
            if(Character.isWhitespace(sentence[i]) || i==sentence.length-1){
                if(i==sentence.length-1){
                    end=i;
                }else{
                    end = i-1;
                }

                reverseString(sentence,start,end);
                start=i+1;
            }
        }
    }
    static void removeDuplicates1(char[] str){
        //TODO: Write - Your - Code
        //Time O(n) and space O(1)
        Set<Character> uniqueChars = new HashSet<Character>();
        for(int i=0; i< str.length; i++){
            if(uniqueChars.contains(str[i])){
                str[i]='\0';
                continue;
            }
            uniqueChars.add(str[i]);
        }
    }

    static void removeDuplicates(char[] str) {
        Set<Character> uniqueChars = new LinkedHashSet<Character>();//Ordered
        int writeIndex=0;
        for(int i=0; i< str.length; i++){
            if(!uniqueChars.contains(str[i])){
                uniqueChars.add(str[i]);
                str[writeIndex]= str[i];
                writeIndex++;
            }
           //for loop increase read index always
        }
        if(writeIndex!=str.length-1){
            str[writeIndex]='\0';//Insert null char to separate
        }
    }
    static void removeWhiteSpaces (char[] s) {
        //TODO: Write - Your - Code
        int readIndex=0;
        int writeIndex=0;
        while(readIndex!=s.length){
            if(!Character.isWhitespace(s[readIndex])){
                s[writeIndex]=s[readIndex];
                ++writeIndex;
            }
            ++readIndex;
        }
        if(writeIndex!=s.length-1){
            s[writeIndex]='\0';
        }
    }
    public static boolean canSegmentString(String s, Set<String> dictionary) {

        for(int i=1; i<s.length(); i++){
            String first =s.substring(0,i);//end index exclusive
            if(dictionary.contains(first)){
            String rest = s.substring(i);// inclusive index
                if(rest.isEmpty()||dictionary.contains(rest)||canSegmentString(rest,dictionary)){
                    return true;
                }
            }
        }
        return false;
    }
//Below Wrong solution ,above is right
    public static boolean canSegmentStringAlt(String s, Set<String> dictionary) {
        String prefix ="";
        for(int i=0; i<s.length(); i++){
            prefix+=s.charAt(i);
            if(dictionary.contains(prefix)){
                prefix="";
            }
        }
        if(prefix.isEmpty()){
            return true;
        }
        return false;
    }

    private static Set<String> getPalindromicSubSets(String s){
        Set<String> subStrings = new HashSet<>();
        List<Character> chars = s.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        getSubStringsHelper(chars, "",subStrings);
        return subStrings;
    }

    private static void getSubStringsHelper(List<Character> chars, String partialSol, Set<String> subStrings) {
        if(chars.size()==0){
            if(isPalindrom(partialSol)){
                subStrings.add(partialSol);
            }
        }
        for(Character c: chars){

            List<Character> newChars = new ArrayList<>(chars);
            newChars.remove(c);
            getSubStringsHelper(newChars,partialSol,subStrings);
            //partialSol+=c;
            getSubStringsHelper(newChars,partialSol+c,subStrings);
        }
    }

    /**
     * Add each character to the previously generated substrings to generate new substrings ex- [a,b,c] - a,  b,ab, c,bc,abc
     * //There is a alternate method to get Substring using 2 for loops generate all sub strings by increasing j starting j=0 and adding one char each time for for all i
     * @param s
     * @return
     */
    private static List<String> getPalindromicSubStrings(String s){
        List<String> result= new ArrayList<>();
        List<String> prevList = new ArrayList<>();
        for(int i=0; i<s.length(); i++){
            if(isPalindrom(""+s.charAt(i))) {
                result.add("" + s.charAt(i));
            }
            List<String> newPrevList = new ArrayList<>();
            newPrevList.add(""+s.charAt(i));
            for(String ps: prevList){
                String ps1 = ps+s.charAt(i);
                if(isPalindrom(ps1)) {
                    result.add(ps1);
                }
                newPrevList.add(ps1);
            }
            prevList = new ArrayList<>(newPrevList);
        }
        return result;
    }
//Alternate using DP  https://www.youtube.com/watch?v=EIf9zFqufbU
    static int getPalindromicSubStringsCountDP(String s){
        int size = s.length();
        int count=0;
        //Create the matrix
        int dp[][] = new int[size][size];
        //Case -1 Fill the char position to 1= true as each of them are palindrom a[i][i] = 1 for single char
        for(int i=0; i<size; i++){
            dp[i][i] = 1;
            count++;
        }
        // dp[row][col]==>> dp[i][j] -> substring s.substr(i,j).. i-> row j-> column ... palindromic substring is represented by 1 from i th index to j for s.substr(i,j); as we discard lower half(i.e, a[i][j] i<j) of the matrix for a[i][j]=a[j][i])
        for(int col=1; col<size; col++){ // move in column wise and change the colum when it reacches i<j
            for(int row=0; row<col; row++){// i<j until it reaches lower half, so only upper half across column then change column
                if(row==col-1 && s.charAt(row)==s.charAt(col)){//case 2 - 2 adjacent char substring as i=j-1 and if they are same is palindromic
                    dp[row][col]=1;
                    count++;
                }
                else if(dp[row+1][col-1]==1 && s.charAt(row)==s.charAt(col)){// Case 3- dp[i+1][j-1]==1 the middle substring is palindromic and last two char are same
                    dp[row][col]=1;
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isPalindrom(String s){
        //if(s.length()<2) return false;
        int start=0;
        int end = s.length()-1;
        while(start<end){
            if(s.charAt(start)!=s.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
//Using recursion
    private static boolean isPalindromRec(String s){
        if(s.length()==1){
            return true;
        }
        if(s.length()==2){
            return s.charAt(0)==s.charAt(1);
        }
        return s.charAt(0)==s.charAt(s.length()-1) && isPalindrom(s.substring(1,s.length()-1)); // if len>3 if first and last char matches and inner substring is palindrom
    }

    static char[] getArray(String t) {
        char[] s = new char[t.length()];
        int i = 0;
        for (; i < t.length(); ++i) {
            s[i] = t.charAt(i);
        }
        return s;
    }

    public static void main(String  args[]){
        StringProblems sp = new StringProblems();
        System.out.println(sp.reverseWords("this is not good job"));
        char[] chars = getArray("To be or not to be");
        sp.reverseWords(chars);
        System.out.println(new String(chars));

        char[] s = getArray("You are amazing");
        System.out.println(s);
        reverseWords(s);
        System.out.println(s);
        char[] s1 = getArray("We love Java");
        removeDuplicates(s1);
        System.out.println(s1);

        char[] s2 = getArray("We love Java     pass");
        removeWhiteSpaces(s2);
        System.out.println(s2);

        Set < String > dictionary = new HashSet < String > ();
        String s4 = new String();
        s4 = "helloonnow";

        dictionary.add("hello");
        dictionary.add("hell");
        dictionary.add("on");
        dictionary.add("now");
        if (canSegmentStringAlt(s4, dictionary)) {
            System.out.println("String Can be Segmented ::using canSegmentStringAlt() method");
        } else {
            System.out.println("String Can NOT be Segmented ::using canSegmentStringAlt() method");

        }
        if (canSegmentString(s4, dictionary)) {
            System.out.println("String Can be Segmented ::using canSegmentString() method");
        } else {
            System.out.println("String Can NOT be Segmented ::using canSegmentString() method");

        }

        System.out.println(getPalindromicSubSets("aabbbaa"));
        long t1 = System.nanoTime();
        System.out.println(getPalindromicSubStrings("aabbbaa"));
        long t2= System.nanoTime();
        System.out.println("Timetaken n^2: "+(t2- t1));
        System.out.println(isPalindromRec("aabdcaa"));
        long t3= System.nanoTime();
        System.out.println(getPalindromicSubStringsCountDP("aabbbaa"));
        System.out.println("Timetaken using dp: "+(t3- t2));
    }

}
