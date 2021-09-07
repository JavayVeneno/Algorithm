package dataStructure.set;

public interface Set<E> {

    void add(E e);

    void remove(E e);

    int size();

    boolean contains(E e);

    boolean isEmpty();


}
