package leetCode;

import utils.ArrayUtil;

/**
 * @Class: MaxSubArray
 * @Description:
 *
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * @Author: Minsky
 * @Date: 2019/7/29 8:11
 * @Version: v1.0
 */
public class MaxSubArray {

    public static void main(String[]args){

        int[] ints = ArrayUtil.randValueArray(1,10);
        ArrayUtil.showArray(ints);
        System.out.println(maxSubArray(ints));

    }

    /**
     * O(n) 的求法， 基于以下规律 ：
     *      若前面连续的累积和小于0，则累计到当前值时可以去掉前面的累积和从当前值开始可以获得更大的累积和
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {

        int sum = nums[0];
        int max =  sum;
        for(int i = 1 ; i < nums.length ; i++){
            if(sum < 0){
                sum = nums[i];
            }else{
                sum += nums[i];
            }

            if(sum > max){
                max = sum;
            }
        }

        return max;
    }
}
