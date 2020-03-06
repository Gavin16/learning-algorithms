package leetCode.backtrackingAlgorithm;

/**
 * 8-皇后问题，回溯解法
 *
 * 有一个 8x8 的棋盘，希望往里放 8 个棋子（皇后），每个棋子所在的行、列、对角线都不能有另一个棋子。
 */
public class Cal8Queen {


    public static void main(String[] args) {
        cal8Queen(0);
    }


    private static int[] result = new int[8];


    private static void printQueen(int[] result){
        for(int row = 0 ; row < 8 ; row++){
            for(int col = 0 ; col < 8 ; col++){
                if(result[row] == col)
                    System.out.print("Q");
                else
                    System.out.print("*");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * 递归调用，第i行j列的结果保存形式为 result[i] = j
     */
    public static  void cal8Queen(int row){
        if(row == 8){
            printQueen(result);
            return;
        }

        for(int col = 0 ; col < 8 ; col ++){
            if(isPositionOK(row,col)){
                result[row] = col;
                cal8Queen(row+1);
            }
        }
    }


    /**
     *  判断当前 row 和 col放置皇后是否合要求
     *  判断两个方面：
     *  (1) 当前位置所在列的之前所有行中，是否有某一行占用
     *  (2) 当前位置的对角线上在放过的行中的那部分，是否放置有皇后，包括左上角和右上角
     */
    private static boolean isPositionOK(int row, int col){
        int leftUp = col - 1, rightUp = col + 1;
        for(int i = row-1 ; i >=0 ; i--){
            if(result[i] == col){
                return false;
            }

            if(leftUp >= 0 && result[i] == leftUp)
                return false;
            if(rightUp <8 && result[i] == rightUp)
                return false;

            leftUp--;rightUp++;
        }
        return true;
    }
}
