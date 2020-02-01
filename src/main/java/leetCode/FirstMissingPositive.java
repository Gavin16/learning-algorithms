package leetCode;

import java.util.Arrays;

/**
 * @Class: FirstMissingPositive
 * @Description:
 *
 *  《GeekTime -- practice day01》
 * 《41. 缺失的第一个正数》
 *  给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 *
 * 示例 1:
 *
 * 输入: [1,2,0]
 * 输出: 3
 * 示例 2:
 *
 * 输入: [3,4,-1,1]
 * 输出: 2
 * 示例 3:
 *
 * 输入: [7,8,9,11,12]
 * 输出: 1
 *
 *
 * @Author: Minsky
 * @Date: 2020/1/30 19:24
 * @Version: v1.0
 */
public class FirstMissingPositive {


    public static void main(String[] args) {
        int[] nums1 = {1,2,0};
        int[]nums2 = {3,4,-1,1};
        int[]nums3 = {7,8,9,11,12};
        int[]nums4 = {0,2,2,1,1};

//        System.out.println(firstMissingPositive(nums1));
//        System.out.println(firstMissingPositive(nums2));
//        System.out.println(firstMissingPositive(nums3));
        System.out.println(firstMissingPositive(nums4));
    }

    /**
     *  找到缺失的最小的正整数
     *
     *  解法（1） ：
     *  先对数组排序，排序之后从前到后遍历数组，找出数组中第一个大于0的元素的下标，然后按照以下方式找出 缺失的最小的正整数
     *  ① 比较 nums[i] 是否等于 i-j+1,若相等则比较下一个元素(i+1)
     *  ② 若不相等，则 i-j+1 即为缺失的最小正整数
     *  ③ 若数组遍历完后仍未找出该缺失的最小正整数，则 nums.length 即为所求
     *  ④ 同时还需要考虑可能出现的重复的元素
     *
     */
    public static int firstMissingPositive(int[] nums) {
        if(null == nums || nums.length == 0) return 1;

        Arrays.sort(nums);
        int len = nums.length;

        int st = len;
        for(int i = 0 ; i < len ; i++){
            if(nums[i] > 0){
                st = i;
                break;
            }
        }

        // 考虑可能存在的重复数值
        int pre = nums[st];
        int mt = 1;
        for(int j = st; j < len ; j++){
            if(j == st){
                if(nums[j] != mt){
                    return mt;
                }
                pre = nums[j];
                mt++;
            }else if(nums[j] == pre){
                continue;
            }else if(nums[j] != mt){
                return mt;
            }else{
                mt++;
                pre = nums[j];
            }
        }

        return nums[len-1] + 1;
    }
}
