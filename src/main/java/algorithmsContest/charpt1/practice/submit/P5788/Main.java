package algorithmsContest.charpt1.practice.submit.P5788;

import java.util.Scanner;

/**
 * @className: Main
 * @description: TODO 待优化MLE
 * @version: 1.0
 * @author: minsky
 * @date: 2022/12/15
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] a =  new int[n + 3];
        for(int i = 1; i <= n; i++)
            a[i] = scanner.nextInt();

        MyStack stack = new MyStack();
        int[] ans = new int[n + 1];

        for(int j = n ; j > 0; j--){
            while(!stack.isEmpty() && a[stack.top()] <= a[j])
                stack.pop();
            if(stack.isEmpty()) ans[j] = 0;
            else ans[j] = stack.top();
            stack.push(j);
        }
        for(int i = 1; i <= n; i++)
            System.out.printf("%d ", ans[i]);
    }

    private static final int STACK_SIZE = 10_000;

    static class MyStack{
        int[] a = new int[STACK_SIZE];
        int t = 0;
        void push(int x){a[++t] = x;}
        int top(){return a[t];}
        void pop(){t--;}
        boolean isEmpty(){return t == 0 ? true : false;}
    }
}
