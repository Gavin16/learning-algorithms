package leetCode;

import utils.ArrayUtil;

/**
 * @Class: RemoveElement
 * @Description:
 *
 *  LeetCode 27
 *
 *  给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
 *  不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *  元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 *  示例 1:
 *  给定 nums = [3,2,2,3], val = 3,
 *  函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 *  你不需要考虑数组中超出新长度后面的元素。
 *
 * @Author: Minsky
 * @Date: 2019/7/28 15:52
 * @Version: v1.0
 */
public class RemoveElement {


    public static void main(String[]args){
        int[] arr = ArrayUtil.randValueArray(15);
        ArrayUtil.showArray(arr);
        System.out.println(removeElement(arr, 8));
        ArrayUtil.showArray(arr);
    }


    /**
     * 从前到后的双指针实现
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement(int[] nums, int val) {
        int k = 0;
        for(int i =0; i < nums.length ; i++){
            if(nums[i] != val){
                nums[k++] = nums[i];
            }
        }
        return k;
    }


}
