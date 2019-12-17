package leetCode.depthFirstSearch;

import dataStruct.TreeNode;
import edu.princeton.cs.Stack;
import edu.princeton.cs.StaticSETofInts;

/**
 *  《110. 平衡二叉树》 标签：深度优先搜索
 *  给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 *
 * 示例 1:
 *
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 */
public class IsBalanced {

    public static boolean isBalanced(TreeNode root) {
        if(null == root) return true;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode pop = stack.pop();
            int lh = treeHeight(pop.left);
            int rh = treeHeight(pop.right);
            if(Math.abs(lh - rh) > 1) return false;

            if(pop.left != null) stack.push(pop.left);
            if(pop.right != null) stack.push(pop.right);
        }
        return true;
    }

    /**
     * 求二叉树的高度
     */
    static int treeHeight(TreeNode child){
        if(null == child) return 0;

        int leftH = treeHeight(child.left);
        int rightH = treeHeight(child.right);

        return 1 + Math.max(leftH,rightH);
    }
}
