package leetCode;

import utils.ArrayUtil;

public class ArrayRotate {

    public static void main(String[]args){
        int[] test = new int[]{1,2,3,4,5,6,7};
        rotate2(test, 3);

        ArrayUtil.showArray(test);
    }

    /**
     * 方法一 ： 双重循环
     * @param nums
     * @param k
     */
    public static void rotate1(int[] nums, int k) {
        int len = nums.length ;
        k = k % len ;
        if(len < 2 || k == 0) return ;

        while(k > 0){

            int temp = nums[len - 1];
            for(int i = len - 1 ; i >0 ; i--){
                nums[i] = nums[i-1];
            }
            nums[0] = temp;
            k--;
        }
    }


    /**
     * 方法二 ： 翻转数组
     * 注意翻转时的一些特点:
     *      当完整的数组翻转成倒序的数组时，从倒序数组中间选择某个位置p分前后两端继续翻转时
     *      这时p的前后部分都是有序的，且数组正好以p为分界，相当于做了循环移位
     * @param nums
     * @param k
     */
    public static void rotate2(int[] nums, int k) {
        int len = nums.length;
        k = k % len;
        if(len < 2 || k == 0) return ;

        reverse(nums , 0 , len-1);
        reverse(nums,0,k-1);
        reverse(nums,k,len-1);
    }

    /**
     * 反转数组
     * @param nums
     * @param start
     * @param end
     */
    private static void reverse(int[] nums, int start, int end){
        int temp ;
        while(start < end){
            temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }
}
