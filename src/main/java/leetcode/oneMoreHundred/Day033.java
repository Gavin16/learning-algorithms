package leetcode.oneMoreHundred;

import datastruct.ListNode;
import utils.LinkedListUtil;

import java.util.Arrays;

/**
 * 92. 反转链表 II
 *
 * 给你单链表的头指针 head 和两个整数left 和 right ，其中left <= right 。
 * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 *
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 *
 *
 * 221. 最大正方形
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 *
 * 输入：matrix = [
 *                  ["1","0","1","0","0"],
 *                  ["1","0","1","1","1"],
 *                  ["1","1","1","1","1"],
 *                  ["1","0","0","1","0"]
 *               ]
 * 输出：4
 *
 */
public class Day033 {

    public static void main(String[] args) {
        ListNode listNode = LinkedListUtil.genSortedLinkedList(5);
        LinkedListUtil.showLinkedList(listNode);

        ListNode reverse = reverseBetween1(listNode, 2, 5);
        LinkedListUtil.showLinkedList(reverse);


        System.out.println("------------最大正方形-----------");
        int[][] input = {{1,0,1,0,0},{1,0,1,1,1},{1,1,1,1,1},{1,0,0,1,0}};


    }


    /**
     * 92. 反转链表 II
     * 直观解法：
     * ① 找出left ,right 对应位置的节点; 以及left之前 preLeft 和right 之后的的节点 afterRight
     * ② 反转left 到 right 范围内的节点
     * ③ preLeft 指向反转后的头结点，反转后的尾节点指向 afterRight
     *
     * 若 left = 1 ，left 之前就没有节点了，这就需要在left 之前加入一个无效的空节点用来方便操作
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode sentinel = new ListNode(-1);
        sentinel.next = head;
        ListNode preLeft = sentinel,rightNode = head;

        for(int i = 1 ; i < left  ; i++){
            preLeft = preLeft.next;
        }
        ListNode leftNode = preLeft.next;

        for(int j = 1 ; j < right ; j++){
            rightNode = rightNode.next;
        }
        ListNode afterRight = rightNode.next;

        //反转leftNode 开始的节点,反转后任然使用原节点
        rightNode.next = null;
        reverseLinkedList(leftNode);

        preLeft.next = rightNode;
        leftNode.next = afterRight;

        return sentinel.next;
    }

    /**
     * 原地反转链表
     * @param head
     */
    private static void reverseLinkedList(ListNode head) {
        ListNode pre = null, curr = head;
        while(curr != null){
            ListNode tmp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = tmp;
        }
    }


    /**
     * 92. 反转链表 II
     * 使用头插法实现
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public static ListNode reverseBetween1(ListNode head, int left, int right) {
        ListNode sentinel = new ListNode(-1);
        sentinel.next = head;

        ListNode guard = sentinel, pos = head;
        for(int i = 1 ; i < left ; i++){
           guard = guard.next;
           pos = pos.next;
        }
        // 每次遍历时都将该节点作为 guard的下一个节点，并将该节点的next 指针指向guard上一次指向的节点
        for(int i = left + 1; i <= right ; i++){
            ListNode forFirst = pos.next;
            ListNode next = forFirst.next;
            pos.next = next;
            forFirst.next = guard.next;
            guard.next = forFirst;
        }
        return sentinel.next;
    }


    /**
     * 动态规划解法
     * 定义dp[i,j] 为以(i,j)坐标作为右下角能构成全"1" 正方形的做大边长;
     * 则dp[i,j] = 0                                              若 matrix[i][j] = 0
     *           = 1 + Math.min(dp[i-1,j],dp[i,j-1],dp[i-1,j-1])  若 matrix[i][j] = 1
     *
     * dp[i,j] 第0行和第0列可以直接以matrix 的第0行和第0列进行初始化；
     * dp二维表迭代更新值时，可以从左到右更新，也可以从上到下更新
     *
     * @param matrix
     * @return
     */
    public static int maximalSquare(char[][] matrix) {
        int R = matrix.length, C = matrix[0].length;
        int[][] dp = new int[R][C];
        // 初始化DP表
        for(int i = 0 ; i < R ;i++){
            dp[i][0] = matrix[i][0] - '0';
        }
        for(int j = 0 ; j < C ;j++){
            dp[0][j] = matrix[0][j] - '0';
        }
        for(int r = 1 ; r < R ; r++){
            for(int c = 1 ; c < C ; c++){
                if(matrix[r][c] == '0') dp[r][c] = 0;
                else{
                    dp[r][c] = 1 + Math.min(dp[r-1][c],Math.min(dp[r][c-1],dp[r-1][c-1]));
                }
            }
        }
        int max = 0;
        for(int[] row : dp){
            for(int ele : row){
                if(ele > max) max = ele;
            }
        }
        return max*max;
    }
}
