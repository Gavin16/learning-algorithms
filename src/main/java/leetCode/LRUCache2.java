package leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 哈希表 + 双向链表实现 LRU缓存
 *
 * LRUCache 简化实现
 */
public class LRUCache2 {

    class DLinkNode{
        int val;
        int key;
        DLinkNode prev;
        DLinkNode next;
        DLinkNode(int k, int v){
            val = v;
            key = k;
        }
    }
    int capacity = 0;
    int size = 0;
    Map<Integer, DLinkNode> map = new HashMap<>();
    // 使用首尾哨兵节点减小判断次数
    DLinkNode head = new DLinkNode(-1, -1);
    DLinkNode tail = new DLinkNode(-1, -1);
    public LRUCache2(int capacity) {
        if(capacity <= 0){
            return;
        }
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if(map.containsKey(key)){
            DLinkNode node = map.get(key);
            remove(node);
            add(node);
            return node.val;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        DLinkNode node;
        if(map.containsKey(key)){
            node = map.get(key);
            node.val = value;
            remove(node);
        } else {
            node = new DLinkNode(key, value);
            map.put(key, node);
            size ++;
        }
        add(node);
        if(size > capacity){
            map.remove(tail.prev.key);
            remove(tail.prev);
            size --;
        }
    }

    private void add(DLinkNode node){
        node.next = head.next;
        node.next.prev = node;
        node.prev = head;
        head.next = node;
    }

    private void remove(DLinkNode node){
        DLinkNode prev = node.prev;
        prev.next = node.next;
        node.next.prev = prev;
    }

}

