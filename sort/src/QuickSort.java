import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class QuickSort {

    private QuickSort(){}

    public static  <E extends Comparable<E>> void  sort(E[] arr){
        quickSort(arr,0,arr.length-1);
    }

    private static <E extends Comparable<E>>  void quickSort(E[] arr, int l, int r) {
        if(r<=l){
            return;
        }
        int p = partition(arr,l,r);
        quickSort(arr,l,p-1);
        quickSort(arr,p+1,r);
    }

    public  static <E extends Comparable<E>> int partition(E[] arr, int l, int r) {

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


    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {


        Integer[] arrs = ArrayGenerator.generatorRandomArray(1000000,1000000);
        Integer[] arrs2 = Arrays.copyOf(arrs, arrs.length);
        Integer[] arrs3 = Arrays.copyOf(arrs, arrs.length);
        Integer[] arrs4 = Arrays.copyOf(arrs, arrs.length);
        Integer[] arrs5 = Arrays.copyOf(arrs, arrs.length);
        Integer[] arrs6 = Arrays.copyOf(arrs, arrs.length);
        Integer[] arrs7 = Arrays.copyOf(arrs, arrs.length);
        SortingHelper.sortTest(QuickSort.class,"sort",arrs);
        SortingHelper.sortTest(MergeSort.class,"sort",arrs2);
        SortingHelper.sortTest(MergeSort.class,"sort6",arrs3);
        SortingHelper.sortTest(MergeSort.class,"sort3",arrs4);
        SortingHelper.sortTest(MergeSort.class,"sort4",arrs5);
        SortingHelper.sortTest(MergeSort.class,"sort5",arrs6);
        SortingHelper.sortTest(MergeSort.class,"sort2",arrs7);
    }
}
