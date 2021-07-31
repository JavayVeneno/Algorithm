package DataStructure.queue;

public interface Queue<E> {

    int getSize();
    boolean isEmpty();
    E getFront();
    void enqueue(E e);
    E dequeue();
}
