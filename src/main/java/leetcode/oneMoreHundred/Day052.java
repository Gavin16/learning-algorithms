package leetcode.oneMoreHundred;

import java.util.*;

/**
 * @className: Day052
 * @description:
 *
 * 229. 多数元素 II
 * 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 *
 * 示例 1：
 *
 * 输入：nums = [3,2,3]
 * 输出：[3]
 * 示例 2：
 *
 * 输入：nums = [1]
 * 输出：[1]
 * 示例 3：
 *
 * 输入：nums = [1,2]
 * 输出：[1,2]
 *
 * 提示：
 *
 * 1 <= nums.length <= 5 * 104
 * -109 <= nums[i] <= 109
 *
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2022/7/27
 */
public class Day052 {


    public static void main(String[] args) {
        int[] arr1 = {3,2,3};
        int[] arr2 = {1,2};
        int[] arr3 = {1};

        Day052 day052 = new Day052();
        System.out.println(day052.majorityElement(arr1));
        System.out.println(day052.majorityElement(arr2));
        System.out.println(day052.majorityElement(arr3));
    }

    /**
     * 排序遍历统计
     */
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if(nums.length <= 2){
            HashSet<Integer> set = new HashSet<>();
            for(int num : nums){ set.add(num); }
            list.addAll(set);
            return list;
        }
        // 排序后使用双指针遍历比较
        int len = nums.length;
        Arrays.sort(nums);
        int threshold = (len/3) + 1 ;
        for(int i = threshold -1; i < len; ){
            if(nums[i - threshold + 1] == nums[i]){
                list.add(nums[i]);
                // 跳过i之后所有与nums[i] 相等的元素
                int j = i+1;
                while(j < len && nums[j] == nums[i]){ j++; }
                i = j;
            }else{
                i++;
            }
        }
        return list;
    }

}
