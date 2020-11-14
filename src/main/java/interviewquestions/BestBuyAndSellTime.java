package interviewquestions;

/**
 * @ClassName: BestBuyAndSellTime
 * @CopyRight: wufangmin
 * @Author: wufangmin
 * @Date: 2019/11/22 9:46
 * @Version: 1.0
 * @Description: 最佳买入卖出时机:对应 LeetCode-
 *
 *  股票一个月内的股价波动情况存数组中,做一次买和一次卖,让买卖的收益最大, 返回最大收益
 *
 *  int[] prices = {3,2,3,2,4,5,9,6,8,7,6,9,10,...};
 *
 */
public class BestBuyAndSellTime {

    public static void main(String[] args) {
        int[] prices = {3,2,3,2,4,5,9,6,8,7,6,9,10,8,7};

        System.out.println(highestEarnings(prices));
    }

    /**
     * 最简单的做法：O(n^2)
     * @param prices
     * @return
     */
    static int highestEarnings(int[] prices){
        int max = 0;
        for(int buy : prices){
            for(int sell:prices){
                if(sell - buy > max){
                    max = sell - buy;
                }
            }
        }
        return max;
    }

    /**
     *  动态规划思想：
     *  前i天的最大收益 = max{前i-1天的最大收益，第i天的价格-前i-1天中的最小价格}
     *
     * @param prices
     * @return
     */
    static int highestEarnings2(int[] prices){
        return -1;
    }


}
