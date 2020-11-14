package leetcode.oneMoreHundred;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Date：2020年7月16日
 * ==============================================================================
 * 121. 买卖股票的最佳时机【股票，动态规划，单调栈】
 * ==============================================================================
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意：你不能在买入股票前卖出股票。
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 *
 * ==============================================================================
 * 122. 买卖股票的最佳时机 II 【贪心算法,动态规划】
 * ==============================================================================
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
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
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 *
 * 提示：
 * 1 <= prices.length <= 3 * 10 ^ 4
 * 0 <= prices[i] <= 10 ^ 4
 *
 * 示例 2:
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 *
 */
public class Day008 {

    public static void main(String[] args) {

        int[] arr = {7,1,5,3,6,4};
        System.out.println(maxProfit3(arr));

        // 122. 买卖股票的最佳时机 II
        int[] testA = {7,1,5,3,6,4};
        System.out.println(maxProfit01(testA));

    }

    /**
     * @Title: 121. 买卖股票的最佳时机
     * @Version: 版本1 比较所有时刻的价格与历史最低价格的差价获取最大收益
     * 可行原因: 控制变量的做法
     * 要想收益最大 ，在遍历时有两方面的要求： 1 买入价格尽可能低， 2 卖出价格尽可能高
     *
     * 但是本质而言我们要的是 卖出价-买入价  的差价最大，而要使差价最大正常我们需要两两比较找出最大的差价，
     * 这就是暴力破解法，两次循环实现。
     *
     * 我们从前至后的遍历数组元素,同时记录当前碰到的最低价和最大差价, 当一段时间内最低价不变时，卖出时刻的最高价
     * 就决定了差价大小，当最低价变小时，变小前的那一段时间内的最大差价会和变下之后的一段时间内的差价做比较
     * 最终保存最大的差价返回
     *
     * @param prices
     * @return
     */
    public static int maxProfit1(int[] prices) {
        int maxProfit = 0,minPrice = Integer.MAX_VALUE;
        for(int n : prices){
            if(n < minPrice){
                minPrice = n;
            }else if(maxProfit < n - minPrice){
                maxProfit = n - minPrice;
            }
        }
        return maxProfit;
    }

    /**
     * @Title: 121. 买卖股票的最佳时机
     * @Version: 版本2 DP算法实现
     * 状态转移方程：
     * maxProfit(i) = max(maxProfit(i-1) , prices[i] - minPrice)
     *
     * @param prices
     * @return
     */
    public static int maxProfit2(int[] prices) {
        if(prices.length < 2) return 0;

        int minPrice = prices[0];
        int[] maxProfit = new int[prices.length];
        maxProfit[0] = 0;
        for(int i = 1 ; i < prices.length ; i++){
            if(prices[i] < minPrice){
                minPrice = prices[i];
            }
            maxProfit[i] = Math.max(maxProfit[i-1],prices[i]-minPrice);
        }
        return maxProfit[prices.length-1];
    }


    /**
     * @Title: 121. 买卖股票的最佳时机
     * @Version: 版本3 使用单调栈
     * 单调栈使用方式：
     * (1)若添加的元素比栈顶元素大，则直接入栈
     * (2)若添加的元素比栈顶元素小，则一直出栈直到当前判断的元素比栈顶大
     * (3)每次出栈时计算出栈元素与栈底元素的差值，若差值大于原有值则更新原有值
     *
     * 使用单调栈需要在数组末尾添加哨兵,以防单调递增的情况下求得最大收益为 0
     *
     * @param prices
     * @return
     */
    public static int maxProfit3(int[] prices) {
        if(prices.length < 2) return 0;

        Deque<Integer> stack = new LinkedList<>();
        int maxProfit = 0;
        for(int n : prices){
            if(stack.isEmpty() || n > stack.peek()){
                stack.push(n);
            }else if(n < stack.peek()){
                while(!stack.isEmpty() && n < stack.peek()){
                    int pop = stack.pop();
                    if(!stack.isEmpty()){
                        int curr = pop - stack.getLast();
                        maxProfit = maxProfit < curr ? curr : maxProfit;
                    }
                }
                stack.push(n);
            }
        }
        return maxProfit;
    }



    /**
     * @Title: 122. 买卖股票的最佳时机 II
     * @Version: 版本1 动态规划实现【解法未理解】
     * 状态转移方程：
     * int[] cash  代表持有现金
     * int[] stock 代表持有股票
     * (1)cash[i] = max(cash[i-1],stock[i-1] + price[i])
     * (2)stock[i] = max(stock[i-1],cash[i-1] - price[i])
     *
     * @param prices
     * @return
     */
    public static int maxProfit01(int[] prices) {
        if(prices.length < 2) return 0;

        int[]cash =  new int[prices.length];
        int[]stock = new int[prices.length];
        cash[0] = 0 ;
        stock[0] = -prices[0];

        for(int i = 1 ; i < prices.length ; i++){
            cash[i] = Math.max(cash[i-1],stock[i-1] + prices[i]);
            stock[i] = Math.max(stock[i-1],cash[i-1] - prices[i]);
        }
        return cash[prices.length-1];
    }

    /**
     * @Title: 122. 买卖股票的最佳时机 II
     * @Version: 版本2 贪心算法实现
     * 每次买卖都争取获得最大收益,那么在全局环境下也可以获得最大收益
     *
     * @param prices
     * @return
     */
    public static int maxProfit02(int[] prices) {
        if(prices.length < 2) return 0;
        int maxProfit = 0;

        for(int k = 1 ; k < prices.length ; k++){
            if(prices[k] > prices[k-1]){
                maxProfit += prices[k] - prices[k-1];
            }
        }
        return maxProfit;
    }
}
