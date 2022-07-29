package leetcode.byteDance;

import datastruct.TreeNode;
import utils.ArrayUtil;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @className: Day03
 * @description:
 * @version: 1.0
 * @author: minsky
 * @date: 2022/7/29
 */
public class Day03 {

    public static void main(String[] args) {
        Day03 day03 = new Day03();

//        TreeNode root = new TreeNode(3);
//        TreeNode treeNode1 = new TreeNode(5);
//        TreeNode treeNode2 = new TreeNode(1);
//        TreeNode treeNode3 = new TreeNode(6);
//        TreeNode treeNode4 = new TreeNode(2);
//        TreeNode treeNode5 = new TreeNode(0);
//        TreeNode treeNode6 = new TreeNode(8);
//        TreeNode treeNode7 = new TreeNode(7);
//        TreeNode treeNode8 = new TreeNode(4);
//        root.left = treeNode1;
//        root.right = treeNode2;
//        treeNode1.left = treeNode3;
//        treeNode1.right = treeNode4;
//        treeNode4.left = treeNode7;
//        treeNode4.right = treeNode8;
//        treeNode2.left = treeNode5;
//        treeNode2.right = treeNode6;

        TreeNode root = new TreeNode(3);
        TreeNode treeNode1 = new TreeNode(5);
        TreeNode treeNode2 = new TreeNode(1);
        TreeNode treeNode3 = new TreeNode(6);
        TreeNode treeNode4 = new TreeNode(2);
        TreeNode treeNode5 = new TreeNode(0);
        TreeNode treeNode6 = new TreeNode(8);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode8 = new TreeNode(4);
        root.left = treeNode1;
        root.right = treeNode2;
        treeNode1.left = treeNode3;
        treeNode1.right = treeNode4;
        treeNode4.left = treeNode7;
        treeNode4.right = treeNode8;
        treeNode2.left = treeNode5;
        treeNode2.right = treeNode6;


//        TreeNode treeNode = day03.lowestCommonAncestor(root, treeNode1, treeNode8);
//        System.out.println(treeNode.val);

        int[][] matrix1 =  {
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}};
        int target1 = 5;
        int[][] matrix2 = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        int target2 = 20;
//        System.out.println(day03.searchMatrix1(matrix1, target1));
//        System.out.println(day03.searchMatrix1(matrix2, target2));
        String num1 = "1234567899999";
        String num2 = "10000";
        System.out.println(day03.addStrings(num1, num2));


//        MyQueue myQueue = new MyQueue();
//        myQueue.push(1);
//        myQueue.push(2);
//        myQueue.push(3);
//        myQueue.push(4);
//        System.out.println(myQueue.pop());
//        myQueue.push(5);
//        System.out.println(myQueue.pop());
//        System.out.println(myQueue.pop());
//        System.out.println(myQueue.pop());
//        System.out.println(myQueue.pop());


        int[] countArr = new int[10];
        for(int i = 0 ; i < 10000 ; i++){
            int rand = day03.rand10();
            countArr[rand - 1]++;
        }
        ArrayUtil.showArray(countArr);
    }

    /**
     *
     * 236. 二叉树的最近公共祖先
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     *
     * 百度百科中最近公共祖先的定义为：
     *      “对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     *
     * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
     * 输出：3
     * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
     *
     * 提示：
     *
     * 树中节点数目在范围 [2, 105] 内。
     * -109 <= Node.val <= 109
     * 所有 Node.val 互不相同 。
     * p != q
     * p 和 q 均存在于给定的二叉树中。
     *
     *
     * DFS 对于每个节点，若找到了一个节点 p 或者 q 那么就返回该节点
     * 若该节点下
     *
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root != null){
            if(root.val == p.val) return p;
            if(root.val == q.val) return q;

            TreeNode leftRes = lowestCommonAncestor(root.left, p, q);
            TreeNode rightRes = lowestCommonAncestor(root.right, p, q);

            // 没找到最近父节点,但是目标节点就在当前root的左右分支
            if(leftRes != null && rightRes != null){
                if((leftRes.val == p.val || leftRes.val == q.val) &&
                        (rightRes.val == p.val || rightRes.val == q.val)){
                    return root;
                }
            }else if(leftRes != null || rightRes != null){
                // 其中一个目标节点在左分支 或者 右分支
                return leftRes != null ? leftRes : rightRes;
            }
        }
        return null;
    }

    /**
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
     *
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     *
     * 示例1：
     * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
     * 输出：true
     *
     * 提示：
     *
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= n, m <= 300
     * -109 <= matrix[i][j] <= 109
     * 每行的所有元素从左到右升序排列
     * 每列的所有元素从上到下升序排列
     * -109 <= target <= 109
     *
     * 对每一行做二分查找 O( m*log(n) )
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix[0].length;
        for(int[] row : matrix){
            // 二分查找
            int low = 0, high = n - 1;
            while(low <= high){
                int mid = low + (high - low)/2;
                if(row[mid] == target) return true;
                else if(row[mid] < target) low = mid + 1;
                else high = mid - 1;
            }
        }
        return false;
    }

    /**
     * Z 字形查找
     *
     * 从矩阵的右上角或者左下角开始搜索，以左下角为例
     * 左下角的坐标为 (x,y) = (m-1,0)
     * 当判断matrix[x][y] > target 时，x 往减小方向移动
     * 当判断matrix[x][y] < target 时，y 往增大的方向移动
     * 这样只要满足 x >= 0 且 y < n ,那么一直找最后一定能遍历完所有可能元素
     *
     * 时间复杂度 O(m + n)
     */
    public boolean searchMatrix1(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int x = m - 1, y = 0;
        while(x >= 0 && y < n){
            if(matrix[x][y] == target) return true;
            else if(matrix[x][y] < target) y++;
            else x--;
        }
        return false;
    }

    /**
     * 415. 字符串相加
     * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
     *
     * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
     *
     * 示例 1：
     *
     * 输入：num1 = "11", num2 = "123"
     * 输出："134"
     * 示例 2：
     *
     * 输入：num1 = "456", num2 = "77"
     * 输出："533"
     * 示例 3：
     *
     * 输入：num1 = "0", num2 = "0"
     * 输出："0"
     *  
     * 提示：
     *
     * 1 <= num1.length, num2.length <= 104
     * num1 和num2 都只包含数字 0-9
     * num1 和num2 都不包含任何前导零
     *
     */
    public String addStrings(String num1, String num2) {
        // 反转num1 num2
        int i = num1.length() - 1, j = num2.length() - 1,carryBit = 0;
        StringBuilder result = new StringBuilder();
        while(i >= 0 || j >= 0){
            int left = (i >= 0 ) ? num1.charAt(i--) - '0' : 0;
            int right =(j >= 0) ? num2.charAt(j--) - '0' : 0;
            int bitSum = left + right + carryBit;
            if(bitSum >= 10 ) carryBit = 1;
            else carryBit = 0;
            bitSum = bitSum % 10;
            result.append(bitSum);
        }
        if(carryBit > 0) result.append(carryBit);
        return result.reverse().toString();
    }

    /**
     * 232. 用栈实现队列
     * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：
     *
     * 实现 MyQueue 类：
     * void push(int x) 将元素 x 推到队列的末尾
     * int pop() 从队列的开头移除并返回元素
     * int peek() 返回队列开头的元素
     * boolean empty() 如果队列为空，返回 true ；否则，返回 false
     * 说明：
     *
     * 你 只能 使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
     * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
     *  
     *
     * 示例 1：
     *
     * 输入：
     * ["MyQueue", "push", "push", "peek", "pop", "empty"]
     * [[], [1], [2], [], [], []]
     * 输出：
     * [null, null, null, 1, 1, false]
     *
     * 解释：
     * MyQueue myQueue = new MyQueue();
     * myQueue.push(1); // queue is: [1]
     * myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
     * myQueue.peek(); // return 1
     * myQueue.pop(); // return 1, queue is [2]
     * myQueue.empty(); // return false
     *
     * 提示：
     *
     * 1 <= x <= 9
     * 最多调用 100 次 push、pop、peek 和 empty
     * 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）
     *
     * 进阶：
     * 你能否实现每个操作均摊时间复杂度为 O(1) 的队列？换句话说，执行 n 个操作的总时间复杂度为 O(n) ，即使其中一个操作可能花费较长时间。
     *
     */
    static class MyQueue {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        private AtomicInteger size = new AtomicInteger(0);

        public MyQueue() {

        }

        public void push(int x) {
            if(stack1.isEmpty()){
                for(int i = 0 ; i < size.get() ; i++){
                    stack1.push(stack2.pop());
                }
            }
            stack1.push(x);
            size.addAndGet(1);
        }

        public int pop() {
            if(stack1.size() > 0){
                for(int i = 0; i < size.get(); i++){
                    stack2.push(stack1.pop());
                }
            }
            if(size.get() > 0) size.addAndGet(-1);
            return stack2.pop();
        }

        public int peek() {
            if(stack1.isEmpty()){
                return stack2.peek();
            }else{
                for(int i = 0; i < size.get() ; i++){
                    stack2.push(stack1.pop());
                }
                return stack2.peek();
            }
        }

        public boolean empty() {
            return size.get() == 0 ? true : false;
        }
    }


    public int rand7(){
        return new Random().nextInt(7) + 1;
    }

    public int rand10() {
        int idx;
        do{
            int row = rand7();
            int col = rand7();
            idx = col + (row - 1)*7;
        }while (idx > 40);
        return (idx % 10) + 1;
    }
}
