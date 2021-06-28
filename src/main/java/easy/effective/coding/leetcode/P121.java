package easy.effective.coding.leetcode;

import org.junit.jupiter.api.Test;

/**
 * 动态规划 -买卖股票的最佳时机
 */
public class P121 {

    @Test
    public void test(){
        int[] prices = {7,1,5,3,6,8};
        System.out.println(maxProfit(prices));
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int maxProfit(int[] prices) {
        int prevMin = prices[0];
        int max = 0;

        for (int i = 1; i < prices.length; i++) {
            prevMin = Math.min(prices[i], prevMin);
            max = Math.max(max, prices[i] - prevMin);
        }

        return max;
    }
}
