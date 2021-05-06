public class Array {

    // 数组底层实现存储

    private int[] data;

    // 已存放数据的个数,同时也表示数组第一个未存放数据的位置的索引

    private int size;

    // 指定空间的构造方法

    public Array(int capacity){
        data = new int[capacity];
        size = 0;
    }

    // 无参构造方法 默认capacity为10

    public Array(){
        this(10);
    }

    // 获取当前数组已存放的个数

    public int getSize(){
        return this.size;
    }

    // 获取当前数组的空间

    public int getCapacity(){
        return data.length;
    }

    // 向数组头部添加元数

    public void addFirst(int e){

        add(0,e);
    }

    public void set(int index, int e) {
        if(index<0 || index>=size){
            throw new IllegalArgumentException("set failed . index require >=0 and < data size ");
        }
        data[index] =e;
    }

    public int get(int index) {
        if(index<0 || index>=size){
            throw new IllegalArgumentException("get failed . index require >=0 and < data size ");
        }
        return data[index];
    }

    // 向数组末尾添加元素

    public void addLast(int e){
        //可以服用add的逻辑
//        if(size == data.length){
//            throw new IllegalArgumentException("addLast failed , this data is full . ");
//        }
//        data[size]= e;
//        size++;
        add(size,e);
    }

    // 向数组指定位置添加元素,并且其余元素向后移动

    public void add(int index,int e){
        if(size == data.length){
            throw new IllegalArgumentException("add failed , this data is full . ");
        }
        if(index < 0 || index > size ){
            throw new IllegalArgumentException("add failed , require index >=0 and index <= data.size");
        }

        for (int i = size-1; i >= index ; i--) {
            data[i+1]=data[i];
        }
        data[index] =e;
        size++;

    }

    public int removeFirst(){
        return remove(0);
    }

    public int removeLast(){
        return remove(size-1);
    }

    // 根据索引删除元素,后面的向前面补进
    public int remove(int index){
        if(index <0 || index >=size){
            throw new IllegalArgumentException("remove failed , require index >=0 and index <= data.size");
        }
        int res = data[index];
        for (int i = index+1 ; i <size ; i++) {
            data[i-1] = data[i];
        }
        size--;
        return res;
    }

    public void removeElement(int e){
        int i = find(e);
        if(i != -1){
            remove(i);
        }
    }

    public boolean contains(int e){
        for (int i = 0; i <size ; i++) {
            if(e  == data[i]){
                return true;
            }
        }
        return false;
    }
    public int find(int e){
        for (int i = 0; i <size ; i++) {
            if(e  == data[i]){
                return i;
            }
        }
        return -1;

    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Array size = %d , capacity = %d %n",size,data.length));
        sb.append("[");
        for (int i = 0; i <size ; i++) {
            sb.append(data[i]);
            if(i != size-1){
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        Array array = new Array(20);
        for (int i = 0; i <10 ; i++) {
            array.addLast(i);
        }
        System.out.println(array);
        array.add(2,100);
        System.out.println(array);
        array.addFirst(-1);
        System.out.println(array);
        array.set(10,50);
        System.out.println(array.get(10));

        System.out.println(array.contains(50));
        System.out.println(array);
        array.removeLast();
        System.out.println(array);
        array.removeFirst();
        System.out.println(array);
        array.remove(2);
        System.out.println(array);


    }


}
