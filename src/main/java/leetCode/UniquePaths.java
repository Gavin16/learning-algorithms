package leetCode;

/**
 * 《LeetCode 62. 不同路径》
 *
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 问总共有多少条不同的路径？
 *
 * 示例 1:
 *
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 *
 */
public class UniquePaths {

    public static void main(String[]args){
        int m = 1000 , n = 100;
        System.out.println(uniquePaths2(m,n));
    }


    /**
     * 递归实现 ： 低效
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths(int m, int n) {
        if(m == 1 || n == 1) return 1;

        return uniquePaths(m-1, n) + uniquePaths(m,n-1);
    }


    /**
     * 循环实现
     * @param m
     * @param n
     * @return
     */
    public static long uniquePaths2(int m , int n){
        long[][] p = new long[m][n];

        for(int i = 0 ; i < m; i++){
            for(int j = 0; j < n ; j++){
                if(i == 0 || j == 0) p[i][j] = 1;
                else p[i][j] = p[i-1][j] + p[i][j-1];
            }
        }

        return p[m-1][n-1];
    }
}
