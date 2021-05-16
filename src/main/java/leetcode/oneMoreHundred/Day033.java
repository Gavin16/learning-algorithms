package leetcode.oneMoreHundred;

import datastruct.ListNode;
import utils.LinkedListUtil;

/**
 * 92. 反转链表 II
 *
 * 给你单链表的头指针 head 和两个整数left 和 right ，其中left <= right 。
 * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 *
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 */
public class Day033 {

    public static void main(String[] args) {
        ListNode listNode = LinkedListUtil.genSortedLinkedList(5);
        LinkedListUtil.showLinkedList(listNode);

        ListNode reverse = reverseBetween1(listNode, 2, 5);
        LinkedListUtil.showLinkedList(reverse);
    }


    /**
     * 92. 反转链表 II
     * 直观解法：
     * ① 找出left ,right 对应位置的节点; 以及left之前 preLeft 和right 之后的的节点 afterRight
     * ② 反转left 到 right 范围内的节点
     * ③ preLeft 指向反转后的头结点，反转后的尾节点指向 afterRight
     *
     * 若 left = 1 ，left 之前就没有节点了，这就需要在left 之前加入一个无效的空节点用来方便操作
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode sentinel = new ListNode(-1);
        sentinel.next = head;
        ListNode preLeft = sentinel,rightNode = head;

        for(int i = 1 ; i < left  ; i++){
            preLeft = preLeft.next;
        }
        ListNode leftNode = preLeft.next;

        for(int j = 1 ; j < right ; j++){
            rightNode = rightNode.next;
        }
        ListNode afterRight = rightNode.next;

        //反转leftNode 开始的节点,反转后任然使用原节点
        rightNode.next = null;
        reverseLinkedList(leftNode);

        preLeft.next = rightNode;
        leftNode.next = afterRight;

        return sentinel.next;
    }

    /**
     * 原地反转链表
     * @param head
     */
    private static void reverseLinkedList(ListNode head) {
        ListNode pre = null, curr = head;
        while(curr != null){
            ListNode tmp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = tmp;
        }
    }


    /**
     * 92. 反转链表 II
     * 使用头插法实现
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public static ListNode reverseBetween1(ListNode head, int left, int right) {
        ListNode sentinel = new ListNode(-1);
        sentinel.next = head;

        ListNode guard = sentinel, pos = head;
        for(int i = 1 ; i < left ; i++){
           guard = guard.next;
           pos = pos.next;
        }
        // 每次遍历时都将该节点作为 guard的下一个节点，并将该节点的next 指针指向guard上一次指向的节点
        for(int i = left + 1; i <= right ; i++){
            ListNode forFirst = pos.next;
            ListNode next = forFirst.next;
            pos.next = next;
            forFirst.next = guard.next;
            guard.next = forFirst;
        }
        return sentinel.next;
    }
}
