package algorithmsContest.charpt1.practice.submit.P1714;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @className: Main
 * @description: TODO
 * @version: 1.0
 * @author: minsky
 * @date: 2022/12/14
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = scanner.nextInt();
        }
        // 计算前缀和
        int[] sum = new int[n + 1];
        sum[0] = 0;
        for(int i = 1; i <= n; i++){
            sum[i] = sum[i - 1] + a[i - 1];
        }
        // 根据前缀和计算 m 长度区间内,最大和
        int max = Integer.MIN_VALUE;
        Deque<Integer> deque = new LinkedList<>();
        for(int j = 0; j <= n; j++){
            while(!deque.isEmpty() && j - deque.getFirst() > m)
                deque.removeFirst();
            while(!deque.isEmpty() && sum[j] < sum[deque.getLast()])
                deque.removeLast();
            deque.addLast(j);
            if(deque.size() == 1)
                max = Math.max(max, sum[deque.getFirst()]);
            else
                max = Math.max(max, sum[deque.getLast()] - sum[deque.getFirst()]);
        }
        System.out.println(max);
    }
}
