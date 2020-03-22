package com.trinath.dsalgo.lb;

import java.util.*;

public class Recursion_Permutation_Combination {
    /**
     * 1.
     * A man puts a pair of rabbit in a closed room. Each pair produce a pair every month from second month onward(let's say they take a month
     * to mature and from second months starts produce and none of the rabbit dies).
     * How many rabbits will be in that room after a year.
     * @param n number of months
     * @return number of pairs
     */
    private static Map<Integer,Integer> cache = new HashMap<>();
    private static int fib(int n){
        if(n<2){
            return 1;
        }
        else{
            if(cache.containsKey(n)){
                return cache.get(n);
            }
            int result =  fib(n-1)+fib(n-2); // make O(2^n) as calling twice each time; but the actual complexity is : > O(2^(n/2)) for right most edge goes to n/2 levels & < O(2^n) for left edge goes to n levels
            cache.put(n, result);
            return result;
        }
    }

    /**
     * 2.
     *Every step n-> 2 * n-1 so O(n) = 2^0 +2^1+2^3... 2^(n-1) = 2^n - 1 ~ O(2^n) GP series
     * @param height - size of source stack
     * @param src - intially all disks are here in a sorted manner
     * @param dest -  finally all disks wiill be moved here
     * @param temp - Used as a auxulary stack for movement for intermdiate moving for height-1 disks so that last disc can be moved just like that
     */
    private static void towerOfHanoi(int height,  Stack src, Stack dest, Stack temp){
        if(height==1){
            System.out.println("Move disk from source: "+src+" to dest : "+dest); // Bottom most disk moved from src to dest
        }
        else{
            towerOfHanoi(height-1, src,temp,dest); // Move to temp and use dest as auxulary
            System.out.println("Move disk from source: "+src+" to temp : "+temp); // height-1 disks moved to temp. So that Bottom most disk moved from src to dest beofre proceeding Move from temp to dest
            towerOfHanoi(height-1, temp,dest,src); // Move  temp to dest and use src as auxulary
            //System.out.println("Move disk from temp: "+temp+" to dest : "+dest); // height-1 disks moved.
        }
    }

    //3. alternate BFS Solution that has space complexity of O(2^n). Time complexiity is O(2^n)
    private static List enumurateBinaryString(int height) {
        if(height==1 ){
            List result = Arrays.asList("0","1");
            return result;

        }
        else{
            List<String> prev= enumurateBinaryString(height - 1); // DONT use result as reference as there is no use
            List result = new ArrayList<>();// We must reset the result as we are acting on the result
            for(int i=0; i< prev.size(); i++ ){
                result.add(prev.get(i)+"0");
                result.add(prev.get(i)+"1");
            }
            return result;
        }

    }
    /**
     * 4.
     * This is a  DFS Solution that has space complexity of O(n) as at a point of time there are only one root to leaf chain exists in memory, so O(n). Time complexiity is O(2^n)
     * Input - len=2 - print 00, 01, 10,11
     * Print leaf level nodes that has the enumurated string
     * @param height is length of the string as well
     */
    private static List<String> binaryStrings(int height){
         return binaryStringsHelper("",height);
    }
    private static List<String> result = new ArrayList<>();
    private static List binaryStringsHelper(String slate, int n){
        if(n==0){
            result.add(slate);
            return result;
        }
        else{
            binaryStringsHelper(slate+"0",n-1);
            binaryStringsHelper(slate+"1",n-1);
        }
        return  result;
    }
    //5. Decimal String -- Permutation this allows repitation 10*10*10
    private static List<String> decimalStrings(int height){
        return decimalStringsHelper("",height);
    }

    private static List decimalStringsHelper(String slate, int n) {
        if (n == 0) {
            result.add(slate);
            return result;
        } else {
            for (int i = 0; i < 10; i++) { // In next problem Generalization is done for any type of permutation, 10 can be represented  0-9 array, if we ask repitiion is not allowed then we need to remove used 'i' from this
                decimalStringsHelper(slate + i, n - 1);
            }
            return result;
        }
    }
//6. Permutation of inputStriing char set where repetition is allowed : charString: "abc" - ans : aaa, abb,abc,.. 3*3*3 = 25 permutation
    private static void permutationsWithRepetition(String inputString){
        int index=inputString.length();
        permutationWithRepetitionHelper("",inputString, index);
    }
    //slate is the partial solution, subprobelm is index , as we dont modify the charString for each sub problem so, dont need to pass a char array, we ecan do by a just String
    private static void permutationWithRepetitionHelper(String slate, String charString, int index) {
        if (index == 0) {
            System.out.println(slate);
        } else {
            for (int i = 0; i < charString.length(); i++) {
                permutationWithRepetitionHelper(slate+charString.charAt(i),charString, index-1 );
            }
        }
    }


    //General pattern for  Find all permutaion of a string, when repitation is NOT allowed and ALLOWED - Similar problem as Decimal String
    private static void permutationsWORepitition(String str){
        ArrayList<Character> charList = new ArrayList<>();
        for(int i=0; i<str.length(); i++){
            charList.add(str.charAt(i));
        }
        permutationWORepetitionHelper("",charList);
    }
//7. We can do using a pointer and charStriing input or char array, easy with char arraylist as we can modify for each subproblem
    private static void permutationWORepetitionHelper(String slate, List charList) {
        if (charList.size() == 0) {
            System.out.println(slate);
        } else {
            for (int i = 0; i < charList.size(); i++) {
                //slate +=charList.get(i);
                ArrayList<Character> newList = new ArrayList<>(charList); // as arraylist is mutable, so dont mess
                newList.remove(i);// Remove so that it wont repeat, the subproblem is the char less from the array that is added to slate
                permutationWORepetitionHelper(slate+charList.get(i),newList );
            }
        }
    }

    //Combination - exclude or Include an element from the list in each recursive call
    //https://www.youtube.com/watch?v=LGs3UzZ_8B0&list=LLKVWfjrciwHvt6ZUPrdSEWg&index=27&t=634s
    private static List<List<Integer>> createAllSubSets(List<Integer> list){
        List<List<Integer>> resultSet = new ArrayList<List<Integer>>();
        //return createAllSubSets(list,new ArrayList<>(),resultSet);
        return createAllSubSetsIterative(list);
    }
    private static List<List<Integer>> createAllSubSets(List<Integer> list, List<Integer> partialSol, List<List<Integer>> resultSet){

        //Base Case at leaf node
        if(list.size()==0){
            System.out.println("com"+partialSol);
            resultSet.add(partialSol);
        }
        else {
            //No for loop as we are need to remove the first elemt each time
            List<Integer> subList = new ArrayList<>(list);
            subList.remove(0);
            List<Integer> partialSolutionNew = new ArrayList<>(partialSol);
            partialSolutionNew.add(list.get(0));
            //exclude the element
            createAllSubSets(subList, partialSol,resultSet);
            //OR include the element

            createAllSubSets(subList, partialSolutionNew,resultSet);
        }

        return resultSet;
    }
    //Iterative solution for combination/subsets problem, Add the iterative element to each subset created before and add them as new subsets
    //Time and space both O(n*2^n)
    private static List<List<Integer>> createAllSubSetsIterative(List<Integer> list){
        List<List<Integer>> resultSet = new ArrayList<List<Integer>>();
        resultSet.add(new ArrayList<>());
        for(Integer item: list){
            List<Integer> subset = new ArrayList<>();
            int existingSize = resultSet.size();
            for(int i=0; i<existingSize; i++){
                List<Integer> newSubset = new ArrayList<>(resultSet.get(i));
                newSubset.add(item);
                resultSet.add(newSubset);
            }
        }
        return resultSet;
    }


    public static void main(String args[]){
        long startTs = System.nanoTime();
        int fib1 = fib(20);
        long endTs = System.nanoTime();
        System.out.println( "Result : "+fib1+" time taken : ");
        System.out.println( endTs-startTs);
        Stack src = new Stack();
        src.push(9);
        src.push(7);
        src.push(5);
        src.push(3);
        src.push(1);
        towerOfHanoi(src.size(),src,new Stack(), new Stack());
        System.out.println(enumurateBinaryString(3));
        System.out.println("Alternate method: "+ binaryStrings(3));
        //binaryStrings(3);
        System.out.println("Decimal Strings: "+ decimalStrings(3));
        result = new ArrayList<>();
String str ="tets";
        System.out.println(str);
        str +="done";
        System.out.println(str);
        System.out.println("Permutation , Repition Not allowed: ");
        permutationsWORepitition("abc");
        System.out.println("Permutation , Repition  allowed: ");
        permutationsWithRepetition("cde");
        int[] a = {2, 4, 0, 9, -1, 0 , 5};
//        a= shiftZeros(a);
        System.out.println(a);

        List<List<Integer>> subsets = Recursion_Permutation_Combination.createAllSubSets(Arrays.asList(1,2,3));
        for(List<Integer> subset:subsets){
            System.out.println(subset);
        }

    }



}
