package dataStructure.map;

import dataStructure.tree.AVLTree;
import common.FileOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class AVLMap<K extends Comparable<K>,V> implements Map<K,V> {

    private AVLTree<K,V> avl;

    public AVLMap(){
        this.avl = new AVLTree<>();
    }

    @Override
    public void add(K key, V value) {
        avl.add(key,value);
    }

    @Override
    public V remove(K key) {
        return avl.remove(key);
    }

    @Override
    public void set(K key, V newValue) {
        avl.set(key,newValue);
    }

    @Override
    public boolean contains(K key) {
        return avl.contains(key);
    }

    @Override
    public V get(K key) {
        return avl.get(key);
    }

    @Override
    public int size() {
        return avl.size();
    }

    @Override
    public boolean isEmpty() {
        return avl.isEmpty();
    }

    public static void main(String[] args) {
        testMap(new AVLMap<>());
        testMap(new BSTMap<>());
        testMap(new LinkedListMap<>());
        testMapJDK(new HashMap<>());
        testMapJDK(new TreeMap<>());
    }

    private static void testMapJDK(java.util.Map<String, Integer> map) {
        ArrayList<String> words = new ArrayList<>(200000);
        FileOperation.readFile("src/PrideAndPrejudice.txt",words);

        long time = System.nanoTime();

        for (String word : words) {
            if(map.containsKey(word)){
                map.put(word,map.get(word)+1);
            }else{
                map.put(word,1);
            }
        }
        System.out.println(map.getClass().getName()+(System.nanoTime()-time)/1000000000.0+" s");
        System.out.println("总词量为 :"+words.size());
        System.out.println("词汇量为 :"+map.size());
        System.out.println("pride 出现的次数 :"+map.get("pride"));
        System.out.println("prejudice 出现的次数:"+map.get("prejudice"));

    }

    public static void testMap(Map<String,Integer> map) {
        ArrayList<String> words = new ArrayList<>(200000);
        FileOperation.readFile("src/PrideAndPrejudice.txt",words);

        long time = System.nanoTime();

        for (String word : words) {
            if(map.contains(word)){
                map.set(word,map.get(word)+1);
            }else{
                map.add(word,1);
            }
        }
        System.out.println(map.getClass().getName()+(System.nanoTime()-time)/1000000000.0+" s");
        System.out.println("总词量为 :"+words.size());
        System.out.println("词汇量为 :"+map.size());
        System.out.println("pride 出现的次数 :"+map.get("pride"));
        System.out.println("prejudice 出现的次数:"+map.get("prejudice"));

    }
}
