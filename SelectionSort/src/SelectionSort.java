import javax.sound.midi.Soundbank;
import java.util.Arrays;

public class SelectionSort {

    private SelectionSort(){}

    //需要多种数据排序,所以需要加泛型约束,比较型算法一般是需要类实现Comparable接口

    public static <E extends Comparable<E>> void sort(E[] arr,boolean asc ){


        for (int i =0;i<arr.length;i++){


            int minIndex = i;
            for(int j = i;j<arr.length;j++){
                if (asc){
                    if(arr[j].compareTo(arr[minIndex])<0){
                        minIndex = j;
                    }
                }else{
                    if(arr[j].compareTo(arr[minIndex])>0){
                        minIndex = j;
                    }
                }
            }
// 可以将数组交换的方法单独提炼出来,代码更容易阅读
//            int temp = arr[i];
//            arr[i]=arr[minIndex];
//            arr[minIndex]=temp;
            swap(arr,i,minIndex);
        }
    }

    private static <E> void swap(E[] arr, int i, int minIndex) {
        E temp = arr[i];
        arr[i] = arr[minIndex];
        arr[minIndex]=temp;
    }


    public static void main(String[] args) {
        Integer[] arr = {2,5,6,7,1,3,4};
        sort(arr,false);
        Arrays.stream(arr).forEach(System.out::println);
    }
}
