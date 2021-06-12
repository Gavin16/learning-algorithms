package leetcode.oneMoreHundred;

/**
 * 877 石子游戏
 * 亚历克斯和李用几堆石子在做游戏。偶数堆石子排成一行，每堆都有正整数颗石子 piles[i] 。
 * 游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局。
 * 亚历克斯和李轮流进行，亚历克斯先开始。 每回合，玩家从行的开始或结束处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此时手中石子最多的玩家获胜。
 * 假设亚历克斯和李都发挥出最佳水平，当亚历克斯赢得比赛时返回 true ，当李赢得比赛时返回 false 。
 *
 *
 * 示例：
 *
 * 输入：[5,3,4,5]
 * 输出：true
 * 解释：
 * 亚历克斯先开始，只能拿前 5 颗或后 5 颗石子 。
 * 假设他取了前 5 颗，这一行就变成了 [3,4,5] 。
 * 如果李拿走前 3 颗，那么剩下的是 [4,5]，亚历克斯拿走后 5 颗赢得 10 分。
 * 如果李拿走后 5 颗，那么剩下的是 [3,4]，亚历克斯拿走后 4 颗赢得 9 分。
 * 这表明，取前 5 颗石子对亚历克斯来说是一个胜利的举动，所以我们返回 true 。
 *  
 *
 * 提示：
 *
 * 2 <= piles.length <= 500
 * piles.length 是偶数。
 * 1 <= piles[i] <= 500
 * sum(piles) 是奇数。
 *
 *
 * 1140. 石子游戏 II
 * 亚历克斯和李继续他们的石子游戏。许多堆石子 排成一行，每堆都有正整数颗石子 piles[i]。游戏以谁手中的石子最多来决出胜负。
 * 亚历克斯和李轮流进行，亚历克斯先开始。最初，M = 1。
 * 在每个玩家的回合中，该玩家可以拿走剩下的 前 X 堆的所有石子，其中 1 <= X <= 2M。然后，令 M = max(M, X)。
 * 游戏一直持续到所有石子都被拿走。
 * 假设亚历克斯和李都发挥出最佳水平，返回亚历克斯可以得到的最大数量的石头。
 *
 * 示例：
 *
 * 输入：piles = [2,7,9,4,4]
 * 输出：10
 * 解释：
 * 如果亚历克斯在开始时拿走一堆石子，李拿走两堆，接着亚历克斯也拿走两堆。在这种情况下，亚历克斯可以拿到 2 + 4 + 4 = 10 颗石子。
 * 如果亚历克斯在开始时拿走两堆石子，那么李就可以拿走剩下全部三堆石子。在这种情况下，亚历克斯可以拿到 2 + 7 = 9 颗石子。
 * 所以我们返回更大的 10。
 *
 * 提示：
 *
 * 1 <= piles.length <= 100
 * 1 <= piles[i] <= 10 ^ 4
 *
 */
public class Day041 {

    public static void main(String[] args) {
        int[] piles = {5,3,4,5,9,6};
        int[] nums = {1,5,2};
        System.out.println(stoneGame(piles));
        System.out.println(stoneGame1(nums));
    }

    public static boolean stoneGame(int[] piles) {
        int first = firstHandler(piles, 0, piles.length - 1);
        int second = secondHandler(piles, 0, piles.length - 1);

        return first > second ? true : false;
    }


    /**
     * 动态规划解法
     * dp[][]   i 代表左下标, j 代表右下标
     */
    public static boolean stoneGame1(int[] piles){
        int LEN = piles.length;
        // dp1 和 dp2 分别代表先手和后手状态转移矩阵
        // dp1[0][LEN-1] 和 dp2[0][LEN-1] 即为所求
        int[][] dp1 = new int[LEN][LEN];
        int[][] dp2 = new int[LEN][LEN];
        for(int i = 0 ; i < LEN ; i++){
            dp1[i][i] = piles[i];
        }
        // 只考虑 t > s 的情况
        for(int s = 1 ; s < LEN ; s++){
            for(int r = 0, c = s ; c < LEN ; r++,c++){
                dp1[r][c] = Math.max(piles[r] + dp2[r+1][c] , piles[c] + dp2[r][c-1]);
                dp2[r][c] = Math.min(dp1[r+1][c] ,dp1[r][c-1]);
            }
        }
        return dp1[0][LEN-1] > dp2[0][LEN-1] ? true : false;
    }

    /**
     * 先手能拿到的石子个数
     */
    public static int firstHandler(int[] piles , int s , int t){
        if(s == t){
            return piles[s];
        }
        int left = piles[s] + secondHandler(piles,s+1,t);
        int right = piles[t] + secondHandler(piles,s,t-1);
        return Math.max(left,right);
    }

    /**
     * 后手能拿到的石子个数
     */
    public static int secondHandler(int[] piles , int s ,int t){
        if(s == t){
            return 0;
        }
        int res = Math.min(firstHandler(piles,s+1,t),firstHandler(piles,s,t-1));
        return res;
    }
}
