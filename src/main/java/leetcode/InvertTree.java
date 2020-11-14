package leetcode;

import datastruct.TreeNode;

/**
 *
 * 《GeekTime -- practice day05》
 * 《226. 翻转二叉树》
 * 翻转一棵二叉树。
 *
 * 示例：
 *
 * 输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 */
public class InvertTree {


    public static void main(String[] args) {

    }


    /**
     *  深度优先搜索，递归的交换左右子树
     */
    public static TreeNode invertTree(TreeNode root) {
        dfsSwapChildren(root);
        return root;
    }


    /**
     *
     */
    private static void dfsSwapChildren(TreeNode root){
        if(root == null) return;

        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;

        dfsSwapChildren(root.left);
        dfsSwapChildren(root.right);
    }
}
