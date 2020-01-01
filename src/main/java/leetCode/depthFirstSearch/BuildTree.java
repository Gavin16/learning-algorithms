package leetCode.depthFirstSearch;

import dataStruct.TreeNode;

/**
 *  《105. 从前序与中序遍历序列构造二叉树》
 *
 *   根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class BuildTree {

    static TreeNode buildTree(int[] preorder, int[] inorder) {
        int t = preorder.length-1;
        return dfsConstructTree(preorder,0,t,inorder,0,t);
    }

    /** 最容易想到的解法 */
    private static TreeNode dfsConstructTree(int[] preorder, int sp,int tp,int[] inorder, int si,int ti) {
        if(sp > tp){
            return null;
        }

        int target = preorder[sp];
        TreeNode root = new TreeNode(target);

        int id = si;
        while(id < ti){
            if(target == inorder[id]) break;
            id++;
            continue;
        }
        // 找出 target 在 inorder 中的下标
        int leftNum = id - si;
        // 考虑 preOrder 的下标传参为什么要用偏移量来表示？？ 直接用id为何不可以
        root.left = dfsConstructTree(preorder,sp+1,sp + leftNum,inorder,si,id-1);
        root.right = dfsConstructTree(preorder,sp + leftNum + 1,tp,inorder,id+1,ti);

        return root;
    }

}
