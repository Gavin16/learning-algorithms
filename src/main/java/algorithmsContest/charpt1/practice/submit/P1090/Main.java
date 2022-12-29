package algorithmsContest.charpt1.practice.submit.P1090;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @className: Main
 * @description:
 *
 * 输入
 * 3
 * 1 2 9
 * 输出
 * 15
 *
 * 输入
 * 5
 * 1 3 6 9 11
 * 输出
 * 63
 *
 *
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2022/12/29
 */
public class Main {


    public static void main(String[] args) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String line = scanner.nextLine();
        String[] s = line.split(" ");
        for(int i = 0; i < n; i++){
            heap.offer(Integer.parseInt(s[i]));
        }
        int sum = 0;
        while(heap.size() > 1){
            Integer first = heap.poll();
            Integer second = heap.poll();
            int add = first + second;
            sum += add;
            heap.offer(add);
        }
        System.out.println(sum);
    }
}
