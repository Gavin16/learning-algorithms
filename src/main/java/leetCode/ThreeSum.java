package leetCode;

import utils.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName: ThreeSum
 * @CopyRight: wufangmin
 * @Description:
 *
 * 《15. 三数之和》标签：数组，双指针
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
 * 使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * @Author: wufangmin
 * @Date: 2019/12/27 15:05
 * @Version:
 */
public class ThreeSum {



    /**
     * 双指针的解法
     *
     * O(n^2) 时间复杂度, O(1) 的空间复杂度的解法：
     * (1) 首先对数组升序排序
     * (2) 从左至右，逐个固定数组中的元素k,并使用双指针i,j 分别指向k右区域【k+1,n】两端
     * (3) 判断方式如下:
     *      若 nums[k] >0 ,结束循环并返回结果
     *      若 nums[k] = nums[k-1] , 则跳过k,取k的下一个值直到nums[k] != nums[k-1]
     *      若 nums[i] + nums[j] + nums[k] = 0 ,则将i,j,k添加到结果集中，
     *          否则若 三数和 < 0 , i++
     *          若    三数和 > 0,  j--
     *      若 i >= j 时固定下一个元素(k=k+1)
     *
     * (4) 若k移动到倒数第二的位置使得 一开始 i就等于j , 则退出循环
     */
    static List<List<Integer>> threeSum(int[] nums) {
        if(null == nums || nums.length < 3) return new ArrayList<>();
        List<List<Integer>> resList = new ArrayList<>();
        // 升序排序数组
        Arrays.sort(nums);

        for(int k=0 ; k < nums.length ; k++){
            // 跳过重复的值
            if(k > 0 && nums[k] == nums[k-1]) continue;

            // 升序排序,从较小值开始固定搜索,当nums[k] >0 时代表不会再出现 后面两数与nums[k] 加起来等于0的值了
            if(nums[k] > 0) break;

            // 首尾双指针搜索值 与nums[k]加起来等于 0 i和j
            int i = k + 1 , j = nums.length - 1;
            boolean firstMatchForK = true;
            while(i < j){
                if(nums[k] + nums[i] + nums[j] < 0){
                    i++;
                }else if(nums[k] + nums[i] + nums[j] > 0){
                    j--;
                }else{
                    // 需要避免i++,j-- 之后出现重复值的情况
                    // 进入else分之代表 nums[i] + nums[j] + nums[k] = 0,由于 nums[k]不变
                    // 因此当nums[i] != nums[i-1] 时,nums[j] 必然不等于 nums[j+1]; 但是此只限当前K非首次匹配
                    if(firstMatchForK || nums[i] != nums[i-1]){
                        List<Integer> res = Arrays.asList(nums[k],nums[i],nums[j]);
                        resList.add(res);
                    }
                    firstMatchForK = false;
                    i++;
                    j--;
                }
            }
        }
        return resList;
    }


    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = threeSum(nums);
        for(List<Integer> list : lists){
            System.out.println(list);
        }
    }
}
