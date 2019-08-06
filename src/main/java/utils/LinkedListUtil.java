package utils;

import java.util.Arrays;
import java.util.Random;

/**
 * @Class: LinkedListUtil
 * @Description: TODO
 * @Author: Minsky
 * @Date: 2019/8/1 16:50
 * @Version: v1.0
 */
public class LinkedListUtil {


    /**
     * 生成顺序值的 len 长度的单向链表
     * @param len
     */
    public static ListNode genLinkedList(int len){

        if(len < 1) return null;
        ListNode head = new ListNode(0);
        ListNode cursor = head;
        Random random = new Random();
        for(int i = 0 ; i < len ; i++){
            if(i == 0 ){
                cursor.val = i+1;
            }else{
                cursor.next = new ListNode(random.nextInt(len));
                cursor = cursor.next;
            }
        }
        return head;
    }

    /**
     * 顺序打印链表元素
     * @param nodes
     */
    public static void showLinkedList(ListNode nodes){
        if(null == nodes) return ;

        while(nodes != null){
            System.out.print(nodes.val + "->");
            nodes = nodes.next;
        }
        System.out.println("null");
    }


    /**
     * 生成有序链表
     * @param n
     */
    public static ListNode genSortedLinkedList(int n){
        int[] arr = ArrayUtil.randValueArray(n);

        Arrays.sort(arr);

        return genLinkedListFromArray(arr);
    }


    /**
     * 根据数组生成链表
     * @param arr
     * @return
     */
    public static ListNode genLinkedListFromArray(int[] arr){
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for(int i : arr){
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        return head.next;
    }
}
