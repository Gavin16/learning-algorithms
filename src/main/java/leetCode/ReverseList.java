package leetCode;

import utils.LinkedListUtil;
import dataStruct.ListNode;

import java.util.Stack;

/**
 * @Class: ReverseList
 * @Description:
 *
 * 《LeetCode 206. 反转链表》
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 *
 *
 * @Author: Minsky
 * @Date: 2019/8/1 16:32
 * @Version: v1.0
 */
public class ReverseList {

    public static void main(String[]args){
        ListNode listNode = LinkedListUtil.genLinkedList(5);
        LinkedListUtil.showLinkedList(listNode);
        ListNode res = reverseList1(listNode);
        LinkedListUtil.showLinkedList(res);
    }

    public static ListNode reverseList(ListNode head) {
        if(head.next == null) return head;

        ListNode res = new ListNode(0) ;

        traverseList(res , head, head.next);

        return res;
    }

    /**
     * 反转链表循环实现
     * @param head
     * @return
     */
    public static ListNode reverseList1(ListNode head){
        Stack<ListNode> stack = new Stack<>();

        ListNode cursor = head;
        while(cursor!=null){
            stack.add(cursor);
            cursor = cursor.next;
        }


        ListNode res = stack.pop();
        ListNode temp = res;

        while(!stack.isEmpty()){
            temp.next = stack.pop();
            temp = temp.next;
        }
        temp.next = null;

        return res;
    }


    /**
     * 第一次写的东西 与 reverse 比较一下
     * @param res
     * @param head
     * @param next
     */
    private static void traverseList(ListNode res, ListNode head, ListNode next){
        if(next != null && next.next == null){
            res = next;

        }else{
            traverseList(res,head.next,next.next);
        }
        res.next = head;
    }

    /**
     * 注意与traverseList 比较下
     * @param pre
     * @param cur
     * @return
     */
    private static ListNode reverse(ListNode pre,ListNode cur){
        if(cur==null) return pre;
        ListNode next = cur.next;
        cur.next = pre;
        return reverse(cur,next);
    }

    public ListNode reverseList2(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while(cur!=null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


}
