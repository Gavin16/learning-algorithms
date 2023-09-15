package leetcode.oneMoreHundred;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static java.util.Comparator.reverseOrder;

/**
 * @className: Day053
 * @description:
 * @version: 1.0
 * @author: minsky
 * @date: 2023/9/10
 */
public class Day053 {

    public static void main(String[] args) {
        Day053 day053 = new Day053();
        int[] arr1 = {3,0,6,1,5};
        int hIndex1 = day053.hIndex(arr1);
        System.out.println(hIndex1);

        int[] arr2 = {1,3,1};
        int hIndex2 = day053.hIndex(arr2);
        System.out.println(hIndex2);

        int[] arr3 = {0,0,0};
        int hIndex3 = day053.hIndex(arr3);
        System.out.println(hIndex3);

        int[] arr4 = {4,3,5,5,5,2,4,6};
        int hIndex4 = day053.hIndex(arr4);
        System.out.println(hIndex4);

        System.out.println(day053.integerBreak(2));
        System.out.println(day053.integerBreak(10));
        System.out.println(day053.integerBreak(18));
        System.out.println(day053.integerBreak(27));
        System.out.println(day053.integerBreak(58));

    }

    /**
     * 274. H 指数
     *
     * 给你一个整数数组 citations ，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数。计算并返回该研究者的 h 指数。
     * 根据维基百科上 h 指数的定义：h 代表“高引用次数” ，一名科研人员的 h 指数 是指他（她）至少发表了 h 篇论文，并且每篇论文
     * 至少 被引用 h 次。如果 h 有多种可能的值，h 指数 是其中最大的那个。
     *
     * 示例 1：
     *
     * 输入：citations = [3,0,6,1,5]
     * 输出：3
     * 解释：给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 3, 0, 6, 1, 5 次。
     *      由于研究者有 3 篇论文每篇 至少 被引用了 3 次，其余两篇论文每篇被引用 不多于 3 次，所以她的 h 指数是 3。
     * 示例 2：
     *
     * 输入：citations = [1,3,1]
     * 输出：1
     *
     * 提示：
     * n == citations.length
     * 1 <= n <= 5000
     */
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        reverse(citations);

        int h = 0;
        for(int i = 0; i < citations.length; i++){
            h = Math.max(Math.min(i+1, citations[i]), h);
        }
        return h;
    }

    private void reverse(int[] arr){
        for(int i=0, j = arr.length-1; i < j; i++, j--){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    /**
     * 343. 整数拆分
     *
     * 给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。
     * 返回 你可以获得的最大乘积 。
     *
     * 示例 1:
     *
     * 输入: n = 2
     * 输出: 1
     * 解释: 2 = 1 + 1, 1 × 1 = 1。
     * 示例 2:
     *
     * 输入: n = 10
     * 输出: 36
     * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
     *
     * 提示:
     *
     * 2 <= n <= 58
     */
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0; dp[1] = 0;
        for(int i = 2; i <= n; i++){
            int max = 0;
            for(int j = 1; j < i; j++){
                max = Math.max(max,
                        Math.max(j * (i - j), j * dp[i - j]));
            }
            dp[i] = max;
        }
        return dp[n];
    }



}
