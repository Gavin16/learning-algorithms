package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 *  O(1) 时间复杂度实现
 *  新查看或者装入数据放置 链表末尾
 *
 *
 */
public class LRUCache3 {


    public static void main(String[] args) {
        LRUCache3 cache3 = new LRUCache3(2);
        cache3.put(2,1);
        cache3.put(1,1);
        cache3.put(2,3);
        cache3.put(4,1);
        System.out.println(cache3.get(1));
        System.out.println(cache3.get(2));
    }


    private int cap;

    private int size = 0;

    Map<Integer,Node> map = new HashMap<>();

    // 使用哨兵要求不与输入数据混淆
    Node head = new Node(-1,-1);
    Node tail = new Node(-1,-1);

    class Node{
        private Node prev;
        private Node next;
        int key;
        int val;

        public Node(int key ,int val){
            this.key = key;
            this.val = val;
        }
    }


    public LRUCache3(int capacity) {
        this.cap = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if(null == node) return -1;
        remove(node);
        addToTail(node);
        return node.val;
    }

    public void put(int key, int value) {
        if(cap <= 0) return;
        Node node = map.get(key);
        if(null == node){
            capCheck();
            Node nNode = new Node(key,value);
            addToTail(nNode);
            map.put(key,nNode);
            size++;
        }else{
            remove(node);
            addToTail(node);
            node.val = value;
        }
    }


    private void remove(Node node){
        Node prev = node.prev;
        prev.next = node.next;
        node.next.prev = prev;
    }

    private void addToTail(Node node){
        node.prev = tail.prev;
        tail.prev.next = node;
        tail.prev = node;
        node.next = tail;
    }


    private void capCheck(){
        if(cap == size){
            // put时有判断cap 要大于0, 这里一定能移除某个元素
            map.remove(head.next.key);
            remove(head.next);
            size--;
        }
    }
}
