import com.alibaba.fastjson.JSON;
import dataStruct.ListNode;
import tools.dto.PersonDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

//        int[] data = {2,2,3,4};
//        List<List<Integer>> lists = listPermute(data);
//        System.out.println(lists);
//        System.out.println("lists size " + lists.size());

        BigDecimal decimal = new BigDecimal("15");
        BigDecimal bigDecimal = new BigDecimal("3");
        BigDecimal divide = decimal.divide(bigDecimal);
        int i = divide.intValue();

        BigDecimal decimal1 = new BigDecimal(i);


        System.out.println(i);
        System.out.println(divide.compareTo(decimal1) > 0);


        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();


        set1.add(new Integer(1));
        set1.add(new Integer(2));
        set1.add(new Integer(3));

        set2.add(new Integer(1));
        set2.add(new Integer(2));

        set1.remove(set2);

        System.out.println(JSON.toJSONString(set1));

        System.out.println(set1.contains(new Integer(1)));

        List<PersonDto> list = new ArrayList<>();

        list.add(new PersonDto("三三",20));
        list.add(new PersonDto("污物",20));
        list.add(new PersonDto("六六",20));
        list.add(new PersonDto("123",22));
        list.add(new PersonDto("234",22));
        list.add(new PersonDto("435",22));


        Map<Integer, List<PersonDto>> collect = list.stream().collect(Collectors.groupingBy(e -> e.getAge()));

        for(Map.Entry<Integer,List<PersonDto>> entry : collect.entrySet()){
            List<PersonDto> value = entry.getValue();
            for(PersonDto dto : value){
                dto.setAge(dto.getAge() + 2);
            }
        }

        System.out.println(JSON.toJSONString(list));


        BigDecimal totalAmt = new BigDecimal("0");

        for(int k = 0 ; k < 10 ; k++){
            totalAmt = totalAmt.add(new BigDecimal(k));
        }

        System.out.println(totalAmt.intValue());
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
