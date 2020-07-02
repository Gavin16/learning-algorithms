package leetCode.oneMoreHundred;

import dataStruct.ListNode;
import utils.LinkedListUtil;


/**
 * 链表5连
 * @Date: 2020年7月2日
 * ==============================================================================
 * 206. 反转链表
 * ==============================================================================
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 * ==============================================================================
 * 141. 环形链表
 * ==============================================================================
 * 给定一个链表，判断链表中是否有环。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。
 *
 * ==============================================================================
 * 21. 合并两个有序链表
 * ==============================================================================
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * ==============================================================================
 * 剑指 Offer 22. 链表中倒数第k个节点
 * ==============================================================================
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 * 例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 k = 2.
 *
 * 返回链表 4->5.
 * ==============================================================================
 * 876. 链表的中间结点
 * ==============================================================================
 * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
 *
 * 如果有两个中间结点，则返回第二个中间结点。
 * 输入：[1,2,3,4,5,6]
 * 输出：此列表中的结点 4 (序列化形式：[4,5,6])
 * 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
 * ==============================================================================
 */
public class Day003 {

    public static void main(String[] args) {
        ListNode listNode = LinkedListUtil.genLinkedList(1);
        LinkedListUtil.showLinkedList(listNode);
        ListNode listNode1 = reverseList1(listNode);
        LinkedListUtil.showLinkedList(listNode1);
    }

    /**
     * @Title: 206. 反转链表
     * @Version: 版本1 迭代反转
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        if(null == head || head.next == null) return head;

        ListNode curr = head , next = head.next;
        while(next != null){
            if(curr == head){
                curr.next = null;
            }
            ListNode tmp = next.next;
            next.next = curr;
            curr = next;
            next = tmp;
        }
        return curr;
    }

    /**
     * @Title: 206. 反转链表
     * @Version: 版本2 迭代反转简化
     * @param head
     * @return
     */
    public static ListNode reverseList1(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null){
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }

    /**
     * @Title: 206. 反转链表
     * @Version: 版本3 递归反转
     * @Description: 返回链表的尾结点,每级递归中反转链表同时设置 head.next = null
     * @param head
     * @return
     */
    public static ListNode reverseList2(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode cur = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return cur;
    }

    /**
     * @Title: 141. 环形链表
     * @Version: 版本1 快慢指针法判断链表中的环
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;
        ListNode slow = head, fast = head.next.next;
        while(fast != null && fast.next != null){
            if(slow == fast) return true;
            fast = fast.next.next;
            slow = slow.next;
        }
        return false;
    }

    /**
     * @Title: 21. 合并两个有序链表
     * @Version: 版本1 使用哨兵修改原链表结构
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode sentinel = new ListNode(-1),curr = sentinel;

        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                curr.next = l1;
                l1 = l1.next;
            }else{
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        curr.next  = l1 != null ? l1 : l2;
        return sentinel.next;
    }

    /**
     * @Title: 21. 合并两个有序链表
     * @Version: 版本2
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        return null;
    }

}
