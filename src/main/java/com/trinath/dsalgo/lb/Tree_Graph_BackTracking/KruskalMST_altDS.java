package com.trinath.dsalgo.lb.Tree_Graph_BackTracking;

import java.util.*;

public class KruskalMST_altDS {
    /**Start of MST with with a diffrent  datastructuure of fraph than in our adj List of graph ****************************************/
    class Vertex1{
        private int id;
        private boolean visited;

        public Vertex1(int id, boolean visited) {
            super();
            this.id = id;
            this.visited = visited;
        }

        int getId() {
            return id;
        }

        void setId(int id) {
            this.id = id;
        }

        boolean isVisited() {
            return visited;
        }

        void setVisited(boolean visited) {
            this.visited = visited;
        }
    }

    class Edge1{
        private int weight;
        private boolean visited;
        private Vertex1 src;
        private Vertex1 dest;

        public Edge1(int weight, boolean visited, Vertex1 src,
                     Vertex1 dest) {
            this.weight = weight;
            this.visited = visited;
            this.src = src;
            this.dest = dest;
        }

        int getWeight() {
            return weight;
        }

        void setWeight(int weight) {
            this.weight = weight;
        }

        boolean isVisited() {
            return visited;
        }

        void setVisited(boolean visited) {
            this.visited = visited;
        }

        Vertex1 getSrc() {
            return src;
        }

        void setSrc(Vertex1 src) {
            this.src = src;
        }

        Vertex1 getDest() {
            return dest;
        }

        void setDest(Vertex1 dest) {
            this.dest = dest;
        }
    }

    class Graph1{
        private List<Vertex1> g;   //vertices
        private List<Edge1> e;     //edges

        int findMinSpanningTree() {
            int mstWeight = 0;
            Set<Vertex1> visited = new HashSet<Vertex1>();// to track
            //Use Krushkal as it's not given as a adjacencylist map of vertex and list of edge, so starting with a vertex and it's min weight edge is a bad idea
            // Puush to Priority Queue
            PriorityQueue<Edge1> minEdgeHeap = new PriorityQueue<Edge1>(new Comparator<Edge1>() {
                @Override
                public int compare(Edge1 e1, Edge1 e2) {
                    return e1.getWeight() - e2.getWeight();
                }
            });

            //Build heap
            for (Edge1 edge1 : e) {
                minEdgeHeap.add(edge1);
            }
            //Process the heap

            for (Vertex1 v : g) {
                makeSet(v.getId());
            }
            while (!minEdgeHeap.isEmpty()) {
                Edge1 e1 = minEdgeHeap.poll();
                // ignore that edge as going to form the cycle if both the vertices are visited
                long set1 = findSet(e1.getSrc().getId());
                long set2 = findSet(e1.getDest().getId());
                if (set1 == set2) {
                    continue;
                }
                union(e1.getSrc().getId(), e1.getDest().getId());
                mstWeight += e1.getWeight();


            }
            return mstWeight;
        }

        int[] parent = new int[10];// any number bigger than vertex size

        void makeSet(int v) {
            parent[v] = v;
        }

        int findSet(int v) {
            if (v == parent[v])
                return v;
            return findSet(parent[v]);
        }

        void union(int a, int b) {
            a = findSet(a);
            b = findSet(b);
            if (a != b)
                parent[b] = a;
        }

        public Graph1(List<Vertex1> g, List<Edge1> e) {
            super();
            this.g = g;
            this.e = e;
        }

        public List<Vertex1> getG() {
            return g;
        }

        public void setG(List<Vertex1> g) {
            this.g = g;
        }

        public List<Edge1> getE() {
            return e;
        }

        public void setE(List<Edge1> e) {
            this.e = e;
        }

        // This method returns the vertex with a given id if it
        // already exists in the graph, returns NULL otherwise
        Vertex1 vertexExists(int id) {
            for (int i = 0; i < g.size(); i++) {
                if (g.get(i).getId() == id) {
                    return g.get(i);
                }
            }
            return null;
        }

        // This method generates the graph with v vertices
        // and e edges
        void generateGraph(int vertices,
                           List<ArrayList<Integer>> edges) {
            // create vertices
            for (int i = 0; i < vertices; i++) {
                Vertex1 v = new Vertex1(i + 1, false);
                g.add(v);
            }

            // create edges
            for (int i = 0; i < edges.size(); i++) {
                Vertex1 src = vertexExists(edges.get(i).get(1));
                Vertex1 dest = vertexExists(edges.get(i).get(2));
                Edge1 e = new Edge1(edges.get(i).get(0), false, src,
                        dest);
                getE().add(e);
            }
        }

        String printGraph() {
            String result = "";

            for (int i = 0; i < e.size(); i++) {
                result += e.get(i).getSrc().getId() + "->"
                        + e.get(i).getDest().getId() + "["
                        + e.get(i).getWeight() + ", "
                        + e.get(i).isVisited() + "]  ";
            }
            return result;
        }

        void printMst(int w) {
            System.out.println("MST");
            for (int i = 0; i < e.size(); i++) {
                if (e.get(i).isVisited() == true) {
                    System.out.println(e.get(i).getSrc().getId() + "->"
                            + e.get(i).getDest().getId());
                }
            }
            System.out.println("weight: " + w);
            System.out.println();
        }
    }
    /**End of MST with diiffrenet DS of graph ****************************************/
}
