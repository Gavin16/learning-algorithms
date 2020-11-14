package leetcode.oneMoreHundred;

import utils.ArrayUtil;

import java.util.*;

/** 20.有效的括号/26.删除排序数组中的重复项
 * @Date: 2020年7月1日
 * ==============================================================================
 * 20. 有效的括号
 * ==============================================================================
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * ==============================================================================
 * 26. 删除排序数组中的重复项
 * ==============================================================================
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * 说明:
 *
 * 为什么返回数值是整数，但输出的答案是数组呢?
 *
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 *
 * 你可以想象内部操作如下:
 *
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 *
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 * ==============================================================================
 *
 */
public class Day002 {


    public static void main(String[] args) {
        System.out.println(isValid("(])"));
        int[] arr = new int[]{1,2,2,2,3,4,5};
        int cnt = removeDuplicates(arr);
        ArrayUtil.showArray(arr);

    }

    /**
     * @Title：  有效的括号
     * @Version: 版本1  利用栈做记录，散列表做映射处理
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        if(null == s || s.length() == 0) return true;
        Stack<String> stack = new Stack<>();
        Map<String,String> bracketPairs = genBracketPairs();

        Set<String> keySet = bracketPairs.keySet();

        for(char c : s.toCharArray()){
            String cstr = String.valueOf(c);
            if(keySet.contains(cstr)){
                if(stack.empty()) return false;
                String value = bracketPairs.get(cstr);
                if(value.equals(stack.peek())){
                    stack.pop();
                }else{
                    return false;
                }
            }else{
                stack.push(cstr);
            }
        }
        return stack.size() == 0 ? true : false;
    }


    private static Map<String, String> genBracketPairs() {
        Map<String,String> map = new HashMap<>();
        map.put(")","(");
        map.put("}","{");
        map.put("]","[");
        return map;
    }


    /**
     * @Titile: 26. 删除排序数组中的重复项
     * @Version: 版本1：双指针
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        if(null == nums || nums.length == 0) return 0;
        int cnt = 1 , prevId = 0;
        for(int k = 1 ; k < nums.length ; k++){
            if(nums[k] == nums[prevId]) continue;
            nums[++prevId] = nums[k];
            cnt++;
        }
        return cnt;
    }



}


