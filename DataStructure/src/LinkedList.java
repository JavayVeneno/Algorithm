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

    private Node head ;
    private int size;

    public int getSize(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }

    public LinkedList(){
        head  = null;
        size = 0;
    }

    public void addFirst(E e){
       // 在头部添加一个元素时,这个元素的next就应该指向原头部,自己再成为新头部
//        Node node = new Node(e);
//        node.next = head;
//        head = node;
       // 更加优雅的写法
        head = new Node(e,head);

        size++;
    }
    public void add(int index,E e){
        if(index < 0 || index > size){
            throw new RuntimeException("can not add with wrong index");
        }
        if(index == 0){
            addFirst(e);
        }else{
            // 在指定索引的位置添加一个元素,那么此添加的元素要代替的当前索引处的元素需要找到当前索引处的前一位元素
            Node prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            // 找到该index处的前一个元素后,只需要让自己指向下一个元素,再让前一个元素指向自己

//            Node current  = new Node(e);
//            current.next = prev.next;
//            prev.next = current;

            // 更加优雅的写法
            prev.next = new Node(e,prev.next);
            size++;
        }

    }
    public void addLast(E e){
        add(size,e);
    }

}
