package leetcode.oneMoreHundred;

import datastruct.ListNode;
import utils.LinkedListUtil;

/**
 * 1669. 合并两个链表
 * 给你两个链表list1 和list2，它们包含的元素分别为n 个和m 个。
 *
 * 请你将list1中第a个节点到第b个节点删除，并将list2接在被删除节点的位置。
 *
 *
 * 322. 零钱兑换
 * 给定不同面额的硬币 coins 和一个总金额 amount。
 * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回-1。
 *
 * 你可以认为每种硬币的数量是无限的。
 * 示例1：
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 示例 2：
 *
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 示例 3：
 *
 * 输入：coins = [1], amount = 0
 * 输出：0
 */
public class Day035 {


    public static void main(String[] args) {
        ListNode listNode = LinkedListUtil.genSortedLinkedList(5);
        ListNode listNode1 = LinkedListUtil.genLinkedList(3);

        LinkedListUtil.showLinkedList(listNode);
        LinkedListUtil.showLinkedList(listNode1);

        ListNode listNode2 = mergeInBetween(listNode, 1, 5, listNode1);
        LinkedListUtil.showLinkedList(listNode2);
    }

    /**
     * 注意这里下标从 0 开始
     * @param list1
     * @param a
     * @param b
     * @param list2
     * @return
     */
    public static ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode sentinel = new ListNode(-1);
        sentinel.next = list1;
        ListNode preA = sentinel;
        // 找到 a 节点的前一个节点
        for(int i = 0 ; i < a ; i++){
            preA = preA.next;
        }
        // 找到 b 节点
        ListNode currB = list1;
        for(int k = 0 ; k < b ; k++){
            currB = currB.next;
        }
        ListNode afterB = currB.next;
        preA.next = list2;
        ListNode curr2 = list2;
        // list2 最后节点
        while(curr2.next != null){
            curr2 = curr2.next;
        }
        curr2.next = afterB;
        return sentinel.next;
    }


    /**
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        return -1;
    }

}
