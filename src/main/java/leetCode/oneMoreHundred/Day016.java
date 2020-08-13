package leetCode.oneMoreHundred;


import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2020年8月13日
 * ==============================================================================
 * 46. 全排列
 * ==============================================================================
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 * ==============================================================================
 * 93. 复原IP地址
 * ==============================================================================
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 *
 * 有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。
 *  
 *
 * 示例:
 *
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 * ==============================================================================
 */
public class Day016 {

    public static void main(String[] args) {
        int[] nums = {};
        List<List<Integer>> permute = permute(nums);
        for(List<Integer> list : permute){
            for(Integer n : list){
                System.out.print(n + " ");
            }
            System.out.println();
        }
    }


    /**
     * @Title: 46. 全排列
     * @Version: 回溯算法实现
     * 假设输入num 长度为 N,则全排列的所有组合的情况为:
     * f(n) = {第一个数为num[0]|f(n-1)} U {第一个数为num[1] | f(n-1)} U ... U {第一个数为num[n-1] | f(n-1)}
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        dfsAddPermute(result,nums,len,0);
        return result;
    }

    /**
     * 深度优先遍历
     * @param nums
     * @param len
     * @param k
     * @return
     */
    private static void dfsAddPermute(List<List<Integer>> result, int[] nums, int len, int k) {
        if(k == len-1){
            List<Integer> list = new ArrayList<>();
            for(int n : nums){
                list.add(n);
            }
            result.add(list);
        }
        for(int i = k ; i < len ; i++){
            swap(nums, i, k);
            dfsAddPermute(result,nums,len,k+1);
            swap(nums, i, k);
        }
    }

    private static void swap(int[]nums , int i , int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    /**
     * @Title: 93. 复原IP地址
     * @param s
     * @return
     */
    public static List<String> restoreIpAddresses(String s) {
        return null;
    }

}
