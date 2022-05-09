package leetcode.oneMoreHundred;

import utils.ArrayUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: Day046
 * @description:
 *
 * 实现LRU 缓存
 * 数据读取/写入 O(1) 时间复杂度
 *
 * (1) 使用双向链表保存数据操作时序
 * (2) 使用HashMap 实现O(1) 时间复杂度数据获取
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2022/5/6
 *
 * 迭代:
 */
public class Day046 {

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(20);

        int[] ints = ArrayUtil.randValueArray(20);
        ArrayUtil.showArray(ints);

        for(int n : ints){
            lruCache.put(n);
        }
        lruCache.showCacheLinkedList();

        int[] ints1 = ArrayUtil.randValueArray(50);
        ArrayUtil.showArray(ints1);
        for(int nn : ints1){
            lruCache.put(nn);
        }
        lruCache.showCacheLinkedList();
    }


    static class LRUCache{

        private int cacheCap = 10_000;

        private Map<Integer,Node> map = new HashMap<>();

        private Node header;

        private Node tail;

        public LRUCache(int cap){
            this.cacheCap = cap;
        }


        public void showCacheLinkedList(){
            Node temp = header;
            while(null != temp){
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
            System.out.println();
        }

        // 从LRU中获取数据
        // 若不存在直接返回
        // 若存在则将当前数据移动到链表头
        public Node get(Integer data){
            Node node = map.get(data);
            if(null != node && node != header){
                Node prev = node.prev;
                Node next = node.next;
                prev.next = next;
                if(null == next) tail = prev;
                else next.prev = prev;

                node.next = header;
                header.prev = node;
                header = node;
            }
            return node;
        }

        // 向LRU cache 中保存数据
        // (1) 判断cache是否为空
        // (2) 判断cache是否存在当前数据，若存在则无需操作(get一下)
        // (3) 若cache不存在该数据,则判断cache是否达到容量上限，
        //         若达到上限则删除链表尾部数据
        //         若未达到上限则向链表头部插入数据

        public void put(Integer data){
            if(null == header && map.size() < cacheCap){
                Node node = new Node(data);
                map.put(data, node);
                header = node;
                tail = node;
                return;
            }

            Node node = get(data);
            if(null != node) return;

            if(map.size() == cacheCap){
                if(null == tail) return; // CACHE_CAP = 0

                Node prev = tail.prev;
                if(null != prev){
                    map.remove(tail.data);
                    prev.next = null;
                    tail = prev;
                }else{
                    header = null;
                    tail = null;
                }
            }

            Node curr = new Node(data);
            curr.next = header;
            if(null != header) header.prev = curr;
            header = curr;
            map.put(data, curr);
        }

        static class Node{
            int data;
            Node prev;
            Node next;

            private Node(int data){
                this.data = data;
            }
        }
    }
}
