package leetcode.byteDance;

import datastruct.ListNode;

import java.util.*;

/**
 * @className: Day08
 * @description:
 * @version: 1.0
 * @author: minsky
 * @date: 2022/8/8
 */
public class Day08 {


    public static void main(String[] args) {
        int[] arr = {7,1,5,3,6,4};
        int[] arr1 = {7};
        int[] arr2 = {7,6,4,3,1};

        Day08 day08 = new Day08();
//        System.out.println(day08.maxProfit(arr));
//        System.out.println(day08.maxProfit(arr1));
//        System.out.println(day08.maxProfit(arr2));

//        LRUCache lruCache = new LRUCache(2);
//        lruCache.put(1,1);
//        lruCache.put(2,2);
//        System.out.println(lruCache.get(1));
//        lruCache.put(3,3);
//        System.out.println(lruCache.get(2));
//        lruCache.put(4,4);
//        System.out.println(lruCache.get(1));
//        System.out.println(lruCache.get(3));
//        System.out.println(lruCache.get(4));


//        System.out.println(day08.reverseWords("the sky is blue"));
        System.out.println(day08.reverseWords("  hello  world  "));
        System.out.println(day08.reverseWords("a  good  example  "));
        System.out.println(day08.reverseWords("a 123 good  example  "));
        System.out.println(day08.reverseWords(" Nice  "));
        System.out.println(day08.reverseWords(" hao123  "));

    }

    /**
     * 121. 买卖股票的最佳时机
     *
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     *
     * 示例 1：
     *
     * 输入：[7,1,5,3,6,4]
     * 输出：5
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
     * 示例 2：
     *
     * 输入：prices = [7,6,4,3,1]
     * 输出：0
     * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
     *
     * 提示：
     *
     * 1 <= prices.length <= 105
     * 0 <= prices[i] <= 104
     *
     */
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) return 0;
        int len = prices.length;
        int[]dp = new int[len];
        dp[0] = 0;
        int min = prices[0];

        for(int i = 1; i < len; i++){
            dp[i] = Math.max(dp[i-1], prices[i] - min);
            if(prices[i] < min) min = prices[i];
        }
        return dp[len-1];
    }


    /**
     * 141. 环形链表
     * 给你一个链表的头节点 head ，判断链表中是否有环。
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
     * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
     *
     * 输入：head = [3,2,0,-4], pos = 1
     * 输出：true
     * 解释：链表中有一个环，其尾部连接到第二个节点。
     *
     * 输入：head = [1,2], pos = 0
     * 输出：true
     * 解释：链表中有一个环，其尾部连接到第一个节点。
     *
     * 输入：head = [1], pos = -1
     * 输出：false
     * 解释：链表中没有环。
     *
     * 提示：
     *
     * 链表中节点的数目范围是 [0, 104]
     * -105 <= Node.val <= 105
     * pos 为 -1 或者链表中的一个 有效索引 。
     *
     * 进阶：你能用 O(1)（即，常量）内存解决此问题吗？
     *
     */
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;
        ListNode slow = head, fast =  head;
        while(fast != null){
            slow = slow.next;
            fast = fast.next;
            fast = (fast != null) ? fast.next : null;
            if(fast == slow) return true;
        }
        return false;
    }

    /**
     * 142. 环形链表 II
     *
     * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     * 不允许修改 链表。
     *
     * 输入：head = [3,2,0,-4], pos = 1
     * 输出：返回索引为 1 的链表节点
     * 解释：链表中有一个环，其尾部连接到第二个节点。
     *
     * 输入：head = [1,2], pos = 0
     * 输出：返回索引为 0 的链表节点
     * 解释：链表中有一个环，其尾部连接到第一个节点。
     *
     * 输入：head = [1], pos = -1
     * 输出：返回 null
     * 解释：链表中没有环。
     *
     * 输入：head = [1,2], pos = 0
     * 输出：返回索引为 0 的链表节点
     * 解释：链表中有一个环，其尾部连接到第一个节点。
     *
     * 提示：
     *
     * 链表中节点的数目范围在范围 [0, 104] 内
     * -105 <= Node.val <= 105
     * pos 的值为 -1 或者链表中的一个有效索引
     *
     */
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) return null;
        ListNode slow = head, fast = head;
        while(fast != null){
            slow = slow.next;
            fast = fast.next;
            fast = (fast != null) ? fast.next : null;
            if(fast == slow) break;
        }
        if(fast == null) return null;

        // 找出环的入口位置
        ListNode curr = head;
        while(curr != slow){
            curr = curr.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 146. LRU 缓存
     *
     * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
     * 实现 LRUCache 类：
     * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
     * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
     * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；
     *      如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
     * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行
     *
     * 示例：
     * 输入
     * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
     * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
     * 输出
     * [null, null, null, 1, null, -1, null, -1, 3, 4]
     *
     * 解释
     * LRUCache lRUCache = new LRUCache(2);
     * lRUCache.put(1, 1); // 缓存是 {1=1}
     * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
     * lRUCache.get(1);    // 返回 1
     * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
     * lRUCache.get(2);    // 返回 -1 (未找到)
     * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
     * lRUCache.get(1);    // 返回 -1 (未找到)
     * lRUCache.get(3);    // 返回 3
     * lRUCache.get(4);    // 返回 4
     *
     * 提示：
     * 1 <= capacity <= 3000
     * 0 <= key <= 10000
     * 0 <= value <= 105
     * 最多调用 2 * 105 次 get 和 put
     *
     *
     *
     */
    static class LRUCache {
        Map<Integer, ListNode> map;
        // head 和 tail 作为哨兵节点,避免频繁的判空
        ListNode head, tail;
        int capacity , size;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            size = 0;
            map = new HashMap<>(capacity);
            head = new ListNode();
            tail = new ListNode();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            ListNode node = map.get(key);
            if(null == node) return -1;
            moveToHead(node);
            return node.val;
        }

        // 从链表中先移除,再添加到链表头部
        private void moveToHead(ListNode node){
            removeNode(node);
            addToHead(node);
        }
        // 从链表中移除节点
        private void removeNode(ListNode node){
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        // 节点直接添加到链表头部
        private void addToHead(ListNode node){
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
            node.prev = head;
        }
        private ListNode removeTail(){
            ListNode prev = tail.prev;
            removeNode(prev);
            return prev;
        }

        public void put(int key, int value) {
            ListNode node = map.get(key);
            if(node == null){
                ListNode newNode = new ListNode(key, value);
                map.put(key, newNode);
                addToHead(newNode);
                size++;
                if(size > capacity){
                    ListNode removed = removeTail();
                    map.remove(removed.key);
                    size--;
                }
            }else{
                node.val =  value;
                moveToHead(node);
            }
        }

        // ListNode 每次更新从head 处插入数据
        // 每次淘汰从tail 处移除数据
        class ListNode{
            int key, val;
            ListNode prev;
            ListNode next;
            ListNode(){}
            ListNode(int key, int val){
                this.key = key;
                this.val = val;
            }
        }
    }

    /**
     * 151. 颠倒字符串中的单词
     *
     * 给你一个字符串 s ，颠倒字符串中 单词 的顺序。
     *
     * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
     * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
     * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
     *
     * 示例 1：
     *
     * 输入：s = "the sky is blue"
     * 输出："blue is sky the"
     * 示例 2：
     *
     * 输入：s = "  hello world  "
     * 输出："world hello"
     * 解释：颠倒后的字符串中不能存在前导空格和尾随空格。
     * 示例 3：
     *
     * 输入：s = "a good   example"
     * 输出："example good a"
     * 解释：如果两个单词间有多余的空格，颠倒后的字符串需要将单词间的空格减少到仅有一个。
     *
     * 提示：
     *
     * 1 <= s.length <= 104
     * s 包含英文大小写字母、数字和空格 ' '
     * s 中 至少存在一个 单词
     *
     * 进阶：如果字符串在你使用的编程语言中是一种可变数据类型，请尝试使用 O(1) 额外空间复杂度的 原地 解法。
     */
    public String reverseWords(String s) {
        int pre = 0, post;
        Deque<String> stack = new LinkedList<>();
        while(pre < s.length()){
            if(s.charAt(pre) != ' '){
                post = pre;
                while(post < s.length() && s.charAt(post) != ' ') post++;
                stack.push(s.substring(pre, post));
                pre = post < s.length() ? post + 1 : post;
            }else{
                pre++;
            }
        }
        // 栈内元素反转拼接成字符串
        List<String> strList = new ArrayList<>();
        while(!stack.isEmpty()){
            strList.add(stack.pop());
        }
        return String.join(" ", strList);
    }

}
