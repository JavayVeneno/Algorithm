public interface Queue<E> {

    int getSize();
    boolean isEmpty();
    E getFront();
    void enqueque(E e);
    E dequeque();
}
