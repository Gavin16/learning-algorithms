package leetCode.oneMoreHundred;

/**
 * @Date: 2020年7月26日
 * ==============================================================================
 * 二分查找变种问题(存在重复元素)
 * ==============================================================================
 * 4种常见的二分查找变形问题
 * ① 查找第一个等于给定值的元素
 * ② 查找最后一个等于给定值的元素
 * ③ 查找第一个大于等于给定值的元素
 * ④ 查找最后一个小于等于给定值的元素
 * ==============================================================================
 * 3. 无重复字符的最长子串
 * ==============================================================================
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * ==============================================================================
 */
public class Day012 {

    public static void main(String[] args) {
        int[] test1 = {1,2,2,3,4,4,5,6,6,7,8,9};
        int[] test2 = {1,2,2,3,3,3,5,6,6,7,7,9};
        int[] test3 = {1,2,2,3,4,5,5,5,6,7,8,9};
        int[] test4 = {1,2,3,3,4,4,5,6,6,6,6,9};

    }

    /**
     * @Title: 查找第一个等于给定值的元素
     * @param nums
     * @param val
     * @return
     */
    public static int findFirstEqual(int[] nums, int val){
        int low = 0 , high = nums.length-1;
        int mid = -1;
        while(low <= high){
            mid = low + ((high-low)>>1);
            if(nums[mid] > val){
                high = mid - 1;
            }else if(nums[mid] < val){
                low = mid + 1;
            }else{
                if(mid == 0 || nums[mid-1] < val) return mid;
                high = mid - 1;
            }
        }
        return mid;
    }

    /**
     * @Title: 查找最后一个等于给定值的元素
     * @param nums
     * @param val
     * @return
     */
    public static int findLastEqual(int[] nums,int val){
        int low = 0 , high = nums.length-1;
        int mid = -1;
        while(low <= high){
            mid = low + ((high-low)>>1);
            if(nums[mid] > val){
                high = mid -1;
            }else if(nums[mid] < val){
                low = mid + 1;
            }else{
                if(mid == nums.length-1 || nums[mid+1] > val) return mid;
                low = mid + 1;
            }
        }
        return mid;
    }

    /**
     * @Title: 查找第一个大于等于给定值的元素
     * @param nums
     * @param val
     * @return
     */
    public static int findLastBigger(int[] nums,int val){
        int low = 0 , high = nums.length-1;
        int mid = -1;
        while(low <= high){
            mid = low + ((high-low)>>1);
            if(nums[mid] < val){
                low = mid + 1;
            }else{
                if(mid == 0 || nums[mid-1] < val) return mid;
                high = mid - 1;
            }
        }
        return mid;
    }

    /**
     * @Title: 查找最后一个小于等于给定值的元素
     * @param nums
     * @param val
     * @return
     */
    public static int findLastSmaller(int[] nums,int val){
        int low = 0 , high = nums.length-1;
        int mid = -1;
        while(low <= high){
            mid = low + ((high-low)>>1);
            if(nums[mid] > val){
                high = mid - 1;
            }else{
                if(mid == nums.length - 1 || nums[mid+1] > val) return mid;
                low = mid + 1;
            }
        }
        return mid;
    }


    /**
     * @Title: 3. 无重复字符的最长子串长度
     * @Version: 版本1 动态规划实现
     * (1) 状态定义
     * 定义maxLen[i] 为以i为末尾元素的最大无重复元素字串的长度
     * (2) 状态转移方程
     * maxLen[i] = maxLen[i-1] + 1    (当字串中不包含s[i]时 )
     *           =
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        return -1;
    }
}
