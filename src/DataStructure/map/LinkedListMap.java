package DataStructure.map;


import common.FileOperation;

import java.util.ArrayList;

public class LinkedListMap<K,V> implements Map<K,V> {

    private class Node{
        public K key;
        public V value;
        public Node next;
        public Node(K key,V value,Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
        public Node(K key,V value){
            this(key,value,null);
        }
        public Node(K key){
            this(key,null,null);
        }
        public Node(){
            this(null,null,null);
        }

        @Override
        public String toString(){
            return key.toString()+":"+ value.toString();
        }

    }

    public Node dummyHead;
    public int size;

    public LinkedListMap(){
        size =0;
        dummyHead = new Node();
    }

    @Override
    public int size() {
        return size;
    }
    @Override
    public boolean isEmpty(){
        return size==0;
    }

    public Node getNode(K key){
        Node cur = dummyHead.next;
        while(cur!=null){
            if(key.equals(cur.key)){
                return cur;
            }else{
                cur = cur.next;
            }
        }
        return null;
    }

    @Override
    public boolean contains(K key){
        return getNode(key)!=null;
    }

    @Override
    public V get(K key){
        Node node = getNode(key);
        return node==null?null:node.value;
    }

    @Override
    public void add(K key ,V value){
        Node node = getNode(key);
        if(node==null){
            dummyHead.next = new Node(key,value,dummyHead.next);
            size++;
        }else{
            node.value=value;
        }
    }

    @Override
    public void set(K key,V value){
        Node node = getNode(key);
        if(node==null){
           throw new IllegalArgumentException("key doesn't exist");
        }else{
            node.value=value;
        }
    }

    @Override
    public V remove(K key){

        Node prev = dummyHead;
        while(prev.next!=null){
            if(prev.next.key.equals(key)){
                break;
            }else{
                prev = prev.next;
            }
        }
        if(prev.next != null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
            return delNode.value;
        }
        return null;
    }

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>(200000);
        FileOperation.readFile("src/PrideAndPrejudice.txt",words);

        long time = System.currentTimeMillis();
        Map<String,Integer> map = new LinkedListMap<>();
        for (String word : words) {
            if(map.contains(word)){
                map.set(word,map.get(word)+1);
            }else{
                map.add(word,1);
            }
        }
        System.out.println(System.currentTimeMillis()-time+"ms");
        System.out.println("总词量为 :"+words.size());
        System.out.println("词汇量为 :"+map.size());
        System.out.println("pride 出现的次数 :"+map.get("pride"));
        System.out.println("prejudice 出现的次数:"+map.get("prejudice"));

    }

}
