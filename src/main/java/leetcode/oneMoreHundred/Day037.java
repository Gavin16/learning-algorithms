package leetcode.oneMoreHundred;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 1190. 反转每对括号间的子串
 * 给出一个字符串 s（仅含有小写英文字母和括号）。
 *
 * 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
 *
 * 注意，您的结果中 不应 包含任何括号。
 * 示例 1：
 *
 * 输入：s = "(abcd)"
 * 输出："dcba"
 * 示例 2：
 *
 * 输入：s = "(u(love)i)"
 * 输出："iloveu"
 * 示例 3：
 *
 * 输入：s = "(ed(et(oc))el)"
 * 输出："leetcode"
 * 示例 4：
 *
 * 输入：s = "a(bcdefghijkl(mno)p)q"
 * 输出："apmnolkjihgfedcbq"
 *
 *
 * 394. 字符串解码
 *
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像3a或2[4]的输入
 *
 * 示例 1：
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 *
 * 示例 2：
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 *
 *
 */
public class Day037 {


    public static void main(String[] args) {
        String str1 = "a(bcdefghijkl(mno)p)q";

        System.out.println(reverseParentheses2(str1));

        String str7 = "2[abc]3[cd]ef";
        String str8 = "3[a2[c]]";
        String str9 = "10[leetcode]";
        System.out.println("-------------------------");
//        System.out.println(decodeString(str7));
//        System.out.println(decodeString(str8));
        System.out.println(decodeString(str9));

    }


    /**
     * 递归写法
     * 解法有问题，当遇到像: "ta()usw((((a))))" 这种情况时，无法正常匹配括号
     *
     * @param s
     * @return
     */
    public static String reverseParentheses(String s) {
        String res = dfsReverse(s, 0, s.length()-1 , 0);
        return res;
    }

    public static String dfsReverse(String str , int s , int e, int cnt){
        if(s > e) return "";
        String leftPart = "", rightPart = "";
        int leftPos = str.substring(s,e+1).indexOf("(") + s;
        int rightPos = str.substring(s,e+1).lastIndexOf(")") + s;

        if(leftPos < rightPos){
            if(cnt % 2 == 0){
                leftPart = leftPart + str.substring(s,leftPos);
                rightPart = rightPart + str.substring(rightPos+1,e+1);
            }else{
                leftPart = reverse(str.substring(rightPos+1,e+1));
                rightPart = reverse(str.substring(s,leftPos));
            }
            String middle = dfsReverse(str,leftPos+1,rightPos-1,cnt+1);
            return leftPart + middle + rightPart;
        }else{
            // 否则认为已经没有括号了
            if(cnt % 2 == 0) return str.substring(s,e+1);
            else{
                char[] temp = new char[e-s+1];
                for(int i = e,k = 0 ; i >= s ;){
                    temp[k++] = str.charAt(i--);
                }
                return String.valueOf(temp);
            }
        }
    }



    /**
     * 使用栈匹配括号
     * "a(bcdefghijkl(mno)p)q"
     * "ta()usw((((a))))"
     * @param str
     * @return
     */
    public static String reverseParentheses1(String str){
        Stack<Character> stack1 = new Stack<>();
        Deque<Character> stack2 = new LinkedList<>();
        int start = 0 , curr = start;
        int deep = 0;

        while(true){
            if(str.charAt(curr) == '('){
                deep += 1;
            }else if(str.charAt(curr) == ')'){
                // 弹出
                while(stack1.peek() != '('){
                    if(deep % 2 == 0){
                        stack2.addLast(stack1.pop());
                    }else{
                        stack2.addFirst(stack1.pop());
                    }
                }
                stack1.pop();
                deep -= 1;
            }else{
                stack1.push(str.charAt(curr));
            }
        }
    }


    /**
     * 使用栈从内向外的反转
     * @param s
     * @return
     */
    public static String reverseParentheses2(String s) {
        Deque<String> stack = new LinkedList<String>();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(sb.toString());
                sb.setLength(0);
            } else if (ch == ')') {
                sb.reverse();
                sb.insert(0, stack.pop());
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }


    private static String reverse(String str){
        char[] temp = str.toCharArray();
        for(int i = 0,j = str.length()-1 ; i <= j ; i++, j--){
            char sw = temp[i];
            temp[i] = temp[j];
            temp[j] = sw;
        }
        return String.valueOf(temp);
    }


    /**
     *
     * 输入：s = "2[abc]3[cd]ef"
     * 输出："abcabccdcdcdef"
     *
     * 输入：s = "3[a2[c]]"
     * 输出："accaccacc"
     *
     * 输入: s = "3[a2[2[c]]]"
     *
     * 从内向外解法:
     * 从左向右遍历,使用StringBuilder 保存字符串当遇到数字时，字符串
     * 当遇到"[" 时保存数字， 当遇到"]" 时弹出数字,重复当前SB中的字符串，若存在前驱字符串则弹出字符串插入在前面
     *
     *
     * @param s
     * @return
     */
    public static String decodeString(String s) {
        int len = s.length();
        Deque<String> strStack = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < len ; i++){
            char c = s.charAt(i);
            if(c == '['){
                strStack.push(sb.toString());
                sb.setLength(0);
            }else if(c == ']'){
                String pop = strStack.pop();
                StringBuilder prefix = new StringBuilder();
                int digit = 0;
                for(int j = 0 ; j < pop.length() ; j++){
                    char c1 = pop.charAt(j);
                    if(Character.isDigit(c1)){
                        digit = digit * 10 + (c1 - '0');
                    }else{
                        prefix.append(pop.charAt(j));
                    }
                }
                char[] chars = sb.toString().toCharArray();
                for(int k = 1 ; k < digit ; k++){
                    sb.append(chars);
                }
                sb.insert(0,prefix);
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
