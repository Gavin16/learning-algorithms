package leetCode;

import utils.LinkedListUtil;
import utils.ListNode;

import java.util.*;

/**
 * @ClassName: DeleteDuplicates
 * @CopyRight: 百果科技
 * @Description: 删除链表中重复元素,保留第一个元素
 *
 * I 删除排序链表中的重复元素
 *      * 删除排序链表中的重复元素
 *      * 方法一：构建新的链表： 使用Set保存所有的元素，并使用Set来构建新的链表
 *      *         (1)  使用 TreeSet 来保存每个元素
 *      *         (2)  使用 HashSet 保存并构建新链表前做排序处理
 *      *
 *      * 方法二：不构建新的链表：
 *                (3)
 *                (4) 递归调用
 *
 * II 删除排序链表中的重复元素,一个也不保留
 *
 * @Author: wufangmin
 * @Date: 2019/11/13 10:03
 * @Version: 1.1
 */
public class DeleteDuplicates {

    public static void main(String[] args) {
        ListNode listNode = LinkedListUtil.genSortedLinkedList(7);

        LinkedListUtil.showLinkedList(listNode);

        LinkedListUtil.showLinkedList(deleteDuplicates2(listNode));
    }

    /**
     * 构建新的链表： 使用 TreeSet 来保存每个元素，并构建新的链表
     *
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates1(ListNode head) {
        if(null == head || head.next == null) return head;
        Set<Integer> set = new TreeSet<>();
        ListNode cur = head;
        do{
            set.add(cur.val);
            cur = cur.next;
        }while (cur != null);

        ListNode first = new ListNode(-1),tmp = first;
        for(int ele : set){
            tmp.next = new ListNode(ele);
            tmp = tmp.next;
        }
        tmp.next = null;
        return first.next;
    }

    /**
     * 删除链表中所有含重复值的节点
     * @param head
     * @return
     */
    public static ListNode deleteAllDuplicates1(ListNode head){
        return null;
    }

    /**
     * 使用HashSet 保存数据,并在
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates2(ListNode head) {
        if(null == head || head.next == null) return head;
        HashSet<Integer> set = new HashSet<>();

        ListNode current = head;
        do{
            set.add(current.val);
            current = current.next;
        }while(current != null);

        List<Integer> array = new ArrayList<>(set);
        Collections.sort(array);

        ListNode first = new ListNode(0);
        ListNode cur = head;
        for(int i : array){
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        return head.next;
    }
}
