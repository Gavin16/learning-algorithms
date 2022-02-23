package leetcode;

import utils.ArrayUtil;

/**
 * 《75. 颜色分类》
 * 给定一个包含红色、白色和蓝色、共n 个元素的数组nums，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 我们使用整数 0、1 和 2 分别表示红色、白色和蓝色。
 * 必须在不使用库的sort函数的情况下解决这个问题。
 *
 * 示例 1：
 *
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * 示例 2：
 *
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] 为 0、1 或 2
 *
 */
public class SortColors {


    public static void main(String[] args) {

        SortColors instance = new SortColors();
        int[] nums = {2,2,1};
        instance.sortColors(nums);
        ArrayUtil.showArray(nums);

        int[] nums1 = {2,0,2,1,1,0};
        instance.sortColors1(nums1);
        ArrayUtil.showArray(nums1);

        int[] nums2 = {2,0,2,1,1,0};
        instance.sortColors2(nums2);
        ArrayUtil.showArray(nums2);
    }

    public void sortColors(int[] nums) {
        if(null == nums || nums.length < 1){ return; }
        int oneCnt = 0, sum = 0, len = nums.length;

        for(int i = 0 ; i < len; i++){
            oneCnt += (nums[i] == 1 ? 1 : 0);
            sum += nums[i];
        }
        int twoCnt = (sum - oneCnt)/2;
        int zeroCnt = len - oneCnt - twoCnt;
        for(int j = 0 ; j < len ; j++){
            if(j < zeroCnt){
                nums[j] = 0;
            }else if(j >= zeroCnt && j < (len - twoCnt)){
                nums[j] = 1;
            }else{
                nums[j] = 2;
            }
        }
    }

    /**
     * 单指针法
     * 循环两遍
     */
    public void sortColors1(int[] nums) {
        int head = 0;
        // 交换 0
        for(int i = 0; i < nums.length ; i++){
            if(nums[i] == 0){
                exchange(nums,head,i);
                head++;
            }
        }
        // 交换 1
        for(int j = head; j < nums.length; j++){
            if(nums[j] == 1){
                exchange(nums,head,j);
                head++;
            }
        }
    }

    private void exchange(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }



    /**
     * 双指针
     * 指针 p0,p1 分别指向最后0和1出现的位置
     * 循环遍历nums 时, 当出现0则与p0位置交换，出现1则与p1位置互换
     * 由于 1 需要连续的排在 0 之后
     *
     * 因此nums[i] 与1互换时，可以直接互换；而与0互换时需要判断后面是否有 1 ，
     * 若p0指向的位置已经是连续1所在区域，p0此时的置换会将1置换到nums[i]
     * 也就是说: 当 p0<p1 时，需要再将p0换出去的再换回到1的末尾(p1位置)
     *
     */
    public void sortColors2(int[] nums) {
        int p0 = 0 , p1 = 0;
        int len = nums.length;
        for(int i = 0 ; i < len; i++){
            if(nums[i] == 1){
                exchange(nums, p1, i);
                p1++;
            }else if(nums[i] == 0){
                exchange(nums, p0, i);
                if(p0 < p1){
                    exchange(nums, p1, i);
                }
                p0++;
                p1++;
            }
        }
    }

}
