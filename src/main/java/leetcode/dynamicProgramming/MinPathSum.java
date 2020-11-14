package leetcode.dynamicProgramming;


import java.util.Arrays;

/**
 * 《64. 最小路径和》
 *
 *  给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 * 示例:
 *
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 *
 */
public class MinPathSum {


    public static void main(String[] args) {
        MinPathSum minPathSum = new MinPathSum();
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        int res = minPathSum.minPathSum(grid);

        System.out.println(res);
    }

    /**
     * DP 解法的状态转移方程为：
     * MinPath(m,n) = Math.min( MinPath(m,n-1) , MinPath(m-1,n) )
     *
     */
    public int minPathSum(int[][] grid) {
        dp = new int[grid.length][grid[0].length];
        for(int[] arr : dp){
            Arrays.fill(arr,-1);
        }
        return iterPathSearch(grid,grid.length - 1,grid[0].length -1);
    }


    private int[][] dp;

    /**
     * 递归并使用dp数组保存
     */
    private int dfsPathSearch(int[][] grid , int i , int j){
        if(i == 0 && j == 0) return grid[i][j];
        if(dp[i][j] > 0)  return dp[i][j];

        int val = grid[i][j];
        if(i == 0){
            val += dfsPathSearch(grid,i,j-1);
        }else if(j == 0){
            val += dfsPathSearch(grid,i-1,j);
        }else{
            val += Math.min(dfsPathSearch(grid, i-1, j),dfsPathSearch(grid, i, j-1));
        }
        dp[i][j] = val;
        return val;
    }

    /**
     *  循环迭代求解
     */
    private int iterPathSearch(int[][]grid, int m , int n){

        dp[0][0] = grid[0][0];
        for(int i = 0 ; i <= m ; i++){
            for(int j = 0; j <= n ; j++){
                if(i == 0){
                    dp[i][j] = grid[i][j] + (j == 0 ? 0 : dp[i][j-1]);
                }else if(j == 0){
                    dp[i][j] = grid[i][j] + (i == 0 ? 0 : dp[i-1][j]);
                }else{
                    dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]) + grid[i][j];
                }
            }
        }
        return dp[m][n];
    }
}
