package leetcode.tree;

class NumArray303 {

    private int[] tree;
    private int[] data;

    public NumArray303(int[] nums) {
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

        NumArray303 numArray = new NumArray303(test);
        System.out.println(numArray.sumRange(0, 2));
        System.out.println(numArray.sumRange(2, 5));
        System.out.println(numArray.sumRange(0, 5));

    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */