package sort;

import common.ArrayGenerator;
import common.Pair;
import common.SortingHelper;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    private static Random random;
    private QuickSort(){}
    static{
        random = new Random();
    }

    // 双路快排
    public static  <E extends Comparable<E>> void  sort(E[] arr){
        quickSort(arr,0,arr.length-1);
    }

    //单路快排 自己优化时,再标定完之后对标定点左右部分判断元素个数小于等于16使用插入排序
    public static  <E extends Comparable<E>> void  sort2(E[] arr){
        quickSort2(arr,0,arr.length-1);
    }

    //单路快排 别人的优化,直接判断整个数组如果小于16就先插入排序否则就进行quick sort
    public static  <E extends Comparable<E>> void  sort3(E[] arr){
        quickSort3(arr,0,arr.length-1);
    }

    //单路快排 别人的优化,直接判断整个数组如果小于8就先插入排序否则进行quick sort
    public static  <E extends Comparable<E>> void  sort4(E[] arr){
        quickSort4(arr,0,arr.length-1);
    }

    // 单路快排
    public static  <E extends Comparable<E>> void  sortSingle(E[] arr){
        quickSort5(arr,0,arr.length-1);
    }
    public static  <E extends Comparable<E>> void  sort3Ways(E[] arr){
        quickSort3Ways(arr,0,arr.length-1);
    }

    private static <E extends Comparable<E>>  void quickSort(E[] arr, int l, int r) {
        if(r<=l){
            return;
        }
        int p = partitionDouble(arr,l,r);
        quickSort(arr,l,p-1);
        quickSort(arr,p+1,r);
    }
    private static <E extends Comparable<E>>  void quickSort2(E[] arr, int l, int r) {
        if(r<=l){
            return;
        }
        int p = partition(arr,l,r);
        if(p-l<=16){
            insertSort(arr,l,p-1);
        }else{
            quickSort2(arr,l,p-1);
        }
        if(r-p<=16){
            insertSort(arr,p+1,r);
        }else{
            quickSort2(arr,p+1,r);

        }
    }

    private static <E extends Comparable<E>>  void quickSort3(E[] arr, int l, int r) {
        if(r-l<=16){
            insertSort(arr,l,r);
            return;
        }
        int p = partition(arr,l,r);
        quickSort3(arr,l,p-1);
        quickSort3(arr,p+1,r);
    }
    private static <E extends Comparable<E>>  void quickSort4(E[] arr, int l, int r) {
        if(r-l<=8){
            insertSort(arr,l,r);
            return;
        }
        int p = partition(arr,l,r);
        quickSort4(arr,l,p-1);
        quickSort4(arr,p+1,r);
    }

    private static <E extends Comparable<E>>  void quickSort5(E[] arr, int l, int r) {
        if(l>=r){
            return;
        }
        int p = partition(arr,l,r);
        quickSort5(arr,l,p-1);
        quickSort5(arr,p+1,r);
    }

    // 三路快排
    public static <E extends Comparable<E>>  void quickSort3Ways(E[] arr, int l, int r) {
        if(l>=r){
            return;
        }
         Pair<Integer,Integer> keyIsLeftValueIsRight = partition3Ways(arr,l,r);
        quickSort3Ways(arr,l,keyIsLeftValueIsRight.getKey());
        quickSort3Ways(arr,keyIsLeftValueIsRight.getValue(),r);
    }

    private static <E extends Comparable<E>> Pair<Integer, Integer> partition3Ways(E[] arr, int l, int r) {

        int p = l+random.nextInt(r-l+1);
        E part = arr[p];
        swap(arr,p,l);
        // 三路快的思路就是将数组分成  [l+1,lt]<part,[lt+1,i-1]=part,[gt,r]>part
        // 由此可见,我们需要初始的值为 lt = l,i=l+1,gt=r+1;

        int lt =l,i=l+1,gt = r+1;

        // 循环终止条件就是i越界gt了,即i>=gt
        while(i<gt){
            // 三种情况互斥 a:arr[i]<part,b:arr[i]>part,c:arr[i]=part;
            //a
            if(arr[i].compareTo(part)<0){
                //如果当前元素小于标定点,说明当前元素应该滚到[l+1,lt]去,所以lt扩容,完成遍历下一个元素
                lt++;
                swap(arr,lt,i);
                i++;
            // b
            }else if(arr[i].compareTo(part)>0){
                //如果当前元素大于标定点,说明当前元素应该滚到[gt,r]去,所以gt--以便扩容,完成之后i不变,因为换来的是一个没有遍历过的元素
                gt--;
                swap(arr,gt,i);
            // c
            }else{
                //除此之外当前元素与标定点相等,那么当前元素应该滚到[lt+1,i-1],只需要i++;就好
                i++;
            }

        }
        //当循环完成之后,我们要把小于part区间里最后一个值和标定点做交换
        swap(arr,l,lt);
        // 交换完成之后,我们的三区间就变成了
        // [l,lt-1],[lt,gt-1],[gt,r]
        return new Pair<>(lt-1,gt);
    }


    private  static <E extends Comparable<E>> int partitionZZL(E[] arr, int l, int r) {
        int p = l + random.nextInt(r-l+1);
        swap(arr,p,l);
        E pivot = arr[l];
        while (l < r) {
            while (l < r && arr[r] .compareTo(pivot)>= 0) {
                r--;
            }
            arr[l] = arr[r];
            while (l < r && arr[l].compareTo(pivot) <= 0) {
                l++;
            }
            arr[r] = arr[l];
        }
        arr[l] = pivot;
        return l;
    }

    //双路快排的partition

    private  static <E extends Comparable<E>> int partitionDouble(E[] arr, int l, int r) {
        // 双路快排的核心思想是partition中,从两端开始筛选,左端大于等于第一个元素,执行右端,右端小于等于第一个元素,与左端交换
        // 即将数组划分位三部分 l=part , [l,i-1]<=part ,[j+1,r]>=part;
        // 不变的逻辑是将随机选取一个一个元素作为标定点,与第一个点做 交换。
        int p = l+random.nextInt(r-l+1);
        swap(arr,l,p);
        E part = arr[l];
        int i=l+1,j=r;
        while(true){
            // 当i开始的循环还能继续循环时(即仍有未扫描的元素),将当前元素与part比较
            // 尊重[l,i-1]<=part,[j+1,r]>=part的逻辑
            while (i<=j && arr[i].compareTo(part)<0){
                // 因为大于等于part的都要扔到[j+1,r]的区间,所以小于part的就继续循环
                i++;
            }
            while (j>=i && arr[j].compareTo(part)>0){
                // 因为小于等于part的都要扔到[l,i-1]的区间,所以大于part的就继续循环
                j--;
            }
            // 如果 j<i 或者j=i了,说明已经循环完所有元素了,需要break
            if(j<=i){
                break;
            }
            // 没有循环完,则需要将两次循环选中的元素交换,然后使循环继续
            swap(arr,i,j);
            //不能重复上次的循环
            i++;
            j--;
        }
        swap(arr,j,l);
        return j;
    }

    public  static <E extends Comparable<E>> int partition(E[] arr, int l, int r) {
        // 选择第一个元素为基准点在测试用例为有序数组时会导致时间复杂度变成O(n²),
        // 因为每次标定后,标定点都是最小元素,即左边区间为空,右边为n-1,n-2...n-n
        // 递归深度也将变成 O(n),即n次递归调用,n超过一定规模直接StackOverflow
        // 所以我们需要随机选择一个元素作为标定点,在[l,r]这个区间随机选择一个元素,与l做交换
        // 因为没有现成的api可以生成[l,r]的方法,但是有[0,n)这样的api,所以我们需要l+[0,r-l]即为[l,r]
        int p = l + random.nextInt(r-l+1);

        // 假设每次选中间,理论上时让左右区间更加平衡,也减少递归深度,但是我们可以整出一个测试用例让每次的中间值都是最小值
        //  当我们设计了(ArrayGenerator.generatorArrayPartition)
        //这样的测试用例就会使quick sort退化至O(n²)的时间复杂度 如下:
        //QuickSort.sort sort 100000 data  use 4.943561 s
        //MergeSort.sort sort 100000 data  use 0.034707 s

        //int p = l+(r-l)/2;

        swap(arr,l,p);
        // 以第一个元素为基准点
        // 那么接下来可以将数组分成三部分 即 part这个基准点 + 小于part的部分 + 大于等part的部分
        // 即 [l] + [l+1...j]<part + [j+1...r]>=part
        E part = arr[l];
        int j =l;
        for (int i = l+1; i <=r; i++) {
            if(arr[i].compareTo(part)<0){
                j++;
                swap(arr,i,j);
            }
        }
        swap(arr,j,l);
        return j;
    }

    private static <E extends Comparable<E>> void swap(E[] arr, int x, int y) {
            E temp = arr[x];
            arr[x] = arr[y];
            arr[y] = temp;
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

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {


        Integer[] arrs = ArrayGenerator.generatorRandomArray(100000 ,100000 );
        Integer[] arrs2 = Arrays.copyOf(arrs, arrs.length);
        Integer[] arrs3 = Arrays.copyOf(arrs, arrs.length);

        Integer[] test = ArrayGenerator.generatorArraySame(1000000);
        Integer[] test2 = Arrays.copyOf(test, test.length);
        Integer[] test3 = Arrays.copyOf(test, test.length);

        Integer[] orders = ArrayGenerator.generatorOrderArray(10000);
        Integer[] orders2 =  Arrays.copyOf(orders, orders.length);
        Integer[] orders3 =  Arrays.copyOf(orders, orders.length);

//
//        Integer[] arrs3 = Arrays.copyOf(arrs, arrs.length);
//        Integer[] arrs4 = Arrays.copyOf(arrs, arrs.length);
//        Integer[] arrs5 = Arrays.copyOf(arrs, arrs.length);
//        Integer[] arrs6 = Arrays.copyOf(arrs, arrs.length);
//        Integer[] arrs7 = Arrays.copyOf(arrs, arrs.length);
//        SortingHelper.sortTest(QuickSort.class,"sort",arrs);  //双路 100000 data  use 0.040727 s
//        SortingHelper.sortTest(QuickSort.class,"sort2",arrs2); // 单路分情况使用插入排序 sort 100000 data  use 0.042009 s
//        SortingHelper.sortTest(QuickSort.class,"sort3",arrs3); // 单路小于16时插入 sort 100000 data  use 0.038713 s
//        SortingHelper.sortTest(QuickSort.class,"sort4",arrs4); //单路小于8时插入 sort 100000 data  use 0.020036 s
//        SortingHelper.sortTest(QuickSort.class,"sortSingle",arrs5); // 原始单路sortSingle sort 100000 data  use 0.021504 s
//        SortingHelper.sortTest(MergeSort.class,"sort5",arrs6); // sort 100000 data  use 0.050350 s
//        SortingHelper.sortTest(MergeSort.class,"sort2",arrs7); // sort 100000 data  use 0.107033 s



        SortingHelper.sortTest(QuickSort.class, "sort",arrs);
        SortingHelper.sortTest(QuickSort.class,"sortSingle",arrs2);
        SortingHelper.sortTest(QuickSort.class,"sort3Ways",arrs3);

        SortingHelper.sortTest(QuickSort.class, "sort",test2);
//        SortingHelper.sortTest(QuickSort.class,"sortSingle",test);
        SortingHelper.sortTest(QuickSort.class,"sort3Ways",test3);

        SortingHelper.sortTest(QuickSort.class, "sort",orders);
        SortingHelper.sortTest(QuickSort.class,"sortSingle",orders2);
        SortingHelper.sortTest(QuickSort.class,"sort3Ways",orders3);



    }
}
