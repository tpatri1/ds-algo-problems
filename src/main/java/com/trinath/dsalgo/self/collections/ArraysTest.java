package com.trinath.dsalgo.self.collections;

import java.util.Arrays;
import java.util.List;

public class ArraysTest {
    public static void main(String args[]){
        Employee[] employees = new Employee[]{new Employee(111,"Trinath"), new Employee(222, "Patri"),new Employee(333,"Seema")};
        System.out.println("Index in bsearch "+ Arrays.binarySearch(employees, new Employee(111,"Trinath")));
        int[] arr= new int[]{10, 4, 5, 12, 4, 1, 24};
        //Arrays.sort(arr);
        Arrays.parallelSort(arr);
        Arrays.stream(arr).forEach(System.out::println);

        int[] numbers = { 1, 2, 13, 4, 5, 6, 7, 8, 9, 10 };
        int[] newArray = Arrays.copyOf(numbers, 2);
        int[] newArray1 = Arrays.copyOfRange(numbers,0,numbers.length);
        System.out.println(" Arrays copy ");
        Arrays.stream(newArray).forEach(System.out::println);
        System.out.println(" equal "+Arrays.equals(numbers,newArray1));
        System.out.println(" equal "+Arrays.equals(numbers,newArray));
        Arrays.fill(numbers, 20);
        System.out.println(" Arrays fill default ");
        Arrays.stream(numbers).forEach(System.out::println);
//        List<Integer> list = Arrays.asList(numbers);
//
//        System.out.print(list);
    }
}
