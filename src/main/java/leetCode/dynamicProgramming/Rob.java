package leetCode.dynamicProgramming;

import dataStruct.TreeNode;
import utils.TreeNodeUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Class: Rob
 * @Description:
 *
 *  《LeetCode 198. 打家劫舍》
 *
 *   你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 *   给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 *   示例 1:
 *
 *   输入: [1,2,3,1]
 *   输出: 4
 *   解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 *
 * @Author: Minsky
 * @Date: 2019/8/7 11:48
 * @Version: v1.0
 */
public class Rob {


    public static void main(String[]args){
        int[] arr = new int[]{2,7,9,3,1};
        System.out.println(rob1(arr));

        Integer[] nums = {2,1,3,null,4};

        // 打家劫舍 III
        TreeNode treeNode = TreeNodeUtil.genTreeNodeFromArray(nums);
        TreeNodeUtil.preOrderPrint(treeNode);

        System.out.println(rob3(treeNode));
        int[] xx = {1,2,3,1};
        System.out.println(rob1X(xx));
        System.out.println(rob1XX(xx));
    }


    /**
     * 自顶向下的DP: 循环实现;
     *
     * 动态规划求解
     * max[i] = max(max[i-1] , max[i-2] + num[i])
     * @param nums
     * @return
     */
    public static int rob1(int[] nums) {
        if(nums == null || nums.length < 1)  return 0;
        int len = nums.length;
        int[] max = new int[len + 1];

        max[0] = 0 ;
        max[1] = nums[0];

        for(int i = 2; i <= len; i++){
            max[i] = Math.max(max[i-1] , max[i-2] + nums[i-1]);
        }
        return max[len];
    }


    /**
     * 自顶向下的DP: 递归实现;
     *
     * 递归实现存在重复计算的问题，引入数组存储某个位置的当前值
     */
    public static int rob1Y(int[] nums){
        int[] memo = new int[nums.length];
        Arrays.fill(memo,-1);
        return dp(nums,0,memo);
    }

    private static int dp(int[] nums , int start , int[] memo){
        if(start >= nums.length) return 0;

        if(memo[start] != -1) return memo[start];

        int skip = dp(nums,start+1,memo);
        int rob = nums[start] + dp(nums,start+2,memo);

        int res = Math.max(skip,rob);
        memo[start] =  res;
        return res;
    }

    /**
     * 自底向上的方式DP: 循环实现
     */
    public static int rob1X(int[] nums){
        int n = nums.length;
        int[] max = new int[n + 2];
        for(int i = n - 1; i >= 0 ; i--){
            max[i] = Math.max(max[i+2] + nums[i],max[i+1]);
        }
        return max[0];
    }

    /**
     * 自底向上优化解法： 数据存储改为三个变量存储
     */
    public static int rob1XX(int[] nums){
        if(null == nums || nums.length == 0) return 0;

        int n = nums.length;
        int maxPost1 = 0 , maxPost2 = 0,maxVal = 0;
        for(int i = n-1; i >= 0 ; i--){
            maxVal = Math.max(maxPost2 + nums[i], maxPost1);
            maxPost2 = maxPost1;
            maxPost1 = maxVal;
        }
        return maxVal;
    }



    /**
     *
     * 《213. 打家劫舍 II》
     *
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
     *
     * 示例 1:
     *
     * 输入: [2,3,2]
     * 输出: 3
     * 解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
     * 示例 2:
     *
     * 输入: [1,2,3,1]
     * 输出: 4
     * 解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     *
     * 状态转移方程： maxs[i] = Math.max((maxs[i-2] + nums[i-1]), maxs[i-1]))    0<= i <= nums.length
     */
    public static int rob2(int[] nums){
        if(null == nums || 0 == nums.length) return 0;
        if(nums.length == 1) return nums[0];
        int max1 = dp(nums,0,nums.length-1);
        int max2 = dp(nums,1,nums.length);
        return Math.max(max1,max2);
    }

    /**
     * 指定首尾位置做 自顶向下DP
     */
    private static int dp(int[] nums, int h,int t){
        int maxPre2 = 0 , maxPre1 = 0, max = 0;
        for(int i = h ; i < t; i++){
            max = Math.max(maxPre2 + nums[i],maxPre1);
            maxPre2 = maxPre1;
            maxPre1 = max;
        }
        return max;
    }

    /**
     * 《337. 打家劫舍 III》
     *  在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
     *
     * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
     *
     * 示例 1:
     *
     * 输入: [3,2,3,null,3,null,1]
     *
     *      3
     *     / \
     *    2   3
     *     \   \
     *      3   1
     *
     * 输出: 7
     * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
     * 示例 2:
     *
     * 输入: [3,4,5,1,3,null,1]
     *
     *      3
     *     / \
     *    4   5
     *   / \   \
     *  1   3   1
     *
     * 输出: 9
     * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
     *
     * 先找出二叉树每层的金额和，从而将问题转化为层的数组问题(同打家劫舍I)
     *
     */
    public static int rob(TreeNode root) {
        if(null == root) return 0;
        List<Integer> layerVal = new ArrayList<>();

        dfsAddupLayers(layerVal,root,0);;

        int ls = layerVal.size();
        if(ls > 2){
            int[] max = new int[ls + 1];

            max[0] = 0 ;
            max[1] = layerVal.get(0);

            for(int i = 2; i <= ls; i++){
                max[i] = Math.max(max[i-1] , max[i-2] + layerVal.get(i-1));
            }
            return max[ls];
        }else{
            int max = layerVal.get(0);
            for(int n : layerVal){
                if(n > max) max = n;
            }
            return max;
        }
    }

    private static void dfsAddupLayers(List<Integer> list ,TreeNode root,int l){
        if(l < list.size()){
            int pre = list.get(l);
            list.set(l,pre + root.val);
        }else{
            list.add(l,root.val);
        }

        if(null != root.left) dfsAddupLayers(list,root.left,l+1);
        if(null != root.right) dfsAddupLayers(list,root.right,l+1);
    }


    private static HashMap<TreeNode,Integer> map = new HashMap<>();

    /**
     * 使用状态保存当前节点：
     * 最大值的保存是从叶子节点往根节点方向，逐层的保存相邻三层的最大值，对于那些靠近根节点的点，需要从叶子节点重新计算最大值，因此起到避免重复计算的效果
     *
     */
    public static int rob3(TreeNode root){
        if(null == root) return 0;

        if(map.containsKey(root)) return map.get(root);

        int rob = root.val + (root.left == null ? 0 : rob3(root.left.left) + rob3(root.left.right))
                    + (root.right == null ? 0 : rob3(root.right.left) + rob3(root.right.right));

        int not_rob = (root.left == null ? 0 : rob3(root.left)) + (root.right == null ? 0 : rob3(root.right));

        int res = Math.max(rob,not_rob);

        // 保存当前节点能获取到的最大的值
        map.put(root,res);
        return res;
    }




}
