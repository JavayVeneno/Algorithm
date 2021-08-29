package DataStructure.tree;

import DataStructure.map.BSTMap;
import common.FileOperation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;

public class RBTree<K extends Comparable<K>,V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node{
       public K key;
       public V value;
       public Node left;
       public Node right;
       public boolean color;
       public Node(K key,V value){
            this.key = key;
            this.value = value;
            this.left=null;
            this.right = null;
            this.color = RED;
       }
    }

    private int size;

    private Node root;

    public RBTree(){
        size=0;
        root = null;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }

    private boolean isRed(Node node){
        if(node==null){
            return BLACK;
        }
        return node.color;
    }

    //向红黑树中添加一个元素

    public void add(K key,V value) {
        root = add(root,key,value);
        //保持根节点为黑色
        root.color = BLACK;
    }
    public V get(K key) {
        Node node = getNode(key);
        return null==node?null:node.value;
    }

//        node                          x
//        /  \       node左旋转        / \
//       T1   x      --------->     node T3
//           / \                    / \
//          T2 T3                  T1 T2

    private Node leftRotate(Node node){
        Node x = node.right;
        // node右子指向x的左子
        node.right= x.left;
        // x的左子指向原node
        x.left  = node;
        // x是取代的新根,所以需要颜色与原节点一致
        x.color = node.color;
        // 原节点相当新添加节点,所以是红色
        node.color=RED;
        return x;
    }

//        node                          x
//        /  \   node进行右旋转         / \
//       x   T4  ------------>        y  node
//      / \                              / \
//     y  T3                            T3 T4

    private Node rightRotate(Node node){
        // 找到 x的位置即node.left
        Node x = node.left;
        // node左子指向x的右子
        node.left = x.right;
        // x的右子指向node
        x.right = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    private void flipColors(Node node){
        if(node==null){
            return ;
        }
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }
    private Node add(Node node, K key,V value) {

        if(node==null){
            size++;
            return new Node(key,value);
        }
        if(node.key.compareTo(key)<0){
            node.right = add(node.right,key,value);
        }
        else if(node.key.compareTo(key)>0){
            node.left = add(node.left,key,value);
        }else{
            node.value=value;
        }
        if(isRed(node.right) && !isRed(node.left) ){
            node  =leftRotate(node);
        }
        if(isRed(node.left) && isRed(node.left.left)){
            node = rightRotate(node);
        }
        if(isRed(node.left) && isRed(node.right)){
           flipColors(node);
        }

        return node;
    }


    public boolean contains(K key){
        return contains(root,key);
    }

    private boolean contains(Node node, K key) {
        if(node==null){
            return false;
        }
        if(node.key.compareTo(key)==0){
            return true;
        }
        else if(node.key.compareTo(key)<0){
            return contains(node.right,key);
        }else{
            return contains(node.left,key);
        }
    }


    private Node getNode(K key){
        return getNode(root,key);
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
        }else{
            return getNode(node.left,key);
        }
    }

    public V remove(K key){
        Node res = getNode(key);
        if(res==null){
            return null;
        }
        remove(root,key);
        return res.value;
    }

    private Node remove(Node node, K key) {

        if(node==null){
            return null;
        }
        if(node.key.compareTo(key)<0){
            node.right = remove(node.right,key);
            return node;
        }else if(node.key.compareTo(key)>0){
            node.left = remove(node.left,key);
            return node;
        }else{
            if(node.left ==null){
                Node right = node.right;
                node.right = null;
                size--;
                return right;
            }else if(node.right == null){
                Node left = node.left;
                node.left = null;
                size--;
                return left;
            }else{
                Node successor = minimum(node.right);
                successor.left = node.left;
                successor.right = remove(node.right,successor.key);
                node.left = node.right = null;
                return successor;
            }
        }
    }


    private void set(K key, V newValue) {
        Node node = getNode(root, key);
//        if(node!=null){
//            add(key,newValue);
//        }
        if(node == null){
            throw new IllegalArgumentException("key doesn't exists!");
        }
        node.value=newValue;
    }


    //非递归的方式实现前序遍历
    public void preOrderNR(){
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node node = stack.pop();
            System.out.println(node.key+" : "+node.value);
            if(node.right !=null){
                stack.push(node.right);
            }
            if(node.left !=null){
                stack.push(node.left);
            }
        }
    }

    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node) {
        if(node==null){
            return;
        }
        System.out.println(node.key+" : "+node.value);
        if(node.left !=null){
            preOrder(node.left);
        }
        if(node.right !=null){
            preOrder(node.right);
        }

    }

    public void inOrder(){
        inOrder(root);
    }
    private void inOrder(Node node){
        if(node!=null){
            inOrder(node.left);
            System.out.println(node.key+" : "+node.value);
            inOrder(node.right);
        }
    }
    public void postOrder(){
        postOrder(root);
    }
    private void postOrder(Node node){
        if(node==null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.key+" : "+node.value);
    }

    public void levelOrder(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            Node node = queue.remove();
            System.out.println(node.key+" : "+node.value);
            if(node.left!=null){
                queue.add(node.left);
            }
            if(node.right!=null){
                queue.add(node.right);
            }
        }

    }


    private Node minimum(Node node) {

        while(node.left !=null){
            node = node.left;
        }
        return node;

//        if(node.left!=null){
//            return minimum(node.left);
//        }
//        return node;

    }


    public static void main(String[] args) {
        testItsProp();
//        testBSTAndAVLTree();
    }

    public static void testItsProp() {
        ArrayList<String> words = new ArrayList<>(200000);
        FileOperation.readFile("src/PrideAndPrejudice.txt",words);

        long time = System.currentTimeMillis();
        RBTree<String,Integer> map = new RBTree<>();
        for (String word : words) {
            if(map.contains(word)){
                map.set(word,map.get(word)+1);
            }else{
                map.add(word,1);
            }
        }
//        for (String word : words) {
//            map.remove(word);
//            if(!map.isBalanced() || !map.isBST()){
//                throw new IllegalArgumentException("this tree is not a balance binary search tree (AVLTree).");
//            }
//        }
        System.out.println(System.currentTimeMillis()-time+"ms");
        System.out.println("总词量为 :"+words.size());
        System.out.println("词汇量为 :"+map.size());
        System.out.println("pride 出现的次数 :"+map.get("pride"));
        System.out.println("prejudice 出现的次数:"+map.get("prejudice"));
//        System.out.println("isBST: "+map.isBST());
//        System.out.println("isBalance : "+map.isBalanced());
    }


    public static void testBSTAndAVLTree() {
        ArrayList<String> words = new ArrayList<>(200000);
        FileOperation.readFile("src/PrideAndPrejudice.txt",words);

        long time1 = System.nanoTime();
        BSTMap<String,Integer> map = new BSTMap<>();
        for (String word : words) {
            if(map.contains(word)){
                map.set(word,map.get(word)+1);
            }else{
                map.add(word,1);
            }
        }
        for (String word : words) {
            map.contains(word);
        }
        long time2 = System.nanoTime();
        double time = (time2-time1)/1000000000.0;
        System.out.println(map.getClass().getName()+"耗时:"+time+" s");

        time1 = System.nanoTime();
        AVLTree<String,Integer> avlTree = new AVLTree<>();
        for (String word : words) {
            if(map.contains(word)){
                map.set(word,map.get(word)+1);
            }else{
                map.add(word,1);
            }
        }
        for (String word : words) {
            map.contains(word);
        }
        time2 = System.nanoTime();
        time = (time2-time1)/1000000000.0;
        System.out.println(avlTree.getClass().getName()+"耗时:"+time+" s");

    }
}
