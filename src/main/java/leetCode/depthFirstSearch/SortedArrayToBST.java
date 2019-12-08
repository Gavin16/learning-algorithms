package leetCode.depthFirstSearch;

import dataStruct.TreeNode;
import utils.TreeNodeUtil;

/**
 *
 * 《108. 将有序数组转换为二叉搜索树》 标签: 深度优先搜索
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定有序数组: [-10,-3,0,5,9],
 *
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 */
public class SortedArrayToBST {

    public static void main(String[] args) {
        int[] testArr = {-10,-3,0,5,9};
        TreeNodeUtil.midOrderPrint(sortedArrayToBST(testArr));
    }

    /**
     *
     */
    static TreeNode sortedArrayToBST(int[] nums){
        return recursiveTransToBST(nums,0,nums.length-1);
    }


    /**
     * 递归实现有序数组转化为 二叉搜索树
     */
    static TreeNode recursiveTransToBST(int[]nums, int s, int e){
        if(s > e) return null;
        int mid = s + (e - s)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = recursiveTransToBST(nums,s, mid - 1);
        root.right = recursiveTransToBST(nums,mid + 1, e);
        return root;
    }
}
