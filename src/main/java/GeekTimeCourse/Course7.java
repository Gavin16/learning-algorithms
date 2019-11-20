package GeekTimeCourse;

import utils.LinkedListUtil;
import utils.ListNode;

import java.util.LinkedList;

/**
 * @ClassName: Course7
 * @CopyRight: wufangmin
 * @Description: 链表的熟练实现 （另外可以了解下 LeetCode 上的题目：206，141，21，19，876）
 * @Author: wufangmin
 * @Date: 2019/11/19 14:36
 * @Version: 1.0
 *
 * （1）单链表反转
 * （2）链表中环的检测
 * （3）两个有序的链表合并
 * （4）删除链表倒数第 n 个结点
 * （5）求链表的中间结点
 *
 */
public class Course7 {

    public static void main(String[] args) {
        ListNode listNode = LinkedListUtil.genSortedLinkedList(9);

        LinkedListUtil.showLinkedList(listNode);
        LinkedListUtil.showLinkedList(inverseList(listNode));
    }

    /*********************** （1）单链表反转  *************************/
    static ListNode inverseList(ListNode head){
        if(null == head || head.next == null) return head;
        ListNode prev = head, curr = head.next, next = head.next.next;
        // 首节指向null
        prev.next = null;
        // 只有两个节点
        if(next == null){
            curr.next = prev;
            return curr;
        }
        // 三个及以上节点
        do {
            curr.next = prev;
            prev = curr;
            curr = next;
            next = next.next;
        }while(next != null);
        // 设置默认节点指向倒数第二个节点
        curr.next = prev;
        return curr;
    }



    /*********************** （2）链表中环的检测 *************************/

    /**
     * 若存在环则返回true，不存在返回false
     * 使用快慢指针检测: 通过LeetCode 测试
     */
    static boolean circleDetection(ListNode head){
        if(null == head || head.next == null) return false;

        ListNode fast = head;
        ListNode slow = head;
        // 节点个数大于2
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow) return true;
        }
        return false;
    }

    /*********************** （3）两个有序的链表合并  *************************/
    /**
     * 合并两个有序链表
     */
    static ListNode mergeSortedList(ListNode list1,ListNode list2){
        // 将 list2 合并到 list1 中
        ListNode curr = list1;
        while(curr != null){

        }

        return list1;
    }

    /*********************** （4）删除链表倒数第 n 个结点 *************************/
    /*********************** （5）求链表的中间结点 *************************/
}
