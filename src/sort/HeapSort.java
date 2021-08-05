package sort;

import DataStructure.heap.MaxHeap;
import common.ArrayGenerator;
import common.SortingHelper;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class HeapSort<E extends Comparable<E>> {


    public HeapSort(){

    }

    // 较为直观的堆排序
    public static <E extends Comparable<E>> void sort2(E[] data){
        MaxHeap<E> heap = new MaxHeap<>();
        for (E e : data) {
            heap.add(e);
        }
        for (int i = data.length-1; i >=0 ; i--) {
            data[i] = heap.extractMax();
        }
    }
    // 较为直观的堆排序(heapify+取最大值,相比较sort2改成了构造方法取heapify)
    public static <E extends Comparable<E>> void sort3(E[] data){
        MaxHeap<E> heap = new MaxHeap<>(data);

        for (int i = data.length-1; i >=0 ; i--) {
            data[i] = heap.extractMax();
        }
    }


    // 优化后的堆排序(使用heapify的方式,然后依次取最大元素依次末端放置并siftdown被破坏之后的堆
    public static <E extends Comparable<E>> void sort(E[] data){

        if(data.length<=1){
            return;
        }

        // 首先构建一个堆,从最后一个非叶子节点开始(lastIndex-1)/2 = (data.length-1 -1)/2;
        for (int i = (data.length-2)/2; i >=0 ; i--) {
            siftDown(data,i,data.length);
        }

        for (int i = data.length-1; i >=0 ; i--) {
            //将首未交换之后,最大值已至末尾,此时除去末尾部位需要重新siftDown ,所以size = i;
            swap(data,0,i);
            siftDown(data,0,i);
        }
    }

    private static <E extends Comparable<E>> void swap(E[] data, int a, int b) {
        E temp = data[a];
        data[a] = data[b];
        data[b] =temp;
    }

    private static <E extends Comparable<E>> void siftDown(E[] data, int k, int size) {

        while( 2 * k +1 <size){
            int j = 2 * k +1;
            if(j+1<size && data[j+1].compareTo(data[j])>0){
                j++;
            }
            if(data[j].compareTo(data[k])<0){
                break;
            }
            swap(data,j,k);
            k = j;
        }

    }


    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generatorRandomArray(n,n);

        Integer[] arr2 = Arrays.copyOf(arr,arr.length);
        Integer[] arr3 = Arrays.copyOf(arr,arr.length);
        Integer[] arr4 = Arrays.copyOf(arr,arr.length);
        Integer[] arr5 = Arrays.copyOf(arr,arr.length);
        Integer[] arr6 = Arrays.copyOf(arr,arr.length);


        SortingHelper.sortTest(QuickSort.class,"sort",arr);
        SortingHelper.sortTest(QuickSort.class,"sort3Ways",arr2);
        SortingHelper.sortTest(MergeSort.class,"sort4",arr3);
        SortingHelper.sortTest(HeapSort.class,"sort",arr4);
        SortingHelper.sortTest(HeapSort.class,"sort2",arr5);
        SortingHelper.sortTest(HeapSort.class,"sort3",arr6);
    }

}
