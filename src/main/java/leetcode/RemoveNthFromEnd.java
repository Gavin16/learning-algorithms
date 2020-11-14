package leetcode;

import utils.LinkedListUtil;
import datastruct.ListNode;

/**
 * 《LeetCode 19. 删除链表的倒数第N个节点》
 *
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *
 * 给定的 n 保证是有效的。
 *
 *
 */
public class RemoveNthFromEnd {


    public static void main(String[]args){
        ListNode listNode = LinkedListUtil.genSortedLinkedList(5);
        LinkedListUtil.showLinkedList(listNode);

        ListNode res = removeNthFromEnd(listNode, 3);
        LinkedListUtil.showLinkedList(res);
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p1 = head;
        ListNode p2 = head , pre = head;
        ListNode curr = head;
        for(int cnt = 0; curr.next != null; cnt++){

            if(cnt >= n-1){
                pre = p2;
                p2 = p2.next;
            }

            curr = curr.next;
        }
        pre.next =p2.next;

        return pre == p2 ? pre.next : head;
    }
}
