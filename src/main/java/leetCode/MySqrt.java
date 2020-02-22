package leetCode;

/**
 *
 * 《GeekTime -- practice day03》
 * 《69. x 的平方根》
 *
 * 实现 int sqrt(int x) 函数。
 *
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 *
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * 示例 1:
 *
 * 输入: 4
 * 输出: 2
 * 示例 2:
 *
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 *
 */
public class MySqrt {

    public static void main(String[] args) {
//        System.out.println(mySqrt(0));
//        System.out.println(mySqrt(1));
//        System.out.println(mySqrt(2));
//        System.out.println(mySqrt(3));
//        System.out.println(mySqrt(8));
//        System.out.println(mySqrt(9));
//        System.out.println(mySqrt(15));
//        System.out.println(mySqrt(16));
        System.out.println(mySqrt(2147395600));
    }

    /**
     * 考虑大整数的情况
     */
    public static int mySqrt(int x) {
        if(x <= 1) return x;
        else if(x <= 3) return 1;
        else{
            long head = 0 , tail = x / 2;
            long mid = head + (tail - head)/2;
            while(head <= tail){
                if(mid * mid < x){
                    head = mid + 1;
                }else  if(mid * mid > x){
                    tail = mid -1;
                }else{
                    return (int) mid;
                }
                mid = head + (tail - head)/2;
            }
            return (int) mid;
        }
    }

}
