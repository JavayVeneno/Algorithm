package dataStructure.tree;

public class UnionFind2 implements UF{

    private int[] parent;

    public UnionFind2(int size){
        parent = new int[size];
        for (int i = 0; i < parent.length; i++) {
            parent[i] =i;
        }
    }

    private int find(int k){
        if(k<0||k>=parent.length){
            throw new IllegalArgumentException("index is out of bound.");
        }
        while(k!=parent[k]){
            k=parent[k];
        }
        return k;
    }

    @Override
    public int getSize(){
        return parent.length;
    }

    @Override
    public boolean isConnected(int p, int q) {

        return find(p)==find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if(pRoot==qRoot){
            return;
        }

        parent[qRoot]=pRoot;

    }
}
