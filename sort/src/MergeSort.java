import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class MergeSort {

    private MergeSort(){}




    public static <E extends Comparable<E>> void sort(E[] arr){

        // mergeSort(arr,0,arr.length-1);
        sort(arr,0,arr.length-1);

    }


    public static <E extends Comparable<E>> void sort4(E[] arr){

        E[] base = Arrays.copyOf(arr,arr.length);
        sort4(arr,0,arr.length-1,base);


    }
    private static <E extends Comparable<E>> void sort4(E[] arr,int start,int end,E[] base){

        if(end<=start){
            return;
        }

        int middle = start+(end-start)/2;
        sort4(arr,start,middle,base);
        sort4(arr,middle+1,end,base);
        if(arr[middle].compareTo(arr[middle+1])>0){
            merge4(arr,start,middle,end,base);
        }

    }

    private static <E extends Comparable<E>> void merge4(E[] arr, int start, int middle, int end,E[] base) {

       System.arraycopy(arr,start,base,start,end-start+1);
        // 使用两个等长数组后,数组比较的起始索引不再有偏移量,所以相应地也减少了索引偏移计算
        // 又减少了逻辑复杂性一举两得
        int left = start, right = middle + 1;
        for (int i = start; i <= end; i++) {

            if (left > middle) {
                arr[i] = base[right];
                right++;
            } else if (right > end) {
                arr[i] = base[left];
                left++;
            } else if (base[left].compareTo(base[right]) <= 0) {
                arr[i] = base[left];
                left++;
            } else {
                arr[i] = base[right];
                right++;
            }
        }
    }






    // sort是仍然有一个可以优化的地方,即每次merge都要取开辟一个base数组的空间,
    // 不过已经对sort2有一个优化就是左右有序区间,左大不大于右小即可无需merge

    private static <E extends Comparable<E>> void sort(E[] arr,int start,int end){

        if(end<=start){
            return;
        }

        int middle = (start+end)/2;
        sort(arr,start,middle);
        sort(arr,middle+1,end);
        if(arr[middle].compareTo(arr[middle+1])>0){
            merge(arr,start,middle,end);
        }

    }


    public static <E extends Comparable<E>> void sort2(E[] arr){

        // mergeSort(arr,0,arr.length-1);
        sort2(arr,0,arr.length-1);

    }

    // sort2最基础的实现
    private static <E extends Comparable<E>> void sort2(E[] arr,int start,int end){

        if(end<=start){
            return;
        }

        int middle = (start+end)/2;
        sort2(arr,start,middle);
        sort2(arr,middle+1,end);
        merge(arr,start,middle,end);
    }

    public static <E extends Comparable<E>> void sort3(E[] arr){

        // mergeSort(arr,0,arr.length-1);
        sort3(arr,0,arr.length-1);

    }
    // sort3是在sort中优化了最某个程度下使用insertionSort来组合排序
    private static <E extends Comparable<E>> void sort3(E[] arr,int start,int end){

        if(end-start<=15){
            insertSort(arr,start,end);
            return;
        }

        int middle = (start+end)/2;
        sort3(arr,start,middle);
        sort3(arr,middle+1,end);
        if(arr[middle].compareTo(arr[middle+1])>0){
            merge(arr,start,middle,end);
        }
    }

    private static  <E extends Comparable<E>> void  insertSort(E[] arr, int start, int end) {

        for (int i = start; i <= end; i++) {

            E tmp = arr[i];
            int j;
            for (j = i ;j -1 >= start && tmp.compareTo(arr[j-1])<0 ; j--) {
                arr[j] = arr[j-1];
            }
            arr[j] = tmp;
        }

    }


    /**
     *  搞清楚需要合并的是那部分
     *  需要合并的是数组[start,end]这个区间,所以for循环应该是从start开始到end结束,左闭右闭
     *  merger其实是将左边排好的数组区间和右边排好的区间合并即 [start,middle],[middle+1,end]
     *  令left为[start,middle]区间的起始值,超出middle即该区间完全被选走了,[middle+1,end]同理
     *  重点:我们base里的第一个元素,与arr有一个偏移,偏移量即start
     *  图解: 例如 arr     a1,a2,a3...start...middle,middle+1...end
     *            base               start...middle,middle+1...end
     *            arr[start] == base[left-start];
     */
    private static <E extends Comparable<E>> void merge(E[] arr, int start, int middle, int end) {

        // 这个方法是左闭右开,所以记得复制的时候加1
        E[] base = Arrays.copyOfRange(arr,start,end+1);
        int left=start,right=middle+1;
        for (int i = start; i <=end ; i++) {

            if(left>middle){
                arr[i] = base[right-start];
                right++;
            }else if(right>end){
                arr[i] = base[left-start];
                left++;
            }else if(base[left-start].compareTo(base[right-start])<=0){
                arr[i] = base[left-start];
                left++;
            }else{
                arr[i] = base[right-start];
                right++;
            }
        }
    }

    // 第一遍实现
//    private static  <E extends Comparable<E>>  void mergeSort(E[] arr, int l, int r) {
//        if(r<=l){
//            return;
//        }
//        int middle = (l+r)/2;
//        // 左边到中间排序好
//        mergeSort(arr,l,middle);
//        // 中间到右边排序好
//        mergeSort(arr,middle+1,r);
//        // 合并数组中两个有序部分
//        mergeOrderedArray(arr,l,middle,r);
//
//    }
//    // 第一遍实现
//    private static  <E extends Comparable<E>>  void mergeOrderedArray(E[] arr, int l, int middle, int r) {
//            E[] base = Arrays.copyOfRange(arr,l,r+1);
//            int x = l;
//            int y = middle+1;
//
//            for (int i = l;i<=r;i++){
//
//                if(x>middle){
//                    arr[i] = base[y-l];
//                    y++;
//                }else if(y>r){
//                    arr[i] = base[x-l];
//                    x++;
//                }else if(base[x-l].compareTo(base[y-l])<=0){
//                    arr[i] = base[x-l];
//                    x++;
//                }else {
//                    arr[i] = base[y-l];
//                    y++;
//                }
//            }
//    }



    private static <E extends Comparable<E>> E compare(E l, E r) {

        if(l.compareTo(r)<0){
            return l;
        }else{
            return r;
        }
    }

    // 测试了MergerSort的时间复杂度为O(nlogn),
    // 但是对于一个有序数组或者说需要合并的两个有序区间右边最小大于左边最大,
    // 是否没有必要在循环比较每个元素了
    // 我们尝试用sort来看一下优化效果

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        int[] dataPool ={10,100,1000,500000,5000000};

        for (int n : dataPool) {
           Integer[] arr = ArrayGenerator.generatorRandomArray(n, n);
//           Integer[] arr = ArrayGenerator.generatorOrderArray(n);
           Integer[] x = Arrays.copyOf(arr,arr.length);
           Integer[] x1= Arrays.copyOf(x,x.length);
           Integer[] x2 = Arrays.copyOf(x1,x1.length);
           Integer[] x3 = Arrays.copyOf(x2,x2.length);
           Integer[] x4 = Arrays.copyOf(x2,x2.length);

//           Integer[] arr = {1,3,5,2,4,6};
            SortingHelper.sortTest("MergeSort",arr);
            SortingHelper.sortTest(MergeSort.class,"sort2",x2);
            SortingHelper.sortTest(MergeSort.class,"sort3",x1);
            SortingHelper.sortTest(MergeSort.class,"sort4",x3);
            SortingHelper.sortTest(MergeSort.class,"sort7",x4);
//            SortingHelper.sortTest("SelectionSort",x2);


            long start = System.nanoTime();
            Arrays.sort(x);
            long end = System.nanoTime();
            double use = (end-start)/1_000_000_000.0;
            System.out.printf("%s.sort %d data  use %f s %n","Arrays.sort ",arr.length,use);
//            mergeLOrderedArray(arr,0,2,5);
//            System.out.println(arr);
        }
    }



    public static <E extends Comparable<E>> void sort5(E[] arr){

        E[] base = Arrays.copyOf(arr,arr.length);
        sort5(arr,base);


    }
    private static <E extends Comparable<E>> void sort5(E[] arr,E[] base){


        // 先每次选中一个数组,再每次选中两个数组,再选中四个数组,直到选中的数字大于数组长度
        // 第一次是每个数组比较后进行merge,然后再每两个数组进行比较,再四个数组进行比较,但是最后可能会剩下一堆不足2 n次方,但他们已经有序了

        int n = arr.length;
        // sz即是每次操作的数组的长度,一开始是每个数组,其中只有一个元素,处理完一次就是翻倍的长度
        for (int sz = 1; sz < n; sz+=sz) {
            //从0开始处理到第一个sz,继续处理到第2个sz,即处理两个数组
            // [i,i+size-1],[i+size,i+size+size-1]
            for (int i = 0;i+sz<n; i+=sz+sz) {
                if(arr[i+sz-1].compareTo(arr[i+sz])>0){
                    merge4(arr,i,i+sz-1,Math.min(i+sz+sz-1,n-1),base);

                }
            }
        }

    }


    public static <E extends Comparable<E>> void sort6(E[] arr){

        E[] base = Arrays.copyOf(arr,arr.length);
        sort6(arr,base);


    }
    private static <E extends Comparable<E>> void sort6(E[] arr,E[] base){
        int n = arr.length;
        // 遍历一遍，对所有每16个元素使用插入排序 即 arr[k, i=k + 15] 的区间，使用插入排序法
        for (int k = 0; k < n; k+=16) {
            insertSort(arr,k,Math.min(n-1,k+15));
        }
        //每组从16开始,连续两组,依次翻倍
        for (int sz = 16; sz < n; sz+=sz) {

            // [i,i+size-1],[i+size,i+size+size-1]
            for (int i = 0;i+sz<n; i+=sz+sz) {
                if(arr[i+sz-1].compareTo(arr[i+sz])>0){
                    merge4(arr,i,i+sz-1,Math.min(i+sz+sz-1,n-1),base);

                }
            }
        }

    }

    // 使用左闭右开的参数来merger and sort
    public static <E extends Comparable<E>> void sort7(E[] arr){

        E[] temp = Arrays.copyOf(arr,arr.length);

        sort7(arr,0,arr.length,temp);
    }

    private static <E extends Comparable<E>> void sort7(E[] arr, int l, int r, E[] temp) {
        if(r-l==1){
            return ;
        }
        int middle = l+(r-l)/2;
        sort7(arr,l,middle,temp);
        sort7(arr,middle,r,temp);
        if(arr[middle-1].compareTo(arr[middle])>0){
            merger7(arr,l,middle,r,temp);
        }


    }

    private static <E extends Comparable<E>> void merger7(E[] arr, int l, int m, int r, E[] temp) {
        System.arraycopy(arr,l,temp,l,r-l);
        //[l,m),[m,r);
        int left = l,right = m;
        for (int i = l ; i <r ; i++) {
            if(left>=m){
                //左边越界
                arr[i] = temp[right];
                right++;
            }else if(right>=r){
                // 右边越界
                arr[i] = temp[left];
                left++;
            }else if(temp[left].compareTo(temp[right])<=0){
                arr[i] = temp[left];
                left++;
            }else{
                arr[i] = temp[right];
                right++;
            }
        }

    }


}
