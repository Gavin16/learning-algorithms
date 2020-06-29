package leetCode;

/**
 *
 */
public class MaxDistance {

    public static void main(String[] args) {
        int[][] grids = {{1,0,0},{0,0,0},{0,0,0}};
        MaxDistance obj = new MaxDistance();
        int sum = 0 ;

        endToSum:
        for(int i = 0 ; i < 10 ; i++){
            for(int j = 0 ; j < 10 ; j++){
                sum += (i*10 + j);
                if(sum > 100) break endToSum;
            }
        }
        System.out.println(sum);
    }

    /**
     * 左上到右下遍历 + 右下到左上遍历  双遍历搜索实现
     */
    public int maxDistance(int[][] grid) {
        if(onlyContainsOneArea(grid)) return -1;

        int rows = grid.length , cols = grid[0].length;

        int[][] dis1 = new int[grid.length][grid[0].length];
        int[][] dis2 = new int[grid.length][grid[0].length];

        bfs(dis1,0,0,rows,cols,grid);
        bfs(dis1,rows,cols,0,0,grid);

        return -1;
    }

    private void bfs(int[][] dis1, int r, int c, int rows, int cols, int[][] grid) {

    }

    private boolean onlyContainsOneArea(int[][] grid) {
        int rows = grid.length;
        int columns = grid.length,sum = 0;
        for(int[] row : grid){
            for(int n : row)
                sum += n;
        }
        return ( sum == (rows*columns) || sum == 0 ) ? true : false;
    }
}
