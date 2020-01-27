import dataStruct.ListNode;

import java.util.ArrayList;
import java.util.List;

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
//        ListNode listNode = LinkedListUtil.genSortedLinkedList(3);
//
//        LinkedListUtil.showLinkedList(listNode);
//
//        LinkedListUtil.showLinkedList(deleteAllDeplicate1(listNode));

        int[] data = {2,2,3,4};
        List<List<Integer>> lists = listPermute(data);
        System.out.println(lists);
        System.out.println("lists size " + lists.size());
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

    /**
     * 打印输出数组的全排列
     */
//    static void printPermute(int[] data){
//        if(data == null) return ;
//        int len = data.length - 1 , pos = len;
//        dfsPermute(data,pos,len);
//    }


    static List<List<Integer>> list = new ArrayList<>();
    /**
     *  返回数组data的所有全排列
     */
    static List<List<Integer>> listPermute(int[] data){
        addDfsPermute(data,data.length-1,data.length-1);
        return list;
    }

    static void addDfsPermute(int[] data,int k , int n){
        if(k == 0){
            List<Integer> row = new ArrayList<>();
            for(int i = 0; i <= n ; i++){
                row.add(data[i]);
            }
            list.add(row);
        }else{
            for(int j = k ; j >= 0  ; j--){
                swap(data,j,k);
                addDfsPermute(data,k-1,n);
                swap(data,k,j);
            }
        }
    }


    /**
     * dfs 打印全排列
     */
//    static void dfsPermute(int[] data,int k , int n){
//        if(k == 0){
//            for(int i = 0; i <= n ; i++){
//                System.out.print(data[i] + " ");
//            }
//            System.out.println();
//        }else{
//            for(int j = k ; j >= 0  ; j--){
//                swap(data,j,k);
//                dfsPermute(data,k-1,n);
//                swap(data,k,j);
//            }
//        }
//
//    }
    static void swap(int[] data, int k, int l){
        int temp = data[k];
        data[k] = data[l];
        data[l] = temp;
    }

}
