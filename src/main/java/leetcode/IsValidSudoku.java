package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 请你判断一个9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
 *
 * 数字1-9在每一行只能出现一次。
 * 数字1-9在每一列只能出现一次。
 * 数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。（请参考示例图）
 * 
 *
 * 注意：
 *
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * 空白格用'.'表示。
 *
 */
public class IsValidSudoku {

    /**
     * 以 3*3 9宫格形式遍历 9*9 的数独矩阵
     */
    public boolean isValidSudoku(char[][] board) {
        int rows = board.length, cols = board[0].length;
        for(int i = 0; i < rows; i++){
            // 计算当前宫格行列偏移量
            int rowShift = i / 3;
            int colShift = i % 3;
            // 宫格内部遍历
            for(int j = 0; j < cols; j++){
                int innerRow = j / 3 + rowShift * 3;
                int innerCol = j % 3 + colShift * 3;
                if(board[innerRow][innerCol] != '.'){
                    if(checkNumIfExist(board, innerRow, innerCol, rowShift, colShift)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    /**
     * 遍历校验宫格是否存在
     */
    private boolean checkNumIfExist(char[][] board, int row, int col,
                                    int rShift, int cShift) {
        for(int i = 0 ; i < board.length; i++){
            if(i != row && board[i][col] == board[row][col]){
                return true;
            }
        }
        for(int j = 0 ; j < board[0].length; j++){
            if(j != col && board[row][j] == board[row][col]){
                return true;
            }
        }
        for(int k = 0; k < board.length; k++){
            int r = k / 3 + rShift * 3;
            int c = k % 3 + cShift * 3;
            if(r != row && c != col &&
                    board[r][c] == board[row][col]){
                return true;
            }
        }
        return false;
    }



    public static void main(String[] args) {
        char[][] board =
                {{'5','3','.','.','7','.','.','.','.'}
                ,{'6','.','.','1','9','5','.','.','.'}
                ,{'.','9','8','.','.','.','.','6','.'}
                ,{'8','.','.','.','6','.','.','.','3'}
                ,{'4','.','.','8','.','3','.','.','1'}
                ,{'7','.','.','.','2','.','.','.','6'}
                ,{'.','6','.','.','.','.','2','8','.'}
                ,{'.','.','.','4','1','9','.','.','5'}
                ,{'.','.','.','.','8','.','.','7','9'}};


        board =
                new char[][]{{'8', '3', '.', '.', '7', '.', '.', '.', '.'}
                        , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                        , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                        , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                        , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                        , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                        , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                        , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                        , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        IsValidSudoku inst = new IsValidSudoku();
        System.out.println(inst.isValidSudoku(board));
    }

}
