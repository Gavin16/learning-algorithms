package leetcode.oneMoreHundred;


import java.util.PriorityQueue;

/**
 * 1049. 最后一块石头的重量 II
 * 有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
 *
 * 示例 1：
 *
 * 输入：stones = [2,7,4,1,8,1]
 * 输出：1
 * 解释：
 * 组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
 * 组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
 * 组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
 * 组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
 * 示例 2：
 *
 * 输入：stones = [31,26,33,21,40]
 * 输出：5
 * 示例 3：
 *
 * 输入：stones = [1,2]
 * 输出：1
 *
 * 提示：
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 100
 *
 *
 *
 * 1046. 最后一块石头的重量
 *
 * 有一堆石头，每块石头的重量都是正整数。
 * 每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
 *  
 * 示例：
 *
 * 输入：[2,7,4,1,8,1]
 * 输出：1
 * 解释：
 * 先选出 7 和 8，得到 1，所以数组转换为 [2,4,1,1,1]，
 * 再选出 2 和 4，得到 2，所以数组转换为 [2,1,1,1]，
 * 接着是 2 和 1，得到 1，所以数组转换为 [1,1,1]，
 * 最后选出 1 和 1，得到 0，最终数组转换为 [1]，这就是最后剩下那块石头的重量。
 *  
 * 提示：
 *
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 1000
 *
 */
public class Day042 {

    private static int min = Integer.MAX_VALUE;


    public static void main(String[] args) {
        int[] stones1 = {2,7,4,1,8,1};
        int[] stones2 = {1,2};
        int[] stones3 = {31,26,33,21,40};

//        System.out.println(lastStoneWeightII(stones1));
//        System.out.println(lastStoneWeightII(stones2));
//        System.out.println(lastStoneWeightII(stones3));

        System.out.println("== lastStoneWeight ==");
        System.out.println(lastStoneWeight(stones1));
    }

    /**
     * 动态规划解法
     * 求出所有石头的重量sum , 题目可转化为从 stones 中挑选出一批石头，使得这批石头在总重量不超过 sum/2 时取得最大值
     * 定义二维布尔数组dp[n+1][m+1], 其中 n代表 石头的个数, m = sum/2
     *
     * dp[i][j] 代表是否从前i个石头中，挑选出能凑出重量为j 的总和, 若能则为 true 不能则为false
     *
     * 对于dp[0][0] 代表不挑选的情况下凑出总量为0 的总和，这个可以设置为true --> dp[0][0] = true
     *
     * 对于第i块石头 stones[i] 有
     * dp[i+1][j] = dp[i][j]    若 stones[i] > j ; 这里的意思是，如果当前石头重量比要凑的总和还大，那么该石头就不能挑选，因此能否凑出总量j与dp[i][j] 是一样的
     * dp[i+1][j] = dp[i][j] || dp[i][j-stones[i]]  若 j > stones[j]; 若j > stones[i] 说明该石头可以挑选，也可以不选，不选时同上;
     *                                                                挑选时若dp[i][j-stones[i]]为true就为true, 若 dp[i][j-stones[i]] 为false就为false
     *
     */
    public static int lastStoneWeightII(int[] stones) {
        int n = stones.length,sum = 0;
        for(int w : stones){
            sum += w;
        }
        int m = sum/2;
        boolean[][] dp = new boolean[n+1][m+1];
        dp[0][0] = true;

        for(int i = 0 ; i < n ; i++ ){
            for(int j = 0 ; j <= m ; j++){
                if(j < stones[i]){
                    dp[i+1][j] = dp[i][j];
                }else{
                    dp[i+1][j] = dp[i][j] || dp[i][j-stones[i]];
                }
            }
        }
        int res = sum;
        for(int k = m; k >= 0 ; k--){
            if(dp[n][k]){
                res = sum - 2*k;
                break;
            }
        }
        return res;
    }



    public static int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);

        for(int num : stones){
            queue.add(num);
        }

        while(queue.size() > 1){
            Integer poll1 = queue.poll();
            Integer poll2 = queue.poll();
            int rem = poll1 - poll2;
            if(rem > 0){
                queue.add(rem);
            }
        }

        return queue.size() == 1 ? queue.remove() : 0;
    }
}
