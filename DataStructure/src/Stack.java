public interface Stack<E> {

    boolean isEmpty();

    int getSize();

    void push(E e);

    E pop();

    E peek();
}
