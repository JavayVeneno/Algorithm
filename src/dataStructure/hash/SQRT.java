package dataStructure.hash;


import common.Merger;



public class SQRT<E> {


    private E[] data;

    private E[] block;

    private Merger<E> merge;

    // dn = data size bn = block size sq = sqrt(dn)= 区间 size
    private int dn,bn,sq;

    public SQRT(E[] data, Merger<E> merge){
        dn = data.length;
        if(dn==0){
            return;
        }

        this.merge = merge;
        sq = (int) Math.sqrt(dn);

        bn = sq + (dn%sq>0?1:0);

        block = (E[]) new Object[bn];


        this.data = (E[]) new Object[dn];
        for (int i = 0; i < dn; i++) {
            this.data[i] = data[i];
            if(i%sq==0){
                block[i/sq]=data[i];
            }
        }

        for (int i = 0; i < dn; i++) {
            int blockIndex = i / sq;
            if((i+1)/sq == blockIndex){
                block[blockIndex] = this.merge.merge(block[blockIndex],this.data[i+1]);
            }

        }

    }

    public E getRange(int x,int y){

        if(x<0 || x>=dn || y<0 || y>=dn || x>y){
            return null;
        }

        int bs = x/sq;
        int be = y/sq;

        E temp = data[x];
        if(bs == be){
            for (int i = x; i <y ; i++) {
                temp = merge.merge(temp,data[i+1]);
            }
            return temp;
        }

        for (int i = x; i < (bs+1)*sq-1 ; i++) {
            temp = merge.merge(temp,data[i+1]);
        }

        for (int b = bs+1; b <be ; b++) {
            temp = merge.merge(temp,block[b]);
        }

        for (int i = be*sq ; i<= y;i++){
            temp = merge.merge(temp,data[i]);
        }

        return temp;

    }


    public void update(int index ,E value){

        if(index<0 || index>=dn) {
            return;
        }

        // 能否继续保持O(1)的时间复杂度?
        int b = index/sq;
        data[index] = value;
        block[b] = data[b*sq];
        for (int i = b*sq; i +1< (b+1)*sq; i++) {
            block[b] = merge.merge(block[b],data[i+1]);
        }


    }


    public static void main(String[] args) {
        Integer[] nums = {1,2,3,4,5,6,7,8,9};
        SQRT<Integer> sqrt = new SQRT<>(nums, Math::addExact);
        sqrt.update(4,10);
        System.out.println(sqrt.getRange(0, 8));
    }
}
