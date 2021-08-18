package DataStructure.tree;

import java.util.Random;

/**
 * @Description: 并查集
 */
public interface UF {

    int getSize();

    void unionElements(int p,int q);

    boolean isConnected(int p ,int q);



}
