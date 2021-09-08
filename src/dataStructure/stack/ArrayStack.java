package dataStructure.stack;

import dataStructure.array.Array;

public class ArrayStack<E> implements Stack<E> {

    Array<E> array;


    public ArrayStack(){
        array = new Array<>();
    }
    public ArrayStack(int capacity){
        array = new Array<>(capacity);
    }

    public int getCapacity(){
        return array.getCapacity();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Stack: [");
        for (int i = 0; i <array.getSize() ; i++) {
            sb.append(array.get(i));
            if(i<array.getSize()-1){
                sb.append(",");
            }
        }
        sb.append("] top ");
        return sb.toString();
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new ArrayStack<>();
        for (int i = 0; i <5 ; i++) {
            stack.push(i);
            System.out.println(stack);
        }
        stack.pop();
        System.out.println(stack);
    }
}
