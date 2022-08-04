package leetcode.byteDance;

import datastruct.ListNode;
import edu.princeton.cs.StdRandom;
import utils.ArrayUtil;
import utils.LinkedListUtil;

import java.util.Random;

/**
 * @className: Day05
 * @description:
 * @version: 1.0
 * @author: minsky
 * @date: 2022/7/31
 */
public class Day05 {

    public static void main(String[] args) {
        int[] arr = {1,2,3,3,4,5,5,6,7};
        Day05 day05 = new Day05();
//        System.out.println(day05.binSearchFirstEq(arr, 5));
//        System.out.println(day05.binSearchLastEq(arr, 5));
//        System.out.println(day05.binSearchFirstGe(arr, 5));
//        System.out.println(day05.binSearchLastLe(arr, 5));

        Random random = new Random();
        int errCount = 0;
        for(int i = 0; i < 10000; i++){
            int rand;
            while((rand = random.nextInt(50)) < 2 ) rand = random.nextInt(50);
            int[] ints = ArrayUtil.randValueArray(rand);
            StdRandom.shuffle(ints);
            day05.quickSort(ints);

            for(int k = 0; k < ints.length - 1; k++){
                if(ints[k] > ints[k+1]){
                    System.out.println("排序错误");
                    ArrayUtil.showArray(ints);
                    errCount++;
                    break;
                }
            }
        }
        System.out.println("总计出错次数: " + errCount);

//        int[] arr1 = {1};
//        int[] arr2 = {1,2};
//        int[] arr3 = {1,1};
//        int[] arr4 = {1,2,1};
//        int[] arr5 = {1,2,2,1};
//        int[] arr6 = {1,2,3,4,2,1};
//
//        System.out.println(day05.isPalindrome(LinkedListUtil.genLinkedListFromArray(arr1)));
//        System.out.println(day05.isPalindrome(LinkedListUtil.genLinkedListFromArray(arr2)));
//        System.out.println(day05.isPalindrome(LinkedListUtil.genLinkedListFromArray(arr3)));
//        System.out.println(day05.isPalindrome(LinkedListUtil.genLinkedListFromArray(arr4)));
//        System.out.println(day05.isPalindrome(LinkedListUtil.genLinkedListFromArray(arr5)));
//        System.out.println(day05.isPalindrome(LinkedListUtil.genLinkedListFromArray(arr6)));
    }

    /**
     * 二分查找变种问题:
     * (1)查找第一个值等于给定值的元素
     * (2)查找最后一个值等于给定值的元素
     * (3)查找第一个大于等于给定值的元素
     * (4)查找最后一个小于等于给定值的元素
     *
     * 返回匹配元素的下标，若未找到对应元素则返回 -1
     */
    public int binSearchFirstEq(int [] nums, int target){
        if(nums.length == 1) return nums[0] == target ? 0 : -1;

        int low = 0, high = nums.length - 1;
        // target 有可能就是在 low == high 位置
        while(low <= high){
            int mid = low + (high - low) / 2;
            if(target < nums[mid]){
                high = mid - 1;
            }else if(target > nums[mid]){
                low = mid + 1;
            }else{
                if(mid == 0 || nums[mid-1] != target) return mid;
                else high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个值等于给定值的元素
     */
    public int binSearchLastEq(int[] nums, int target){
        int len = nums.length - 1;
        int low = 0, high = nums.length - 1;
        // target 有可能就是在 low == high 位置
        while(low <= high){
            int mid = low + (high - low) / 2;
            if(target < nums[mid]){
                high = mid - 1;
            }else if(target > nums[mid]){
                low = mid + 1;
            }else{
                if(mid == len-1 || nums[mid+1] != target) return mid;
                else low = mid + 1;
            }
        }
        return -1;
    }

    // 查找第一个大于等于给定值的元素
    public int binSearchFirstGe(int[] nums, int target){
        int len = nums.length;
        int low = 0, high = nums.length - 1;

        while(low <= high){
            int mid = low + (high - low) / 2;
            if(target <= nums[mid]){
                if(mid == 0 || nums[mid - 1] < target)return mid;
                else high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return -1;
    }

    // 查找最后一个小于等于给定值的元素
    public int binSearchLastLe(int[] nums, int target){
        int len = nums.length;
        int low = 0, high = len - 1;
        while(low <= high){
            int mid = low + (high - low) / 2;
            if(target < nums[mid]){
                high = mid - 1;
            }else{
                if(mid == len - 1 || nums[mid + 1] > target) return mid;
                else low = mid + 1;
            }
        }
        return -1;
    }

    // 二分查找大于target 的第一个值元素
    public int binSearchFirstGt(int[]nums, int target){
        int len = nums.length;
        int low = 0, high = len - 1;
        while(low <= high){
            int mid = low + ((high - low) >> 1);
            if(target > nums[mid]){
                low = mid + 1;
            }else if(target < nums[mid]){
                high = mid - 1;
            }else{
                if(mid < len - 1 && nums[mid + 1] != target) return mid + 1;
                else low = mid + 1;
            }
        }
        return -1;
    }

    // 二分查找小于 target 的最后一个元素
    public int binSearchLastLt(int[]nums, int target){
        int len = nums.length;
        int low = 0, high = len - 1;

        while(low <= high){
            int mid = low + ((high - low) >> 1);
            if(target > nums[mid]){
                low = mid + 1;
            }else if(target < nums[mid]){
                high = mid - 1;
            }else{
                if(mid > 0 && nums[mid - 1] != target) return mid - 1;
                else high = mid - 1;
            }
        }
        return -1;
    }


    /**
     * 快速排序回顾梳理，明确疑问点
     *
     * 尤其需要注意partition方法:
     * partition方法我们希望达成一个这样的效果:
     * 随机选取出来的枢轴，在比较过程中所有小于枢轴的值放到左边，大于等于枢轴的值放在右边
     * 且需要满足排序的稳定性。
     *
     * (1) 枢轴的选取，若选择的是最左边元素，则判断是否需要交换到枢轴前面的条件需要是 nums[k] < pivot
     *     这样才能保证排序的稳定性
     * (2) 寻找枢轴位置时，最后确定的位置需要满足条件: 它是最后一个小于枢轴的元素
     * (3) partitin 方法需要能应付特殊情况
     *     ①: 一次交换都不需要进行的情况下,最后swap操作进行单个元素交换
     *
     *
     */
    public void quickSort(int[] nums){
        quickSort(nums, 0 , nums.length-1);
    }



    private void quickSort(int[] nums, int start, int end) {
        if(start >= end) return;
        int pivotIdx = partition(nums,start, end);
        quickSort(nums,start, pivotIdx - 1);
        quickSort(nums,pivotIdx + 1, end);
    }

    public int partition(int[]nums, int start, int end){
        int pivotId = start;
        int index = pivotId + 1;
        for(int k = index; k <= end; k++){
            if(nums[k] < nums[pivotId]){
                swap(nums, k, index);
                index++;
            }
        }
        swap(nums, pivotId, index - 1);
        return index - 1;
    }


    private void swap(int[]nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 判断链表是否是回文链表
     * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
     *
     * 输入：head = [1,2,2,1]
     * 输出：true
     *
     * 输入：head = [1,2]
     * 输出：false
     *
     * 提示：
     *
     * 链表中节点数目在范围[1, 105] 内
     * 0 <= Node.val <= 9
     */
    public boolean isPalindrome(ListNode head) {
        if(head.next == null) return true;
        ListNode slow = head, fast = head;
        ListNode pre = null , prePre;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            prePre = slow.next;
            slow.next = pre;
            pre = slow;
            slow = prePre;
        }
        if(fast != null && fast.next == null){
            slow = slow.next;
        }
        while(pre != null && slow != null){
            if(pre.val != slow.val) return false;
            pre = pre.next;
            slow = slow.next;
        }
        return true;
    }

}
