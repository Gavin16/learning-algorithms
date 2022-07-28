package leetcode.byteDance;


import utils.ArrayUtil;

import java.util.*;

/**
 * @className: Day01
 * @description:
 * @version: 1.0
 * @author: minsky
 * @date: 2022/7/27
 */
public class Day01 {

    public static void main(String[] args) {
        int[] arr = {2,7,11,15};
        int[] arr1 = {3,2,4};
        int[] arr2 = {3,3};
        Day01 day01 = new Day01();
//        ArrayUtil.showArray(day01.twoSum(arr,9));
//        ArrayUtil.showArray(day01.twoSum(arr1, 6));
//        ArrayUtil.showArray(day01.twoSum(arr2, 6));

        String s1 = "abcabcbb";
        String s2 = "bbbbb";
        String s3 = "pwwkew";

//        System.out.println(day01.lengthOfLongestSubstring(s1));
//        System.out.println(day01.lengthOfLongestSubstring(s2));
//        System.out.println(day01.lengthOfLongestSubstring(s3));

        int[] t1 = {3,74,75,71,69,72,76,73};
        int[] t2 = {30,40,50,60};
        int[] t3 = {10};
//        ArrayUtil.showArray(day01.dailyTemperatures(t1));
//        ArrayUtil.showArray(day01.dailyTemperatures(t2));
//        ArrayUtil.showArray(day01.dailyTemperatures(t3));

        String ss = "babad";
        String ss1 = "bababe";
        String ss2 = "tt";
        String ss3 = "ta";
        String ss4 = "babaddtattarrattatddetartrateedredividerb";
        System.out.println(day01.longestPalindrome(ss));
        System.out.println(day01.longestPalindrome(ss1));
        System.out.println(day01.longestPalindrome(ss2));
        System.out.println(day01.longestPalindrome(ss3));
        System.out.println(day01.longestPalindrome(ss4));
    }


    /**
     * 1. 两数之和
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * 你可以按任意顺序返回答案。
     *
     * 示例 1：
     *
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     *
     * 示例 2：
     *
     * 输入：nums = [3,2,4], target = 6
     * 输出：[1,2]
     * 示例 3：
     *
     * 输入：nums = [3,3], target = 6
     * 输出：[0,1]
     *
     * 提示：
     *
     * 2 <= nums.length <= 104
     * -109 <= nums[i] <= 109
     * -109 <= target <= 109
     * 只会存在一个有效答案
     * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
     *
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        int[] ans = new int[2];
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(target - nums[i])){
                ans[0] = i;
                ans[1] = map.get(target - nums[i]);
                return ans;
            }
            map.putIfAbsent(nums[i] , i);
        }
        return ans;
    }


    /**
     * 3. 无重复字符的最长子串
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * 示例 1:
     *
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     *
     * 输入: s = "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     *
     * 输入: s = "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     *  
     * 提示：
     *
     * 0 <= s.length <= 5 * 104
     * s 由英文字母、数字、符号和空格组成
     *
     *
     * 使用Hash + 滑动窗实现
     *
     * 滑动窗用来截取字符串的长度
     * Hash存储滑动窗口内出现的字符，用于判断是否重复
     *
     */
    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0, sLen = s.length();
        if(sLen <= 1) return sLen;

        Set<Character> set = new HashSet<>();
        for(int l = 0, r = 0; l < sLen; l++){
            if(l > 0){
                set.remove(s.charAt(l - 1));
            }
            // 从 r 开始往后一直遍历, 直到出现重复字符
            while(r < sLen && !set.contains(s.charAt(r))){
                set.add(s.charAt(r));
                r++;
            }
            maxLen = Math.max(maxLen, r - l);
        }
        return maxLen;
    }


    /**
     *
     * 739. 每日温度
     * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，
     * 其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
     *
     * 示例 1:
     *
     * 输入: temperatures = [73,74,75,71,69,72,76,73]
     * 输出: [1,1,4,2,1,1,0,0]
     * 示例 2:
     *
     * 输入: temperatures = [30,40,50,60]
     * 输出: [1,1,1,0]
     * 示例 3:
     *
     * 输入: temperatures = [30,60,90]
     * 输出: [1,1,0]
     *  
     * 提示：
     *
     * 1 <= temperatures.length <= 105
     * 30 <= temperatures[i] <= 100
     *
     * 使用单调递减栈实现
     *
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        Deque<Integer> stack = new LinkedList<>();
        int[] ans = new int[len];
        for(int i = 0; i < len; i++){
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
                Integer pop = stack.pop();
                ans[pop] = i - pop;
            }
            stack.push(i);
        }
        return ans;
    }


    /**
     * 5. 最长回文子串
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     *
     * 示例 1：
     *
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     * 示例 2：
     *
     * 输入：s = "cbbd"
     * 输出："bb"
     *
     * 提示：
     *
     * 1 <= s.length <= 1000
     * s 仅由数字和英文字母组成
     *
     * (1) 深度优先搜索 -->  超时
     * (2) 动态规划
     * 动态规划使用 dp[][] 二维数组保存是否是回文字符串
     * 其中横坐标 i 表示字符串起始下标, j 表示字符串终止下标 dp[i][j] 表示子串s.subString(i,j+1) 是否是回文串
     * 显然有 dp[i][j] = true (i = j)
     *       dp[i][j] = false (i > j)
     * 状态转移方程:
     *      dp[i][j] = dp[i+1][j-1] && true  (s[i] == s[j])
     *               = false
     *
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        for(int i = 0; i < len; i++){
            dp[i][i] = true;
        }
        // 斜对角线方向以转移方程迭代dp二维数组
        int maxLen = 1,start = 0, end = 0;
        for(int right = 1; right < len ; right++){
            for(int left = 0; left < right; left++){
                if(s.charAt(left) == s.charAt(right)
                        && (right - left <= 2 || dp[left+1][right-1])){
                    dp[left][right] = true;
                    if(right - left + 1> maxLen){
                        maxLen = right - left + 1;
                        start = left;
                        end = right;
                    }
                }
            }
        }
        return s.substring(start, end + 1);
    }

    // dfs(s, 0, s.length() - 1);
    private String dfs(String s, int left, int right){
        if(left > right ) return "";
        int lp = left, rp = right;
        while(lp < rp){
            if(s.charAt(lp) != s.charAt(rp)){ break; }
            lp++;
            rp--;
        }
        if(lp >= rp){
            return s.substring(left, right + 1);
        }
        String s1 = dfs(s, left + 1, right);
        String s2 = dfs(s, left, right - 1);
        return s1.length() > s2.length() ? s1 : s2;
    }

}
