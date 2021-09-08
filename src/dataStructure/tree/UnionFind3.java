package dataStructure.tree;

public class UnionFind3 implements UF {

    private int[] parent;
    private int[] sz;

    public UnionFind3(int size){
        parent = new int[size];
        sz = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i]=i;
            sz[i]=1;
        }
    }

    private int find(int k){
        if(k<0||k>=parent.length){
            throw new IllegalArgumentException("index out of bound.");
        }
        while(k!=parent[k]){
            k = parent[k];
        }
        return k;
    }
    @Override
    public boolean isConnected(int p,int q){
        return find(p)==find(q);
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public void unionElements(int p, int q) {
     int pRoot = find(p);
     int qRoot = find(q);
     if(pRoot == qRoot){
         return;
     }

     if(sz[pRoot]<sz[qRoot]){
         parent[pRoot]=qRoot;
        sz[qRoot]+=sz[pRoot];
     }else{
         parent[qRoot]=pRoot;
         sz[pRoot]+=sz[qRoot];
     }
    }
}

