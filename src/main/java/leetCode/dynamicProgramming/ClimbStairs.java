package leetCode.dynamicProgramming;

import java.util.Arrays;

/**
 * 70. 爬楼梯
 *
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 */
public class ClimbStairs {

    public static void main(String[] args) {
        System.out.println(climbStairs4(2));
    }


    /**
     * 版本1: 存在大量重复计算，性能低下
     * @param n
     * @return
     */
    public static int climbStairs1(int n) {
        if(n <= 2){
            return n;
        }else{
            return climbStairs1(n-1) + climbStairs1(n-2);
        }
    }


    /**
     * 版本2：深度优先搜索 使用备忘录避免重复计算
     * @param n
     * @return
     */
    public static int climbStairs2(int n){
        int[] memo = new int[n+1];
        Arrays.fill(memo,-1);
        return dfs(memo,n);
    }

    private static int dfs(int[] memo , int n){
        if(n <= 3){
            return n;
        }else{
            if(memo[n] > -1) return memo[n];
            int num = dfs(memo,n-1) + dfs(memo,n-2);
            memo[n] = num;
            return num;
        }
    }

    /**
     * 版本3：动态规划  使用数组
     * @param n
     * @return
     */
    public static int climbStairs3(int n){
        int[] memo = new int[n+1];
        if(n <= 2) return n;
        memo[1] = 1;
        memo[2] = 2;

        for(int i = 3; i <= n ; i++){
            memo[i] = memo[i-1] + memo[i-2];
        }
        return memo[n];
    }


    /**
     * 版本4 : 动态规划 空间复杂度优化
     * @param n
     * @return
     */
    public static int climbStairs4(int n){
        if(n <= 2) return n;

        int pre1 = 2, pre2 = 1;
        for(int i = 3; i <= n ; i++){
            int curr = pre1 + pre2;
            pre2 = pre1;
            pre1 = curr;
        }
        return pre1;
    }

}
