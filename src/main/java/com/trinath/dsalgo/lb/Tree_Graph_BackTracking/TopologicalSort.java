package com.trinath.dsalgo.lb.Tree_Graph_BackTracking;

import java.util.*;

public class TopologicalSort {
    public static List<Integer> topologicalSort(List<Integer> jobs, List<Integer[]> deps) {
        Map<Integer,Integer> inDegree = new HashMap<>();//  job B's indegrees/dependencies are A, C
        Map<Integer, List<Integer>> adjList = new HashMap<>(); // dependent to node A->B , C->B

        //create adjList
        for(int i=0; i<jobs.size(); i++){
            inDegree.put(jobs.get(i),0);
        }
        System.out.println("Indegree done :-)");
        //AdjList
        for(Integer[] row : deps){ // process every record of matrix or 2D array
            List<Integer> list = adjList.getOrDefault(row[0],new ArrayList<>());// key is prerequisite
            list.add(row[1]);//row[1] "prereq for"
            inDegree.put(row[1],inDegree.getOrDefault(row[1],0)+1); //row[1] key is "whoever depends"
            adjList.put(row[0],list);

        }
        //Now create a queue and add all indegree 0 and process to do BFS
        Queue<Integer> queue = new LinkedList<Integer>();
        for(Integer key: adjList.keySet()){
            if(!inDegree.containsKey(key) || inDegree.get(key)==0){//independent
                queue.add(key);
            }
        }
        //Sorting
        List<Integer> sorted = new LinkedList<Integer>() ;
        while(!queue.isEmpty()) {
            int item = queue.poll();
            System.out.println("item " + item);
            sorted.add(item);
            if (adjList.containsKey(item)) {
                for (Integer neighbour : adjList.get(item)) {
                    int i = Integer.valueOf(inDegree.get(neighbour)) - 1;
                    System.out.println("i " + i);
                    inDegree.put(neighbour, i);
                    if (i == 0) {
                        queue.add(neighbour);
                    }
                }
            }
        }
        return sorted;
    }

    public static void main(String[] args){
        List<Integer> jobs = Arrays.asList(1,2,3,4);
        List<Integer[]> deps = new ArrayList<>();
        Integer[] one = new Integer[2];
        one[0] =1;
        one[1]=2;
        deps.add(one);
        Integer[] two = new Integer[2];
        two[0] =1;
        two[1]=3;
        deps.add(two);
        Integer[] three = new Integer[2];
        three[0] =3;
        three[1]=2;
        deps.add(three);
        Integer[] four = new Integer[2];
        four[0] =4;
        four[1]=2;
        deps.add(four);
        Integer[] five = new Integer[2];
        five[0] =4;
        five[1]=3;
        deps.add(five);
        topologicalSort(jobs, deps);
    }

}
