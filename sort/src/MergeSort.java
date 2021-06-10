import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class MergeSort {

    private MergeSort(){}




    public static <E extends Comparable<E>> void sort(E[] arr){

        // mergeSort(arr,0,arr.length-1);
        sort(arr,0,arr.length-1);

    }

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
           Integer[] x1= Arrays.copyOf(arr,arr.length);
           Integer[] x2 = Arrays.copyOf(arr,arr.length);

//           Integer[] arr = {1,3,5,2,4,6};
            SortingHelper.sortTest("MergeSort",arr);
            SortingHelper.sortTest(MergeSort.class,"sort2",x1);
            SortingHelper.sortTest(MergeSort.class,"sort3",x2);
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

}
