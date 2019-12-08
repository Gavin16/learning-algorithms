package leetCode.depthFirstSearch;

import dataStruct.TreeNode;

/**
 * 《111. 二叉树的最小深度》 标签：深度优先搜索
 *
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最小深度  2.
 *
 */
public class MinDepth {

    public static void main(String[] args) {

    }

    /**
     *  最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     */
    static int minDepth(TreeNode root) {
        if(null == root) return 0;

        int minH = 1, minChildD = 0;
        if(root.left == null && root.right == null){
            return 1;
        }else if(root.left == null){
            minChildD = minDepth(root.right);
        }else if(root.right == null){
            minChildD = minDepth(root.left);
        }else{
            minChildD = Math.min(minDepth(root.left),minDepth(root.right));
        }
        return minH + minChildD;
    }
}
