package leetCode;


import java.util.ArrayList;
import java.util.List;

/**
 * 《LeetCode 442. 数组中重复的数据》
 *  给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
 *
 * 找到所有出现两次的元素。
 * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
 *
 * 示例：
 * 输入:
 * [4,3,2,7,8,2,3,1]
 *
 * 输出:
 * [2,3]
 *
 */
public class FindDuplicates {

    public static void main(String[] args) {
        int[] nums = {4,3,2,7,8,2,3,1};
        System.out.println(findDuplicates(nums));
    }

    /**
     * 利用好前提条件： 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）
     * 做法： 遍历数组时,对每一个元素值 - 1所在的位置作标记(取反操作),当发现某个元素值-1 所在位置的元素为 负时,则可认为该元素曾经出现过
     * @param nums
     * @return
     */
    static public List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<>();

        for(int i = 0 ; i < nums.length ; i++){
            if(nums[Math.abs(nums[i]) - 1] < 0){
                list.add(Math.abs(nums[i]));
            }else{
                nums[Math.abs(nums[i]) - 1] *= -1;
            }
        }
        return list;
    }


    /**
     *
     * 《LeetCode 287. 寻找重复数》
     * 大小为 n+1 的数组，元素大小在[1:n] 之间，只有一个元素且可能会重复出现多次，找出该重复的元素
     * https://leetcode-cn.com/problems/find-the-duplicate-number/comments/
     * 不能更改原数组
     * 只能使用额外的O(1) 的空间
     * 时间复杂度小于O(n^2)
     * 数组中只有一个重复的数字，但它可能不止重复出现一次
     * @param nums
     * @return
     */
    static public int findDuplicate(int[] nums){
        return -1;
    }
}
