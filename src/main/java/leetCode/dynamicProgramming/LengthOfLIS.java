package leetCode.dynamicProgramming;

import java.util.Arrays;

/**
 *
 * 《300. 最长上升子序列》
 *
 *给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 *
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 *
 */
public class LengthOfLIS {


    public static void main(String[] args) {

        int[] input1 = {10,9,2,5,3,7,101,18};
        LengthOfLIS obj = new LengthOfLIS();
        System.out.println(obj.lengthOfLIS(input1));

//        System.out.println(null == null);
        int[] test = {1,2,5,7,8};

        System.out.println(obj.lengthOfLIS1(input1));
    }


    /**
     * DP算法实现
     *
     * dp[i] = max(dp[j]+1)   nums[i] > nums[j]
     *       = 1              nums[i] < nums[0],nums[1] ,… ,nums[j] (0 <= j < i)
     * 其中dp[i] 代表以nums[i] 结尾的最长子序列的长度
     *
     * 时间复杂度：O(n^2)
     */
    public int lengthOfLIS(int[] nums) {
        if(null == nums || nums.length == 0) return 0;

        int[] dp = new int[nums.length];
        dp[0] = 1;
        for(int i = 1 ; i < nums.length ; i ++){
            int maxLen = dp[0];
            for(int k = 0 ; k < i ; k++){
                if(nums[i] > nums[k] && dp[k] + 1 > maxLen){
                    maxLen = dp[k] + 1;
                }
            }
            dp[i] = maxLen;
        }
        int res  = 1;
        for(int len : dp){
            if(len > res) res = len;
        }
        return res;
    }


    /**
     *  贪心算法 + 二分查找实现
     *  [10,9,2,5,3,7,101,18]
     */
    public int lengthOfLIS1(int[] nums){

        int[] sortedSeq = new int[nums.length];
        int len = 0;
        for(int n : nums){
            // Arrays.binarySearch();方法返回找到元素的下标
            // 若未找到则返回 -id-1,这里的id为第一个大于搜索元素的下标
            int id = Arrays.binarySearch(sortedSeq, 0, len, n);
            id = id > 0 ? id : -id -1;

            if(id == len)
                len++;
            // 找到了是等值替换,未找分在中间位置还是首尾中间位置三种情况
            // 中间位置/首位置: 直接替换原来的数组，置换后 sortedSeq 中有序序列无含义,但是依然有序
            // 末尾位置：插入有序序列末尾，新增元素作为第id个有序元素
            sortedSeq[id] = n;
        }
        return len;
    }
}
