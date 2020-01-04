package leetCode.depthFirstSearch;

import dataStruct.TreeNode;

import java.util.HashMap;
import java.util.Map;

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
        // 考虑 preOrder 的下标传参为什么要用偏移量来表示？？ 直接用id为何不可以 ？ -- 因为id相对于si的偏移量并不等于相对于sp的偏移量
        root.left = dfsConstructTree(preorder,sp+1,sp + leftNum,inorder,si,id-1);
        root.right = dfsConstructTree(preorder,sp + leftNum + 1,tp,inorder,id+1,ti);

        return root;
    }


    static TreeNode buildTree2(int[] preorder, int[] inorder) {

        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < inorder.length ; i++){
            map.put(inorder[i],i);
        }
        return dfsConstructTree2(map,preorder,0,preorder.length-1,inorder,0,inorder.length-1);
    }

    /**
     * 针对以上解法在 (inorder 每次都需要遍历才能找出id)的优化
     */
    private static TreeNode dfsConstructTree2(Map<Integer,Integer> map , int[] preorder, int sp, int tp, int[] inorder, int si, int ti){
        if(sp > tp) return null;

        int val = preorder[sp];
        TreeNode root = new TreeNode(val);

        int inId = map.get(val);
        int leftRange = inId - si;

        root.left = dfsConstructTree2(map,preorder,sp+1,sp+leftRange,inorder,si,inId-1);
        root.right = dfsConstructTree2(map,preorder,sp + leftRange + 1,tp,inorder,inId+ 1,ti);

        return root;
    }


    /**
     * 《106. 从中序与后序遍历序列构造二叉树》
     * 中序遍历 inorder = [9,3,15,20,7]
     * 后序遍历 postorder = [9,15,7,20,3]
     */
    public static TreeNode buildTree3(int[] inorder, int[] postorder) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int j = 0 ; j < inorder.length; j++){
            map.put(inorder[j],j);
        }
        return dfsConstructTree3(map,inorder,0,inorder.length-1,postorder,postorder.length-1,0);
    }

    private static TreeNode dfsConstructTree3(Map<Integer,Integer> map , int[] inorder,int si ,int ti , int[] postorder,int sp,int tp){
        if(sp < tp) return null;

        int val = postorder[sp];
        TreeNode root = new TreeNode(val);

        Integer pos = map.get(val);
        int leftRange = pos - si;

        root.left = dfsConstructTree3(map,inorder,si,pos-1,postorder,tp + leftRange -1,tp);
        root.right = dfsConstructTree3(map,inorder,pos + 1,ti,postorder,sp-1,tp + leftRange);

        return root;
    }

}
