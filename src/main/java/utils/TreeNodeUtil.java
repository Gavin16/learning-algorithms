package utils;

import dataStruct.TreeNode;

/**
 * @ClassName: TreeNodeUtil
 * @Description: 二叉树测试工具类
 * @Author: wufangmin
 * @Date: 2019/12/5 12:12
 * @Version: 1.0
 */
public class TreeNodeUtil {

    /**
     * 产生随机的 BinTree
     */
    public static TreeNode genRandomTreeNode(){
        return null;
    }


    public static void main(String[] args) {
        int[] testArr = {2,3,5,4,1,8,3};
        TreeNode res = genTreeNodeFromArray(testArr);
        backOrderPrint(res);
    }

    /**
     * TODO 从数组生成二叉搜索树: 读取数组所有元素生成二叉搜索树
     */
    public static TreeNode genBinSearchTreeFromArray(int[] arr){
        if(null == arr || arr.length == 0) return null;

        TreeNode root = new TreeNode(arr[0]);
        for(int i = 1; i < arr.length ; i++){

        }

        return null;
    }


    /**
     * 从数组生成完全二叉树
     */
    public static TreeNode genTreeNodeFromArray(int[] arr){
        int[] vals = offsetOnePosition(arr);
        // vals 转二叉树
        TreeNode root = null;
        return initTreeNode(root,vals,1);
    }

    /**
     *  递归生成二叉树
     */
    private static TreeNode initTreeNode(TreeNode root, int[] vals, int i) {
        if(i >= vals.length) return null;

        root = new TreeNode(vals[i]);

        if(2*i < vals.length){
            TreeNode left = null;
            root.left = initTreeNode(left,vals,2*i);
        }
        if((2*i + 1) < vals.length){
            TreeNode right = null;
            root.right = initTreeNode(right,vals,2*i + 1);
        }

        return root;
    }

    /**
     * 数组元素向后偏移一个槽位
     */
    private static int[] offsetOnePosition(int[] arr) {
        int[] res = new int[arr.length + 1];
        for(int i = 0 ; i < arr.length ; i++){
            res[i+1] = arr[i];
        }
        return res;
    }

    /** 前序遍历二叉树 */
    public static void preOrderPrint(TreeNode root){
        print(root);
        if(root.left != null){
            preOrderPrint(root.left);
        }
        if(root.right != null){
            preOrderPrint(root.right);
        }
    }
    /** 中序遍历二叉树 */
    public static void midOrderPrint(TreeNode root){
        if(root == null) return ;

        if(root.left != null){
            midOrderPrint(root.left);
        }
        print(root);
        if(root.right != null){
            midOrderPrint(root.right);
        }
    }
    /** 后序遍历二叉树 */
    public static void backOrderPrint(TreeNode root){
        if(root.left != null){
            backOrderPrint(root.left);
        }
        if(root.right != null){
            backOrderPrint(root.right);
        }
        print(root);
    }

    /**
     *  为便于分析
     *  打印输出二叉树图形
     *  形如：
     *         A
     *        / \
     *       B   C
     *      /\   /\
     *     D  E F  G
     *
     * 循环遍历实现
     */
    public static void printTreeGraph(TreeNode root){

    }

    private static void print(TreeNode node){
        System.out.print(node.val + " ");
    }
}
