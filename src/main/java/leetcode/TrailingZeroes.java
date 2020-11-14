package leetcode;

/**
 * @Class: TrailingZeroes
 * @Description: 《172. 阶乘后的零》 ：
 *
 * 给定一个整数 n，返回 n! 结果尾数中零的数量。
 *
 * 输入: 3
 * 输出: 0
 * 解释: 3! = 6, 尾数中没有零。
 *
 * @Author: Minsky
 * @Date: 2019/10/23 21:21
 * @Version: v1.0
 */
public class TrailingZeroes {


    public static void main(String[]args){
        int[] testArr = {3,5,15,31,2147483647};

        System.out.println((int)Math.pow(5,13));

        for(int ele : testArr){
            System.out.println(trailingZeroes(ele));
        }
    }


    public static int trailingZeroes(int n) {

        int p = nearestPower(n);
        int cnt = 0;

        for(int i = 1; i <= p ; i++){
            cnt += n/(int) Math.pow(5,i);
        }
        return cnt;
    }

    static int nearestPower(int n){
        long tmp = 1;
        int p = 0;
        while(tmp <= n && tmp <= Integer.MAX_VALUE){
            tmp = tmp * 5;
            p++;
        }

        return p == 0 ? 0 : p - 1;
    }
}
