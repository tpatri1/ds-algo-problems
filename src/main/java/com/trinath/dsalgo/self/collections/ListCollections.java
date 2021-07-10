package com.trinath.dsalgo.self.collections;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class ListCollections {

    private static List<Integer> listOperation(){
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(10);
        list.add(100);
        list.replaceAll(l->l+10);
        print(list," replace list elemnt");
//        for(Integer i: list){
//            //list.add(1);
//            list.remove(1);
//        }
        Iterator<Integer> itr = list.iterator();
        while(itr.hasNext()){
            int next = itr.next();
            if(next==11){
                //list.remove(new Integer(11));
                itr.remove();
            }
        }
        print(list, " iterator removal");
        //list.add(200);

        ListIterator<Integer> listIterator = list.listIterator();
        while(listIterator.hasNext()){
            int next = listIterator.next();
            if(next==110){
                System.out.println(listIterator.previous());
                listIterator.set(100);
            }
            if(next==20){
                listIterator.add(12);
            }
        }

        print(list,"add to list ");
        //Collections.sort(list); // Collections.sort(list, Comparator.reverseOrder())
        list = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        print(list,"sorting list");

        Employee e1 = new Employee("Trinath", 36);
        Employee e2 = new Employee("Seema", 35);
        Employee e3 = new Employee("Patri", 43);
        List<Employee> employees = new ArrayList<>();
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);
        //Collections.sort(employees);
        Collections.sort(employees, new EmployeeComparator());
        System.out.println(employees);

        return list;

    }

    private static void linkedListOps(List<Integer> list) {
    LinkedList<Integer> ll = new LinkedList<>();
    ll.addAll(list);
    print(ll," adding list elemnt to linked list");
    ll.add(1000);
    print(ll," add 1000");
    ll.remove();
    print(ll," removed ");
    ll.addFirst(1);
    ll.addLast(1001);

    print(ll," add first 1 and last 1001");
    System.out.println("\nFirst elem "+ll.getFirst()+" Last element "+ll.getLast());
    ll.removeFirst();
    print(ll," remove first ");
    ll.removeLast();
    print(ll," remove last ");
    Collections.sort(ll);
    print(ll, " sorted");

    }

    public static void main(String args[]){

        System.out.println("list Operations");
        List<Integer> list  = listOperation();
        System.out.println("Linkedlist Operations");
        linkedListOps(list);
        Collections.synchronizedList(list);//thread safe but put a lock on all ops
        //Use
        List<Integer> syncList = new CopyOnWriteArrayList<>(list);
        CopyOnWriteArrayList<Integer> cList = new CopyOnWriteArrayList();
        cList.add(1);
        cList.add(2);
        cList.add(3);
        Iterator<Integer> itr = cList.iterator();
        System.out.println("first");
        while(itr.hasNext()){
            int next = itr.next();
            if(next==2){
                cList.remove(new Integer(next));
            }
            System.out.println(next);
        }
        cList.add(4);
        itr = cList.iterator();
        System.out.println("second");
        while(itr.hasNext()){
            int next = itr.next();
            System.out.println(next);
        }
    }

    public static void print(List list, String msg){
        System.out.println("\nPrinting  "+msg);
        list.stream().forEach(l->System.out.print(" "+l));

    }


}

