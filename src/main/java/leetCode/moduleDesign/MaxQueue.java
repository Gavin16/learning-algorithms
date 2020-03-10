package leetCode.moduleDesign;

import java.util.PriorityQueue;

/**
 *
 * 《面试题59 - II. 队列的最大值》
 *
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的时间复杂度都是O(1)。
 *
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 *
 * 示例 1：
 *
 * 输入:
 * ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 * [[],[1],[2],[],[],[]]
 * 输出: [null,null,null,2,1,2]
 * 示例 2：
 *
 * 输入:
 * ["MaxQueue","pop_front","max_value"]
 * [[],[],[]]
 * 输出: [null,-1,-1]
 *
 *
 */
public class MaxQueue {

    public static void main(String[] args) {
        MaxQueue maxQueue = new MaxQueue();
        maxQueue.push_back(1);
        maxQueue.push_back(2);
        System.out.println(maxQueue.max_value());
        System.out.println(maxQueue.pop_front());
        System.out.println(maxQueue.max_value());
    }


    private PriorityQueue<Node> queue;

    // 首尾双哨兵
    private Node head;
    private Node tail;

    public MaxQueue() {
        queue = new PriorityQueue<>((o1,o2)->o2.val - o1.val);
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.pre = head;
    }

    public int max_value() {
        Node peek = queue.peek();
        return peek == null ? -1 : peek.val;
    }

    public void push_back(int value) {
        Node node = new Node(value);
        Node pre = tail.pre;

        pre.next = node;
        node.pre = pre;
        node.next = tail;
        tail.pre = node;
        // 添加优先队列
        queue.add(node);
    }

    public int pop_front() {
        Node target = head.next;
        if(target == tail) return -1;
        Node tNext = target.next;
        head.next = tNext;
        tNext.pre = head;

        // 元素移出优先队列
        queue.remove(target);
        return target.val;
    }

    // 双向链表，方便删除和插入
    class Node{
        int val;
        Node next;
        Node pre;
        public Node(int val){
            this.val = val;
        }
    }
}

