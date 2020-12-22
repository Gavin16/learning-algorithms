package leetcode.oneMoreHundred;

import datastruct.ListNode;
import utils.ArrayUtil;
import utils.LinkedListUtil;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Class: Day023
 * @Description: 重拾算法练习
 * 以下5方面相关类型刷题找回感觉
 * 链表 -> 栈 -> 数组 -> 动态规划 -> 回溯算法
 *
 *
 * @Author: Minsky
 * @Date: 2020/12/20 9:42
 * @Version: v1.0
 */
public class Day023 {


    public static void main(String[] args) {
        ListNode listNode = LinkedListUtil.genSortedLinkedList(1);
        ListNode listNode1 = LinkedListUtil.genSortedLinkedList(5);
        LinkedListUtil.showLinkedList(listNode);
        LinkedListUtil.showLinkedList(listNode1);


        ListNode res = new Day023().mergeTwoLists(listNode, listNode1);
        LinkedListUtil.showLinkedList(res);
        
        System.out.println("------------linkedList + stack -------------");
        ListNode listNode2 = LinkedListUtil.genLinkedList(1);
        LinkedListUtil.showLinkedList(listNode2);
        int[] ints = new Day023().nextLargerNodes(listNode2);
        ArrayUtil.showArray(ints);

        System.out.println("---------------Array + Double Pointer-------------");
        int[] arr = {2,3,1,2,4,3};
        int s = 7;
        System.out.println(new Day023().minSubArrayLen(s, arr));

        System.out.println("---------------Simple DP ------------");
//        int[] nums = {7,1,5,3,6,4};
        int[] nums = {2,5};
        System.out.println(new Day023().maxProfit(nums));

        System.out.println("---------------- backtracking --------------");
        int[] nums1 = {2,3,5};
        List<List<Integer>> lists = new Day023().combinationSum(nums1, 8);
        for(List<Integer> list : lists){
            System.out.println(list);
        }


    }

    /**
     * (1)链表
     * 合并两个有序链表
     * 返回首节点较小的链表
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
        if(l1 != null && l2 != null){
            ListNode mergeTo = l1.val < l2.val ? l1 :l2;
            ListNode mergeFrom = l1.val < l2.val ? l2 : l1;
            ListNode currTo = mergeTo ,currFrom = mergeFrom;
            while(currTo != null && currFrom != null){
                if(null == currTo.next){
                    currTo.next = currFrom;
                    break;
                }else if(currFrom.val < currTo.next.val){
                    ListNode toNext = currTo.next, fromNext = currFrom.next;
                    currTo.next = currFrom;
                    currFrom.next = toNext;
                    currFrom = fromNext;
                }
                currTo = currTo.next;
            }

            return mergeTo;
        }
        return l1 == null ? l2 : l1;
    }

    /**
     * (2) 链表 + 栈
     * 1019. 链表中的下一个更大节点
     * 将链表转化为List 做处理
     * 遍历List 时每次将当前元素与栈顶元素对应节点值做比较
     *     若大于节点值则  可认为当前节点是栈顶元素的下一个更大值
     *     若小于等于节点值则  跳过处理
     * 遍历List的每个元素都存入栈中，这样可以不漏过任何元素
     *
     * 不排除出现栈中某个元素特别大，那样在遍历List时，该元素之后再也没有更大的元素出现，这样该元素对应的返回值就是0(数组默认值也是0)
     * ** 每次都对元素做和栈顶元素的比较 这一点很重要，也很巧妙***
     *
     *
     *
     */
    public int[] nextLargerNodes(ListNode head) {
        if(head == null) return new int[0];
        List<Integer> list = new ArrayList<>();
        while(head != null){
            list.add(head.val);
            head = head.next;
        }

        int[] res = new int[list.size()];

        Deque<Integer> stack = new LinkedList<>();
        for(int i = 0 ; i < list.size() ; i++){
            while(!stack.isEmpty() && list.get(stack.peek()) < list.get(i)){
                Integer pop = stack.pop();
                res[pop] = list.get(i);
            }

            stack.push(i);
        }
        return res;
    }


    /**
     * (3) 数组 + 双指针
     * 209. 长度最小的子数组
     * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。
     *
     * 双指针 start ,end 一开始都指向初始下标 0
     * end 开始往后加移， 每次移的时候将 nums[end] 加入 sum中 ，当发现sum >= s 时，不再移动end 而是改为对start 往后移
     * 在移动start 指针时记录 满足 sum >= s 时 end - start + 1的最小值
     *
     */
    public int minSubArrayLen(int s, int[] nums) {
        int sum = 0 , minLen = Integer.MAX_VALUE;
        for(int i = 0 , j = 0 ;;){
            if(sum >= s){
                if(i == nums.length) break;
                minLen = j - i < minLen ? j -i : minLen;
                sum -= nums[i];
                i++;
            }else{
                if(j == nums.length) break;
                sum += nums[j++];
            }
            if(minLen <= 1) return minLen;
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }


    /**
     *
     * (4) 动态规划
     * 剑指 Offer 63. 股票的最大利润
     * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
     *
     * DP状态定义
     * dp[i] 为前i天可以获得的最大收益
     *
     * dp[i] = max(dp[i-1], prices[i] - minPrice)
     *
     * dp[0] = 0, dp[1] = 0
     *
     */
    public int maxProfit(int[] prices) {
        if(null == prices || prices.length < 2) return 0;
        int[]dp = new int[prices.length+1];
        int minPrice = prices[0];

        for(int i = 1 ; i < prices.length ; i++){
            dp[i+1] = Math.max(dp[i],prices[i] - minPrice);
            minPrice = prices[i] < minPrice ? prices[i] : minPrice;
        }
        return dp[prices.length];
    }






    /**
     * 39. 组合总和
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     *
     * candidates 中的数字可以无限制重复被选取
     *
     * 说明：
     *
     * 所有数字（包括 target）都是正整数。
     * 解集不能包含重复的组合。
     *
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> comb = new ArrayList<>();

        dfs(result,comb,candidates,target,0);
        return result;
    }


    private void dfs(List<List<Integer>> result, List<Integer> comb, int[] candidates, int target, int id) {
        if(id >= candidates.length) return;
        if(target == 0){
            result.add(new ArrayList<>(comb));
            return;
        }
        // 选择当前元素和跳过当前元素是否可以调换顺序
        if(target - candidates[id] >= 0){

            comb.add(candidates[id]);
            dfs(result, comb, candidates, target-candidates[id], id);
            comb.remove(comb.size()-1);
        }
        dfs(result,comb,candidates,target,id+1);
    }

}
