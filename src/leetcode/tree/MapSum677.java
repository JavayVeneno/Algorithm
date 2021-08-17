package leetcode.tree;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class MapSum677 {


    private class Node{
        public boolean isWord;
        public int val;
        public TreeMap<Character,Node> next;

        public Node(int val){
            this.val=val;
            this.isWord = true;
            next = new TreeMap<>();

        }
        public Node(boolean isWord,int val){
            this.isWord = isWord;
            this.val = val;
            next = new TreeMap<>();
        }
        public Node(){
            this(false,0);
        }

    }

    private Node root;


    /** Initialize your data structure here. */
    public MapSum677() {
        root = new Node();
    }
    
    public void insert(String key, int val) {
        Node cur = root;
        for (int i = 0; i < key.length(); i++) {
            char k = key.charAt(i);
            if(cur.next.get(k)==null){
                cur.next.put(k,new Node());
            }
            cur = cur.next.get(k);
        }
        if(!cur.isWord){
            cur.isWord = true;
        }
        // leetCode希望值可以覆盖,,所以到底了,不管该字母是否为一个单词
        cur.val =val;
    }
    
    public int sum(String prefix) {
        Node cur = root;
        for (int i =0;i<prefix.length();i++){
            char key = prefix.charAt(i);
            if(cur.next.get(key)==null){
                // 前缀没匹配上,直接return 0;
                return 0;
            }
            //指针移动
            cur = cur.next.get(key);
        }
        // 已经到匹配到了,cur表示最后一个字母的节点,sum(该节点所有子节点即可)
        return sum(cur);
    }

    private int sum(Node node  ) {
        // 递归终止条件是什么?
        // node.next.size==0,即末尾了
//        if(node.next.size()==0){
//            return node.val;
//        }

        int res = node.val;
        // 其实在这里如果node.next.size()==0(循环不执行)已经是递归终止条件了,所以前面的代码可以注释掉
        for (char key : node.next.keySet()){
            res += sum(node.next.get(key));
        }
        return res;
    }

    public static void main(String[] args) {


        MapSum677 mapSum = new MapSum677();
        mapSum.insert("apple",3);
        mapSum.insert("app",2);
        System.out.println(mapSum.sum("ap"));
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */