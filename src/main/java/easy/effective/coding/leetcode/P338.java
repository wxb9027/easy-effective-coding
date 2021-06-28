package easy.effective.coding.leetcode;

import org.junit.jupiter.api.Test;

/**
 * 动态规划 - 比特位计算
 */
public class P338 {


    @Test
    public void test() {
        int[] result = countBits(5);
        for (int i : result) {
            System.out.print(i);
        }
    }

    /**
     * 根据奇偶性找规律，进一步简化代码
     * 1.奇数：二进制表示中，奇数一定比前面那个偶数多一个1；
     * 2.偶数：由于偶数的二进制表示中末位一定是0，故其一比特数，一定与右移一位（即除以2）后的数相同；
     */
    public int[] countBits(int n) {
        int[] result = new int[n + 1];
        result[0] = 0;

        for (int i = 1; i <= n; i++) {
            result[i] = result[i >> 1] + i % 2;
        }
        return result;
    }


    /**
     * 根据奇偶性找规律
     * 1.奇数：二进制表示中，奇数一定比前面那个偶数多一个1；
     * 2.偶数：由于偶数的二进制表示中末位一定是0，故其一比特数，一定与右移一位（即除以2）后的数相同；
     */
    /*public int[] countBits(int n) {
        int[] result = new int[n + 1];
        result[0] = 0;

        for (int i = 1; i <= n; i++) {
            if ((i & 1) == 0) { //偶数
                result[i] = result[i >> 1];
            } else { //奇数
                result[i] = result[i - 1] + 1;
            }
        }
        return result;
    }*/


    /**
     * 直接用api
     */
    /*public int[] countBits(int num) {
        int[] sum=new int[num+1];
        for(int i=0;i<=num;i++){
            sum[i]=Integer.bitCount(i);
        }
        return sum;
    }*/


}
