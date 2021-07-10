package com.trinath.dsalgo.self.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CollectionsTest {
    public static void main(String args[]){
        List<Integer> list = Arrays.asList(1,2, 3, 10, 2, 4, 5);
        System.out.println(list);
        //list.add(8);// cant do
        //list.remove();
        System.out.println("freq of 4: " +Collections.frequency(list, 4));

        List<Integer> list1 = Arrays.asList(11,12, 13);
        Collections.copy(list, list1);
        System.out.println(list);
        Collections.fill(list1, 2);
        System.out.println(list1);
        Collections.unmodifiableList(list);
        //list.add(10);
    }
}
