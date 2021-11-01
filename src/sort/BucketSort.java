package sort;


public class BucketSort {
    private BucketSort(){}

    public static void sort(Integer[] arr,int B){
        if(B<=1){
            throw new IllegalArgumentException("B must be greater than 1");
        }
        Integer[] temp = new Integer[arr.length];

        sort(arr,0,arr.length-1,B,temp);
    }

    private static void sort(Integer[] arr, int left, int right, int B, Integer[] temp) {
        if(left>=right){
            return;
        }
        int maxv = Integer.MIN_VALUE;
        int minv = Integer.MAX_VALUE;
        for (int i = left; i <=right ; i++) {
            maxv = Math.max(maxv,arr[i]);
            minv = Math.min(minv,arr[i]);
        }
        if(maxv == minv){
            return;
        }
        // 计算每个桶中元素最大数量(上取整)
        int d = (maxv-minv+1)/B + ((maxv-minv+1)%B>0?1:0);

        int[] cnt = new int[B];
        int[] index = new int[B+1];

        //统计(从区间起点开始,统计每个元素应该存在那个桶中)
        for (int i = left; i <=right ; i++) {
            cnt[(arr[i]-minv)/d]++;
        }

        //计算index
        for (int i = 0; i+1 <= B; i++) {
            index[i+1] = index[i] +cnt[i];
        }

        //填充
        for (int i = left; i <= right; i++) {
            int p = index[(arr[i]-minv)/d];
            temp[p+left] = arr[i];
            index[(arr[i]-minv)/d]++;
        }

        //搬运
        for (int i = left; i <= right; i++) {
            arr[i] = temp[i];
        }

        // 单独排一下第0个桶

        sort(arr,left,left+index[0]-1,B,temp);
        // 递归
        for (int i = 1; i+1 < index.length; i++) {
            sort(arr,left+index[i],left+index[i+1]-1,B,temp);
        }
    }

    public static void main(String[] args) {
        Integer[] ints = {1,2,3,5,6,3,2,7,10,100};

        BucketSort.sort(ints,4);
        System.out.println(ints);
    }
}
