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

    public static void main(String[] args) {
        Integer[] test = {0,1,2,3,5,6,7,8};
        int search = search(test, 8);
        System.out.println(search);
    }
}
