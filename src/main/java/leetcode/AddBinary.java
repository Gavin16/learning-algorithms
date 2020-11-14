package leetcode;

/**
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 *
 * 输入为非空字符串且只包含数字 1 和 0。
 *
 * 示例 1:
 *
 * 输入: a = "11", b = "1"
 * 输出: "100"
 *
 */
public class AddBinary {


    public static void main(String[]args){

        String a = "1010";
        String b = "1011";

        System.out.println(addBinary(a, b));

    }

    public static String addBinary(String a, String b) {
        int lenA = a.length();
        int lenB = b.length();

        int i = lenA - 1 , j = lenB - 1 ,carry = 0;
        String res = "";
        while( i >= 0 || j >= 0 || carry != 0){
            int bitA = i >= 0 ? (a.charAt(i--) - '0') : 0;
            int bitB = j >= 0 ? (b.charAt(j--) - '0') : 0;

            int bitRes =  (bitA + bitB + carry) % 2;
            carry = (bitA + bitB + carry) / 2;

            res = bitRes + res;
        }
        return res;
    }
}
