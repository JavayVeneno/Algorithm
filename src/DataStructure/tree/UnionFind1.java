package DataStructure.tree;

import java.util.Random;

public class UnionFind1 implements UF{

    private int[] id;

    public UnionFind1(int size){
        id = new int[size];
        for (int i = 0; i < id.length; i++) {
            id[i] =i;
        }
    }

    private int find(int k){
        if(k<0||k>=id.length){
            throw new IllegalArgumentException("index is out of bound.");
        }
        return id[k];
    }

    @Override
    public int getSize(){
        return id.length;
    }

    @Override
    public boolean isConnected(int p, int q) {

        return find(p)==find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int idP = find(p);
        int idQ = find(q);
        if(idP==idQ){
            return;
        }
        for (int i = 0; i < id.length; i++) {
            if(id[i]==idP){
                id[i]=idQ;
            }
        }
    }


    public static void main(String[] args) {

        int size = 10000000; //元素个数
        int m = 10000000; //操作次数

//        UF uf1 = new UnionFind1(size);
//        System.out.println("UnionFind1: "+ testUF(uf1, m)+" s");
//
//        UF uf2 = new UnionFind2(size);
//        System.out.println("UnionFind2: "+ testUF(uf2, m)+" s");

        UF uf3 = new UnionFind3(size);
        System.out.println("UnionFind3: "+ testUF(uf3, m)+" s");

        UF uf4 = new UnionFind4(size);
        System.out.println("UnionFind4: "+ testUF(uf4, m)+" s");

        UF uf5 = new UnionFind5(size);
        System.out.println("UnionFind5: "+ testUF(uf5, m)+" s");

        UF uf6 = new UnionFind6(size);
        System.out.println("UnionFind6: "+ testUF(uf6, m)+" s");
    }

    private static double testUF(UF uf, int m) {

        long startTime = System.nanoTime();
        Random random = new Random();
        int size = uf.getSize();
        for (int i = 0; i < m; i++) {
            int p = random.nextInt(size);
            int q = random.nextInt(size);
            uf.unionElements(p, q);
        }

        for (int i = 0; i < m; i++) {
            int p = random.nextInt(size);
            int q = random.nextInt(size);
            uf.isConnected(p, q);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;

}
}
