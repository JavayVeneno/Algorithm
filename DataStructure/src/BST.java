import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

        return root;

    }

    //使用非递归来实现添加元素
    public void addElement(E e){

        if(root ==null){
            size++;
            root = new Node(e);
            return;
        }
        Node cur = root;
        while(cur != null){
            if(e.compareTo(cur.e)>0){
                if(cur.right == null){
                    cur.right = new Node(e);
                    size++;
                    return ;
                }else{
                    cur = cur.right;
                }
            }else if(e.compareTo(cur.e)<0){
                if(cur.left == null){
                    cur.left = new Node(e);
                    size++;
                    return ;
                }else{
                    cur = cur.left;
                }
            }else{
                return;
            }
        }

    }

    public boolean contains(E e){
        return contains(root,e);
    }

    private boolean contains(Node root, E e) {

        if(root == null){
            return false;
        }
        if(e.compareTo(root.e)==0){
            return true;
        }else if(e.compareTo(root.e)<0){
            return contains(root.left,e);
        }else{
            return contains(root.right,e);
        }
    }

    //非递归的方式实现前序遍历
    public void preOrderNR(){
        //我们可以模拟系统栈
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);
            //记住栈是先入后出,优先访问的应放在后面push
            if(cur.right != null){
                stack.push(cur.right);
            }
            if(cur.left != null){
                stack.push(cur.left);
            }
        }
    }

    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node root) {
        if(root == null){
            return ;
        }
        System.out.println(root.e);
        preOrder(root.left);
        preOrder(root.right);
    }

    public void inOrder(){
        inOrder(root);
    }
    private void inOrder(Node root){
        if(root == null){
            return;
        }
        inOrder(root.left);
        System.out.println(root.e);
        inOrder(root.right);
    }
    public void postOrder(){
        postOrder(root);
    }
    private void postOrder(Node root){
        if(root != null){
            postOrder(root.left);
            postOrder(root.right);
            System.out.println(root.e);
        }
    }

    public void levelOrder(){

        // 层序遍历,我们可以使用队列来实现
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node cur = queue.remove();
            System.out.println(cur.e);
            if(cur.left != null){
                queue.add(cur.left);
            }
            if(cur.right != null){
                queue.add(cur.right);
            }
        }
    }
    // 查找最小元素,对于二分搜索树来说,最左端即最小,所以没有左子说明到底
    public E minimum(){
        if(isEmpty()){
            throw new IllegalArgumentException("BST is Empty!");
        }
        return minimum(root).e;
    }

    private Node minimum(Node node) {

        if(node.left == null){
            return node;
        }
        return minimum(node.left);

    }

    //查找最大元素 对于二分搜索树来说,最右端即最大,所以没有右子说明到底
    public E maximum(){
        if(isEmpty()){
            throw new IllegalArgumentException("BST is Empty!");
        }
        return maximum(root).e;
    }

    private Node maximum(Node root) {
        if(root.right==null){
            return root;
        }
        return maximum(root.right);

    }

    // 移除最小元素,那么移除之后需要返回移除之后的右子节点/null,以便串联
    public E removeMin(){
        E res = minimum();
        root = removeMin(root);
        return res;
    }

    private Node removeMin(Node node) {

        // 写递归的第一件事就是先想好递归到底的情况
        // 移除最小,递归到底就是node.left是空
        if(node.left == null){
            //取出右子,因为右子将取代当前node
            Node right = node.right;
            node.right = null;
            size--;
            return right;
        }
        // 如果没有到底那么就递归调用node.left
        node.left = removeMin(node.left);
        return node;
    }

    public E removeMax(){
        E res = maximum();
        root = removeMax(root);
        return res;
    }

    private Node removeMax(Node node) {

        if(node.right==null){
            Node left = node.left;
            node.left = null;
            size--;
            return left;
        }
        node.right = removeMax(node.right);
        return node;
    }

    public void remove(E e){
        root = removeByLtSuccessor(root,e);
    }

    private Node removeByGtSuccessor(Node node, E e) {
        // 首先确立递归终止条件,即node为空时
        if(node == null){
            // 没有找到该元素
            return null;
        }
        // 除此之外,分三种情况
        if(e.compareTo(node.e)<0){
            // 待删除元素小于当前节点,往当前的左子去删除
            node.left = removeByGtSuccessor(node.left,e);
            return node;
        }
        else if(e.compareTo(node.e)>0){
            // 待删除元素大于当前节点,往当前的右子去删除
            node.right = removeByGtSuccessor(node.right,e);
            return node;
        }
        else{
            // 待删除元素等于当前节点,删除当前节点,分三种情况
            if(node.left == null){
                //当前元素没有左子,将右子选作继承人
                Node right = node.right;
                node.right = null;
                size--;
                return right;
            }
            if(node.right == null){
                //当前元素没有右子,将左子选作继承人
                Node left = node.left;
                node.left = null;
                size--;
                return left;
            }
            // 左右子均存在
            //选中大于当前值的最小值来继承;
            // 找到右子最小节点,删除右子最小节点返回最新的右子并连接到successor
            //连接左子于successor
            // 删除右子的最小节点,size--,其实白减了
            // 因为我们将successor指向了它,所以理论需要补充一个size++的逻辑,后面置空了node节点,所以需要补size--;
            // 一加一减抵消了,所以不需要再--了
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }
    private Node removeByLtSuccessor(Node node, E e) {
        // 首先确定递归终止条件
        if(node ==null){
            return null;
        }
        if(e.compareTo(node.e)<0){
            node.left = removeByLtSuccessor(node.left,e);
            return node;
        }
       else if(e.compareTo(node.e)>0){
            node.right = removeByLtSuccessor(node.right,e);
            return node;
        }
        else{
            if(node.left == null){
                Node right = node.right;
                node.right = null;
                size--;
                return right;
            }
            if(node.right == null){
                Node left = node.left;
                node.left = null;
                size--;
                return left;
            }
            Node successor = maximum(node.left);
            successor.left = removeMax(node.left);
            successor.right =  node.right;
            node.right = node.left = null;
            return successor;
        }
    }

    @Override
    public String toString(){

        StringBuilder sb  = new StringBuilder();
        generateBSTString(root,0,sb);
        return sb.toString();
    }

    private void generateBSTString(Node root, int depth, StringBuilder sb) {
        if(root == null){
            sb.append(generateDepthString(depth)+"null \n");
            return ;
        }
        sb.append(generateDepthString(depth)+root.e+"\n");
        generateBSTString(root.left,depth+1,sb);
        generateBSTString(root.right,depth+1,sb);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth ; i++) {
            res.append("--");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
//        Integer[] test ={5,3,6,8,4,2};
        //
        Integer[] test = ArrayGenerator.generatorRandomArray(1000,1000);
        for (Integer integer : test) {
            bst.add(integer);
        }

        ArrayList<Integer> list  = new ArrayList<>(1000);
        while (bst.size!=0) {
            Integer maximum = bst.maximum();
            bst.remove(maximum);
            list.add(maximum);
        }

        System.out.println(bst);
        for (int i = 1; i <list.size() ; i++) {
            if(list.get(i-1)<list.get(i)){
                throw new IllegalArgumentException("remove err");
            }
        }
        System.out.println("remove completed");
//        System.out.println(bst.contains(3));
//        System.out.println(bst.contains(4));
//        bst.preOrder();
//        System.out.println();
//        System.out.println(bst);
//        bst.preOrder();
//        System.out.println();
//        bst.preOrderNR();
//        System.out.println();
//        bst.postOrder();

//        bst.levelOrder();

    }



}
