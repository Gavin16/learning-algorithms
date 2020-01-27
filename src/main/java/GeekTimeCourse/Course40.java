package GeekTimeCourse;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 *
 */
public class Course40 {


    public static void main(String[] args) {
        int[] nums = {2,2,4,6,3};
        int w = 9;
        System.out.println(oneZeroPackage(nums,w));
        System.out.println("递归调用次数：" + rCnt);

        int[]nums1 = {2,2,4,6,3};
        int[]val = {3,4,8,9,6};
        int n = nums1.length;
        int tw = 9;
        System.out.println(oneZeroPackageValue(nums,val,n,tw));
    }

    public static int oneZeroPackage(int[] nums,int w){
        // (1) 回溯解法
//        int n = nums.length;
//        repeated = new boolean[n][w];
//        trackingBack(nums,w,n,0,0);
//        return maxW;

        // (2) 动态归回解法
//        return dp(nums,nums.length,w);

        // (3) 动态规划优化解法
        return dp2(nums,nums.length,w);

    }

    private static int maxW = Integer.MIN_VALUE;
    private static boolean[][] repeated;

    // 统计回溯算法递归调用次数，比较枝减和不枝减的效率
    private static int rCnt = 0;

    /**
     * 0 - 1 背包问题回溯解法
     *
     * 通过枝减可避免重复计算的问题，提高了回溯算法的效率
     *
     * @param weight
     * @param w
     * @param n
     * @param i
     * @param cw
     */
    public static void trackingBack(int[] weight, int w ,int n , int i,int cw){
        // 统计调用次数
        rCnt ++;
        if(i == n || cw == w){
            if(cw >= maxW){
                maxW = cw;
            }
            return;
        }

        // 枝减去重
        if(repeated[i][cw]){
            return;
        }

        repeated[i][cw] = true;
        // 不放进背包
        trackingBack(weight, w, n, i+1, cw);
        if(cw + weight[i] <= w){
            // 放进背包
            trackingBack(weight, w, n, i+1, cw+weight[i]);
        }
    }

    /**
     * 动态归回解法： 相当于用空间换时间，重点理解动态规划的状态转移
     *
     * 状态转移方程：
     * 使用二维数组保存每个物品放与不放如背包，对应背包的重量的最大值： 若将第i件物品放入背包，则背包对应能达到的最大值上做一个标记
     * 若不将第i件物品放入背包，则在对第i-1件物品处理时能达到的背包的最大值就是当前的最大值、
     *              w
     *              0   1   2   3   4   5   6   7    8    9
     *         i 0        true
     *           1
     *           2
     *           3
     *           4
     * @param weight
     * @param n
     * @param w
     * @return
     */
    public static int dp(int[]weight,int n, int w){
        boolean[][] state = new boolean[n][w+1];

        // 第一行数据单独处理
        state[0][0] = true;
        if(weight[0] <= w){
            state[0][weight[0]] = true;
        }

        for(int i = 1 ; i < n ;i ++){
            // 第i件物品不加入背包
            for (int j = 0 ; j <= w ; j++){
                if(state[i-1][j]) state[i][j] = state[i-1][j];
            }
            // 将第i件物品加入背包
            for(int k = 0 ; k <= w - weight[i] ; k++){
                if(state[i-1][k]) state[i][k+weight[i]] = true;
            }
        }

        // 找出背包能装入的最大重量
        for(int i = w ; i >=0 ; i--){
            if(state[n-1][i]) return i;
        }
        return 0;
    }


    /**
     *
     * 使用一维数组保存背包中的状态
     *
     * @param weight
     * @param n
     * @param w
     * @return
     */
    public static int dp2(int[]weight,int n, int w){
        boolean[] state = new boolean[w+1];
        state[0] = true;
        if(weight[0] <= w){
            state[weight[0]] = true;
        }

        for(int i = 1 ; i < n ; i++){
            // 从后到前的保存能达到的最大重量
            for(int j = w - weight[i]; j >= 0 ; j--){
                if(state[j]) state[j+weight[i]] = true;
            }
        }

        for(int k = w ; k >=0 ; k--){
            if(state[k]) return k;
        }
        return 0;
    }



    /**
     * 1-0背包升级问题: 背包装入最大重量改为最大价值
     */
    public static int oneZeroPackageValue(int[]nums,int[]itemVals, int n,int w){
        vals = new int[n][w+1];
        for(int i = 0 ; i < vals.length ; i++){
            Arrays.fill(vals[i],-1);
        }
        vals[0][0] = 0;
        trackingBack2(nums,itemVals,nums.length,w,0,0,0);
        return maxVal;
    }


    /**
     *  1-0背包升级问题: 背包装入最大重量改为最大价值
     *
     *  为避免对 i,cw,v 中 i,cw 相同的情况下v较小的重复调用，使用vals二维数组记录 i,cw 坐标中已经出现过的较大值
     *  以此枝减i,cw 相同时 v较小的值
     *
     *
     *  回溯解法
     *
     */
    // 回溯算法递归去重
    private static int[][] vals;
    private static int maxVal;
    public static void trackingBack2(int[]weight,int[] itemVals ,int n,int w,int i , int cw,int v){
        if(i == n || cw == w){
            if(v > maxVal) maxVal = v;
            return ;
        }

        if(v < vals[i][cw]) return;
        vals[i][cw] = v;
        trackingBack2(weight,itemVals, n, w, i+1, cw, v);
        if(cw + weight[i] <= w){
            trackingBack2(weight,itemVals, n, w, i+1, cw+weight[i], v+itemVals[i]);
        }
    }

    /**
     * 1-0背包升级问题: 背包装入最大重量改为最大价值
     *
     * @param weight
     * @param n
     * @param w
     * @return
     */
    public static int dp3(int[]weight,int[] values ,int n, int w){
        int[][] states = new int[n][w+1];
        for(int i = 0 ; i < n ; i++){
            Arrays.fill(states[i],-1);
        }
        // 初始化第一个物品的价值
        states[0][0] = 0;
        if(weight[0] <= w){
            states[0][weight[0]] = values[0];
        }

        // 动态规划实现
        for(int i = 1; i < n; i++){
            // 不选择第i个物品
            for(int j = 0 ; j < w; j++){
                // 若不选择该物品,则总价值保持和第 i-1 件物品选择后的结果一样
                if(states[i-1][j] >= 0) states[i][j] = states[i-1][j];
            }
            // 选择第i个物品; k <= w-weight[i] 是为了防止重量超过范围
            for(int k = 0 ; k <= w - weight[i]; k++){
                if(states[i-1][k] >= 0) {
                    int val = states[i-1][k] + values[i];
                    // 选择到相同物品 i 时， 如果重量为 cw ,则判断当前重量下得到的价值是否更大,保存更大的价值
                    // 这样随着物品逐个的往后选择, 满足以下规律
                    // (1) 当前物品i 能放入背包，则更新state[i] 这一行的数据
                    // (2) 当前物品i 不能放入则不走该for循环，实际就相当于走上一层for循环即不选择该物品
                    if(val > states[i][weight[i] + k]){
                        states[i][k+weight[i]] = val;
                    }
                }
            }
        }
        int maxvalue = -1;
        for(int i = w ; i >= 0 ; i--){
            if(states[n-1][i] > maxvalue) maxvalue = states[n-1][i];
        }
        return maxvalue;
    }



    /**
     *   n个商品价格凑单满足 商品总金额不低于200,
     *
     *  （TODO）
     *
     */
    public static void goodsCombination(int[] items,int n , int w){

    }


}
