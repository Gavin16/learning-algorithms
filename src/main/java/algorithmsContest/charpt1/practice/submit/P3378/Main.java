//package algorithmsContest.charpt1.practice.submit.P3378;

import java.util.Scanner;

/**
 * @className: Main
 * @description: TODO
 * @version: 1.0
 * @author: minsky
 * @date: 2022/12/29
 */
public class Main {


    private static final int N = 1000_000;
    private static int[] heap = new int[N + 5];
    private static int len = 0;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        while(n-- > 0){
            String line = scanner.nextLine();
            String[] s = line.split(" ");
            int cmd = Integer.parseInt(s[0]);
            if(cmd == 1){
                int input = Integer.parseInt(s[1]);
                push(input);
            }else if(cmd == 2){
                System.out.println(heap[1]);
            }else{
                pop();
            }
        }

    }


    // 入堆操作
    public static void push(int x){
        heap[++len] = x;
        int i = len;
        while(i > 1 && heap[i] < heap[i / 2]){
            swap(i, i/2);
            i = i / 2;
        }
    }

    private static void swap(int pos1, int pos2){
        int temp = heap[pos1];
        heap[pos1] = heap[pos2];
        heap[pos2] = temp;
    }

    // 出堆操作
    public static void pop(){
        heap[1] = heap[len--];
        int i = 1;
        while(2 * i <= len){
            int son = 2 * i;
            // 选择两个儿子中较小的与父节点进行交换
            if(son < len && heap[son + 1] < heap[son])
                son++;
            if(heap[son] < heap[i]){
                swap(son, i);
                i = son;
            }else break;
        }
    }

}
