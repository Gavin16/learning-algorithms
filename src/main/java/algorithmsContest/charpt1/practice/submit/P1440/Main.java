package algorithmsContest.charpt1.practice.submit.P1440;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
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

}
