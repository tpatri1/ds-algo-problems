package com.trinath.dsalgo.self.collections;

import java.util.Comparator;

public class Employee implements Comparable<Employee> {
    int age;
    String name;

    Employee(String name,int age){
        this.age = age;
        this.name = name;
    }
    Employee(int age,String name){
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Employee e){
        return this.age - e.age;
    }

    @Override
    public String toString(){
        return "Name: "+name+" age "+age;
    }

}

class EmployeeComparator implements Comparator<Employee>{

    @Override
    public int compare(Employee e1, Employee e2){
        return e2.age -e1.age;
    }
}


