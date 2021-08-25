package DataStructure.set;

import DataStructure.tree.AVLTree;
import common.FileOperation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

public class AVLSet<E extends Comparable<E>> implements Set<E> {

    private AVLTree<E,Object> avl;

    public AVLSet(){
        this.avl = new AVLTree<>();
    }


    @Override
    public void add(E e) {
        avl.add(e,null);
    }

    @Override
    public void remove(E e) {
        avl.remove(e);
    }

    @Override
    public int size() {
        return avl.size();
    }

    @Override
    public boolean contains(E e) {
        return avl.contains(e);
    }

    @Override
    public boolean isEmpty() {
        return avl.isEmpty();
    }

    public static void main(String[] args) {
        test(new AVLSet<>());
        test(new BSTSet<>());
        test(new LinkedListSet<>());
        testJdk(new HashSet<>());
        testJdk(new TreeSet<>());

    }

    private static void testJdk(java.util.Set<String> set) {
        ArrayList<String> words = new ArrayList<>(200000);
        FileOperation.readFile("src/PrideAndPrejudice.txt",words);
        System.out.println(words.size());
        long time = System.nanoTime();
        for (String word : words) {
            set.add(word);
        }
        System.out.println(set.getClass().getName()+":"+ (System.nanoTime()-time)/1000000000.0+"ms");
        System.out.println(set.size());
    }

    public static void test(Set<String> set) {
        ArrayList<String> words = new ArrayList<>(200000);
        FileOperation.readFile("src/PrideAndPrejudice.txt",words);
        System.out.println(words.size());
        long time = System.nanoTime();
        for (String word : words) {
            set.add(word);
        }
        System.out.println(set.getClass().getName()+":"+ (System.nanoTime()-time)/1000000000.0+"ms");
        System.out.println(set.size());
    }
}
