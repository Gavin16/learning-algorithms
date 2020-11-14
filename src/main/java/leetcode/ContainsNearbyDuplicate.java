package leetcode;

import utils.ArrayUtil;

import java.util.HashMap;

/**
 * @Class: ContainsNearbyDuplicate
 * @Description:
 *  LeetCode 219
 *
 *  给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，
 *  使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k。
 *
 *
 *
 * @Author: Minsky
 * @Date: 2019/7/28 16:23
 * @Version: v1.0
 */
public class ContainsNearbyDuplicate {

    public static void main(String[]args){
//        int[] arr = ArrayUtil.randValueArray(20);
        int[] arr = new int[]{1,2,3,1};
        ArrayUtil.showArray(arr);

        System.out.println(containsNearbyDuplicate(arr,3));
    }


    /**
     *
     *
     * 与判断是否有重复元素不一样的地方在于：
     *          需要考虑两个相同元素之间距离，若距离不再指定范围内则需要再次将元素保存入map
     * @param nums
     * @param k
     * @return
     */
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();

        for(int i =0 ; i < nums.length ; i++){
            if(map.containsKey(nums[i])){
                int j = map.get(nums[i]);
                int d = Math.abs(j - i);
                if(d <= k){
                    return true;
                }
            }
            map.put(nums[i],i);

        }
        return false;
    }

}
