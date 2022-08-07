package leetcode.byteDance;

import datastruct.TreeNode;
import utils.TreeNodeUtil;

import java.util.*;


/**
 * @className: Day07
 * @description: TODO
 * @version: 1.0
 * @author: minsky
 * @date: 2022/8/4
 */
public class Day07 {


    public static void main(String[] args) {
        Day07 day07 = new Day07();

//        int[] preorder1 = {3,9,20,15,7}, inorder1 = {9,3,15,20,7};
//        int[] preorder2 = {-1}, inorder2 = {-1};
//        int[] preorder3 = {1,3,6,7,9,5,11,4,8}, inorder3 = {6,3,9,7,1,11,4,5,8};

//        TreeNode treeNode1 = day07.buildTree(preorder1, inorder1);
//        TreeNode treeNode2 = day07.buildTree(preorder2, inorder2);
//        TreeNode treeNode3 = day07.buildTree(preorder3, inorder3);
//        TreeNodeUtil.preOrderPrint(treeNode1);
//        TreeNodeUtil.midOrderPrint(treeNode1);
//
//        TreeNodeUtil.preOrderPrint(treeNode2);
//        TreeNodeUtil.midOrderPrint(treeNode2);
//
//        TreeNodeUtil.preOrderPrint(treeNode3);
//        TreeNodeUtil.midOrderPrint(treeNode3);

//        Integer[] root1 = {5,4,8,11,null,13,4,7,2,null,null,null,null,5,1};
//        TreeNode treeNode = TreeNodeUtil.genTreeNodeFromArray(root1);
//        TreeNodeUtil.preOrderPrint(treeNode);


        Integer[] nums = {1,2,5,3,4,null,6};
        TreeNode treeNode = TreeNodeUtil.genTreeNodeFromArray(nums);
        day07.flatten(treeNode);
        TreeNodeUtil.preOrderPrint(treeNode);

        Integer[] nums1 = {1};
        TreeNode treeNode1 = TreeNodeUtil.genTreeNodeFromArray(nums1);
        day07.flatten(treeNode1);
        TreeNodeUtil.preOrderPrint(treeNode1);

        Integer[] nums2 = {};
        TreeNode treeNode2 = TreeNodeUtil.genTreeNodeFromArray(nums2);
        day07.flatten(treeNode2);
        TreeNodeUtil.preOrderPrint(treeNode2);

//        System.out.println(day07.pathSum(treeNode, 22));

    }



    /**
     * 101. 对称二叉树
     * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
     *
     * 输入：root = [1,2,2,3,4,4,3]
     * 输出：true
     *
     * 提示：
     *
     * 树中节点数目在范围 [1, 1000] 内
     * -100 <= Node.val <= 100
     *
     *
     * 使用双节点同时递归判断是否对称
     *
     */
    public boolean isSymmetric(TreeNode root) {
        return check(root.left, root.right);
    }

    private boolean check(TreeNode left, TreeNode right){
        if(left == null && right == null) return true;
        if(left == null || right == null) return false;

        boolean isEq = left.val == right.val;
        return isEq && check(left.left, right.right) && check(left.right, right.left);
    }


    /**
     *
     * 循环遍历实现
     * 使用双端队列保存每层数据
     *
     */
    public boolean isSymmetric1(TreeNode root) {
        if(root.left == null && root.right == null) return true;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root.left);
        deque.addLast(root.right);
        while(!deque.isEmpty()){
            TreeNode left = deque.removeFirst();
            TreeNode right = deque.removeLast();
            if(left == null && right == null) continue;
            if(left == null || right == null || left.val != right.val) return false;
            if(left.val == right.val){
                deque.addFirst(left.right);
                deque.addLast(right.left);
                deque.addFirst(left.left);
                deque.addLast(right.right);
            }
        }
        return true;
    }



    /**
     * 105. 从前序与中序遍历序列构造二叉树
     * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
     *
     * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
     * 输出: [3,9,20,null,null,15,7]
     * 示例 2:
     *
     * 输入: preorder = [-1], inorder = [-1]
     * 输出: [-1]
     *
     * 提示:
     *
     * 1 <= preorder.length <= 3000
     * inorder.length == preorder.length
     * -3000 <= preorder[i], inorder[i] <= 3000
     * preorder 和 inorder 均 无重复 元素
     * inorder 均出现在 preorder
     * preorder 保证 为二叉树的前序遍历序列
     * inorder 保证 为二叉树的中序遍历序列
     *
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root = recursivelyBuild(preorder, 0 , inorder, 0, inorder.length-1);
        return root;
    }

    private TreeNode recursivelyBuild(int[] preorder, int ps, int[] inorder, int sp, int tp) {
        if(sp > tp) return null;
        // 二叉树当前定位节点在中序遍历中出现的位置
        int pos = sp;
        for(; pos <= tp; pos++){
            if(preorder[ps] == inorder[pos]) break;
        }
        TreeNode treeNode = new TreeNode(preorder[ps]);
        TreeNode left = recursivelyBuild(preorder, ps + 1, inorder, sp, pos - 1);
        TreeNode right = recursivelyBuild(preorder, ps + (pos - sp) + 1, inorder, pos + 1, tp);
        treeNode.left = left;
        treeNode.right = right;
        return treeNode;
    }



    /**
     * 113. 路径总和 II
     *
     * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
     * 叶子节点 是指没有子节点的节点。
     *
     * 示例 1:
     * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
     * 输出：[[5,4,11,2],[5,8,4,5]]
     *
     * 示例 2:
     * 输入：root = [1,2,3], targetSum = 5
     * 输出：[]
     *
     * 示例 3：
     * 输入：root = [1,2], targetSum = 0
     * 输出：[]
     *  
     * 提示：
     *
     * 树中节点总数在范围 [0, 5000] 内
     * -1000 <= Node.val <= 1000
     * -1000 <= targetSum <= 1000
     *
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> pathNums = new ArrayList<>();
        searchPathRecursively(root, targetSum, list,pathNums, 0);
        return list;
    }

    /**
     * 深度优先搜索二叉树, 若判断是叶子节点则求和判断是否等于targetSum
     * 若等于targetSum 则将pathNums 复制一份写入到结果列表，并返回
     * 在递归调用返回的结果里面移除当前元素在做返回
     *
     */
    private void searchPathRecursively(TreeNode root, int targetSum, List<List<Integer>> list,
                                       List<Integer> pathNums, int pathSum) {
        if(root == null) return;
        if(root.left == null && root.right == null){
            int sum = pathSum + root.val;
            if(sum == targetSum){
                pathNums.add(root.val);
                list.add(new ArrayList<>(pathNums));
                pathNums.remove(pathNums.size() - 1);
            }
        }else{
            pathNums.add(root.val);
            searchPathRecursively(root.left, targetSum, list, pathNums, pathSum + root.val);
            searchPathRecursively(root.right, targetSum, list, pathNums, pathSum + root.val);
            pathNums.remove(pathNums.size() - 1);
        }
    }

    /**
     * 114. 二叉树展开为链表
     *
     * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
     *
     * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
     *
     * 输入：root = [1,2,5,3,4,null,6]
     * 输出：[1,null,2,null,3,null,4,null,5,null,6]
     *
     * 示例 2：
     *
     * 输入：root = []
     * 输出：[]
     * 示例 3：
     *
     * 输入：root = [0]
     * 输出：[0]
     *
     * 提示：
     *
     * 树中结点数在范围 [0, 2000] 内
     * -100 <= Node.val <= 100
     *
     */
    public void flatten(TreeNode root) {
        dfsFlatten(root);
    }

    /**
     * 左子节点展开
     */
    private TreeNode dfsFlatten(TreeNode root){
        if(root == null) return null;
        if(root.left == null && root.right == null) return root;
        else{
            TreeNode left = dfsFlatten(root.left);
            dfsFlatten(root.right);

            if(left != null){
                TreeNode oldRight = root.right;
                root.right = left;

                TreeNode curr = left;
                while(curr.right != null) curr = curr.right;
                curr.right = oldRight;
            }
            root.left = null;
            return root;
        }
    }
}
