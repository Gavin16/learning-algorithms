package leetCode.oneMoreHundred;

import dataStruct.TreeNode;
import utils.TreeNodeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Date: 2020年8月15日
 * ==============================================================================
 * 461. 汉明距离
 * ==============================================================================
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 *
 * 给出两个整 x 和 y，计算它们之间的汉明距离。
 * 注意：
 * 0 ≤ x, y < 231.
 * 示例:
 *
 * 输入: x = 1, y = 4
 *
 * 输出: 2
 *
 * 解释:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 *
 * 上面的箭头指出了对应二进制位不同的位置。
 * ==============================================================================
 * 94. 二叉树的中序遍历
 * ==============================================================================
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * ==============================================================================
 * 79. 单词搜索
 * ==============================================================================
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 *  
 *
 * 示例:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 *  
 *
 * 提示：
 *
 * board 和 word 中只包含大写和小写英文字母。
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 * ==============================================================================
 */
public class Day017 {


    public static void main(String[] args) {
        System.out.println(new Day017().hammingDistance(0,0));
        int[] arr = {1,2,3,4,5,6};
        TreeNode treeNode = TreeNodeUtil.genTreeNodeFromArray(arr);
        TreeNodeUtil.midOrderPrint(treeNode);
        List<Integer> list = new Day017().inorderTraversal2(treeNode);
        System.out.println(list);

        //79. 单词搜索
        char[][] test = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        char[][] test1 = {
                {'A','B','C','E'},
                {'S','F','E','S'},
                {'A','D','E','E'}};
        
        System.out.println(new Day017().exist(test,"ABCCED"));
        System.out.println(new Day017().exist(test,"SEE"));
        System.out.println(new Day017().exist(test,"ABCB"));
        System.out.println(new Day017().exist(test1,"ABCESEEEFS"));


    }

    /**
     * @Title: 461. 汉明距离
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance(int x, int y) {
        int dist = 0;
        while(x != 0 || y != 0){
            if((x & 1) != (y & 1)){
                dist++;
            }
            x = x >>> 1;
            y = y >>> 1;
        }
        return dist;
    }




    /**
     * @Title: 94. 二叉树的中序遍历
     * @Version: 递归实现
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        traverse(root);
        return list;
    }
    private List<Integer> list = new ArrayList<>();
    /**
     * @param node
     */
    private void traverse(TreeNode node){
        if(node == null){
            return;
        }
        traverse(node.left);
        list.add(node.val);
        traverse(node.right);
    }


    /**
     * @Title 94. 二叉树的中序遍历
     * @Version: 栈循环实现
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while(curr != null || !stack.isEmpty()){
            if(curr != null){
                stack.push(curr);
                curr = curr.left;
            }else{
                curr = stack.pop();
                result.add(curr.val);
                curr = curr.right;
            }
        }
        return result;
    }


    /**
     * @title: 94. 二叉树的中序遍历
     *
     * 栈+循环实现重写
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal3(TreeNode root) {
        TreeNode curr = root;
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while(curr != null || !stack.isEmpty()){
            if(curr != null){
                stack.push(curr);
                curr = curr.left;
            }else{
                TreeNode pop = stack.pop();
                list.add(pop.val);
                curr = curr.right;
            }
        }
        return list;
    }


    /**
     * @Title: 79. 单词搜索
     * @Version: 回溯算法实现
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        boolean res = false;
        int rNum = board.length;
        int cNum = board[0].length;
        for(int i = 0 ; i < rNum; i++){
            for(int j = 0 ; j < cNum ; j++){
                if(board[i][j] == word.charAt(0)){
                    boolean[][]memo = new boolean[rNum][cNum];
                    res = res || dfs(board,memo,i,j,word,0);
                }
            }
        }
        return res;
    }

    private boolean dfs(char[][] board,boolean[][] memo, int i, int j, String word, int k) {
        if(i>= 0 && j >= 0 && i < board.length && j < board[0].length && !memo[i][j]){
            if(board[i][j] == word.charAt(k)){
                memo[i][j] = true;
                if(k == word.length()-1){
                    return true;
                }else{
                    boolean next = dfs(board,memo, i+1, j, word, k+1)
                                    || dfs(board,memo, i-1, j, word, k+1)
                                    || dfs(board,memo,i,j+1,word,k+1)
                                    || dfs(board,memo,i,j-1,word,k+1);
                    if(!next){
                        memo[i][j] = false;
                    }
                    return next;
                }
            }
        }
        return false;
    }



}