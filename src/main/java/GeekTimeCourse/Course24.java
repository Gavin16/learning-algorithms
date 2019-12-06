package GeekTimeCourse;

import dataStruct.TreeNode;
import utils.TreeNodeUtil;

/**
 * @ClassName: Course24
 * @Description: 求出一棵给定二叉树的确切高度
 * @Author: wufangmin
 * @Date: 2019/12/6 14:41
 * @Version: 1.0
 */
public class Course24 {

    public static void main(String[] args) {
        int[] arr = {2,2,3,5,6,7,3,8,9};
        TreeNode treeNode = TreeNodeUtil.genTreeNodeFromArray(arr);
        System.out.println(findTreeHeight(treeNode));
    }

    /**
     *  求出一棵给定二叉树的确切高度
     */
    static int findTreeHeight(TreeNode root){
        int max = 0;
        if(null != root){
            max = 1;
        }else
            return max;

        int lMax = findTreeHeight(root.left);
        int rMax = findTreeHeight(root.right);
        return max + max(lMax,rMax);
    }

    static int max(int x, int y){
        return x >= y ? x : y;
    }

    /**
     * 二叉搜索树：查,增,删除
     */
    static void insertBinSearchTree(TreeNode root, int val){

    }
    static TreeNode findInBinSearchTree(TreeNode root, int val){
        return null;
    }
    static void deleteInBinSearchTree(TreeNode root, int val){

    }


}
