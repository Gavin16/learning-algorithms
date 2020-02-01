package leetCode;

import utils.ArrayUtil;

import java.util.*;

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
        int[] nums = {-1, 0, 1, 2, -1, -4,-1};
        int[] nums1 = {0,0,0,0};
        int[] nums2 = {-2,0,0,2,2};
        int[] nums3 = {-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0};
        Arrays.sort(nums3);
        ArrayUtil.showArray(nums3);
//        List<List<Integer>> lists = threeSum(nums);
//        for(List<Integer> list : lists){
//            System.out.println(list);
//        }

        threeSum2(nums3);
        System.out.println(result);
        System.out.println(threeSum3(nums3));
    }



    /**
     *  解题思路
     *
     *  考虑是否可以使用回溯算法？   ---  可以回溯解决, 怎么去重？？ 参考《40. 组合总和 II》
     *
     */
    public static List<List<Integer>> threeSum2(int[] nums) {
        if(null == nums || nums.length == 0) return new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums,nums.length,0);
        return result;
    }
    private static List<List<Integer>> result = new ArrayList<>();
    private static List<Integer> combin = new ArrayList<>();
    private static Set<Integer> set = new HashSet<>();

    private static void dfs(int[]nums ,int len, int i){
        if(i == len || combin.size() > 2) return;
        // 当前是第三个数，且满足 a+b+c = 0;
        if(combin.size() == 2 && combin.get(0) + combin.get(1) + nums[i] == 0 ){
            combin.add(nums[i]);
            result.add(new ArrayList<>(combin));
            return;
        }

        if( i < len && len - i + combin.size()  >= 2){

            // 跳过当前元素，跳过规则:
            // 若下一个元素与当前元素相同，则找出i之后第一个不同的元素
            // 若下一元素与当前元素不同，则取 i+1 作为下一个元素
            int nextId = getNextId(nums,i);
            dfs(nums,len,nextId);

            combin.add(nums[i]);
            dfs(nums,len,i+1);
            combin.remove(combin.size()-1);
        }
    }

    private static int getNextId(int[] nums, int i) {
        int j = i+1;
        for(; j < nums.length ; j++){
            if(nums[j] == nums[i]) continue;
            else break;
        }
        return j;
    }


    /**
     * 三数之和循环解法：
     * 《GeekTime -- practice day01》
     * (1) 先对数组排序
     * (2) 按顺序从左至右固定一个元素，然后对这之后的元素求两数之和
     * (3) 如果固定的元素自身大于 0, 则介绍循环(因为最小的数大于0，三数之和不可能为0)
     * (4) 若固定的元素的当前元素和前一个元素相同则跳过当前元素
     */
    public static List<List<Integer>> threeSum3(int[]nums){
        if(null == nums || nums.length < 3) return new ArrayList<>();

        Arrays.sort(nums);
        int pre = nums[0] , len = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0 ; i < len ; i++){
            if(i > 0 && pre == nums[i]){
                continue;
            }else{
                // 求nums 中 i 到 len 范围内是否存在满足 两数之和等于 -nums[i]
                twoSum(result,nums,i,len);
                pre = nums[i];
            }
        }
        return result;
    }

    /**
     * 两数之和去重做法：
     * 判断其中一个指针是否与前一个值相同 ，若相同则跳过，此时保持另一个指针不变
     */
    private static void twoSum(List<List<Integer>> result, int[] nums, int i, int len) {
        if(i + 3 <= len){
            int j = i + 1 , k = len - 1, target = -1*nums[i];
            int pj = j , pjv = nums[j];
            while(j < k){
                if(nums[j] + nums[k] > target){
                    k -- ;
                }else if(nums[j] + nums[k] < target){
                    j++;
                }else{
                    if(j == pj || nums[j] != pjv){
                        List<Integer> combin = new ArrayList<>();
                        combin.add(nums[i]);
                        combin.add(nums[j]);
                        combin.add(nums[k]);
                        result.add(combin);
                        pjv = nums[j];
                    }
                    j++;k--;
                }
            }
        }
    }

}
