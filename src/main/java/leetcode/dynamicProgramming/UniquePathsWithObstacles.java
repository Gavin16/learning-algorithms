package leetcode.dynamicProgramming;


/**
 *
 * 《63. 不同路径 II》
 *
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 *
 *
 *
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 *
 * 说明：m 和 n 的值均不超过 100。
 *
 * 示例 1:
 *
 * 输入:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * 输出: 2
 * 解释:
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 *
 */
public class UniquePathsWithObstacles {


    public static void main(String[] args) {
        UniquePathsWithObstacles obj = new UniquePathsWithObstacles();
        int[][] grids = {{0,0,0},{0,1,0},{0,0,0}};

        System.out.println(obj.uniquePathsWithObstacles(grids));
    }

    private int[][] pathNums;

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(null == obstacleGrid || obstacleGrid.length == 0) return 0;
        int rows = obstacleGrid.length;
        int columns = obstacleGrid[0].length;
        if(obstacleGrid[rows-1][columns-1] == 1) return 0;

        pathNums = new int[rows][columns];
//        for(int i = 0 ; i < rows ; i++){
//            Arrays.fill(pathNums[i],-1);
//        }
        return iterFindTotalPathNum(obstacleGrid,rows-1,columns-1);
    }

    // 迭代处理，状态转移方程为： num[m][n] = num[m-1][n] + num[m][n-1]
    // m-1和 n-1 的前提条件是 m > 0 ，n > 0 ，另外所有方格不能处于障碍物上
    private int iterFindTotalPathNum(int[][]grid, int m ,int n){
        if(n==1&&m==1&&grid[0][0]==0)return 1;
        if(grid[0][0]==1)return 0;
        // 初始化第一行和第一列
        pathNums[0][0] = grid[0][0] == 0 ? 1 : 0;
        for(int i = 1 ; i <= n ; i++){
            if(grid[0][i] == 1) break;
            else{
                pathNums[0][i] = pathNums[0][i-1];
            }
        }
        for(int k = 1 ; k <= m ; k++){
            if(grid[k][0] == 1) break;
            else{
                pathNums[k][0] = pathNums[k-1][0];
            }
        }

        for(int i = 1 ; i <= m ; i ++){
            for(int j = 1 ; j <= n ; j++){
                if(grid[i][j] != 1){
                    pathNums[i][j] = pathNums[i-1][j] + pathNums[i][j-1];
                }
            }
        }
        return pathNums[m][n];
    }

    private int dfsFindDiffPaths(int[][] grid , int m, int n){
        if(m == 0 && n == 0) return 1;

        if(pathNums[m][n] > 0) return pathNums[m][n];

        int leftNum =  (n == 0 || grid[m][n-1] == 1) ? 0 : dfsFindDiffPaths(grid,m,n-1);
        int rightNum = (m == 0 || grid[m-1][n] == 1) ? 0 : dfsFindDiffPaths(grid, m-1, n);

        pathNums[m][n] = leftNum + rightNum;

        return pathNums[m][n];
    }
}
