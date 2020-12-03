package leetcode.oneMoreHundred;

import edu.princeton.cs.In;

import javax.xml.stream.events.Characters;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Stack;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * ==============================================================================
 * 《316. 去除重复字母》
 * ==============================================================================
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。
 * 需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 *
 * 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同
 *
 * 示例 1：
 *
 * 输入：s = "bcabc"
 * 输出："abc"
 * 示例 2：
 *
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 *
 * 提示：
 *
 * 1 <= s.length <= 104
 * s 由小写英文字母组成
 *
 * ==============================================================================
 * 《682. 棒球比赛》
 * ==============================================================================
 * 你现在是一场采特殊赛制棒球比赛的记录员。这场比赛由若干回合组成，过去几回合的得分可能会影响以后几回合的得分。
 *
 * 比赛开始时，记录是空白的。你会得到一个记录操作的字符串列表 ops，其中 ops[i] 是你需要记录的第 i 项操作，ops 遵循下述规则：
 *
 * 整数 x - 表示本回合新获得分数 x
 * "+" - 表示本回合新获得的得分是前两次得分的总和。题目数据保证记录此操作时前面总是存在两个有效的分数。
 * "D" - 表示本回合新获得的得分是前一次得分的两倍。题目数据保证记录此操作时前面总是存在一个有效的分数。
 * "C" - 表示前一次得分无效，将其从记录中移除。题目数据保证记录此操作时前面总是存在一个有效的分数。
 * 请你返回记录中所有得分的总和。
 *
 * 示例 1：
 *
 * 输入：ops = ["5","2","C","D","+"]
 * 输出：30
 * 解释：
 * "5" - 记录加 5 ，记录现在是 [5]
 * "2" - 记录加 2 ，记录现在是 [5, 2]
 * "C" - 使前一次得分的记录无效并将其移除，记录现在是 [5].
 * "D" - 记录加 2 * 5 = 10 ，记录现在是 [5, 10].
 * "+" - 记录加 5 + 10 = 15 ，记录现在是 [5, 10, 15].
 * 所有得分的总和 5 + 10 + 15 = 30
 * 示例 2：
 *
 * 输入：ops = ["5","-2","4","C","D","9","+","+"]
 * 输出：27
 * 解释：
 * "5" - 记录加 5 ，记录现在是 [5]
 * "-2" - 记录加 -2 ，记录现在是 [5, -2]
 * "4" - 记录加 4 ，记录现在是 [5, -2, 4]
 * "C" - 使前一次得分的记录无效并将其移除，记录现在是 [5, -2]
 * "D" - 记录加 2 * -2 = -4 ，记录现在是 [5, -2, -4]
 * "9" - 记录加 9 ，记录现在是 [5, -2, -4, 9]
 * "+" - 记录加 -4 + 9 = 5 ，记录现在是 [5, -2, -4, 9, 5]
 * "+" - 记录加 9 + 5 = 14 ，记录现在是 [5, -2, -4, 9, 5, 14]
 * 所有得分的总和 5 + -2 + -4 + 9 + 5 + 14 = 27
 * 示例 3：
 *
 * 输入：ops = ["1"]
 * 输出：1
 * 提示：
 * 1 <= ops.length <= 1000
 * ops[i] 为 "C"、"D"、"+"，或者一个表示整数的字符串。整数范围是 [-3 * 104, 3 * 104]
 * 对于 "+" 操作，题目数据保证记录此操作时前面总是存在两个有效的分数
 * 对于 "C" 和 "D" 操作，题目数据保证记录此操作时前面总是存在一个有效的分数
 *
 * ==============================================================================
 *
 *
 */
public class Day022 {


    public String removeDuplicateLetters(String s) {
        return null;
    }


    public static void main(String[] args) {
        String[] test1 = {"5","-2","4","C","D","9","+","+"};
        String[] test2 = {"5","2","C","D","+"};

        System.out.println(new Day022().calPoints(test1));
        System.out.println(new Day022().calPoints(test2));
    }

    public int calPoints(String[] ops) {
        Deque<Integer> stack = new LinkedList<>();

        int later;
        for (int i = 0 ; i < ops.length ; i++){
            String str = ops[i];
            Integer intValue = getIntValue(str);
            if(null != intValue){
                stack.push(intValue);
            }
            switch (str){
                case "C": stack.remove(); break;
                case "D": stack.push(2*stack.peek());  break;
                case "+": later = stack.pop(); int sum = later + stack.peek(); stack.push(later); stack.push(sum); break;
                default: break;
            }
        }
        int res = 0;
        while(!stack.isEmpty()){
            res += stack.pop();
        }
        return res;
    }


    private Integer getIntValue(String num){
        if(!Character.isDigit(num.charAt(num.length()-1))) return null;
        int sign = 1;
        if(num.startsWith("-")){
            sign = -1 * sign;
            num = num.substring(1);
        }
        int val = 0;
        for(char ch : num.toCharArray()){
            val = 10*val + ch - '0';
        }
        return sign * val;
    }



    /**
     * 简化实现
     */
    public int calPoints2(String[] ops) {
        Deque<Integer> stack = new LinkedList<>();

        for(String str : ops){
            switch (str){
                case "+":{
                    int top = stack.pop();
                    int newTop = top + stack.peek();
                    stack.push(top);
                    stack.push(newTop);
                } break;
                case "C": stack.pop(); break;
                case "D": stack.push(2*stack.peek()); break;
                default:{
                    stack.push(Integer.valueOf(str));
                } break;
            }
        }
        int res = 0 ;
        for(Integer ele : stack){
            res += ele;
        }
        return res;
    }

}
