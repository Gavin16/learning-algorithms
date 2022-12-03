package algorithmsContest.charpt1;

import datastruct.ListNode;

/**
 *
 * @className: JosephProblem
 * @description: 约瑟夫问题
 * 问题描述: 有 n 个人, 编号为 1 - n, 按照顺序围城一圈， 从第 1 个人开始报数, 数到 m 的人出列，
 * 再有下一个人开始报数, 数到 m 的人再出列， 依次类推， 知道所有的人都出列， 请依次输出出列人的编号。
 *   输入: 两个整数和n和m, 1 <= m, n <= 100
 *   输出: n个整数，按顺序输出每个出列人的编号
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2022/12/3
 */
public class JosephProblem {

    public static void main(String[] args) {

        JosephProblem instance = new JosephProblem();
//        instance.solution1(10, 3);
//        instance.solution2(10, 3);
        instance.solution3(10,3);
        instance.solution4(10,3);
    }

    /**
     * 使用循环链表实现
     *
     * @param n
     * @param m
     */
    public void solution1(int n, int m){
        // 构建循环列表
        ListNode head = new ListNode(1);
        ListNode now = head, prev;
        for(int i = 2; i <= n; i++){
            ListNode node = new ListNode(i);
            now.next = node;
            now = now.next;
        }
        now.next = head;

        // 打印输出元素
        prev = head; now = head;
        while((n--) > 1){
            for(int i = 1; i < m; i++){
                prev = now;
                now = now.next;
            }
            System.out.printf("%d ", now.val);
            prev.next = now.next;
            now = prev.next;
        }
        System.out.printf("%d%n", now.val);
    }

    /**
     * 使用静态链表实现
     * 定义足够长的Node 数组,使用数组实现链表功能
     *
     * @param n
     * @param m
     */
    public void solution2(int n, int m){
        // 初始化静态Node 列表
        nodes[0] = new JosephProblem.Node();
        nodes[0].next = 1;
        for(int i = 1; i <= n; i++){
            nodes[i] = new JosephProblem.Node(i, i+1);
        }
        nodes[n].next = 1;

        int now = 1, prev = 1;
        while ((n--) > 1){
            for(int k = 1; k < m; k++){
                prev = now;
                now = nodes[now].next;
            }
            System.out.printf("%d ",nodes[now].data);
            nodes[prev].next = nodes[now].next;
            now = nodes[now].next;
        }
        System.out.printf("%d %n", nodes[now].data);
    }
    Node[] nodes = new Node[108];
    class Node{
        int data;
        int next;
        Node(){}
        Node(int data, int next){
            this.data = data;
            this.next = next;
        }
    }


    /**
     * 静态双向莲表示实现
     *
     *
     * @param n
     * @param m
     */
    public void solution3(int n, int m){
        // 初始化双向链表
        ddNodes[0] = new JosephProblem.DDNode(0,-1,1);
        for(int i = 1; i <= n; i++){
            ddNodes[i] = new JosephProblem.DDNode(i,i-1,i+1);
        }
        ddNodes[1].prev = n;
        ddNodes[n].next = 1;
        // 遍历打印输出
        int now = 1, prev = 1;
        while((n--) > 1){
            for(int k = 1; k < m; k++){
                prev = now;
                now = ddNodes[now].next;
            }
            System.out.printf("%d ", ddNodes[now].data);
            int next = ddNodes[now].next;
            ddNodes[prev].next = ddNodes[now].next;
            ddNodes[next].prev = ddNodes[now].prev;
            now = next;
        }
        System.out.printf("%d%n", ddNodes[now].data);
    }
    DDNode[] ddNodes = new DDNode[108];
    class DDNode{ // 双向链表
        int data;
        int prev, next;
        public DDNode(){}
        public DDNode(int data, int prev, int next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * 使用一维数组实现单向链表
     * 一维数组设置下标 i 保存 i+1 的值
     * 对于末尾元素 n 则保存 1
     *
     */
    int[] iNodes = new int[110];
    public void solution4(int n, int m){
        // 初始化一维数组
        for(int i = 0; i <= n; i++) iNodes[i] = i + 1;
        iNodes[n] = 1;

        int now = 1, prev = 1;
        while((n--) > 1){
            for(int i = 1; i < m; i++){
                prev = now;
                now = iNodes[now];
            }
            System.out.printf("%d ", now);
            iNodes[prev] = iNodes[now];
            now = iNodes[prev];
        }
        System.out.printf("%d%n", now);
    }

}
