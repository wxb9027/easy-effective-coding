package easy.effective.coding.suanfa.divide_and_conquer;

/**
 * 使用分而治之思想，求一个数值的和
 */

public class DCSum {
    public static void main(String[] args) {
        int[] ints = {1,3,5,7,9};
        int sum = DCSum.sum(ints,0,ints.length -1);
        System.out.println(sum);

        int count = DCSum.count(ints,0,ints.length - 1);
        System.out.println(count);


    }
    public static int sum(int[] ints ,int from,int to){
        if (from == to){
            // 基线条件： 分到最小的问题来治理
            return ints[to];
        }else {
            // 不断将问题分解 ：这里把问题分解成更小的问题
            return ints[to] + sum(ints,from,to - 1);
        }
    }


    public static int count(int[] ints,int from,int to){
        // 基线条件
        if (from == to){
            return 1;
        }else {
            // 问题分解
            return 1 + count(ints,from,to - 1);
        }
    }


    // 基本条件 集合只剩下最后一个数

    // 问题分解 找出子集的最大数

    public static int searchNax(int[] ints,int from,int to,int maxIndex){
        if (from == to){
            if (ints[maxIndex] > ints[to]){
                return ints[maxIndex];
            }else {
                return ints[to];
            }
        }else {
            searchNax(ints,from,to -1,maxIndex);
        }
        return 1;
    }


}
