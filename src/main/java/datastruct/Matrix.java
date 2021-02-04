package datastruct;

import java.util.Objects;

/**
 * 整数矩阵工具类
 */
public class Matrix {


    /**
     * 两个矩阵相乘，返回相乘后的结果
     *
     * 矩阵相乘需要满足 左乘矩阵的列数 = 右乘矩阵的行数
     * 如 m*n   乘以  n*l  =》 m*l 的矩阵返回
     *
     * @param m1
     * @param m2
     * @return
     */
    public static int[][] multiply(int[][]m1 ,int[][]m2){
        if(null == m1 || null == m2 || m1.length == 0 || m2.length ==0) return null;
        if(m1[0].length != m2.length) return null;

        int rows = m1.length , columns = m2[0].length;
        int[][] res = new int[rows][columns];

        for(int i = 0; i < res.length ; i++){
            for(int j = 0 ; j < res[0].length ; j++){
                res[i][j] = 0 ;
                for(int k = 0 ; k < m1[0].length ; k++){
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    /**
     * 计算矩阵的 e 次方(矩阵必须是方阵)
     * @param m
     * @param e
     * @return
     */
    public static int[][] power(int[][]m ,int e){
        if(Objects.isNull(m) || m.length != m[0].length) return null;
        int[][] res = new int[m.length][m[0].length];
        for(int k = 0 ; k < res.length ;k++){
            res[k][k] = 1;
        }
        int tmp = e;
        while(tmp != 0){
            int bit = tmp & 1;
            if(bit == 1){
                res = multiply(m,res);
            }
            m = multiply(m,m);
            tmp = tmp >>> 1;
        }
        return res;
    }

    /**
     * 打印输出矩阵内容
     * @param matrix
     */
    public static void showMatrix(int[][] matrix){
        if(Objects.isNull(matrix) || 0 == matrix.length) return;
        int R = matrix.length , C = matrix[0].length;

        for(int i = 0 ; i < R ; i++){
            for(int j = 0 ; j < C ;j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
