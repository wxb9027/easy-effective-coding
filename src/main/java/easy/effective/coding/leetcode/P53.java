package easy.effective.coding.leetcode;

import org.junit.jupiter.api.Test;

/**
 * 动态规划 - 求最大子序列和
 */
public class P53 {

    @Test
    public void test() {
        int[] nums = {-1, -2};
//        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
    }

    /**
     * 递推法
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     */
    public int maxSubArray(int[] nums) {
        int prev = nums[0], max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prev = Math.max(nums[i] + prev, nums[i]);
            max = Math.max(prev, max);
        }
        return max;
    }

    /**
     * 递推法
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     */
    /*public int maxSubArray(int[] nums) {

        int tmp = nums[0];
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int sum = nums[i] + tmp;
            if (sum <= nums[i]) { //没有增益效果
                tmp = nums[i];
            } else { //有增益效果
                tmp = sum;
            }
            max = tmp > max ? tmp : max;
        }
        return max;
    }*/


    /**
     * 递推法
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     */
    /*public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i] + dp[i - 1], nums[i]);
            max = Math.max(dp[i], max);
        }
        return max;
    }*/

}
