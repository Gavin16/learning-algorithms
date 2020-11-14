package leetcode.depthFirstSearch;

import datastruct.TreeNode;

/**
 * 《LeetCode 98. 验证二叉搜索树》
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 *
 */
public class IsValidBST {

    static boolean isValidBST = true;

    static boolean isValidBST(TreeNode root) {
        nodeValueCheck(root,null,null);

        return isValidBST;
    }

    /**
     * 地毯式的搜索,即使发现当前二叉树不是BST树也不能立即停止
     * @param root
     * @param lessThan
     * @param greatThan
     */
    static void nodeValueCheck(TreeNode root,TreeNode lessThan,TreeNode greatThan){
        if(root == null) return ;
        TreeNode lLess = root,lGreat = null,rLess = null,rGreat = root;
        // 当前节点为左子节点
        if(lessThan != null){
            if(root.val >= lessThan.val){
                isValidBST = false;
            }
            rLess = lessThan;
        }
        if(greatThan != null){
            // 当前节点作为右子节点
            if(root.val <= greatThan.val){
                isValidBST = false;
            }
            lGreat = greatThan;
        }

        nodeValueCheck(root.left,lLess,lGreat);
        nodeValueCheck(root.right,rLess,rGreat);
    }


    public static boolean isValidBST1(TreeNode root) {
        return false;
    }
}
