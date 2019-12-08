package leetCode;

import dataStruct.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 《257. 二叉树的所有路径》 标签：深度优先搜索
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 输入:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * 输出: ["1->2->5", "1->3"]
 *
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 *
 */
public class BinaryTreePaths {

    public static void main(String[] args) {


    }

    static List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        if(root == null) return list;

        String rStr = root.val + "";
        if(root.left != null || root.right != null){
            if(root.left != null){
                recursiveAddPath(list,rStr,root.left);
            }
            if(root.right != null){
                recursiveAddPath(list,rStr,root.right);
            }
        }else{
            list.add(rStr);
        }
        return list;
    }

    private static void recursiveAddPath(List<String> list, String rStr, TreeNode node) {
        String currStr = rStr + "->"+ node.val;
        if(node.left == null && node.right == null){
            list.add(currStr);
        }else if(node.left != null){
            recursiveAddPath(list,currStr,node.left);
        }else{
            recursiveAddPath(list,currStr,node.right);
        }
    }
}
