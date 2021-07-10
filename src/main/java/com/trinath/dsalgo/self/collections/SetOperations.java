package com.trinath.dsalgo.self.collections;

import java.util.*;
import java.util.stream.Collectors;

public class SetOperations {

    private static void hashSetOps(){
        Set<Integer> set = new HashSet<>();
        System.out.println(set.add(1));
        System.out.println(set.add(3));
        System.out.println(set.add(1));
        System.out.println(set.contains(1));
        System.out.println(set);
        set.remove(3);
        set.add(33);
        set.add(12);
        List<Integer> list = new ArrayList<Integer>(set);
        //Collections.sort(list);
        System.out.println(set.stream().sorted().collect(Collectors.toList()));
        //System.out.println(list);
        set.clear();
        System.out.println(set);
        System.out.println(set.isEmpty());

    }
    private static void treeSetOps(){
        Set<Integer> treeSet = new TreeSet<>(Comparator.reverseOrder());
        treeSet.add(20);
        treeSet.add(10);
        treeSet.add(30);
        treeSet.add(3);
        treeSet.add(31);
        System.out.println(treeSet);
        System.out.println(treeSet.stream().findFirst());
        treeSet.remove(new Integer(3));
        System.out.println(treeSet);


    }

    public static void main(String args[]){
        hashSetOps();
        treeSetOps();
    String addlStatuses = "Draft,Submitted" ;
    List<String> list = new ArrayList<>(Arrays.asList("Draft","Submiuttes"));
    list.add("PRQ");
    System.out.println(list);

    }
}
