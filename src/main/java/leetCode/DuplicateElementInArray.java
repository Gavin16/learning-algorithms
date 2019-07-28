package leetCode;

import utils.ArrayUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @Class: DuplicateElementInArray
 * @Description:
 *
 * LeetCode 217
 *
 * 给定一个整数数组，判断是否存在重复元素。
 * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
 *
 * 示例 1:
 * 输入: [1,2,3,1]
 * 输出: true
 *
 * @Author: Minsky
 * @Date: 2019/7/28 15:07
 * @Version: v1.0
 */
public class DuplicateElementInArray {

    public static void main(String[]args){
        int[] input = ArrayUtil.randValueArray(20);
        Arrays.sort(input);
        ArrayUtil.showArray(input);
        System.out.println(containsDuplicate2(input));
    }



    /**
     * 使用HashMap 来实现
     * 注意一个问题：
     *      如果使用 map.put(id,value) 方式来存储将会非常耗性能 -- 考虑原因？？
     *       (1) map.put(id,value) 这种方式存储在判断是否包含某个元素时需要调用 containsValue方法
     *       (2)当调用containsKey 时只需要对key做hash 然后再在array 中的LinkedList 或者TreeNode中 搜索就能判断
     *       (3)如果调用containsValue 每次调用都需要对整个HashMap 做遍历 时间复杂度为O(n^3)
     * @param nums
     * @return
     */
    public static boolean containsDuplicate2(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0; i < nums.length ; i++){
            if(map.containsKey(nums[i])){
               return true;
            }else{
                map.put(nums[i],i);
            }
        }
        return false;
    }


    /**
     * 利用集合 Set 来去重并比较集合大小与数组大小
     * @param nums
     * @return
     */
    public static boolean containsDuplicate1(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

         for(int i=0 ; i < nums.length ; i++){
             set.add(nums[i]);
         }

         return set.size() == nums.length ? false : true;
    }
}
