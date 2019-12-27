package leetCode.depthFirstSearch;

import dataStruct.TreeNode;

/**
 * @ClassName: Flatten
 * @Description: 二叉树原地展开为链表
 * @Author: wufangmin
 * @Date: 2019/12/27 10:51
 * @Version: 1.0
 */
public class Flatten {

    public static void main(String[] args) {

    }

    /**
     * 二叉树转链表，适应right指针作为链表的next指针
     * 原地写法
     */
    static void flatten(TreeNode root) {
        if (null == root || (null == root.left && null == root.right)) return;

        if (null == root.left) {
            flatten(root.right);
        } else if (null == root.right) {
            flatten(root.left);
            root.right = root.left;
            root.left = null;
        } else {
            flatten(root.right);
            flatten(root.left);
            TreeNode right = root.right;
            TreeNode left = root.left, lCurr = left;
            while (lCurr.right != null) {
                lCurr = lCurr.right;
            }
            lCurr.right = right;
            root.right = left;
            root.left = null;
        }
    }

    static TreeNode dfsConvert(TreeNode root){
        if(null == root.left && null == root.right) return root;

        if(null == root.left){
            root.right = dfsConvert(root.right);
        }else if(null == root.right){
            root.right = dfsConvert(root.left);
            root.left = null;
        }else{
            TreeNode r = dfsConvert(root.right);
            TreeNode l = dfsConvert(root.left);
            root.right = l;
            while(l.right != null){
                l = l.right;
            }
            l.right = r;
        }
        return root;
    }
}
