package leetcode.editor.cn;

import java.util.List;

/**
 * @Description: TODO
 * @Author TODO
 * @DATE 2020/10/22 23:25
 * @Version 1.0
 **/
//Java：括号生成
public class P22GenerateParentheses {
    public static void main(String[] args) {
        Solution solution = new P22GenerateParentheses().new Solution();
        // TO TEST
        solution.generateParenthesisAll(2);
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> generateParenthesis(int n) {
            return null;
        }
        /**
         * @Description
         * 递归生成所有括号
         * @Date  2020/10/25 21:17
         * @Param
         * @return
         **/
        public List<String> generateParenthesisAll(int n){

            _generateAll( 0, n, "");

            return null;

        }

        private void _generateAll(int level, int MAX, String s) {
            // end
            if (level >= MAX){
                System.out.println(s);
                return;
            }
            // proc
            String s1 = s + "0";
            String s2 = s + "1";
            // recur
            _generateAll(level + 1,MAX,s1);
            _generateAll(level + 1,MAX,s2);
            // current status

        }
    }

}
