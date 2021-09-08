package dataStructure.tree;

public class UnionFind5 implements UF {

    private int[] parents;
    //rank[i]表示i为根的集合所表示的树的层数
    private int[] rank;

    public UnionFind5(int size) {
        parents = new int[size];
        rank = new int[size];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
            //每个根节点所在的树一开始都只有一个层
            rank[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parents.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 查询某个节点的根节点
     * 时间复杂度为O(h)
     *
     * @param k
     * @return
     */
    private int find(int k) {
        while (k != parents[k]) {
            parents[k] = parents[parents[k]];
            k = parents[k];
        }
        return k;
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        //根据根节点所在树的层级来判断合并方向
        //层级矮的树往层级高的树合并不需要维护rank
        if (rank[pRoot] < rank[qRoot]) {
            parents[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) {
            parents[qRoot] = pRoot;
        } else {//只有rank相等的情况才需要维护rank
            parents[pRoot] = qRoot;
            rank[qRoot] += 1;
        }
    }

}