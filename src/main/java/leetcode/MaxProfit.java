package leetcode;

/**
 * @className: MaxProfit
 * @description: 309. 买卖股票的最佳时机含冷冻期
 *
 *  给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。​
 *
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 * 输入: prices = [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 *
 * 示例 2:
 * 输入: prices = [1]
 * 输出: 0
 *
 *  提示：
 * 1 <= prices.length <= 5000
 * 0 <= prices[i] <= 1000
 *
 *  @version: 1.0
 * @author: minsky
 * @date: 2023/9/4
 */
public class MaxProfit {

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
//        int[] prices = {7,6,5,4,3,2,1};
//        System.out.println(maxProfit1(prices));

        int[] prices1 = {1,2,3,0,2};
        System.out.println(maxProfit(prices1));
    }

    /**
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。
     * 设计一个算法来计算你所能获取的最大利润。
     */
    public static int maxProfit1(int[] prices) {
        if(null == prices || prices.length < 2) return 0;
        int min = prices[0] , max = 0;
        for (int price : prices) {
            if (price < min) min = price;
            max = Math.max(price - min, max);
        }
        return max;
    }


    /**
     * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
     * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。
     * 你也可以先购买，然后在 同一天 出售。
     * 返回 你能获得的 最大 利润。
     */
    public static int maxProfit2(int[] prices) {
        int len = prices.length;
        if(len < 2) return 0;
        int max = 0;
        for(int i = 1; i < len; i++){
            int diff = prices[i] - prices[i-1];
            if(diff > 0) max += diff;
        }
        return max;
    }

    /**
     * 买卖股票的最佳时机含冷冻期
     * 动态规划,使用二维DP数组求解
     * 定义 dp[i][0],dp[i][1],dp[i][2] 分别表示
     * 第i天持股, 第i天不持股(冷冻期) 和 第i天不持股(非冷冻期)
     *
     * 则对应的有状态转移方程
     * dp[i][0] = Max(dp[i-1][2]-price[i], dp[i-1][0])
     * dp[i][1] = dp[i-1][0] + price[i]
     * dp[i][2] = Max(dp[i-1][1], dp[i-1][2])
     *
     */
    public static int maxProfit(int[] prices) {
        if(null == prices || prices.length < 2) return 0;

        int len = prices.length;
        int[][] dp = new int[len][3];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        dp[0][2] = 0;

        for(int i = 1; i < len; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][2]-prices[i]);
            dp[i][1] = dp[i-1][0] + prices[i];
            dp[i][2] = Math.max(dp[i-1][1], dp[i-1][2]);
        }
        return Math.max(dp[len-1][1], dp[len-1][2]);
    }
}
