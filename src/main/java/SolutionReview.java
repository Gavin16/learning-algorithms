import utils.LinkedListUtil;
import utils.ListNode;

/**
 * @ClassName: SolutionReview
 * @CopyRight: wufangmin
 * @Description: 代码题解回顾
 * @Author: wufangmin
 * @Date: 2019/11/28 15:08
 * @Version: 1.0
 */
public class SolutionReview {

    public static void main(String[] args) {
        ListNode listNode = LinkedListUtil.genSortedLinkedList(3);

        LinkedListUtil.showLinkedList(listNode);

        LinkedListUtil.showLinkedList(deleteAllDeplicate1(listNode));
    }

    /**
     *  删除链表中的重复元素: 删除所有重复元素
     *
     *  如：
     *  输入：0->0->1->2->2->3
     *  输出：1->3
     */
    static ListNode delecteAllDuplicated(ListNode head){
        if(null == head || null == head.next) return head;

        if(head.next.val == head.val){
            while(head.next != null && head.next.val == head.val){
                head = head.next;
            }
            head = delecteAllDuplicated(head.next);
        }else{
            head.next = delecteAllDuplicated(head.next);
        }
        return head;
    }

    /**
     * 删除链表中所有重复元素: 循环实现
     * @param head
     * @return
     */
    static ListNode deleteAllDeplicate1(ListNode head){
        if(head == null || head.next == null) return head;

        return null;
    }
}
