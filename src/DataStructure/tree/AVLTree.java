package DataStructure.tree;

import DataStructure.map.BSTMap;
import com.sun.javafx.font.t2k.T2KFactory;
import common.FileOperation;

import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>,V> {
    private class Node{
        public K key;
        public V value;
        public Node left,right;
        public int height;
        public Node(K key,V value){
            this.key = key;
            this.value = value;
            this.left=null;
            this.right = null;
            this.height=1;
        }
    }

    private int size;
    private Node root;


    public int size() {
        return size;
    }


    public boolean isEmpty() {
        return size==0;
    }



    public V get(K key) {
        Node node = getNode(key);
        return null==node?null:node.value;
    }


    public boolean contains(K key) {

        return getNode(key)!=null;
    }


    private int getHeight(Node node){
        if(node==null){
            return 0;
        }
        return node.height;
    }

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
        }
        else if(key.compareTo(node.key)>0){
            node.right = add(node.right,key,value);
        }
        else {
            node.value=value;
        }

        node.height = 1+Math.max(getHeight(node.left),getHeight(node.right));
        int balance = getBalanceFactor(node);
//        if(Math.abs(balance)>1){
//            System.out.println("unBalance: "+balance);
//        }
        // 旋转时机
        // LL
        if(balance>1 && getBalanceFactor(node.left)>=0){
            // 如果当前节点打破平衡了,并且左子大于右子(即左斜),将右旋转
            return rightRotate(node);
        }
        // RR
        if(balance<-1 && getBalanceFactor(node.right)<=0){
            // 如果当前节点打破平衡了,并且左小于右子(即右斜),将左旋转
            return leftRotate(node);
        }
        // LR
        if(balance>1 && getBalanceFactor(node.left)<0){

            //首先需要明白node节点是左斜,但是node.left的平衡因子是负数(即node.left.left<node.left.right)
            // 我们首先需要将node.left进行一次左旋转,然后整个节点将会变成LL的情况

//            x在y的左边,z在x的右边即对于y形成LR
//                y                                              y
//               / \                                            / \
//              x  T4     x进行左旋之后将会形成LL的形式          z   T4
//             / \        -------------------------->         / \
//            T1  z              先进行一次x的左旋             x  T3
//               / \                                        / \
//              T2 T3                                      T1 T2
            node.left = leftRotate(node.left);
            // 直接调用LL的方法即可
            return rightRotate(node);
        }

        // RL
        if(balance<-1 && getBalanceFactor(node.right)>0){
            // 反之,node节点是右斜,但是node.left平衡因子是正数(即node.right.left>node.right.right)
            // 首先将node.right右旋,然后node变成了RR的情况

//            x在y的右边,z在x的左边即对于y形成RL
//                y                                              y
//               / \                                            / \
//             T1   x     x进行右旋之后将会形成RR的形式          T1  z
//                /  \     -------------------------->           / \
//               z   T4           先进行一次x的右旋              T2  x
//              / \                                               / \
//             T2 T3                                             T3 T4
            node.right = rightRotate(node.right);
            // 右旋之后直接调用RR的方法
            return leftRotate(node);
        }
        return node;
    }

    //         y                         x
    //        / \                      /  \
    //      T4   x    左旋转          y     z
    //         / \     ----->       / \   / \
    //        T3  z               T4  T3 T1 T2
    //           / \
    //         T1  T2

    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T3 = x.left;
        x.left = y;
        y.right = T3;
        // 更新height
        y.height = 1 + Math.max(getHeight(y.left),getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left),getHeight(x.right));
        return x;
    }

    //         y                        x
    //        / \                      / \
    //       x   T4    右旋转         z    y
    //      / \        ----->       / \   / \
    //     z  T3                   T1 T2 T3 T4
    //    / \
    //   T1 T2

    private Node rightRotate(Node y) {

        Node x = y.left;
        Node T3 = x.right;
        x.right=y;
        y.left = T3;

        // 更新height
        y.height = 1+Math.max(getHeight(y.left),getHeight(y.right));
        x.height = 1+Math.max(getHeight(x.left),getHeight(x.right));

        return x;
    }

    private int getBalanceFactor(Node node) {
        if(node==null){
            return 0;
        }
        return getHeight(node.left)-getHeight(node.right);
    }


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


    public V remove(K key) {
        Node node = getNode(key);
        if(node==null){
            return null;
        }
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
        Node responseNode;
        if(key.compareTo(node.key)>0){
            node.right = remove(node.right,key);
            responseNode =  node;
        }
        else if(key.compareTo(node.key)<0){
            node.left = remove(node.left,key);
            responseNode = node;
        }
        else{
            if(node.left==null){
                Node right = node.right;
                node.right = null;
                size--;
                responseNode = right;
            }
            else if(node.right==null){
                Node left = node.left;
                node.left = null;
                size--;
                node = left;
                responseNode =  node;
            }else{
                Node successor = minimum(node.right);
                successor.right = remove(node.right,successor.key);
                successor.left = node.left;
                node.left = node.right = null;
                responseNode =  successor;
            }
        }
        // 需要维护height,以及balance
        if(responseNode==null){
            // 有可能删除了一个叶子节点,删除后返回的是一个null,所以不需要维护height和balance
            return null;
        }
        responseNode.height = 1+Math.max(getHeight(responseNode.left),getHeight(responseNode.right));
        int balance = getBalanceFactor(responseNode);
        //LL
        if(balance>1 && getBalanceFactor(responseNode.left)>=0){
           return rightRotate(responseNode);
        }
        //RR
        if(balance<-1 && getBalanceFactor(responseNode.right)<=0){
            return leftRotate(responseNode);
        }
        //LR
        if(balance>1 && getBalanceFactor(responseNode.left)<0){
            responseNode.left = leftRotate(responseNode.left);
            return  rightRotate(responseNode);
        }
        //RL
        if(balance<-1 && getBalanceFactor(responseNode.right)>0){
            responseNode.right = rightRotate(responseNode.right);
            return  leftRotate(responseNode);
        }

        return responseNode;
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


    public boolean isBalanced() {

        return isBalanced(root);

    }

    private boolean isBalanced(Node node) {

        if(node==null){
            return true;
        }
        int balance = getBalanceFactor(node);
        if(Math.abs(balance)>1){
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);

    }

    public boolean isBST() {
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root,keys);
        for (int i = 1; i < keys.size(); i++) {
            if(keys.get(i).compareTo(keys.get(i-1))<0){
                return false;
            }
        }
        return true;
    }

    private void inOrder(Node node, ArrayList<K> keys) {
        if(node == null){
            return;
        }
        inOrder(node.left,keys);
        keys.add(node.key);
        inOrder(node.right,keys);
    }

    public static void main(String[] args) {
        testItsProp();
//        testBSTAndAVLTree();
    }

    public static void testItsProp() {
        ArrayList<String> words = new ArrayList<>(200000);
        FileOperation.readFile("src/PrideAndPrejudice.txt",words);

        long time = System.currentTimeMillis();
        AVLTree<String,Integer> map = new AVLTree<>();
        for (String word : words) {
            if(map.contains(word)){
                map.set(word,map.get(word)+1);
            }else{
                map.add(word,1);
            }
        }
        for (String word : words) {
            map.remove(word);
            if(!map.isBalanced() || !map.isBST()){
                throw new IllegalArgumentException("this tree is not a balance binary search tree (AVLTree).");
            }
        }
        System.out.println(System.currentTimeMillis()-time+"ms");
        System.out.println("总词量为 :"+words.size());
        System.out.println("词汇量为 :"+map.size());
        System.out.println("pride 出现的次数 :"+map.get("pride"));
        System.out.println("prejudice 出现的次数:"+map.get("prejudice"));
        System.out.println("isBST: "+map.isBST());
        System.out.println("isBalance : "+map.isBalanced());
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
