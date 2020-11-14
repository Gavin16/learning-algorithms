package leetcode.oneMoreHundred;

import java.util.HashSet;
import java.util.Set;

/**
 * @Date: 2020年7月6日
 * ==============================================================================
 * 532. 数组中的K-diff数对
 * ==============================================================================
 * 给定一个整数数组和一个整数 k, 你需要在数组里找到不同的 k-diff 数对。
 * 这里将 k-diff 数对定义为一个整数对 (i, j), 其中 i 和 j 都是数组中的数字，且两数之差的绝对值是 k.
 *
 * 示例 1:
 *
 * 输入: [3, 1, 4, 1, 5], k = 2
 * 输出: 2
 * 解释: 数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
 * 尽管数组中有两个1，但我们只应返回不同的数对的数量。
 *
 */
public class Day005 {

    public static void main(String[] args) {
        int[] test = {1, 3, 1, 5, 4};
        System.out.println(findPairs(test,0));

        // 测试快排枢轴
        int[] arr = {7,3,6,4,2,5,8};
        System.out.println(testPartition(arr));
    }

    /**
     * @Title： 532. 数组中的K-diff数对
     * @Version: 版本1 使用一个Set 保存迭代输入数据，一个set保存数据对中较小的值
     * @param nums
     * @param k
     * @return
     */
    public static int findPairs(int[] nums, int k) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> pairs = new HashSet<>();
        for(int n : nums){
            // pair 不会将负数添加进来,因为visited 只会包含nums中的元素
            if(visited.contains(n-k)){
                pairs.add(n-k);
            }
            if(visited.contains(n+k)){
                pairs.add(n);
            }
            visited.add(n);
        }
        return pairs.size();
    }



    /**
     * @Title： 532. 数组中的K-diff数对
     * @Version: 版本2  TODO
     * @param nums
     * @param k
     * @return
     */
    public static int findPairs1(int[] nums, int k) {


        return -1;
    }


    /**
     * 快排 partition方法测试
     * 当 i != j 时，nums[i] > pivot ，这时遇到nums[j] < pivot 时 交换nums[i] 和 nums[j] 可以确保[0,i) 范围都是小于 pivot
     * 当 i == j 时, [0,i) 范围内所有元素都小于 pivot
     *
     * @param nums
     * @return
     */
    public static int testPartition(int[]nums){
        if(nums == null || nums.length < 1) return -1;
        int pivot = nums[nums.length-1] , i = 0;
        for(int j = i ; j < nums.length ; j++){
            if(nums[j] < pivot){
                swap(nums,j,i);
                i++;
            }
        }
        swap(nums,i,nums.length-1);
        return i;
    }


    private static void swap(int[] nums , int j , int k){
        int temp = nums[j];
        nums[j] = nums[k];
        nums[k] = temp;
    }
}
