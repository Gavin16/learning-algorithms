package leetCode;

import edu.princeton.cs.Stack;

import java.util.HashSet;
import java.util.Set;

/**
 * @Class: EvalRPN
 * @Description:
 *
 * 《GeekTime -- practice day02》
 * 150. 逆波兰表达式求值
 *
 * 根据逆波兰表示法，求表达式的值。
 *
 * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 *
 * 说明：
 *
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * 示例 1：
 *
 * 输入: ["2", "1", "+", "3", "*"]
 * 输出: 9
 * 解释: ((2 + 1) * 3) = 9
 * 示例 2：
 *
 * 输入: ["4", "13", "5", "/", "+"]
 * 输出: 6
 * 解释: (4 + (13 / 5)) = 6
 * 示例 3：
 *
 * 输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 * 输出: 22
 * 解释:
 *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 *
 * @Author: Minsky
 * @Date: 2020/2/2 21:09
 * @Version: v1.0
 */
public class EvalRPN {


    public static void main(String[] args) {
        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(evalRPN(tokens));
    }

    private static Set<String> operators = new HashSet<>();

    static {
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");
    }

    public static int evalRPN(String[] tokens) {
        Stack<Integer> operands = new Stack<>();

        for(int i = 0 ; i < tokens.length ; i++){
            if(!operators.contains(tokens[i])){
                operands.push(Integer.parseInt(tokens[i]));
            }else{
                int right = operands.pop();
                int left = operands.pop();
                int res = operation(left,right,tokens[i]);
                operands.push(res);
            }
        }

        return operands.pop();
    }


    private static int operation(int left, int right, String token) {
        int res ;
        switch (token){
            case "+" : res = left + right; break;
            case "-" : res = left - right; break;
            case "*" : res = left * right; break;
            case "/" : res = left / right; break;
            default: res = 0 ; break;
        }
        return res;
    }
}
