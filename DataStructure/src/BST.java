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
        BST bst = new BST();
        Integer[] test ={5,3,6,8,4,2};
        for (Integer integer : test) {
            bst.add(integer);
        }


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

        bst.levelOrder();

    }



}
