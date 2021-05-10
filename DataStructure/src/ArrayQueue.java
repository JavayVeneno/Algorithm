public class ArrayQueue<E> implements Queue<E> {

    private Array<E> array;

    public ArrayQueue(){
        array = new Array<>();
    }

    public ArrayQueue(int capacity){
        array  = new Array<>(capacity);
    }


    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public String toString(){

        StringBuilder sb = new StringBuilder();
        sb.append("Queue: front [");
        for (int i = 0; i <array.getSize() ; i++) {
            sb.append(array.get(i));
            if(i<array.getSize()-1){
                sb.append(',');
            }
        }
        sb.append("] tail");
        return sb.toString();
    }


    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayQueue<>(10);
        for (int i = 0; i < 10 ; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if(i%3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }


        }
    }
}
