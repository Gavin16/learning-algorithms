package leetcode.oneMoreHundred;

import java.util.*;


/**
 * 《将暴力递归改动态规划》
 *
 * 暴力递归改动态规划总结
 *
 * 暴力递归  =>  记忆化搜索  =>  动态规划 =>  优化
 *
 * 1.1 设计暴力递归过程的原则：
 *   (1) 每一个可变参数的类型，不要比int类型更复杂
 *   (2) 若违反原则1，让类型突破到一维线性结构，则该参数必须是唯一参数
 *   (3) 若唯一的可变参数是一维线性结构,只需要做到记忆化搜索即可
 *   (4) 可变参数的个数，能少则尽量少
 *
 * 以上原则可以用来作为主线的尝试法..
 *
 *
 * 1.2 常见四种 模型：
 *    (1) 从左往右的尝试模型
 *    (2) 范围上的尝试模型
 *    (3) 多样本位置全对应的尝试模型
 *    (4) 寻找业务限制的尝试模型
 *
 * 1.3 暴力递归到动态规划的套路
 *    （1） 获取到不违反原则的暴力递归的过程时，而递归过程存在解得重复调用
 *     (2)  找到哪些参数变化会影响返回值,对每一个列出变化范围
 *     (3) 参数间的所有组合数量，意味着表的大小
 *     (4) 记忆化搜索的方法就是用简单缓存来存数据
 *     (5) 规定好严格表的大小，分析位置的依赖顺序，然后从基础填写到最终解
 *     (6) 对于有枚举行为的决策过程，需要进一步优化
 *
 *
 * 实践：
 * 以上1,2,3 条 对应的是暴力递归改造动态规划的三条主线，在练习过程中实际应该尝试的顺序是  1->2->3  ,
 * 若在尝试时发现1 选择的模型并不能很好的确定状态关系，可以进一步的返回1 修改递归模型(递归函数的定义)
 *
 *
 * 暴力递归解决问题：
 * 1. 打印所有子序列
 *
 * 2. 打印字符数组全排列
 *
 * 3. 数字转字符串
 *
 * 4. 0-1 背包问题
 *
 * 5. 博弈拿牌问题
 *
 * 6. N皇后问题
 *
 * 7. 机器人走路问题
 *
 * 8. 最小路径和
 *
 * 9. 最大正方形
 *
 * --->暴力递归改动态规划<---:
 *
 * 1. 机器人走路问题
 *
 * 2. 最小路径和
 *
 * 3. 0-1 背包问题
 *
 * 4. 博弈拿牌问题
 */
public class Day032 {


    public static void main(String[] args) {
        System.out.println("--------------打印字符串所有不重复子序列------------");
        Collection<String> ans = printAllSubStrings("aba");
        System.out.println(ans);
        System.out.println("---------------打印数组全排列---------------");
        printAllPermutation(new char[]{'a','b','c','b'});
        System.out.println("---------------facebook原题,数字转字符串 ---------------");
        int allConvertCases = findAllConvertCases(new char[]{'1', '3', '0'});
        System.out.println("all possible cases :" + allConvertCases);
        System.out.println("--------------- 0-1 背包问题 ---------------");
        int[] weights = {3,4,2,6};
        int[] values = {4,2,5,7};
        int weightLimit = 6;
        int maxValue = packageProblem(weights, values, weightLimit);
        System.out.println(maxValue);
        System.out.println("动态规划改造：");



        System.out.println("-----------------博弈拿牌问题-----------------");
        int[] nums = {5,9,3,4};
        int cardLineWinner = findCardLineWinner(nums);
        int winnerSum = cardLineWinnerSum1(nums);
        System.out.println(cardLineWinner);
        System.out.println(winnerSum);

        System.out.println("-----------------N皇后问题-----------------");
        long startTm = System.currentTimeMillis();
        int nqc = NQueenProblem(8);
        System.out.println(nqc);
        System.out.println("time cost : " + (System.currentTimeMillis() - startTm) + "ms");
        System.out.println("------------------机器人走路问题------------------");
        long startTm1 = System.currentTimeMillis();
        System.out.println(robotWalk(20,9,7,10));
        System.out.println("robotWalk time cost:" + (System.currentTimeMillis() - startTm1));
        long startTm2 = System.currentTimeMillis();
        System.out.println(robotWalk2(20,9,7,10));
        System.out.println("robotWalk2 time cost:" + (System.currentTimeMillis() - startTm2));
        System.out.println("--------------------最小路径和------------------");
        int[][] grids1 = {{1,3,1},{1,5,1},{4,2,1}};
        int[][] grids2 = {{1,2,3},{4,5,6}};
        System.out.println(minPathSum(grids2));
        System.out.println(minPathSum1(grids2));
        System.out.println("-----------------最大子数组和----------------");
        int[] arr = {-2,1,-3,4,-1,2,1,-5,4};
        int[] arr2 = {-2,-1};
        System.out.println(maxSubArray(arr));
        System.out.println(maxSubArray(arr2));
    }

    /**
     * 1 找出传入字符串所有子序列
     * @param str
     * @return
     */
    public static Set<String> printAllSubStrings(String str){
        Set<String> res = new HashSet<>();
        if(str.length() == 0) return  res;
        String path = "";
        findAllSubSequence(str,0,res,path);
        return res;
    }

    /**
     * 2 找出输入字符数组的全排列
     * @param arr
     * @return
     */
    public static void printAllPermutation(char[]arr){
        List<String> res = new ArrayList<>();
        if(null == arr || arr.length == 0) return;
        process2(arr,0,res);
        System.out.println(res);
    }


    /**
     * 3 找出所有的转换可能数
     * 规定 1 和 A对应, 2 和 B对应 ， 3 和 C对应 ... 26 和Z 对应
     * 那么一个数字字符串比如 "111" 就可以转换为 "AAA", "AK" 和 "KA"
     *
     * 现在给定任何一个数字输入，求所有转换而成的所有字符串数？
     */
    public static int findAllConvertCases(char[]str){
        int res = dfsCount(str, 0);
        return res;
    }

    /**
     * 深度优先所搜，尝试是否可行,返回：1 - 可行， 0 - 不可行
     */
    public static int dfsCount(char[]str , int i){
        if(i == str.length){
            return 1;
        }
        if(str[i] == '0'){
            return 0;
        }
        if(str[i] == '1'){
            int ans  = dfsCount(str,i+1);
            if(i+1 < str.length){
                ans += dfsCount(str, i+2);
            }
            return ans;
        }
        if(str[i] == '2'){
            int ans = dfsCount(str, i+1);
            if(i+1 < str.length && (str[i+1] >= '0' && str[i+1] <= '6')){
                ans += dfsCount(str,i+2);
            }
            return ans;
        }
        return dfsCount(str,i+1);
    }


    /**
     * 4 背包问题
     * 已知所有商品的重量和商品对应的价值，求能承受最大重量为tw 的背包最多能装多大价值的商品
     */
    public static int packageProblem(int[] weights , int[] values, int tw){
        return dfsBag(weights, values, 0, tw);
    }

    /**
     * 背包问题动态规划改造
     *
     */
    public static int packageProblem1(int[]weights, int[]values , int limit){
        int LEN = weights.length;
        // 行表示商品数量变化范围,列表示剩余商品重量变化范围
        int[][]dp = new int[LEN+1][limit+1];
        // 根据题意,第一行和第一列的数据均需要初始化

        return -1;
    }


    /**
     * 深度优先挑选
     */
    public static int dfsBag(int[]weights, int[]values , int i, int left){
        if(left < 0){
            return -1;
        }
        // 超出商品总数量的范围,递归到该下标时认为商品价值为0
        if(i == weights.length){
            return 0;
        }
        int value = values[i];
        // 不选当前 i 商品
        int v1 = dfsBag(weights,values,i+1,left);
        // 选择当前 i 商品
        int v2 = Integer.MIN_VALUE;
        int nextV2 = dfsBag(weights, values, i + 1, left - weights[i]);
        if(nextV2 != -1){
            v2 = value + nextV2;
        }
        return Math.max(v1,v2);
    }

    /**
     * 5 博弈拿牌问题：
     * 两个人从一堆牌中轮流取牌,规定只能取最左边或者最右边的牌，而不能从中间取
     * 假设两个玩家都是聪明的，问，对于给定的一组牌 nums, 获胜方是先手还是后手
     *
     */
    public static int findCardLineWinner(int[]nums){
        if(null == nums || nums.length == 0) return 0;

        int former = former(nums, 0, nums.length - 1);
        int latter = latter(nums, 0, nums.length - 1);
        return former > latter ? former :  latter;
    }

    /**
     * 作为先手取牌，在让对方当前取牌数值最小的情况下，获取到的牌的大小
     * @return
     */
    public static int former(int[]nums ,int l,int r){
        if(l == r){
            return nums[l];
        }
        int c1 = nums[l] + latter(nums,l+1,r);
        int c2 = nums[r] + latter(nums, l,r-1);
        return Math.max(c1,c2);
    }
    /**
     * 作为后手取牌,实际取牌拿到的牌是对方选择后的
     */
    public static int latter(int[]nums , int l ,int r){
        if(l == r){
            return 0;
        }
        int f1 = former(nums, l + 1, r);
        int f2 = former(nums, l, r - 1);
        return Math.min(f1,f2);
    }


    public static int cardLineWinnerSum1(int[]nums){
        if(null == nums || nums.length == 0) return 0;
        int len = nums.length;
        int[][] former = new int[len][len];
        int[][] latter = new int[len][len];
        for(int i = 0 ; i < len ; i++){
            former[i][i] = nums[i];
            latter[i][i] = 0;
        }
        // 填表former 和 latter
        for(int k = 1 ; k < len ; k++){
            for(int j = k , i = 0; j < len ; i++,j++){
                former[i][j] = Math.max(nums[i] + latter[i+1][j], nums[j] + latter[i][j-1]);
                latter[i][j] = Math.min(former[i+1][j],former[i][j-1]);
            }
        }
        // 最大值在 former 或者 latter 的右上角
        int maxSum = former[0][len-1] > latter[0][len-1] ? former[0][len-1] : latter[0][len-1];
        return maxSum;
    }


    /**
     * 6 皇后问题: 返回所有摆放可能数
     *
     * 由于皇后不能同行，不能同列和同对角线，因此可以使用数据来当前皇后的摆放情况
     *
     *
     */
    public static int NQueenProblem(int n){
        int[] board = new int[n];
        int total = dfsChessBoard(board,0);
        return total;
    }


    private static int dfsChessBoard(int[] board, int i) {
        if(i == board.length){
            return 1;
        }
        int cnt = 0;
        for(int j = 0 ; j < board.length ; j++){
            // 判断能否放置在 i行j列的位置
            if(isPosOk(board,i,j)){
                board[i] = j;
                cnt += dfsChessBoard(board,i+1);
            }
        }
        return cnt;
    }

    /**
     * 判断当前(i,j) 位置能否放置：
     * 要求不能同一列: 即 [0,i-1] 上的元素不能与 j相同
     * 不能在同一对角线上: 即当前(i,j)位置到 (n,board[n]) n ∈[0,i-1] 横纵坐标的距离不能相等
     */
    private static boolean isPosOk(int[] board, int i, int j) {
        for(int k = 0 ; k < i ; k++){
            if(j == board[k] || Math.abs(i - k) == Math.abs(j - board[k])){
                return false;
            }
        }
        return true;
    }


    /**
     * 7. 机器人走路问题
     *
     * 机器人一共可以再 1 - N 范围内移动
     * 其实位置为 P , 需要到 T 位置去, 剩余的可走步数为 rest
     *
     * 当机器人到达 1 位置时，只能往右继续走；若机器人在 N 位置， 只能往左走；
     * 若处于中间位置，则即可以往左也可以往右走
     *
     * 求机器人到大P位置可以走的方式
     * @param N
     * @param P
     * @param T
     * @return
     */
    public static int robotWalk(int N , int P , int T , int rest){
        return walk(N, P, T, rest);
    }

    /**
     * 机器人走路问题 增加dp 数组缓存
     *
     * 定义二维数组 dp[N+1][rest+1], 其中dp 中的行代表当前位置， 列代表剩余步数
     *
     * 则 第一行对应0位置 由于位置编号从 1 到 N , 所以可以忽略
     * 对于第一列代表 rest = 0 ，这里只有到 当前位置就是T 位置时才为 1， 其它情况为 0
     * 而对于第二列，rest = 1，2，… 则每次都需要分三种情况讨论：
     *      ①：curr 位置为 1, 则dp[curr+1][rest-1] 位置的值等于当前 dp[curr][rest] 的值
     *      ②：curr 位置为 N, 则dp[curr-1][rest-1] 位置的值等于当前 dp[curr][rest] 的值
     *      ③：curr 位置为(1,N) 中间的值，则dp[curr-1][rest-1] + dp[curr+1][rest-1] 的值即为所求
     *
     * 最后对应到问题所求，dp[P][rest] 及为处于当前P 位置时，还剩rest 步情况下，的走法数
     *
     * @param N
     * @param P
     * @param T
     * @param K
     * @return
     */
    public static int robotWalk2(int N, int P , int T, int K){
        int[][] dp = new int[N+1][K+1];
        for(int i = 0 ; i <= K ; i++){
            dp[0][i] = -1;
        }
        for(int j = 1 ; j <= N ; j++){
            dp[j][0] = (T == j) ? 1 : 0;
        }
        int ans = walk2(N,P,K,dp);
        return ans;
    }

    /**
     * 机器人走路动态规划实现
     *
     * 动态规划的实质：将参数组合改造成结构化缓存
     *
     */
    private static int walk2(int N, int P, int K, int[][] dp) {
        for(int c = 1 ; c <= K  ; c++){
            for(int l = 1 ; l <= N ; l++){
                if(l == 1){
                    dp[l][c] = dp[l+1][c-1];
                }else if(l == N){
                    dp[l][c] = dp[l-1][c-1];
                }else{
                    dp[l][c] = dp[l-1][c-1] + dp[l+1][c-1];
                }
            }
        }
        return dp[P][K];
    }


    private static int walk(int N , int curr ,int T, int rest){
        if(rest == 0){
            return curr == T ? 1 : 0;
        }
        if(curr == 1){
            return walk(N,curr+1,T,rest - 1);
        }else if(curr == N){
            return walk(N,N-1,T,rest - 1);
        }

        int walk1 = walk(N, curr + 1, T, rest - 1);
        int walk2 = walk(N, curr - 1, T, rest - 1);
        return walk1 + walk2;
    }

    /**
     * 8. 最小路径和
     * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * 说明：每次只能向下或者向右移动一步。
     *
     * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
     * 输出：7
     * 解释：因为路径 1→3→1→1→1 的总和最小。
     *
     * 暴力递归尝试
     *
     *
     */
    public static int minPathSum(int[][]grid){
        int sum = dfsMinPathSum(grid,0,0);
        return sum;
    }

    /**
     *
     * @param grid
     * @param i
     * @param j
     * @return 处于当前(i,j) 位置到右下角的最小路径
     */
    private static int dfsMinPathSum(int[][] grid, int i, int j) {
        if(i == grid.length-1 && j == grid[0].length - 1){
            return grid[i][j];
        }
        int c1 = Integer.MAX_VALUE, c2 = Integer.MAX_VALUE;
        if(i+1 <= grid.length - 1){
            c1 = grid[i][j] + dfsMinPathSum(grid, i + 1, j);
        }
        if(j+1 <= grid[0].length - 1){
            c2 = grid[i][j] + dfsMinPathSum(grid, i, j + 1);
        }
        return Math.min(c1,c2);
    }

    /**
     * 8. 最小路径和
     * 暴力递归改动态规划
     *
     *
     *
     * @param grid
     * @return
     */
    public static int minPathSum1(int[][]grid){
        // 定义相同规模的dp矩阵
        int M = grid.length, N = grid[0].length;
        int[][] dp = new int[M][N];
        // 初始化最后一行和最后一列
        dp[M-1][N-1] = grid[M-1][N-1];
        for(int i = N-2 ; i >= 0 ; i--){
            dp[M-1][i] = dp[M-1][i+1] + grid[M-1][i];
        }
        for(int j = M-2; j >= 0 ; j--){
            dp[j][N-1] = dp[j+1][N-1] + grid[j][N-1];
        }
        for(int k = M-2 ; k >= 0 ; k--){
            for(int l = N-2 ; l >= 0 ; l--){
                int right = grid[k][l] + dp[k][l+1];
                int down = grid[k][l] + dp[k+1][l];
                dp[k][l] = Math.min(right,down);
            }
        }
        return dp[0][0];
    }


    /**
     * 9  最大正方形
     * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
     * 输入：matrix = [
     *                  ["1","0","1","0","0"],
     *                  ["1","0","1","1","1"],
     *                  ["1","1","1","1","1"],
     *                  ["1","0","0","1","0"]
     *               ]
     * 输出：4
     * @param matrix
     * @return
     */
    int maxSquare = 0;
    public int maximalSquare(char[][] matrix) {
        return 0;
    }

    /**
     * 示例1:
     *
     * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        if(nums.length <= 1) return nums.length == 1 ? nums[0] : 0;
        int max = nums[0] , sum = nums[0];
        for(int i = 1 ; i < nums.length; i++){
            sum += nums[i];
            if(nums[i] > sum){
                sum = nums[i];
            }
            max = max < sum ? sum : max;
        }
        return max;
    }


    private static void process(char[]arr,int index,List<String> res){
        if(index == arr.length){
            res.add(String.valueOf(arr));
            return;
        }
        // 所有后续位置都与当前位置交换一次
        // i==index 时自己与自己交换相当于不交换
        for(int i = index ; i < arr.length ;i++){
            swap(arr,index,i);
            process(arr,index+1,res);
            swap(arr,index,i);
        }
    }

    /**
     * 打印字符串全排列,带去重处理: 分支限界处理(枝剪)
     */
    private static void process2(char[]arr,int index,List<String> res){
        if(index == arr.length){
            res.add(String.valueOf(arr));
            return;
        }
        // 所有后续位置都与当前位置交换一次
        // i==index 时自己与自己交换相当于不交换
        boolean[] visit = new boolean[26];
        for(int i = index ; i < arr.length ;i++){
            if(!visit[arr[i] - 'a']){
                visit[arr[i]-'a'] = true;
                swap(arr,index,i);
                process2(arr,index+1,res);
                swap(arr,index,i);
            }
        }
    }

    private static void swap(char[] arr , int i , int j){
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void findAllSubSequence(String str, int index, Set<String> res, String path){
        if(index == str.length()){
            if(path.length() > 0){
                res.add(path);
            }
            return;
        }
        String no = path;
        findAllSubSequence(str,index+1,res,no);
        String yes = path + str.charAt(index);
        findAllSubSequence(str,index+1,res,yes);
    }


}
