package easy.effective.coding.leetcode;

import org.junit.jupiter.api.Test;

/**
 * 动态规划 - 爬楼梯(斐波那契数列)
 */
public class P70 {

    @Test
    public void test(){
        for(int i =1;i<=45;i++){
            System.out.println(climbStairs(i));
        }
    }

    /**
     * 利用斐波那契数列递推公式
     * 由于pow 函数的时空复杂度与 CPU 支持的指令集相关，这里不深入分析
     */
    public int climbStairs(int n) {
        double sqrt5 = Math.sqrt(5);
        double fibn = Math.pow((1+sqrt5)/2, n+1) - Math.pow((1-sqrt5)/2, n+1);
        return (int) Math.round(fibn / sqrt5);
    }

    /**
     * 利用滚动数组的思想，进一步优化空间复杂度
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     */
    /*public int climbStairs(int n) {
        int p = 0;
        int q = 0;
        int r = 1;

        for(int i =1;i<=n;i++){
            p = q;
            q = r;
            r = p+q;
        }
        return r;
    }*/

    /**
     * 利用数组求解，优化空间复杂度
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     */
    /*public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        int[] dp = new int[n+1];

        dp[1] = 1;
        dp[2] = 2;
        for(int i=3;i<=n;i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }*/

    /**
     * 递归求解
     * 时间复杂度O(2^n)
     */
    /*public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }*/
}
