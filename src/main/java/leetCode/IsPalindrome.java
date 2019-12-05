package leetCode;

import utils.LinkedListUtil;
import dataStruct.ListNode;

import java.util.Stack;

/**
 * @ClassName: IsPalindrome
 * @CopyRight: wufangmin
 * @Description: 回文链表
 * @Author: wufangmin
 * @Date: 2019/12/3 16:08
 * @Version: 1.0
 */
public class IsPalindrome {

    public static void main(String[] args) {
        ListNode listNode = LinkedListUtil.genLinkedList(2);
        LinkedListUtil.showLinkedList(listNode);
        System.out.println(isPalindrome1(listNode));
    }

    /**
     * 请判断一个链表是否为回文链表。
     *
     * 示例 1:
     *
     * 输入: 1->2
     * 输出: false
     */
    static boolean isPalindrome(ListNode head) {
        if(head == null) return true;

        Stack<ListNode> nStack = new Stack<>();
        ListNode curr = head;
        while(curr != null){
            nStack.push(curr);
            curr = curr.next;
        }

        // 判断是否回文
        curr = head;
        ListNode backIt;
        while(curr != null){
            backIt = nStack.pop();
            if(backIt.val != curr.val){
                return false;
            }
            curr = curr.next;
        }
        return true;
    }

    /**
     * 用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题
     *
     * (1)快慢指针找链表中点
     * (2)反转中点后半部分的链表
     * (3)比较链表前后半部分
     */
    static boolean isPalindrome1(ListNode head) {
        if(head == null || head.next == null) return true;
        ListNode sentinel = new ListNode(-1) ,fast = sentinel, slow = sentinel, mid;
        sentinel.next = head;

        while(fast != null){
            fast = fast.next;
            if(fast != null){
                fast = fast.next;
                slow = slow.next;
            }
        }
        // slow 对应为链表的中点(偶数长度时在n/2处，基数长度时在n/2 + 1处)
        ListNode bHalf = slow.next, bCurr = bHalf, next = null;
        //反转链表
        while(bCurr != null){
            ListNode prev = bCurr.next;
            bCurr.next = next;
            next = bCurr;
            if(prev == null){
                // 反转后首节点返回 bHalf
                bHalf = bCurr;
            }
            bCurr = prev;
        }
        // 循环比较前后半部分
        while(head != null && bHalf != null){
            if(head.val != bHalf.val) return false;
            head = head.next ;
            bHalf = bHalf.next;
        }
        return true;
    }
}
