package leetcode.oneMoreHundred;

import datastruct.ListNode;
import utils.LinkedListUtil;

import java.util.Arrays;

/**
 * 1669. 合并两个链表
 * 给你两个链表list1 和list2，它们包含的元素分别为n 个和m 个。
 *
 * 请你将list1中第a个节点到第b个节点删除，并将list2接在被删除节点的位置。
 *
 *
 * 322. 换零钱兑
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

        ListNode listNode2 = mergeInBetween(listNode, 0, 4, listNode1);
        LinkedListUtil.showLinkedList(listNode2);


        System.out.println("-------------换零钱兑--------------");
        int[] coins = {1,2,5};
        System.out.println(coinChange3(coins,11));
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
     * 322. 零钱兑换
     * 给定不同面额的硬币 coins 和一个总金额 amount。
     * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回-1。  2
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int ans = dfsChange1(coins, 0, 0, amount);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    /**
     * 直接搜索所有情况
     * @param coins
     * @param index
     * @param cnt
     * @param amtLeft
     * @return
     */
    private static int dfsChange1(int[]coins,int index ,int cnt, int amtLeft){
        if(index >= coins.length || amtLeft < 0){
            return Integer.MAX_VALUE;
        }
        if(amtLeft == 0){
            return cnt;
        }
        int curr = dfsChange1(coins, index, cnt+1, amtLeft - coins[index]);
        int next = dfsChange1(coins, index + 1, cnt, amtLeft);
        int res = Math.min(curr,next);
        return res;
    }


    /**
     * 动态规划解法
     *
     * @param coins
     * @param amount
     * @return
     */
    private static int[] memo;
    private static int coinChange2(int[] coins, int amount) {
        if(coins.length == 0) return -1;
        // memo[i] 代表金额为i 时可用硬币换取的最小硬币数
        memo = new int[amount];
        int res = dfsChange2(coins, amount);
        return res;
    }

    /**
     * 递归每层收集结果1, 以amount 作为变化量
     * @param coins
     * @param amount
     * @return
     */
    private static int dfsChange2(int[] coins,int amount){
        if(amount < 0)  return -1;
        if(amount == 0) return 0;
        if(memo[amount-1] != 0) return memo[amount-1];
        Integer min = Integer.MAX_VALUE;
        for(int i = 0 ; i < coins.length ; i++){
            int res = dfsChange2(coins, amount - coins[i]);
            if(res >= 0 && res < min){
                min = res + 1;
            }
        }
        memo[amount-1] = min == Integer.MAX_VALUE ? -1 : min;
        return memo[amount-1];
    }

    /**
     * 递归改动态规划
     *
     * dp[amt] = Math.min(dp[amt-coins[j]]) , j∈(0,length)
     * dp[i] = 0 i <= 0
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange3(int[] coins, int amount) {
        if(coins.length == 0) return -1;
        // memo[n] 代表凑成金额为 n 所需要的硬币数
        int[] memo = new int[amount+1];
        for(int i = 1 ; i <= amount ; i++){
            int min = Integer.MAX_VALUE;
            for(int j = 0; j < coins.length ; j++){
                if(i - coins[j] >= 0 && memo[i-coins[j]] < min){
                    min = memo[i-coins[j]] + 1;
                }
            }
            memo[i] = min ;
        }
        return memo[amount] == Integer.MAX_VALUE ? -1 : memo[amount];
    }
}
