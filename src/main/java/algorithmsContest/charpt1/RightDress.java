package algorithmsContest.charpt1;

import edu.princeton.cs.In;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

/**
 * 1.3 栈
 *
 * @className: RightDress
 * @description:
 * 例1.6 向右看齐
 * 问题描述:
 * N( 1<= N <= 10^5) 头奶牛站成一排, 奶牛i的身高为Hi( 1 <= Hi <= 10^6) .
 * 现在眉头奶牛都在向右看齐。对于奶牛i,如果奶牛j满足 i < j 且 Hi < Hj. 我们说奶牛 i 仰望
 * 奶牛 j. 求出每头奶牛离他最近的仰望对象。
 *
 * 输入: 第1行输入N, 后面N行中,每行输入一个身高Hi
 * 输出: 共输出N行, 按顺序每行输出一头奶牛的最近仰望对象，如果没有仰望对象输出 0
 *
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2022/12/8
 */
public class RightDress {

    // 3 5 1 7 4 6 =>  5 7 7 0 6 0
    public static void main(String[] args) {
        RightDress instance = new RightDress();
        instance.nearestHigher();
    }


    public void nearestHigher(){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = scanner.nextInt();
        }

        // 单调递减栈
        Deque<Integer> stack = new LinkedList<>();
        int[] ans = new int[n];
        for(int i = 0; i < n ; i++){
            while(!stack.isEmpty() && a[i] > a[stack.peek()]){
                Integer pop = stack.pop();
                ans[pop] = a[i];
            }
            stack.push(i);
        }
        for(int k : ans)
            System.out.println(k);
    }
}
