package leetCode.dynamicProgramming;

import dataStruct.TreeNode;
import utils.TreeNodeUtil;

import java.util.ArrayList;
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
        System.out.println(rob(arr));

        Integer[] nums = {2,1,3,null,4};

        // 打家劫舍 III
        TreeNode treeNode = TreeNodeUtil.genTreeNodeFromArray(nums);
        TreeNodeUtil.preOrderPrint(treeNode);
    }


    /**
     * 动态规划求解
     * max[i] = max(max[i-1] , max[i-2] + num[i])
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
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


}
