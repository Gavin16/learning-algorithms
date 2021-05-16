package leetcode;

import utils.LinkedListUtil;
import datastruct.ListNode;

/**
 * 《LeetCode 92. 反转链表 II》
 *
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 *
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 *
 */
public class ReverseBetween {



    public static void main(String[]args){
        ListNode list = LinkedListUtil.genSortedLinkedList(5);
        LinkedListUtil.showLinkedList(list);
        ListNode res = reverseBetween(list, 2, 5);
        LinkedListUtil.showLinkedList(res);
    }

    /**
     * @param head
     * @param m
     * @param n
     * @return
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode first = head, last = head ,next;
        ListNode preF = head, afterL = null, curr = head, preC = head ;
        if(m == n) return head;

        for(int i = 1 ; curr != null; i++){
            if(i == m) {
                first = curr;
                preF = preC;
            }
            if( i > m && i <= n ){
                next = curr.next;
                curr.next = preC;
                // 反转完最后一个节点后, 退出循环
                if(i == n){
                    last = curr;
                    afterL = next;
                    break;
                }
                preC = curr;
                curr = next;
            }else{
                preC = curr;
                curr = curr.next;
            }
        }
        // 连接各个节点
        if(preF == first){
            preF.next = afterL != null ? afterL : null;
            head = last;
        }
        else
            preF.next = last;

        if(first != last){
            if(afterL != null)
                first.next = afterL;
            else
                first.next = null;
        }
        return head;
    }

    /**
     * 反转 m 到 n 之间的链表
     * @param head
     * @param m
     * @param n
     */
    public static void reverseBetween1(ListNode head, int m, int n){

    }
}
