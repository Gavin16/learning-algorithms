package leetCode.oneMoreHundred;

import dataStruct.ListNode;
import utils.LinkedListUtil;

/**
 *  @Date: 2020年8月8日
 *  ==============================================================================
 *  面试题 02.07. 链表相交
 *  ==============================================================================
 *  给定两个（单向）链表，判定它们是否相交并返回交点。请注意相交的定义基于节点的引用，而不是基于节点的值。
 *  换句话说，如果一个链表的第k个节点与另一个链表的第j个节点是同一节点（引用完全相同），则这两个链表相交。
 *
 *
 * 示例 1：
 *
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，
 * 链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 *
 *  ==============================================================================
 *  面试题 02.06. 回文链表
 *  ==============================================================================
 *  编写一个函数，检查输入的链表是否是回文的。
 *  示例 1：
 *
 *  输入： 1->2
 *  输出： false
 *  示例 2：
 *
 *  输入： 1->2->2->1
 *  输出： true
 *  ==============================================================================
 *  148. 排序链表
 *  ==============================================================================
 *  在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 *
 *  示例 1:
 *  输入: 4->2->1->3
 *  输出: 1->2->3->4
 *  示例 2:
 *
 *  输入: -1->5->3->4->0
 *  输出: -1->0->3->4->5
 *
 *  ==============================================================================
 *
 */
public class Day013 {


    public static void main(String[] args) {
        
        System.out.println(null == null);

        ListNode listNode = LinkedListUtil.genLinkedList(3);
        LinkedListUtil.showLinkedList(listNode);
        System.out.println(isPalindrome(listNode));

        ListNode listNode1 = LinkedListUtil.genLinkedList(2);

//        System.out.println(isPalindrome2(listNode));
        System.out.println(isPalindrome2(listNode1));

        ListNode listNode2 = LinkedListUtil.genLinkedList(4);
        LinkedListUtil.showLinkedList(listNode2);
        ListNode sortRes = sortList(listNode2);
        LinkedListUtil.showLinkedList(sortRes);
    }

    /**
     * @Title: 面试题 02.07. 链表相交
     * @version: 版本1 多次循环遍历法 O(N)时间复杂度
     * 将两个输入链表的长度分别即为 lenA 和 lenB
     * 若 lenA > lenB 且记lenA - lenB = k;
     * 则通过让链表A 从第k个位置,链表B从表头开始逐一遍历比较即可找出两个链表相交的位置
     *
     * 进一步,链表A中第k个位置可以通过先找出倒数第k个位置，然后使用双指针遍历，后面指针所指即为第k个位置
     *
     * @param headA
     * @param headB
     * @return 若两个链表相交,则返回相交节点,若不相交则返回null
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        ListNode currA = headA, currB = headB;
        while(currA != null && currB  != null){
            currA = currA.next;
            currB = currB.next;
        }
        ListNode left = currA != null ? currA : currB;
        ListNode longer = currA != null ? headA : headB;
        ListNode shorter = currA != null ? headB : headA;
        while(left != null){
            left = left.next;
            longer = longer.next;
        }
        while(shorter != null){
            if(shorter == longer) return longer;
            else{
                shorter = shorter.next;
                longer = longer.next;
            }
        }
        return null;
    }


    /**
     * @Title: 面试题 02.07. 链表相交
     * @Version: 版本2 单次遍历实现
     *
     * 假设A，B两个链表相交于节点C,他们公共的末尾节点为D
     * 则A节点出发走到D之后再继续从B节点出发直到再次走到D 和 B节点出发走到节点D然后再次从A节点出发再次走到D
     * 两种方式遍历的节点数是一样的.
     *
     * 对于上述的走法，若存在相交节点C，则他们一定可以在C节点相遇:
     * 因为AC+CD+BC = BC+CD+AC ,只是走的顺序不同一个先走的AC另一个先走的BC
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        ListNode currA = headA,currB = headB;
        // java 中满足 null == null ,故而可以简化写法;循环最多执行两遍
        while(currA != currB){
            currA = (currA == null) ? headB : currA.next;
            currB = (currB == null) ? headA : currB.next;
        }
        return currA;
    }


    /**
     * @Title: 02.06. 回文链表
     * @Version: 版本1 反转并保存链表,比较反转前后的链表是否相同
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        if(head.next == null) return true;
        ListNode reverse = null, newNode,curr = head;
        while(curr != null){
            newNode = new ListNode(curr.val);
            newNode.next = reverse;
            reverse = newNode;
            curr = curr.next;
        }
        while(reverse != null){
            if(head.val != reverse.val) return false;
            else{
                head = head.next;
                reverse = reverse.next;
            }
        }
        return true;
    }


    /**
     * @Title:  02.06. 回文链表
     * @Version: 版本2  O(n) 时间复杂度和 O(1) 空间复杂度
     *
     * 先找到链表的中间节点,然后反转链表的后半部分,最后从链表的两端向链表中间遍历判断链表是否回文
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome2(ListNode head) {
        if(head == null || head.next == null) return true;
        // 快慢指针找出链表中间位置
        ListNode fast = head, slow = head;
        ListNode reverseHead = null,temp;
        while(fast.next != null){
            slow = slow.next;
            fast = fast.next;
            if(fast.next != null){
                fast = fast.next;
            }
        }
        // 反转链表
        while(slow != null){
            temp = slow.next;
            slow.next = reverseHead;
            reverseHead = slow;
            slow = temp;
        }
        // 首尾向中间遍历
        while(reverseHead != null){
            if(reverseHead.val != head.val)return false;
            else{
                reverseHead = reverseHead.next;
                head = head.next;
            }
        }
        return true;
    }


    /**
     * @Title: 148. 排序链表
     * @Version: 版本1 归并排序实现
     *
     * 递归从中间位置将链表拆分为断开的左右两部分,同时将左边部分的next指针指向null
     *
     * 拆分到不能再拆分时(即左右元素个数为不超过1时) 将所有所有部分按顺序合并
     *
     * @param head
     * @return
     */
    public static ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode middleNode = findMiddleNode(head);
        ListNode right = middleNode.next;
        middleNode.next = null;

        ListNode rightPart = sortList(right);
        ListNode leftPart = sortList(head);
        ListNode result = merge(leftPart,rightPart);
        return result;
    }


    /**
     * 合并左右部分
     * @param leftPart
     * @param rightPart
     * @return ListNode
     */
    private static ListNode merge(ListNode leftPart, ListNode rightPart) {
        ListNode res = new ListNode(-1),curr = res;
        while(leftPart != null && rightPart != null){
            if(leftPart.val < rightPart.val){
                curr.next = leftPart;
                leftPart = leftPart.next;
            }else{
                curr.next = rightPart;
                rightPart = rightPart.next;
            }
            curr = curr.next;
        }
        ListNode remain = leftPart != null ? leftPart : rightPart;
        curr.next = remain;
        return res.next;
    }

    /**
     * 找出链表的中间节点(若节点个数为偶数个,取前面节点)
     * @param head
     * @return
     */
    private static ListNode findMiddleNode(ListNode head) {
        ListNode slow = head, fast = head.next;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
