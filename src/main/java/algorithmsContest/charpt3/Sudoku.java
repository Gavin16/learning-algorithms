package algorithmsContest.charpt3;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: Sudoku
 * @description: TODO
 * @version: 1.0
 * @author: minsky
 * @date: 2023/5/4
 */
public class Sudoku {

    public static void main(String[] args) {
        Sudoku instance = new Sudoku();

        char[][] board =  {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        instance.solveSudoku(board);

        for(char[] line : board){
            for(int i = 0; i < line.length; i++){
                System.out.printf("%c ", line[i]);
                if(i == line.length - 1) System.out.println();
            }
        }
    }


    private boolean[][] line = new boolean[9][9];
    private boolean[][] column = new boolean[9][9];
    private boolean[][][] block = new boolean[3][3][9];
    private boolean valid = false;
    private List<int[]> spaces = new ArrayList<>();

    /**
     * 例3.10 解数独(同LeetCode 37)
     * @param board
     * @return
     */
    public void solveSudoku(char[][] board) {
        for(int i = 0 ; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] == '.'){
                    spaces.add(new int[]{i, j});
                }else{
                    int val = board[i][j] - '0';
                    line[i][val - 1] = true;
                    column[j][val - 1] = true;
                    block[i/3][j/3][val - 1] = true;
                }
            }
        }
        dfsFillBoard(board, 0);
    }

    // 深度优先填写数独
    // 填写顺序: 行扫描顺序
    private void dfsFillBoard(char[][] board, int pos){
        if(pos == spaces.size()){
            valid = true;
            return;
        }
        int[] emptyPos = spaces.get(pos);
        int i = emptyPos[0], j = emptyPos[1];

        for(int d = 1 ; d <= 9 && !valid ; d++){
            if(!line[i][d - 1] && !column[j][d - 1]
                    && !block[i/3][j/3][d - 1]){
                line[i][d-1] =  true;
                column[j][d-1] = true;
                block[i/3][j/3][d-1] = true;
                board[i][j] = (char)('0' + d);
                dfsFillBoard(board, pos + 1);
                line[i][d-1] = false;
                column[j][d-1] = false;
                block[i/3][j/3][d-1] = false;
            }
        }
    }


//    private boolean[][] line = new boolean[9][9];
//    private boolean[][] column = new boolean[9][9];
//    private boolean[][][] block = new boolean[3][3][9];
//    private boolean valid = false;
//    private List<int[]> spaces = new ArrayList<int[]>();
//
//    public void solveSudoku(char[][] board) {
//        for (int i = 0; i < 9; ++i) {
//            for (int j = 0; j < 9; ++j) {
//                if (board[i][j] == '.') {
//                    spaces.add(new int[]{i, j});
//                } else {
//                    int digit = board[i][j] - '0' - 1;
//                    line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
//                }
//            }
//        }
//
//        dfs(board, 0);
//    }

//    public void dfs(char[][] board, int pos) {
//        if (pos == spaces.size()) {
//            valid = true;
//            return;
//        }
//
//        int[] space = spaces.get(pos);
//        int i = space[0], j = space[1];
//        for (int digit = 0; digit < 9 && !valid; ++digit) {
//            if (!line[i][digit] && !column[j][digit] && !block[i / 3][j / 3][digit]) {
//                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
//                board[i][j] = (char) (digit + '0' + 1);
//                dfs(board, pos + 1);
//                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = false;
//            }
//        }
//    }
}
