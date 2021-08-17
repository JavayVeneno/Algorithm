package DataStructure.tree;

import DataStructure.set.BSTSet;
import common.FileOperation;

import java.util.ArrayList;
import java.util.TreeMap;

public class Trie {

    private class Node{
        public boolean isWord;

        public TreeMap<Character,Node> next;

        public Node(boolean isWord){
            next = new TreeMap<>();
            this.isWord =isWord;
        }
        public Node(){
            this(false);
        }
    }

    private int size;
    private Node root;

    public int getSize(){
        return size;
    }

    public Trie(){
        root = new Node();
        size = 0;
    }

    public void add(String word){
        Node cur = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            // 判断当前子符是否存在后续节点
            if (cur.next.get(c)==null){
                cur.next.put(c,new Node());
            }
            cur = cur.next.get(c);
        }

        if(!cur.isWord){
            cur.isWord=true;
            size++;
        }
    }

    public void addR(String word){
        addR(root,word);
    }

    private void addR(Node node, String word) {
        if (word.length()==0){
            size++;
            node.isWord=true;
            return;
        }
        char head = word.charAt(0);
        String body = word.substring(1);
        if(node.next.get(head)==null){
            node.next.put(head,new Node());

        }
        addR(node.next.get(head),body);
    }

    public boolean contains(String word){

        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c)==null){
                return false;
            }
            cur = cur.next.get(c);
        }

        return cur.isWord;
    }

    public boolean containsR(String word){
        return containsR(root,word);
    }

    private boolean containsR(Node node, String word) {

        if(word.length()==0){
            return node.isWord;
        }
        char head = word.charAt(0);
        if(node.next.get(head)==null){
            return false;
        }
        return containsR(node.next.get(head),word.substring(1));
    }


    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>(200000);
        BSTSet<String> set = new BSTSet<>();
        Trie trie = new Trie();
        if(FileOperation.readFile("src/PrideAndPrejudice.txt",words)){
            System.out.println("BSTSet ................");

            long time1 = System.nanoTime();

            for (String word : words) {
                set.add(word);
            }
            for (String word : words) {
                set.contains(word);
            }

            long time2 = System.nanoTime();
            double time = (time2-time1)/ 1_000_000_000.000000000;
            System.out.println("BST has "+set.size()+" elements but add and contains this elements used "+time +" seconds");

            System.out.println("Trie ................");

            time1 = System.nanoTime();

            for (String word : words) {
                trie.add(word);
            }
            for (String word : words) {
                trie.contains(word);
            }

            time2 = System.nanoTime();
            time = (time2-time1)/ 1_000_000_000.000000000;
            System.out.println("Trie has "+set.size()+" elements but add and contains this elements used "+time +" seconds");
        }
    }

}
