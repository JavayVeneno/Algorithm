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




    public static void main(String[] args) {
        Integer[] test = {-1,0,3,5,9,12};
        int search = search2(test,0);
        System.out.println(search);


    }
}
