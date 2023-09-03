package leetcode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @className: NthUglyNumber
 * @description: 264. 丑数 II
 *
 * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
 *
 * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 *
 * 示例 1：
 *
 * 输入：n = 10
 * 输出：12
 * 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：1
 * 解释：1 通常被视为丑数。
 *
 * 提示：
 *
 * 1 <= n <= 1690
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2023/9/3
 */
public class NthUglyNumber {

    public static void main(String[] args) {
//        System.out.println(nthUglyNumber(1));
//        System.out.println(nthUglyNumber(2));
//        System.out.println(nthUglyNumber(3));
//        System.out.println(nthUglyNumber(4));
//        System.out.println(nthUglyNumber(5));
        System.out.println(nthUglyNumber(6));
//        System.out.println(nthUglyNumber(7));
//        System.out.println(nthUglyNumber(8));
//        System.out.println(nthUglyNumber(9));
//        System.out.println(nthUglyNumber(10));
//        System.out.println(nthUglyNumber(11));
//        System.out.println(nthUglyNumber(12));
//
        long st1 = System.currentTimeMillis();
        System.out.println(nthUglyNumber(1690));
        System.out.println("nthUglyNumber 耗时:" + (System.currentTimeMillis() - st1));
        long st2 = System.currentTimeMillis();
        System.out.println(nthUglyNumber1(1690));
        System.out.println("nthUglyNumber1 耗时:" + (System.currentTimeMillis() - st2));
    }

    /**
     * 丑数可以按照 1*2, 1*3,1*5, 然后再重复的取前面已生成的队首元素进行
     * 队首元素*2, 队首元素*3, 队首元素*5 的方式进行 迭代生成
     *
     */
    public static int nthUglyNumber(int n) {
        PriorityQueue<Long> queue = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();
        long ans = 1;
        set.add(ans);
        for(int i = 1; i < n; i++){
            if(!set.contains(ans * 2)){
                set.add(ans * 2);
                queue.offer(ans * 2);
            }
            if(!set.contains(ans * 3)){
                set.add(ans * 3);
                queue.offer(ans * 3);
            }
            if(!set.contains( ans * 5)){
                set.add(ans * 5);
                queue.offer(ans * 5);
            }
            ans = queue.poll();
            set.remove(ans);
        }
        return (int) ans;
    }


    /**
     * 动态规划解法
     * 定义指针p2,p3,p5分别代表需要乘以2，乘以3，乘以5的元素下表
     * 向dp数组中按照顺序存入元素
     * 已知丑数的生成方式为:
     *  1
     *  1*2, 1*3, 1*5
     *  2*2, 2*3, 2*5
     *  3*2, 3*3, 3*5
     *  每次取2*p2, 3*p3, 5*p5 中最小的元素为dp[i]
     *  若发现2*p2, 3*p3, 5*5 中的任意元素与dp[i] 相等，则说明它就是最小元素
     *  若有多个则对应的指针都往下移动
     */
    public static int nthUglyNumber1(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int p2 = 0, p3 = p2, p5 = p2;
        for(int i = 1; i < n; i++){
            dp[i] = Math.min(Math.min(2*dp[p2], 3*dp[p3]), 5*dp[p5]);
            if(dp[i] == 2 * dp[p2]) p2++;
            if(dp[i] == 3 * dp[p3]) p3++;
            if(dp[i] == 5 * dp[p5]) p5++;
        }
        return dp[n-1];
    }

}
