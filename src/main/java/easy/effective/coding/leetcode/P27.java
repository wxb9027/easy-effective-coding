package easy.effective.coding.leetcode;

import org.junit.jupiter.api.Test;

public class P27 {

    @Test
    public void test(){
        int[] nums = {3,2,2,3,4};
        int size = removeElement(nums, 3);
        System.out.println(size);
        for (int i = 0; i < size; i++) {
            System.out.print(nums[i]);
        }
    }

    public int removeElement(int[] nums, int val) {
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[left] = nums[i];
                left++;
            }
        }
        return left;
    }
}
