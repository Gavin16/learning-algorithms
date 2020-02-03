package leetCode;

/**
 * @Class: LongestValidParentheses
 * @Description:
 * 《GeekTime -- practice day02》
 * 《32. 最长有效括号》
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 示例 1:
 *
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 *
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 *
 *
 * @Author: Minsky
 * @Date: 2020/2/2 12:33
 * @Version: v1.0
 */
public class LongestValidParentheses {


    public static void main(String[] args) {
        String s1 = "(()";
        String s2 = ")()())";
        String s3 = "((()))";
        String s4 = "()(()";
        String s5 = "()())";
        String s6 = "())";
        String s7 = "(()";

        System.out.println(longestValidParentheses1(s1));
        System.out.println(longestValidParentheses1(s2));
        System.out.println(longestValidParentheses1(s3));
        System.out.println(longestValidParentheses1(s4));
        System.out.println(longestValidParentheses1(s5));
        System.out.println(longestValidParentheses1(s6));
        System.out.println(longestValidParentheses1(s7));

    }

    /**
     *  当遇到 "(" 时 , left + 1
     *  当遇到 ")" 时分两种情况：
     *      ① ： left = 0 , 这时 len 置为 0
     *      ② ： left > 0 , 这时 left - 1, len + 2  同时更新max
     *
     * 算法有问题，无法解决“()(()”的问题
     *
     */
    public static int longestValidParentheses(String s) {
        if(null == s || s.length() < 1) return 0;

        int left = 0 ,temp = 0,len = 0, max = 0;

        for(int i = 0 ; i < s.length() ; i++){
            if(s.charAt(i) == '('){
                left++;
            }else if(s.charAt(i) == ')'){
                if(left == 0){
                    temp = len;
                    len = 0 ;
                }else if(left > 0){
                    left--;
                    len += 2;
                    max = temp > max ? temp : max;
                }
            }
        }
        return max > len ? max : len;
    }


    /**
     *  网友解法
     */
    public static int longestValidParentheses1(String s) {
        int sum = 0 , validCnt = 0 , max = 0 ,cnt = 0;

        for(int i = 0 ; i < s.length() ; i++){
            sum += s.charAt(i) == '('? 1 : -1;
            cnt ++;

            if(sum == 0){
                validCnt = cnt;
            }else if(sum < 0){
                max = validCnt > max ? validCnt : max;
                cnt = 0;
                sum = 0;
                validCnt = 0;
            }
        }

        int res1 = validCnt > max ? validCnt : max;
        sum = 0 ; validCnt = 0 ; max = 0 ; cnt = 0;
        for(int i = s.length() - 1 ; i >= 0 ; i--){
            sum += s.charAt(i) == ')'? 1 : -1;
            cnt ++;

            if(sum == 0){
                validCnt = cnt;
            }else if(sum < 0){
                max = validCnt > max ? validCnt : max;
                cnt = 0;
                sum = 0;
                validCnt = 0;
            }
        }

        int res2 = validCnt > max ? validCnt : max;

        return res1 > res2 ? res1 : res2;
    }


}
