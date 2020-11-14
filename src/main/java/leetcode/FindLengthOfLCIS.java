package leetcode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @Class: FindLengthOfLCIS
 * @Description:
 *  LeetCode 697. 数组的度
 *  给定一个非空且只包含非负数的整数数组 nums, 数组的度的定义是指数组里任一元素出现频数的最大值。
 *
 *  你的任务是找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 *
 *  示例 1:
 *
 *  输入: [1, 2, 2, 3, 1]
 *  输出: 2
 *  解释:
 *  输入数组的度是2，因为元素1和2的出现频数最大，均为2.
 *  连续子数组里面拥有相同度的有如下所示:
 *  [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 *  最短连续子数组[2, 2]的长度为2，所以返回2.
 *  示例 2:
 *
 *  输入: [1,2,2,3,1,4,2]
 *  输出: 6
 *
 * @Author: Minsky
 * @Date: 2019/7/30 20:44
 * @Version: v1.0
 */
public class FindLengthOfLCIS {

    public static void main(String[]args){
        int[] arr  =new int[]{1, 2};
        System.out.println(findShortestSubArray(arr));
    }


    public static int findLengthOfLCIS(int[] nums) {

        if(nums == null || nums.length < 1) return 0;
        if(nums.length == 1) return 1;

        int max = 0;
        int cnt = 0;
        for(int i = 0, j = i+1 ; j < nums.length ; i++ , j++){
            if(nums[j] > nums[i]){
                cnt ++;
            }else{
                cnt = 0;
            }

            if(cnt > max){
                max = cnt;
            }
        }

        return max;
    }

    public static int findShortestSubArray(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();
        int max = 1;

        for(int i = 0 ; i < nums.length ; i++){
            if(map.containsKey(nums[i])){
                int cnt = map.get(nums[i]);
                cnt ++ ;
                if(cnt >= max){
                    if(cnt > max){
                        max = cnt;
                        set.clear();
                    }
                    set.add(nums[i]);
                }
                map.put(nums[i],cnt);
            }else{
                map.put(nums[i],1);
            }
        }

        //出现次数等于数组度的元素都保存在 set 中， 遍历set 可以找出所有长度最短的子数组
        int min = set.isEmpty() ? 1 : nums.length;
        for(int n : set){
            int j = -1 , k = nums.length;
            while(nums[++j] != n);
            while(nums[--k] != n);

            if(k - j + 1 < min){
                min = k - j +1;
            }
        }
        return min;
    }
}
