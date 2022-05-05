package leetcode;

import utils.LinkedListUtil;
import datastruct.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 《LeetCode 86. 分隔链表》
 *
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 *
 * 你应当保留两个分区中每个节点的初始相对位置。
 *
 * 示例:
 *
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 *
 *
 * 《131. 分割回文串》
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * 回文串 是正着读和反着读都一样的字符串。
 *
 * 示例 1：
 *
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * 示例 2：
 *
 * 输入：s = "a"
 * 输出：[["a"]]
 * 
 *
 * 提示：
 * 1 <= s.length <= 16
 * s 仅由小写英文字母组成
 *
 */
public class Partition {

    public static void main(String[]args){
//        ListNode list = LinkedListUtil.genLinkedList(7);
//        LinkedListUtil.showLinkedList(list);
//        ListNode partition = partition(list, 3);
//        LinkedListUtil.showLinkedList(partition);

        // 回文字符串拆分
        String str = "aaba";
        Partition instance = new Partition();
        List<List<String>> partition = instance.partition(str);
        System.out.println(partition);
    }


    /**
     * 方法一： 找出删除拼接法实现
     * @param head
     * @param x
     * @return
     */
    public static ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null) return head;
        // 移除到链表中的元素到 List 中
        ListNode curr = head , pre = head, first = pre;
        List<ListNode> list = new ArrayList<>();
        while(curr != null){
            ListNode next = curr.next;
            if(curr.val < x){
                list.add(curr);
                if(pre != null) pre.next = next;

                if(curr == first) first = next;
            }else{
                pre = curr;
            }
            curr = next;
        }

        // 取出栈中的元素 首位链接
        ListNode l1 = null, l2 = null, h1 = null;
        for(int i = 0 ; i < list.size(); i++){
            if(l1 == null){
                l1 = list.get(i);
                l2 = list.get(i);
                h1 = l1;
            }else if(l1 == l2){
                l2 = list.get(i);
                l1.next = l2;
            }else{
                l1 = l2;
                l2 = list.get(i);
                l1.next = l2;
            }
        }

        // 找到移除后原链表中第一个大于x的节点，将小于x的链表插入在该节点前
        if(h1 != null)
            l2.next = first != null ? first : null;
        else
            h1 = first;
        return h1;
    }

    /**
     *  借助虚拟头结点实现
     */
    public static ListNode partition1(ListNode head, int x) {
        ListNode dummyHead1 = new ListNode(0);
        ListNode dummyHead2 = new ListNode(0);
        ListNode node1 = dummyHead1;
        ListNode node2 = dummyHead2;
        while (head != null) {
            if (head.val < x) {
                node1.next = head;
                node1 = node1.next;
                node1.next = null;
            } else {
                node2.next = head;
                node2 = node2.next;
                node2.next = null;
            }
            head = head.next;
        }
        node1.next = dummyHead2.next;
        return dummyHead1.next;
    }


    /**
     * 分隔回文串
     * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
     * 由于需要找出所有拆分得到的回文子串，因此需要使用回溯算法进行搜索
     *
     * 回溯判断回文串过程中可能出现重复判断的情况(?) 因此先用动态规划方法将字符串s[i...j] 是否是回文串
     * 事先判断出来，然后在做回溯搜索来找出所有拆分得到的回文串列表
     *
     * 动态规划找出所有s[i..j] 是否为回文字符串步骤
     * 定义 f(i,j) 代表字符串 s[i..j] 部分是否为回文字符串
     *
     * f(i,j) = f(i+1, j-1) & s[i] == s[j]   j >= i
     *        = true                         j < i
     *
     */
    public List<List<String>> partition(String s) {
        int len = s.length();
        dp = new boolean[len][len];
        // 初始化 i <= j 时 dp[i][j] 为true
        for(int i = 0 ; i < len ; i++){
            for(int j = 0; j <= i ; j++){
                dp[i][j] = true;
            }
        }
        // 判断所有s[i..j] 是否为回文字符串
        // 由于dp[i][j] 依赖左下方位置,因此采用从下至上，从左至右的方式填充
        for(int i = len -1 ; i >= 0 ; i--){
            for(int j = i+1; j < len ; j++){
                dp[i][j] = dp[i+1][j-1] && (s.charAt(i) == s.charAt(j));
            }
        }
        // 深度优先搜索
        // 若s[i..j] 是回文串则加入结果列表
        // 若 i == j 则递归到了最底部
        dfs(s, 0 ,len);

        return list;
    }

    private boolean[][] dp;
    private List<List<String>> list = new ArrayList<>();
    private List<String> ans = new ArrayList<>();

    private void dfs(String s, int i, int len) {
        if(i == len){
            list.add(new ArrayList<>(ans));
            return;
        }else{
            for(int j = i; j < len ; j++){
                if(dp[i][j]){
                    ans.add(s.substring(i,j+1));
                    dfs(s,j+1, len);
                    ans.remove(ans.size()-1);
                }
            }
        }
    }


}
