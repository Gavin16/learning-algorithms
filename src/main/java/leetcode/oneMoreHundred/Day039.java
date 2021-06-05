package leetcode.oneMoreHundred;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * 200. 岛屿数量
 * 给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 输入：grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * 输出：3
 *
 * 463. 岛屿的周长
 * 给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。
 * 网格中的格子 水平和垂直 方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 *
 * 输入：grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
 * 输出：16
 * 解释：它的周长是上面图片中的 16 个黄色的边
 *
 *
 * 695. 岛屿的最大面积
 * 给定一个包含了一些 0 和 1 的非空二维数组 grid 。
 * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。
 * 你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
 *
 * 示例 1:
 *
 *
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。
 *
 *
 */
public class Day039 {

    public static void main(String[] args) {
        char[][] grid = {
                            {'1','1','0','0','0'},
                            {'1','1','0','0','0'},
                            {'0','0','1','0','0'},
                            {'0','0','0','1','1'}
                        };

//        int res = numIslands(grid);
        int res1 = numIslands1(grid);
//        System.out.println(res);
        System.out.println(res1);

        System.out.println("------------------------");
        int[][] input = {{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};

        int ans = islandPerimeter(input);
        System.out.println(ans);


        System.out.println("-----------------------");
        int[][] array = {
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}
        };
        int max = maxAreaOfIsland(array);
        System.out.println(max);

    }


    /**
     * 深度优先搜索
     * 遍历所有网格,对网格中每个元素做深度优先搜索
     */
    public static int numIslands(char[][] grid) {
        int cnt = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                cnt += dfs(grid,i,j);
            }
        }
        return cnt;
    }


    /**
     * 搜索与(i,j) 相邻的所有元素将它设置为'2'
     */
    public static int dfs(char[][] grid, int i , int j){
        if(i >= grid.length || j >=grid[0].length || i < 0 || j < 0){
            return 0;
        }
        if(grid[i][j] != '1'){
            return 0;
        }
        grid[i][j] = '2';
        dfs(grid,i+1,j);
        dfs(grid,i-1,j);
        dfs(grid,i,j+1);
        dfs(grid,i,j-1);
        return 1;
    }


    /**
     * 广度优先搜索
     * 遍历网格中所有元素
     * 若当前元素为'1' 标记为 '2'; 判断当前元素上下左右所有元素是否为 '1' 是则添加队列，不是则跳过
     * 对队列中所有元素做判断，知道队列为空
     */
    public static int numIslands1(char[][] grid) {
        int cnt = 0, rows = grid.length , cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(grid[i][j] == '1'){
                    cnt++;
                    grid[i][j] = '2';
                    Deque<Integer> deque = new LinkedList<>();
                    int pos = i * cols + j;
                    deque.addLast(pos);

                    while(!deque.isEmpty()){
                        // 判断当前元素前后左右是否为 '1' 若为1 则添加到队列
                        Integer first = deque.removeFirst();
                        int r = first / cols;
                        int c = first - r * cols;
                        grid[r][c] = '2';
                        if(r - 1 >= 0 && grid[r-1][c] == '1'){
                            int currPos = (r - 1) * cols + c;
                            deque.addLast(currPos);
                        }
                        if(c - 1 >= 0 && grid[r][c-1] == '1'){
                            int currPos = r * cols  + c - 1;
                            deque.addLast(currPos);
                        }
                        if(r + 1 < rows && grid[r+1][c] == '1'){
                            int currPos = (r + 1) * cols + c;
                            deque.addLast(currPos);
                        }
                        if(c + 1 < cols && grid[r][c+1] == '1'){
                            int currPos = r * cols + c + 1;
                            deque.addLast(currPos);
                        }
                    }
                }
            }
        }
        return cnt;
    }

    /**
     *
     * [
     *   [0,1,0,0],
     *   [1,1,1,0],
     *   [0,1,0,0],
     *   [1,1,0,0]
     *  ]
     *
     */
    public static int islandPerimeter(int[][] grid) {
        int perim = 0, R = grid.length , C = grid[0].length;
        for(int i = 0 ; i < R ; i++){
            for(int j = 0 ; j < C ; j++){
                if(grid[i][j] == 1){
                    perim += dfsAddPer(grid,i,j);
                    break;
                }
            }
        }
        return perim;
    }

    /**
     * 深度优先搜索
     * 遇到边界和0 时周长加+1
     */
    private static int dfsAddPer(int[][] grid , int i , int j){
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0){
            return 1;
        }
        if(grid[i][j] == 2){
            return 0;
        }
        grid[i][j] = 2;
        int p = 0 ;
        p += dfsAddPer(grid,i-1,j);
        p += dfsAddPer(grid,i,j-1);
        p += dfsAddPer(grid,i+1,j);
        p += dfsAddPer(grid,i,j+1);

        return p;
    }


    /**
     * 岛屿最大面积
     * 遍历所有岛屿 ，每个做深度优先遍历
     */
    public static int maxAreaOfIsland(int[][] grid) {
        int rows = grid.length , cols = grid[0].length;
        int max = 0 ;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int area = dfsArea(grid, i, j);
                max = area > max ? area : max;
            }
        }
        return max;
    }


    private static int dfsArea(int[][] grid , int i , int j){
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0){
            return 0;
        }
        int area = 1;
        grid[i][j]  = 0 ;
        area += dfsArea(grid,i+1,j);
        area += dfsArea(grid,i-1,j);
        area += dfsArea(grid,i,j-1);
        area += dfsArea(grid,i,j+1);
        return area;
    }




}
