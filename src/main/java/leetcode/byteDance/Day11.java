package leetcode.byteDance;

import datastruct.ListNode;
import utils.ArrayUtil;
import utils.LinkedListUtil;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @className: Day11
 * @description:
 * @version: 1.0
 * @author: minsky
 * @date: 2022/8/14
 */
public class Day11 {


    public static void main(String[] args) {
//        int[] arr1 = {1,2,3,4,5};
//        ListNode listNode1 = LinkedListUtil.genLinkedListFromArray(arr1);
//        int k1 = 2;
//        int[] arr2 = {1,2,3,4,5};
//        ListNode listNode2 = LinkedListUtil.genLinkedListFromArray(arr2);
//        int k2 = 1;
//        int[] arr3 = {1,2};
//        ListNode listNode3 = LinkedListUtil.genLinkedListFromArray(arr3);
//        int k3 = 2;
//        int[] arr4 = {1};
//        ListNode listNode4 = LinkedListUtil.genLinkedListFromArray(arr4);
//        int k4 = 1;

        Day11 day11 = new Day11();
//        LinkedListUtil.showLinkedList(day11.reverseKGroup(listNode1, k1));
//        LinkedListUtil.showLinkedList(day11.reverseKGroup(listNode2, k2));
//        LinkedListUtil.showLinkedList(day11.reverseKGroup(listNode3, k3));
//        LinkedListUtil.showLinkedList(day11.reverseKGroup(listNode4, k4));

//        int[] arr1 = {5,7,7,8,8,10};
//        ArrayUtil.showArray(day11.searchRange(arr1, 8));
//        ArrayUtil.showArray(day11.searchRange(arr1, 6));
//        int[] arr2 = {};
//        int[] arr3 = {5,7,8,9,10};
//        ArrayUtil.showArray(day11.searchRange(arr2, 1));
//        ArrayUtil.showArray(day11.searchRange(arr3, 8));


        int[] nums = {4,2,1,5,3};
        int[] nums1 = {4,2,1,6,-3};
        int[] nums2 = {3,4,-1,1};
        System.out.println(day11.firstMissingPositive(nums));
        System.out.println(day11.firstMissingPositive(nums1));
        System.out.println(day11.firstMissingPositive(nums2));
    }


    /**
     * 21. 合并两个有序链表
     *
     * 输入：l1 = [1,2,4], l2 = [1,3,4]
     * 输出：[1,1,2,3,4,4]
     * 示例 2：
     *
     * 输入：l1 = [], l2 = []
     * 输出：[]
     * 示例 3：
     *
     * 输入：l1 = [], l2 = [0]
     * 输出：[0]
     *  
     *
     * 提示：
     *
     * 两个链表的节点数目范围是 [0, 50]
     * -100 <= Node.val <= 100
     * l1 和 l2 均按 非递减顺序 排列
     *
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(-1), curr = head;
        while(list1 != null && list2 != null){
            if(list1.val <= list2.val){
                curr.next = list1;
                list1 = list1.next;
            }else{
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }
        curr.next = (list1 != null) ? list1 : list2;
        return head.next;
    }


    /**
     * 23. 合并K个升序链表
     *
     * 给你一个链表数组，每个链表都已经按升序排列。
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     *
     * 示例 1：
     *
     * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
     * 输出：[1,1,2,3,4,4,5,6]
     * 解释：链表数组如下：
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * 将它们合并到一个有序链表中得到。
     * 1->1->2->3->4->4->5->6
     * 示例 2：
     *
     * 输入：lists = []
     * 输出：[]
     * 示例 3：
     *
     * 输入：lists = [[]]
     * 输出：[]
     *  
     *
     * 提示：
     *
     * k == lists.length
     * 0 <= k <= 10^4
     * 0 <= lists[i].length <= 500
     * -10^4 <= lists[i][j] <= 10^4
     * lists[i] 按 升序 排列
     * lists[i].length 的总和不超过 10^4
     *
     */
    public ListNode mergeKLists(ListNode[] lists) {
        int size =  lists.length;
        if(size == 0) return null;
        PriorityQueue<ListNode> heap = new PriorityQueue<>(size,
                Comparator.comparingInt(o -> o.val));
        ListNode result = new ListNode(-1);
        ListNode curr = result;
        for(ListNode node : lists){
            if(null != node) heap.offer(node);
        }
        while(!heap.isEmpty()){
            ListNode poll = heap.poll();
            curr.next = poll;
            curr = curr.next;
            ListNode next = poll.next;
            if(next != null) heap.offer(next);
        }
        return result.next;
    }


    /**
     * 25. K 个一组翻转链表
     *
     * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
     * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
     *
     * 输入：head = [1,2,3,4,5], k = 2
     * 输出：[2,1,4,3,5]
     *
     * 输入：head = [1,2,3,4,5], k = 3
     * 输出：[3,2,1,4,5]
     *
     * 提示：
     * 链表中的节点数目为 n
     * 1 <= k <= n <= 5000
     * 0 <= Node.val <= 1000
     *
     * 进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？
     *
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head.next == null) return head;
        ListNode sentinel = new ListNode(-1);
        sentinel.next = head;
        ListNode curr = head;
        int len = 0;
        while(curr != null){curr = curr.next; len++;}
        int segCnt = len / k;
        // 逐段翻转链表
        ListNode pre = null, tail = head, start, prepre = pre;
        for(int i = 0 ; i < segCnt; i++){
            int cnt = 0;
            start = tail;
            while(tail != null && cnt < k){
                ListNode next = tail.next;
                tail.next = pre;
                pre = tail;
                tail = next;
                cnt++;
            }
            if(i == 0) sentinel.next = pre;
            else prepre.next = pre;
            start.next = tail;
            pre = start;
            prepre = pre;
        }
        return sentinel.next;
    }



    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     *
     * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
     *
     * 示例 1：
     *
     * 输入：nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     * 示例 2：
     *
     * 输入：nums = [5,7,7,8,8,10], target = 6
     * 输出：[-1,-1]
     * 示例 3：
     *
     * 输入：nums = [], target = 0
     * 输出：[-1,-1]
     *
     * 提示：
     *
     * 0 <= nums.length <= 105
     * -109 <= nums[i] <= 109
     * nums 是一个非递减数组
     * -109 <= target <= 109
     *
     * 两次二分查找
     * 先找出大于等于 target的第一个元素
     * 然后找出小于等于target的最后一个元素
     *
     */
    public int[] searchRange(int[] nums, int target) {
        int[] ans = new int[2];
        Arrays.fill(ans,-1);
        binSearchBoundary(nums,target,ans,Boolean.TRUE);
        binSearchBoundary(nums,target,ans,Boolean.FALSE);
        return ans;
    }

    private void binSearchBoundary(int[]nums, int target, int[] ans, boolean isLeft){
        for(int low = 0, high = nums.length - 1; low <= high ; ){
            int mid = low + ((high - low) >> 1);
            if(nums[mid] < target){
                low = mid + 1;
            }else if(nums[mid] > target){
                high = mid - 1;
            }else{
                // 左右边界在遇到等于target值时 下标朝相反方向搜索
                if(isLeft){
                    if(mid == 0 || nums[mid - 1] < nums[mid]){
                        ans[0] = mid;
                        break;
                    }else high = mid - 1;
                }else{
                    if(mid == nums.length - 1 || nums[mid] < nums[mid + 1]){
                        ans[1] = mid;
                        break;
                    }else low = mid + 1;
                }
            }
        }
    }


    /**
     * 41. 缺失的第一个正数
     *
     * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
     * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
     *
     * 示例 1：
     *
     * 输入：nums = [1,2,0]
     * 输出：3
     * 示例 2：
     *
     * 输入：nums = [3,4,-1,1]
     * 输出：2
     * 示例 3：
     *
     * 输入：nums = [7,8,9,11,12]
     * 输出：1
     *
     * 提示：
     *
     * 1 <= nums.length <= 5 * 105
     * -231 <= nums[i] <= 231 - 1
     *
     * 使用bitmap 实现 -- 超出内存限制
     *
     * 遍历 使下标为 i 值为 n 的元素, 判断 n 是否位于[1,nums.length] 范围内
     * 若在范围内, 则将它与  nums[n-1] 元素互换
     * 这样单次遍历下来, nums 中所有位置的元素只要在 [1,nums.length] 范围内 都可以在 n-1 的位置找到
     * 从i = 0 开始遍历nums, 只要发现 nums[i] != i+1, 那么 i+1 就是第一个未出现的最小正整数
     *
     */
    public int firstMissingPositive(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            while(nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]){
                // 交换下标为 i 和 下标为 nums[i] - 1 的元素
                int k = nums[i] - 1;
                int temp = nums[i];
                nums[i] = nums[k];
                nums[k] = temp;
            }
        }
        int t = 0;
        for(; t < nums.length; t++){
            if(nums[t] != t + 1) return t+1;
        }
        return t + 1;
    }

}
