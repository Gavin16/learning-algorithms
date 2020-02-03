package leetCode;

import edu.princeton.cs.Stack;

/**
 * @Class: IsValid
 * @Description:
 *
 *  《GeekTime -- practice day02》
 * 《20. 有效的括号》
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 *
 * 输入: "()"
 * 输出: true
 * 示例 2:
 *
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 *
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 *
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 *
 * 输入: "{[]}"
 * 输出: true
 *
 * @Author: Minsky
 * @Date: 2020/2/1 21:49
 * @Version: v1.0
 */
public class IsValid {

    public static void main(String[] args) {
        String s1 = "()[]{}";
        String s2 = "([)]";
        String s3 = "{[]}";

        System.out.println(isValid(s1));
        System.out.println(isValid(s2));
        System.out.println(isValid(s3));
    }


    /**
     * 使用两个栈判断
     */
    public static boolean isValid(String s) {
        if(null == s || s.length() == 0) return true;

        Stack<Character> left = new Stack<>(),right = new Stack<>();

        char[] cs = s.toCharArray();
        for(char c : cs) left.push(c);

        // 判断括号是否合法; 先向s2中存入一个括号
        right.push(left.pop());
        while(left.size() > 0){
            Character pop = left.pop();
            if(matchStack2(pop,right)){
                right.pop();
            }else{
                right.push(pop);
            }
        }
        return right.size() == 0 ? true : false;
    }

    private static boolean matchStack2(char pop, Stack<Character> s2) {
        if(s2.size() == 0) return false;
        char peek = s2.peek();
        boolean result = false;
        if(pop - peek < 0 && pop - peek >=-2) result = true;
        return result;
    }
}
