package leetcode;

/**
 * @ClassName: HammingWeight
 * @CopyRight:
 * @Description:
 * 《LeetCode 191》
 *  编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。
 * @Author: wufangmin
 * @Date: 2019/9/30 16:10
 * @Version:
 */
public class HammingWeight {

    public static void main(String[] args) {
        System.out.println(hammingWeight(99));
    }

    public static int hammingWeight(int n) {
        int num = 0;
        int bit = 0;
        while(n != 0){
            bit = n & 1;
            num += bit;
            n = n >>> 1;
        }
        return num;
    }
}
