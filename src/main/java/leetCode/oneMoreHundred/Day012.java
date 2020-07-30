package leetCode.oneMoreHundred;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
     * @Version: 版本1 单次循环(双指针)实现
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if(s.length() < 2) return s.length();
        int startId = 0,maxLen = 0;
        Set<Character> charSet = new HashSet<>();
        for(int k = 0 ; k < s.length() ; k++){
            char c = s.charAt(k);
            if(charSet.contains(c)){
                while(charSet.contains(c)){
                    char cl = s.charAt(startId++);
                    charSet.remove(cl);
                }
            }
            charSet.add(c);
            maxLen = Math.max(maxLen,charSet.size());
        }
        return maxLen;
    }


    /**
     * @Title: 3. 无重复字符的最长子串长度
     * @version： 版本2 滑动窗(版本1简化)
     * 这里优化的地方在于:当发现前面有重复自字符时不再需要循环的去遍历处理
     * 若采用HashMap 保存字符-下标  k-v 对时，若发现重复可以立刻获取到重复字符对应的下标，
     * 然后让子串的起始位置从重复字符的下一个字符开始算起即可；该解法巧妙的地方在于:
     *    (*) 当发现重复子串起始下标从left 开始后,Map 中重复字符之前的元素不需要移除
     *    不移除怎么避免重复字符之前的元素被重复误判为重复呢，基于以下两点
     *    (1) Map 每次都会把最新的字符保存进来,新的重复字符会覆盖原有的重复字符的下标
     *    (2) left 更新时每次取 重复字符的下标 和 left自身 两者中间的较大值
     *
     *    当map判断有重复字符时，对应的所有情况如下
     *    (I) map没有value 小于left 的k-v对
     *    (II) map中有value 小于left,重复元素对应value 小于left
     *    (III) map中有value 小于left,重复元素对应 value 大于left
     *
     *    对于情况(I),对应为初始的情况, 直接取 map.get(c) 作为left 的更新值即可
     *    对于情况(II) 说明就是在当前子串中出现了重复元素(可能是第一次出现,也可能是多次出现后面的覆盖了前面的)，处理方式同(I)
     *    对于情况(III) 说明在当前子串的前面出现了重复元素,这种情况可以忽略,之所以会有这种情况是因为 map中left之前的元素没有清楚
     *    所有这三种情况都可以通过 left = Math.max(left,map.get(c) + 1) 来实现更新
     *
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s){
        if(s.length() < 2) return s.length();

        Map<Character,Integer> map = new HashMap<>();
        int left = 0,max = 0 ;
        for(int i = 0 ; i < s.length() ; i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                left = Math.max(left,map.get(c) + 1);
            }
            map.put(c,i);
            max = Math.max(max,i - left +1);
        }
        return max;
    }

}
