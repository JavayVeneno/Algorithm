package dataStructure.stack;

import dataStructure.linkedlist.LinkedList;

import java.util.Random;

public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> linkedList;

    public LinkedListStack(){
        linkedList = new LinkedList<>();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public void push(E e) {
        linkedList.addFirst(e);
    }

    @Override
    public E pop() {
        return linkedList.removeFirst();
    }

    @Override
    public E peek() {
        return linkedList.getFirst();
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("Stack : top ");
        sb.append(linkedList);
        return sb.toString();
    }

    public static void main(String[] args) {
//        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
//        for (int i = 0; i <5 ; i++) {
//            linkedListStack.push(i);
//            System.out.println(linkedListStack);
//        }
//        linkedListStack.pop();
//        System.out.println(linkedListStack);

        // 这一块的时间复杂度不好比较,但从算法与数据结构来说,LinkedListStack的压栈出栈操作都是O(1),而ArrayStack则是因为会发生扩容操作,均摊复杂度后O((2n+1)/n)即O(1*C)
        // 但是在编程语言中,比如java在linkedlist中是一直在new 一个Node节点,一直在寻找内存开辟空间。
        int optCount = 10_000_000;
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();

        double arrayStackTime = testStack(arrayStack, optCount);
        double linkedListStackTime = testStack(linkedListStack, optCount);

        System.out.printf("ArrayStack use %f s %n",arrayStackTime);
        System.out.printf("LinkedListStack use %f s %n",linkedListStackTime);

    }


    private  static double testStack(Stack<Integer> stack,int optCount ){


        Random random = new Random();
        long start = System.nanoTime();
        for (int i = 0; i < optCount; i++) {
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i <optCount ; i++) {
            stack.pop();
        }

        long end = System.nanoTime();

        return (end-start)/1000000000.0;
    }
}
