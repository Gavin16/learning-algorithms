package leetcode.oneMoreHundred;

/**
 * ==》从暴力递归到动态规划《==
 * 暴力递归抽象出来的优化过程  就是动态规划(暴力递归不一定都能改为动态规划,但是)
 *
 *
 * ==============================================================================
 *  01背包问题
 *  剑指 Offer 46. 把数字翻译成字符串
 *  博弈拿牌问题
 *  N皇后问题
 *
 * ==============================================================================
 *
 *
 *
 */
public class Day025 {


    public static void main(String[] args) {

        int[] nums1 = {3,7,5,10,8};
        System.out.println("---------Card In Line---------");
        System.out.println(maxCardSum(nums1));
        System.out.println(maxCardSum1(nums1));

        System.out.println("--------N Queen Problem------");
        System.out.println(NQueenProblem(8));


        System.out.println("----------max bag value-------");
        int[] weights = {3,2,4,7};
        int[] values = {5,6,3,19};
        int wLimit = 11;
        System.out.println(maxBagValue(weights,values,wLimit));
        System.out.println(maxBagValue2(weights,values,wLimit));
    }


    /**
     * 01背包问题:
     * 暴力递归解法
     *
     * @param w  背包中各个商品的重量
     * @param v  背包中各个商品的价值
     * @param wLimit 背包能承受商品的最大重量
     * @return
     */
    public static int maxBagValue(int[]w , int[]v , int wLimit){
        return bagDfs(w,v,0,wLimit);
    }


    /**
     * bagDfs(w,v,curr,rest) 代表在 curr位置下 继续往后走，选或者不选能得到的最大价值
     *
     * @param w
     * @param v
     * @param curr
     * @param rest 背包剩余能承受重量
     * @return
     */
    private static int bagDfs(int[]w ,int[]v, int curr ,int rest){
        if(rest < 0){
            // 标识当前商品选择组合不合规
            return -1;
        }
        // 在还有剩余空间的情况下遍历完所有的商品
        if(curr == w.length){
            return 0;
        }
        int c1 = -1;
        int c2 = bagDfs(w,v,curr+1,rest);
        int c1Next = bagDfs(w,v,curr+1,rest-w[curr]);
        if(c1Next != -1){
            c1 = v[curr] + c1Next;
        }
        return Math.max(c1,c2);
    }



    /**
     * 01背包暴力递归过程 改为 动态规划
     * 改造方法:
     * (1) 递归的终止条件对应为动态规划的DP数组的初始值
     * (2) 递归的嵌套调用逻辑对应动态规划的状态转移方程
     * (3) 递归的初始调用值 对应动态规划的返回值
     *
     *  定义二维数组dp[N+1][M+1]  其中N代表背包中商品的总数量(从0开始,递归中结束条件用到N), M代表背包最大的承重
     *  由暴力递归写法可知:
     *      1) dp[N][..] 数组中的所有元素为0
     *      2) 同时dp[..][..,-1] 中所有元素为 -1; 没有实际意义
     *      3) 二维数组 dp[][] 的初始化值均为 0
     *
     *
     *
     * @param w
     * @param v
     * @param wLimit
     * @return
     */
    public static int maxBagValue2(int[]w, int[]v ,int wLimit){
        int[][]dp = new int[w.length+1][wLimit+1];
        for(int i = w.length-1 ; i >= 0 ; i--){
            for(int j = 0; j <= wLimit ; j++){
                int c1 = -1;
                int c2 = dp[i+1][j];
                if(j - w[i] >= 0){
                    c1 = v[i] + dp[i+1][j-w[i]];
                }
                dp[i][j] = Math.max(c1,c2);
            }
        }
        return dp[0][wLimit];
    }




    /**
     * ================= 博弈拿牌问题  ======================
     *
     * 题设: 有一组写有正整数的卡片，现有两个人一前一后交替的从这组卡片的左边或者卡片的右边取一张，取的卡片上数值总和最大的一方获胜。
     *       假设两人都是绝对聪明的，求获胜方取到的数值总和。
     *
     * @param nums
     * @return
     */
    public static int maxCardSum(int[] nums){
        int first = first(nums,0,nums.length-1);
        int second = second(nums,0,nums.length-1);

        return Math.max(first,second);
    }

    /**
     * 首先明确 first , second 方法对应传参处理的是什么样的状况:
     * first(nums, left , right) -- 对于先手而言，在左右边界分别为 left 和 right 时对应的取到的值
     * second(nums,left,right)  -- 对于后手而言，在左右边界分别是 left 和 right 时能取到的值
     *
     *
     * 先手取数时, 只考虑左右位置当前情况来看能取到的最大值，每次都选能取到的最大值，同时
     * 而能取到的值可以分两部分 当前值 和 后面后手选完之后先手继续选择的值
     * 当前值很好理解， 对于 后面后手选完后留下来的值 -- 如何理解？
     *
     * @param nums
     * @param left
     * @param right
     * @return
     */
    private static int first(int[]nums,int left , int right){
        if(left == right){
            return nums[left];
        }
        int c1 = nums[left] + second(nums,left+1,right);
        int c2 = nums[right] + second(nums,left,right-1);
        return Math.max(c1,c2);
    }

    /**
     * second 方法可以理解为先手留给后手的是最小值, 但是
     * 若传参写成  second(nums,0,len-1) 则可视为后手能取到的 对于先手来说是最大值的情况下 后手的剩余值
     * 这里的剩余值就是 牌的总和 - 先手取到的总和 ， 由于获胜方依赖于牌原来的顺序，因此即使先手留给后手的是他取完最大值后的剩余值，由于取牌规则，这个剩余值仍然可能更大
     *
     *
     * @param nums
     * @param left
     * @param right
     * @return
     */
    private static int second(int[]nums,int left , int right){
        if(left == right){
            return 0;
        }
        int c1 = first(nums,left+1, right);
        int c2 = first(nums,left, right-1);
        return Math.min(c1,c2);
    }


    /**
     * 博弈拿牌问题动态规划改造
     * @param nums
     */
    public static int maxCardSum1(int[] nums){
        int[][]first = new int[nums.length][nums.length];
        int[][]second = new int[nums.length][nums.length];

        for(int i = 0 ; i < nums.length ; i++){
            first[i][i] = nums[i];
        }

        // 先手DP数据结果
        for(int j = nums.length - 1 ; j >= 0 ; j--){
            for(int k = j ; k < nums.length ; k++){
                if(j != k){
                    int c1 = nums[j] + second[j+1][k];
                    int c2 = nums[k] + second[j][k-1];
                    first[j][k] = Math.max(c1,c2);

                    int p1 = first[j+1][k];
                    int p2 = first[j][k-1];
                    second[j][k] = Math.min(p1,p2);
                }
            }
        }
        return Math.max(first[0][nums.length-1],second[0][nums.length-1]);

    }


    /**
     *
     * ============= N皇后问题 ==================
     *
     *
     * N*N 棋盘上放置 N个皇后，要求每个皇后与其他皇后不在同一行，不在同一列且不在同一对角线上
     *
     * 暴力递归解法：经典且复杂度最优解法
     *
     * @param N : 棋盘的大小(N*N)
     * @return
     */
    public static int NQueenProblem(int N){
        int[] res = new int[N];
        return dfs(0,res,N);
    }

    /**
     *
     * @param curr 当前行数，对应数组下标
     * @param res  保存不同可能结果的数组
     * @param N    棋盘大小，数据规模
     * @return
     */
    private static int dfs(int curr ,int[]res , int N){
        if(curr == N){
            return 1;
        }
        int cnt = 0;
        for(int k = 0 ; k < res.length ; k++){
            if(isPosOk(res,curr,k)){
                res[curr] = k;
                cnt += dfs(curr+1,res,N);
            }
        }
        return cnt;
    }

    private static boolean isPosOk(int[] res, int curr, int k) {
        for(int i = 0 ; i < curr ; i++){
            // Math.abs(i - curr) == Math.abs(res[i] - k) 等价于curr在之前行的queen 的对角线上
            if(res[i] == k || Math.abs(i - curr) == Math.abs(res[i] - k)){
                return false;
            }
        }
        return true;
    }



}
