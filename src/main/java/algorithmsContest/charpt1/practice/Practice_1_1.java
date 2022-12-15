package algorithmsContest.charpt1.practice;

import datastruct.ListNode;
import utils.LinkedListUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: LinkedListPractice
 * @description: 1.1 链表练习
 * @version: 1.0
 * @author: minsky
 * @date: 2022/12/11
 */
public class Practice_1_1 {

    public static void main(String[] args) {
        Practice_1_1 instance = new Practice_1_1();
//        Node node = new Node(1);
//
//        Node node2 = new Node(3);
//        node.next = node2;
//        node2.random = node;
//        node.random = node2;
//
//        Node node1 = instance.copyRandomList(node);
//        showRandNodeList(node1);


        int[] arr = new int[]{1,8,4,5};
        ListNode listNode = LinkedListUtil.genLinkedListFromArray(arr);
        ListNode headA = new ListNode(4);
        headA.next = listNode;
        ListNode headB = new ListNode(5);
        ListNode headBNext = new ListNode(6);
        headB.next = headBNext;
        headBNext.next = listNode;
        ListNode resNode = instance.getIntersectionNode(headA, headB);
        LinkedListUtil.showLinkedList(resNode);
    }

    /**
     * 剑指 Offer 35. 复杂链表的复制
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if(null == head) return null;
        Node sentinel_new = new Node(-1);
        Node now = head;
        Node prev_new = sentinel_new, now_new = null;
        Map<Node,Node> oldToNew = new HashMap<>();
        while(now != null){
            now_new = new Node(now.val);
            prev_new.next = now_new;
            prev_new = now_new;
            oldToNew.put(now, now_new);
            now = now.next;
        }
        // 再次遍历,设置 rand 值
        now = head; now_new = sentinel_new.next;
        while(now != null){
            Node rand = now.random;
            if(null != rand){
                Node rand_new = oldToNew.get(rand);
                now_new.random = rand_new;
            }else{
                now_new.random = null;
            }
            now = now.next;
            now_new = now_new.next;
        }
        return sentinel_new.next;
    }
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public static void showRandNodeList(Node node){
        Node now = node;
        while(now != null){
            Integer randVal =  now.random != null ? now.random.val : null;
            System.out.print("[" + now.val  + ","+ randVal + "]");
            now = now.next;
            if(now != null){
                System.out.print("->");
            }
        }
        System.out.println();
    }

    /**
     * 剑指 Offer II 023. 两个链表的第一个重合节点
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        ListNode routeA = headA, routeB = headB;
        int flagA = 0, flagB = 0;
        while(routeA != null){
            if(routeA.equals(routeB)) return routeA;
            routeA = routeA.next;
            routeB = routeB.next;
            if(routeA == null && flagA == 0){
                routeA = headB;
                flagA += 1;
            }
            if(routeB == null && flagB == 0){
                routeB = headA;
                flagB += 1;
            }
        }
        return null;
    }

    /**
     * 剑指 Offer 18. 删除链表的节点
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode(ListNode head, int val) {
        ListNode sentinel = new ListNode(-1);
        sentinel.next = head;
        ListNode prev = sentinel, now = head;

        while(now != null){
            if(now.val == val){
                prev.next = now.next;
            }else{
                prev = now;
            }
            now = now.next;
        }
        return sentinel.next;
    }
}
