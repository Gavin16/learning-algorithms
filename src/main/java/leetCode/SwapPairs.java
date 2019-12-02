package leetCode;

import utils.LinkedListUtil;
import utils.ListNode;

/**
 * @Class: SwapPairs
 * @Description:
 *
 * 《LeetCode 24. 两两交换链表中的节点》
 *
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 *  
 *
 * 示例:
 *
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 *
 *
 * @Author: Minsky
 * @Date: 2019/8/4 14:47
 * @Version: v1.0
 */
public class SwapPairs {

    public static void main(String[] args) {
        ListNode listNode = LinkedListUtil.genLinkedList(7);

        LinkedListUtil.showLinkedList(listNode);

        LinkedListUtil.showLinkedList(swapPairs1(listNode));
    }

    /**
     * @param head
     * @return
     */
    static ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode pre = head , suf = head.next , res = suf , lp = null;
        while(pre != null && suf != null){
            ListNode next1 = suf.next;
            suf.next = pre ;
            pre.next = next1;
            // 保证每个两两交换之间的指向关系不断
            if(lp != null) lp.next = suf;

            if(next1 != null){
                lp = pre;
                pre = next1;
                suf = next1.next;
            }else{
                break;
            }
        }
        return res;
    }

    /**
     *  写出来和第一次实现差不多，代码结构相对变简单一些了
     */
    static ListNode swapPairs1(ListNode head){
        if(head == null || head.next == null) return head;

        ListNode odd = head, even = head.next, res = even , prev = null;
        while(odd != null && even != null){
            ListNode temp = even.next;
            even.next = odd;
            odd.next = temp;
            if(prev != null){
                prev.next = even;
            }

            prev = odd;
            odd = odd.next;

            if(odd != null) {
                even = odd.next;
            }
        }
        return res;
    }
}
