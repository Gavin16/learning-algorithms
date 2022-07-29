package leetcode.byteDance;

import utils.ArrayUtil;

import java.util.*;

/**
 * @className: Day02
 * @description: TODO
 * @version: 1.0
 * @author: minsky
 * @date: 2022/7/28
 */
public class Day02 {

    public static void main(String[] args) {

        int[] arr = {5,7,7,8,8,10};
        int[] arr1 = {5,7,7,8,8,10};
        int[] arr2 = {1,4};
        Day02 day02 = new Day02();
//        int[] ans = day02.searchRange(arr, 8);
//        int[] ans1 = day02.searchRange(arr1, 6);
//        int[] ans2 = day02.searchRange(arr2, 4);
//        ArrayUtil.showArray(ans);
//        ArrayUtil.showArray(ans1);
//        ArrayUtil.showArray(ans2);

        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(3);
        TreeNode treeNode3 = new TreeNode(4);
        TreeNode treeNode4 = new TreeNode(5);
        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        treeNode1.left = treeNode3;
        treeNode2.right = treeNode4;

//        List<List<Integer>> lists = day02.zigzagLevelOrder(treeNode);
//        System.out.println(lists);
//        List<List<Integer>> lists1 = day02.zigzagLevelOrder(null);
//        System.out.println(lists1);
//        List<List<Integer>> lists2 = day02.zigzagLevelOrder(treeNode4);
//        System.out.println(lists2);
        String version1 = "1.01", version2 = "1.001";
        String version3 = "1.0", version4 = "1.0.0";
        String version5 = "0.1", version6 = "1.1";

//        System.out.println(day02.compareVersion(version1, version2));
//        System.out.println(day02.compareVersion(version3, version4));
//        System.out.println(day02.compareVersion(version5, version6));

        int[] house = {1,2,3,1};
        int[] house1 = {2,7,9,3,1};
        int[] house2 = {2,1,1,2};

//        System.out.println(day02.rob(house));
//        System.out.println(day02.rob(house1));
//        System.out.println(day02.rob(house2));

        char[][] grid = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}};
        char[][] grid2 = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };
        System.out.println(day02.numIslands(grid));
        System.out.println(day02.numIslands(grid2));
    }



    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     *
     * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
     *
     * 示例 1：
     *
     * 输入：nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     * 示例 2：
     *
     * 输入：nums = [5,7,7,8,8,10], target = 6
     * 输出：[-1,-1]
     * 示例 3：
     *
     * 输入：nums = [], target = 0
     * 输出：[-1,-1]
     *  
     * 提示：
     *
     * 0 <= nums.length <= 105
     * -109 <= nums[i] <= 109
     * nums 是一个非递减数组
     * -109 <= target <= 109
     *
     */
    public int[] searchRange(int[] nums, int target) {
        int[] ans = new int[2];
        Arrays.fill(ans, -1);
        if(nums.length == 0) return ans;

        // 两次二分查找，第一次找最左边那个，第二次找最右边那个
        int leftPos = binSearchWithBound(nums,target,Boolean.TRUE);
        int rightPos = binSearchWithBound(nums,target,Boolean.FALSE);
        ans[0] = leftPos;
        ans[1] = rightPos;
        return ans;
    }

    // 带边界二分查找
    private int binSearchWithBound(int[]nums, int target, boolean leftBound){
        int mid = 0, low = 0, high = nums.length - 1;
        while( low <= high ){
            mid = low + (high - low)/2;
            if(nums[mid] < target){
                low = mid + 1;
            }else if(nums[mid] > target){
                high = mid - 1;
            }else{
                break;
            }
        }
        if(low > high) return -1;
        while((mid >= 0 && mid < nums.length) && nums[mid] == target){
            if(leftBound) mid--;
            else  mid++;
        }
        return leftBound ? mid+1 : mid-1;
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
        }
    }

    /**
     * 103. 二叉树的锯齿形层序遍历
     * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
     *
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：[[3],[20,9],[15,7]]
     * 示例 2：
     *
     * 输入：root = [1]
     * 输出：[[1]]
     * 示例 3：
     *
     * 输入：root = []
     * 输出：[]
     *
     * 提示：
     * 树中节点数目在范围 [0, 2000] 内
     * -100 <= Node.val <= 100
     *
     * 使用双端队列做层序遍历
     *
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        boolean orderLeft2Right = true;
        while(!deque.isEmpty()){
            List<Integer> intList = new ArrayList<>();
            int len = deque.size();
            for(int i = 0; i < len ; i++){
                TreeNode treeNode = deque.removeFirst();
                if(null != treeNode.left) deque.addLast(treeNode.left);
                if(null != treeNode.right) deque.addLast(treeNode.right);
                intList.add(treeNode.val);
            }
            if(!orderLeft2Right){
                Collections.reverse(intList);
            }
            orderLeft2Right = !orderLeft2Right;
            ans.add(intList);
        }
        return ans;
    }

    /**
     * 给你两个版本号 version1 和 version2 ，请你比较它们。
     * 版本号由一个或多个修订号组成，各修订号由一个 '.' 连接。每个修订号由 多位数字 组成，可能包含 前导零 。
     * 每个版本号至少包含一个字符。修订号从左到右编号，下标从 0 开始，最左边的修订号下标为 0 ，下一个修订号下标为 1 ，
     * 以此类推。例如，2.5.33 和 0.1 都是有效的版本号。
     *
     * 比较版本号时，请按从左到右的顺序依次比较它们的修订号。比较修订号时，只需比较 忽略任何前导零后的整数值 。
     * 也就是说，修订号 1 和修订号 001 相等 。如果版本号没有指定某个下标处的修订号，则该修订号视为 0 。
     * 例如，版本 1.0 小于版本 1.1 ，因为它们下标为 0 的修订号相同，而下标为 1 的修订号分别为 0 和 1 ，0 < 1 。
     *
     * 返回规则如下：
     *
     * 如果 version1 > version2 返回 1，
     * 如果 version1 < version2 返回 -1，
     * 除此之外返回 0。
     *
     * 示例 1：
     *
     * 输入：version1 = "1.01", version2 = "1.001"
     * 输出：0
     * 解释：忽略前导零，"01" 和 "001" 都表示相同的整数 "1"
     * 示例 2：
     *
     * 输入：version1 = "1.0", version2 = "1.0.0"
     * 输出：0
     * 解释：version1 没有指定下标为 2 的修订号，即视为 "0"
     * 示例 3：
     *
     * 输入：version1 = "0.1", version2 = "1.1"
     * 输出：-1
     * 解释：version1 中下标为 0 的修订号是 "0"，version2 中下标为 0 的修订号是 "1" 。0 < 1，所以 version1 < version2
     *  
     *
     * 提示：
     *
     * 1 <= version1.length, version2.length <= 500
     * version1 和 version2 仅包含数字和 '.'
     * version1 和 version2 都是 有效版本号
     * version1 和 version2 的所有修订号都可以存储在 32 位整数 中
     *
     */
    public int compareVersion(String version1, String version2) {
        String[] ver1 = version1.split("\\.");
        String[] ver2 = version2.split("\\.");

        int maxLen = Math.max(ver1.length, ver2.length);
        int[] intVer1 = new int[maxLen];
        int[] intVer2 = new int[maxLen];
        int i1 = 0, i2 = 0;
        for(String str : ver1){
            intVer1[i1++] = Integer.parseInt(str);
        }
        for(String str : ver2){
            intVer2[i2++] = Integer.parseInt(str);
        }
        // 从左到右比较修订号
        int cmpRes = 0;
        for(int i = 0 ; i < maxLen; i++){
            if(intVer1[i] > intVer2[i]) return 1;
            else if(intVer1[i] < intVer2[i]) return -1;
        }
        return cmpRes;
    }

    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
     * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     * 示例 1：
     *
     * 输入：[1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     * 示例 2：
     *
     * 输入：[2,7,9,3,1]
     * 输出：12
     * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     *
     * 提示：
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 400
     */
    public int rob(int[] nums) {
        int len = nums.length;
        if(len == 1) return nums[0];
        if(len == 2) return Math.max(nums[0], nums[1]);

        int[] dp = new int[len + 1];
        dp[1] = nums[0];
        dp[2] = Math.max(nums[0], nums[1]);

        for(int i = 2; i < len; i++){
            dp[i+1] = Math.max(dp[i], dp[i-1] + nums[i]);
        }
        return dp[len];
    }


    /**
     * 200. 岛屿数量
     *
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     * 此外，你可以假设该网格的四条边均被水包围。
     *
     * 示例 1：
     *
     * 输入：grid = [
     *   ["1","1","1","1","0"],
     *   ["1","1","0","1","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","0","0","0"]
     * ]
     * 输出：1
     * 示例 2：
     *
     * 输入：
     * grid = [
     *   ["1","1","0","0","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","1","0","0"],
     *   ["0","0","0","1","1"]
     * ]
     * 输出：3
     *
     * 提示：
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 300
     * grid[i][j] 的值为 '0' 或 '1'
     *
     */
    public int numIslands(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int islandsCount = 0;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                int dfs = dfs(grid,rows, cols, i, j);
                islandsCount += dfs;
            }
        }
        return islandsCount;
    }

    private int dfs(char[][] grid, int rows , int cols, int i, int j){
        // 边界判断
        if(i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] != '1'){ return 0;}
        grid[i][j] = '2';
        dfs(grid,rows,cols,i-1,j);
        dfs(grid,rows,cols, i+1,j);
        dfs(grid,rows,cols,i,j-1);
        dfs(grid,rows,cols,i,j+1);
        return 1;
    }
}
