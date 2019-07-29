package leetCode;

import utils.ArrayUtil;

/**
 * @Class: MergeTwoArray
 * @Description:
 *
 *  给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 *
 *  说明:
 *
 *  初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 *  你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *
 * @Author: Minsky
 * @Date: 2019/7/29 10:00
 * @Version: v1.0
 */
public class MergeTwoArray {

    public static void main(String[]args){
//        int[] nums1 = new int[] {1,2,3,0,0,0};
//        int[] nums2 = new int[] {2,5,6};

        int[] nums1 = new int[] {0};
        int[] nums2 = new int[] {1};

        merge(nums1,0,nums2,1);

        ArrayUtil.showArray(nums1);
    }

    /**
     * 从后到前的合并不需要一次遍历就可做到
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int k = m + n - 1;
        int i = m - 1;
        int j = n - 1;
        while(i >= 0 && j >= 0){
            nums1[k--] = (nums1[i] < nums2[j]) ? nums2[j--] : nums1[i--];
        }

        // 剩余元素继续移动
        while(i >= 0){
            nums1[k--] = nums1[i--];
        }
        while(j >= 0){
            nums1[k--] = nums2[j--];
        }
    }
}
