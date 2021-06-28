package easy.effective.coding.leetcode;

import org.junit.jupiter.api.Test;

public class P26 {

    @Test
    public void test() {
        int[] nums = {1, 1, 1};
        int size = removeDuplicates(nums);
        System.out.println(size);

        for (int i = 0; i < size; i++) {
            System.out.print(nums[i]);
        }
     }

    public int removeDuplicates(int[] nums) {
        int p = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[p] = nums[i];
                p++;
            }
        }
        return p;
    }

}
