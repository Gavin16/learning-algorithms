package leetcode.byteDance;

/**
 * @className: Day14
 * @description: TODO
 * @version: 1.0
 * @author: minsky
 * @date: 2022/9/4
 */
public class Day14 {


    public static void main(String[] args) {
        Day14 day14 = new Day14();
//        int[][] grid = {{1,1,1,1,1,1,1,0},
//                        {1,0,0,0,0,1,1,0},
//                        {1,0,1,0,1,1,1,0},
//                        {1,0,0,0,0,1,0,1},
//                        {1,1,1,1,1,1,1,0}};
//
//        int[][] grid1 = {{1,1,1,1,1,1,1},
//                        {1,0,0,0,0,0,1},
//                        {1,0,1,1,1,0,1},
//                        {1,0,1,0,1,0,1},
//                        {1,0,1,1,1,0,1},
//                        {1,0,0,0,0,0,1},
//                        {1,1,1,1,1,1,1}};
//        int[][]grid2 = {{1,1},{0,0}};
//        int[][]grid3 = {{0,1},{1,0}};
//        System.out.println(day14.closedIsland(grid2));
//        System.out.println(day14.closedIsland(grid3));

//        int[] arr = new int[]{1,2,3,4,5,3,1};
//        int[] arr = new int[]{0,1,2,4,2,1};
        int[] arr = new int[]{3,5,3,2,0};
        MountainArray mountainArray = new MountainArray(arr);
        System.out.println(day14.findInMountainArray(3, mountainArray));


    }


    /**
     * 1254. 统计封闭岛屿的数目
     *
     * 二维矩阵 grid 由 0 （土地）和 1 （水）组成。
     * 岛是由最大的4个方向连通的 0 组成的群，封闭岛是一个 完全 由1包围（左、上、右、下）的岛。
     * 请返回 封闭岛屿 的数目。
     *
     * 输入：grid = [[1,1,1,1,1,1,1,0],
     *              [1,0,0,0,0,1,1,0],
     *              [1,0,1,0,1,1,1,0],
     *              [1,0,0,0,0,1,0,1],
     *              [1,1,1,1,1,1,1,0]]
     * 输出：2
     * 解释：
     * 灰色区域的岛屿是封闭岛屿，因为这座岛屿完全被水域包围（即被 1 区域包围）。
     *
     * 输入：grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
     * 输出：1
     * 示例 3：
     *
     * 输入：grid = [[1,1,1,1,1,1,1],
     *              [1,0,0,0,0,0,1],
     *              [1,0,1,1,1,0,1],
     *              [1,0,1,0,1,0,1],
     *              [1,0,1,1,1,0,1],
     *              [1,0,0,0,0,0,1],
     *              [1,1,1,1,1,1,1]]
     * 输出：2
     * 提示：
     *
     * 1 <= grid.length, grid[0].length <= 100
     * 0 <= grid[i][j] <=1
     *
     * 提示：
     * 1 <= grid.length, grid[0].length <= 100
     * 0 <= grid[i][j] <=1
     */
    public int closedIsland(int[][] grid) {
        int row  = grid.length;
        int col = grid[0].length;
        int cnt = 0;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(grid[i][j] == 0){
                    boolean dfs = dfs(grid, i, j);
                    if(dfs) cnt += 1;
                }
            }
        }
        return cnt;
    }


    private boolean dfs(int[][] grid, int i, int j) {
        if((i == 0 || i == grid.length - 1 ||
                j == 0 || j == grid[0].length - 1) && grid[i][j] == 0 ) return false;

        if(grid[i][j] == 0){
            grid[i][j] = 2;
            boolean up = dfs(grid, i - 1, j);
            boolean down = dfs(grid, i + 1, j);
            boolean left = dfs(grid, i, j - 1);
            boolean right = dfs(grid, i, j + 1);
            return up && down && left && right;
        }else{
            return true;
        }
    }


    /**
     * 1095. 山脉数组中查找目标值
     *
     * 找出最大值的位置 mp
     * 搜索 0 到 mp 范围内
     * 若 0 到 mp 范围内没有 target,则搜索 mp+1, len-1 范围
     * 以上过程有则返回，都没有则返回 -1
     *
     */
    public int findInMountainArray(int target, MountainArray mountainArr) {
        if(mountainArr.length() < 3) return -1;
        int low = 0, high = mountainArr.length() - 1;
        int mid  = low + (high - low) >> 1;
        while(low < high){
            if(mountainArr.get(mid) > mountainArr.get(mid - 1)
                    && mountainArr.get(mid + 1) > mountainArr.get(mid)){
                low = mid + 1;
            }else if(mountainArr.get(mid-1) > mountainArr.get(mid)
                    && mountainArr.get(mid) > mountainArr.get(mid+1)){
                high = mid - 1;
            }else break;
            mid  = low + ((high - low) >> 1);
        }
        //搜索 0 到 mid
        int s1 = 0, t1 = mid, m1 = s1 + ((t1 - s1) >> 1);
        while(s1 <= t1){
            if(mountainArr.get(m1) < target)  s1 = m1 + 1;
            else if(mountainArr.get(m1) > target) t1 = m1 - 1;
            else return m1;
            m1 = s1 + ((t1 - s1) >> 1);
        }
        // 搜索 mid 到 len - 1
        int s2 = mid, t2 = mountainArr.length() - 1, m2 = s2 + ((t2 - s2) >> 1);
        while(s2 <= t2){
            if(mountainArr.get(m2) < target)  t2 = m2 - 1;
            else if(mountainArr.get(m2) > target) s2 = m2 + 1;
            else return m2;
            m2 = s2 + ((t2 - s2) >> 1);
        }
        return -1;
    }

    static class MountainArray {

        int[] arr;

        public MountainArray(int[] arr) {
            this.arr = arr;
        }
        public int get(int index) {
            return arr[index];
        }
        public int length() {
            return arr.length;
        }
    }


}


