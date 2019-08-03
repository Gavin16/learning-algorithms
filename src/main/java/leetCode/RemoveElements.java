package leetCode;

import utils.LinkedListUtil;
import utils.ListNode;

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
        ListNode head1 = LinkedListUtil.genLinkedList(5);

        LinkedListUtil.showLinkedList(head1);

        ListNode res = removeElements(head1, 3);
        LinkedListUtil.showLinkedList(res);
    }


    /**
     * 移除链表中值为 val 的元素
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements(ListNode head, int val) {
        ListNode root = new ListNode(0);
        ListNode cursor = root;
        ListNode cur = head;

        while(cur != null){
            if(cur.val != val){
                cursor.next = cur;
                cursor = cursor.next;
            }
            cur = cur.next;
        }
        cursor.next = null;

        return root.next;
    }
}
