package leetCode;

import edu.princeton.cs.Stack;
import utils.ListNode;

/**
 * @Class: GetIntersectionNode
 * @Description:
 *  《LeetCode 160. 相交链表》
 *
 *  编写一个程序，找到两个单链表相交的起始节点。
 *
 *  输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 *
 * @Author: Minsky
 * @Date: 2019/8/3 14:09
 * @Version: v1.0
 */
public class GetIntersectionNode {


    public static void main(String[]args){
        // java 中 null == null 是成立的
        System.out.println(null == null);
    }
    
    /**
     * 实现方式一，用两个栈分别保存两个list
     * 从栈中弹出的节点中第一个不相同的节点的前一个节点就是相交的起始点
     * @param headA
     * @param headB
     * @return 相交点，若没有相交点则返回 null
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Stack<ListNode> stackA = new Stack<>();
        Stack<ListNode> stackB = new Stack<>();

        ListNode curA = headA;
        ListNode curB = headB;
        while(curA != null){
            stackA.push(curA);
            curA = curA.next;
        }

        while(curB != null){
            stackB.push(curB);
            curB = curB.next;
        }

        ListNode preA = null;
        ListNode preB = null;
        while(stackA.size() > 0 && stackB.size() > 0){
            ListNode eleA = stackA.pop();
            ListNode eleB = stackB.pop();
            if(eleA.val != eleB.val){
                return preA == null ? eleA : preA;
            }else{
                preA = eleA;
                preB = eleB;
            }
        }

        return null;
    }


    public static ListNode getIntersectionNode1(ListNode headA, ListNode headB){
        /**
         定义两个指针, 第一轮让两个到达末尾的节点指向另一个链表的头部, 最后如果相遇则为交点(在第一轮移动中恰好抹除了长度差)
         两个指针等于移动了相同的距离, 有交点就返回, 无交点就是各走了两条指针的长度
         **/
        if(headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        // 在这里第一轮体现在pA和pB第一次到达尾部会移向另一链表的表头,
        // 而第二轮体现在如果pA或pB相交就返回交点, 不相交最后就是null==null
        while(pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}
