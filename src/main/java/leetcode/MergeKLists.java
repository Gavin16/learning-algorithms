package leetcode;

import datastruct.ListNode;
import utils.LinkedListUtil;

import java.util.*;

/**
 * @Class: MergeKLists
 * @Description:
 *
 * 《GeekTime -- practice day01》
 * 《23. 合并K个排序链表》
 *
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 示例:
 *
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 * @Author: Minsky
 * @Date: 2020/2/1 17:52
 * @Version: v1.0
 */
public class MergeKLists {


    public static void main(String[] args) {

        ListNode listNode1 = LinkedListUtil.genSortedLinkedList(7);
        ListNode listNode2 = LinkedListUtil.genSortedLinkedList(8);
        ListNode listNode3 = LinkedListUtil.genSortedLinkedList(9);

        ListNode[] input = new ListNode[3];
        input[0] = listNode1;
        input[1] = listNode2;
        input[2] = listNode3;

        LinkedListUtil.showLinkedList(listNode1);
        LinkedListUtil.showLinkedList(listNode2);
        LinkedListUtil.showLinkedList(listNode3);

        ListNode listNode = mergeKLists1(input);

        LinkedListUtil.showLinkedList(listNode);
    }


    /**
     * 思路行不通：因为存在譬如某个链表的第二个元素比另一个链表的第一个元素小的情况
     * (××)
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode result = new ListNode(-1);
        int mc , len = lists.length;
        while(true){
            mc = 0;
            for(int i = 0 ; i < len ; i++){
                if(lists[i] != null){
                    result.next = lists[i];
                    lists[i] = lists[i].next;

                    result = result.next;
                    mc++;
                }
            }

            // 合并计数为0 代表已经不存在未合并完的链表了
            if(mc == 0) break;
            // 更新不为null 的数组长度
            len = mc;
            ListNode[] nodes = new ListNode[len];
            for(int  j = 0 ,k = 0 ; j < len ; j++){
                if(lists[j] != null) nodes[k++] = lists[j];
            }

            Arrays.sort(nodes,0,mc,(o1,o2)->{ return o1.val - o2.val; });
            System.arraycopy(nodes,0,lists,0,len);
        }
        return result.next;
    }

    /**
     * 使用大小为 lists.length 的小顶堆，每次移除堆顶元素时将
     */
    public static ListNode mergeKLists1(ListNode[] lists) {
        int len = lists.length;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(len, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        // 链表首节点添加入优先队列
        for(ListNode node : lists){
            queue.add(node);
        }

        ListNode result = new ListNode(-1), curr = result;
        // 移除堆顶元素，取而代之的是该链表元素的下一个元素
        while(queue.size() > 0){
            ListNode remove = queue.remove();
            if(remove.next != null){
                queue.add(remove.next);
            }
            curr.next = remove;
            curr = curr.next;
        }

        return result.next;
    }

}
