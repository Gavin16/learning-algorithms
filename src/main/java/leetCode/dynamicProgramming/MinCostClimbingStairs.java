package leetCode.dynamicProgramming;


import java.util.Arrays;

/**
 * 《746. 使用最小花费爬楼梯》
 *
 * 数组的每个索引做为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。
 *
 * 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
 *
 * 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。
 *
 * 示例 1:
 *
 * 输入: cost = [10, 15, 20]
 * 输出: 15
 * 解释: 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15。
 *  示例 2:
 *
 * 输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * 输出: 6
 * 解释: 最低花费方式是从cost[0]开始，逐个经过那些1，跳过cost[3]，一共花费6。
 *
 * 注意：
 *
 * cost 的长度将会在 [2, 1000]。
 * 每一个 cost[i] 将会是一个Integer类型，范围为 [0, 999]。
 *
 */
public class MinCostClimbingStairs {

    public static void main(String[] args) {
        MinCostClimbingStairs climbingStairs = new MinCostClimbingStairs();
        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        int[] cost1 = {0,0,1,1};
//        System.out.println(climbingStairs.minCostClimbingStairs(cost));
        System.out.println(climbingStairs.minCostClimbingStairs1(cost1));
    }


    /**
     * 循环方式解法
     * 状态转移方程为 Min(n) = Math.min(Min(n-1) + cost[n-1] , Min(n-2) + cost[n-2]);
     * 其中 Min(n) 代表到达第n阶台阶需要花费的体力
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] minCost = new int[cost.length + 1];

        minCost[0] = 0;
        minCost[1] = 0;
        for(int i = 2 ; i <= cost.length ; i++){
            minCost[i] = Math.min(minCost[i-1] + cost[i-1],minCost[i-2] + cost[i-2]);
        }
        return minCost[cost.length];
    }

    /**
     * Min(n) = Math.min(Min(n-1) + cost[n-1] , Min(n-2) + cost[n-2]);
     *
     * 其中 Min(n) 代表到达第n阶台阶需要花费的体力
     *
     *
     * 0,0,1,1
     */
    public int minCostClimbingStairs1(int[] cost) {
        memo = new int[cost.length + 1];
        Arrays.fill(memo,-1);
        return dfsFindMinCost(cost,cost.length);
    }

    private int[] memo;

    private int dfsFindMinCost(int[] cost,int n){
        if(n == 0 || n == 1) return 0;

        if(memo[n] >= 0) return memo[n];

        int oneStep = dfsFindMinCost(cost,n-1);
        int twoStep  = dfsFindMinCost(cost,n-2);

        memo[n] =  Math.min(oneStep + cost[n-1],twoStep + cost[n-2]);
        return memo[n];
    }
}

