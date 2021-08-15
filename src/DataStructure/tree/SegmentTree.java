package DataStructure.tree;

import common.Merger;

public class SegmentTree<E> {

    private E[] tree;
    private E[] data;
    private Merger<E> merger;
    public SegmentTree(E[] arr, Merger<E> merger){
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        tree = (E[]) new Object[4*arr.length];
        // 创建线段树,可以从根节点递归,根节点所表示的是全部[0,data.length-1]的某种操作
        // 比如0~arr.length-1之和,期间最值等等,所以这一块交给用户去自定义,参考PriorityQueue用户需要自己实现比较器

        buildSegmentTree(0,0,data.length-1);
    }


    private void buildSegmentTree(int index, int l, int r) {
        // 递归终止条件
        if(l==r){
            // 只有一个元素的时候当然是终止条件
            tree[index]=data[l];
            return;
        }
        // 当l真的小于r,时候我们需要去构建左子树与右子树
        // 构建左右子树需要找到左右子的索引,以及左右子所表示的区间
        int left =leftChild(index);
        int right = rightChild(index);
        // 求左右子区间
        int middle = l+(r-l)/2;
        // 分别构建左右子
        buildSegmentTree(left,l,middle);
        buildSegmentTree(right,middle+1,r);
        // 当左右子树都构建完了,需要交给用户来自定义实现对区间的处理或者叫融合(相加,求最值,等等)
        tree[index] = merger.merge(tree[left],tree[right]);
    }

    public E get(int index){

        if(index<0 ||  index>=data.length){
            throw new IllegalArgumentException("index is Illegal.");
        }
        return data[index];
    }

    public int getSize(){
        return data.length;
    }

    public int leftChild(int k){
        return 2*k+1;
    }
    public int rightChild(int k){
        return 2*k+2;
    }
    public E query(int queryL, int queryR) {

        if(queryL<0 || queryL>=data.length || queryR<0 ||queryR>=data.length || queryL>queryR){
            throw new IllegalArgumentException("index is Illegal.");
        }

        // 表示在根节点,即[0,length-1]之间,查询[queryL,queryR]的值
        return query(0,0,data.length-1,queryL,queryR);
    }

    private E query(int index, int l, int r, int queryL, int queryR) {

        // 依然是先考虑递归到底的情况
        if(l==queryL && r == queryR){
            return tree[index];
        }
        // 除此之外,LR在l-r之间,即在index的左右子中查询
        // 左子 left 其管辖区间为[l,middle]
        int left = leftChild(index);
        // 右子 tight 其管辖区间为[middle+1,r]
        int right = rightChild(index);
        int middle = l+(r-l)/2;
        // 如果用户的查询起点大于 middle,说明该去右子查询
        if(queryL>=middle+1){
            return query(right,middle+1,r,queryL,queryR);
        }
        // 如果用户的查询终点小等于 middle,说明该去左子查询
        if(queryR<=middle){
            return query(left,l,middle,queryL,queryR);
        }

        // 还有一种情况就是左子与右子都存在一部分区间
        // 我们则将用户的区间拆成两部分,分别去左右子区间查询,再合并
        E leftResult = query(left,l,middle,queryL,middle);
        E rightResult = query(right,middle+1,r,middle+1,queryR);
        return merger.merge(leftResult,rightResult);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < tree.length; i++) {
            if(tree[i]!=null){
                sb.append(tree[i]);
            }else{
                sb.append("null");
            }
            if(i!=tree.length-1){
                sb.append(',');
            }
        }
        sb.append(']');
        return sb.toString();
    }


    public static void main(String[] args) {
        Integer[] test = {1,2,3,4,1,2,3};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(test, Math::addExact);
        System.out.println(segmentTree.query(2, 4));
    }


}
