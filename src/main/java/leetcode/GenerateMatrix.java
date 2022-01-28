package leetcode;

import utils.ArrayUtil;

/**
 *《59. 螺旋矩阵II》
 *
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 *
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：[[1]]
 *
 * 提示：
 * 1 <= n <= 20
 */
public class GenerateMatrix {

    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int[][] direction = {{0,1},{1,0},{0,-1},{-1,0}};
        int directId = 0;

        int row = 0 , col = 0;
        for(int i = 1 ; i <= n*n ; i++){
            matrix[row][col] = i;
            int nRow = row + direction[directId][0];
            int nCol = col + direction[directId][1];
            if(nCol >= n || nRow >= n || nCol < 0 || nRow < 0 || matrix[nRow][nCol] > 0){
                directId = (directId + 1) % 4;
            }
            row = row + direction[directId][0];
            col = col + direction[directId][1];
         }
        return matrix;
    }

    public static void main(String[] args) {
        GenerateMatrix inst = new GenerateMatrix();
        int[][] ints = inst.generateMatrix(3);
        for(int[] arr : ints){
            ArrayUtil.showArray(arr);
        }
    }
}
