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
        int[] nums03 = {3,3,5,0,0,3,1,4};
        int[] nums04 = {3,2,6,5,0,3};
        int[] nums041 = {2,4,1};

        Day027 day027 = new Day027();
        System.out.println(day027.maxProfit1(nums01));

        System.out.println("===================");
        System.out.println(day027.maxProfit2(nums02));
        System.out.println("===================");

        System.out.println(day027.maxProfit3(nums03));


        System.out.println("=======================");
        System.out.println(day027.maxProfit4(2,nums04));
        System.out.println(day027.maxProfit4(2,nums041));
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
     * 由于股票只能买两次，因此对于股票的买卖状态，仅以下5种
     * (0) 还未买过股票
     * (1) 第一次买了股票
     * (2) 第一次买的股票卖了
     * (3) 第二次卖了股票
     * (4) 第二次买的股票卖了
     *
     * 定义二维数组 dp[N][5]
     * 其中N代表天数，5 对应当前买入的状态数，dp[i][j] 对应在第i天第j中状态下的余额
     *
     * 针对第i天的  0 ，1，2，3，4 五种状态下分别的有
     * 状态0：代表第i天一次股票都没买的状态，显然的 有dp[i][0] = dp[i-1][0] , 并且dp[i][0] = 0;
     * 状态1：代表第i天进入了买了第一次股票的状态，那么这个一次可以是前一天就已经买了的，也可以是第i天当天买的，因而有：
     *    dp[i][1] = dp[i-1][1]  或者 dp[i-1][0] - prices[i], 究竟是第i 天买还是在前一天就进入买入状态，
     *    这里可以通过贪心算法来获取全局最优解，因此 dp[i][1] = max(dp[i-1][1],dp[i-1][0] - prices[i])
     * 对于状态2：第i天进入第一次卖出状态, 那么也存在两种可能
     *    前一天就已经卖出了： dp[i][2] = dp[i-1][2]
     *    当天才卖出： dp[i][2] = dp[i-1][1] + prices[i]
     *    类似的，dp[i][2] 取 max(dp[i-1][2],dp[i-1][1] + prices[i])
     *
     * 对于状态3, 4 同1，2 类似，只不过对应的是第二次的买卖，因而有：
     *    dp[i][3] = max(dp[i-1][3], dp[i-1][2] - prices[i])
     *    dp[i][4] = max(dp[i-1][4],dp[i-1][3] + prices[i])
     *
     * 以上状态转移方程中每一步在填写 dp[i][j] 的剩余金额时，都是取的对当前时间和状态大的值，这样通过由前到后求解最终得到的会是一个全局的最优解
     *
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        if(null == prices || prices.length < 2) return 0;
        int N = prices.length;
        int[][] dp = new int[N][5];
        dp[0][1] = -1*prices[0];
        dp[0][3] = -1*prices[0];
        for(int i = 1 ; i < N ; i++){
            dp[i][0] = dp[i-1][0];
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0] - prices[i]);
            dp[i][2] = Math.max(dp[i-1][2],dp[i-1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i-1][3],dp[i-1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i-1][4],dp[i-1][3] + prices[i]);
        }
        return Math.max(dp[N-1][2],dp[N-1][4]);
    }


    /**
     * 188. 买卖股票的最佳时机 IV
     *
     * 最多完成K笔交易
     * 类似于 123. 买卖股票的最佳时机 III
     *
     *
     * 0 <= k <= 100
     * 0 <= prices.length <= 1000
     * 0 <= prices[i] <= 1000
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit4(int k, int[] prices) {
        if(k == 0 || prices.length == 0) return 0;
        int N = prices.length;
        int[][] dp = new int[N][2*k+1];
        for(int i = 0 ; i < k ; i++){
            dp[0][2 * i + 1] = -1 * prices[0];
        }

        for(int j = 1 ; j < N ; j++){
            for(int t = 1 ; t < 2 * k + 1 ; t++){
                if(t % 2 == 1){
                    dp[j][t] = Math.max(dp[j-1][t],dp[j-1][t-1] - prices[j]);
                }else{
                    dp[j][t] = Math.max(dp[j-1][t],dp[j-1][t-1] + prices[j]);
                }
            }
        }
        int maxProfit = -1;
        for(int r = 0 ; r < 2 * k + 1 ; r++){
            if(dp[N-1][r] > maxProfit){
                maxProfit = dp[N-1][r];
            }
        }
        return maxProfit;
    }
}
