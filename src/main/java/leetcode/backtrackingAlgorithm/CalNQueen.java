package leetcode.backtrackingAlgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 《51. N皇后》
 *
 *  n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 *
 * 上图为 8 皇后问题的一种解法。
 *
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 *
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * 示例:
 *
 * 输入: 4
 * 输出: [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 *
 */
public class CalNQueen {

    public static void main(String[] args) {
        List<List<String>> lists = solveNQueens(4);

        for(List<String> list : lists){
            System.out.println(list);
        }
    }


    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        int[] scheme = new int[n];
        dfsFindAllSolutions(result,scheme,0,n);
        return result;
    }

    private static void dfsFindAllSolutions(List<List<String>> result, int[] scheme , int row ,int n){
        if(row == n){
            List<String> res = parseIntegerArray(scheme);
            result.add(res);
            return;
        }

        for(int col = 0 ; col < n ; col++){
            if(isPositionOK(row,col,n,scheme)){
                scheme[row] = col;
                dfsFindAllSolutions(result,scheme,row+1,n);
            }
        }
    }

    private static boolean isPositionOK(int row, int col, int n, int[] scheme) {
        int leftUp = col - 1, rightUp = col + 1;
        for(int r = row - 1 ; r >=0 ; r--){
            if(scheme[r] == col) return false;
            if(leftUp >= 0 && scheme[r] == leftUp) return false;
            if(rightUp < n && scheme[r] == rightUp) return false;
            leftUp--;rightUp++;
        }
        return true;
    }

    private static List<String> parseIntegerArray(int[] scheme) {
        List<String> result = new ArrayList<>();
        for(int n : scheme){

            StringBuilder sb = new StringBuilder();
            for(int i = 0 ; i < scheme.length ; i++){
                if(i == n){
                    sb.append("Q");
                }else {
                    sb.append(".");
                }
            }
            result.add(sb.toString());
        }
        return result;
    }
}
