package com.trinath.dsalgo.lb.Stack_Queue_LinkedList_HashTable;

import java.util.Stack;

public class ShortenPath {
    public static String shortenPath(String path) {
        String[] tokens = path.split("/");
        Stack<String> pathStack = new Stack<String>();
        Stack<String> tokenStack = new Stack<String>();
        for(int i=0; i<tokens.length; i++){
            if(tokens[i].equals("..")|| tokens[i].equals(".")||tokens[i].equals("")){
                continue;
            }
            //pathStack.push(tokens[i]);
            //System.out.println(pathStack.peek());
        }

        //int idx = tokens.length-1;
        boolean flag = false;
        System.out.println(".....");
        for(int idx=tokens.length-1; idx>=0; idx--){
            String token = tokens[idx];
            if(token.equals(".")||token.equals("")){
                continue;
            }else if(token.equals("..")){
                pathStack.push("..");

                flag = true;
            }else{
                if(pathStack.isEmpty()) {
                    tokenStack.push(token);
                }else{
                    pathStack.pop();
                }
                continue;
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!pathStack.isEmpty()){
            if(path.charAt(0)=='/'){
                sb.append("/").append(pathStack.pop());
            }else{
                sb.append(pathStack.pop()).append("/");
            }
        }
        while(!tokenStack.isEmpty()){
            //
            if(path.charAt(0)=='/' && sb.toString().length()>1){
                sb.append("/").append(tokenStack.pop());
            }else{
                sb.append(tokenStack.pop()).append("/");
            }
        }
        String res = sb.toString();
        return path.charAt(0)=='/' ? res : res.length() >2 ? res.substring(0,res.length()-1) : res;
        //return res.length() >2 ? res.substring(0,res.length()-1) : res;
    }

    public static void main(String args[]){
        System.out.println(shortenPath("../../foo/../../bar/baz"));
    }
}
