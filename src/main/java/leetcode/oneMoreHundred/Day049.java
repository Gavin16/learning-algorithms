package leetcode.oneMoreHundred;

/**
 * @className: Day049
 * @description:
 *
 *
 *《581. 最短无序连续子数组》
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 * 示例 1：
 * 输入：nums = [2,6,4,8,10,9,15]
 * 输出：5
 * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 *
 * 示例 2：
 * 输入：nums = [1,5,4]
 * 输出：0
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：0
 *  
 *
 * 提示：
 * 1 <= nums.length <= 10^4
 * -10^5 <= nums[i] <= 10^5
 *
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2022/5/12
 */
public class Day049 {

    public static void main(String[] args) {


        Day049 day049 = new Day049();

        int[] nums1 = {1};
        int[] nums2 = {1,5,3,4};
        int[] nums3 = {1,2,3,4,5};
        int[] nums4 = {2,6,4,8,10,9,15};

        System.out.println(day049.findUnsortedSubarray(nums1));
        System.out.println(day049.findUnsortedSubarray(nums2));
        System.out.println(day049.findUnsortedSubarray(nums3));
        System.out.println(day049.findUnsortedSubarray(nums4));
    }


    /**
     * 找出连续最短子数组，使得对该子数组排序后, 整个数组都变得有序
     * 返回子数组的长度
     *
     * 单次循环遍历:
     * 从左向右找出最后小于最大值的下标, 记为 maxSR
     * 从右向左找出最后大于最小值的下标, 记为 minGL
     *
     * 子数组的长度就是 maxSR - minGL
     *
     *
     */
    public int findUnsortedSubarray(int[] nums) {
        int len = nums.length;
        int minGL = nums[len-1];
        int maxSR = nums[0];
        int end = -1, begin = 0;

        for(int i = 0, j = len-1 ; i < len && j >= 0; i++, j--){
            if( nums[i] < maxSR ){
                end = i;
            }else{
                maxSR = nums[i];
            }

            if(nums[j] > minGL){
                begin = j;
            }else{
                minGL = nums[j];
            }
        }

        return end - begin + 1;
    }
}
