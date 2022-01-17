package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 *
 */
public class SpiralOrder {


    /**
     * 以螺旋方式遍历矩阵
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        boolean[][] visitMatrix = new boolean[rows][cols];
        int total = rows * cols;

        // 定义数据移动方向矩阵: 右,下,左,上
        int[][] direction = {{0,1},{1,0},{0,-1},{-1,0}};
        int xPos = 0 , yPos = 0, directId = 0;

        List<Integer> results = new ArrayList<>();
       for(int i = 0 ; i < total; i++){
            visitMatrix[xPos][yPos] = true;
            results.add(matrix[xPos][yPos]);
            int xPosNext = xPos + direction[directId][0];
            int yPosNext = yPos + direction[directId][1];

            if(xPosNext < 0 || xPosNext >= rows || yPosNext < 0
                    || yPosNext >= cols || visitMatrix[xPosNext][yPosNext]){
                directId = (directId + 1) % 4;
            }
            xPos = xPos + direction[directId][0];
            yPos = yPos + direction[directId][1];
        }
        return results;
    }

    public static void main(String[] args) {
        SpiralOrder spiralOrder = new SpiralOrder();
        int[][] array = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};

        List<Integer> integers = spiralOrder.spiralOrder(array);
        System.out.println(integers);
    }


}
