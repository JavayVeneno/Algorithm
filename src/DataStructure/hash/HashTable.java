package DataStructure.hash;

import DataStructure.tree.AVLTree;
import DataStructure.tree.RBTree;
import common.ArrayGenerator;
import common.FileOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class HashTable<K,V> {

    private static final int upperTol  = 10;
    private static final int lowerTol = 2;


    // 将自适应扩容的capaCity取用最佳值

    private static int[] capaCity = {53,97,193,389,769,1543,3079,6151,12289,24593,
                                    49157,98317,196613,393241,786433,1572869,3145739,6291469,
                                   12582917,25165843,50331653,100663319,201326611,402653189,805306457,1610612741};

    private TreeMap<K,V>[] hashTable;
    private int size;
    private int m;

    private int index = 0;
    public HashTable(int initCapaCity){
        while(index+1<capaCity.length  && initCapaCity>capaCity[index] ){
            index++;
        }
        this.m  = capaCity[index];
        this.size=0;
        hashTable = new TreeMap[m];
        for (int i = 0; i < m; i++) {
            hashTable[i] = new TreeMap<>();
        }
    }
    public HashTable(){
        this(0);
    }

    private int hash(K key){
        return (key.hashCode() & 0x7fffffff) % capaCity[index];
    }
    public int size(){
        return size;
    }
    public void add(K key,V value){
        TreeMap<K, V> kvTreeMap = hashTable[hash(key)];
        if(!kvTreeMap.containsKey(key)){
           size++;
           if(size>=upperTol*m && index+1<=capaCity.length){
               index++;
               reSize(capaCity[index]);
           }
        }
        kvTreeMap.put(key,value);
    }

    public V remove(K key){
        TreeMap<K, V> kvTreeMap = hashTable[hash(key)];
        V remove = null;
        if(kvTreeMap.containsKey(key)){
            remove =  kvTreeMap.remove(key);
            size--;
            if(size<lowerTol*m &&  index>=1){
                index--;
                reSize(capaCity[index]);
            }
        }
        return  remove;
    }

    private void reSize(int newM) {
        int oldM = this.m;
        this.m = newM;

        TreeMap<K,V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i++) {
            newHashTable[i] = new TreeMap<>();
        }
        for (int i = 0; i < oldM; i++) {
            TreeMap<K, V> map = hashTable[i];
            for(K key: map.keySet()){
                newHashTable[hash(key)].put(key,map.get(key));
            }
        }
        hashTable = newHashTable;
    }

    public void set(K key,V value){
        TreeMap<K, V> kvTreeMap = hashTable[hash(key)];
        if(!kvTreeMap.containsKey(key)){
            throw new IllegalArgumentException("key doesn't exists!");
        }
        kvTreeMap.put(key,value);

    }

    public boolean contains(K key){
        return hashTable[hash(key)].containsKey(key);
    }

    public V get(K key){
       return  hashTable[hash(key)].get(key);
    }



    public static void main(String[] args) {
        testItsProp();
//        testRBTreeAndHashTableAndAVLTree();
//        test3();
    }

    public static void testItsProp() {
        ArrayList<String> words = new ArrayList<>(200000);
        FileOperation.readFile("src/PrideAndPrejudice.txt",words);

        long time = System.nanoTime();
        HashTable<String,Integer> map = new HashTable<>();
        for (String word : words) {
            if(map.contains(word)){
                map.set(word,map.get(word)+1);
            }else{
                map.add(word,1);
            }
        }
//        for (String word : words) {
//            map.remove(word);
//        }
        System.out.println(map.getClass().getName()+" : "+((System.nanoTime()-time)/1000000000.0)+"s");
        System.out.println("总词量为 :"+words.size());
        System.out.println("词汇量为 :"+map.size());
        System.out.println("pride 出现的次数 :"+map.get("pride"));
        System.out.println("prejudice 出现的次数:"+map.get("prejudice"));


        long time2 = System.nanoTime();
        HashMap<String,Integer> hashMap = new HashMap<>();
        for (String word : words) {
            if(hashMap.containsKey(word)){
                hashMap.put(word,hashMap.get(word)+1);
            }else{
                hashMap.put(word,1);
            }
        }
//        for (String word : words) {
//            map.remove(word);
//        }
        System.out.println(hashMap.getClass().getName()+" : "+((System.nanoTime()-time2)/1000000000.0)+"s");
        System.out.println("总词量为 :"+words.size());
        System.out.println("词汇量为 :"+map.size());
        System.out.println("pride 出现的次数 :"+map.get("pride"));
        System.out.println("prejudice 出现的次数:"+map.get("prejudice"));

    }



    public static void testRBTreeAndHashTableAndAVLTree() {
        ArrayList<String> words = new ArrayList<>(200000);
        FileOperation.readFile("src/PrideAndPrejudice.txt",words);

        long time1 = System.nanoTime();
        RBTree<String,Integer> rbTree = new RBTree<>();
        for (String word : words) {
            if(rbTree.contains(word)){
                rbTree.set(word,rbTree.get(word)+1);
            }else{
                rbTree.add(word,1);
            }
        }
        for (String word : words) {
            rbTree.contains(word);
        }
        long time2 = System.nanoTime();
        double time = (time2-time1)/1000000000.0;
        System.out.println(rbTree.getClass().getName()+"耗时:"+time+" s");

        time1 = System.nanoTime();
        HashTable<String,Integer> hashTable = new HashTable<>(131071);
        for (String word : words) {
            if(hashTable.contains(word)){
                hashTable.set(word,hashTable.get(word)+1);
            }else{
                hashTable.add(word,1);
            }
        }
        for (String word : words) {
            hashTable.contains(word);
        }
        time2 = System.nanoTime();
        time = (time2-time1)/1000000000.0;
        System.out.println(hashTable.getClass().getName()+"耗时:"+time+" s");


        time1 = System.nanoTime();
        AVLTree<String,Integer> avlTree = new AVLTree<>();
        for (String word : words) {
            if(avlTree.contains(word)){
                avlTree.set(word,avlTree.get(word)+1);
            }else{
                avlTree.add(word,1);
            }
        }
        for (String word : words) {
            avlTree.contains(word);
        }
        time2 = System.nanoTime();
        time = (time2-time1)/1000000000.0;
        System.out.println(avlTree.getClass().getName()+"耗时:"+time+" s");

    }

    public static void test3() {
        int n = 20000000;
        Integer[] array  = ArrayGenerator.generatorRandomArray(n,Integer.MAX_VALUE);


        AVLTree<Integer,Integer> avlTree = new AVLTree<>();
        long time3 = System.nanoTime();
        for (Integer integer : array) {
            avlTree.add(integer,null);
        }
        long time4 = System.nanoTime();
        double timeb = (time4-time3)/1000000000.0;
        System.out.println(avlTree.getClass().getName()+"耗时:"+timeb+" s");


        HashTable<Integer,Integer> hashTable = new HashTable<>(98317);
        long time5 = System.nanoTime();
        for (Integer integer : array) {
            hashTable.add(integer,null);
        }
        long time6 = System.nanoTime();
        double timec = (time6-time5)/1000000000.0;
        System.out.println(hashTable.getClass().getName()+"耗时:"+timec+" s");


        RBTree<Integer,Integer> map = new RBTree<>();
        long time1 = System.nanoTime();
        for (Integer integer : array) {
            map.add(integer,null);
        }
        long time2 = System.nanoTime();
        double time = (time2-time1)/1000000000.0;
        System.out.println(map.getClass().getName()+"耗时:"+time+" s");



    }


}
