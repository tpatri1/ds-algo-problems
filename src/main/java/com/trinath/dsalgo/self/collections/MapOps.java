package com.trinath.dsalgo.self.collections;

import java.util.*;
import java.util.stream.Collectors;

public class MapOps {

    private static void lhmOps(){
        LinkedHashMap<Integer,Integer> lhm = new LinkedHashMap<Integer,Integer>(5, 0.75f, true);
        lhm.put(1,11);
        lhm.put(2, 22);
        lhm.put(3, 33);
        lhm.forEach((k,v) -> System.out.println(k + " " + v));//access order check
        lhm.get(1);
        lhm.forEach((k,v) -> System.out.println(k + " " + v));//access order check
    }

    private static void mapOps() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Orange", 1);
        map.put("Apple", 2);
        map.put("None", 3);
        map.replaceAll((k, v) -> {
            v = v + 10;
            return v;
        });
        System.out.println(map.containsKey("Orange"));
        System.out.println(map.containsValue(11));
        System.out.println(map.keySet());
        System.out.println(map.values());
        map.remove("None", 13);// if key and value match
        map.replace("Apple", 12, 22);
        System.out.println(map);
        map.compute("test", (k, v) -> v == null ? 10 : 20);
        map.computeIfAbsent("test1", k -> k.length());
        System.out.println(map);

        Map<Employee1, Integer> empMap = new HashMap<>();
        Employee1 emp1 = new Employee1(12, "Patri");
        empMap.put(new Employee1(123, "Trinath"), 3000);
        empMap.put(new Employee1(123, "Trinath"), 5000);
        empMap.put(new Employee1(124, "Hi"), 6000);
        empMap.put(emp1, 4000);
        //System.out.println(empMap);
        Iterator<Map.Entry<Employee1, Integer>> itr = empMap.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<Employee1, Integer> entry = itr.next();
            System.out.println(entry.getKey() + " " + entry.getValue());
            if (entry.getValue() == 5000) {
                itr.remove();
            }
        }
        System.out.println("After removal " + empMap);
        emp1.empNo = 10;
        System.out.println(empMap.get(emp1));//Mutable object so getting null, so better to have immutable object as key

    }

    private static TreeMap<String, Integer> sortByValue(TreeMap<String, Integer> map) {
        Comparator<String> valueComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return map.get(o1) - map.get(o2);
            }
        };
        TreeMap<String, Integer> sorted = new TreeMap<>(valueComparator);
        sorted.putAll(map);

        Map<String, Integer> sortedMap = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return sorted;
    }

    private static Map<String, Integer> sortByValueAlt(Map<String, Integer> map){
        Map<String, Integer> sortedMap = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return sortedMap;
    }
    public static HashMap<String, Integer> sortByValueAlt2(Map<String, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list = new LinkedList<>(hm.entrySet());

        Collections.sort(list, (l1, l2)->l1.getValue().compareTo(l2.getValue()));
        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    private static void print(Map<String, Integer> map) {
        map.keySet().stream().forEach(k -> System.out.println(k + " " + map.get(k)));
    }

    public static void main(String args[]) {
        lhmOps();
        mapOps();
        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("Apple", 12);
        map.put("Banana", 1);
        map.put("Cocunut", 11);
        System.out.println("Before Sorting");
        print(map);
        //System.out.println("After Sorting 1");
        //print(sortByValue(map));
//        System.out.println("After Sorting 2");
//        print(sortByValueAlt(map));
        System.out.println("After Sorting 2");
        print(sortByValueAlt2(map));


    }
}
     class Employee1{
        int empNo;
        String name;
        Employee1(int empNo, String name){
            this.empNo = empNo;
            this.name = name;
        }

        //If hashcode is equals does not mean the object is equals(then the default equals method is called that compares the object), so need to override equals method
        public int hashCode(){
            final int prime = 31;
            int result=1;
            result =prime*result+empNo;
            result = prime*result+((name == null) ? 0 : name.hashCode());
            return result;
        }
        //default as extends Object
        public boolean equals(Object obj){
            //return this==obj;
            return this.empNo==((Employee1)obj).empNo && this.name==((Employee1)obj).name;
        }
    }

    class ValueComparator implements Comparator<String> {//for kay
        Map<String, Integer> map = new HashMap<>();
        ValueComparator(Map<String, Integer> map){
            this.map = map;
        }
        @Override
        public int compare(String o1, String o2) {
            return map.get(o1).compareTo(map.get(o2));
        }
    }

