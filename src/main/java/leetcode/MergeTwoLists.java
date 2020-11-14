package leetcode;

import utils.LinkedListUtil;
import datastruct.ListNode;

/**
 * @Class: MergeTwoLists
 * @Description:
 *
 * 《LeetCode 21. 合并两个有序链表》
 *
 *  将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 *  示例：
 *
 *  输入：1->2->4, 1->3->4
 *  输出：1->1->2->3->4->4
 *
 * @Author: Minsky
 * @Date: 2019/8/3 10:21
 * @Version: v1.0
 */
public class MergeTwoLists {

    public static void main(String[]args){
        ListNode head1 = LinkedListUtil.genSortedLinkedList(5);
        ListNode head2 = LinkedListUtil.genSortedLinkedList(4);
        LinkedListUtil.showLinkedList(head1);
        LinkedListUtil.showLinkedList(head2);
        ListNode merged = mergeTwoLists(head1, head2);
        LinkedListUtil.showLinkedList(merged);
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode cur1 = l1;
        ListNode cur2 = l2;

        ListNode res = new ListNode(0);
        ListNode temp = res;
        while(cur1 != null || cur2 != null){
            if(cur1 == null){
                while(cur2 !=null){
                    temp.next = new ListNode(cur2.val);
                    cur2 = cur2.next;
                    temp = temp.next;
                }
            }else if(cur2 == null){
                while(cur1 !=null ){
                    temp.next = new ListNode(cur1.val);
                    cur1 = cur1.next;
                    temp = temp.next;
                }
            }else{
                if(cur1.val <= cur2.val){
                    temp.next = new ListNode(cur1.val);
                    cur1 = cur1.next;
                }else{
                    temp.next = new ListNode(cur2.val);
                    cur2 = cur2.next;
                }

            }
            temp = temp.next;
        }
        return res.next;
    }
}
