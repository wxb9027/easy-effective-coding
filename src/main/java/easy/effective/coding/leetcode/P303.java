package easy.effective.coding.leetcode;

import org.junit.jupiter.api.Test;

/**
 * 动态规划 - 区域和检索 - 数组不可变
 */
public class P303 {

    @Test
    public void test() {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray obj = new NumArray(nums);
        int res1 = obj.sumRange(0, 2);
        int res2 = obj.sumRange(2, 5);
        int res3 = obj.sumRange(0, 5);
        System.out.println(res1 + "," + res2 + "," + res3);
    }


    class NumArray {

        int[] dp;

        public NumArray(int[] nums) {
            dp = new int[nums.length];
            dp[0] = nums[0];

            for (int i = 1; i < nums.length; i++) {
                dp[i] = dp[i - 1] + nums[i];
            }

        }

        /**
         * 初始化时间复杂度O(n)
         * 每次检索时间复杂度O(1)
         */
        public int sumRange(int left, int right) {
            return left == 0? dp[right] : dp[right] - dp[left - 1];
        }

        /**
         * 调用多次，时间复杂度会比较高
         */
        /*public int sumRange(int left, int right) {
            int sum = 0;
            for(int i = left; i<=right; i++){
                sum += nums[i];
            }
            return sum;
        }*/
    }

}
