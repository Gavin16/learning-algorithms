package algorithmsContest.charpt1.practice.submit.P1449;

import java.util.Scanner;
import java.util.Stack;

/**
 * @className: Main
 * @description:
 *
 * 后缀表达式
 * 所谓后缀表达式是指这样的一个表达式：式中不再引用括号，运算符号放在两个运算对象之后，
 * 所有计算按运算符号出现的顺序，严格地由左而右新进行（不用考虑运算符的优先级）。
 *
 * 如：3*(5-2)+7 对应的后缀表达式为: 3.5.2.-*7.+@  在该式中，@ 为表达式的结束符号。. 为操作数的结束符号。
 *
 * 输入格式
 * 输入一行一个字符串 s，表示后缀表达式。
 *
 * 输出格式
 * 输出一个整数，表示表达式的值。
 *
 * 输入输出样例
 * 输入
 * 3.5.2.-*7.+@
 * 输出
 * 16
 *
 * 说明/提示
 * 数据保证 1 <= abs(s) <= 50，答案和计算过程中的每一个值的绝对值不超过 10^9;
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2022/12/20
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        String[] split = s.split("\\.");

        Stack<Long> stack = new Stack<>();

        if(split.length == 1)
            System.out.print(split[0].replace("@",""));
        for(int i = 0 ; i < split.length; i++){
            String part = split[i];
            if(part.contains("+") || part.contains("-")
                    || part.contains("*") || part.contains("/")){
                for(int k = 0; k < part.length(); k++){
                    char c = part.charAt(k);
                    if(Character.isDigit(c)){
                        stack.push(Long.valueOf(part.substring(k)));
                        break;
                    }else if(c == '@'){
                        break;
                    }else{
                        Long right = stack.pop();
                        Long left = stack.pop();
                        switch (c){
                            case '+': stack.push( left + right); break;
                            case '-': stack.push(left - right); break;
                            case '*': stack.push(left * right); break;
                            case '/': stack.push(left / right); break;
                            default: break;
                        }
                    }
                }
            }else{
                stack.push(Long.parseLong(part));
            }
        }
        System.out.print(stack.pop());
    }

}
