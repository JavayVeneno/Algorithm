package dataStructure.cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache <K, V>{

    static class Node<K,V> {

        private K key;
        private V value;

        private Node<K,V> prev;

        private Node<K,V> next;
        public Node(){}

        public Node(K key, V value){
            this.key = key;
            this.value = value;

        }

        @Override
        public String toString() {
            Object pk = prev == null ? "null": prev.key;
            Object nk = next == null ? "null": next.key;
            return "{prev : " + pk + ",key : " + this.key +  ", value : " + this.value + ", next " + nk +"}";
        }
    }
    private int initCapacity;

    private Map<K, Node<K,V>> map;

    private Node<K, V> head;

    private Node<K, V> tail;

    public LRUCache(int initCapacity) {
        this.initCapacity = initCapacity;

        map = new HashMap<>(initCapacity);

        head = new Node<>();

        tail = new Node<>();

        head.next = tail;

        tail.prev = head;
    }

    public V get(K k){
        Node<K, V> node = map.get(k);
        if (node == null) {
            return null;
        }
        moveNodeToHead(node);
        return node.value;
    }


    private void put(K k, V v) {
        Node<K, V> node = map.get(k);
        if (node == null) {
            if (map.size()>=initCapacity) {
                //从map中移除tail的prev
                map.remove(tail.prev.key);
                removeTailNode();
            }
            // 将put的数据组织成一个Node
            Node<K, V> put = new Node<>(k, v);
            map.put(k, put);
            // 新节点需要加入首节点
            addToHead(put);
        } else{
            node.value=v;
            moveNodeToHead(node);
        }
    }

    private void addToHead(Node<K,V> add) {
        add.prev = head;
        add.next = head.next;
        head.next.prev = add;
        head.next = add;
    }

    private void moveNodeToHead(Node<K, V> node) {
        remove(node);
        addToHead(node);
    }

    private void remove(Node<K,V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void removeTailNode(){
        //首尾节点为自己的内部默认的空节点，只做指针使用，所以移除尾节点是移除tail的prev
        remove(tail.prev);
    }

    public static void main(String[] args) {
        LRUCache<Integer, Integer> lruCache = new LRUCache<>(5);

        lruCache.put(1,1);
        System.out.println(lruCache); //1
        lruCache.put(2,2);
        System.out.println(lruCache); // 2 1
        lruCache.put(3,3);
        System.out.println(lruCache); // 3 2 1
        lruCache.get(1);
        System.out.println(lruCache); // 1 3 2

        lruCache.put(4,4);
        System.out.println(lruCache); // 4 1 3 2

        lruCache.put(5,5);
        System.out.println(lruCache); // 5 4 1 3 2

        lruCache.put(6,6);

        System.out.println(lruCache); //6 5 4 1 3

    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        Node<K,V> current = this.head.next;
        while (current != this.tail) {
            stringBuilder.append(current);

            if (current.next != tail) {
                stringBuilder.append(" -> ");
            }
            current = current.next;
        }

        return stringBuilder.toString();
    }
}
