package leetcode.tree;

import java.util.TreeMap;

class WordDictionary211 {

    private class Node{
        public boolean isWord;
        public TreeMap<Character,Node> next;

        public Node(){
            this(false);
        }
        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }
    }

    private Node root;
    /** Initialize your data structure here. */
    public WordDictionary211() {
        root = new Node();
    }
    
    public void addWord(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(cur.next.get(c)==null){
                cur.next.put(c,new Node());
            }
            cur = cur.next.get(c);
        }
        if (!cur.isWord){
            cur.isWord = true;
        }
    }
    
    public boolean search(String word) {
        return match(root,word,0);
    }

    private boolean match(Node node, String word, int index) {

        if(index==word.length()){
            return node.isWord;
        }

        char c = word.charAt(index);

       if('.'!= c ){
           if(node.next.get(c)==null){
               return false;
           }else{
               return match(node.next.get(c),word,index+1);
           }
       }else{
          for(char nextChar : node.next.keySet()){
              if( match(node.next.get(nextChar),word,index+1)){
                  return true;
              }
          }
          return false;
       }
    }

    public static void main(String[] args) {
        String[] test = {"bad","dad","mad","pad","panda"};
        WordDictionary211 wordDictionary =  new WordDictionary211();
        for (String s : test) {
            wordDictionary.addWord(s);
        }

        String[] input = {"","bad","dad","mad","pad","pan",".ad","b.."};
        for (String s : input) {
            System.out.println(wordDictionary.search(s));
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */