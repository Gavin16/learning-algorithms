package leetCode;

/**
 * @Class: FindPeakElement
 * @Description:
 * 《LeetCode 162. 寻找峰值》
 *
 *  峰值元素是指其值大于左右相邻值的元素。
 *
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 *
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 *
 * 你可以假设 nums[-1] = nums[n] = -∞。
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,3,1]
 * 输出: 2
 * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
 *
 * 说明:
 *
 * 你的解法应该是 O(logN) 时间复杂度的。
 *
 *
 * @Author: Minsky
 * @Date: 2019/8/6 21:26
 * @Version: v1.0
 */
public class FindPeakElement {

    public int findPeakElement(int[] nums) {
        //使用二分查找
        int len = nums.length;
        int lo = 0 , hi = len - 1;

        while(lo < hi){
            int mid = lo + (hi - lo) / 2;
            if(nums[mid] < nums[mid+1]){
                lo = mid + 1;
            }else{
                hi = mid;
            }
        }
        return hi;
    }
}
