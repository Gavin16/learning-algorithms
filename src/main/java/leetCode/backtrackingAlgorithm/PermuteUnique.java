package leetCode.backtrackingAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  《47. 全排列 II》
 *
 *  给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 *
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 *
 *
 */
public class PermuteUnique {

    public static void main(String[] args) {
        int[] nums = {1,1,2,3};
//        int[] nums = {0,1,0,0,9};
//        int[] nums = {-1,2,-1,2,1,-1,2,1};
        List<List<Integer>> lists = permuteUnique(nums);
        System.out.println(lists);
        System.out.println("lists size is " + lists.size());
    }


    /**
     *  全排列的基本规律是某一个位置不变，然后剩下的位置做全排列，这是作为递归调用的基础
     *  这里需要对全排列做枝减，枝减的基本思路如下：
     *
     *  在循环中固定数组中的每一个位置时，如果该位置之前已经被固定过，那么久跳过该位置；
     *  这样可以确保取值为该值的元素只在被固定的位置(递归时每次循环的第一个位置)出现一次
     *
     *
     */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        if(null == nums || nums.length == 0) return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        recursivePermute(result,nums,0);
        return result;
    }

    private static void recursivePermute(List<List<Integer>> result, int[] nums, int fid) {
        if(fid == nums.length){
            List<Integer> list = new ArrayList<>();
            for(int i = 0 ; i < nums.length; i++){
                list.add(nums[i]);
            }
            result.add(list);
            return;
        }

        for(int j = fid ; j < nums.length ; j++){
            // 是否需要固定该元素在在最左端
            boolean fix = true;
            for(int k = fid ; k < j ; k++){
                if(nums[k] == nums[j]) fix = false;
            }

            if(fix){
                swap(nums,fid,j);
                recursivePermute(result,nums,fid+1);
                swap(nums,fid,j);
            }
        }
    }

    private static void swap(int[] nums , int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
