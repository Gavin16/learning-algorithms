package leetCode;

import java.util.Arrays;

/**
 * 《146. LRU缓存机制》
 *
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 *
 * 进阶:
 *
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 * LRUCache cache = new LRUCache( 2 // 缓存容量);
 *
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // 返回  1
 * cache.put(3,3);    // 该操作会使得密钥 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4,4);    // 该操作会使得密钥 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 *
 */
public class LRUCache {


    // 双向链表首尾结点
    private Node head;
    private Node tail;


    private int cap;

    private Node[] tab;

    public LRUCache(int capacity) {
        this.cap = capacity;
        tab = new Node[capacity];
    }

    public int get(int key) {
        Node node = tab[key-1];
        if(null == node) return -1;

        if(node != head){
            Node temp = node.next;
            node.next = head;
            node.pre.next = temp;
            head = node;
            if(head == tail){
                tail = node.pre;
            }
        }
        return node.val;
    }

    public void put(int key, int value) {
        Node curr = tab[key-1];
        if(curr == null){
            curr = new Node(value);
        }else{
            curr.val = value;
        }

        tab[key-1] = curr;
    }

    private class Node{
        int val;
        // hash拉链指针
        Node hNext;
        // 双向链表指针
        Node pre;
        Node next;
        public Node(int val){
            this.val = val;
        }
    }
}
