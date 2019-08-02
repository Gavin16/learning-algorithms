package leetCode;

/**
 * @Class: GameOfLife
 * @Description:
 *
 * 《LeetCode 289. 生命游戏》
 *  根据百度百科，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在1970年发明的细胞自动机。
 *
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞具有一个初始状态 live（1）即为活细胞， 或 dead（0）即为死细胞。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 *
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * 根据当前状态，写一个函数来计算面板上细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
 *
 * 示例:
 *
 * 输入:
 * [
 *   [0,1,0],
 *   [0,0,1],
 *   [1,1,1],
 *   [0,0,0]
 * ]
 * 输出:
 * [
 *   [0,0,0],
 *   [1,0,1],
 *   [0,1,1],
 *   [0,1,0]
 * ]
 *
 *
 * @Author: Minsky
 * @Date: 2019/8/1 16:08
 * @Version: v1.0
 */
public class GameOfLife {

    public static void main(String[]args){
        int[][] board = new int[][]{{0,1,0},{0,0,1},{1,1,1},{0,0,0}};

        gameOfLife(board);

    }


    public static void gameOfLife(int[][] board) {
        int r = board.length;
        int c = board[0].length;

        int[][] res = new int[r][c];

        for(int i = 0 ; i < r ; i++ ){
            for(int j = 0; j < c ; j++){

                res[i][j] = getState(board,i,j);
            }
        }

        for(int i = 0 ; i < r ; i++ ) {
            for (int j = 0; j < c; j++) {
                board[i][j] = res[i][j];
            }
        }
    }

    private static int getState(int[][] board, int i , int j){
        int cnt = 0 ;
        int rs = board.length;
        int cs = board[0].length;

        int val = board[i][j];

        int[] ia = new int[]{-1,-1,-1,0,0,1,1,1};
        int[] ja = new int[]{-1,0,1,-1,1,-1,0,1};

        for(int k = 0; k < ja.length ; k++){
            if(i+ia[k] >= 0 && i+ia[k] < rs && j+ja[k] >= 0 && j+ja[k] <cs ){
                int temp = board[i+ia[k]][j+ja[k]];
                if(temp == 1){
                    cnt++;
                }
            }
        }

        if(val == 1){
            if(cnt < 2 || cnt > 3)
                val = 0;
            else
                val = 1;
        }else{
            if(cnt == 3)
                val = 1;
        }
        return val;
    }
}
