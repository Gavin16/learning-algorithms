package leetCode.oneMoreHundred;

/**
 * @Date: 2020年7月19日
 * ==============================================================================
 * 面试题 08.01. 三步问题
 * ==============================================================================
 * 三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。实现一种方法，
 * 计算小孩有多少种上楼梯的方式。结果可能很大，你需要对结果模1000000007。
 *
 * 示例1:
 *
 *  输入：n = 3
 *  输出：4
 *  说明: 有四种走法
 * 示例2:
 *
 *  输入：n = 5
 *  输出：13
 * ==============================================================================
 * 718. 最长重复子数组
 * ==============================================================================
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 *
 * 示例：
 *
 * 输入：
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出：3
 * 解释：
 * 长度最长的公共子数组是 [3, 2, 1] 。
 * ==============================================================================
 *
 */
public class Day010 {

    public static void main(String[] args) {

    }

    /**
     * @Title: 面试题 08.01. 三步问题
     * @Version: 版本1 动态规划实现
     * 状态定义:
     * dp[i] 代表到第i阶楼梯的走法数
     *
     * 状态转移方程:
     * dp[i] = dp[i-1] + dp[i-2] + dp[i-3]
     *
     *
     * 题目未交待清楚什么时候做求模处理！！
     * @param n
     * @return
     */
    public static int waysToStep(int n) {
        if(n <= 3) return n == 3 ? 4 : ((n==2) ? 2 : 1) ;
        long[] dp = new long[n];
        dp[0] = 1;dp[1] = 2;dp[2] = 4;
        for(int k = 3; k < n ; k++){
            dp[k] = (dp[k-1] + dp[k-2] + dp[k-3])%1000000007;
        }
        return (int)dp[n-1];
    }

    /**
     * @Title:  718. 最长重复子数组
     * @Version:
     * 状态定义:
     * dp[i][j]  定义为以 A[i] 和 B[j] 为末尾元素的最长公共子序列的长度
     * 状态转移方程:
     * dp[i][j] = dp[i-1][j-1] + 1       (若 A[i] = B[j])
     *          = 0                     (若A[i] != B[j])
     *
     * 初始条件:
     * 初始化dp[i][0],和 dp[0][j] (i∈[0,A.length-1] , j∈[0,B.length-1])
     *
     * @param A
     * @param B
     * @return
     */
    public static int findLength(int[] A, int[] B) {
        if(A.length < 1 || B.length < 1) return 0;
        int[][] dp = new int[A.length][B.length];

        for(int i = 0 ; i < A.length ; i ++){
            if(A[i] == B[0]) dp[i][0] = 1;
        }
        for(int j = 0 ; j < B.length ; j++){
            if(A[0] == B[j]) dp[0][j] = 1;
        }

        // 嵌套循环求最长公共子序列长度
        int maxLen = 0;
        for(int k = 1 ; k < A.length ; k++){
            for(int l = 1; l < B.length ; l++){
                if(A[k] == B[l]){
                    dp[k][l] = dp[k-1][l-1] + 1;
                    maxLen = dp[k][l] > maxLen ? dp[k][l] : maxLen;
                }
            }
        }
        return maxLen;
    }


    /**
     * @Title: 718. 最长重复子数组
     * @Version: 版本2  动态规划优化解法
     * @param A
     * @param B
     * @return
     */
    public static int findLength2(int[] A, int[] B) {
        if(A.length < 1 || B.length < 1) return 0;
        int[][] dp = new int[A.length+1][B.length+1];
        int maxLen = 0;
        for(int k = 0 ; k < A.length ; k++){
            for(int l = 0; l < B.length ; l++){
                if(A[k] == B[l]){
                    dp[k+1][l+1] = dp[k][l] + 1;
                    if(dp[k+1][l+1] > maxLen) maxLen = dp[k+1][l+1];
                }
            }
        }
        return maxLen;
    }
}
