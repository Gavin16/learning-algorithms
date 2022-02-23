package leetcode;

/**
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围[−231, 231− 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * 
 * 示例 1：
 * 输入：x = 123
 * 输出：321
 * 示例 2：
 *
 * 输入：x = -123
 * 输出：-321
 * 示例 3：
 *
 * 输入：x = 120
 * 输出：21
 * 示例 4：
 *
 * 输入：x = 0
 * 输出：0
 * 
 * 提示：
 * -231 <= x <= 231 - 1
 *
 */
public class Reverse {

    public int reverse(int x) {
        long sum = 0;
        String xStr = String.valueOf(x);
        char c = xStr.charAt(0);
        int sign = ('-' == c) ? -1 : 1;
        xStr = (sign == -1) ? xStr.substring(1): xStr;
        StringBuilder reverse = new StringBuilder(xStr).reverse();
        xStr = reverse.toString();
        for(int i = 0 ; i < xStr.length(); i++){
            char c1 = xStr.charAt(i);
            int val = c1 - '0';
            sum = 10 * sum + val;
            if(sum > Integer.MAX_VALUE || sum < Integer.MIN_VALUE){
                return 0;
            }
        }
        return (int)sum*sign;
    }

    public static void main(String[] args) {
        Reverse inst = new Reverse();
        int aaa = 0;
        System.out.println(inst.reverse(aaa));
    }
}
