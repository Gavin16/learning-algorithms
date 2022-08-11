package leetcode.byteDance;

import datastruct.ListNode;
import datastruct.TreeNode;

import java.util.*;

/**
 * @className: Day09
 * @description:
 * @version: 1.0
 * @author: minsky
 * @date: 2022/8/10
 */
public class Day09 {


    public static void main(String[] args) {
//        MinStack minStack = new MinStack();
//        minStack.push(-2);
//        minStack.push(0);
//        minStack.push(-3);
//        System.out.println(minStack.getMin());
//        minStack.pop();
//        System.out.println(minStack.top());
//        System.out.println(minStack.getMin());


        Day09 day09 = new Day09();

        int[] nums1 = {2,2,1,1,1,2,2};
        int[] nums2 = {3,2,3,1};
        int[] nums3 = {5,5};

        System.out.println(day09.majorityElement(nums1));
        System.out.println(day09.majorityElement(nums2));
        System.out.println(day09.majorityElement(nums3));
    }


    /**
     * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
     *
     * 实现 MinStack 类:
     * MinStack() 初始化堆栈对象。
     * void push(int val) 将元素val推入堆栈。
     * void pop() 删除堆栈顶部的元素。
     * int top() 获取堆栈顶部的元素。
     * int getMin() 获取堆栈中的最小元素。
     *
     * 示例 1:
     *
     * 输入：
     * ["MinStack","push","push","push","getMin","pop","top","getMin"]
     * [[],[-2],[0],[-3],[],[],[],[]]
     *
     * 输出：
     * [null,null,null,null,-3,null,0,-2]
     *
     * 解释：
     * MinStack minStack = new MinStack();
     * minStack.push(-2);
     * minStack.push(0);
     * minStack.push(-3);
     * minStack.getMin();   --> 返回 -3.
     * minStack.pop();
     * minStack.top();      --> 返回 0.
     * minStack.getMin();   --> 返回 -2.
     *  
     *
     * 提示：
     *
     * -231 <= val <= 231 - 1
     * pop、top 和 getMin 操作总是在 非空栈 上调用
     * push, pop, top, and getMin最多被调用 3 * 104 次
     *
     *
     * 使用栈 + 链表保存数据
     */
    static class MinStack {

        private Stack<Node> stack;
        private Node head, tail;

        public MinStack() {
            stack = new Stack<>();
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.prev = head;
        }

        public void push(int val) {
            Node node = new Node(val);
            stack.push(node);
            addToList(node);
        }

        public void pop() {
            Node pop = stack.pop();
            removeNode(pop);
        }

        // 将节点添加到指定的位置
        private void addToList(Node newNode){
            Node cur = head.next;
            while(cur != tail && cur.val <= newNode.val) cur = cur.next;
            Node prev = cur.prev;
            prev.next = newNode;
            newNode.next = cur;
            cur.prev = newNode;
            newNode.prev = prev;
        }

        // 在链表中移除节点
        private void removeNode(Node node){
            Node prev = node.prev;
            prev.next = node.next;
            node.next.prev = prev;
        }


        public int top() {
            return stack.peek().val;
        }

        public int getMin() {
            return head.next.val;
        }

        class Node{
            int val;
            Node prev;
            Node next;
            Node(){}
            Node(int val){
                this.val = val;
            }
        }
    }

    /**
     * 优化版本:
     */
    static class MinStack2 {

        Node node;

        public MinStack2() {
        }

        public void push(int val) {
            if(node == null){
                node = new Node(val,val);
            }else{
                node = new Node(val, Math.min(val, node.min), node);
            }
        }

        public void pop() {
            node = node.next;
        }

        public int top() {
            return node.val;
        }

        public int getMin() {
            return node.min;
        }

        class Node{
            int val;
            int min;
            Node next;
            Node(int val, int min){
                this.val = val;
                this.min = min;
            }
            Node(int val, int min, Node next){
                this.val = val;
                this.min = min;
                this.next = next;
            }
        }
    }



    /**
     * 160. 相交链表
     *
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
     * 图示两个链表在节点 c1 开始相交：
     * 题目数据 保证 整个链式结构中不存在环。
     * 注意，函数返回结果后，链表必须 保持其原始结构 。
     *
     * 自定义评测：
     * 评测系统 的输入如下（你设计的程序 不适用 此输入）：
     *
     * intersectVal - 相交的起始节点的值。如果不存在相交节点，这一值为 0
     * listA - 第一个链表
     * listB - 第二个链表
     * skipA - 在 listA 中（从头节点开始）跳到交叉节点的节点数
     * skipB - 在 listB 中（从头节点开始）跳到交叉节点的节点数
     * 评测系统将根据这些输入创建链式数据结构，并将两个头节点 headA 和 headB 传递给你的程序。如果程序能够正确返回相交节点，那么你的解决方案将被 视作正确答案 。
     *
     * 示例 1：
     *
     * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
     * 输出：Intersected at '8'
     * 解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
     * 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,6,1,8,4,5]。
     * 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
     * 示例 2：
     *
     * 输入：intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
     * 输出：Intersected at '2'
     * 解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。
     * 从各自的表头开始算起，链表 A 为 [1,9,1,2,4]，链表 B 为 [3,2,4]。
     * 在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
     * 示例 3：
     *
     * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
     * 输出：null
     * 解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。
     * 由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
     * 这两个链表不相交，因此返回 null 。
     *  
     *
     * 提示：
     *
     * listA 中节点数目为 m
     * listB 中节点数目为 n
     * 1 <= m, n <= 3 * 104
     * 1 <= Node.val <= 105
     * 0 <= skipA <= m
     * 0 <= skipB <= n
     * 如果 listA 和 listB 没有交点，intersectVal 为 0
     * 如果 listA 和 listB 有交点，intersectVal == listA[skipA] == listB[skipB]
     *  
     *
     * 进阶：你能否设计一个时间复杂度 O(m + n) 、仅用 O(1) 内存的解决方案？
     *
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode currA = headA, currB = headB;
        while(currA.next != null || currB.next != null){
            currA = (currA.next != null) ? currA.next : currA;
            currB = (currB.next != null) ? currB.next : currB;
        }
        // 两个链表若没有交点则返回null
        if(currA != currB) return null;
        // 若存在交点,则找出交点位置
        currA = headA;
        currB = headB;
        while(true){
            currA = (currA.next != null) ? currA.next : headB;
            currB = (currB.next != null) ? currB.next : headA;
            if(currA == currB) break;
        }
        return currA;
    }





    /**
     * 169. 多数元素
     * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     *
     * 示例 1：
     *
     * 输入：nums = [3,2,3]
     * 输出：3
     * 示例 2：
     *
     * 输入：nums = [2,2,1,1,1,2,2]
     * 输出：2
     *  
     * 提示：
     * n == nums.length
     * 1 <= n <= 5 * 104
     * -109 <= nums[i] <= 109
     *
     * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
     *
     */
    public int majorityElement(int[] nums) {
        int num = nums[0], count = 1;
        for(int i = 1; i < nums.length; i++){
            if(num == nums[i]){
                count++;
            }else{
                count -= 1;
                if(count < 0){
                    num = nums[i];
                    count = 1;
                }
            }
        }
        return num;
    }


    /**
     * 199. 二叉树的右视图
     *
     * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     *
     * 输入: [1,2,3,null,5,null,4]
     * 输出: [1,3,4]
     * 示例 2:
     *
     * 输入: [1,null,3]
     * 输出: [1,3]
     * 示例 3:
     *
     * 输入: []
     * 输出: []
     *
     * 提示:
     *
     * 二叉树的节点个数的范围是 [0,100]
     * -100 <= Node.val <= 100 
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(null == root) return ans;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while(!queue.isEmpty()){
            int len = queue.size();
            for(int i = 0; i < len; i++){
                TreeNode treeNode = queue.removeFirst();
                if(treeNode.left != null) queue.addLast(treeNode.left);
                if(treeNode.right != null) queue.addLast(treeNode.right);
                if(i == len - 1) ans.add(treeNode.val);
            }
        }
        return ans;
    }

}
