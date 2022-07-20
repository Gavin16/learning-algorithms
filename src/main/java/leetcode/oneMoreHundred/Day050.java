package leetcode.oneMoreHundred;

import utils.ArrayUtil;

/**
 * @className: Day050
 * @description:
 *
 * 《260. 只出现一次的数字 III》
 *
 * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
 *
 * 进阶：你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
 * 示例 1：
 *
 * 输入：nums = [1,2,1,3,2,5]
 * 输出：[3,5]
 * 解释：[5, 3] 也是有效的答案。
 * 示例 2：
 *
 * 输入：nums = [-1,0]
 * 输出：[-1,0]
 * 示例 3：
 *
 * 输入：nums = [0,1]
 * 输出：[1,0]
 * 提示：
 *
 * 2 <= nums.length <= 3 * 104
 * -231 <= nums[i] <= 231 - 1
 * 除两个只出现一次的整数外，nums 中的其他数字都出现两次
 *
 *
 *
 * 《560. 和为 K 的子数组》
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的连续子数组的个数 。
 *
 * 示例 1：
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 *
 * 提示：
 *
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 *
 */
public class Day050 {

    public static void main(String[] args) {
        int[] arr1 = {1,2,1,3,2,5};
        int[] arr2 = {-1,0};

        Day050 day050 = new Day050();
        int[] ans1 = day050.singleNumber(arr1);
        int[] ans2 = day050.singleNumber(arr2);
        ArrayUtil.showArray(ans1);
        ArrayUtil.showArray(ans2);
    }

    /**
     * 按位与的方式求出 a ^ b
     * 然后找出 a与b中存在的位差异，并取出该位
     * 通过该位来对原数据进行拆分，可以拆分出两组
     * 其中一组改位为1, 另一组该位为0
     * 这样问题就转化成了求两个"只有一个元素出现一次，其它元素出现两次"的两个数组中各自的出现一次的元素
     *
     */
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for(int i : nums){
            xor = xor ^ i;
        }
        // 假设 a ^ b = xor, 那么 a 与 b 不相同的最低位对应的值如下
        // 显然 bit 等于 2^n(n = 0,1,2, ...)
        int bit = xor & (-1 * xor);
        int a = 0, b = 0;
        for(int j : nums){
            if((bit & j) == 0){
                a = a ^ j;
            }else{
                b = b ^ j;
            }
        }
        int[] ints = new int[2];
        ints[0] = a ;
        ints[1] = b ;
        return ints;
    }

    /**
     * 找出所有和为 K 的数组
     *
     */
    public int subarraySum(int[] nums, int k) {
        return -1;
    }


}
