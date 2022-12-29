package algorithmsContest.charpt1.practice;

/**
 * @className: Practice_1_5
 * @description:
 *
 * 练习题:
 * P3378/P1090/P1168/P2085/P2827/P3045
 *
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2022/12/29
 */
public class Practice_1_5 {


    private static final int N = 1000_000;
    private int[] heap = new int[N];
    private int len = 0;


    // 入堆操作
    public void push(int x){
        heap[++len] = x;
        int i = len;
        while(i > 1 && heap[i] < heap[i / 2]){
            swap(i, i/2);
            i = i / 2;
        }
    }

    private void swap(int pos1, int pos2){
        int temp = heap[pos1];
        heap[pos1] = heap[pos2];
        heap[pos2] = temp;
    }

    // 出堆操作
    public void pop(){
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
