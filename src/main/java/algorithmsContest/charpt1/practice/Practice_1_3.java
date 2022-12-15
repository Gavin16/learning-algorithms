package algorithmsContest.charpt1.practice;

import java.util.Scanner;
import java.util.Stack;

/**
 * @className: Practice_1_3
 * @description:
 * 单调栈练习题:
 * 1. 洛谷P5788/P1449/P1739/P1981/P1175
 * 2. 力扣: 用两个栈实现队列, 包含min()函数的栈，栈的压入，弹出序列，翻转单词顺序列
 *
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2022/12/15
 */
public class Practice_1_3 {

    public static void main(String[] args) {
        Practice_1_3 instance = new Practice_1_3();
        instance.monotoneStack();
    }

    /**
     * P5788 单调栈
     * 给出数项为n的整数数列 a1,a2, ..an
     * 定义函数f(i) 代表数列中第 i 个元素之后第一个大于 ai 的元素的下标。若不存在则 f(i) = 0
     * 试求 f(i) i∈[1, n]
     *
     *
     */
    public void monotoneStack(){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] a =  new int[n + 3];
        for(int i = 1; i <= n; i++)
            a[i] = scanner.nextInt();

        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[n + 1];
        for(int j = n ; j > 0; j--){
            while(!stack.isEmpty() && a[stack.peek()] <= a[j])
                stack.pop();
            if(stack.isEmpty()) ans[j] = 0;
            else ans[j] = stack.peek();
            stack.push(j);
        }
        for(int i = 1; i < ans.length; i++)
            System.out.printf("%d ", ans[i]);
    }

}
