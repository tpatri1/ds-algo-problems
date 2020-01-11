package practice.problems;

import java.util.*;

public class TriesContacts1 {
    class Node {
        Map<Character, List< Node>> childNodes = new HashMap();
        boolean isWord;

    }

    //class Tries {
    static Node root;
    Node traverseNode = root;

    public void addWord(String word) {
        boolean flag = false;
        Node nodePtr = null;
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (root == null) {
                root = new Node();
                root.childNodes.put(chars[i], new ArrayList<>());
            } else {
                Node node = new Node();
                String newWord = word.substring(0, i+1);
                if (findNode(newWord, root) == null) {
                    flag = true;
                    String prevWord = word.substring(0, i);
                    nodePtr = findNode(prevWord, root);

                }  if (flag == true) {
                    nodePtr = addNode(nodePtr, chars[i] , chars[i-1]);
                    if (i == chars.length - 1) {
                        nodePtr.isWord = true;
                    }
                }
            }
        }
    }

    private Node addNode(Node node, char c , char p) {
        Node node1 = new Node();
        node1.childNodes.put(c, new ArrayList<>());
        node.childNodes.get(p).add(node1); //add the link
        return node;
    }

    private static Node findNode(String word, Node traverseNode) {
        if(word.length()>0) {
            if (word.length() == 1) {
                if (traverseNode.childNodes.containsKey(word.charAt(0))) {
                    return traverseNode;
                } else {
                    return null;
                }
            } else {
                for (Node node : traverseNode.childNodes.get(word.charAt(0))) {
                    if (word.length() > 1 && node.childNodes.containsKey(word.charAt(1))) {
                        return findNode(word.substring(1), node);
                    } else {
                        return null;
                    }
                }
            }
        }
        return null;
    }


    private static void search(String word){
        Node foundNode = findNode(word, root);
        int match = foundNode.childNodes.get(word.charAt(word.length()-1)).size();
System.out.println(match);
    }

//}


    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        for(int a0 = 0; a0 < n; a0++){
//            String op = in.next();
//            String contact = in.next();
//        }
        TriesContacts1 contacts = new TriesContacts1();
        contacts.addWord("hack");
        contacts.addWord("hackerank");
        contacts.search("hac");
       // contacts.search("hak");
    }
}
