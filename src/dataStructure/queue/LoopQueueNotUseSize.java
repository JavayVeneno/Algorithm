package dataStructure.queue;

public class LoopQueueNotUseSize<E> implements Queue<E> {

    private E[] data;
    private int front,tail;

    @SuppressWarnings("unchecked")
    public LoopQueueNotUseSize(int capacity){
        data = (E[]) new Object[capacity+1];
        front = 0;
        tail = 0;
    }

    public LoopQueueNotUseSize(){
        this(10);
    }

    public int getCapacity(){
        return data.length-1;
    }

    @Override
    public int getSize() {
        return tail>=front?tail-front:tail+data.length-front;
    }

    @Override
    public boolean isEmpty() {
        return tail==front;
    }

    @Override
    public E getFront() {
        if(isEmpty()){
            throw new IllegalArgumentException("cannot getFront from an empty queue");
        }
        return data[front];
    }

    @Override
    public void enqueue(E e) {
        if((tail+1)%data.length==front){
            resize(getCapacity()<<1);
        }
        data[tail]=e;
        tail=tail%data.length+1;
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        E[] newData = (E[]) new Object[capacity+1];
        int sz = getSize();
        for (int i = 0; i<getSize() ; i++) {
            newData[i] = data[(i+front)%data.length];
        }
        front = 0;
        tail =sz;
        data = newData;

    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("cannot dequeue from an empty queue");
        }
        E res =  data[front];
        data[front] = null;
        front  = (front+1)%data.length;

        if(getSize()==getCapacity()>>2 && getCapacity()>>1 !=0){
            resize(getCapacity()>>1);
        }

        return res;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Queue size = %d , capacity = %d %n",getSize(),getCapacity()));
        sb.append("front [");
//        for (int i = front; i !=tail ; i=(i+1)%data.length) {
//            sb.append(data[i]);
//
//            if((i+1)%data.length !=tail){
//                sb.append(',');
//            }
//
//        }

        for (int i = 0; i <getSize() ; i++) {
            sb.append(data[(i+front)%data.length]);
            if ((i+front)%data.length+1 != tail){
                sb.append(',');
            }
        }
        sb.append("] tail");
        return sb.toString();
    }

    public static void main(String[] args) {
        LoopQueueNotUseSize<Integer> queue = new LoopQueueNotUseSize<>(10);
        for (int i = 0; i < 10 ; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if(i%3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }


        }
        System.out.println(queue);
    }
}
