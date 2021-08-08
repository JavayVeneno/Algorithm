package DataStructure.array;

import Serach.Student;

public class Array<E> {

    // 数组底层实现存储

    private E[] data;

    // 已存放数据的个数,同时也表示数组第一个未存放数据的位置的索引

    private int size;

    // 指定空间的构造方法

    @SuppressWarnings("unchecked")
    public Array(int capacity){
        data = (E[])new Object[capacity];
        size = 0;
    }

    // 无参构造方法 默认capacity为10

    public Array(){
        this(10);
    }

    public Array(E[] arr) {
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        size=arr.length;
    }
    //判断数组是否为空

    public boolean isEmpty(){
        return size == 0;
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

    public void addFirst(E e){

        add(0,e);
    }

    public void set(int index, E e) {
        if(index<0 || index>=size){
            throw new IllegalArgumentException("set failed . index require >=0 and < data size ");
        }
        data[index] =e;
    }

    public E get(int index) {
        if(index<0 || index>=size){
            throw new IllegalArgumentException("get failed . index require >=0 and < data size ");
        }
        return data[index];
    }

    // 向数组末尾添加元素

    public void addLast(E e){
        //可以服用add的逻辑
//        if(size == data.length){
//            throw new IllegalArgumentException("addLast failed , this data is full . ");
//        }
//        data[size]= e;
//        size++;
        add(size,e);
    }

    // 向数组指定位置添加元素,并且其余元素向后移动

    public void add(int index,E e){
//        if(size == data.length){
//            throw new IllegalArgumentException("add failed , this data is full . ");
//        }
        if(index < 0 || index > size ){
            throw new IllegalArgumentException("add failed , require index >=0 and index <= data.size");
        }
        if(size == data.length){
            resize(data.length<<1);
        }
        for (int i = size-1; i >= index ; i--) {
            data[i+1]=data[i];
        }
        data[index] =e;
        size++;

    }


    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size-1);
    }

    // 根据索引删除元素,后面的向前面补进
    public E remove(int index){
        if(index <0 || index >=size){
            throw new IllegalArgumentException("remove failed , require index >=0 and index < data.size");
        }
        E res = data[index];
        for (int i = index+1 ; i <size ; i++) {
            data[i-1] = data[i];
        }
        size--;
        // loitering object  != mem leak 所以我们手动置空可以提示jvm释放内存,但是不释放的逻辑也是对的,这是针对jvm而言
        data[size]=null;
        // 如果每次添加后扩容,再删除又得缩容,这样复杂度震荡,单纯来讲,最坏的情况下添加元素会触发扩容他的时间复杂度为O(n),但是在实际中,并不是每次都会扩容
        // 发生扩容 一定是添加到了n=length个元素,那么这里应该用均摊复杂度来分析,换句话说就是每次扩容其实是 n(次添加) + (n+1)(n次重新赋值+1次申请新数组)
        // 我们把总计的操作均摊到每次add上面其实就是每次是(2n+1)/n+1的复杂度,但是由于又复杂度震荡的因素,我们分析得出其实每次缩容是一个eager的操作,这个操作
        // 略过激进,我们是不是可以把缩容改成lazy方式,也就是当remove到一半的一半的时候,我们再去缩一半的容
        if(size == data.length>>2 && data.length>>1 !=0 ){
            resize(data.length>>1);
        }
        return res;
    }

    public void removeElement(E e){
        int i = find(e);
        if(i != -1){
            remove(i);
        }
    }

    public boolean contains(E e){
        for (int i = 0; i <size ; i++) {
            if(data[i].equals(e)){
                return true;
            }
        }
        return false;
    }
    public int find(E e){
        for (int i = 0; i <size ; i++) {
            if( data[i].equals(e)){
                return i;
            }
        }
        return -1;

    }

    public void swap(int i, int j) {
        if(i<0 || i>=size || j<0 || j>=size){
            throw new IllegalArgumentException("index out of bound !");
        }
        E temp = data[j];
        data[j] = data[i] ;
        data[i] = temp;
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
    // 换容操作
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {

        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i <size ; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }


    public E getLast() {
        return get(size-1);
    }

    public E getFirst() {
        return get(0);
    }

    public static void main(String[] args) {
//        Array<Integer> array = new Array<>(20);
//        for (int i = 0; i <10 ; i++) {
//            array.addLast(i);
//        }
//        System.out.println(array);
//        array.add(2,100);
//        System.out.println(array);
//        array.addFirst(-1);
//        System.out.println(array);
//        array.set(10,50);
//        System.out.println(array.get(10));
//
//        System.out.println(array.contains(50));
//        System.out.println(array);
//        array.removeLast();
//        System.out.println(array);
//        array.removeFirst();
//        System.out.println(array);
//        array.remove(2);
//        System.out.println(array);


        Array<Student> mates = new Array<>(2);
        Student s1 = new Student(1L,"老刘",30);
        Student s2 = new Student(2L,"胜英",29);
        Student s3 = new Student(3L,"云神",28);
        Student s4 = new Student(4L,"海军",27);
        System.out.println(mates);
        mates.addFirst(s1);
        System.out.println(mates);
        mates.addLast(s3);
        mates.addLast(s4);
        System.out.println(mates);
        mates.add(1,s2);
        System.out.println(mates);
        System.out.println(mates.isEmpty());
        Student s5 = new Student(5L,"王波",26);
        System.out.println(mates.find(s5));
        System.out.println(mates.remove(1));
        System.out.println(mates);
        mates.removeFirst();
        System.out.println(mates);
        mates.removeLast();
        System.out.println(mates);
        mates.removeElement(s4);
        System.out.println(mates);
        System.out.println(mates.remove(0));
        System.out.println(mates);

    }


}
