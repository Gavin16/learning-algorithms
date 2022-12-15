package algorithmsContest.charpt1.practice.submit.P2032;

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
}
