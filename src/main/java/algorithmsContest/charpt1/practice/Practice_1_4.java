package algorithmsContest.charpt1.practice;

import datastruct.TreeNode;

import java.util.HashMap;

/**
 * @className: Practice_1_4
 * @description: 1.4 二叉树
 *
 * 哈夫曼树:
 * 洛谷: P1087/P1305/P1030/P1229/P5018/P5597/P2168
 *
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2022/12/16
 */
public class Practice_1_4 {

    public static void main(String[] args) {
        Practice_1_4 instance = new Practice_1_4();
//        int[] inorder = {9,3,15,20,7}, postorder = {9,15,7,20,3};
//        TreeNode treeNode = instance.buildTree2(inorder, postorder);
//        TreeNodeUtil.printTreeGraph(treeNode);

        int[] preorder = {3,9,20,15,7}, inorder1 = {9,3,15,20,7};
        TreeNode treeNode1 = instance.buildTree1(preorder, inorder1);
    }



    /**
     * 105. 从前序与中序遍历序列构造二叉树
     * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
     *
     * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
     * 输出: [3,9,20,null,null,15,7]
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        int len = preorder.length;
        for(int k = 0; k < len; k++){
            map.put(inorder[k], k);
        }
        return buildRecursively(preorder,0,len-1,0,len-1);
    }
    HashMap<Integer,Integer> map = new HashMap<>();
    private TreeNode buildRecursively(int[]preorder,int ps, int pe,
                                      int is, int ie){
        if(ps > pe || is > ie)return null;
        int inId = map.get(preorder[ps]);
        int left_is = is, left_ie = inId - 1, left_ps = ps + 1, left_pe = ps + inId - is;
        int right_is = inId + 1, right_ie = ie,
                right_ps = left_pe + 1, right_pe = pe;
        TreeNode treeNode = new TreeNode(preorder[ps]);
        treeNode.left = buildRecursively(preorder,left_ps, left_pe, left_is, left_ie);
        treeNode.right = buildRecursively(preorder,right_ps,right_pe,right_is,right_ie);
        return treeNode;
    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     * 给定两个整数数组 inorder 和 postorder
     * 其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
     *
     * 输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
     * 输出：[3,9,20,null,null,15,7]
     *
     * 提示:
     *
     * 1 <= inorder.length <= 3000
     * postorder.length == inorder.length
     * -3000 <= inorder[i], postorder[i] <= 3000
     * inorder 和 postorder 都由 不同 的值组成
     * postorder 中每一个值都在 inorder 中
     * inorder 保证是树的中序遍历
     * postorder 保证是树的后序遍历
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        int inLen = inorder.length;
        for(int i = 0; i < inLen; i++){
            memo.put(inorder[i], i);
        }
        return buildTreeNode(inorder,0, inLen - 1,
                postorder, 0, inLen - 1);
    }
    HashMap<Integer,Integer> memo = new HashMap<>();
    private TreeNode buildTreeNode(int[] inorder, int is, int ie,
                                   int[] postorder, int ps, int pe){
        if(is > ie || ps > pe) return null;

        int iid = memo.get(postorder[pe]);
        TreeNode treeNode = new TreeNode(inorder[iid]);
        int left_is = is, left_ie = iid - 1;
        int left_ps = ps , left_pe = ps + iid - is - 1;
        int right_is = iid + 1, right_ie = ie;
        int right_ps = ps + iid - is, right_pe = pe - 1;

        treeNode.left = buildTreeNode(inorder,left_is, left_ie,
                postorder, left_ps, left_pe);
        treeNode.right = buildTreeNode(inorder,right_is, right_ie,
                postorder, right_ps, right_pe);
        return treeNode;
    }



}
