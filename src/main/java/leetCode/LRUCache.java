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

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1,1);
        cache.put(2,2);
        System.out.println(cache.get(1));
        cache.put(3,3);
        System.out.println(cache.get(2));
        cache.put(4,4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }


    // 双向链表首尾结点
    private Node head;
    private Node tail;


    private int cap;
    private int size;

    private Node[] tab;

    private int hashCode(int key){
        return  key % cap;
    }

    public LRUCache(int capacity) {
        this.size = 0;
        this.cap = capacity;
        tab = new Node[capacity];
    }

    private Node getNode(int key){
        Node curr = tab[hashCode(key)];
        if(curr == null) return curr;

        Node iter = curr;
        while(iter != null){
            if(iter.key == key){
                break;
            }
            iter = iter.hNext;
        }

        return iter;
    }

    private void addToHead(Node node){
        // get 查询时 head 不为null
        if(head == null){
            head = node;
            tail = node;
            head.pre = null;
            tail.next = null;
        }else if(node != head){
            // node 为非新增节点
            if(node.pre != null || node.next != null){
                node.pre.next = node.next;
                if(node != tail){
                    node.next.pre = node.pre;
                }else{
                    tail = node.pre;
                    tail.next = null;
                }
            }
            node.next = head;
            head.pre = node;
            head = node;
            head.pre = null;
        }
    }

    /**
     *   节点添加至槽位列表首节点，添加逻辑
     *   (1) 若槽位首元素就是待添加节点，则无需添加
     *   (2) 若槽位首元素为空，则直接添加元素
     *   (3) 若槽位首元素不为空，则
     *          ① 从拉链中删除该元素
     *          ② 将该元素添加至拉链头结点
     *
     */
    private void addToSlotHead(int key, Node node){
        Node curr = tab[hashCode(key)];
        if(curr == node){
            return;
        }else if(curr == null){
            tab[hashCode(key)] = node;
        }else{
            deleteFromSlotChain(curr,node);
            // node 添加至拉链首节点
            node.hNext = curr;
            tab[hashCode(key)] = node;
        }
    }

    /**
     *  若node 在拉链中则将其从拉链中移除，若不在则直接返回
     */
    private void deleteFromSlotChain(Node head, Node node){
        if(head == node){
            tab[hashCode(node.key)] = null;
            return;
        }

        Node iter = head , pre = iter;
        while(iter != null){
            if(iter == node){
                break;
            }
            pre = iter;
            iter = iter.hNext;
        }
        if(iter != null){
            pre.hNext = iter.hNext;
        }

    }




    public int get(int key) {
        Node node = getNode(key);
        if(node != null){
            // 调整当前节点到双向链表头部
            addToHead(node);
            // 调整当前节点到拉链头部
            addToSlotHead(key,node);
            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(cap == 0) return;
        Node node = getNode(key);
        if(node == null){
            // 不存在
            Node newPut = new Node(key,value);
            if(size < cap){
                size++;
            }else{
                // 拉链表中删除该节点
                Node head = tab[hashCode(tail.key)];
                deleteFromSlotChain(head,tail);

                // size == cap 双向链表中删除尾结点
                tail = tail.pre;
                if(tail != null){
                    tail.next = null;
                }
            }
            addToHead(newPut);
            addToSlotHead(key,newPut);
        }else{
            // 存在该节点
            node.val = value;
            addToHead(node);
            addToSlotHead(key,node);
        }

    }

    private class Node{
        int key;
        int val;
        // hash拉链指针
        Node hNext;
        // 双向链表指针
        Node pre;
        Node next;
        public Node(int key , int val){
            this.key = key;
            this.val = val;
        }
    }
}
