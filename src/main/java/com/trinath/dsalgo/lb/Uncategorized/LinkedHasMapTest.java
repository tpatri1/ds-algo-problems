package com.trinath.dsalgo.lb.Uncategorized;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class LinkedHasMapTest {
    public static void main(String args[]){
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap();
        map.put(1,11);
        map.put(5, 55);
        map.put(3, 33);
        map.put(2, 22);
        Iterator itr = map.keySet().iterator();

    }
}
