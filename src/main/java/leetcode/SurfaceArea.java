package leetcode;
/**
 * 892. 三维形体的表面积
 *
 *
 * 在 N * N 的网格上，我们放置一些 1 * 1 * 1  的立方体。
 *
 * 每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。
 *
 * 请你返回最终形体的表面积。
 *
 *
 * 示例 1：
 *
 * 输入：[[2]]
 * 输出：10
 * 示例 2：
 *
 * 输入：[[1,2],[3,4]]
 * 输出：34
 * 示例 3：
 *
 * 输入：[[1,0],[0,2]]
 * 输出：16
 * 示例 4：
 *
 * 输入：[[1,1,1],[1,0,1],[1,1,1]]
 * 输出：32
 * 示例 5：
 *
 * 输入：[[2,2,2],[2,1,2],[2,2,2]]
 * 输出：46
 *
 */
public class SurfaceArea {

    int nearbyArea = 0;
    boolean[][] flags;
    public int surfaceArea(int[][] grid) {
        // 计算单独每隔立方体表面积和
        int area =  0;
        for(int i = 0 ; i < grid .length ; i ++){
            for(int n : grid[i]){
                if(n > 0){
                    area += 2 + 4*n;
                }
            }
        }
        flags = new boolean[grid.length][grid[0].length];
        // 计算相邻被覆盖的表面积
        dfsFindNearbyArea(grid,grid.length-1,grid[0].length-1,0,0,1,0);
        dfsFindNearbyArea(grid,grid.length-1,grid[0].length-1,0,0,0,1);

        return area - 2*nearbyArea;
    }

    private void dfsFindNearbyArea(int[][] grid,int m ,int n, int i1, int j1, int i2, int j2) {

        int min = Math.min(grid[i1][j1],grid[i2][j2]);
        nearbyArea += min > 0 ? min : 0;

        if(!flags[i2][j2]){

            flags[i2][j2] = true;
            if(i2 < m){
                dfsFindNearbyArea(grid, m, n, i2, j2, i2+1, j2);
            }
            if(j2 < n){
                dfsFindNearbyArea(grid, m, n, i2, j2,i2,j2+1);
            }
        }

    }


    public static void main(String[] args) {
        int[][] test = {{2,2,2},{2,1,2},{2,2,2}};
        System.out.println(new SurfaceArea().surfaceArea(test));
    }
}
