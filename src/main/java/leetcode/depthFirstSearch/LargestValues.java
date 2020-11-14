package leetcode.depthFirstSearch;

import datastruct.TreeNode;
import utils.TreeNodeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 《515. 在每个树行中找最大值》
 * 您需要在二叉树的每一行中找到最大的值。
 *
 * 示例：
 *
 * 输入:
 *           1
 *          / \
 *         3   2
 *        / \   \
 *       5   3   9
 *
 * 输出: [1, 3, 9]
 *
 */
public class LargestValues {

    public static void main(String[] args) {
        TreeNode treeNode = TreeNodeUtil.genRandomBinTree(6);
        TreeNodeUtil.preOrderPrint(treeNode);
        System.out.println();
        TreeNodeUtil.midOrderPrint(treeNode);
        System.out.println();
        System.out.println(largestValues(treeNode));
    }


    /**  */
    private static List<Integer> list = new ArrayList<>();

    public static List<Integer> largestValues(TreeNode root) {
        dfsFindLargestValues(root,0);
        return list;
    }

    private static void dfsFindLargestValues(TreeNode root,int l){
        if(l >= list.size()){
            list.add(root.val);
        }else{
            if(root.val > list.get(l)){
                list.set(l,root.val);
            }
        }

        if(null != root.left) dfsFindLargestValues(root.left,l+1);
        if(null != root.right) dfsFindLargestValues(root.right,l+1);
    }

}
