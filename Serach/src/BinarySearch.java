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

    // 二分搜索的变种:ceil ,查找某个值的最大索引,如果不存在则取大于该值的最小值索引
    // 类似 ceil(5.0) = 5 ceil(5.5) = 6;

    public static <E extends Comparable<E>> int binarySearchCeil(E[] arr,E target){
        int index = binarySearchUpper(arr,target);
        if(index>0 && arr[index-1].compareTo(target)==0){
            return index-1;
        }
        return index==arr.length?-1:index;
    }

    // 二分搜索的变种:lowerCeil,查找某个值的最小索引,如果不存在则取大于该值的最小索引
    public static <E extends Comparable<E>> int binarySearchLowerCeil(E[] arr,E target){
        int index = binarySearchLowerCeil(arr,0,arr.length,target);
        return index==arr.length?-1:index;
    }

    private static <E extends Comparable<E>> int binarySearchLowerCeil(E[] arr, int l, int r, E target) {

        // [l,p]<target  [p+1,r]>=target;
        while(l<r){
            int p =l+(r-l)/2;
            if(arr[p].compareTo(target)>=0){
                r = p;
            }else {
                l = p+1;
            }
        }
        return l;
    }

    // 二分搜索的变种:lower,查到小于某个值的的最大值
    // 因为target的值非常大的话,我们至少有一个r是满足的,反之target非常小,我们可能没有哟个值满足,所以l取到-1[l-1,r]

    public static <E extends Comparable<E>> int binarySearchLower(E[] arr,E target){

        int index = binarySearchLower(arr,-1,arr.length-1,target);
        return index ;
    }

    private static <E extends Comparable<E>> int binarySearchLower(E[] arr, int l, int r, E target) {


        // 循环的条件就是l < r;
        while (l<r){
            int p = l+(r-l+1)/2;
            if(arr[p].compareTo(target)<0){
                l = p;
            }else{
                // arr[p]大于等target,可以放心的丢掉 即r = p -1;
                r = p-1;
            }
        }
        return l;
    }


    public static void main(String[] args) {
        Integer[] test = {-1};
        int search = search2(test,0);

        System.out.println(search);
        int i = binarySearchUpper(test, 12);
        int j = binarySearchCeil(test, 3);
        System.out.println(i);
        System.out.println(j);
        int index = binarySearchLowerCeil(test, 3);
        System.out.println(index);

        int lower = binarySearchLower(test, -1);
        System.out.println(lower);


    }
}
