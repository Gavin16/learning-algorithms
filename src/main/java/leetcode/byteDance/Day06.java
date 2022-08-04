package leetcode.byteDance;

import edu.princeton.cs.StdRandom;
import utils.ArrayUtil;

import java.util.*;

/**
 * @className: Day06
 * @description:
 * @version: 1.0
 * @author: minsky
 * @date: 2022/8/2
 */
public class Day06 {

    public static void main(String[] args) {

        Day06 day06 = new Day06();
//        Random random = new Random();
//        int errCount = 0;
//        for(int i = 0; i < 10000; i++){
//            int rand;
//            while((rand = random.nextInt(50)) < 2 ) rand = random.nextInt(50);
//            int[] ints = ArrayUtil.randValueArray(rand);
//            StdRandom.shuffle(ints);
//
//            day06.mergeSort(ints);
//
//            for(int k = 0; k < ints.length - 1; k++){
//                if(ints[k] > ints[k+1]){
//                    System.out.println("排序错误");
//                    ArrayUtil.showArray(ints);
//                    errCount++;
//                    break;
//                }
//            }
//        }
//        System.out.println("总计出错次数: " + errCount);

//        int[] nums1 = {432,43243};
//        int[] nums2 = {90,9001,77};
//        int[] nums3 = {1,2,3,4,5,6,7,8,9};
//        int[] nums4 = {3,30,34,5,9};
//        System.out.println(day06.largestNumber(nums1));
//        System.out.println(day06.largestNumber(nums2));
//        System.out.println(day06.largestNumber(nums3));
//        System.out.println(day06.largestNumber(nums4));

//        System.out.println("9".compareTo("43"));
//        System.out.println("01".compareTo("43"));


        System.out.println(day06.countDigitOne(13));
        System.out.println(day06.countDigitOne(100));
        System.out.println(day06.countDigitOne(102));
        System.out.println(day06.countDigitOne(110));
    }


    /**
     * 归并排序回顾确认
     */
    public void mergeSort(int[] nums){
        int len = nums.length;
        int[] reg = new int[len];
        mergeSortRecursive(nums, reg, 0, len-1);
    }

    /**
     * 先递归再合并
     */
    private void mergeSortRecursive(int[]nums , int[] reg, int start, int end){
        if(start >= end) return;

        int p = start + ((end - start) >> 1);
        mergeSortRecursive(nums, reg, start, p);
        mergeSortRecursive(nums, reg, p+1, end);

        // 合并
        int start1 = start, end1 = p;
        int start2 = p + 1, end2 = end;
        int k = start;
        while(start1 <= end1 && start2 <= end2){
            reg[k++] = nums[start1] <= nums[start2] ? nums[start1++] : nums[start2++];
        }

        while(start1 <= end1) reg[k++] = nums[start1++];
        while(start2 <= end2) reg[k++] = nums[start2++];

        for(int i = start; i <= end; i++){
            nums[i] = reg[i];
        }
    }



    /**
     * 华为算法题-1
     *
     * 179. 最大数
     * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
     * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
     *
     * 示例 1：
     *
     * 输入：nums = [10,2]
     * 输出："210"
     * 示例 2：
     *
     * 输入：nums = [3,30,34,5,9]
     * 输出："9534330"
     *
     * 提示：
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 109
     *
     * 贪心算法: 将数字转化为字符串，并将字符串拼接之后进行排序
     * 当字符串在排序做两两比较时，如果拼接出来的字符串更大，那么就返回对应降序
     * 这里做比较时，由于调换顺序进行拼接可以做到 比较的是相同的两个字符串数字
     *
     *
     */
    public String largestNumber(int[] nums) {
        int len = nums.length;
        String[] strs = new String[len];
        for(int i = 0; i < len; i++) strs[i] = "" + nums[i];

        Arrays.sort(strs, (s1,s2)->{
            String ss1 = s1 + s2, ss2 = s2 + s1;
            return ss2.compareTo(ss1);
        });

        StringBuilder sb = new StringBuilder();
        for(String str : strs) sb.append(str);
        // 去掉前导0
        int k = 0;
        while(k < sb.length() - 1 && sb.charAt(k) == '0') k++;
        return sb.substring(k);
    }


    /**
     * 华为面试题2
     * 剑指 Offer 43. 1～n 整数中 1 出现的次数
     *
     * 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
     * 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
     *
     * 示例 1：
     *
     * 输入：n = 12
     * 输出：5
     * 示例 2：
     *
     * 输入：n = 13
     * 输出：6
     *  
     * 限制：
     *
     * 1 <= n < 2^31
     *
     */
    public int countDigitOne(int n) {
        int ans = 0, h = 0, num = n;
        for(; num != 0; h++){ num = num / 10; }
        // k 从个位(第0位) 开始, base = 10^k
        int k = 0, base = 1;
        while(k < h){
            ans += (n / (10 * base)) * base + Math.min(Math.max((n % (10 * base)) - base + 1,0), base);
            base = 10 * base;
            k++;
        }
        return ans;
    }
}
