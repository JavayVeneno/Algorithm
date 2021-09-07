package dataStructure.set;

import dataStructure.linkedlist.LinkedList;
import common.FileOperation;

import java.util.ArrayList;

public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> list;
    public LinkedListSet(){
        list = new LinkedList<>();
    }

    @Override
    public void add(E e) {
        if(!contains(e)){
            list.addFirst(e);
        }
    }

    @Override
    public void remove(E e) {
        list.removeElement(e);
    }

    @Override
    public int size() {
        return list.getSize();
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>(200000);
        FileOperation.readFile("src/PrideAndPrejudice.txt",words);
        System.out.println(words.size());
        long time = System.currentTimeMillis();
        LinkedListSet<String> set = new LinkedListSet<>();
        for (String word : words) {
            set.add(word);
        }
        System.out.println(System.currentTimeMillis()-time+"ms");
        System.out.println(set.size());
    }
}
