package leetCode;

import utils.LinkedListUtil;
import dataStruct.ListNode;

import java.util.*;

/**
 * @ClassName: DeleteDuplicates
 * @CopyRight: wufangmin
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
 *       方法一： 将元素及对应计数保存到HashMap中，取出所有计数为1的元素排序，排序后构建链表
 *       方法二： 递归实现
 * @Author: wufangmin
 * @Date: 2019/11/13 10:03
 * @Version: 1.1
 */
public class DeleteDuplicates {

    public static void main(String[] args) {
        ListNode listNode = LinkedListUtil.genSortedLinkedList(12);

        LinkedListUtil.showLinkedList(listNode);

        LinkedListUtil.showLinkedList(deleteDuplicates3(listNode));
    }

    /************************** 删除排序链表中的重复元素,保留第一个 ****************************/

    /**
     * 构建新的链表： 使用 TreeSet 来保存每个元素，并构建新的链表
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
     * 构建新的链表：使用 LinkedHashSet 保存数据
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates2(ListNode head) {
        if(null == head || head.next == null) return head;
        Set<Integer> set = new LinkedHashSet<>();

        ListNode current = head;
        do{
            set.add(current.val);
            current = current.next;
        }while(current != null);

        List<Integer> array = new ArrayList<Integer>(set);

        ListNode cur = new ListNode(0),first = cur;
        for(int i : array){
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        return first.next;
    }


    /**
     * 递归删除重复的元素,保留第一个元素
     *
     * 递归改写思路：
     * 对从头到尾的每一个元素,都可以按照以下方式去判断
     * 若当前元素与下一个元素不同，则当前元素指向下一个元素的调用
     * 若当前元素与下一个元素相同，则当前元素循环获取相同元素中的最后一个,拿到最后一个元素后让该元素指向下一个元素的调用
     *
     * 这样"递"到边界条件  head.next == null  "归" 返回得到的就是最终结果
     *
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates3(ListNode head){
        if(null == head || head.next == null) return head;

        ListNode next = head.next;

        if(next != null && head.val == next.val){
            while(next != null && head.val == next.val){
                head = head.next;
                next = next.next;
            }
            head = deleteDuplicates3(head);
        }else{
            head.next = deleteDuplicates3(next);
        }

        return head;
    }







    /************************** 删除排序链表中的重复元素,一个也不保留 ****************************/


    /**
     * 删除排序链表中的重复元素,一个也不保留
     * 将元素及对应计数保存到Set中，取出所有计数为1的元素排序，排序后构建链表
     *
     * @param head
     * @return
     */
    public static ListNode deleteAllDuplicates1(ListNode head){
        if(null == head || head.next == null) return head;
        // ListNode 元素分开存 set1,set2
        // 或者直接使用 HashMap 取value 等于1的所以key构建链表
        Set<Integer> set1 = new LinkedHashSet<>();
        Set<Integer> set2 = new LinkedHashSet<>();

        ListNode cur = head;
        while(cur != null){
            Integer valKey = cur.val;
            if(!set1.contains(valKey)){
                set1.add(valKey);
            }else{
                set2.add(valKey);
            }
            cur = cur.next;
        }

        // set1 - set2 得到的便是只出现一次的元素
        set1.removeAll(set2);

        // set 转 ListNode
        ListNode first = new ListNode(0),tmp = first;
        for(Integer ele : set1){
            tmp.next = new ListNode(ele);
            tmp = tmp.next;
        }
        return first.next;
    }

    /**
     * 删除排序链表中的重复元素,一个也不保留
     * 递归删除
     * @param head
     * @return
     */
    public static ListNode deleteAllDuplicates2(ListNode head){
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        if (head.val == next.val) {
            while (next != null && head.val == next.val) {
                next = next.next;
            }
            head = deleteAllDuplicates2(next);
        } else {
            head.next = deleteAllDuplicates2(next);
        }
        return head;
    }

}
