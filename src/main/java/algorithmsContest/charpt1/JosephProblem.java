package algorithmsContest.charpt1;

import datastruct.ListNode;
import utils.ArrayUtil;
import utils.LinkedListUtil;

import java.util.Scanner;

/**
 * 1.1 链表
 *
 * @className: JosephProblem
 * @description: 例1.1 约瑟夫问题
 * 问题描述: 有 n 个人, 编号为 1 - n, 按照顺序围城一圈， 从第 1 个人开始报数, 数到 m 的人出列，
 * 再有下一个人开始报数, 数到 m 的人再出列， 依次类推， 知道所有的人都出列， 请依次输出出列人的编号。
 *   输入: 两个整数和n和m, 1 <= m, n <= 100
 *   输出: n个整数，按顺序输出每个出列人的编号
 *
 * 习题:
 * (1) leetcode: 从尾到头打印链表， 链表中倒数第k个节点， 反转链表， 合并两个有序链表， 复杂链表的复制
 *               两个链表的第一个公共节点， 删除链表中的节点
 * (2) 洛谷: P1160-队列安排
 *
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
//        instance.solution3(10,3);
//        instance.solution4(10,3);

//        ListNode listNode = LinkedListUtil.genSortedLinkedList(5);
//        LinkedListUtil.showLinkedList(listNode);
//        int[] ans = instance.reversePrint(listNode);
//        ArrayUtil.showArray(ans);

        instance.queueArrange();


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


    /************************   练习题  ************************/
    /**
     * 剑指 Offer 06. 从尾到头打印链表
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        if(null == head) return new int[0];
        // 翻转数组，并计数
        ListNode prev = null, now = head;
        int cnt = 0;
        while(now != null){
            ListNode tmp = now.next;
            now.next = prev;
            prev = now;
            now = tmp;
            cnt++;
        }
        // 翻转后的链表存入 ans 数组
        now = prev;
        int[] ans = new int[cnt];
        for(int i = 0; now != null; now = now.next){
            ans[i++] = now.val;
        }
        return ans;
    }

    /**
     * 剑指 Offer 22. 链表中倒数第k个节点
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode front = head, tail = head;
        for(int i = 0; i < k-1; i++){
            front = front.next;
            if(null == front) return null;
        }
        while(front.next != null){
            front = front.next;
            tail = tail.next;
        }
        return tail;
    }


    /**
     * 反转链表
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null, now = head;
        while(now != null){
            ListNode next = now.next;
            now.next = prev;
            prev = now;
            now = next;
        }
        return prev;
    }


    /**
     * 洛谷: P1160-队列安排
     * 地址: https://www.luogu.com.cn/problem/P1160
     *
     * 题目描述
     * 一个学校里老师要将班上 NN 个同学排成一列，同学被编号为 1\sim N1∼N，他采取如下的方法：
     * 先将 11 号同学安排进队列，这时队列中只有他一个人；
     * 2-N2−N 号同学依次入列，编号为 ii 的同学入列方式为：老师指定编号为 ii 的同学站在编号为 1\sim(i-1)1∼(i−1) 中某位同学（即之前已经入列的同学）的左边或右边；
     * 从队列中去掉 M(M<N)M(M<N) 个同学，其他同学位置顺序不变。
     * 在所有同学按照上述方法队列排列完毕后，老师想知道从左到右所有同学的编号。
     *
     * 输入格式
     * 第 1 行为一个正整数 N，表示了有 N 个同学。
     * 第 2∼N行，第 i 行包含两个整数 k,p，其中 k 为小于i的正整数，p为 0或者1。
     * 若 p为0，则表示将i号同学插入到k号同学的左边，p为1则表示插入到右边。
     *
     * 第 N+1 行为一个正整数 M，表示去掉的同学数目。
     * 接下来M行，每行一个正整数x,表示将x号同学从队列中移去，如果x号同学已经不在队列中则忽略这一条指令。
     *
     * 输入:
     * 4
     * 1 0
     * 2 1
     * 1 0
     * 2
     * 3
     * 3
     * 输出:
     * 2 4 1
     *
     * 最后结果控制台打印输出
     */
    public void queueArrange(){
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        QueueNode[] queue = new QueueNode[n+1];
        queue[0] = new QueueNode(-1,1);
        queue[1] = new QueueNode(0,-1);
        // 读取输入初始化队列
        int id = 1;
        while(--n > 0){
            id += 1;
            int targetId = scanner.nextInt();
            int side = scanner.nextInt();
            QueueNode target = queue[targetId];
            // 放target 左边还是右边
            if(side == 0){
                int left = target.l;
                QueueNode leftNode = queue[left];
                leftNode.r = id;
                target.l = id;
                queue[id] = new QueueNode(left, targetId);
            }else{
                int right = target.r;
                if(right > 0){
                    QueueNode rightNode = queue[right];
                    queue[id] = new QueueNode(targetId, right);
                    rightNode.l = id;
                }else{
                    queue[id] = new QueueNode(targetId, right);
                }
                target.r = id;
            }
        }
        // 移除指定位置上的元素
        int rmNum = scanner.nextInt();
        while(rmNum-- > 0){
            Integer rmId = scanner.nextInt();
            if(rmId < 0) continue;
            QueueNode queueNode = queue[rmId];
            if(null != queueNode){
                // 元素存在
                int left = queueNode.l;
                int right = queueNode.r;

                QueueNode leftNode = queue[left];
                leftNode.r = right;
                if(right > 0){
                    QueueNode rightNode = queue[right];
                    rightNode.l = left;
                }
            }
        }
        // 打印输出队列内容
        QueueNode now = queue[0];
        while(now.r > 0){
            System.out.printf("%d ",now.r);
            now = queue[now.r];
        }
    }
    class QueueNode{
        int l, r;
        QueueNode(){}
        QueueNode(int l, int r){
            this.l = l;
            this.r = r;
        }
    }
}
