package leetcode.depthFirstSearch;

import datastruct.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 《199. 二叉树的右视图》 树，深度优先搜索，广度优先搜索
 *
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例:
 *
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *
 */
public class RightSideView {

    public static void main(String[] args) {

    }

    /**
     *  深度优先搜索解法
     *
     */
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(null == root) return list;
        dfsFindRigthView(root,list,0);
        return list;
    }


    static void dfsFindRigthView(TreeNode root, List<Integer> list,int depth){
        if(depth == list.size()){
            list.add(root.val);
        }

        if(root.right != null){
            dfsFindRigthView(root.right,list,depth+1);
        }else if(root.left != null){
            dfsFindRigthView(root.left,list,depth+1);
        }
    }

}
