package leetCode.depthFirstSearch;

import dataStruct.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 《112. 路径总和》 标签： 深度优先搜索
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例: 
 * 给定如下二叉树，以及目标和 sum = 22
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 *  5,4,11,7,2,8,13,4,1
 *
 *  《113. 路径总和 II》
 *  给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 *
 */
public class HasPathSum {

    public static void main(String[] args) {
        int[] arr = {5,4,8,11,};

    }

    static boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;

        int sumLeft = sum - root.val;
        if((root.left == null && root.right == null) && sumLeft == 0)
            return true;

        boolean isLeft = false,isRight = false;
        if(root.left != null)
            isLeft = hasPathSum(root.left,sumLeft);
        if(root.right != null)
            isRight = hasPathSum(root.right,sumLeft);
        return isLeft || isRight;
    }


    static List<List<Integer>> resList = new ArrayList<>();
    /**
     *  找出所有从根结点到叶子节点路径和等于sum 的路径
     */
    static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<Integer> list = new ArrayList<>();
        dfsToMatchSum(root,list,sum);
        return resList;
    }

    /** 深度优先搜索 */
    static void dfsToMatchSum(TreeNode root,List<Integer> list, int sum){
        if(null == root) return;

        list.add(root.val);
        if(sum == root.val && root.left == null && root.right == null){
            // 需要在叶子节点处将对象拷贝一份，以便返回上级调用时可以重复使用
            resList.add(new ArrayList<>(list));
        }
        dfsToMatchSum(root.left,list,sum - root.val);
        dfsToMatchSum(root.right,list,sum - root.val);
        // 返回上一级节点需要将当前节点删除
        list.remove(list.size()-1);
    }

}
