package leetcode;

import datastruct.ListNode;
import datastruct.TreeNode;

/**
 * @ClassName: SortedListToBST
 * @Description: 有序链表转二叉搜索树
 *  《109. 有序链表转换二叉搜索树》
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 *
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 * @Author: wufangmin
 * @Date: 2019/12/19 15:51
 * @Version:
 */
public class SortedListToBST {

    public static void main(String[] args) {

    }

    /**
     * 有序链表转二叉搜索树
     *
     * 要求二叉树是平衡二叉树
     *
     * 找出中间节点，然后以中间节点做为根节点，递归的执行中间节点两端的链表
     */
    static TreeNode sortedListToBST(ListNode head) {

        ListNode mid = findMiddleNode(head);

        TreeNode root = null;
        if(mid != null){
            root = new TreeNode(mid.val);
            root.left = sortedListToBST(head == mid ? null : head);
            root.right = sortedListToBST(mid.next);
        }

        return root;
    }

    static ListNode findMiddleNode(ListNode head){
        ListNode slow = head, fast = slow, slowPrev = null;

        while(fast!= null && fast.next != null){
            slowPrev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if(slowPrev != null){
            // 断开中间节点前面对后面的连接
            slowPrev.next = null;
        }else{
            // 首节点返回 null
            return null;
        }
        return slow;
    }
}
