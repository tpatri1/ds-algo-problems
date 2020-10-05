package com.trinath.dsalgo.lb.Tree_Graph_BackTracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class Pair<X,Y>{
    X first;
    Y second;

    public Pair(X first, Y second){
        this.first= first;
        this.second = second;
    }
}
class IntPair extends Pair<Integer,Integer>{
    public IntPair(int first, int second){
        super(first,second);
    }
}
public class BoggleBoard {
    char[][] grid;
    Set<String> dictionary;
    boolean[][] state ;
    BoggleBoard(char[][] grid, HashSet<String> dictionary){
        this.grid = grid;
        this.dictionary= dictionary;
        this.state = new boolean[grid.length][grid.length];
        for(int i=0; i< grid.length; i++){
            for(int j=0; j<grid.length; j++){
                state[i][j] = false;
            }
        }
    }
    public HashSet<String> findAllWords(){
        //TODO: Write - Your - Code
        HashSet<String> words = new HashSet<String>();
        StringBuilder currentWord = new StringBuilder();
        for(int i=0; i<grid.length; ++i){
            for(int j=0; j<grid.length; ++j){
                dfs(i, j, currentWord, words);
            }
        }
        return words;
    }

    ArrayList<IntPair> getNeighbours(int x, int y){

        ArrayList<IntPair> nbrs = new ArrayList<IntPair>();

        int startX = Math.max(0, x - 1);
        int startY = Math.max(0, y - 1);
        int endX = Math.min(grid.length - 1, x + 1);
        int endY = Math.min(grid.length - 1, y + 1);

        for (int i = startX; i <= endX; ++i) {
            for (int j = startY; j <= endY; ++j) {
                if (state[i][j]) {
                    continue;
                }
                nbrs.add(new IntPair(i, j));
            }
        }
        return nbrs;
    }

    void dfs(int i, int j, StringBuilder currentWord, HashSet<String> words){
        if(currentWord.length()>0 && dictionary.contains(currentWord.toString())){
            words.add(currentWord.toString());
        }

        ArrayList<IntPair> neighbours = getNeighbours(i,j);
        for(IntPair n : neighbours){
            currentWord.append(grid[n.first][n.second]);
            state[n.first][n.second]= true; // visited
            dfs(n.first, n.second,currentWord,words);
            currentWord.setLength(currentWord.length() - 1);
            state[n.first][n.second]= false; // visited
        }
    }
    public static void main(String[] args) {
        char[][] grid = new char[][] {
                {'c', 'a', 't'},
                {'r', 'r', 'e'},
                {'t', 'o', 'n'}
        };

        String[] dict = {"cat", "cater", "cartoon", "art", "toon", "moon", "eat", "ton"};
        HashSet<String> dictionary = new HashSet<String>();
        for (String s: dict) {
            dictionary.add(s);
        }

        BoggleBoard b = new BoggleBoard(grid, dictionary);
        HashSet<String> words = b.findAllWords();
        for (String s: words) {
            System.out.println(s);
        }
    }
}
