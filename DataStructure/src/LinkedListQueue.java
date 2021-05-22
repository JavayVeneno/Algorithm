public class LinkedListQueue<E> implements Queue<E> {

    private class Node{
        public E e;
        public Node next;
        public Node(E e,Node next){
            this.e = e;
            this.next = next;
        }
        public Node(E e){
            this(e,null);
        }
        public Node(){
            this(null,null);
        }
    }

    private int size;
    private Node head,tail;

    public LinkedListQueue(){
        head = new Node();
        tail = new Node();
    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return tail == null;
    }

    @Override
    public E getFront() {
        return head.e;
    }

    @Override
    public void enqueue(E e) {
        if(tail.e == null){
            head.e=e;
            tail = head;
        }else{
            tail.next = new Node(e);
            tail = tail.next;
        }

        size++;
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new RuntimeException("can not dequeue from empty queue");
        }

        Node current = head;
        head = head.next;
        current.next = null;
        if(head == null){
            tail = null;
        }
        size--;
        return current.e;

    }


    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("front ");
        Node current = head;
        while(current != null ){
            sb.append(current.e+"->");
            current = current.next;
        }

        sb.append("NULL tail");
        return sb.toString();

    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> queue =  new LinkedListQueue<>();
        for (int i = 0; i < 10 ; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if(i%3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
//        for (int i = 0; i < 10 ; i++) {
//            queue.dequeue();
//            System.out.println(queue);
//
//        }
    }
}
