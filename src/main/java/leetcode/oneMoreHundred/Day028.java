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


    /**
     * 对于输入数组 int[] nums 和 数值 n
     * 求数组中 nums数组中的所有子数组中满足  子数组中最大值 - 子数组中最小值 <= n 的数量
     *
     * 滑动窗 + 两个双端队列    求解
     *
     * 分析:
     * 对于任意数组，arr[L..R] 范围内若满足  max - min <= n
     * 那么 arr[L..R]的子数组同样满足 max'-min' <= n
     *
     * 类似的，对于任意数组 arr[L..R] 范围内，若 max -min > n
     * 那么包括 arr[L..R] 范围比 arr[L..R]大的数组同样满足 max'' - min'' > n
     *
     * 可以得出结论:
     * 使用固定一端(下标l)，滑动另一端(下标r)的窗口，当窗口右端滑动到第一次 max - min > n 时，
     * 那么此前所有以l开头子数组，都满足 max - min <= n
     *
     *
     */
    public int subArrayCount(int[] nums, int k) {
        int count = 0 ,l = 0 , r = 0;
        Deque<Integer> maxQueue = new LinkedList(),minQueue = new LinkedList();
        while(l < nums.length){
            while(r < nums.length) {
                while (!maxQueue.isEmpty() && nums[maxQueue.getLast()] < nums[r]) {
                    maxQueue.removeLast();
                }
                while (!minQueue.isEmpty() && nums[minQueue.getLast()] > nums[r]) {
                    minQueue.removeLast();
                }
                maxQueue.addLast(r);
                minQueue.addLast(r);
                if (nums[maxQueue.getFirst()] - nums[minQueue.getFirst()] > k) {
                    break;
                }
                //
                r++;
            }
            count += r - l;
            // 更新过期窗口内容
            if(minQueue.getFirst() == l){
                minQueue.removeFirst();
            }
            if(maxQueue.getFirst() == l){
                maxQueue.removeFirst();
            }
            l++;
        }
        return count;
    }



}
