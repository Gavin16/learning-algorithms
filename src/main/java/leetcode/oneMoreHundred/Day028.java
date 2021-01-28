package leetcode.oneMoreHundred;


import utils.ArrayUtil;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 算法训练营：
 * 第1节: 单调栈和窗口及其更新结构
 * ==============================================================================
 * 剑指 Offer 59 - I. 滑动窗口的最大值
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 *
 * ==============================================================================
 *
 *
 *
 * ==============================================================================
 *
 */
public class Day028 {


    public static void main(String[] args) {
        Day028 day028 = new Day028();
        int[] nums0 = new int[]{1,3,-1,-3,5,3,6,7};
        int[] nums1 = new int[]{1,3,-1,-3,5,3,6,7};
        int[] ints0 = day028.maxSlidingWindow(nums0, 3);
        int[] ints1 = day028.maxSlidingWindow1(nums1, 3);
        ArrayUtil.showArray(ints0);
        ArrayUtil.showArray(ints1);
    }





    /**
     * 剑指 Offer 59 - I. 滑动窗口的最大值
     *
     * 简单双指针解法
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(k < 1 || nums.length < 1 || k > nums.length){return new int[]{};}
        int[] res = new int[nums.length - k + 1];
        for(int l = 0 , r = k -1; r < nums.length ; l++,r++){
            int max = nums[l];
            for(int i = l ; i <= r ; i++){
                if(nums[i] > max) max = nums[i];
            }
            res[l] = max;
        }
        return res;
    }


    /**
     * 剑指 Offer 59 - I. 滑动窗口的最大值
     * 滑动窗 + 双端队列 解法
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow1(int[] nums, int k) {
        if(k < 1 || nums.length < 1 || k > nums.length) return null;
        Deque<Integer> deque = new LinkedList();
        int l = 0;
        int[] res = new int[nums.length - k + 1];
        for(int r = 0 ; r < nums.length ; r++){
            while(!deque.isEmpty() && nums[deque.getLast()] < nums[r]){
                deque.removeLast();
            }
            deque.addLast(r);
            if(r - l == k-1){
                Integer first = deque.getFirst();
                if(first == l) deque.removeFirst();
                res[l++] = nums[first];
            }
        }
        return res;
    }

}
