package DataStructure.tree;

public class UnionFind4 implements UF {

    private int[] parent;
    private int[] rank;

    public UnionFind4(int size){
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i]=1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    private int find(int k){
        if(k<0||k>=parent.length){
            throw new IllegalArgumentException("index out of bound.");
        }

        while(k!=parent[k]){
            k=parent[k];
        }
        return k;
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
            return ;
        }

        if(rank[pRoot]<rank[qRoot]){
            parent[pRoot] = qRoot;
        }else if(rank[pRoot]>rank[qRoot]){
            parent[qRoot]=pRoot;
        }else{
            parent[qRoot] =pRoot;
            rank[pRoot]+=1;
        }
    }
}
