package leetcode.linkedlist;

import java.util.Objects;
import javafx.util.Pair;

public class LinkedList<E> {


    private class Node{
        private E e;
        private Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }
        public Node(E e){
            this(e,null);
        }
        public Node(){
            this(null,null);
        }

        @Override
        public String toString() {
//            return e.toString();


        StringBuilder sb =  new StringBuilder();
        Node current = this;
        while (current != null){
            sb.append(current.e+"->");
            current = current.next;
        }
        sb.append("NULL");
        return sb.toString();

        }
    }
    private Node dummyHead ;
    private int size;

    public int getSize(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }

    public LinkedList(){
        dummyHead  = new Node();
        size = 0;
    }

    public void addFirst(E e){
        add(0,e);
    }
    public void add(int index,E e){
        if(index < 0 || index > size){
            throw new RuntimeException("can not add with wrong index");
        }
        //使用递归添加到链表中

        dummyHead.next = add(index,dummyHead.next,e,1);

        size++;

    }
    private String generateDepthString(int depth) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <depth ; i++) {
            sb.append("--|");
        }
        return sb.toString();
    }

    // 给我一个头节点,和想要放置的索引,以及元素,我返回你一个新的Node
    private Node add(int index, Node head, E e,int depth) {
        String depthString = generateDepthString(depth);
        System.out.println(depthString+"add: "+ e +" into [" + index+"] ,此时的head:"+head);
        if(index==0){
            // 如果index是0,说明新添加的元素要取代头节点,原来的头节点变成次节点
            ++depth;
            Node node = new Node(e, head);
            System.out.println(depthString+"return: "+node + " index:"+0);
            return node;
        }
        // 如果新添加的节点不是头节点
        // 那么我们就调用add(index,head,e)方法给我返回一个添加成功的次节点
        // 因为不是在头节点添加,所以index应该先递减,并且应该把次节点交给该方法去处理
        head.next = add(index-1,head.next,e,depth+1);
        System.out.println(depthString+"return: "+head + " index:"+index);
        return head;
    }

    // 时间复杂度分析,O(n)
    public void addLast(E e){
        add(size,e);
    }


    // 时间复杂度分析,O(n)
    public E get(int index){
        if(index < 0 || index >= size){
            throw new RuntimeException("can not get with wrong index");
        }
//        Node current = dummyHead.next;
//        for (int i = 0; i <index ; i++) {
//            current = current.next;
//        }
//        return current.e;
    //改用递归来查找
        Node current = getNodeByIndex(index,dummyHead.next);
        return current.e;

    }
    public Node getNodeByIndex(int index,Node head){

        if(index == 0 ){
            return head;
        }
        return getNodeByIndex(--index,head.next);

    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size-1);
    }

    public void set(int index,E e){
        if(index < 0 || index >= size){
            throw new RuntimeException("can not set with wrong index");
        }
        Node head = dummyHead.next;
       dummyHead.next = setNode(index,head,e);


    }

    private Node setNode(int index, Node head ,E e) {

        if(index == 0 ){
            head.e = e;
            return head;
        }

        head.next = setNode(--index,head.next,e);
        return head;
    }

    public boolean contains(E e){

        //使用递归
      return containsByNode(e,dummyHead.next);
    }

    private boolean containsByNode(E e, Node head) {


        if(head == null ){
            return false;
        }
        if( Objects.equals(head.e,e)){
            return true;
        }

        return containsByNode(e,head.next);
    }


    // 时间复杂度分析,O(n)
    public E remove(int index){
        if(index < 0 || index >= size){
            throw new RuntimeException("can not remove with wrong index");
        }
        Pair<Node,E> pair= removeByIndexFromNode(index, dummyHead.next);
        dummyHead.next = pair.getKey();
        if(pair.getKey() == null){
            return null;
        }
        size--;
        return pair.getValue();
    }

    private Pair<Node, E> removeByIndexFromNode(int index, Node head){

        if(index == 0){
            return new Pair<>(head.next,head.e);
        }
        Pair<Node, E> nodeEPair = removeByIndexFromNode(--index, head.next);
        head.next =nodeEPair.getKey();
        E x = nodeEPair.getValue();
        return new Pair<>(head,x);
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size-1);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (Node current = dummyHead.next;current!=null;current=current.next){
            sb.append(current.e).append("->");
        }
        sb.append("NULL");
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();

//        linkedList.add(0,1);
//        linkedList.add(1,2);
//        System.out.println(linkedList);
        for (int i = 0; i < 5; i++) {
            linkedList.addLast(i);
//            System.out.println(linkedList);
        }
        System.out.println(linkedList.removeLast());
        System.out.println(linkedList.removeFirst());
        System.out.println(linkedList);

        System.out.println(linkedList.getLast());
        System.out.println(linkedList.getFirst());
        linkedList.set(2,4);
        linkedList.set(1,66);
        System.out.println(linkedList.contains(4));
//        linkedList.add(2,666);
//        System.out.println(linkedList);
//        linkedList.add(2,666);
//        System.out.println(linkedList);
//        System.out.println(linkedList.contains(666));
//        System.out.println(linkedList.contains(6));
//        System.out.println(linkedList.get(2));
//
//        linkedList.remove(2);
//        System.out.println(linkedList);
//        linkedList.removeFirst();
//        System.out.println(linkedList);
//        linkedList.removeLast();
//        System.out.println(linkedList);
    }
}
