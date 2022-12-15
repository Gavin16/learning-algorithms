package algorithmsContest.charpt1.practice;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @className: Practice_1_2
 * @description: 1.2 队列练习
 *
 *
 * 练习题:
 * (1) 单调队列简单题: 洛谷 P1440/P2032/P1714/P2629/P2422/P1540
 * (2) 单调队列优化DP: 洛谷 P3957/P1725
 * (3) 二维队列： 洛谷 P2776
 *
 *
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2022/12/12
 */
public class Practice_1_2 {


    public static void main(String[] args) {
        Practice_1_2 instance = new Practice_1_2();
//        instance.findRangeMin();
//        instance.numScan();
        instance.cutCake();
    }


    /**
     * P1440 求m区间内的最小值
     * 一个含有 n 项的数列，求出每一项前的 m 个数到它这个区间内的最小值。若前面的数不足 m 项则从第 1 个数开始，若前面没有数则输出 0。
     *
     * 输入格式：
     * 第一行两个整数,分别表示n，m
     * 第二行，n 个正整数, 为所给定的数列ai
     * 输出格式:
     * n行,每行一个整数, 第i个数为序列中ai之前m个数的最小值
     * ​
     * 输入输出样例:
     * 输入:
     * 6 2
     * 7 8 1 4 3 2
     * 输出:
     * 0
     * 7
     * 7
     * 1
     * 1
     * 3
     * 说明/提示
     * 对于100%的数据,保证1<= m <= n <= 2*10^6, 1<= ai <= 3 * 10^7
     *
     */
    public void findRangeMin(){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt();
        int[] arr = new int[n + 5];
        for(int i = 0; i < n; i++){
            int k = scanner.nextInt();
            arr[i] = k;
        }
        Deque<Integer> queue = new LinkedList<>();
        for(int j = 0; j < n; j++){
            if(queue.isEmpty()){
                queue.addLast(j);
                System.out.println(0);
            }else{
                while(!queue.isEmpty() && j - queue.getFirst() > m)
                    queue.removeFirst();
                System.out.println(arr[queue.getFirst()]);
                while(!queue.isEmpty() && arr[j] < arr[queue.getLast()])
                    queue.removeLast();
                queue.addLast(j);
            }
        }
    }


    /**
     * P2032 扫描
     * 有一个 1 * n 的矩阵， 有 n 个整数
     * 现在给你一个可以盖住 k 个数的木板
     * 一开始木板盖住了矩阵的第 1 ~ k个数，每次讲木板向右移动一个单位, 知道右端与第n个数重合
     * 每次移动前输出被盖住的数字中最大的数是多少
     *
     * 输入格式:
     * 共 n - k + 1 行, 每行一个整数
     * 第 i 行表示第 i ~ i + k - 1 个数中最大值是多少
     *
     * 输入输出样例:
     * 输入:
     * 5 3
     * 1 5 3 4 2
     * 输出：
     * 5
     * 5
     * 4
     * 说明/提示
     * 对于20% 的数据， 1 <= k <= n <= 10^3
     * 对于50% 的数据， 1 <= k <= n <= 10^4
     * 对于100%的数据, 1 <= k <= n <= 2*10^6
     *
     * 时间限制: 1s
     * 内存限制:125M
     *
     */
    public void numScan(){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int[] numArr = new int[n];
        for(int i = 0; i < n ; i++){
            numArr[i] = scanner.nextInt();
        }
        Deque<Integer> queue = new LinkedList<>();
        for(int j = 0; j < n; j++){
            while(!queue.isEmpty() && (j - queue.getFirst()) >= k)
                queue.removeFirst();
            while(!queue.isEmpty() && numArr[j] > numArr[queue.getLast()])
                queue.removeLast();
            queue.addLast(j);
            if(j >= k - 1) System.out.println(numArr[queue.getFirst()]);
        }
    }


    /**
     * P1714 切蛋糕
     * 今天是小Z的生日,同学们为他带来了一块蛋糕。这块蛋糕是一个长方体, 被用不同色彩
     * 分成了 n 个相同的小块， 每小块都有对应的幸运值。
     *
     * 小Z作为寿星，自然希望吃到的蛋糕的幸运值总和最大，但是小Z 最多又只能吃 m 小块的蛋糕。
     * 请你帮他从这 n 小块中找出连续的 k(1 <= k <= m) 块蛋糕, 使得其上的总幸运值最大。
     *
     * 形式化的，在数列(Pn) 中，找出一个子段 [l,r](r - l + 1 <= m) 最大化 Sum(pi) i∈[l,r]
     *
     * 输入格式
     * 第一行两个整数 n,m . 分别代表共有n小块蛋糕 ， 小Z最多只能吃 m 小块
     * 第二行n个整数， 第 i 个整数 pi 代表第i小块蛋糕的幸运值
     *
     * 输出格式
     * 仅一行一个整数，即小 Z 能够得到的最大幸运值
     *
     * 输入样例：
     * 5 2
     * 1 2 3 4 5
     * 输出样例:
     * 9
     *
     * 说明/提示
     * 数据规模与约定
     * 对于 20% 的数据  有  1 <= n <= 100
     * 对于 100% 的数据  有 1 <= n <= 5 * 10^5 , |pi| <= 500
     */
    public void cutCake(){
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
