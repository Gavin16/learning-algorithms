package leetcode.oneMoreHundred;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 15. 三数之和
 * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？
 * 请你找出所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *
 * 18. 四数之和
 * 给定一个包含n 个整数的数组nums和一个目标值target，判断nums中是否存在四个元素 a，b，c和 d，使得a + b + c + d的值与target相等？
 * 找出所有满足条件且不重复的四元组。
 *
 * 注意：答案中不可以包含重复的四元组。
 *
 *
 * 523. 连续的子数组和
 * 给定一个包含 非负数 的数组和一个目标 整数k ，编写一个函数来判断该数组是否含有连续的子数组，其大小至少为 2，且总和为 k 的倍数，
 * 即总和为 n * k ，其中 n 也是一个整数。
 *
 * 示例 1：
 *
 * 输入：[23,2,4,6,7], k = 6
 * 输出：True
 * 解释：[2,4] 是一个大小为 2 的子数组，并且和为 6。
 *
 */
public class Day036 {


    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = {23,2,4,6,7};
        int[] nums1 = {23,2,4,6,7};
        int[] nums2 = {23,2,6,4,7};
        int[] nums3 = {23,2,4,6,6};
        System.out.println(checkSubarraySum(nums,6));
        System.out.println(checkSubarraySum(nums1,6));
        System.out.println(checkSubarraySum1(nums2,13));
        System.out.println(checkSubarraySum2(nums2,13));
        System.out.println(checkSubarraySum2(nums3,7));

        System.out.println(hammingDistance(3,7));

    }

    public static int hammingDistance(int x, int y) {
        int res = x ^ y , dist = 0;
        while(res != 0){
            int bit = res & 1;
            dist = dist + bit;
            res = res >>> 1;
        }
        return dist;
    }

    /**
     * 递归回溯
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum1(int[] nums) {
        return null;
    }

    /**
     * 双指针遍历: O(N^2) 时间复杂度, LeetCode 执行超时
     * @param nums
     * @param k
     * @return
     */
    public static boolean checkSubarraySum(int[] nums, int k) {
        if(nums.length <= 1) return false;

        int l = 0, r = l + 1 , sum = nums[l];
        for( ; l < r && r < nums.length ; r++){
            sum += nums[r];
            if(sum % k == 0){
                return true;
            }
            if(r == nums.length-1){
                l++;
                r = l;
                sum = nums[l];
            }
        }
        return false;
    }


    /**
     * 使用前缀和: 时间复杂度 O(N^2) 超出时间限制
     * @param nums
     * @param k
     * @return
     */
    public static boolean checkSubarraySum1(int[] nums, int k) {
        if(nums.length <= 1) return false;
        int len = nums.length;
        // preSum[i] 前i个元素的和；preSum[0] = 0
        int[] preSum = new int[len+1];
        for(int l = 0 ; l < len ; l++){
            preSum[l+1] = preSum[l] + nums[l];
        }

        for(int i = 0 ; i < len-1; i++){
            for (int j = i+1; j < len; j++) {
                int sum = preSum[j+1] - preSum[i];
                if(sum == k || (k != 0 && sum % k == 0)){
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 使用HashMap 保存余数与下标的位置：时间复杂度O(n)
     * @param nums
     * @param k
     * @return
     */
    public static boolean checkSubarraySum2(int[] nums, int k) {
        if(nums.length <= 1 || k == 0) return false;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
        int sum = 0;
        for(int i = 0 ; i < nums.length ; i++){
            sum += nums[i];
            int rem = sum % k;
            if(map.containsKey(rem)){
                if(i - map.get(rem) > 1){
                    return true;
                }
            }else{
                map.put(rem,i);
            }
        }
        return false;
    }

}
