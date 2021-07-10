package com.trinath.dsalgo.lb.Tree_Graph_BackTracking;

import java.util.*;

class AllTaskSchedulingOrders {
    public static List<Integer> printOrders(int tasks, int[][] prerequisites) {
        List<List<Integer>> sortedOrder = new ArrayList<>();
        if(tasks<0){
            return null;
        }
        //AdjList and inDegree and build graph
        Map<Integer,List<Integer>> adjList = new HashMap<>();
        Map<Integer,Integer> inDegree = new HashMap<>();
        for(int[] row:prerequisites){//[1,2] => 1->2, so indegree of 1 is unchnages and 2 is +1
            inDegree.put(row[1], inDegree.getOrDefault(row[1],0)+1);
            inDegree.put(row[0], inDegree.getOrDefault(row[0],0));//add
            List<Integer> list = adjList.getOrDefault(row[0],new ArrayList<>());
            List<Integer> list1 = adjList.getOrDefault(row[1],new ArrayList<>());
            list.add(row[1]);
            adjList.put(row[0], list);
            adjList.put(row[1], list1);
        }
        //Find all vertices with 0 indegree
        Queue<Integer> queue = new LinkedList<Integer>();
        for(Integer key: adjList.keySet()){
            if(!inDegree.containsKey(key) || inDegree.get(key)==0){
                queue.add(key);
            }
        }
        //Sort now
        List<Integer> sorted = new LinkedList<Integer>();
        sortedOrder.add(new ArrayList<>(queue));
        while(!queue.isEmpty()){
            Integer item = queue.poll();
            sorted.add(item);
            //add a copy to result
            //addToResult(item,sortedOrder);
            if(adjList.containsKey(item)){
                for(Integer neighbour:adjList.get(item)){
                    //System.out.println(neighbour);
                    int i = Integer.valueOf(inDegree.get(neighbour)) - 1;
                    inDegree.put(neighbour, i);
//                    if(i==1){
//                        List<Integer> l = new ArrayList<>(sorted);
//                        l.add(neighbour);
//                        sortedOrder.add(l);
//                    }
                    if (i == 0) {
                        queue.add(neighbour);
                        List<Integer> l = new ArrayList<>(sorted);
                        l.add(neighbour);
                        sortedOrder.add(l);
                    }
                }
            }

        }
        print(sortedOrder);
        return sorted;
    }

    private static void addToResult(Integer item,List<List<Integer>> sortedOrder ){
        for(List<Integer> list:sortedOrder){
            list.add(item);

        }
    }

    private static void print(List<List<Integer>> sortedOrder ){
        for(List<Integer> list:sortedOrder){
            System.out.println(list);

        }
    }


    public static void main(String[] args) {
       // AllTaskSchedulingOrders.printOrders(3, new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 } });
        System.out.println();

        AllTaskSchedulingOrders.printOrders(4,
                new int[][] { new int[] { 3, 2 }, new int[] { 3, 0 }, new int[] { 2, 0 }, new int[] { 2, 1 } });
        System.out.println();

        AllTaskSchedulingOrders.printOrders(6, new int[][] { new int[] { 2, 5 }, new int[] { 0, 5 }, new int[] { 0, 4 },
                new int[] { 1, 4 }, new int[] { 3, 2 }, new int[] { 1, 3 } });
        System.out.println();
    }
}