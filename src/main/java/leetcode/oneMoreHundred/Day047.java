package leetcode.oneMoreHundred;

import utils.ArrayUtil;

import java.util.*;

/**
 * @className: Day047
 * @description: 栈应用
 *
 * 《739. 每日温度》
 *  给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指在第 i 天之后，才会有更高的温度。
 *  如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 *  示例 1:
 *  输入: temperatures = [73,74,75,71,69,72,76,73]
 *  输出: [1,1,4,2,1,1,0,0]
 *
 *
 * 《316. 去除重复字母》
 *  给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 *
 *  示例 1：
 *  输入：s = "bcabc"
 *  输出："abc"
 *  示例 2：
 *
 *  输入：s = "cbacdcbc"
 *  输出："acdb"
 *
 *
 * 《402. 移掉 K 位数字》
 * 给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。请你以字符串形式返回这个最小的数字。
 *
 *  
 * 示例 1 ：
 *
 * 输入：num = "1432219", k = 3
 * 输出："1219"
 * 解释：移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219 。
 * 示例 2 ：
 *
 * 输入：num = "10200", k = 1
 * 输出："200"
 * 解释：移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 示例 3 ：
 *
 * 输入：num = "10", k = 2
 * 输出："0"
 * 解释：从原数字移除所有的数字，剩余为空就是 0 。
 *
 *
 *《227. 基本计算器 II》
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * 整数除法仅保留整数部分。
 * 你可以假设给定的表达式总是有效的。所有中间结果将在 [-231, 231 - 1] 的范围内。
 *
 * 注意：不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 *
 * 示例 1：
 * 输入：s = "3+2*2"
 * 输出：7
 * 示例 2：
 *
 * 输入：s = " 3/2 "
 * 输出：1
 * 示例 3：
 *
 * 输入：s = " 3+5 / 2 "
 * 输出：5
 *
 *
 * 提示：
 * 1 <= s.length <= 3 * 105
 * s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
 * s 表示一个 有效表达式
 * 表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内
 * 题目数据保证答案是一个 32-bit 整数
 *
 *
 * 《496. 下一个更大元素 I》
 *  nums1 中数字 x 的 下一个更大元素 是指 x 在 nums2 中对应位置 右侧 的 第一个 比 x 大的元素。
 *  给你两个 没有重复元素 的数组 nums1 和 nums2 ，下标从 0 开始计数，其中nums1 是 nums2 的子集。
 *
 *  对于每个 0 <= i < nums1.length ，找出满足 nums1[i] == nums2[j] 的下标 j ，并且在 nums2 确定 nums2[j] 的 下一个更大元素 。如果不存在下一个更大元素，那么本次查询的答案是 -1 。
 *  返回一个长度为 nums1.length 的数组 ans 作为答案，满足 ans[i] 是如上所述的 下一个更大元素 。
 *
 * 示例 1：
 * 输入：nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出：[-1,3,-1]
 * 解释：nums1 中每个值的下一个更大元素如下所述：
 * - 4 ，用加粗斜体标识，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。
 * - 1 ，用加粗斜体标识，nums2 = [1,3,4,2]。下一个更大元素是 3 。
 * - 2 ，用加粗斜体标识，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。
 *
 * 示例 2：
 * 输入：nums1 = [2,4], nums2 = [1,2,3,4].
 * 输出：[3,-1]
 * 解释：nums1 中每个值的下一个更大元素如下所述：
 * - 2 ，用加粗斜体标识，nums2 = [1,2,3,4]。下一个更大元素是 3 。
 * - 4 ，用加粗斜体标识，nums2 = [1,2,3,4]。不存在下一个更大元素，所以答案是 -1 。
 *
 *
 *
 *
 *
 *《503. 下一个更大元素 II》
 * 给定一个循环数组 nums （ nums[nums.length - 1] 的下一个元素是 nums[0] ），返回 nums 中每个元素的 下一个更大元素 。
 * 数字 x 的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1 。
 *
 *  
 *
 * 示例 1:
 * 输入: nums = [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 *
 * 示例 2:
 * 输入: nums = [1,2,3,4,3]
 * 输出: [2,3,4,-1,4]
 *  
 *
 * 提示:
 * 1 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2022/5/7
 */
public class Day047 {

    public static void main(String[] args) {
        Day047 inst = new Day047();
        int[] ints = {73,74,75,71,69,72,76,73};

        int[] res = inst.dailyTemperatures(ints);
        ArrayUtil.showArray(res);


        System.out.println("============= 316. 去除重复字母 ============");
        String str = "abacb";
        String s = inst.removeDuplicateLetters(str);
        System.out.println(s);


        System.out.println("============= 402. 移掉 K 位数字 ============");

        String num = "10200";
        String s1 = inst.removeKdigits(num, 1);
        System.out.println(s1);

        System.out.println("================ 227. 基本计算器 II ==============");
        int calculate = inst.calculate("31 + 12* 2");
        System.out.println(calculate);


        System.out.println("============= 496. 下一个更大元素 I ===============");
        int[] nums1 = {4,1,2};
        int[] nums2 = {1,3,4,2};
        int[] ints1 = inst.nextGreaterElement(nums1, nums2);
        ArrayUtil.showArray(ints1);

        System.out.println("=============== 503. 下一个更大元素 II ================");
        int[] arr1 = {1,2,1};
        int[] arr2 = {1,2,3,4,3};
        int[] arr3 = {3};
        ArrayUtil.showArray(inst.nextGreaterElements(arr1));
        ArrayUtil.showArray(inst.nextGreaterElements(arr2));
        ArrayUtil.showArray(inst.nextGreaterElements(arr3));

    }


    /**
     * 单调栈: 站内元素单调递减,
     *      若出现大于栈顶元素,则执行弹栈和结果更新
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < temperatures.length; i++){
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
                Integer pop = stack.pop();
                res[pop] = i - pop;
            }
            stack.push(i);
        }
        // 最后栈中数据为单调递减,之后没有大于他们的元素
        return res;
    }


    /**
     * 使用单调栈存储字符列表
     *
     * 每来一个字符判断当前字符是否比栈顶小
     *      若小于栈顶元素则进一步判断新来元素之后是否还有和栈顶相同的元素，
     *          若有则抛弃栈顶
     *          否则直接将元素入栈
     */
    public String removeDuplicateLetters(String s) {
        if(s.length() <= 1) return s;

        Deque<Character> stack = new LinkedList<>();
        for(int i = 0; i < s.length(); i++){
            String substring = s.substring(i + 1);
            while (!stack.isEmpty() && s.charAt(i) - stack.peek() < 0){
                Character peek = stack.peek();
                if(substring.contains(String.valueOf(peek))){
                    stack.pop();
                }else{
                    break;
                }
            }
            if(!stack.contains(s.charAt(i))){
                stack.push(s.charAt(i));
            }
        }

        // 从头到位遍历
        StringBuilder sb = new StringBuilder();
        for(Character ch : stack){
            sb.append(ch);
        }
        return sb.reverse().toString();
    }



    /**
     * "1432219" k = 3  -> 1219
     * "10200" k=2 -> 200
     * "10" k=2 -> 0
     *
     * 1 <= k <= num.length <= 105
     * num 仅由若干位数字（0 - 9）组成
     * 除了 0 本身之外，num 不含任何前导零
     *
     * 解题思路:
     * 使用单调栈保存需要保留下来的数字
     * (1) 若移除了所有元素,则返回 0
     * (2) 若栈为空,字符'0'不入栈保留
     * (3) 若字符i比栈顶元素小,则一直弹出栈顶元素，直到k==0或者字符i>栈顶
     * (4) 以上保证了栈中元素为单调递增(除0外),返回前 n - k 个元素即可
     */
    public String removeKdigits(String num, int k) {
        Deque<Character> stack = new LinkedList<>();
        for(char ch : num.toCharArray()){
            while (!stack.isEmpty() && k > 0 && (stack.peek()  - ch > 0)){
                stack.pop();
                k--;
            }
            stack.push(ch);
        }
        // 若 k 还有剩余则将栈顶中 k 个数字去掉;
        // 此时 k > 0 代表移除的元素不够，还需从栈中单调递增元素中进一步移除
        for(int i=0; i < k; i++){
            stack.pop();
        }
        // 从非零部分开始截取
        StringBuilder sb = new StringBuilder();
        for(Character character : stack){
            sb.append(character);
        }
        sb = sb.reverse();
        int nonZero = 0;
        for(; nonZero < sb.length(); nonZero++){
            if('0' != sb.charAt(nonZero)) break;
        }
        String res = sb.substring(nonZero);
        return res.length() > 0 ? res : "0";
    }


    /**
     * 1 <= s.length <= 3 * 105
     * s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
     * s 表示一个 有效表达式
     * 表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内
     * 题目数据保证答案是一个 32-bit 整数
     *
     * 3+2*2
     * 3+5 / 2
     *
     * 1）获取到数字后直接入栈
     *
     * 2）获取都符号判断当前符号是否比栈顶优先级高，
     *        若高于栈顶符号则直接计算结果，并将结果压栈
     *        若与栈顶符号优先级相同或者低于栈顶优先级，则弹出栈顶符号计算完再将结果入栈
     *
     * 3)
     */
    public int calculate(String s) {
        Deque<Character> signStack = new LinkedList<>();
        Deque<Integer> numStack = new LinkedList<>();
        for(int i = 0; i < s.length(); ){
            if(Character.isDigit(s.charAt(i))){
                int num = 0;
                // 读取连续的数字
                for(;i < s.length() && Character.isDigit(s.charAt(i)); i++){
                    num = num * 10 + (s.charAt(i) - '0');
                }
                numStack.push(num);
                continue;
            }else if(s.charAt(i) == '+' || s.charAt(i) == '-'
                                            || s.charAt(i) == '*' || s.charAt(i) == '/'){
                // 符号操作, 若当前符号优先级不高于栈顶，则一直弹栈直到栈空
                while(!signStack.isEmpty() && !priorToLater(s.charAt(i), signStack.peek())){
                    Integer num2 = numStack.pop();
                    Integer num1 = numStack.pop();
                    int operate = operate(num1, num2, signStack.pop());
                    numStack.push(operate);
                }
                signStack.push(s.charAt(i));
            }
            i++;
        }
        // 计算最终结果,此时操作符号栈中符号按优先级递增
        while(!signStack.isEmpty()){
            Integer num2 = numStack.pop();
            Integer num1 = numStack.pop();
            Character opeSign = signStack.pop();
            int result = operate(num1, num2, opeSign);
            numStack.push(result);
        }
        return numStack.pop();
    }

    private int operate(int num1, int num2, char optChar){
        int ret;
        switch (optChar){
            case '+': ret = num1 + num2; break;
            case '-': ret = num1 - num2; break;
            case '*': ret = num1 * num2; break;
            case '/': ret = num1 / num2; break;
            default: ret = 0;
        }
        return ret;
    }

    private boolean priorToLater(char former, char later){
        if((former == '*' || former == '/') &&
                (later == '+' || later == '-')){
            return true;
        }else{
            return false;
        }
    }

    /**
     * nums1 = [4,1,2], nums2 = [1,3,4,2].  ==》 [-1,3,-1]
     * 题设提到: nums1, nums2 中均无重复元素
     *
     * 使用 单调栈 + 哈希表 实现
     *
     * 栈使用单调递减栈，nums2 数组从右向左实现遍历
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> stack = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = nums2.length - 1; i >= 0; i--){
            int num = nums2[i];
            while(!stack.isEmpty() && num > stack.peek()){
                stack.pop();
            }
            map.put(num, stack.isEmpty() ? -1 : stack.peek());
            stack.push(num);
        }
        int[] ret = new int[nums1.length];
        for(int k = 0; k < nums1.length; k++){
            ret[k] = map.get(nums1[k]);
        }
        return ret;
    }



    /**
     * 遍历数组 使用单调递减栈保存数据
     * 若栈中元素个数 > 1, 则再次遍历获取循环遍历中的最小值
     *
     */
    public int[] nextGreaterElements(int[] nums) {
        Deque<Integer> stack = new LinkedList<>();
        int[] ret = new int[nums.length];
        boolean repeat = true;
        int stackBottom = nums.length;
        for(int i = 0; i < nums.length; i++){
            // 若循环遍历时走到了最大值所在位置，则终止第二次遍历
            if(i > stackBottom) break;
            while(!stack.isEmpty() && nums[i] > nums[stack.peek()]){
                ret[stack.peek()] = nums[i];
                stack.pop();
            }
            stack.push(i);
            if(i == nums.length - 1 && stack.size() > 1 && repeat){
                repeat = false;
                i = -1;
                stackBottom = stack.getLast();
            }
        }
        // 栈中仅剩下最大值(一个或者多个)
        while(!stack.isEmpty()){
            ret[stack.pop()] = -1;
        }
        return ret;
    }

}
