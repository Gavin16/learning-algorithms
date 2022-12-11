package algorithmsContest.charpt1;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 1.2 队列
 *
 * @className: MaxSum
 * @description:
 * 例1.4 Max Sum
 * 问题描述: 给定一个序列, 求最大子序和;
 * 分两种情况
 * (1) 不限定子序列的长度，在所有可能的子序列中，找出一个子序列，使该子序列和最大
 *     对应解法: 贪心算法、动态规划
 *
 * (2) 限定子序列的长度，给定一个限制长度 m, 找出一段长度不超过 m 的连续子序列，使它的子序和最大
 *     对应解法: 使用单调队列求解
 *
 *
 * 练习题:
 * (1) 单调队列简单题: 洛谷 P1440/P2032/P1714/P2629/P2422/P1540
 * (2) 单调队列优化DP: 洛谷 P3957/P1725
 * (3) 二维队列： 洛谷 P2776
 *
 *
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2022/12/4
 */
public class MaxSum {

    public static void main(String[] args) {
        MaxSum maxSum = new MaxSum();
        int[] a = {6,-1,5,4,-7,3,2,8,6,-11,50,-35};
//        System.out.println(maxSum.solution1(a));
//        System.out.println(maxSum.solution2(a));

        System.out.println(maxSum.solution3(3,a));
    }

    /**
     * 贪心算法实现
     * 维护一个最大值和值 maxSum
     * 逐个累加元素，当判断 sum > maxSum 时更新maxSum
     *             当 sum < 0 时，从下一个位置重新开始累加(因为即使下一位置值很大，和还是会小于单个值)
     *
     * @param a
     * @return
     */
    public int solution1(int[] a){
        int len = a.length;
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for(int i = 0; i < len; i++){
            sum += a[i];
            if(sum > maxSum) maxSum = sum;
            if(sum < 0) sum = 0;
        }
        return maxSum;
    }

    /**
     * 使用动态规划求解
     * 定义 dp[i] 为以 i 位置作为结束的最大和
     * 则dp[i] 可能有两种情况:
     *   (1)dp[i] 序列只有一个元素 即dp[i] = a[i]
     *   (2)dp[i] 包含多个元素也即 dp[i] = dp[i-1] + a[i]
     * 所有可能中, 使得dp[i] 最大的值即为所求
     *   因此: dp[i] = max(a[i], dp[i-1] + a[i])
     *
     * @param a
     * @return
     */
    public int solution2(int[] a){
        int maxSum = Integer.MIN_VALUE;
        if(a.length < 1) return maxSum;

        int len = a.length, dp = 0;
        for(int i = 0; i < len; i++){
            dp = Math.max(a[i], dp + a[i]);
            if(dp > maxSum) maxSum = dp;
        }
        return maxSum;
    }


    /**
     * 单调队列求解
     *
     * @param k
     * @param a
     * @return
     */
    public int solution3(int k , int[] a){
        int len = a.length;
        int[] s = new int[len + 1];
        for(int i = 1; i <= len; i++)
            s[i] = s[i - 1] + a[i - 1];
        // 对序列 s 应用滑动窗+单调队列
        Deque<Integer> deque = new LinkedList<>();
        deque.addLast(0);
        int maxSum = Integer.MIN_VALUE;
        for(int i = 1; i <= len ; i++){
            while(!deque.isEmpty() && s[deque.getLast()] >= s[i])
                deque.removeLast();
            deque.addLast(i);

            // 这里判断是否超出窗口与范围,需要判断id 是否 小于 i-k
            // 因为对于前缀和序列,当 id = i - k 时, s[i] - s[id] 对应窗口长度正好为k
            while(!deque.isEmpty() && deque.getFirst() < i - k)
                deque.removeFirst();
            if(deque.isEmpty()) maxSum = Math.max(maxSum, s[i]);
            else maxSum = Math.max(maxSum, s[i] - s[deque.getFirst()]);
        }
        return maxSum;
    }

}
