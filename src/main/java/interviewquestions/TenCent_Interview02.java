package interviewquestions;

import java.util.Stack;

/**
 * @Class: Interview  腾讯面试题(2020年11月13日)
 * @Description: 数组中包含重复数字，实现一个方法去掉数组中重复数字，返回去重后的数组
 * @Author: Minsky
 * @Date: 2020/11/13 20:00
 * @Version: v1.0
 */
public class TenCent_Interview02 {

    public static void main(String[] args) {
        Integer[] test = {1,1,3,2,2,3,5};

        Integer[] method = new TenCent_Interview02().method(test);
        for(Integer n : method){
            System.out.print(n + " ");
        }
        System.out.println();
    }

    /**
     * 使用栈对数据去重
     * @param arr
     * @return
     */
    Integer[] method(Integer[] arr){
        if(null == arr || arr.length <= 1) return arr;
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
