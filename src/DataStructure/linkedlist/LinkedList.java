package DataStructure.linkedlist;

import java.util.Objects;

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
            return e.toString();
        }
    }
// 改用虚拟头部节点
    private Node dummyHead ;
    private int size;

    public int getSize(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }

    public LinkedList(){
        // 虚拟头部节点是真实存在的节点,只是不可用,对用户无感知便于我们统一逻辑
        dummyHead  = new Node();
        size = 0;
    }

    // 递归的ａｄｄ一个元素
    public void addR(E e){
        dummyHead.next = addR(dummyHead.next,e);
    }

    private Node addR(Node next, E e) {
        if(next == null){
            return new Node(e);
        }
        next.next=addR(next.next,e);
        return next;

    }

    // 时间复杂度分析,O(1)
    public void addFirst(E e){
       // 在头部添加一个元素时,这个元素的next就应该指向原头部,自己再成为新头部
//        Node node = new Node(e);
//        node.next = head;
//        head = node;
       // 更加优雅的写法
//        head = new Node(e,head);
        // 使用 dummyHead可以复用Add
        add(0,e);
//        size++;
    }
    // 时间复杂度分析,O(n)
    public void add(int index,E e){
        if(index < 0 || index > size){
            throw new RuntimeException("can not add with wrong index");
        }
        // 使用虚拟头节点就不用判断特殊情况
//        if(index == 0){
//            addFirst(e);
//        }else{
            // 在指定索引的位置添加一个元素,那么此添加的元素要代替的当前索引处的元素需要找到当前索引处的前一位元素
            Node prev = dummyHead; // 从dummyHead开始循环,起点就更早一个节点,所以循环次数将会加一,所以原有循环将被抵消
            for (int i = 0; i < index; i++) {
                prev = prev.next;
            }
            // 找到该index处的前一个元素后,只需要让自己指向下一个元素,再让前一个元素指向自己

//            Node current  = new Node(e);
//            current.next = prev.next;
//            prev.next = current;

            // 更加优雅的写法
            prev.next = new Node(e,prev.next);
            size++;
//        }

    }
    // 时间复杂度分析,O(n)
    public void addLast(E e){
        add(size,e);
    }


    // 时间复杂度分析,O(n)
    public E get(int index){
        if(index < 0 || index > size){
            throw new RuntimeException("can not get with wrong index");
        }
        Node current = dummyHead.next;
        for (int i = 0; i <index ; i++) {
            current = current.next;
        }
        return current.e;
    }

    // 时间复杂度分析,O(1)
    public E getFirst(){
        return get(0);
    }

    // 时间复杂度分析,O(n)
    public E getLast(){
        return get(size-1);
    }

    // 时间复杂度分析,O(n)
    public void set(int index,E e){
        if(index < 0 || index >= size){
            throw new RuntimeException("can not set with wrong index");
        }
        Node current = dummyHead.next;
        for (int i = 0; i <index ; i++) {
            current = current.next;
        }
        current.e = e;
    }

    // 时间复杂度分析,O(n)
    public boolean contains(E e){
        Node current = dummyHead.next;

        while (current != null ){
            if(Objects.equals(current.e,e)){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // 时间复杂度分析,O(n)
    public E remove(int index){
        if(index < 0 || index >= size){
            throw new RuntimeException("can not remove with wrong index");
        }
        Node prev = dummyHead;
        for (int i = 0; i <index ; i++) {
            prev = prev.next;
        }
        Node current = prev.next;
        prev.next = current.next;
        current.next = null;
        size--;
        return current.e;
    }

    // 时间复杂度分析,O(1)
    public E removeFirst(){
        return remove(0);
    }

    // 时间复杂度分析,O(n)
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

        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
        linkedList.add(2,666);
        System.out.println(linkedList);
        System.out.println(linkedList.contains(666));
        System.out.println(linkedList.contains(6));
        System.out.println(linkedList.get(2));

        linkedList.remove(2);
        System.out.println(linkedList);
        linkedList.removeFirst();
        System.out.println(linkedList);
        linkedList.removeLast();
        System.out.println(linkedList);

        linkedList.addR(9);
        System.out.println(linkedList);
    }
}
