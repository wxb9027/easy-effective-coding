//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 
//
// 示例: 
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0] 
//
// 说明: 
//
// 
// 必须在原数组上操作，不能拷贝额外的数组。 
// 尽量减少操作次数。 
// 
// Related Topics 数组 双指针 
// 👍 741 👎 0


package leetcode.editor.cn;
//Java：移动零
class P283MoveZeroes{
    public static void main(String[] args) {
        Solution solution = new P283MoveZeroes().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * @Description //TODO
     * @Date  2020/9/23 19:16
     * @Param
     * @return
     **/
class Solution {
        /**
         * @Description
         * 非0元素左移，右端剩余原属置0
         **/
        public void moveZeroes1(int[] nums){
            int copyToMe = 0;
            for ( int i = 0; i < nums.length; i++ ){
                if ( nums[i] != 0 ){
                    nums[copyToMe++] = nums[i];
                }
            }
            while ( copyToMe < nums.length ){
                nums[copyToMe++] = 0;
            }
        }


        /**
         * @Description
         * 将非0元素交换到左端
         **/
        public void moveZeroes2(int[] nums) {
            for ( int left = 0, right = 0; right < nums.length; ){
                if (nums[right] == 0){
                    right ++;
                    continue;
                }
                if ( nums[right] != 0 ){
                    int tmp = nums[right];
                    // 这里不能写nums[right] = 0  ，为什么？ 考虑nums[left]为非0
                    nums[right] = nums[left];
                    nums[left] = tmp;
                    right ++;
                    left ++;
                }
            }
        }
        /**
         * @Description //TODO
         * 基于上一版代码结构优化 --> for
         **/
        public void moveZeroes3(int[] nums) {
            for ( int left = 0, right = 0 ; right < nums.length; right++ ){
                if ( nums[right] != 0 ){
                    int tmp = nums[right];
                    nums[right] = nums[left];
                    nums[left++] = tmp;
                }
            }
        }

        /**
         * @Description
         * 基于上一版代码结构优化 --> while
         **/
        public void moveZeroes4(int[] nums) {
            int left = 0, right = 0;
            while ( right < nums.length ){
                if ( nums[right] !=0 ){
                    int tmp = nums[right];
                    nums[right] = nums[left];
                    nums[left] = tmp;
                    left ++;
                }
                right ++;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
