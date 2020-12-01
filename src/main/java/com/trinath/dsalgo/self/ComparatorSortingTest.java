package com.trinath.dsalgo.self;

import java.util.*;
import java.util.stream.Collectors;

public class ComparatorSortingTest {

    public static void main(String args[]){
        List<Date> list1 = new ArrayList<>();
        populateList(list1);
        long start = System.currentTimeMillis();
        System.out.println("Before Sorting");
        //print(list1);
        sortdate(list1);
        System.out.println("After Sorting by own sortdate() (in ms) "+(System.currentTimeMillis()-start));

        List<Date> list2 = new ArrayList<>();
        populateList(list2);
        start = System.currentTimeMillis();
        list2.sort(Collections.reverseOrder(Comparator.comparing(o->o)));
        System.out.println("After Sorting using java (in ms) "+(System.currentTimeMillis()-start));

        List<Date> list3 = new ArrayList<>();
        populateList(list3);
        start = System.currentTimeMillis();
        list3.stream().filter(o->o!=null).collect(Collectors.toList()).sort(Collections.reverseOrder(Comparator.comparing(o->o)));
        System.out.println("After Sorting using java 2nd type (in ms) "+(System.currentTimeMillis()-start));
        //print(list1);

        //Map Test
        Map<String, String> map =  new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length() > o2.length()){
                    return -1;
                }else if(o1.equals(o2)){
                    return 0;
                }else{
                    return 1;
                }
            }
        });

        map.put("@Te", "Te");
        map.put("@Test", "Test");
        map.put("@Test2", "Test2");
        map.put("Test", "Test");
        System.out.println(map.keySet().iterator().next());



    }

    private static void populateList(List<Date> list1) {
        for(int i=0; i< 100000; i++) {
            list1.add(new Date(1582409217));
            list1.add(new Date());
            list1.add(new Date(1603404417));

            Date dt = new Date();
            list1.add(dt);
            list1.add(new Date(1703404417));
          //  list1.add(null);
        }
    }

    private static void sortdate(List<Date> list){
        list.sort(new Comparator<Date>() {
            @Override
            public int compare(Date o1, Date o2) {
                if(o1 ==null || o2==null){ // descending
                    return 0;
                }
                else if(o1.after(o2)){ // descending
                    return -1;
                }
                else if(o1.equals(o2)){
                    return 0;
                }else{
                    return 1;
                }
            }
        });
        //return list;
    }

    private static void print(List<Date> list){
        list.forEach(d-> System.out.println(d));
    }

}
