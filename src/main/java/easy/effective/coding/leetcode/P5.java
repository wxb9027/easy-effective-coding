package easy.effective.coding.leetcode;

import org.junit.jupiter.api.Test;

/**
 * 动态规划-最长回文子串
 */
public class P5 {

    @Test
    public void test() {
        String s = "baabcbaa";
        System.out.println(longestPalindrome(s));
    }


    /**
     * 动态规划思想-状态转移
     * dp[i][j] = dp[i+1][j-1] ^ s[i]=s[j]
     */
    public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        //初始化子串为第一个字符
        String maxSonStr = String.valueOf(chars[0]);
        int maxSonLen = 1;
        int len = chars.length;

        //定义一个布尔类型的二维数组dp
        boolean[][] dp = new boolean[len][len];

        //标记单个字符即长度为1的子串为回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        //标记长度为2的子串是否为回文串
        for (int i = 0; i < len - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                dp[i][i + 1] = true;
                if (maxSonLen < 2){
                    maxSonLen = 2;
                    maxSonStr = s.substring(i, i + 2);
                }
            }
        }

        //标记长度大于等于3的回文串是否为回文串，通过状态转移
        for (int L = 3; L <= len; L++) {
            for (int i = 0; i <= len - L; i++) {
                dp[i][i + L - 1] = dp[i + 1][i + L - 2] && chars[i] == chars[i + L - 1];
                if (dp[i][i + L - 1] == true && maxSonLen < L) {
                    maxSonLen = L;
                    maxSonStr = s.substring(i, i + L);
                }
            }
        }

        return maxSonStr;
    }


    /**
     * 先找出所有的回文子串
     * 边界情况：
     * 1、长度为1的回文子串；
     * 2、长度为2的回文子串；
     */
    /*public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        //初始化子串为第一个字符
        String sonStr = String.valueOf(chars[0]);
        int len = chars.length;

        //判断长度为奇数的回文串
        for (int i = 1; i < len; i++) {
            //以此元素为中心，向两侧扩展检查是否对称
            for (int j = 1; ; j++) {
                if (i - j >= 0 && i + j < len && chars[i - j] == chars[i + j]) {
                    String str = s.substring(i - j, i + j + 1);
                    sonStr = str.length() > sonStr.length() ? str : sonStr;
                } else {
                    break;
                }
            }
        }

        //判断长度为偶数的回文串
        for (int i = 0; i < len - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                String str = s.substring(i, i + 2);
                sonStr = str.length() > sonStr.length() ? str : sonStr;
                //以此两位元素为中心，向两侧扩展检查是否对称
                for (int j = 1; ; j++) {
                    if (i - j >= 0 && i + 1 + j < len && chars[i - j] == chars[i + 1 + j]) {
                        str = s.substring(i - j, i + j + 2);
                        sonStr = str.length() > sonStr.length() ? str : sonStr;
                    } else {
                        break;
                    }
                }
            }
        }
        return sonStr;
    }*/

}
