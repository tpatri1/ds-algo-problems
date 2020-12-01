package com.trinath.dsalgo.lb.Uncategorized;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PassByValue {

        public static void main(String[] args) {
            Balloon red = new Balloon("Red"); //memory reference 50
            Balloon blue = new Balloon("Blue"); //memory reference 100
            swap(red, blue);
            System.out.println("red color="+red.getColor());
            System.out.println("blue color="+blue.getColor());
            foo(blue);
            System.out.println("blue color="+blue.getColor());

            List<Integer> nums = new ArrayList<Integer>();
            nums.add(1);
            nums.add(2);
            nums.add(3);
            nums.add(4);
            print(nums);
            //removeOdd(nums);
            int len = nums.size();
            Iterator itr = nums.iterator();
            while(itr.hasNext()){
                int a = (int) itr.next();
                if(a%2!=0){
                    itr.remove();
                }
            }
            print(nums);

        }

        private static void foo(Balloon balloon) { //baloon=100
            balloon.setColor("Red");
            balloon = new Balloon("Green");
            balloon.setColor("Blue");
        }

        public static void swap(Object o1, Object o2){
            Object temp = o1;
            o1=o2;
            o2=temp;
        }
        private static void removeOdd(List<Integer> nums){
            for(int i=0; i<nums.size(); i++){
                if(nums.get(i)%2!=0){
                    nums.remove(i);
                }
            }
        }
        private static void print(List<Integer> nums){
            System.out.println("Printing list:");
            for(Integer i: nums){
                System.out.print(i);
            }
        }

    }


class Balloon{
    String color;
    Balloon(String color){
        this.color = color;
    }
    Balloon(){

    }
    public void setColor(String color){
        this.color = color;
    }
    public String getColor(){
        return this.color;
    }
}
