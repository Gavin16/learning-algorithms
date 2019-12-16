package leetCode;

import utils.ArrayUtil;

import java.util.Arrays;

/**
 * @Class: MissingNumber
 * @Description:
 * @Author: Minsky
 * @Date: 2019/7/29 8:57
 * @Version: v1.0
 */
public class MissingNumber {

    public static void main(String[]args){
        int[] ints = ArrayUtil.randValueArray(9);
        ArrayUtil.showArray(ints);
        System.out.println(missingNumber1(ints));
    }

    /**
     * 通过求实际和 以及 期望和， 两个和相减来找出确实值
     * @param nums
     * @return
     */
    public static int missingNumber1(int[] nums) {
        // 求最大值
        int max = 0;
        for(int n : nums){
            if(n > max){
                max = n;
            }
        }

        // 求累积和
        int sum = 0 ;
        for(int n : nums){
            sum += n;
        }

        int expectedSum = max*(max+1)/2;
        int missingEle = expectedSum - sum ;
        if(missingEle > max || (nums.length == max+1)){
            return max+1;
        }
        return missingEle;
    }


    /**
     * 采用异或方式抵消重复值
     * @param nums
     * @return
     */
    public static int missingNumber2(int[] nums){
        // 这里重点是异或前初始值的选择
        // 取 missing = nums.length 时可以将有以下好处
        // 当nums缺中间或者第一个元素时，missing = nums.length 可以让nums中的最大的元素在异或中被抵消,从而可以通过与下标的异或来找出缺失
        // 当nums缺的是最后一个元素时, nums.length 实际就是缺失的元素,因此可以通过异或来让它保留，最后一会得到的结果就是缺失的元素

        int missing = nums.length;
        int len = nums.length;
        for(int i = 0 ; i < len ; i++){
            missing ^= nums[i];
            missing ^= i;
        }

        return missing;
    }
}
