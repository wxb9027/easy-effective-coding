package leetcode.editor.cn;

/**
 * @Description: TODO
 * @Author TODO
 * @DATE 2020/10/21 22:29
 * @Version 1.0
 **/
public class P70ClimbingStairs {
    public static void main(String[] args) {
        Solution solution = new P70ClimbingStairs().new Solution();
        // TO TEST
        int result = solution.climbStairs(10);
        System.out.println(result);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int climbStairs(int n) {
            if ( n <= 1){
                return 1;
            }
            if ( n < 3 ){
                return n;
            }
            return climbStairs( n - 1 ) + climbStairs( n -2 );
        }
    }

    class Solution2 {
        public int climbStairs(int n) {
            int[] dp = new int[n+1];
            dp[0] = 1;
            dp[1] = 1;
            for(int i = 2; i <= n; i++){
                dp[i] = dp[i-1] + dp[i-2];
            }
            return dp[n];
        }
    }
}
