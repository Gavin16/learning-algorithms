package algorithmsContest.charpt1;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 1.2 队列
 *
 * @className: SlideWindow
 * @description: 滑动窗口/单调队列
 *
 * 例1.3 滑动窗口/单调队列(洛谷P1886)
 * 问题描述: 有一个长度为n的序列a, 以及一个大小为 k 的窗口, 从左边开始向右边滑动，每次滑动一个的单位，
 * 求出每次滑动窗口中的最大值和最小值。 例如，序列为[1, 3, -1, -3, 5, 3,6,7], k = 3 .
 *
 * 输入样例:
 * 8,3
 * 1 3 -1 -3 5 3 6 7
 *
 * 输出样例:
 * -1 -3 -3 -3 3 3
 * 3 3 5 5 6 7
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2022/12/4
 */
public class SlideWindow {

    public static void main(String[] args) {
        SlideWindow slideWindow = new SlideWindow();
        int[] a = {1, 3, -1, -3, 5, 3, 6, 7};
        slideWindow.solution1(3, a);
    }


    /**
     * 队列 a 中找出 k 为滑动窗长度中的最大和最小值
     * 使用单调队列实现
     *
     * @param k
     * @param a
     */
    public void solution1(int k , int[] a){
        Deque<Integer> deque = new LinkedList<>();
        int len = a.length;
        for(int i = 0; i < len; i++){
            while(!deque.isEmpty() && a[deque.getLast()] > a[i])
                deque.removeLast();
            deque.addLast(i);
            if(i >= k - 1){
                // 窗口移动, 超出 k 范围[i-k+1,..,i-1,i] 的元素需要移出窗口
                while(!deque.isEmpty() && deque.getFirst() <= i - k)
                    deque.removeFirst();
                System.out.printf("%d ", a[deque.getFirst()]);
            }
        }
        System.out.println();

        deque.clear();
        for(int j = 0; j < len; j++){
            while(!deque.isEmpty() && a[deque.getLast()] < a[j])
                deque.removeLast();
            deque.addLast(j);
            // 窗口长度达到 k 时,需要输出最大值
            if(j >= k - 1){
                while(!deque.isEmpty() && deque.getFirst() <= j - k)
                    deque.removeFirst();
                System.out.printf("%d ", a[deque.getFirst()]);
            }
        }
        System.out.println();
    }

}
