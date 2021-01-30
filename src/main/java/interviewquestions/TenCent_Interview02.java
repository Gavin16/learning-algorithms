package interviewquestions;

import utils.ArrayUtil;

import java.util.*;

/**
 * @Class: Interview  腾讯面试题(2020年11月13日)
 * @Description: 数组中包含重复数字，实现一个方法去掉数组中重复数字，返回去重后的数组
 * @Author: Minsky
 * @Date: 2020/11/13 20:00
 * @Version: v1.0
 */
public class TenCent_Interview02 {

    public static void main(String[] args) {
        int[] test = {1,1,3,2,2,3,5};

        TenCent_Interview02 tencent = new TenCent_Interview02();

        Integer[] method = tencent.method(test);
        for(Integer n : method){
            System.out.print(n + " ");
        }
        System.out.println("=====================");
        int[] ints = tencent.removeRepeatNums1(test);
        ArrayUtil.showArray(ints);
        System.out.println("=====================");
        int[] ints1 = tencent.removeRepeatNums2(test);
        ArrayUtil.showArray(ints1);
        System.out.println("=====================");
        int[] ints2 = tencent.removeRepeatNums2(test);
        ArrayUtil.showArray(ints2);

    }


    /**
     * 版本1: 删除数组中的相邻重复元素，保留其中一个
     * @param nums
     * @return 相邻无重复元素的数组
     */
    public int[] removeRepeatNums1(int[] nums){
        Deque<Integer> deque = new LinkedList<>();
        for(int n : nums){
            if(!deque.isEmpty() && n == deque.getLast()){
                continue;
            }else{
                deque.addLast(n);
            }
        }
        int size = deque.size();
        int[] res = new int[size];
        for(int i = 0 ; i < size ;i++){
            res[i] = deque.pop();
        }
        return res;
    }



    /**
     * 版本2: 删除数组中的相邻重复元素，不保留相邻重复元素
     * @param nums
     * @return 相邻无重复元素的数组
     */
    public int[] removeRepeatNums2(int[] nums){
        Deque<Integer> deque = new LinkedList<>();
        boolean isRepeated = false;
        for(int n : nums){
            if(!deque.isEmpty() && n == deque.getLast()){
                isRepeated = true;
                continue;
            }else{
                if(isRepeated){
                    deque.removeLast();
                    if(!deque.isEmpty() && n == deque.getLast()){
                        continue;
                    }
                    isRepeated = false;
                }
                deque.addLast(n);
            }
        }
        if(!deque.isEmpty() && isRepeated) deque.removeLast();

        int size = deque.size();
        int[] res = new int[size];
        for(int i = 0 ; i < size ;i++){
            res[i] = deque.pop();
        }
        return res;
    }


    /**
     * 使用栈对数据去重
     * @param arr
     * @return
     */
    Integer[] method(int[] arr){
        if(null == arr || arr.length <= 1) return null;
        Stack<Integer> stack = new Stack<>();
        boolean repeated = false;
        for(int n : arr){
            if(stack.size() > 0){
                Integer peek = stack.peek();
                if(n == peek){
                    repeated = true;
                    continue;
                }else if(repeated){
                    stack.pop();
                    if(stack.size() > 0 && n == stack.peek()){
                        stack.pop();
                    }else{
                        stack.push(n);
                    }
                    repeated = false;
                }else{
                    stack.push(n);
                }
            }else{
                stack.push(n);
            }
        }
        Integer[] res = new Integer[stack.size()];
        return stack.toArray(res);
    }

}
