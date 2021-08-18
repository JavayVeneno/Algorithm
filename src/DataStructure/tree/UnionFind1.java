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

        int size  = 100000;

        int m  = 100000;

        UF uf1 = new UnionFind1(size);
        System.out.println("UnionFind1: "+ testUF(uf1, m)+" s");

        UF uf2 = new UnionFind2(size);
        System.out.println("UnionFind2: "+ testUF(uf2, m)+" s");

        UF uf3 = new UnionFind3(size);
        System.out.println("UnionFind3: "+ testUF(uf3, m)+" s");
    }

    public static double testUF(UF uf,int m){

        Random random = new Random();
        int size = uf.getSize();

        long time1 = System.nanoTime();

        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a,b);
        }
        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a,b);
        }

        long time2 = System.nanoTime();
        return (time2-time1)/1_000_000_000.0;
    }
}
