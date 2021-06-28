package easy.effective.coding.leetcode;

import org.junit.jupiter.api.Test;

public class P88 {

    @Test
    public void test() {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        merge(nums1, 3, nums2, 3);

        for (int i = 0; i < nums1.length; i++) {
            System.out.println(nums1[i]);
        }
    }


    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int len = m + n;
        int j = 0;
        for (int i = m; i < len; i++) {
            nums1[i] = nums2[j];
            j++;
        }

        for (int i = 0; i < len; i++) {
            for (int p = i + 1; p < len; p++) {
                if (nums1[p] < nums1[i]) {
                    int tmp = nums1[i];
                    nums1[i] = nums1[p];
                    nums1[p] = tmp;
                }
            }
        }

    }
}
