package leetcode.backtrackingAlgorithm;


import java.util.*;

/**
 * 《322. 零钱兑换》
 * 《GeekTime -- practice day07》
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 示例 1:
 *
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 *
 * 输入: coins = [2], amount = 3
 * 输出: -1
 *
 */
public class CoinChange {




    public static void main(String[] args) {
        int[] coins1 = {1, 2, 5};
        int[] coins2 = {2};

//        System.out.println(coinChange(coins1,11));
        System.out.println(coinChange(coins2,3));
    }

    private static int[] memo;

    private static int min = Integer.MAX_VALUE;

    /**
     *  采用动态规划求解
     *  转移方程：MinMatchCoins(coins[0:n-1]) = 0/1/…/n  + MinMatchCoins(coins[1:n-1])
     *  使用备忘录保存结果
     */
    public static int coinChange(int[] coins, int amount) {
        memo = new int[amount+1];
        Arrays.fill(memo,-1);
        memo[0] = 0;
        // 迭代解法
//        return iterCoinChange(coins, amount);
//        return rCoinChange(coins, amount);

        // 深度优先搜索解法
        Arrays.sort(coins);
        reverseArray(coins);
        dfsCoinChange(coins,0,0,amount);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private static void reverseArray(int[] coins) {
        int i = 0 , j = coins.length - 1;
        while(i < j){
            int temp = coins[i];
            coins[i] = coins[j];
            coins[j] = temp;
            i++;j--;
        }
    }


    /**
     *  自底向上动态规划 迭代实现
     *  状态转移方程: dp[i] = min(dp[i],1+dp[i-n])  其中n为硬币 的金额
     *  遍历所有硬币，可得到 dp 的最小值
     *
     *  （1）设置数组dp 初始值为大于所有可能硬币数的一个值，这里取 amount+1
     *  （2）设置起始值为0，也即总金额为0时，对应可选的硬币数为0
     */
    private static int iterCoinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp,amount+1);
        dp[0] = 0;
        for(int i = 0 ;i < dp.length ; i++){
            // 这里每次都遍历所有硬币是因为硬币可以重复使用
            for(int n : coins){
                if(i < n) continue;
                dp[i] = Math.min(dp[i] , 1 + dp[i-n]);
            }
        }
        return dp[amount] == amount+1 ? -1 : dp[amount];
    }


    /**
     *  递归实现
     */
    private static int rCoinChange(int[] coins, int amount){
        if(amount == 0) return 0;
        else if(amount < 0) return -1;

        if(memo[amount] > -1) return memo[amount];

        int min = Integer.MAX_VALUE;
        for(int n : coins){
            if(n > amount) continue;
            int sub = rCoinChange(coins, amount - n);
            if(sub > -1) min = Math.min(min,sub);
        }

        memo[amount] = (min == Integer.MAX_VALUE) ? -1 : min + 1;
        return memo[amount];
    }


    /**
     *
     * 前提条件是数组 coins 已经做过倒序排序
     * 深度优先遍历实现
     *
     * 执行出现超时情况，枝减掉事先判断出来的硬币数大于当前最小硬币数的情况
     *
     */
    private static void dfsCoinChange(int[] coins , int id , int used , int amount){
        if(id == coins.length) return;

        int repUse = amount / coins[id];
        for(int i = repUse ; i >= 0 ; i --){
            int rest = amount - i * coins[id] ;
            // 枝减掉已经大于现有最小硬币数的情况
            if(rest > 0 && used + i + 1 < min){
                dfsCoinChange(coins,id+1, used + i, rest);
            }else{
                int tUsed = used + i ;
                if(rest == 0 && tUsed < min) min = tUsed;
                break;
            }
        }
    }


//    private int min = Integer.MAX_VALUE;
//
//    public int coinChange(int[] coins, int amount) {
//        Arrays.sort(coins); // asc
//        dfs(coins, coins.length - 1, amount, 0);
//        return min == Integer.MAX_VALUE ? -1 : min;
//    }
//
//    private void dfs(int[] coins, int ci, int rest, int cnt) {
//        if (ci < 0) return;
//        for (int i = rest / coins[ci]; i >= 0; i--) {
//            int currRest = rest - i * coins[ci], currCnt = cnt + i;
//            if (currRest > 0 && currCnt + 1 < min)
//                dfs(coins, ci - 1, currRest, currCnt);
//            else {
//                if (currRest == 0 && currCnt < min) min = currCnt;
//                break;
//            }
//        }
//    }
}
