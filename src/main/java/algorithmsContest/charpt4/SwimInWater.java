package algorithmsContest.charpt4;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @className: SwimInWater
 * @description:
 * LeetCode 778. 水位上升的泳池中游泳
 * 在一个 n x n 的整数矩阵 grid 中，每一个方格的值 grid[i][j] 表示位置 (i, j) 的平台高度。
 * 当开始下雨时，在时间为 t 时，水池中的水位为 t 。你可以从一个平台游向四周相邻的任意一个平台，
 * 但是前提是此时水位必须同时淹没这两个平台。假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。
 * 当然，在你游泳的时候你必须待在坐标方格里面。
 * 你从坐标方格的左上平台 (0，0) 出发。返回 你到达坐标方格的右下平台 (n-1, n-1) 所需的最少时间 。
 *
 * 输入: grid = [[0,2],[1,3]]
 * 输出: 3
 * 解释:
 * 时间为0时，你位于坐标方格的位置为 (0, 0)。
 * 此时你不能游向任意方向，因为四个相邻方向平台的高度都大于当前时间为 0 时的水位。
 * 等时间到达 3 时，你才可以游向平台 (1, 1). 因为此时的水位是 3，坐标方格中的平台没有比水位 3 更高的，所以你可以游向坐标方格中的任意位置
 *
 *
 * 输入: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
 * 输出: 16
 * 解释: 最终的路线用加粗进行了标记。
 * 我们必须等到时间为 16，此时才能保证平台 (0, 0) 和 (4, 4) 是连通的
 *
 * 提示:
 *
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 50
 * 0 <= grid[i][j] < n2
 * grid[i][j] 中每个值 均无重复
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2023/5/28
 */
public class SwimInWater {

    public static void main(String[] args) {
        SwimInWater instance = new SwimInWater();

        int[][] grid = {
                {0,1,2,3,4},
                {24,23,22,21,5},
                {12,13,14,15,16},
                {11,17,18,19,20},
                {10,9,8,7,6}};

        int waitTime = instance.swimInWater(grid);
        System.out.println(waitTime);

        int[][] grid1 = {
                {3,2},
                {0,1}
        };
        int waitTime1 = instance.swimInWater(grid1);
        System.out.println(waitTime1);

        int[][] grid2 = {
                {5,3,2,3},
                {4,5,6,2},
                {7,6,0,3},
                {8,3,9,4},
        };
        int waitTime2 = instance.swimInWater(grid2);
        System.out.println(waitTime2);

        int[][] grid3 = {
                {10,12,4,6},
                {9,11,3,5},
                {1,7,13,8},
                {2,0,15,14}
        };
        System.out.println(instance.swimInWater(grid3));

        int[][] grid4 = {{9,5,7,2},{0,10,8,15},{1,4,3,12},{6,13,11,14}};
        System.out.println(instance.swimInWater(grid4));
    }

    /**
     * 使用二分查找 + DFS求解
     * @param grid
     * @return
     */
    public int swimInWater(int[][] grid) {
        int N = grid.length;
        int left = 0, right = N * N - 1;
        while(left < right){
            int mid = (left + right) / 2;
            visit = new boolean[N][N];
            if(grid[0][0] <= mid
                    && targetReachable(grid,visit,mid, 0, 0)){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return left;
    }
    int[][] DIRS = {{0,1},{0,-1},{1,0},{-1,0}};
    boolean[][] visit;

    private boolean targetReachable(int[][] grid, boolean[][] visit,
                                    int threshold, int i, int j) {
        int N = grid.length;
        visit[i][j] = true;
        for(int[] dir : DIRS){
            int ni = i + dir[0], nj = j + dir[1];
            if(ni >= 0 && ni < N && nj >= 0 && nj < N && !visit[ni][nj] && grid[ni][nj] <= threshold){
                if(ni == N - 1 && nj == N - 1) return true;
                if(targetReachable(grid,visit,threshold, ni, nj)){
                    return true;
                }
            }
        }
        return false;
    }


}
