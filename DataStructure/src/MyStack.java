import java.util.ArrayDeque;

class MyStack<E> {

    private ArrayDeque<E> a;
    private ArrayDeque<E> b;

    /** Initialize your data structure here. */
    public MyStack() {
        this(10);
    }
    public MyStack(int capacity) {
        a = new ArrayDeque<>(10);
        b = new ArrayDeque<>(10);
    }

    /** Push element x onto stack. */
    public void push(E x) {

        //TODO 该方法时间复杂度为O(n²)需要优化

        if(a.isEmpty()){
            addLast(x, a, b);
        }else {
            addLast(x, b, a);
        }


    }

    private void addLast(E x, ArrayDeque<E> add, ArrayDeque<E> move) {
        add.addLast(x);
        int sizeB = move.size();
        for (int i = 0; i < sizeB ; i++) {
            add.addLast(move.removeFirst());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public E pop() {
        if(a.isEmpty() && b.isEmpty()){
            throw new RuntimeException("can not pop from empty stack");
        }
        if(a.isEmpty()){
            return b.removeFirst();
        }
        return a.removeFirst();
    }

    /** Get the top element. */
    public E top() {
        if(a.isEmpty() && b.isEmpty()){
            throw new RuntimeException("can not pop from empty stack");
        }
        if(a.isEmpty()){
            return b.getFirst();
        }
        return a.getFirst();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return a.isEmpty() && b.isEmpty();
    }

    // leetcode 225题,但是比较奇怪的是我的添加的复杂度应该是O(n²)的
    //push 10000 条 data  use 0.183942 s
    //push 100000 条 data  use 21.196866 s 通过这个简单测试验证了,双队列实现栈时,每次入队,都需要把另一队数据重排的方法,但是其复杂度不可取
    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();
//        System.out.println(stack.empty());
        long start = System.nanoTime();
        int[] test = {10_000,100_000};
        for (int n : test) {
            for (int i = 0; i <n; i++) {
                stack.push(i);

            }
            long end = System.nanoTime();



//        System.out.println(stack.top());
//        for (int i = 0; i <5 ; i++) {
//            System.out.println(stack.pop());
//
//        }

            double use = (end-start)/1_000_000_000.0;
            System.out.printf("push %d 条 data  use %f s %n",n,use);
        }


    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */

