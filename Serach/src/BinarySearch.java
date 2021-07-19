import sun.plugin.com.event.COMEventHandler;

public class BinarySearch {
    private BinarySearch(){}

    public static <E extends Comparable<E>> int search(E[] data,E target){

        return search(data,0,data.length-1,target);
    }

    private static <E extends Comparable<E>> int search(E[] data, int l, int r, E target) {
        if(r<l){
            return -1;
        }
        int  middle = l+(r-l)/2;
        if(data[middle].compareTo(target)==0){
            return middle;
        }else if(data[middle].compareTo(target)<0){
            return search(data,middle+1,r,target);
        }else{
            return search(data,l,middle-1,target);
        }
    }
    public static <E extends Comparable<E>> int search2(E[] data,E target){
        int l = 0, r = data.length-1;

        // 循环不变量是 [l,r]区间仍然有值
        while(l<=r){
            int p  = l+(r-l)/2;
            if(data[p].compareTo(target)==0){
                return p;
            }else if(data[p].compareTo(target)<0){
                l = p+1;
            }else{
                r = p-1;
            }
        }
        return -1;
    }

    // 二分搜索的变种:upper,查找大于某值的最小值
    // [l,m]<=value [m,r]>value 按此逻辑进行二分,那么最终只有lr值,[l,r]确定一个值

    public static <E extends Comparable<E>> int binarySearchUpper(E[] arr,E target){
        int i = binarySearchUpper(arr, 0, arr.length, target);
        return i==arr.length?-1:i;
    }

    private static <E extends Comparable<E>> int binarySearchUpper(E[] arr, int l, int r, E target) {


        while(l<r){
            int middle = l+(r-l)/2;
            // 假如中间值5,比4大
            if(arr[middle].compareTo(target)>0){
                // 说明我这个r的索引至少是middle 5
                r=middle;
            }
            if(arr[middle].compareTo(target)<=0){
               //反之我左边界最大值也只能等于middle值
                // 所以需要排掉middle这个等于的值
                l = middle+1;
            }

        }
        return r;
    }


    public static void main(String[] args) {
        Integer[] test = {-1,0,0,3,3,5,9,12};
        int search = search2(test,0);


        System.out.println(search);
        int i = binarySearchUpper(test, 2);
        System.out.println(test[i]);

    }
}
