package leetcode;

/**
 *
 * 《1071. 字符串的最大公因子》
 * 对于字符串 S 和 T，只有在 S = T + ... + T（T 与自身连接 1 次或多次）时，我们才认定 “T 能除尽 S”。
 *
 * 返回最长字符串 X，要求满足 X 能除尽 str1 且 X 能除尽 str2。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：str1 = "ABCABC", str2 = "ABC"
 * 输出："ABC"
 * 示例 2：
 *
 * 输入：str1 = "ABABAB", str2 = "ABAB"
 * 输出："AB"
 * 示例 3：
 *
 * 输入：str1 = "LEET", str2 = "CODE"
 * 输出：""
 *  
 *
 * 提示：
 *
 * 1 <= str1.length <= 1000
 * 1 <= str2.length <= 1000
 * str1[i] 和 str2[i] 为大写英文字母
 *
 */
public class GcdOfStrings {


    /**
     * gcd 算法：
     *
     * 辗转相除法是用来求两个整数的最大公约数的古老算法.
     * 给定 两个数: m ,n 求 m,n 的最大公约数的计算方式如下
     * 假定 m > n , 则
     * m % n 作为新的n,
     * n 作为新的 m,
     * 一直这样持续下去，直到 m % n = 0, 最后的这个n 就是原两个整数的最大公约数
     *
     * 用在求字符串的最长公共子串上：
     * (1) 当长串等于短串，直接返回任意一个
     * (2) 当长串包含短串，且短串长度大于0，则让长串多出短串那部分做为新的短串，而越来的短串变为长串；以此循环迭代
     * (3) 辗转相除后，若短串长度为 0 说明这时的长串就是最大公约子串
     *
     *
     */
    public String gcdOfStrings(String str1, String str2) {

        String dividend = (str1.length() > str2.length()) ? str1: str2;
        String divider = (str1 == dividend) ? str2 : str1;

        if(dividend.equals(divider)) return divider;

        // 长串包含短串，且短串不为空串，则一直执行下去
        int id = dividend.indexOf(divider);
        while(id > -1 && divider.length() > 0){
            String tempStr = divider;
            divider = getEemainderByDivider(id,dividend,divider);

            dividend = tempStr;
            id = dividend.indexOf(divider);
        }

        return divider.length() == 0 ? dividend : "";
    }

    private String getEemainderByDivider(int id, String dividend, String divider) {

        while(dividend.length() >= divider.length() && id > -1){
            dividend = dividend.substring(id + divider.length());
            id = dividend.indexOf(divider);
        }
        return dividend;
    }


    public static void main(String[] args) {
        String str1 = "TAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXX";
        String str2 = "TAUXXTAUXXTAUXXTAUXXTAUXX";

        GcdOfStrings obj = new GcdOfStrings();
        System.out.println(obj.gcdOfStrings(str1,str2));
    }
}
