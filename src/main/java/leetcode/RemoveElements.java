package leetcode;

import utils.LinkedListUtil;
import datastruct.ListNode;

/**
 * @Class: RemoveElements
 * @Description:
 *
 *  《LeetCode 203. 移除链表元素》
 *
 *  删除链表中等于给定值 val 的所有节点。
 *
 *  示例:
 *
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 *
 *
 * @Author: Minsky
 * @Date: 2019/8/3 11:22
 * @Version: v1.0
 */
public class RemoveElements {

    public static void main(String[]args){
        ListNode head1 = LinkedListUtil.genLinkedList(9);

        LinkedListUtil.showLinkedList(head1);

        ListNode res = removeElements1(head1, 2);
        LinkedListUtil.showLinkedList(res);
    }


    /**
     * 移除链表中值为 val 的元素 -- 使用哨兵节点
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements(ListNode head, int val) {
        ListNode sentinel = new ListNode(0);
        ListNode cursor = sentinel;
        ListNode curr = head;

        while(curr != null){
            if(curr.val != val){
                cursor.next = curr;
                cursor = cursor.next;
            }
            curr = curr.next;
        }
        cursor.next = null;

        return sentinel.next;
    }


    /**
     * 递归移除链表中值为 val 的元素
     * 循环改递归(2)  -- (√)
     */
    public static ListNode removeElements1(ListNode head, int val) {
        return recursiveRemove(head,val);
    }

    static ListNode recursiveRemove(ListNode head,int val){
        if(head == null) return head;

        if(head.val == val){
            while(head != null && head.val == val){
                head = head.next;
            }
            head = recursiveRemove(head,val);
        }else{
            head.next = recursiveRemove(head.next,val);
        }

        return head;
    }

}
