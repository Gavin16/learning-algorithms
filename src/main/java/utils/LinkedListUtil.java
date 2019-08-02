package utils;

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
     * 生成随机值的 len 长度的单向链表
     * @param len
     */
    public static ListNode genLinkedList(int len){

        if(len < 1) return null;
        ListNode head = new ListNode(0);
        ListNode cursor = head;
        Random random = new Random();
        for(int i = 0 ; i < len ; i++){
            if(i == 0 ){
                cursor.val = random.nextInt(len);
            }else{
                cursor.next = new ListNode(random.nextInt(len));
                cursor = cursor.next;
            }
        }
        return head;
    }

    public static void showLinkedList(ListNode nodes){
        if(null == nodes) return ;

        while(nodes != null){
            System.out.print(nodes.val + "->");
            nodes = nodes.next;
        }
        System.out.println("null");
    }
}
