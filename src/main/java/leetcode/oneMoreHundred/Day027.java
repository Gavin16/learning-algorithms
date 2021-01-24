package leetcode.oneMoreHundred;

/**
 *  股票问题
 * ==============================================================================
 * 《121. 买卖股票的最佳时机》
 *
 * 给定一个数组，它的第i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意：你不能在买入股票前卖出股票。
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 *
 *
 * ==============================================================================
 * 《122. 买卖股票的最佳时机 II》
 *
 * 给定一个数组，它的第i 个元素是一支给定股票第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 *
 * ==============================================================================
 * 《123. 买卖股票的最佳时机 III》
 *
 *
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成两笔交易。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）
 *
 * 示例1:
 *
 * 输入：prices = [3,3,5,0,0,3,1,4]
 * 输出：6
 * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 * 随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 *
 * ==============================================================================
 * 《188. 买卖股票的最佳时机 IV》
 *
 * 给定一个整数数组prices ，它的第 i 个元素prices[i] 是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）
 *
 *
 * 示例 1：
 *
 * 输入：k = 2, prices = [2,4,1]
 * 输出：2
 * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 *
 *
 * ==============================================================================
 */
public class Day027 {


    public static void main(String[] args) {
        int[] nums01 = {7,1,5,3,6,4};
        int[] nums02 = {7,1,5,3,6,4};

        Day027 day027 = new Day027();
        System.out.println(day027.maxProfit1(nums01));

        System.out.println("===================");
        System.out.println(day027.maxProfit2(nums02));
    }


    /**
     * 121. 买卖股票的最佳时机
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {
        if(null == prices || prices.length <= 1) return 0;
        int minVal = prices[0];

        int maxProfit = Integer.MIN_VALUE;

        for(int k = 1 ; k < prices.length ; k++){
            int currProfit = prices[k] - minVal;
            maxProfit = currProfit > maxProfit ?  currProfit : maxProfit;
            minVal = prices[k] < minVal ? prices[k] : minVal;
        }
        return maxProfit;
    }


    /**
     * 122. 买卖股票的最佳时机 II
     * 直接DP实现
     * 找出范围之间的关系
     * 设DP[i] 代表prices数组中从0 到 i 的范围内能获取到的最大利润
     * 比较容易的有: dp[i] = max(dp[i-1] , dp[i-2] + prices[i] - prices[i-1]);
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        if(null == prices || prices.length < 2) return 0;
        int N = prices.length;
        int[] dp = new int[N];
        dp[0] = 0 ;
        dp[1] = Math.max(dp[0],prices[1] - prices[0]);
        for(int k = 2 ; k < N ; k++){
            int priceGap = prices[k] - prices[k-1];
            dp[k] = Math.max(dp[k-1] , dp[k-2] + priceGap);
        }
        return dp[N-1];
    }


    /**
     * 暴力递归实现
     * 调用方式 dfsMaxProfits(prices,N-1)
     * @param prices
     * @param idx
     * @return
     */
    private int dfsMaxProfits(int[] prices , int idx){
        if(idx == 0){
            return 0;
        }
        if(idx == 1){
            return prices[1] > prices[0] ? prices[1] - prices[0] : 0;
        }
        return Math.max(dfsMaxProfits(prices,idx-1), dfsMaxProfits(prices,idx-2) + (prices[idx] - prices[idx-1]));
    }


    /**
     * 123. 买卖股票的最佳时机 III
     *
     *
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit3(int k, int[] prices) {
        return -1;
    }


    public int maxProfit4(int k, int[] prices) {
        return -1;
    }
}
