package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Author TODO
 * @DATE 2020/10/30 14:55
 * @Version 1.0
 **/
public class P78Subsets {
    public static void main(String[] args) {
        Solution solution = new P78Subsets().new Solution();
        // TO TEST
        solution.subsets(new int[]{1,2,3,4,5});
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> ans = new ArrayList<>();
            if (nums == null || nums.length == 0){
                return ans;
            }
            dfs(ans, nums, new ArrayList<Integer>(), 0);

            ans.stream().forEach(e -> System.out.println(e));
            return ans;
        }

        private void dfs(List<List<Integer>> ans, int[] nums, ArrayList<Integer> list, int index) {
            //end
            if (index == nums.length){
                ans.add(new ArrayList<Integer>(list));
                return;
            }
            // process

            // recur
            dfs(ans, nums, list, index + 1);
            list.add(nums[index]);
            dfs(ans, nums, list, index + 1);

            // restore state
            list.remove(list.size() - 1);

        }
    }


}
