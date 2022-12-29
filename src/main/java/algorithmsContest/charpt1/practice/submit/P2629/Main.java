package algorithmsContest.charpt1.practice.submit.P2629;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @className: Main
 * @description:
 *
 * 好消息，坏消息
 * uni 在公司里面当秘书, 现在有 n 条消息要告知老板。每条消息有一个好坏度，这会影响老板的心情。
 * 告知完一条消息后，老板的心情等于老板之前的心情加上这条消息的好坏度。最开始老板的心情是 0，
 * 一旦老板心情到了 0 以下就会勃然大怒，炒了 Uim 的鱿鱼。Uim 为了不被炒，提前知道了这些消息（已经按时间的发生顺序进行了排列）的好坏度，
 * 希望知道如何才能不让老板发怒。
 * Uim 必须按照事件的发生顺序逐条将消息告知给老板。
 *
 * 他希望知道，有多少个 k，可以使从 k 号事件开始通报到 n 号事件然后再从 1 号事件通报到 k−1 号事件可以让老板不发怒。
 *
 * 输入格式
 * 第一行一个整数，表示有n个消息
 * 第二行 n 个整数，按时间顺序给出第i条消息的好坏度 Ai (-10^3 <= Ai <= 10^3)
 *
 * 输出格式
 * 一行一个整数，表示可行的方案个数
 *
 * 输入输出样例
 * 输入
 * 4
 * -3 5 1 2
 * 输出
 * 2
 *
 *
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2022/12/20
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        int[] s = new int[2 * n];
        for (int i = 1; i < 2 * n ; i++){
            if(i <= n) a[i - 1] = scanner.nextInt();
            s[i] = s[i - 1] + a[(i - 1) % n];
        }
        // 单调队列窗口长度为 n
        Deque<Integer> queue = new LinkedList<>();
        int ans = 0;
        for(int k = 1; k < 2 * n ; k++){
            while(!queue.isEmpty() && k - queue.getFirst() > n) queue.removeFirst();
            while(!queue.isEmpty() && s[k] < s[queue.getLast()]) queue.removeLast();
            queue.addLast(k);
            if(k >= n && s[queue.getFirst()] >= s[k - n]) ans++;
        }
        System.out.printf("%d", ans);
    }

}
