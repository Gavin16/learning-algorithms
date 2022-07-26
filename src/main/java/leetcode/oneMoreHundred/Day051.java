package leetcode.oneMoreHundred;

/**
 * @className: Day051
 * @description:
 * 《80. 删除有序数组中的重复项 II》
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * 说明：
 * 为什么返回数值是整数，但输出的答案是数组呢？
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * 你可以想象内部操作如下:
 *
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 *
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1,2,2,3]
 * 输出：5, nums = [1,1,2,2,3]
 * 解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。 不需要考虑数组中超出新长度后面的元素。
 * 示例 2：
 *
 * 输入：nums = [0,0,1,1,1,1,2,3,3]
 * 输出：7, nums = [0,0,1,1,2,3,3]
 * 解释：函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。 不需要考虑数组中超出新长度后面的元素。
 *
 * 提示：
 * 1 <= nums.length <= 3 * 104
 * -104 <= nums[i] <= 104
 * nums 已按升序排列
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2022/7/26
 */
public class Day051 {

    public static void main(String[] args) {
        int[] arr = {0,0,1,1,1,2,2,3,3,4};

        int[] arr2 = {0,0,1,1,1,1,2,3,3};
        int[] arr3 = {1,1,1,2,2,3};
        int[] arr4 = {1};
        Day051 day051 = new Day051();

        System.out.println(day051.removeDuplicates2(arr2));
        System.out.println(day051.removeDuplicates2(arr3));
        System.out.println(day051.removeDuplicates2(arr4));
    }


    /**
     * 26. 删除有序数组中的重复项
     */
    public int removeDuplicates(int[] nums) {
        int idx = 0, len = nums.length;
        int start = 0, end = start + 1;
        while(end < len){
            if(nums[start] != nums[end]){
                nums[idx++] = nums[start];
                start = end;
            }
            end++;
        }
        nums[idx] = nums[end-1];
        return idx + 1;
    }

    /**
     * 80. 删除有序数组中的重复项 II
     * 输入：nums = [0,0,1,1,1,1,2,3,3]
     * 输出：7, nums = [0,0,1,1,2,3,3]
     *
     */
    public int removeDuplicates2(int[] nums) {
        int idx = 0, len = nums.length;
        int start = 0, end = start + 1;
        while(end < len){
            if(nums[start] != nums[end]){
                // 没有出现重复超过2个的元素,直接赋值
                int repNum = Math.min(end - start,2);
                for(int i = 0 ; i < repNum ; i++){
                    nums[idx++] = nums[start];
                    start += 1;
                }
                start = end;
            }
            end++;
        }
        // 判断最后遍历完之后 end 与 start 相差多少
        int repNum = Math.min(end - start,2);
        for(int i = 0 ; i < repNum ; i++){
            nums[idx++] = nums[start];
            start += 1;
        }
        return idx;
    }

    /**
     * *** 官方题解做法 ****
     * 快慢指针，双指针解法
     * slow 记录需要保留的元素下标
     * fast 用于寻找出现变化的元素
     *
     * 输入：nums = [0,0,1,1,1,1,2,3,3]
     * 输出：7, nums = [0,0,1,1,2,3,3]
     *
     * slow 总是指向出现一次或者出现两次元素的下一个
     * 那么当 nums[fast] != nums[slow - 2] 时意味着fast此时遇到了新的值
     * 这时可以将fast指向的值赋值给到 slow; fast 与  slow - 2 下标比较
     * 可以再fast 重复次数超过 2 次时跳过赋值操作
     *
     */
    public int RemoveDuplicates3(int[] nums){
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        int slow = 2, fast = 2;
        while (fast < n) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }



}
