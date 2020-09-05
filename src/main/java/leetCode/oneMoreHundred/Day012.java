package leetCode.oneMoreHundred;

import java.util.*;

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
 * 5. 最长回文子串
 * ==============================================================================
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * ==============================================================================
 * 152. 乘积最大子数组
 * ==============================================================================
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 * 示例 1:
 *
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 *
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * ==============================================================================
 * 53. 最大子序和
 *
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 *
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * ==============================================================================
 *
 */
public class Day012 {

    public static void main(String[] args) {
        int[] test1 = {1,2,2,3,4,4,5,6,6,7,8,9};
        int[] test2 = {1,2,2,3,3,3,5,6,6,7,7,9};
        int[] test3 = {1,2,2,3,4,5,5,5,6,7,8,9};
        int[] test4 = {1,2,3,3,4,4,5,6,6,6,6,9};


        // 最长回文字串
        String tt = "babad";
        String tt1 = "aaaa";
        String ss = longestPalindrome2(tt);
        System.out.println(ss);

        System.out.println(longestPalindrome3(tt1));

        // 最大子序和
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4,3};
        System.out.println(new Day012().maxSubArray2(nums));

        // 最大乘积子数组
        int[] nums1 = {2,3,-2,4};
        System.out.println(new Day012().maxProduct2(nums1));

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


    /**
     * @Title: 5. 最长回文子串
     * @Version: 版本1 滑动窗解法 算法错误
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if(s.length() <= 1) return s;
        char[] carr = s.toCharArray();
        int len = carr.length;
        // 从前到后搜索
        String max1 = findMaxByOrder(carr, len);
        // 从后到前搜索
        for(int k = 0 ; k < len/2 ; k++){
            char temp = carr[k];
            carr[k] = carr[len - k - 1];
            carr[len-k-1] = temp;
        }
        String max2 = findMaxByOrder(carr, len);
        return max1.length() > max2.length() ? max1 : max2;
    }

    /**
     * @return
     */
    public static String findMaxByOrder(char[] carr , int len){
        String maxStr = "";
        for(int i = 0;  i < len ; i++){
            int head = i , tail = len-1;
            while(head < tail){
                if(carr[head] !=carr[tail]){break;}
                else{
                    head++;tail--;
                }
            }
            // 找到便返回
            if(head >= tail){
                maxStr = String.valueOf(Arrays.copyOfRange(carr, i, len));
                break;
            }
        }
        return maxStr;
    }


    /**
     * @title: 5. 最长回文子串
     * @Version: 版本2 暴力破解法 O(N^3)时间复杂度
     * @param s
     * @return
     */
    public static String longestPalindrome2(String s){
        if(s.length() < 2) return s;
        char[] ca  = s.toCharArray();
        int maxLen = 1,startId = 0;
        int len = ca.length;
        for(int i = 0 ; i < len-1 ; i++){
            for(int k = i+1 ; k < len; k++){
                if(k - i +1 > maxLen && isPalindrome(ca,i,k)){
                    maxLen = k-i+1;
                    startId = i;
                }
            }
        }
        return s.substring(startId,startId + maxLen);
    }



    private static boolean isPalindrome(char[] chars ,int s , int e){
        int head = s ,tail = e ;
        while(head < tail){
            if(chars[head] != chars[tail]){
                return false;
            }
            head ++ ; tail --;
        }
        return true;
    }


    /**
     * @title: 5. 最长回文子串
     * @Version: 版本3 动态规划解法
     * 状态定义:
     * dp[i][j] 代表以下标i为起始下标,下标j为终止下标的字串是否是回文字符串
     * 状态转移方程:
     * dp[i][j] = dp[i+1][j-1]   (s[i] == s[j] && j - i >= 3)
     *          = true           (s[i] == s[j] && j - i < 3)
     *          = false          (s[i] != s[j])
     * 返回结果:
     * 记录maxLen并更新状态, 返回 s.subString(startId , startId + maxLen)
     *
     * @param s
     * @return
     */
    public static String longestPalindrome3(String s){
        if(s.length() < 2) return s;
        char[] ca = s.toCharArray();
        int len = s.length();
        int maxLen = 1, startId = 0;
        boolean[][] dp = new boolean[len][len];

        for(int k = 0; k < len ; k++){
            dp[k][k] = true;
        }

        // 由于dp[i][j] 需要用到dp[i+1][j-1] 的值,因此需要确保 i+1 行的值事先被赋值
        // 所以这里采用逐列对dp数组赋值的方式
        for(int j = 1 ; j < len ; j++){
            for(int i = 0 ; i < j ; i++){
                if(ca[i] == ca[j]){
                    if(j - i >= 3){
                        dp[i][j] = dp[i+1][j-1];
                    }else{
                        dp[i][j] = true;
                    }
                }else{
                    dp[i][j] = false;
                }
                if(dp[i][j] == true && j - i + 1 > maxLen){
                    maxLen = j - i + 1;
                    startId = i;
                }
            }
        }
        return s.substring(startId,startId + maxLen);
    }

    /**
     * @Title: 152. 乘积最大子数组
     * @version: 动态规划解法
     *
     * 定义dp1[i]为以下标i结尾的乘积最大子数组的乘积
     * 定义dp2[i] 为以下标i结尾的乘积最小子数组的乘积
     *
     * 状态转移方程：
     * dp1[i] = Math.max(dp1[i-1]*nums[i],dp2[i-1]*nums[i],nums[i]);
     * dp2[i] = Math.min(dp1[i-1]*nums[i],dp2[i-1]*nums[i],nums[i]);
     *
     * 初始值:
     * dp1[0] = nums[0];
     * dp2[0] = nums[0];
     *
     * @param nums
     * @return
     */
    public static int maxProduct(int[] nums) {
        if(null == nums || nums.length == 0) return 0;
        int len = nums.length;
        int[]minDp = new int[len];
        int[]maxDp = new int[len];
        minDp[0] = nums[0];
        maxDp[0] = nums[0];
        for(int k = 1 ; k < len ; k++){
            maxDp[k] = Math.max(nums[k],Math.max(maxDp[k-1]*nums[k],minDp[k-1]*nums[k]));
            minDp[k] = Math.min(nums[k],Math.min(maxDp[k-1]*nums[k],minDp[k-1]*nums[k]));
        }
        int max = maxDp[0];
        for(int n : maxDp){
            max = Math.max(max , n);
        }
        return max;
    }


    /**
     * @Title: 152. 乘积最大子数组
     * @version: 动态规划 O(1) 空间复杂度
     * @param nums
     * @return
     */
    public static int maxProduct2(int[] nums){
        if(null == nums || nums.length == 0) return 0;
        int max = nums[0], maxCurr = max , minCurr = nums[0];

        for(int k = 1 ; k < nums.length ; k++){
            int maxTmp = maxCurr;
            maxCurr = Math.max(nums[k],Math.max(maxCurr * nums[k],minCurr * nums[k]));
            minCurr = Math.min(nums[k],Math.min(maxTmp * nums[k],minCurr * nums[k]));
            max = Math.max(max,maxCurr);
        }
        return max;
    }



    /**
     * @title: 53. 最大子序和
     * @version: 动态规划实现
     *
     * 状态定义：
     * 定义dp[i] 为以下标i结尾的最大子数组和
     *
     * 状态转移方程:
     * dp[i] = Math.max(dp[i-1]+nums[i],nums[i])
     *
     * 状态初始值:
     * dp[0] = nums[0];
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if(null == nums || nums.length == 0) return 0;
        int len = nums.length;
        int[]dp = new int[len];
        dp[0] = nums[0];
        for(int k = 1 ; k < len ; k++){
            dp[k] = Math.max(dp[k-1]+nums[k] , nums[k]);
        }
        int max = dp[0];
        for(int n : dp){
            max = Math.max(max,n);
        }
        return max;
    }

    /**
     * @title: 53. 最大子序和
     * @Version: 动态规划优化 -- O(1) 空间复杂度
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
        if(null == nums || nums.length == 0) return 0;
        int curr = nums[0], max = curr;
        for(int k = 1 ; k < nums.length ; k++){
            curr = Math.max(nums[k] + curr,nums[k]);
            max = Math.max(curr,max);
        }
        return max;
    }

}
