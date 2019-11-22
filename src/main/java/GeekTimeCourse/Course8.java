package GeekTimeCourse;

/**
 * @ClassName: Course8
 * @CopyRight: wufangmin
 * @Author: wufangmin
 * @Date: 2019/11/22 16:08
 * @Version: 1.0
 * @Description: 栈的原理及使用
 *
 *  网友推荐 LeetCode 20,155,232,844,224,682,496 几个题作为练习; 844,224,682,496 待添加
 *
 */
public class Course8 {

    public static void main(String[] args) {

    }


    /**
     * 《LeetCode 20》
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * 有效字符串需满足：
     *
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     *
     * 示例 1:
     *
     * 输入: "()"
     * 输出: true
     * 示例 2:
     *
     * 输入: "()[]{}"
     * 输出: true
     */
    static boolean isValid(String s) {
        return false;
    }

    /**
     *
     * 《LeetCode 155》
     * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
     *
     * push(x) -- 将元素 x 推入栈中。
     * pop() -- 删除栈顶的元素。
     * top() -- 获取栈顶元素。
     * getMin() -- 检索栈中的最小元素。
     * 示例:
     *
     * MinStack minStack = new MinStack();
     * minStack.push(-2);
     * minStack.push(0);
     * minStack.push(-3);
     * minStack.getMin();   --> 返回 -3.
     * minStack.pop();
     * minStack.top();      --> 返回 0.
     * minStack.getMin();   --> 返回 -2.
     *
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


    /**
     * 《LeetCode 232》
     * 使用栈实现队列的下列操作：
     *
     * push(x) -- 将一个元素放入队列的尾部。
     * pop() -- 从队列首部移除元素。
     * peek() -- 返回队列首部的元素。
     * empty() -- 返回队列是否为空。
     * 示例:
     *
     * MyQueue queue = new MyQueue();
     *
     * queue.push(1);
     * queue.push(2);
     * queue.peek();  // 返回 1
     * queue.pop();   // 返回 1
     * queue.empty(); // 返回 false
     * 说明:
     *
     * 你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
     * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
     * 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
     *
     */
    class MyQueue {

        /** Initialize your data structure here. */
        public MyQueue() {

        }

        /** Push element x to the back of queue. */
        public void push(int x) {

        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            return -1;
        }

        /** Get the front element. */
        public int peek() {
            return -1;
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return false;
        }
    }


}
