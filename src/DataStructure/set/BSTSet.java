package DataStructure.set;

import DataStructure.tree.BST;
import common.FileOperation;

import java.util.ArrayList;


public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private BST<E> bst;

    public BSTSet(){
        bst = new BST<>();
    }

    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public int size() {
        return bst.size();
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>(200000);
        FileOperation.readFile("src/PrideAndPrejudice.txt",words);
        System.out.println(words.size());

        Set<String> set = new BSTSet<>();
        for (String word : words) {
            set.add(word);
        }
        System.out.println(set.size());
    }
}
