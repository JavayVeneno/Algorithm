package leetcode.tree;

import java.util.Arrays;

/*
 *
 * @Description: 使用SQRT来完成leetcode上303号题
 *
 * @author: Amei
 * @date: 2021/9/6 19:49
 * @param:
 * @return:
 */
public class NumArray307pSQRT {

    /*
    *  data存放元素
    *
    *  blocks存放预处理的每个块区间元素和
    *
    *  sqrt 表示每块元素数量 因为其本身存储最大为 Math.sqrt(dataSize)下取整;
    *
    * */
    private int[] data,blocks;

    private int dataSize,blocksSize,sqrt;

    public NumArray307pSQRT(int[] nums){

        dataSize = nums.length;
        if(dataSize==0){
            return;
        }
        data = new int[dataSize];
        data = Arrays.copyOf(nums,dataSize);
        sqrt = (int) Math.sqrt(dataSize);
        blocksSize = dataSize/sqrt + (dataSize%sqrt>0?1:0);
        blocks = new int[blocksSize];
        for (int i = 0; i < dataSize; i++) {
            blocks[i/sqrt] += data[i];
        }
    }

    public int sumRange(int x,int y){

        if(x<0 || y<0 || x>y || x>=dataSize || y>=dataSize){
            return 0;
        }
        int blockStart = x/sqrt;
        int blockEnd = y/sqrt;

        int res =0;
        // 如果查询区间在同一个块上
        if(blockStart == blockEnd){
            for (int i = x; i <=y ; i++) {
                res+=data[i];
            }
            return res;
        }
        // 查询区间不在同一块上(可以跨一块(中间的for不走)甚至多块)
        for (int i = x; i < (1+blockStart)*sqrt; i++) {
            res+=data[i];
        }

        for (int b = 1+blockStart ; b <blockEnd ; b++) {
            res+=blocks[b];
        }
        for (int i = blockEnd*sqrt; i <= y; i++) {
            res+=data[i];
        }


        return res;
    }

    public void update(int index ,int value){

        if(index<0 || index>=dataSize) {
            return;
        }

        int bIndex = index/sqrt;
        blocks[bIndex]  -= data[index];
        blocks[bIndex]  += value;
        data[index] = value;
    }


    public static void main(String[] args) {
        int[] test = {-2, 0, 3, -5, 2, -1};

        NumArray307pSQRT numArray = new NumArray307pSQRT(test);
        System.out.println(numArray.sumRange(0, 2));
        System.out.println(numArray.sumRange(2, 5));
        System.out.println(numArray.sumRange(0, 5));
        numArray.update(1,2);
        System.out.println(numArray.sumRange(0, 2));
    }
}
