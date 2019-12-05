package leetCode;

import dataStruct.TreeNode;

import java.util.Stack;

/**
 * @Class: IsSameTree
 * @Description:
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


}
