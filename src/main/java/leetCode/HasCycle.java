package leetCode;

import utils.ListNode;

import java.util.HashSet;

/**
 * @Class: HasCycle
 * @Description:
 *
 *  《LeetCode 141. 环形链表》
 *
 *  给定一个链表，判断链表中是否有环。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * @Author: Minsky
 * @Date: 2019/8/4 13:33
 * @Version: v1.0
 */
public class HasCycle {


    /**
     * 使用Map保存遍历过的节点  空间复杂度O(n)
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode curr = head;
        for(; curr != null ;){
            if(set.contains(curr)){
                return true;
            }else{
                set.add(curr);
            }
            curr = curr.next;
        }
        return false;
    }

    /**
     *
     * 使用快慢指针
     *
     *  快指针每次走2步，满指针每次走1步
     *
     *  (1) 当两者相差1步时，快指针和慢指针各走1步，两者相遇
     *  (2) 当两者相差2步，快慢指针各走一步，快慢指针相差1步，转化为(1) 的情况
     *  (3) 当两者相差N步时，快慢指针各走一步，两者之间的距离缩小为N-1，这样只要再同时向前走N-1步，两者即可相遇
     *
     *  首先快指针一定可以出现在慢指针之后， 只要出现在慢指针之后，就可以通过以上分析明确快慢指针一定可以相遇
     *
     * @param head
     * @return
     */
    public static boolean hasCycle1(ListNode head){
        if(head == null || head.next == null) return false;

        ListNode fast = head,slow = head;

        while(fast != null && slow != null){
            slow = slow.next;
            if(fast.next == null) return false;
            fast = fast.next.next;

            if(fast == slow) return  true;
        }

        return false;
    }

}
