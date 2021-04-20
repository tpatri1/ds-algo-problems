package com.trinath.dsalgo.lb.Tree_Graph_BackTracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Cons of adjacency matrix
 * The VxV space requirement of the adjacency matrix makes it a memory hog. Graphs out in the wild usually don't have too many connections and this is the major reason why adjacency lists are the better choice for most tasks.
 *
 * While basic operations are easy, operations like inEdges and outEdges are expensive when using the adjacency matrix representation.
 * Adjacency Matrix Applications---Creating routing table in networks, Navigation tasks
 */
public class AdjMatrixGraph {
    private int numVertices;//Number of vertices
    private boolean[][] adjMetrices;

    public AdjMatrixGraph(int numVertices){
        this.numVertices = numVertices;
        this.adjMetrices = new boolean[numVertices][numVertices];
    }
    public void addEdge(int source, int dest){
        adjMetrices[source][dest] = true;
        //if undirected
        adjMetrices[dest][source] = true;
    }
    public void removeEdge(int source, int dest){
        adjMetrices[source][dest] = false;
        //if undirected
        adjMetrices[dest][source] = false;
    }
    public List<Integer> getAdjListOrNextMove(int rowNum){
        List<Integer> list = new ArrayList<>();
        for(int j=0; j<adjMetrices[rowNum].length; j++){
            list.add(adjMetrices[rowNum][j]? 1:0);
        }
        return list;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for(int i=0; i<numVertices; i++){
            s.append("vertex "+i+" -> ");
            for(boolean j: adjMetrices[i]){
                s.append((j? 1:0)+" ");
            }
            s.append("\n");
        }
        return s.toString();
    }
    public static void main(String args[]) {
        AdjMatrixGraph g = new AdjMatrixGraph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);

        System.out.print(g.toString());
    }
}
