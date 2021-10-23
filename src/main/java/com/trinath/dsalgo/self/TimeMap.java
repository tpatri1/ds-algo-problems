package com.trinath.dsalgo.self;

import java.util.*;

class TimeMap {

     Map<String, TreeMap<Integer, String>> timeMap;
    /** Initialize your data structure here. */
    public TimeMap() {
        timeMap = new HashMap<>();
    }

    public  void set(String key, String value, int timestamp) {
        TreeMap<Integer, String> map = timeMap.getOrDefault(key, new TreeMap<>());
        map.put(timestamp, value);
        timeMap.put(key, map);
    }

    public  String get(String key, int timestamp) {
        TreeMap<Integer, String> map = timeMap.get(key);
        if (map == null || map.isEmpty() || !map.containsKey(key)) {
            return "";
        }
        Map.Entry<Integer, String> floor = map.floorEntry(timestamp);
        if (floor == null) return "";
        return floor.getValue();

    }

    public static void main(String args[]){

        ArrayList<String> list = new ArrayList<>();
        list.add("trin");
        list.add("0");
        list.remove(0);
//        TimeMap tm = new TimeMap();
//        tm.set("trinath","lunch", 1);
//        tm.set("trinath","dinner", 2);
//        tm.set("trinath","breakfast", 3);
//
//        System.out.println(tm.get("trinath",0));
        Queue<Integer> queue = new LinkedList();
        StringBuilder sb= new StringBuilder();
        sb.append(" hi ").append(" ").append(" hello ").append("t");

        sb.substring(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());
    }
}