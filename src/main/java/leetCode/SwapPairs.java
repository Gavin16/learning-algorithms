package leetCode;

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

    /**
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
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
}
