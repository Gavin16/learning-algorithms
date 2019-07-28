package interviewQuestions;

import utils.ArrayUtil;

import java.util.Arrays;

/**
 * @Class: CouplesInArray
 * @Description:
 *  找出数组两个数加起来等于某个数的所有可能组合数
 *
 *     对应 LeetCode 167题
 *
 *     考虑以下几点：
 *          (1) 数组中是否存在重复的数,如果有重复是否需要考虑
 *          (2) 是否存在负数,如果有负数就不可通过单个元素判断
 *
 *  -- 问题衍生出 ：
 *      (1)两个数加起来小于(大于)某个数的所有可能数
 *      (2)不限元素个数的和为某个值的组合数求解
 *
 * @Author: Minsky
 * @Date: 2019/7/28 8:18
 * @Version: v1.0
 */
public class CouplesInArray {

    public static void main(String[]args){
        int[] testSol1 = ArrayUtil.randValueArray(30);
        ArrayUtil.showArray(testSol1);
        int count = solution1(testSol1, 18,true);
        ArrayUtil.showArray(testSol1);
        System.out.println(count);
    }

    /**
     * 方法一： 先对数组排序，然后在排好序数组两端同时对数组做遍历
     * @param arr
     */
    public static int solution1(int[] arr, int target, boolean repeat){
        // 对数组排序, ArrayS 已有现成方法可用
        Arrays.sort(arr);
        // 遍历
        int last = arr.length - 1;
        int first = 0;
        int cpCnt = 0 , sum;
        while(last > first){
            // 和小于等于 target 首指针均向后移动
            sum = arr[first] + arr[last];
            if(sum < target) {
                first++;
            }else if(sum > target){
                last--;
            }else{
                if(arr[first] == arr[last]){
                    int numCount = ArrayUtil.countRepeatTimes(arr,arr[first]);
                    cpCnt = repeat ? cpCnt + ((numCount-1)*numCount)/2 : cpCnt + 1;
                    // 相等意味着循环可以立即结束
                    break;
                }else{
                    // 否则分别计算两个元素出现的次数，并计算他们所有可能的组合数
                    int firstCnt = ArrayUtil.countRepeatTimes(arr,arr[first]);
                    int lastCnt = ArrayUtil.countRepeatTimes(arr,arr[last]);
                    cpCnt = repeat ? cpCnt + firstCnt*lastCnt : cpCnt + 1;

                    first += firstCnt;
                    last += lastCnt;
                }
            }
        }

        return cpCnt;
    }

    /**
     * (*)问题衍生：
     * 两数的和小于某个指定的值
     * @param arr
     * @param target
     * @param repeat 是否考虑重复的情况
     * @return
     */
    public static int sumValueLessThanN(int[] arr, int target, boolean repeat){
        // 先不考虑重复的情况  TODO
        // 当前思路都是 O(n^2)
        return 0;
    }


}
