package dataStructure.map;

import common.FileOperation;

import java.util.ArrayList;

public class BSTMap<K extends Comparable<K>,V> implements Map<K,V> {

    private class Node{
        public K key;
        public V value;
        public Node left,right;
        public Node(K key,V value){
            this.key = key;
            this.value = value;
            this.left=null;
            this.right = null;
        }
    }

    private int size;
    private Node root;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }


    @Override
    public V get(K key) {
        Node node = getNode(key);
        return null==node?null:node.value;
    }

    @Override
    public boolean contains(K key) {

        return getNode(key)!=null;
    }

    @Override
    public void add(K key, V value) {
        root = add(root,key,value);
    }

    private Node add(Node node, K key, V value) {
        if(node == null){
            size++;
            return new Node(key,value);
        }
        if(key.compareTo(node.key)<0){
            node.left = add(node.left,key,value);
            return node;
        }
        else if(key.compareTo(node.key)>0){
            node.right = add(node.right,key,value);
            return node;
        }
        else {
            node.value=value;
            return node;
        }

    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(root, key);
//        if(node!=null){
//            add(key,newValue);
//        }
        if(node == null){
            throw new IllegalArgumentException("key doesn't exists!");
        }
        node.value=newValue;
    }

    @Override
    public V remove(K key) {
        Node node = getNode(key);
        root = remove(root,key);
        return node.value;
    }

    private Node minimum(Node node){
        if(node == null){
            return null;
        }
        if(node.left !=null){
            return minimum(node.left);
        }
        return node;
    }

    private Node removeMin(Node node) {
        if(node.left == null){
           Node right = node.right;
           node.right=null;
           size--;
           return right;

        }
        node.left = removeMin(node.left);
        return node;
    }

    private Node remove(Node node, K key) {

        if(node==null){
            return null;
        }
        if(key.compareTo(node.key)>0){
            node.right = remove(node.right,key);
            return node;
        }
        else if(key.compareTo(node.key)<0){
            node.left = remove(node.left,key);
            return node;
        }
        else{
            if(node.left==null){
                Node right = node.right;
                node.right = null;
                size--;
                return right;
            }
            else if(node.right==null){
                Node left = node.left;
                node.left = null;
                size--;
                node = left;
                return node;
            }else{
                Node successor = minimum(node.right);
                successor.right = removeMin(node.right);
                successor.left = node.left;
                node.left = node.right = null;
                return successor;
            }
        }

    }



    private Node getNode(K key){

        return getNode(root, key);

    }

    private Node getNode(Node node, K key) {
        if(node==null){
            return null;
        }
        if(node.key.compareTo(key)==0){
            return node;
        }
        else if(node.key.compareTo(key)<0){
            return getNode(node.right,key);
        }
        else{
            return getNode(node.left,key);
        }
    }

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>(200000);
        FileOperation.readFile("src/PrideAndPrejudice.txt",words);

        long time = System.currentTimeMillis();
        Map<String,Integer> map = new BSTMap<>();
        for (String word : words) {
            if(map.contains(word)){
                map.set(word,map.get(word)+1);
            }else{
                map.add(word,1);
            }
        }
        System.out.println(System.currentTimeMillis()-time+"ms");
        System.out.println("总词量为 :"+words.size());
        System.out.println("词汇量为 :"+map.size());
        System.out.println("pride 出现的次数 :"+map.get("pride"));
        System.out.println("prejudice 出现的次数:"+map.get("prejudice"));

    }
}
