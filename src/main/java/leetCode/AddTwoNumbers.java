package leetCode;

import edu.princeton.cs.Stack;
import utils.LinkedListUtil;
import utils.ListNode;

/**
 * 《LeetCode 445. 两数相加 II》
 *
 * 给定两个非空链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储单个数字。将这两数相加会返回一个新的链表。
 *
 *  
 *
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *
 * 进阶:
 *
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 *
 * 示例:
 *
 * 输入: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出: 7 -> 8 -> 0 -> 7
 *
 */
public class AddTwoNumbers {

    public static void main(String[]args){
        int[] arr1 = new int[]{7,2,4,3};
        int[] arr2 = new int[]{5,6,4};
        ListNode list1 = LinkedListUtil.genLinkedListFromArray(arr1);
        ListNode list2 = LinkedListUtil.genLinkedListFromArray(arr2);
        LinkedListUtil.showLinkedList(list1);
        LinkedListUtil.showLinkedList(list2);


        ListNode listNode = addTwoNumbers2(list1, list2);

        LinkedListUtil.showLinkedList(listNode);
    }

    /**
     * 方法一：先翻转链表 然后相加
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode rl1 = reverse(l1);
        ListNode rl2 = reverse(l2);

        ListNode resHead = new ListNode(0) , resCurr  = resHead;
        int carry = 0 ;
        while(rl1 != null || rl2 != null  || carry != 0){
            int val1 = rl1 != null ? rl1.val : 0 ;
            int val2 = rl2 != null ? rl2.val : 0 ;

            int val = (val1 + val2 + carry) % 10 ;
            carry = (val1 + val2 + carry) / 10 ;
            resCurr.next = new ListNode(val);

            resCurr = resCurr.next;
            rl1 = (rl1 != null) ? rl1.next : rl1;
            rl2 = (rl2 != null) ? rl2.next : rl2;
        }

        return reverse(resHead.next);
    }


    /**
     * 方法二： 使用两个栈
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {

        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();

        ListNode cur1 = l1 , cur2 = l2;

        while(cur1 != null){
            stack1.push(cur1);
            cur1 = cur1.next;
        }
        while(cur2 != null){
            stack2.push(cur2);
            cur2 = cur2.next;
        }

        ListNode res = new ListNode(0), resCur = res, pre = null;
        int carry = 0;
        while(stack1.size() > 0 || stack2.size() > 0 || carry != 0){
            ListNode ele1 = stack1.isEmpty() ? null : stack1.pop();
            ListNode ele2 = stack2.isEmpty() ? null : stack2.pop();

            int val1 = ele1 != null ? ele1.val : 0;
            int val2 = ele2 != null ? ele2.val : 0;

            int sum = (val1 + val2 + carry) % 10;
            carry = (val1 + val2 + carry) / 10;

            resCur.next = new ListNode(sum);
            resCur = resCur.next;
        }
        resCur = res.next;
        while(resCur != null){
            stack1.push(resCur);
            resCur = resCur.next;
        }
        while(stack1.size() > 0){
            ListNode hi = stack1.pop();
            if(pre != null){
                pre.next = hi;
                pre = pre.next;
            }else {
                pre = hi;
                res = pre;
            }
        }
        pre.next = null;

        return res;
    }

    /**
     * 翻转链表
     * @param head
     */
    private static ListNode reverse(ListNode head){
        if(head == null || head.next == null) return head;

        ListNode pre = head , curr = head.next;

        while(curr != null){
            ListNode next = curr.next;
            curr.next = pre;

            pre = curr;
            curr = next;
        }
        head.next = null;
        return pre;
    }
}
