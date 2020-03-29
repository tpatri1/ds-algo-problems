package com.trinath.dsalgo.lb;

import org.jcp.xml.dsig.internal.dom.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//Head recursion vs tail recursion where processing happens where? Tail recursion is like a loop so, no need to hold to prev state, head recursion go to to top of recusrsive stack first so it holds the rpev state
//https://www.youtube.com/watch?v=lG6HxO7cDRw
//https://www.youtube.com/watch?v=qH7fVuYlOOc&list=PLNmW52ef0uwsMppECkGBpoao1p0_xWX2E
public class RecursionProblems {

    LinkedListProblems.Node head;
    LinkedListProblems.Node curr;
    //8.If linked list is a Palindrome
    boolean isPalindrome(LinkedListProblems.Node head)
    {
        curr = head;
        if (head == null)
            return true;
        boolean result = isPalindrome(head.next);
        if (result == false)
            return false;

        boolean result1 = (head.val == curr.val);
        curr = curr.next;

        return result1;
    }

    //TODO: Removing duplicates in a String
    //TODO: 10.GCD
    //TODO: 11.Prime Number
    //TODO: 12Palindromic String
    //TODO: 13Number of occurance of a number in an array
    //TODO: 14.Invert position of numbers in an array


    //15 DFS - DONE in GraphOperations.class
    //16.Topological Sort - DONE in GraphOperations.class
    //17.Binary Search of a tree - inOrder() , preOrder(), postOrder() traversal - DONE in TreeOperations.class


    //18. Search a value in Linked List
    static LinkedListProblems.Node searchLinkedList(LinkedListProblems.Node head, int val){
        //LinkedListProblems.Node result = null;
        if(head==null || head.val==val){
            if(head==null) {
                System.out.println("Search Not found: "+val);
            }else{
                System.out.println("Search found: "+head.val);
            }
            return head;
        }
        LinkedListProblems.Node result = searchLinkedList(head.next, val);// Tail recursion , so no processing required after recursive call
        return result;
    }
    //Print reverse Linkedlist
    static void printReverseLinkedList(LinkedListProblems.Node head){
        if(head==null){
            return;
        }
        printReverseLinkedList(head.next);// head recursion will to top to recursive stack first before processing
        System.out.println(head.val);// process
    }
    //19. Sum numbers in a linked list
    static int sumLinkedList(LinkedListProblems.Node head){
        int sum=0;
        if(head==null){
            return sum;
        }
        sum=sumLinkedList(head.next);// head recursion will to top to recursive stack first before processing
        sum+=head.val; //process Same as one line  sum+= head.val+sumLinkedList(head.next);
        return sum;
    }
    //TODO: 20. Sort an Array

    //21.Reverse a singly LinkedList using recursion
    LinkedListProblems.Node reverseLinkedList(LinkedListProblems.Node head){
        if(head==null || head.next==null){
            return head;
        }
        LinkedListProblems.Node result = reverseLinkedList(head.next);
        head.next.next = head;//new link
        head.next = null;//null existing links
        return result;
    }
    //22.Product sum
    public static int productSum(List<Object> array) {
        return productSum(array, 1);
    }

    public static int productSum(List<Object> array,  int factor) {// List can have nested list
        int sum=0;
        for(Object o1: array){
            if(o1 instanceof Integer){// This is not a Base case so no return stmt  as used in loop as well
                sum = sum +  (Integer)o1;
            }
            else if(o1 instanceof List){
               sum=sum+ productSum((List)o1,factor+1);
            }

        }

        return sum*factor;// This is the base case at the end of the loop
    }

    private static int sumArray(List<Integer> arr){
        int sum=0;
        return sumArray(arr,0);
        //return sumArray1(arr,0,0);
    }
    private static int sumArray(List<Integer> arr,int index){
        int sum =0;
        if(arr.size()==index){
            return sum;
        }

        sum=arr.get(index)+sumArray(arr, index+1);//small work and deligate rest, be careful when you passs result by reference
        return sum;


    }
//Alternate
    private static int sumArray1(List<Integer> arr,int index, int sum){
        int res =0;
        if(arr.size()==index){
            return sum;
        }
        sum= sum+ arr.get(index);
        res=sumArray1(arr, index+1,sum);//small work and deligate rest, be careful when you passs result by reference

        return res;


    }
//static List<List<Integer>> result = new ArrayList<>(); // Almost same as pass by reference , instead use a class var and use return  at the end

    private static List<List<Integer>>  getPermutation(List<Integer> array){
        List<List<Integer>> result = new ArrayList<List<Integer>>();
          getPermutation(array,new ArrayList<>(),result);
         return result;
    }

    private static void  getPermutation(List<Integer> array, List<Integer> partialSol,List<List<Integer>> result) {

        if(array.size()==0){

            result.add(partialSol);
            //return result; // Not required as it is not used in recursive call, just add to result is enough;

        }
        for(int i=0; i< array.size();i++){
            List<Integer> newArray = new ArrayList<>(array);
            List<Integer> newPartialSol = new ArrayList<>(partialSol);
            newPartialSol.add(newArray.get(i));
            newArray.remove(i);
            getPermutation(newArray,newPartialSol,result);// Not required to make result = ; or we can do pass by reference and return void
        }
    //return result; // We can return void as well as we pass by reference
    }

    //Alternate
    private static List<List<Integer>>  getPermutationAlt(List<Integer> array){
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        getPermutationAltHelper(array,result, 0);
        return result;
    }
    private static void  getPermutationAltHelper(List<Integer> array,List<List<Integer>> result, int index) {

        if(index==array.size()-1){
            result.add(new ArrayList<>(array));

        }
        for(int j=index; j< array.size();j++) {
            swap(array, index, j);
            getPermutationAltHelper(array, result,index+1);// index is changing so base case has index not j
            swap(array, index, j);
        }

    }
    private static void swap(List<Integer> list, int i, int j){
        int temp = list.get(i);
        list.set(i,list.get(j));
        list.set(j, temp);
    }


    public static void main(String args[]){
        LinkedListProblems.Node head = new LinkedListProblems.Node(1);
        head.next = new LinkedListProblems.Node(2);
        head.next.next = new LinkedListProblems.Node(3);
        head.next.next.next = new LinkedListProblems.Node(4);
        head.next.next.next.next = new LinkedListProblems.Node(5);
        head.next.next.next.next.next = new LinkedListProblems.Node(6);
        head.next.next.next.next.next.next = new LinkedListProblems.Node(7);
        head.next.next.next.next.next.next.next = new LinkedListProblems.Node(8);
        List<Object> test = new ArrayList<Object>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("Print reverse Linked List: ");
        RecursionProblems.printReverseLinkedList(head);
        System.out.println("Sum of Linked List: "+RecursionProblems.sumLinkedList(head));
        RecursionProblems.searchLinkedList(head,8);
        RecursionProblems.searchLinkedList(head,9);

        //System.out.println(RecursionProblems.productSum(test));

        List<Object> test1 =
                new ArrayList<Object>(Arrays.asList(1, 2, new ArrayList<Object>(Arrays.asList(3)), 4, 5));
        //System.out.println(RecursionProblems.productSum(test1));

        List<Object> test2 = new ArrayList<Object>();
        test2.add(
                new ArrayList<Object>(
                        Arrays.asList(
                                new ArrayList<Object>(
                                        Arrays.asList(
                                                new ArrayList<Object>(
                                                        Arrays.asList(new ArrayList<Object>(Arrays.asList(5)))))))));

        System.out.println(RecursionProblems.productSum(test2));

        List<List<Integer>> permutations = RecursionProblems.getPermutation(Arrays.asList(1,2));
        System.out.println(permutations);
        System.out.println(RecursionProblems.getPermutationAlt(Arrays.asList(1,2,3)));


    }
}
