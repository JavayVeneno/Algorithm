package dataStructure.queue;

public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front,tail,size;

    @SuppressWarnings("unchecked")
    public LoopQueue(int capacity){
        data = (E[]) new Object[capacity+1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue(){
        this(10);
    }

    public int getCapacity(){
        return data.length-1;
    }
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty(){
        return front == tail;
    }

    @Override
    public E getFront() {

        return data[front];
    }

    @Override
    public void enqueue(E e) {
        if((tail+1)%data.length==front){
            resize(getCapacity()<<1);
        }
        data[tail]=e;
        tail = (tail+1)%data.length;
        size++;
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        E[] newData = (E[]) new Object[capacity+1];
        for (int i = 0; i < size ; i++) {
            newData[i]= data[(front+i)%data.length];
        }
        front = 0;
        tail = size;
        data=newData;
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("cannot dequeue from an empty queue");
        }
        E res = data[front];
        data[front]=null;
        front =  (front+1)%data.length;
        size--;


        if(size==getCapacity()>>2 && getCapacity()>>1 !=0 ){
            resize(getCapacity()>>1);
        }

        return res;
    }


    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Queue size = %d , capacity = %d %n",size,getCapacity()));
        sb.append("front [");
        for (int i = front; i !=tail ; i=(i+1)%data.length) {
            sb.append(data[i]);
//            if(i!=(tail-1)%data.length){
//                sb.append(',');
//            }
            if((i+1)%data.length !=tail){
                sb.append(',');
            }

        }
        sb.append("] tail");
        return sb.toString();
    }

    public static void main(String[] args) {
        LoopQueue<Integer> queue = new LoopQueue<>(3);
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
