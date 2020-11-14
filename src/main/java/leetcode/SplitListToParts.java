package leetcode;

import datastruct.ListNode;
import utils.LinkedListUtil;


public class SplitListToParts {

    public static void main(String[] args) {
        ListNode listNode = LinkedListUtil.genLinkedList(4);

        LinkedListUtil.showLinkedList(listNode);

        ListNode[] result = splitListToParts(listNode, 5);
        for(ListNode node : result){
            System.out.print(null == node ? "null" : node.val + " ");
        }
    }

    static ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] nodes = new ListNode[k];
        if(null == root){
            for(int i = 0 ; i < k ; i++){
                nodes[i] = null;
            }
            return nodes;
        }else{
            // 统计链表中节点个数
            ListNode curr = root;
            int cnt = 0;
            while(curr != null){
                curr = curr.next;
                cnt++;
            }
            // 计算链表中数值的长度
            int[] sizes = genElements(cnt,k);
            ListNode temp,start = root,pre = null;
            for(int t = 0 ; t < sizes.length ; t++){
                if(sizes[t] > 0){
                    int c = 0 ;
                    temp = start;
                    nodes[t] = temp;
                    while(c < sizes[t]){
                        pre = temp;
                        temp = temp.next;
                        c++;
                    }
                    pre.next = null;
                    start = temp;
                }else{
                    nodes[t] = null;
                }
            }
            return nodes;
        }
    }

    private static int[] genElements(int cnt, int k) {
        int[] res = new int[k];
        int div = cnt / k ;
        int mod = cnt % k;
        for(int i = 0 ; i < k ; i++){
            res[i] += div;
            if(i < mod){
                res[i] += 1;
            }
        }
        return res;
    }
}
