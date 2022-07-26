package leetcode.oneMoreHundred;

import com.alibaba.fastjson.JSON;
import utils.ArrayUtil;

import java.util.*;

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
 *
 * 《15. 三数之和》
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例 1：
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 示例 2：
 *
 * 输入：nums = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：[]
 *  
 * 提示：
 *
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 *
 */
public class Day050 {

    public static void main(String[] args) {
        int[] arr1 = {1,2,1,3,2,5};
        int[] arr2 = {-1,0};

        Day050 day050 = new Day050();
//        int[] ans1 = day050.singleNumber(arr1);
//        int[] ans2 = day050.singleNumber(arr2);
//        ArrayUtil.showArray(ans1);
//        ArrayUtil.showArray(ans2);

        int[] nums = {1,2,3};
        int[] nums1 = {1,1,1};
        int[] nums2 = {1,-1,0};
//        System.out.println(day050.subarraySum2(nums, 3));
//        System.out.println(day050.subarraySum2(nums1, 2));
//        System.out.println(day050.subarraySum2(nums2, 0));

        int[] input = {-1,0,1,2,-1,-4};
        List<List<Integer>> lists = day050.threeSum(input);
        for(List<Integer> list : lists){
            System.out.println(JSON.toJSON(list));
        }
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
     * 嵌套循环遍历
     * 时间复杂度 O(N^2)
     * 空间复杂度 O(1)
     */
    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        int count = 0;
        for(int left = 0 ; left < len; left++){
            int sum = 0;
            for(int right = left; right < len ; right++){
                sum += nums[right];
                if(sum == k){
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 使用前缀数组减少重复计算量
     *
     * 时间复杂度 O(N^2)
     * 空间复杂度 O(N)
     */
    public int subarraySum1(int[] nums, int k) {
        int len = nums.length;
        int[] preSums = new int[len+1];
        // 计算前缀和,注意int数据是否越界，越界使用long
        for(int i = 0; i < len; i++){
            preSums[i+1] = preSums[i] + nums[i];
        }
        int count = 0;
        for(int left = 0; left < len; left++){
            for(int right = left; right < len ;right++){
                if(preSums[right+1] - preSums[left] == k){
                    count++;
                }
            }
        }
        return count;
    }


    /**
     * 使用前缀和 + hash
     *
     * 构造前缀和时，在Map中 逐个保存每个前缀出现的次数
     * 因此找出子数组的个数问题可以转化为如下模型：
     *      子前缀和 + 子数组  = 完整前缀和
     *      当子数组和为 K 时则有  完整前缀和 - 子前缀和 = K
     *      因此只要判断 Map中是否存在等于 完整前缀和 - K 的子前缀和
     * 假设子数组的下标起止位置为 [j, i ]
     * 那么只需要在Map中找出子前缀出现的次数 preSum[i] - k 出现的次数
     * 出现过多少次，代表这存在着多少中满足[x, i] 的可能使用 [x,i] 的和为K
     *
     */
    public int subarraySum2(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        int preSum = 0, count = 0;
        for(int i = 0 ; i < nums.length; i++){
            preSum += nums[i];
            if(map.containsKey(preSum - k)){
                count += map.get(preSum - k);
            }
            // 若之前存在了preSum 则在原基础上+1
            map.put(preSum, map.getOrDefault(preSum,0) + 1);
        }
        return count;
    }


    /**
     * 求三数之和等于 0
     *
     * 为了避免重复，首先对数组进行排序
     * 排序后的数组方便使用相邻下标来判断元素是否相同
     * 若相同则跳过当前元素,否则进行等值判断
     *
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        ArrayList<List<Integer>> lists = new ArrayList<>();
        int len = nums.length;
        for(int first = 0; first < len; first++){
            if(first > 0 && nums[first] == nums[first - 1 ]){ continue;}
            int target = - nums[first];
            for(int second = first + 1; second < len; second++ ){
                if(second > first + 1 && nums[second] == nums[second-1]){ continue; }
                int third = len - 1;
                // 判断 若 nums[second] + nums[third] > target 则说明third 太大
                while(third > second && nums[second] + nums[third] > target){
                    third--;
                }
                if(third == second){ break; }
                if(nums[second] + nums[third] == target){
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    lists.add(list);
                }
            }
        }
        return lists;
    }

}
