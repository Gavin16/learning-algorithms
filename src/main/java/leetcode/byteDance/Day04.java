package leetcode.byteDance;

import datastruct.ListNode;
import edu.princeton.cs.StdRandom;
import utils.ArrayUtil;

import java.util.*;

/**
 * @className: Day04
 * @description:
 * @version: 1.0
 * @author: minsky
 * @date: 2022/7/30
 */
public class Day04 {


    public static void main(String[] args) {
        Day04 day04 = new Day04();
        int[] nums = {3,2,5,1,0,9,8,6};

//        day04.mergeSort(nums);
//        ArrayUtil.showArray(nums);

//        day04.fastSort(nums);
//        ArrayUtil.showArray(nums);

        int[] arr = {1,3,4,2,2};
        int[] arr1 = {1,2,3,1};
//        System.out.println(day04.findDuplicate(arr));
//        System.out.println(day04.findDuplicate(arr1));


        int[] nums1 = {3,2,1,5,6,4};
        int[] nums2 = {3,2,3,1,2,4,5,5,6};
        int[] nums3 = {3,2,3,1,2,4,5,5,6,7,7,8,2,3,1,1,1,10,11,5,6,2,4,7,8,5,6};
//        System.out.println(day04.findKthLargest1(nums1, 2));
//        System.out.println(day04.findKthLargest1(nums2, 4));
//        System.out.println(day04.findKthLargest1(nums3, 2));

        int[] input1 = {1,3,-1,-3,5,3,6,7};
        int[] input2 = {1};
//        int[] ints = day04.maxSlidingWindow(input1, 3);
//        int[] ints2 = day04.maxSlidingWindow(input2, 1);
//        ArrayUtil.showArray(ints);
//        ArrayUtil.showArray(ints2);

        int[] aa = {0,1,0,3,2,3};
        int[] aa1 = {10,9,2,5,3,7,101,18};
        int[] aa2 = {7,7,7,7,7,7,7};
        System.out.println(day04.lengthOfLIS(aa));
        System.out.println(day04.lengthOfLIS(aa1));
        System.out.println(day04.lengthOfLIS(aa2));

    }



    /**
     * 归并排序
     */
    public void mergeSort(int[] nums){
        int[] result = new int[nums.length];
        mergeSort(nums,result,0,nums.length-1);
    }

    private void mergeSort(int[] nums,int[] result, int start, int end){
        if(start >= end) return;
        int mid = start + (end - start)/2;
        int start1 = start, end1 = mid;
        int start2 = mid + 1, end2 = end;
        mergeSort(nums, result, start1, end1);
        mergeSort(nums, result, start2, end2);

        int k = start;
        while(start1 <= end1 && start2 <= end2){
            if(nums[start1] <= nums[start2]) result[k++] = nums[start1++];
            else result[k++] = nums[start2++];
        }
        while(start1 <= end1){
            result[k++] = nums[start1++];
        }
        while(start2 <= end2){
            result[k++] = nums[start2++];
        }
        for(int i = start; i <= end; i++){
            nums[i] = result[i];
        }
    }

    /**
     * 快速排序
     */
    public void fastSort(int[] nums){
        fastSortRecursive(nums,0,nums.length-1);
    }

    private void fastSortRecursive(int[] nums, int start, int end){
        if(start >= end) return;
        int p = partition(nums, start, end);
        fastSortRecursive(nums, start,p-1);
        fastSortRecursive(nums, p+1, end);
    }


    private int partition(int[] nums, int start, int end){
        int pivot = nums[start];
        int left = start, right = end;
        while(left < right){
            while(left < right && nums[left] <= pivot) left++;
            while(left < right  && nums[right] >= pivot) right--;
            if(left >= right) break;
            swap(nums, left, right);
        }
        if(nums[left] <= pivot){
            swap(nums, left, start);
        }
        return left;
    }

    private void swap(int[]nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }






    /**
     * 287. 寻找重复数
     * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
     * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
     * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
     *
     * 示例 1：
     *
     * 输入：nums = [1,3,4,2,2]
     * 输出：2
     * 示例 2：
     *
     * 输入：nums = [3,1,3,4,2]
     * 输出：3
     *  
     * 提示：
     *
     * 1 <= n <= 105
     * nums.length == n + 1
     * 1 <= nums[i] <= n
     * nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
     *  
     *
     * 进阶：
     *
     * 如何证明 nums 中至少存在一个重复的数字?
     * 你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？
     *
     * 由于数组元素处于[1,n] 范围内, 且数组长度为n+1
     * 使用找出链表中环的入口方式寻找重复元素
     *
     */
    public int findDuplicate(int[] nums) {
        int slow = nums[0], fast = nums[nums[0]];
        while(slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        // 此时slow 再走环外的m步就能到达环的入口
        int p = 0;
        while(p != slow){
            slow = nums[slow];
            p = nums[p];
        }
        return slow;
    }

    /**
     * 215. 数组中的第K个最大元素
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: [3,2,1,5,6,4], k = 2
     * 输出: 5
     * 示例 2:
     *
     * 输入: [3,2,3,1,2,4,5,5,6], k = 4
     * 输出: 4
     *  
     *
     * 提示：
     *
     * 1 <= k <= nums.length <= 105
     * -104 <= nums[i] <= 104
     *
     *
     * (1) 使用优先队列, 优先队列使用小顶堆
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for(int num : nums){
            queue.add(num);
            if(queue.size() == k + 1){
                queue.poll();
            }
        }
        return queue.peek();
    }

    /**
     * 使用快排partition 思想 + 优先队列
     * 当以快排思想对数组做减序排序,并返回枢轴大于k 时,选择将枢轴左边部分加入优先队列,并返回优先队列k个元素中最小的即为所求
     *
     */
    public int findKthLargest1(int[] nums, int k) {
        // 找出枢轴中
        int left = 0, right = nums.length - 1;
        int target = nums.length - k;
        while(true){
            int pivotIndex = findKthLargestPartition(nums,left,right);
            if(pivotIndex == target) return nums[target];
            else if(pivotIndex < target ){
                left = pivotIndex + 1;
            }else{
                right = pivotIndex - 1;
            }
        }
    }

    public int findKthLargestPartition(int[]nums, int left, int right){
        int randomIndex = left + new Random().nextInt(right - left + 1);
        swap(nums, left, randomIndex);

        int pivot = nums[left];
        int low = left + 1, high = right;
        while(true){
            // 降序排序这里优先将high部分进行遍历
            while(low <= high && nums[low] < pivot) low++;
            while(low <= high && nums[high] > pivot) high--;

            if(low >= high) break;
            exch(nums, low, high);
            low++;
            high--;
        }
        exch(nums,left,high);
        return high;
    }

    private void exch(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 206. 反转链表
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     *
     * 输入：head = [1,2,3,4,5]
     * 输出：[5,4,3,2,1]
     *
     * 提示：
     * 链表中节点的数目范围是 [0, 5000]
     * -5000 <= Node.val <= 5000
     */
    public ListNode reverseList(ListNode head) {
        if(null == head || null == head.next) return head;
        ListNode pre = null, post= head;
        while(post != null){
            ListNode tmp = post.next;
            post.next = pre;
            pre = post;
            post = tmp;
        }
        return pre;
    }

    /**
     * 239. 滑动窗口最大值
     * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     * 返回 滑动窗口中的最大值 。
     *
     * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
     * 输出：[3,3,5,5,6,7]
     *
     * 输入：nums = [1], k = 1
     * 输出：[1]
     *
     * 提示：
     *
     * 1 <= nums.length <= 105
     * -104 <= nums[i] <= 104
     * 1 <= k <= nums.length
     *
     * 使用优先队列实现,优先队列使用大顶堆
     *
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) ->
                o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1]);
        int[] ans = new int[len - k + 1];
        for(int m = 0; m < k; m++) queue.offer(new int[]{nums[m], m});
        ans[0] = queue.peek()[0];
        for(int i = k; i < len; i++){
            while(!queue.isEmpty() && queue.peek()[1] < i - k + 1){
                queue.poll();
            }
            queue.offer(new int[]{nums[i], i});
            ans[i - k + 1] = queue.peek()[0];
        }
        return ans;
    }

    private int findMax(int[] nums, int i, int j){
        int max = nums[i];
        for(int k = i+1; k <= j; k++){
            max = Math.max(max, nums[k]);
        }
        return max;
    }


    /**
     * 300. 最长递增子序列
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     *
     * 示例 1：
     *
     * 输入：nums = [10,9,2,5,3,7,101,18]
     * 输出：4
     * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
     * 示例 2：
     *
     * 输入：nums = [0,1,0,3,2,3]
     * 输出：4
     * 示例 3：
     *
     * 输入：nums = [7,7,7,7,7,7,7]
     * 输出：1
     *
     * 提示：
     * 1 <= nums.length <= 2500
     * -104 <= nums[i] <= 104
     *  
     * 进阶：
     * 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
     *
     *
     * 动态规划实现
     * dp[i] 表示以 i 结尾的最长严格递增子序列的长度
     * 那么 dp[i] = dp[j] + i 当 j < i 且 nums[i]>nums[j]
     *
     */
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int[]dp = new int[len];
        dp[0] = 1;
        int maxLen = 1;
        for(int i = 1; i < len; i++){
            dp[i] = 1;
            for(int k = 0; k < i; k++){
                if(nums[k] < nums[i]){
                    dp[i] = Math.max(dp[k] + 1, dp[i]);
                }
            }
            maxLen = Math.max(maxLen,dp[i]);
        }
        return maxLen;
    }


}
