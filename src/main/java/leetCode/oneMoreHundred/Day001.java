package leetCode.oneMoreHundred;

import utils.ArrayUtil;

import java.util.Stack;


/**
 * 283 移动零/155. 最小栈
 * @Date： 2020年6月30日
 * ==============================================================================
 * 《283 移动零》
 * ==============================================================================
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数
 * ==============================================================================
 * 《155. 最小栈》
 * ==============================================================================
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 * pop、top 和 getMin 操作总是在 非空栈 上调用。
 * ==============================================================================
 */
public class Day001 {

    public static void main(String[] args) {
        int[] input = new int[]{0,1,0,3,12};
//        moveZeroes1(input);
        moveZeroes2(input);
        ArrayUtil.showArray(input);

        // test 最小栈
        testMinStack();

    }

    private static void testMinStack() {
        MinStack minStack = new MinStack();

        minStack.push(512);
        minStack.push(-1024);
        minStack.push(-1024);
        minStack.push(512);

        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
    }


    /**
     * @Title: 283 移动零
     * @Version: 版本1： 从前到后遍历数组, 数组中非零元素写入 nums[id] 中
     * @param nums
     */
    public static void moveZeroes1(int[] nums) {
        if(null == nums) return;
        int id = 0;
        for(int n : nums){
            if(n != 0){
                nums[id++] = n;
            }
        }
        for(int k = id ; k < nums.length ; k++){
            nums[k] = 0;
        }
    }

    /**
     * @Title: 283 移动零
     * @Version: 版本2： 双下标在数组中交换元素，一次for循环
     * @param nums
     */
    public static void moveZeroes2(int[] nums) {
        if(null == nums) return;
        for(int i =0 , j = 0 ; i < nums.length ; i++){
            if(nums[i] != 0){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j++] = temp;
            }
        }
    }


    /**
     * @Title: 155. 最小栈
     * @Version: 版本1 使用辅助栈存储数据
     */
    static class MinStack {

        private Stack<Integer> data;
        private Stack<Integer> helper;

        /** initialize your data structure here. */
        public MinStack() {
            this.data = new Stack();
            this.helper = new Stack();
        }

        public void push(int x) {
            data.push(x);
            if(helper.empty() || helper.peek() >= x){
                helper.push(x);
            }
        }

        public void pop() {
            Integer pop = data.pop();
            if(helper.peek() == pop.intValue()){
                helper.pop();
            }
        }

        public int top() {
            return data.peek();
        }

        public int getMin() {
            return helper.peek();
        }
    }


}
