package com.trinath.dsalgo;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class IceCreamParlour {

    private static int pos1 = -1;
    private static int pos2;
    static void solve(int[] arr, int money) {
        // Complete this function
        HashMap<Integer,ArrayList<Integer>> map = new HashMap();
        ArrayList<Integer> list = new ArrayList();
        for(int i=0; i<arr.length-1;  i++){

            if(map.get(arr[i])==null){
                list = new ArrayList();
                list.add(i);
                map.put(arr[i],list);
            }
            else{
                list =  map.get(arr[i]);
                list.add(i);
                map.put(arr[i],list);
            }
        }

        for(int i=0; i<arr.length-1;  i++){
            if(arr[i] < money){
                if(map.get(arr[i]).get(0)!=null){
                    pos1 = map.get(arr[i]).get(0) +1 ;
                    if(map.get(money - arr[i])!=null){
                        pos2 = (map.get(money - arr[i])).get(0) +1;
                        break;
                    }
                }


            }
        }
        if(arr[pos1]<=arr[pos2]){
            System.out.println((pos1+1)+" "+(pos2+1));
        }
        else{
            System.out.println((pos2+1)+" "+(pos1+1));
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int money = in.nextInt();
            int n = in.nextInt();
            int[] arr = new int[n];
            for(int arr_i = 0; arr_i < n; arr_i++){
                arr[arr_i] = in.nextInt();
            }
            solve(arr, money);
        }
        in.close();
    }
}
