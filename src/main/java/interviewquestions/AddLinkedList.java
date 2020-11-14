package interviewquestions;

/**
 * @Class: AddLinkedList
 * @Description: 计算两个链表元素相加
 * @Author: Minsky
 * @Date: 2019/7/28 11:23
 * @Version: v1.0
 */
public class AddLinkedList {

    public static void main(String[]args){
        ListNode nodes1 = genListNodeNumber(5);
        ListNode nodes2 = genListNodeNumber(9);

        ListNode listNode = addTwoNumbers(nodes1, nodes2);
        showLinkedListNodes(listNode);
    }


    public static void showLinkedListNodes(ListNode nodes){
        while(nodes != null){
            if(nodes.next != null)
                System.out.print(nodes.val + "-->");
            else
                System.out.print(nodes.val);

            nodes = nodes.next;
        }
    }

    /**
     * 生成指定数值的 ListNode 链表
     * @param n
     * @return
     */
    private static ListNode genListNodeNumber(int n) {
        int temp = n;

        ListNode res = new ListNode(0);
        ListNode pos = res;
        do {
            int val = temp % 10;
            pos.val = val;
            temp = temp/10;
            if(temp != 0){
                ListNode next = new ListNode(0);
                pos.next = next;
                pos = pos.next;
            }
        }while(temp != 0);

        return res;
    }

    /**
     * 链表元素采用倒序存储：数字的低位存储在链表的前部
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int carry = 0;
        ListNode res = new ListNode(0);
        ListNode pos = res;


        while(l1 != null || l2 != null || carry != 0){
            int l1val = (l1 != null)? l1.val : 0;
            int l2val = (l2 != null)? l2.val : 0;
            int sum = l1val + l2val + carry;

            carry = sum/10;

            pos.next = new ListNode(sum%10);
            pos = pos.next;

            l1 = (l1 != null)? l1.next : null;
            l2 = (l2 != null)? l2.next : null;

        }

        return res.next;
    }


    static class ListNode{
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
