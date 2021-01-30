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

        System.out.println("==============");
        System.out.println(day028.chineseChessHorseCases(2,3,3));
        System.out.println(day028.chineseChessHorseCases1(2,3,3));
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


    /**
     * 中国象棋马 从坐标(0,0) 到 坐标(x,y) 的走法数
     *
     * 坐标 x ∈[0,9) , y ∈ [0,10)
     *
     * 暴力递归实现
     *
     * @return
     */
    public int chineseChessHorseCases(int x , int y ,int k){
        return func(x,y,k);
    }

    /**
     * 到坐标 x,y 用 k 步走完，对应的走法数
     * @param x
     * @param y
     * @param k
     * @return
     */
    private int func(int x, int y, int k){
        if(k == 0){
            return (x == 0 && y == 0) ? 1 : 0;
        }
        if(x < 0 || x > 9 || y < 0 || y > 10){
            return 0;
        }

        int steps = func(x-1,y+2, k-1) +
                    func(x+1,y+2,k-1)+
                    func(x+2,y+1,k-1)+
                    func(x+2,y-1,k-1)+
                    func(x+1,y-2,k-1)+
                    func(x-1,y-2,k-1)+
                    func(x-2,y-1,k-1)+
                    func(x-2,y+1,k-1);

        return steps;
    }


    /**
     * 动态规划实现
     * @param x
     * @param y
     * @param k
     * @return
     */
    public int chineseChessHorseCases1(int x , int y ,int k){
        int[][][] dp = new int[9][10][k+1];
        dp[0][0][0] = 1;

        for(int step = 1 ; step <= k ; step++){
            for(int i = 0 ; i < 9 ; i++){
                for(int j = 0 ; j < 10 ; j++){
                    dp[i][j][step] = getRelatedCases(dp,i-1,j+2,step-1)+
                            getRelatedCases(dp,i+1,j+2,step-1)+
                            getRelatedCases(dp,i+2,j+1,step-1)+
                            getRelatedCases(dp,i+2,j-1,step-1)+
                            getRelatedCases(dp,i+1,j-2,step-1)+
                            getRelatedCases(dp,i-1,j-2,step-1)+
                            getRelatedCases(dp,i-2,j-1,step-1)+
                            getRelatedCases(dp,i-2,j+1,step-1);
                }
            }
        }
        return dp[x][y][k];
    }

    private int getRelatedCases(int[][][] dp, int x, int y, int step) {
        if(x < 0 || x >= 9 || y < 0 || y >= 10){
            return 0;
        }
        return dp[x][y][step];
    }

}
