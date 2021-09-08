package dataStructure.queue;

public class LoopQueueNotUseSpace<E> implements Queue<E> {

    private E[] data;
    private int front,tail;
    private int size;

    @SuppressWarnings("unchecked")
    public LoopQueueNotUseSpace(int capacity){
        data = (E[]) new Object[capacity];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueueNotUseSpace(){
        this(10);
    }

    public int getCapacity(){
        return data.length;
    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
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
        if(size==data.length){
            resize(data.length<<1);
        }
        data[tail]=e;
        tail=(tail+1)%data.length;
        size++;
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        E[] newData = (E[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
           newData[i] =  data[(front+i)%data.length];
        }
        front = 0;
        tail=size;
        data = newData;
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("cannot dequeue from an empty queue");
        }

        E res = data[front];
        data[front] = null;
        front=(front+1)%data.length;
        size--;
        if(size == data.length>>2 && data.length>>1 !=0){
            resize(data.length>>1);
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
            if((i+front)%data.length !=(tail-1)%data.length ){
                sb.append(',');
            }
        }
        sb.append("] tail");
        return sb.toString();

    }

    public static void main(String[] args) {
        LoopQueueNotUseSpace<Integer> queue = new LoopQueueNotUseSpace<>(10);
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
