package leetCode;

import dataStruct.ListNode;
import utils.LinkedListUtil;

/**
 *  《142. 环形链表 II》
 *  给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 *
 * 说明：不允许修改给定的链表。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：tail connects to node index 1
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 */
public class HasCycle2 {


    public static void main(String[] args) {
        ListNode listNode = LinkedListUtil.genLinkedList(2);

        LinkedListUtil.showLinkedList(listNode);

        detectCycle(listNode);
    }

    /**
     * 双重快慢指针找链表中环的入口
     */
    static ListNode detectCycle(ListNode head) {
        if(null == head || head.next == null) return null;

        ListNode fast = head, slow = fast;
        // 判断链表是否含环,不含环则返回null
        do{
            slow = slow.next;
            if(fast != null && fast.next != null){
                fast = fast.next.next;
            }else{
                break;
            }
        }while(fast != slow);
        if(fast == null || fast.next == null) return null;

        // 若含环，找出环的入口
        fast = head;
        while(fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
