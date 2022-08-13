package leetcode.byteDance;

import datastruct.BSTNode;
import datastruct.ListNode;
import datastruct.TreeNode;
import utils.LinkedListUtil;
import utils.TreeNodeUtil;

import java.util.*;

/**
 * @className: Day10
 * @description: TODO
 * @version: 1.0
 * @author: minsky
 * @date: 2022/8/12
 */
public class Day10 {


    public static void main(String[] args) {
        Day10 day10 = new Day10();

        int[] nums1 = {1,2,3,4,5};
        int[] nums2 = {1,2};
        int[] nums3 = {1};
        int[] nums4 = {1,2,3};
        int[] nums5 = {1,2,3,4,5};
//        ListNode listNode1 = LinkedListUtil.genLinkedListFromArray(nums1);
//        ListNode listNode2 = LinkedListUtil.genLinkedListFromArray(nums2);
//        ListNode listNode3 = LinkedListUtil.genLinkedListFromArray(nums3);
//        ListNode listNode4 = LinkedListUtil.genLinkedListFromArray(nums4);
//        ListNode listNode5 = LinkedListUtil.genLinkedListFromArray(nums5);
//
//        LinkedListUtil.showLinkedList(day10.reverseBetween(listNode1,3,4));
//        LinkedListUtil.showLinkedList(day10.reverseBetween(listNode2,1,2));
//        LinkedListUtil.showLinkedList(day10.reverseBetween(listNode3,1,1));
//        LinkedListUtil.showLinkedList(day10.reverseBetween(listNode4,2,3));
//        LinkedListUtil.showLinkedList(day10.reverseBetween(listNode5,1,1));

        int[] arr = {2,1,3};
        int[] arr2 = {5,1,4,3,6};
        Integer[] arr3 = {5,4,6,null,null,3,7};
        BSTNode bstNode = TreeNodeUtil.genBinSearchTreeFromArray(arr);
        BSTNode bstNode2 = TreeNodeUtil.genBinSearchTreeFromArray(arr2);
        TreeNode treeNode = TreeNodeUtil.genTreeNodeFromArray(arr2);
        TreeNode treeNode3 = TreeNodeUtil.genTreeNodeFromArray(arr3);
        System.out.println(day10.isValidBST(bstNode));
        System.out.println(day10.isValidBST(bstNode2));
        System.out.println(day10.isValidBST(treeNode));
        System.out.println(day10.isValidBST(treeNode3));
    }

    /**
     * 92. 反转链表 II
     * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。
     * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
     *
     * 输入：head = [1,2,3,4,5], left = 2, right = 4
     * 输出：[1,4,3,2,5]
     * 示例 2：
     *
     * 输入：head = [5], left = 1, right = 1
     * 输出：[5]
     *
     * 提示：
     *
     * 链表中节点数目为 n
     * 1 <= n <= 500
     * -500 <= Node.val <= 500
     * 1 <= left <= right <= n
     *
     * 进阶： 你可以使用一趟扫描完成反转吗？
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(left == right) return head;
        ListNode pre = null, curr = head;
        int pos = 1;
        while(curr != null){
            if(pos >= left){
                ListNode inPre = null, inStart = curr;
                while(pos <= right){
                    ListNode next = curr.next;
                    curr.next = inPre;
                    inPre = curr;
                    curr = next;
                    pos++;
                }
                if(pre == null) head = inPre;
                else pre.next = inPre;
                inStart.next = curr;
                return head;
            }else{
                pre = curr;
                curr = curr.next;
                pos += 1;
            }
        }
        return pre;
    }


    /**
     * 98. 验证二叉搜索树
     *
     * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
     *
     * 有效 二叉搜索树定义如下：
     *
     * 节点的左子树只包含 小于 当前节点的数。
     * 节点的右子树只包含 大于 当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     *
     * 输入：root = [2,1,3]
     * 输出：true
     *
     * 输入：root = [5,1,4,null,null,3,6]
     * 输出：false
     * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
     *
     * 提示：
     * 树中节点数目范围在[1, 104] 内
     * -231 <= Node.val <= 231 - 1
     *
     *
     * DFS判断 只要有一个节点不满足就非二叉搜索树
     */
    public boolean isValidBST(TreeNode root) {
        boolean left =  dfsValidBST(root.left, Long.MIN_VALUE, root.val);
        boolean right = dfsValidBST(root.right, root.val, Long.MAX_VALUE);
        return left && right;
    }

    private boolean dfsValidBST(TreeNode root, long min, long max){
        if(root == null) return true;
        if(root.val < min || root.val > max) return false;
        boolean isLeftValid = dfsValidBST(root.left, min, Math.min(max, root.val));
        boolean isRightValid = dfsValidBST(root.right, Math.max(min, root.val), max);
        return isLeftValid && isRightValid;
    }

    /**
     * 中序遍历解法
     */
    long prev = Long.MIN_VALUE;
    public boolean isValidBST1(TreeNode root) {
        if(root == null) return true;
        if(!isValidBST1(root.left)) return false;
        if(root.val <= prev) return false;
        prev = root.val;
        return  isValidBST1(root.right);
    }

    /**
     * 5. 最长回文子串
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     * 示例 1：
     *
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     * 示例 2：
     *
     * 输入：s = "cbbd"
     * 输出："bb"
     *  
     * 提示：
     *
     * 1 <= s.length <= 1000
     * s 仅由数字和英文字母组成
     *
     */
    public String longestPalindrome(String s) {
        if(s.length() < 2) return s;
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        for(int i = 0 ; i < len ; i++){
            dp[i][i] = true;
        }

        int maxLen = 0, start = 0, end = 0;
        // 根据规则,以列方式遍历dp数组
        for(int j = 1; j < len ; j++){
            for(int i = 0; i < j ; i++){
                // 若 i+1 > j-1 表示起始位置大于终止位置，也就是空串,这里空串默认为回文串
                boolean pre = (i + 1 > j - 1) ? true : dp[i+1][j-1];
                dp[i][j] = pre && (s.charAt(i) == s.charAt(j));
                if(dp[i][j] && (j - i + 1) > maxLen){
                    maxLen = j - i + 1;
                    start = i;
                    end = j;
                }
            }
        }
        return s.substring(start,end+1);
    }


    /**
     * 高性能解法: 中心扩散法
     *
     */
    public String longestPalindrome1(String s) {
        int start = 0;
        int end = 0;
        char[] chars = s.toCharArray();
        for(int i = 0 ; i < chars.length ; i++){
            int len1 = center(chars, i, i);
            int len2 = center(chars, i, i+1);
            int len = Math.max(len1,len2);
            if(len > end - start){
                start = i - (len-1)/2;
                end = i + len/2;
            }
        }
        return s.substring(start,end+1);
    }

    public int center(char[] chars, int left, int right){
        while(left >= 0 && right < chars.length && chars[left] == chars[right]){
            left--;
            right++;
        }
        return right - left - 1;
    }


    /**
     * 15. 三数之和
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     *
     * 注意：答案中不可以包含重复的三元组。
     *
     * 示例 1：
     *
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     * 示例 2：
     *
     * 输入：nums = []
     * 输出：[]
     * 示例 3：
     *
     * 输入：nums = [0]
     * 输出：[]
     *
     * 提示：
     * 0 <= nums.length <= 3000
     * -105 <= nums[i] <= 105
     *
     * 高效解法
     *
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0 ; i < nums.length; i++){
            // 最小的元素大于 0,后面不会有满足条件的，提前返回
            if(nums[i] > 0) return result;
            if(i > 0 && nums[i] == nums[i-1]) continue;
            // 当前第 1 个元素小于 0 且不与前面元素重复;只要nums[i] 的值不重复,另外两个值也不会重复
            int left = i + 1, right = nums.length - 1;
            // left 不能等于 right 否则该元素重复使用
            while(left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if(sum > 0){
                    right--;
                }else if(sum < 0){
                    left++;
                }else{
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 跳过left 和 right的重复元素
                    while(left < right && nums[right] == nums[right-1]) right--;
                    while(left < right && nums[left] == nums[left + 1]) left++;
                    right--; left++;
                }
            }
        }
        return result;
    }


    /**
     * 20. 有效的括号
     *
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     *  
     * 示例 1：
     *
     * 输入：s = "()"
     * 输出：true
     * 示例 2：
     *
     * 输入：s = "()[]{}"
     * 输出：true
     * 示例 3：
     *
     * 输入：s = "(]"
     * 输出：false
     * 示例 4：
     *
     * 输入：s = "([)]"
     * 输出：false
     * 示例 5：
     *
     * 输入：s = "{[]}"
     * 输出：true
     *  
     * 提示：
     *
     * 1 <= s.length <= 104
     * s 仅由括号 '()[]{}' 组成
     */
    public boolean isValid(String s) {
        if(s.length() % 2 != 0) return false;
        Map<Character,Character> map = new HashMap<>();
        map.put(']','[');
        map.put(')','(');
        map.put('}','{');
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(' || s.charAt(i) == '['
                    || s.charAt(i) == '{'){
                stack.push(s.charAt(i));
            }else{
                Character character = map.get(s.charAt(i));
                if(stack.isEmpty() || !stack.pop().equals(character)){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

}
