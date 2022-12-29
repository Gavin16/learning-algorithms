package algorithmsContest.charpt1.practice.submit.P1739;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @className: Main
 * @description:
 *
 * 题目描述
 * 假设一个表达式有英文字母，运算符(+,-,*,/) 和左右小圆括号构成，
 * 以 @ 作为表达式的结束符.请编写一个程序检验表达式中的左右圆括号是否匹配。
 * 若匹配，则输出  YES, 否则输出 NO。
 *
 * 表达式长度小于255，左圆括号少于20个
 *
 * 输入
 * 2*(x+y)/(1-x)@
 * 输出
 * YES
 *
 * 输入
 * (25+x)*(a*(a+b+b)@
 * 输出
 * NO
 *
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2022/12/20
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        Deque<Character> stack = new LinkedList<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '@'){
                break;
            }else if(c == ')'){
                while(!stack.isEmpty() && stack.peek() != '(') stack.pop();
                if(stack.isEmpty()){
                    System.out.print("NO");
                    return;
                }else{
                    stack.pop();
                }
            }else {
                stack.push(c);
            }
        }
        ArrayList<Character> characters = new ArrayList<>(stack);
        String s1 = String.valueOf(characters);
        if(s1.contains("(")){
            System.out.print("NO");
        }else{
            System.out.print("YES");
        }

    }

}
