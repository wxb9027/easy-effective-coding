//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。 
//
// 
//
// 示例: 
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
// 
// Related Topics 数组 哈希表 
// 👍 9206 👎 0


package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//Java：两数之和
class P1TwoSum{
    public static void main(String[] args) {
        Solution solution = new P1TwoSum().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * @Description //TODO
     * @Date  2020/9/25 23:54
     * @Param
     * @return
     **/
class Solution {
    /**
     * @Description
     * 思想：暴力解封，遍历所有结果
     * 时间复杂度：
     * 空间复杂度：
     * 优缺点：
     **/
    public int[] twoSum1(int[] nums, int target){
        if (nums == null){
            return null;
        }
        for (int i = 0 ; i < nums.length - 1; i++){
            for (int j = i + 1; j < nums.length; j++){
                if ( nums[i] + nums[j] == target ){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{};
    }

    /**
     * @Description
     * 思想：使用map减少遍历
     * 时间复杂度：
     * 空间复杂度：
     * 优缺点：
     **/
    public int[] twoSum2(int[] nums, int target) {
        if (nums == null){
            return null;
        }
        Map<Integer,Integer> map = new HashMap<>(nums.length);
        for (int i = 0 ; i < nums.length ; i ++){
            int other = target - nums[i];
            if (map.containsKey(other)){
                return new int[]{i,map.get(other)};
            }else {
                map.put(nums[i],i);
            }
        }
        return new int[]{};
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
