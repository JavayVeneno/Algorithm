
/**
 * @author Veneno
 * BinarySearchTree
 */
public class BST<E extends Comparable<E>> {

    private class Node{
        public E e;
        public Node left;
        public Node right;
        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }

    }

    private Node root;

    private int size;

    public BST(){
        root = null;
        size = 0;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }
// 这一段代码的逻辑并不统一,我们可以发现在根节点的时候做了特殊处理,并且子节点来回比较
//    public void add(E e){
//        // 如果root节点就是空的,那么添加的时候直接添加一个root节点即可
//        if(root == null){
//            root = new Node(e);
//            size++;
//        }else{
//            //否则的话,我们需要将该元素e添加到这个root节点之下(左还是右看具体情况)
//            add(root,e);
//        }
//    }
//
//    private void add(Node node, E e) {
//        //如果node.e等于e,那么就相当于每添加 直接终止就行
//        if(e.compareTo(node.e)==0){
//            return ;
//        }
//        // 如果当当前元素小于该节点值,并且左孩为空,则创建左孩节点,并且终止
//        else if (e.compareTo(node.e)<0 && node.left == null){
//            node.left = new Node(e);
//            size++;
//            return;
//        }// 右孩同理
//        else if(e.compareTo(node.e)>0 && node.right == null){
//            node.right = new Node(e);
//            size++;
//            return;
//        }
//        //如果出现左孩不为空则,则添加到左孩之下(左孩的左孩还是右孩看具体情况)
//        if(e.compareTo(node.e)<0){
//            add(node.left,e);
//        }else{
//            add(node.right,e);
//        }
//    }

    //我们设计一个新的方法

    public void add(E e) {
        root = add(root,e);

    }

    private Node add(Node root, E e) {

        if(root == null){
            size++;
            // 如果传来的节点是空节点时,我们需要构建一个新的节点
            // 但此时新的节点与原来的节点没有指向关系,所以我们需要把新节点返回
            return new Node(e);
        }
        if(e.compareTo(root.e)<0){
            // 如果元素小于节点值,那么应该在左节点中插入该元素
            // root的左节点要指向在左节点中
            root.left = add(root.left,e);
        }else if(e.compareTo(root.e)>0){
            //同理
            root.right = add(root.right,e);
        }
        // 相等的情况只需要 返回root即可。
        return root;

    }

    public static void main(String[] args) {
        BST bst = new BST();
        Integer[] test = ArrayGenerator.generatorRandomArray(10,10);
        for (Integer integer : test) {
            bst.add(integer);
        }
        System.out.println(bst);
    }



}
