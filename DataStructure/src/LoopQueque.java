public class LoopQueque<E> implements Queue<E> {

    private E[] data;
    private int front,tail,size;

    @SuppressWarnings("unckecked")
    public LoopQueque(int capacity){
        data = (E[]) new Object[capacity+1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueque(){
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
    public void enqueque(E e) {
        if((tail+1)%data.length==front){
            resize(getCapacity()<<1);
        }
        data[tail]=e;
        tail = (tail+1)%data.length;
        size++;
    }

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
    public E dequeque() {
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
        LoopQueque<Integer> queue = new LoopQueque<>(3);
        for (int i = 0; i < 10 ; i++) {
            queue.enqueque(i);
            System.out.println(queue);
            if(i%3 == 2){
                queue.dequeque();
                System.out.println(queue);
            }


        }
        System.out.println(queue);

    }
}
