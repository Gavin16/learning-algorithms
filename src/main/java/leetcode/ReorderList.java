package leetcode;

import datastruct.ListNode;
import utils.LinkedListUtil;

/**
 * 《143. 重排链表》
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 *
 * L0 → L1 → … → Ln - 1 → Ln
 * 请将其重新排列后变为：
 *
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 输入：head = [1,2,3,4]
 * 输出：[1,4,2,3]
 *
 * 输入：head = [1,2,3,4,5]
 * 输出：[1,5,2,4,3]
 *
 * 提示：
 *
 * 链表的长度范围为 [1, 5 * 104]
 * 1 <= node.val <= 1000
 */
public class ReorderList {


    /**
     * 解题思路:
     * (1) 对链表分段成前后两部分
     * (2) 反转链表后半截
     * (3) 合并两部分链表
     *
     * 考虑边界条件:
     * (1) 链表长度 < 2 : 无需改动
     * (2) 链表拆分前部分需要比后部分长
     *
     */
    public static void reorderList(ListNode head) {
        // 长度校验
        if(head.next == null || head.next.next == null)
            return;
        // 链表拆分: 快慢指针实现
        ListNode lastHalf = findLastHalf(head);
        // 链表反转
        ListNode reversedHead = reverseListNode(lastHalf);
        // 链表合并
        mergeLinkedList(head, reversedHead);
    }


    /**
     * 合并链表
     */
    private static ListNode mergeLinkedList(ListNode head, ListNode reversedHead) {
        ListNode frontHead = head, backHead = reversedHead;
        ListNode sentinel = new ListNode(-1),curr = sentinel;

        while(frontHead != null && backHead != null){
            curr.next = frontHead;
            frontHead = frontHead.next;

            curr = curr.next;
            curr.next = backHead;
            backHead = backHead.next;

            curr = curr.next;
        }
        curr.next = frontHead;
        return sentinel.next;
    }


    /**
     * 反转链表
     */
    private static ListNode reverseListNode(ListNode lastHalf) {
        if(lastHalf == null || lastHalf.next == null)
            return lastHalf;
        ListNode pre = null;
        ListNode curr = lastHalf;

        while(curr != null){
            ListNode next =  curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }


    /**
     * 快慢指针找出链表后半部分首节点
     */
    private static ListNode findLastHalf(ListNode head) {
        ListNode  fast = head, slow = head, slowPre = null;
        while(fast != null){
            slowPre = slow;
            slow = slow.next;
            fast = fast.next;
            if(fast != null)
                fast = fast.next;
        }
        slowPre.next = null;
        return slow;
    }


    public static void main(String[] args) {
        ListNode listNode = LinkedListUtil.genLinkedList(3);
        LinkedListUtil.showLinkedList(listNode);

        reorderList(listNode);
//        LinkedListUtil.showLinkedList(lastHalfHead);
        LinkedListUtil.showLinkedList(listNode);
    }
}
