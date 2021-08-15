package leetcode.tree;


class NumArray307 {

    private int[] tree;
    private int[] data;

    public NumArray307(int[] nums) {
        data = new int[nums.length];
        tree = new int[4*nums.length];
        for (int i = 0; i < nums.length; i++) {
            data[i] = nums[i];
        }
        buildTree(0,0,nums.length-1);
    }

    private void buildTree(int index, int l, int r) {

        if(l==r){
            tree[index] = data[l];
            return;
        }
        int leftChild = index*2+1;
        int rightChild = leftChild+1;

        int middle = l+(r-l)/2;

        buildTree(leftChild,l,middle);
        buildTree(rightChild,middle+1,r);
        tree[index] = tree[leftChild]+tree[rightChild];

    }

    public void update(int index, int val) {
        if(index<0||index>=data.length){
            throw new IllegalArgumentException("error index");
        }

        data[index] = val;
        setTree(0,0,data.length-1,index,val);
    }

    private void setTree(int treeIndex, int l, int r, int dataIndex, int val) {
        if(l==r){
            tree[treeIndex] = data[dataIndex];
            return;
        }
        int leftChild = 2*treeIndex+1;
        int rightChild = leftChild+1;
        int middle = l+(r-l)/2;

        if(dataIndex>=middle+1){
            setTree(rightChild,middle+1,r,dataIndex,val);
        }else{
            setTree(leftChild,l,middle,dataIndex,val);
        }
        tree[treeIndex]= tree[leftChild]+tree[rightChild];
    }

    public int sumRange(int left, int right) {
        return query(0,0,data.length-1,left,right);
    }

    private int query(int index, int l, int r, int queryL, int queryR) {
        if(l<0 || r<0 || r>=data.length||l>=data.length || l>r){
            throw new IllegalArgumentException("index is Illegal.");
        }

        if(l == queryL && r == queryR){
            return tree[index];
        }
        int middle = l+(r-l)/2;
        if(queryL>=middle+1){
            return query(index*2+2,middle+1,r,queryL,queryR);
        }
        if(queryR<=middle){
            return query(index*2+1,l,middle,queryL,queryR);
        }

        int left = query(index*2+1,l,middle,queryL,middle);
        int right = query(index*2+2,middle+1,r,middle+1,queryR);
        return left+right;

    }

    public static void main(String[] args) {
        int[] test = {-2, 0, 3, -5, 2, -1};

        NumArray307 numArray = new NumArray307(test);
        System.out.println(numArray.sumRange(0, 2));
        System.out.println(numArray.sumRange(2, 5));
        System.out.println(numArray.sumRange(0, 5));
        numArray.update(1,2);
        System.out.println(numArray.sumRange(0, 2));
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */
