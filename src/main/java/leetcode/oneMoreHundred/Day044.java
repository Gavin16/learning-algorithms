package leetcode.oneMoreHundred;

import com.alibaba.fastjson.JSON;

/**
 * 深度优先搜索: 1/10 ~ 2/10
 *
 * 《200. 岛屿数量》
 *
 * 给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 示例 1：
 * 输入：grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * 输出：1
 * 示例 2：
 *
 * 输入：grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * 输出：3
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 *
 *
 *
 * 《130. 被围绕的区域》
 *
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充
 *
 * 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的'O'都不会被填充为'X'。
 * 任何不在边界上，或不与边界上的'O'相连的'O'最终都会被填充为'X'。
 * 如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * 示例 2：
 *
 * 输入：board = [["X"]]
 * 输出：[["X"]]
 *
 *
 * 提示：
 *
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] 为 'X' 或 'O'
 *
 */
public class Day044 {

    /**
     *  解法:
     *  找出所有边界上的'O', 挨个做深度优先搜索
     *  若上下左右的元素为'O',则执行dfs
     *  并将发现的元素置为 'P'
     *
     *  回过头来将所有'O'置为'X',所有'P'恢复为'O'
     */
    public void solve(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        for(int i = 0 ; i < rows ; i++){
            for(int j = 0 ; j < cols ; j++){
                if(i==0 || i==rows-1 || j==0 || j==cols-1){
                    roundAreaDfs(board,i,rows,j,cols);
                }
            }
        }
        // 更新元素
        for(int i = 0 ; i < rows ; i++){
            for(int j = 0 ; j < cols ; j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }else if(board[i][j] == 'P'){
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void roundAreaDfs(char[][]board, int i, int rows, int j, int cols){
        if(i >= 0 && i < rows && j >=0 && j < cols){
            if(board[i][j] == 'O'){
                board[i][j] = 'P';
                roundAreaDfs(board,i-1,rows,j,cols);
                roundAreaDfs(board, i+1, rows, j, cols);
                roundAreaDfs(board, i, rows, j-1, cols);
                roundAreaDfs(board, i, rows, j+1, cols);
            }
        }
    }

    /**
     * 深度优先搜索
     */
    public int numIslands(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int isLands = 0;
        for(int i = 0 ; i < rows ; i++){
            for(int j = 0 ; j < cols; j++){
                isLands += dfs(grid, i, rows, j, cols);
            }
        }
        return isLands;
    }

    /**
     * 深度优先搜索 & 翻转字符
     *
     * 需判断方框边界
     */
    private int dfs(char[][]grid, int i ,int rows, int j, int cols){
        if((i >= 0 && i < rows) && (j >= 0 && j < cols)){
            if(grid[i][j] == '1'){
                grid[i][j]  = '0';
                dfs(grid, i-1, rows, j, cols);
                dfs(grid, i+1, rows, j, cols);
                dfs(grid, i, rows, j-1, cols);
                dfs(grid, i, rows, j+1, cols);
                return 1;
            }
        }
        return 0;
    }


    public static void main(String[] args) {
//        char[][] grid = {{'1','1','0','0','0'},
//                        {'1','1','0','0','0'},
//                        {'0','0','1','0','0'},
//                        {'0','0','0','1','1'}};
//
//        Day044 instance = new Day044();
//        int num = instance.numIslands(grid);
//
//        System.out.println(num);
//        for(char[] chars : grid){
//            System.out.println(JSON.toJSON(chars));
//        }

        char[][] board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        Day044 instance = new Day044();
        instance.solve(board);
        for(char[] chars : board){
            System.out.println(JSON.toJSON(chars));
        }

    }

}
