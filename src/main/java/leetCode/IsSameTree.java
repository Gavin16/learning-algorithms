package leetCode;

import dataStruct.TreeNode;

import java.util.Stack;

/**
 * @Class: IsSameTree
 * @Description:
 *
 * 《LeetCode 100. 相同的树》 标签: 深度优先搜索
 *  给定两个二叉树，编写一个函数来检验它们是否相同。
 *
 *  如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 *
 *  示例 1:
 *
 *  输入:       1         1
 *            / \       / \
 *           2   3     2   3
 *
 *         [1,2,3],   [1,2,3]
 *  输出: true
 *
 * @Author: Minsky
 * @Date: 2019/8/14 20:16
 * @Version: v1.0
 */
public class IsSameTree {

    public static void main(String[]args){
        TreeNode rt1 = new TreeNode(1);
        TreeNode rt2 = new TreeNode(1);

        TreeNode l11 = new TreeNode(2);
        TreeNode r11 = new TreeNode(3);


        TreeNode l21 = new TreeNode(2);
        TreeNode r22 = new TreeNode(3);

        rt1.left = l11;
        rt1.right = r11;

        rt2.left = l21;
        rt2.right = r22;

        System.out.println(isSameTree(rt1,rt2));
    }


    /**
     * 循环实现
     * @param p
     * @param q
     * @return
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        // 中序遍历两个 treeNode
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        stack1.push(p);
        stack2.push(q);

        while(stack1.size()>0 || stack2.size() > 0){
            TreeNode np = stack1.pop();
            TreeNode nq = stack2.pop();
            // 值不相等
            if(( np != nq && (np == null || nq == null)) || np.val != nq.val) return false;


            if(np.left != null && nq.left != null){
                stack1.push(np.left);
                stack2.push(nq.left);
            }
            if(np.right != null && nq.right != null){
                stack1.push(np.right);
                stack2.push(nq.right);
            }

            if(stack1.size() < 1 &&(np.left != null || nq.left != null || np.right != null || nq.right != null)){
                return false;
            }
        }

        return true;
    }


    /**
     * 判断p,q 两棵树是否是相同的树： 递归实现
     */
    static boolean isSameTree1(TreeNode p, TreeNode q){
        if(p == null && q == null){
            return true;
        }else if(p == null || q == null){
            return false;
        }else{
            if(p.val == q.val){
                return isSameTree1(p.left, q.left) && isSameTree1(p.right,q.right);
            }else{
                return false;
            }
        }

    }


}
