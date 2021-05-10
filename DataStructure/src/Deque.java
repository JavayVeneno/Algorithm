public class Deque<E> {

    private E[] data;
    private int front,tail;
    private int size;

    @SuppressWarnings("unchecked")
    public Deque(int capacity){
        data = (E[]) new Object[capacity];
        front = 0;
        tail = 0;
        size = 0;
    }
    public Deque(){
        this(10);
    }
    public int getSize(){
        return size;
    }

    public int getCapacity(){
        return data.length;
    }

    public E getFront() {
        if(isEmpty()){
            throw new IllegalArgumentException("cannot getFront from an empty queue");

        }
        return data[front];
    }
    public E getTail() {
        if(isEmpty()){
            throw new IllegalArgumentException("cannot getTail from an empty queue");

        }
        return data[(tail-1)%data.length];
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity){
        E[] newData = (E[]) new Object[capacity];
        for (int i = 0; i <size ; i++) {
            newData[i]= data[(i+front)%data.length];
        }
        tail=size;
        front = 0;
        data = newData;
    }
    public void addFirst(E e){
        if(size==data.length){
            resize(data.length<<1);
        }
        int first = front-1<0?(data.length-1-front)%data.length:front-1;
        data[first] = e;
        front = first;
        size++;
    }
    public void addLast(E e){
        if(size==data.length){
            resize(data.length<<1);
        }
        data[tail] = e;
        tail = (tail+1)%data.length;
        size++;
    }

    public E removeFirst(){
        if(isEmpty()){
            throw new IllegalArgumentException("cannot remove first from an empty queue");
        }
        E res = data[front];
        data[front]=null;
        front = (front+1)%data.length;
        size--;
        if (size==data.length>>2 && data.length>>1 !=0){
            resize(data.length>>1);
        }
        return res;
    }
    public E removeLast(){
        if(isEmpty()){
            throw new IllegalArgumentException("cannot remove last from an empty queue");
        }
        int last = tail-1<0?(data.length+tail-1)%data.length:tail-1;
        E res = data[last];
        data[last]=null;
        tail = (last-1)%data.length;
        size--;
        if (size==data.length>>2 && data.length>>1 !=0){
            resize(data.length>>1);
        }
        return res;
    }


    private boolean isEmpty() {
        return size==0;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Queue size = %d , capacity = %d %n",size,getCapacity()));
        sb.append("front [");
//        for (int i = 0; i <size ; i++) {
//            sb.append(data[(i+front)%data.length]);
//            if((i+front)%data.length !=tail ){
//                sb.append(',');
//            }
//        }

        for(int i = 0 ; i < size ; i ++){
            sb.append(data[(i + front) % data.length]);
            if(i != size - 1) {
                sb.append(", ");
            }
        }

        sb.append("] tail");
        return sb.toString();

    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>(10);
        for (int i = 0; i < 10 ; i++) {
            deque.addFirst(i);
            System.out.println(deque);
            if(i%3 == 2){
                deque.removeLast();
                System.out.println(deque);
            }


        }
        System.out.println(deque);
    }

}
