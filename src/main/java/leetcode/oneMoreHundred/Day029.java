package leetcode.oneMoreHundred;

import datastruct.Matrix;

/**
 * 4. 寻找两个正序数组的中位数
 * ==============================================================================
 * 给定两个大小为 m 和 n 的正序（从小到大）数组nums1 和nums2。请你找出并返回这两个正序数组的中位数。
 *
 * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * ==============================================================================
 *
 * 第2节 斐波那契数列递归
 *
 * 斐波那契数列作为严格递归数列满足如下关系:
 *
 * [Fn ,Fn-1] = [Fn-1,Fn-2]* C
 * 其中矩阵C 为
 * 「1，1
 *   1，0」
 *
 *
 *
 */
public class Day029 {


    /**
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return 0;
    }


    /**
     * 快速求一个数的指数次方
     *
     * 将指数视为二进制，并对基数循环的做平方处理，
     * 每次处理根据二进制比特位判断是否需要乘进结果里
     *
     * @param m
     * @param e
     * @return
     */
    public long fastPower(int m , int e){
        long res = 1;
        long tmp = e , mm = m;
        while(tmp != 0){
            long bit = tmp & 1;
            if(bit == 1){
                res *= mm;
            }
            mm *= mm;
            tmp = tmp >>> 1;
        }
        return res;
    }

    public static void main(String[] args) {
        Day029 day029 = new Day029();
        System.out.println(day029.fastPower(2,16));

        System.out.println("================");
        System.out.println(day029.Fibonacci1(4));
    }

    /**
     * f(1) = 1,f(2）= 2, f(3) = 3 ... f(n) = f(n-1) + f(n-2)
     *
     * @param n
     * @return
     */
    public int Fibonacci1(int n){
        if(n < 1) return 0;
        if(n == 1 || n == 2) return n;
        int[][] convertor = {{1,1},{1,0}};

        int[][]res = Matrix.power(convertor,n-2);
        return 2*res[0][0] + res[1][0];
    }
}
