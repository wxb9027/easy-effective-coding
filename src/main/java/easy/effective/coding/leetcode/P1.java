package easy.effective.coding.leetcode;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 */
public class P1 {

    @Test
    public void test() {
        int[] nums = new int[]{2, 7, 11, 15};
        System.out.println(twoSum(nums, 9));
    }

    public int[] twoSum(int[] nums, int target) {
        Map map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(target - nums[i])) {
                map.put(nums[i], i);
            } else {
                return new int[]{(int) map.get(target - nums[i]), i};
            }
        }
        return new int[]{};
    }
}
