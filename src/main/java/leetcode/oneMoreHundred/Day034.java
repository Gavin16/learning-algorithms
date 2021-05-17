package leetcode.oneMoreHundred;

/**
 * 62. 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 *
 * 输入：m = 3, n = 7
 * 输出：28
 *
 *
 *
 * 63. 不同路径 II
 *
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 *
 * 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * 输出：2
 * 解释：
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 *
 */
public class Day034 {

    public static void main(String[] args) {
        int m = 3, n = 7;
        System.out.println(uniquePaths(m,n));
        m = 3; n = 2;
        System.out.println(uniquePaths(m,n));

        System.out.println("--------------------网格有障碍物-----------------");
        int[][] obstacleGrid = {{0,0,0},{0,1,0},{0,0,0}};
        System.out.println(uniquePathsWithObstacles1(obstacleGrid));

    }



    public static int uniquePaths(int m, int n) {
//        int ans = dfsWalk(m, n, 1, 1);
//        return ans;
        int[][] dp = new int[m+1][n+1];

        for(int i = m-1 ; i >= 0 ; i--){
            for(int j = n-1; j >= 0 ; j--){
                if(i == m-1 && j == n-1){
                    dp[i][j] = 1;
                }else{
                    dp[i][j] = dp[i+1][j] + dp[i][j+1];
                }
            }
        }
        return dp[0][0];
    }

    private static int dfsWalk(int m, int n, int i, int j){
        if(i > m || j > n) return 0;
        if(i == m && j == n ) return 1;

        int down = dfsWalk(m, n, i + 1, j);
        int right = dfsWalk(m, n, i, j + 1);

        return down + right;
    }


    /**
     * 递归解法
     * @param obstacleGrid
     * @return
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int ans = dfsWalk2(obstacleGrid, 0, 0);
        return ans;
    }


    public static int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        int R = obstacleGrid.length , C = obstacleGrid[0].length;
        int[][]dp = new int[R+1][C+1];
        if(obstacleGrid[R-1][C-1] == 1) return 0;
        for(int r = R-1 ; r >= 0 ; r--){
            for(int c = C-1; c >= 0 ; c--){
                if(r == R-1 && c == C-1){
                    dp[r][c] = 1;
                }else if(obstacleGrid[r][c] == 1){
                    dp[r][c] = 0;
                }else{
                    dp[r][c] = dp[r+1][c] + dp[r][c+1];
                }
            }
        }
        return dp[0][0];
    }

    private static int dfsWalk2(int[][] obstacleGrid, int i,int j){
        if(i >= obstacleGrid.length || j >= obstacleGrid[0].length
                || obstacleGrid[i][j] == 1) return 0;

        if(i == obstacleGrid.length-1 && j == obstacleGrid[0].length - 1) return 1;

        int down = dfsWalk2(obstacleGrid,i+1,j);
        int right = dfsWalk2(obstacleGrid,i,j+1);

        return down + right;
    }
}
