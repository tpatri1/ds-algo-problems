package com.trinath.dsalgo.lb.Tree_Graph_BackTracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;

public class XMLToTree {
    static class TreeNode {
        public int data;
        public String text;
        public ArrayList<TreeNode> Children = new ArrayList<TreeNode>();

        public TreeNode(int data) {
            this.data = data;
        }

        public TreeNode(String text) {
            this.text = text;
        }
    }

        public static void display_level_order(TreeNode root) {
            if(root == null)
                return;

            ArrayDeque<TreeNode> queue = new ArrayDeque<TreeNode>();
            queue.addLast(root);
            TreeNode tempNode = new TreeNode(100);
            queue.addLast(tempNode);

            while(!queue.isEmpty()){
                TreeNode temp = queue.removeFirst();

                if(temp == tempNode) {
                    System.out.println();
                    if(!queue.isEmpty()){
                        queue.addLast(tempNode);
                        continue;
                    }
                    else {
                        return;
                    }

                }

                System.out.print(temp.data + ", ");

                for(int i = 0 ; i < temp.Children.size() ; i++) {
                    queue.addLast(temp.Children.get(i));
                }
            }
            System.out.println();
        }



        public static TreeNode createXmlTree(String xml) {
            TreeNode root = null;
            TreeNode head=null;

            int i=0;
            boolean closing = false;
            boolean leaf = false;
            Stack<TreeNode> stack = new Stack<TreeNode>();
            while(i<xml.length()){
                String token="";
                if(xml.charAt(i)=='<'){
                    i++;
                    if(xml.charAt(i)=='/'){
                        closing = true;
                        i++;
                    }
                    while(xml.charAt(i)!='>' || i==xml.length()-2){

                        token+=xml.charAt(i);
                        i++;
                    }
                    i++;
                }else{
                    while(xml.charAt(i)!='<') {
                        token += xml.charAt(i);
                        i++;
                    }
                    leaf = true;
                }
//                if(token.trim().isEmpty()){
//                    i++;
//                    continue;
//                }
                TreeNode node = new TreeNode(token);
                if(!closing){
                    if(head!=null){
                        head.Children.add(node);
                        head = node;
                    }else{
                        head = new TreeNode(token);
                        root= head;
                    }
                    if(!leaf) {
                        stack.push(head);
                    }

                }else{
                    head = stack.pop();
                    if(!stack.isEmpty()) {
                        head = stack.peek();
                    }
                }
                closing=false;
                leaf = false;

            }

            return root;
        }
    public static void print_tree(TreeNode root, int depth) {
        if (root == null) {
            return;
        }

        for (int i = 0; i < depth; ++i) System.out.print("\t");
        System.out.print(root.text + "\n");
        for (TreeNode child : root.Children) {
            print_tree(child, depth + 1);
        }
    }

    public static void main(String[] args) {
        try {
            String xml = "<xml><data>hello world</data><a><b></b><b><c></c></b></a></xml>";
            //String xml = "<xml><data>hello world</data><data>hello Trinath</data></xml>";
            TreeNode result = createXmlTree(xml);
            print_tree(result,0);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
