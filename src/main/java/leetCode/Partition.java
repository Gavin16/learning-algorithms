package leetCode;

import utils.LinkedListUtil;
import dataStruct.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 《LeetCode 86. 分隔链表》
 *
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 *
 * 你应当保留两个分区中每个节点的初始相对位置。
 *
 * 示例:
 *
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 *
 */
public class Partition {

    public static void main(String[]args){
        ListNode list = LinkedListUtil.genLinkedList(7);
        LinkedListUtil.showLinkedList(list);
        ListNode partition = partition(list, 3);
        LinkedListUtil.showLinkedList(partition);
    }


    /**
     * 方法一： 找出删除拼接法实现
     * @param head
     * @param x
     * @return
     */
    public static ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null) return head;
        // 移除到链表中的元素到 List 中
        ListNode curr = head , pre = head, first = pre;
        List<ListNode> list = new ArrayList<>();
        while(curr != null){
            ListNode next = curr.next;
            if(curr.val < x){
                list.add(curr);
                if(pre != null) pre.next = next;

                if(curr == first) first = next;
            }else{
                pre = curr;
            }
            curr = next;
        }

        // 取出栈中的元素 首位链接
        ListNode l1 = null, l2 = null, h1 = null;
        for(int i = 0 ; i < list.size(); i++){
            if(l1 == null){
                l1 = list.get(i);
                l2 = list.get(i);
                h1 = l1;
            }else if(l1 == l2){
                l2 = list.get(i);
                l1.next = l2;
            }else{
                l1 = l2;
                l2 = list.get(i);
                l1.next = l2;
            }
        }

        // 找到移除后原链表中第一个大于x的节点，将小于x的链表插入在该节点前
        if(h1 != null)
            l2.next = first != null ? first : null;
        else
            h1 = first;
        return h1;
    }

    /**
     *  借助虚拟头结点实现
     */
    public static ListNode partition1(ListNode head, int x) {
        ListNode dummyHead1 = new ListNode(0);
        ListNode dummyHead2 = new ListNode(0);
        ListNode node1 = dummyHead1;
        ListNode node2 = dummyHead2;
        while (head != null) {
            if (head.val < x) {
                node1.next = head;
                node1 = node1.next;
                node1.next = null;
            } else {
                node2.next = head;
                node2 = node2.next;
                node2.next = null;
            }
            head = head.next;
        }
        node1.next = dummyHead2.next;
        return dummyHead1.next;
    }
}
