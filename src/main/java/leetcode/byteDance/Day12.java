package leetcode.byteDance;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @className: Day12
 * @description: TODO
 * @version: 1.0
 * @author: minsky
 * @date: 2022/8/16
 */
public class Day12 {


    public static void main(String[] args) {

        Day12 day12 = new Day12();
//        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
//        int[] height1 = {4,2,0,3,2,5};
//        System.out.println(day12.trap2(height));
//        System.out.println(day12.trap2(height1));

//        int[] arr = {-2,1,-3,4,-1,2,1,-5,4};
//        int[] arr1 = {5,4,-1,7,8};
//        System.out.println(day12.maxSubArray(arr));
//        System.out.println(day12.maxSubArray(arr1));

//        int[][]matrix = {{1,2,3},{4,5,6},{7,8,9}};
//        int[][]matrix1 = {{1,2,3},{4,5,6}};
//        int[][]matrix2= {{1,9}};
//        int[][]matrix3= {{9}};
//        int[][]matrix4= {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
//        System.out.println(day12.spiralOrder(matrix));
//        System.out.println(day12.spiralOrder(matrix1));
//        System.out.println(day12.spiralOrder(matrix2));
//        System.out.println(day12.spiralOrder(matrix4));

//        for(int i = Integer.MAX_VALUE - 10; i < Integer.MAX_VALUE; i++){
//            int ans = day12.mySqrt(i);
//            if(ans != Math.floor(Math.sqrt(i))){
//                System.out.println(Boolean.FALSE);
//            }
//        }


        char[][] board1 = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        char[][] board2 = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        System.out.println(day12.exist(board1, "ABCCED"));
        System.out.println(day12.exist(board2, "ABCB"));

    }

    /**
     * 42. 接雨水
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     *
     * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出：6
     * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
     * 示例 2：
     *
     * 输入：height = [4,2,0,3,2,5]
     * 输出：9
     *
     * 示例 2：
     *
     * 输入：height = [4,2,0,3,2,5]
     * 输出：9
     *
     * 提示：
     *
     * n == height.length
     * 1 <= n <= 2 * 104
     * 0 <= height[i] <= 105
     *
     */
    public int trap(int[] height) {
        int len = height.length;
        if(len <= 2) return 0;
        int[] left = new int[len];
        int[] right = new int[len];

        for(int i = 1; i < len; i++){
            left[i] = Math.max(left[i-1],height[i-1]);
        }
        for(int k = len - 2; k >= 0; k--){
            right[k] = Math.max(right[k+1], height[k+1]);
        }
        int ans = 0;
        for(int j = 1; j < len - 1; j++){
            int amt = Math.min(left[j], right[j]) - height[j];
            ans += (amt > 0 )? amt : 0;
        }
        return ans;
    }

    /**
     * 使用单调栈
     *
     */
    public int trap2(int[] height) {
        int len = height.length;
        if(len <= 2) return 0;
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for(int i = 0 ; i < height.length; i++){
            while(!stack.isEmpty() && height[i] > height[stack.peek()]){
                int pop = stack.pop();
                if(stack.isEmpty()) break;
                int left = stack.peek();
                int width = i - left - 1;
                int heightGap = Math.min(height[left], height[i]) - height[pop];
                ans += heightGap * width;
            }
            stack.push(i);
        }
        return ans;
    }


    /**
     * 53. 最大子数组和
     *
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 子数组 是数组中的一个连续部分。
     *
     * 示例 1：
     *
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     * 示例 2：
     *
     * 输入：nums = [1]
     * 输出：1
     * 示例 3：
     *
     * 输入：nums = [5,4,-1,7,8]
     * 输出：23
     *  
     * 提示：
     *
     * 1 <= nums.length <= 105
     * -104 <= nums[i] <= 104
     *
     * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
     *
     *
     * 动态规划求解
     * 定义dp[i] 代表以 i 为结尾的子数组的最大和
     *
     * 对于dp[i] = max(dp[i-1] + nums[i], nums[i]) 也就是说对于元素 i,它要么加入前面的子数组组成新的更大子数组的和
     * 要么以自己单独作为最大子数组。
     *
     */
    public int maxSubArray(int[] nums) {
        if(nums.length == 1) return nums[0];
        int preMax = nums[0], currMax;
        int ans = nums[0];

        for(int i = 1; i < nums.length; i++){
            currMax = Math.max(preMax + nums[i], nums[i]);
            ans = Math.max(currMax, ans);
            preMax = currMax;
        }
        return ans;
    }


    /**
     * 54. 螺旋矩阵
     * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
     *
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[1,2,3,6,9,8,7,4,5]
     *
     * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
     * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
     *
     * 提示：
     *
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 10
     * -100 <= matrix[i][j] <= 100
     *
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
        int[] boundarys = {n,m,-1,-1};

        List<Integer> ans = new ArrayList<>();
        int total = m * n, dir = 0;
        for(int i = 0, j = 0; ans.size() < total; ){
            ans.add(matrix[i][j]);
            if(!isNextPositionInRange(dirs[dir],i, j, boundarys)){
                int bd = (dir + 3) % 4;
                boundarys[bd] += (bd >= 2) ? 1 : -1;
                dir  = (dir + 1) % 4;
            }
            i += dirs[dir][0];
            j += dirs[dir][1];
        }
        return ans;
    }

    private boolean isNextPositionInRange(int[] dir, int i, int j, int[] boundarys) {
        int iNext = i + dir[0], jNext = j + dir[1];
        if(iNext > boundarys[3] && iNext < boundarys[1] &&
                jNext > boundarys[2] && jNext < boundarys[0]){
            return true;
        }
        return false;
    }


    /**
     * 69. x 的平方根
     * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
     * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
     * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5
     *
     * 示例 1：
     *
     * 输入：x = 4
     * 输出：2
     * 示例 2：
     *
     * 输入：x = 8
     * 输出：2
     * 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
     *  
     * 提示：
     *
     * 0 <= x <= 231 - 1
     *
     */
    public int mySqrt(int x) {
        long low = 0, high = x;
        while(low <= high){
            long mid = low + ((high - low) >> 1);
            if(mid * mid > x){
                high = mid - 1;
            }else if(mid * mid < x){
                low = mid + 1;
            }else{
                return (int)mid;
            }
        }
        return (int)high;
    }



    /**
     * 79. 单词搜索
     *
     * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     *
     * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
     * 输出：true
     *
     * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
     * 输出：false
     *  
     *
     * 提示：
     *
     * m == board.length
     * n = board[i].length
     * 1 <= m, n <= 6
     * 1 <= word.length <= 15
     * board 和 word 仅由大小写英文字母组成
     */
    public boolean exist(char[][] board, String word) {
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length ; j++){
                if(word.charAt(0) == board[i][j]){
                    boolean isExist = dfsSearch(board,i,j,word,0);
                    if(isExist) return true;
                }
            }
        }
        return false;
    }

    private boolean dfsSearch(char[][] board, int i, int j, String word, int sPos){
        if(i >= 0 && i < board.length && j >= 0 && j < board[0].length){
            if(sPos == word.length() - 1 && board[i][j] == word.charAt(sPos)) return true;
            if(board[i][j] != word.charAt(sPos)) return false;
            char temp = board[i][j];
            board[i][j] = '1';
            boolean b = dfsSearch(board, i + 1, j, word, sPos + 1);
            boolean b1 = dfsSearch(board, i - 1, j, word, sPos + 1);
            boolean b2 = dfsSearch(board, i, j + 1, word, sPos + 1);
            boolean b3 = dfsSearch(board, i, j - 1, word, sPos + 1);
            board[i][j] = temp;
            return b || b1 || b2 || b3;
        }
        return false;
    }

}
