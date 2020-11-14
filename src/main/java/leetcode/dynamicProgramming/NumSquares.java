package leetcode.dynamicProgramming;

/**
 * @Class: NumSquares
 * @Description:
 *
 * 《LeetCode 279. 完全平方数》
 *
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 示例 1:
 *
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * 示例 2:
 *
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 *
 *
 * @Author: Minsky
 * @Date: 2019/8/7 14:48
 * @Version: v1.0
 */
public class NumSquares {

    public static void main(String[]args){
        System.out.println(numSquares(12));
    }

    /**
     * 状态转移方程为
     *      dp[i] = min(dp[i] , dp[i-j*j])
     * @param n
     * @return
     */
    public static int numSquares(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 0 ; i <=n ; i++){
            dp[i] = i ;
            for(int j = 0 ; i -j*j >=0 ; j++){
                dp[i] = Math.min(dp[i], dp[i-j*j] + 1);
            }
        }

        return dp[n];
    }
}
