package leetCode.oneMoreHundred;

import utils.ArrayUtil;


/**
 * 283 移动零/155. 最小栈
 * @Date： 2020年6月30日
 * ==============================================================================
 * 《283 移动零》
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数
 * ==============================================================================
 * 《155. 最小栈》
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
        moveZeroes(input);
        ArrayUtil.showArray(input);
    }


    /**
     * @Title: 283 移动零
     * @Version: 版本1： 从前到后遍历数组, 数组中非零元素写入 nums[id] 中
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
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
     * @Title: 155. 最小栈
     */
    class MinStack {

        /** initialize your data structure here. */
        public MinStack() {

        }

        public void push(int x) {

        }

        public void pop() {

        }

        public int top() {
            return -1;
        }

        public int getMin() {
            return -1;
        }
    }


}
