package leetcode.oneMoreHundred;

import utils.ArrayUtil;

import java.util.Arrays;
import java.util.Comparator;

/**
 * ==============================================================================
 *《56. 合并区间》
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例2:
 *
 * 输入: intervals = [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * ==============================================================================
 * 《1143. 最长公共子序列》
 *  给定两个字符串text1 和text2，返回这两个字符串的最长公共子序列的长度。
 *
 *  一个字符串的子序列是指这样一个新的字符串：
 *  它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 *
 *  例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
 *
 *  若这两个字符串没有公共子序列，则返回 0
 *
 *
 *  例如：
 *  输入：text1 = "abcde", text2 = "acef"
 *  输出：3
 *  解释：最长公共子序列是 "ace"，它的长度为 3。
 * ==============================================================================
 */
public class Day026 {


    public static void main(String[] args) {

        int[][] test = {{1,2},{3,9},{8,10},{15,18}};
        int[][] merge = new Day026().merge(test);
        for(int[] ele : merge){
            ArrayUtil.showArray(ele);
        }

        System.out.println("------ longest Common Subsequence---------- ");
        String text1 = "abcde", text2 = "acef";
        System.out.println(new Day026().longestCommonSubsequence2(text1,text2));

    }

    /**
     * 合并区间
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if(null == intervals || intervals.length == 0) return intervals;
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int cid = 0;
        int[] row = intervals[0];
        for(int k = 1 ; k < intervals.length ; k++){
            if(intervals[k][0] <= row[1]){
                row[1] = Math.max(intervals[k][1],row[1]);
            }else{
                intervals[cid++] = row;
                row = intervals[k];
            }
        }
        intervals[cid] = row;
        return Arrays.copyOf(intervals,cid+1);
    }

    /**
     * (1) 暴力递归实现
     *
     * 很容易的可以看到，对于输入的两个字符串是不变的，可变的就是对字符串进行遍历的下标，
     * 首先考虑采用从左往右的尝试模型:
     * 那边对应的递归函数的定义就为 f(i,j)  i∈[0,text1.length), j ∈[0,text2.length)
     *
     * 调用时的传参为: f(text1.length-1,text2.length-1)
     * 递归调用的边界条件有：
     * ① i = 0 , j = 0 :  f(i,j) = (text1[0] == text2[0]) ? 1 : 0;
     * ② i = 0 , j ∈[1,text2.length) ; f(i,j) = max(f(0,j-1) , text1[0] == text2[j] ? 1 : 0);
     * ③ j = 0 , i ∈[1,text1.length) ; f(i,j) = max(f(i-1,0) , text1[i] == text2[0] ? 1 : 0);
     *
     * 递归调用的依赖关系(情况枚举)为:
     *
     * f(i,j) =  f(i-1,j-1)   ;     text1[i] != text2[j] 且 text1[i],text2[j] 并没有增加公共子序列的长度
     * f(i,j) =  max(f(i-1,j) , f(i,j-1)) ; text1[i] != text2[j] 且 text1[i] 或者 text2[j] 增加了公共子序列的长度
     * f(i,j) =  f(i-1,j-1) + 1 ; text1[i] = text2[j]
     *
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence1(String text1, String text2) {
        int len1 = text1.length() ,len2=  text2.length();
        return f(text1.toCharArray(),text2.toCharArray(),len1-1,len2-1);
    }


    private int f(char[]ch1 , char[]ch2 , int i , int j){
        if(i == 0 && j == 0){
            return ch1[i] == ch2[j] ? 1 : 0;
        }else if(i == 0){
            return Math.max(f(ch1,ch2,i,j-1) , ch1[i] == ch2[j] ? 1 : 0);
        }else if(j == 0){
            return Math.max(f(ch1,ch2,i-1,j) , ch1[i] == ch2[j] ? 1 : 0);
        }
        // i, j均不为 0的情况
        int p = -1;
        int ret = Math.max(f(ch1,ch2,i-1,j),f(ch1,ch2,i,j-1));
        if(ch1[i] == ch2[j]){
            p = f(ch1,ch2,i-1,j-1) + 1;
        }
        return Math.max(p,ret);
    }



    /**
     * (2) 基于暴力递归的动态规划改造
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence2(String text1, String text2) {
        int RN = text1.length(), CN = text2.length();
        char[] ch1 = text1.toCharArray(), ch2 = text2.toCharArray();
        int[][] dp = new int[RN][CN];
        // 初始化DP数组
        dp[0][0] = (ch1[0] == ch2[0]) ? 1 : 0;
        for(int j = 1 ; j < CN ; j++){
            dp[0][j] = Math.max(dp[0][j-1], ch1[0] == ch2[j]? 1 : 0);
        }
        for(int i = 1 ; i < RN ; i++){
            dp[i][0] = Math.max(dp[i-1][0] , ch1[i] == ch2[0] ? 1 : 0);
        }
        // 状态转移
        for(int i = 1 ; i < RN ; i++){
            for(int j = 1 ; j < CN ; j++){
                int temp = Math.max(dp[i-1][j],dp[i][j-1]);
                int  kk = -1;
                if(ch1[i] == ch2[j]){
                    kk = dp[i-1][j-1] + 1;
                }
                dp[i][j] = Math.max(kk,temp);
            }
        }
        return dp[RN-1][CN-1];
    }
}
