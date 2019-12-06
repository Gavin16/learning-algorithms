package leetCode;

import dataStruct.TreeNode;
import edu.princeton.cs.Stack;
import utils.TreeNodeUtil;

/**
 * 《LeetCode 101. 对称二叉树》
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 * 运用递归和迭代两种方法解决这个问题.
 */
public class IsSymmetric {

    public static void main(String[] args) {
        int[] arr = {1,2,2,3,4,4};
        TreeNode treeNode = TreeNodeUtil.genTreeNodeFromArray(arr);
        TreeNodeUtil.preOrderPrint(treeNode);

        System.out.println(isSymmetric1(treeNode));
    }

    /**
     * 递归实现
     */
    static boolean isSymmetric(TreeNode root) {
        if(null == root) return true;
        return isChildSymmetric(root.left,root.right);
    }

    /**
     * 循环实现: 使用栈分别遍历根节点左右子树
     */
    static boolean isSymmetric1(TreeNode root) {
        if(null == root || (root.left == null && root.right == null)) return true;

        Stack<TreeNode> leftStatck = new Stack<>(),rightStatck = new Stack<>();

        leftStatck.push(root.left);
        rightStatck.push(root.right);

        // 逐层遍历左右子树
        while(!leftStatck.isEmpty() || !rightStatck.isEmpty()){
            TreeNode lRoot = leftStatck.pop(),rRoot = rightStatck.pop();

            // 循环条件控制 lRoot 和 rRoot 不能同时为null；
            // 因此只要其中一个为null,就代表左右子树不对称
            if(lRoot == null || rRoot == null)
                return false;

            if(lRoot.val == rRoot.val){
                if(lRoot.right != null || rRoot.left != null){
                    leftStatck.push(lRoot.right);
                    rightStatck.push(rRoot.left);
                }
                if(lRoot.left != null || rRoot.right != null){
                    leftStatck.push(lRoot.left);
                    rightStatck.push(rRoot.right);
                }
            }else{
                return false;
            }
        }
        return true;
    }

    private static boolean isChildSymmetric(TreeNode left, TreeNode right) {
        if(null == left && null == right){
            // 边界条件
            return true;
        }else if(null == left || null == right){
            return false;
        }else if(left.val == right.val){
            return isChildSymmetric(left.left,right.right) && isChildSymmetric(left.right,right.left);
        }else{
            return false;
        }
    }
}
