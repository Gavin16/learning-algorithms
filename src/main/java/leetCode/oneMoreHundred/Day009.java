package leetCode.oneMoreHundred;

/**
 * @Date：2020年7月16日  DP 算法3连
 * ==============================================================================
 * 392. 判断子序列 【动态规划，】
 * ==============================================================================
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 *
 * 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
 *
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
 * （例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 *
 * 示例 1:
 * s = "abc", t = "ahbgdc"
 * 返回 true.
 *
 * 示例 2:
 * s = "axc", t = "ahbgdc"
 * 返回 false.
 *
 * 后续挑战 :
 * 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 * ==============================================================================
 * 198. 打家劫舍 【动态规划，】
 * ==============================================================================
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 * 示例 1：
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2：
 *
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * 提示：
 * 0 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 * ==============================================================================
 * 面试题 16.17. 连续数列 【贪心算法, 动态规划，分治算法】
 * ==============================================================================
 * 给定一个整数数组，找出总和最大的连续数列，并返回总和。
 *
 * 示例：
 * 输入： [-2,1,-3,4,-1,2,1,-5,4]
 * 输出： 6
 * 解释： 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶：
 *
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * ==============================================================================
 */
public class Day009 {

    public static void main(String[] args) {
        int[] arr = {-2,1,-3,4,-1,2,1,-5,4};
        int[] arr1 = {-2};
        int[] arr2 = {-2,-3,4,-1};
        int[] arr3 = {-2,1};

        System.out.println(maxSubArray3(arr));
        System.out.println(maxSubArray3(arr1));
        System.out.println(maxSubArray3(arr2));
        System.out.println(maxSubArray3(arr3));
    }

    /**
     * @Title: 392. 判断子序列
     * @Version: 版本1
     * @param s
     * @param t
     * @return
     */
    public static boolean isSubsequence(String s, String t) {
        int k = 0 ;
        for(char ch : s.toCharArray()){
            while(k < t.length() && ch != t.charAt(k)) k++;
            k++;
        }
        return k <= t.length();
    }


    /**
     * @Title: 198. 打家劫舍
     * @Version: 版本1 动态规划
     * 设定 maxAmt(i) 代表偷窃到第i个房屋所能获得的最大金额
     * 那么状态转移方程如下:
     * maxAmt(i) = max(maxAmt(i-1), maxAmt(i-2) + num[i])
     *
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        if(nums.length < 2){
            return nums.length == 1 ? nums[0] : 0;
        }
        int prePre = nums[0],pre = Math.max(nums[0],nums[1]);
        int maxAmt = pre;
        for(int k = 2 ; k < nums.length ; k++){
            maxAmt = Math.max(pre, prePre + nums[k]);
            prePre = pre;
            pre = maxAmt;
        }
        return maxAmt;
    }


    /**
     * @Title:  面试题 16.17. 连续数列
     * @Version: 版本1 遍历求连续子序列最大和
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        if(nums.length < 2) return nums.length == 0 ? 0 : nums[0];
        int currSum = nums[0],maxSum =  currSum;

        for(int i = 1 ; i < nums.length; i++){
            if(currSum >= 0){
                currSum += nums[i];
            }else if(nums[i] >= 0){
                currSum = nums[i];
            }else{
                currSum = Math.max(currSum,nums[i]);
            }

            maxSum = currSum > maxSum ? currSum : maxSum;
        }
        return maxSum;
    }


    /**
     * @Title:  面试题 16.17. 连续数列
     * @Version: 版本2 贪心算法， 版本1优化
     * @param nums
     * @return
     */
    public static int maxSubArray1(int[] nums) {
        if(nums.length < 2) return nums.length == 0 ? 0 : nums[0];
        int currSum = nums[0],maxSum =  currSum;

        for(int i = 1 ; i < nums.length; i++){
            if(currSum >= 0){
                currSum += nums[i];
            }else{
                currSum = nums[i];
            }

            maxSum = currSum > maxSum ? currSum : maxSum;
        }
        return maxSum;
    }


    /**
     * @Title:  面试题 16.17. 连续数列
     * @Version: 版本3 动态规划实现
     * 状态定义:
     * dp[i] 代表以i结尾的最大连续子序列的和
     *
     * 状态转移方程：
     * dp[i]    =  dp[i-1] + nums[i]  (dp[i-1] > 0)
     *       or =  nums[i]            (dp[i-1] < 0)
     *
     * 状态转移方程的含义
     * (1)若以 i-1 结尾的最大子序列的和小于0, 那么从i开始重新计算子序列
     * (2)若以i-1 结尾的子序列的和大于0，则继续累加
     *
     * 情况(2) 在将子序列和累加为负数之前一定会有一个最大值出现，这样再扫描一遍dp数组就可获得
     * 最大子序列的和
     *
     * @param nums
     * @return
     */
    public static int maxSubArray2(int[] nums) {
        if(nums.length <= 1) return nums.length == 1 ? nums[0] : 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for(int j = 1 ; j < nums.length ;j++){
            if(dp[j-1] > 0){
                dp[j] = dp[j-1] + nums[j];
            }else{
                dp[j] = nums[j];
            }
        }
        int maxSum = dp[0];
        for(int n : dp){
            if(n > maxSum) maxSum =n;
        }
        return maxSum;
    }

    /**
     * @Title: 面试题 16.17. 连续数列
     * @Version: 版本4 动态规划空间复杂度优化实现(一次循环实现)
     * @param nums
     * @return
     */
    public static int maxSubArray3(int[] nums){
        if(nums.length <= 1) return nums.length == 1 ? nums[0] : 0;
        int maxSum = nums[0];

        for(int i = 1 ; i < nums.length ; i++){
            if(nums[i-1] > 0){
                nums[i] += nums[i-1];
            }
            maxSum = nums[i] > maxSum ? nums[i] : maxSum;
        }
        return maxSum;
    }

}
