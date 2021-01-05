package leetcode.oneMoreHundred;

import utils.ArrayUtil;

import java.util.*;

/**
 *  单调栈++
 *  ==============================================================================
 *  239. 滑动窗口最大值
 *  给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 *  返回滑动窗口中的最大值。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * 示例 2：
 *
 * 输入：nums = [1], k = 1
 * 输出：[1]
 *
 *  ==============================================================================
 *  84. 柱状图中最大的矩形
 *  给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 *  求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *  输入: [2,1,5,6,2,3]
 *  输出: 10
 *
 *
 *
 *  ==============================================================================
 *
 * @author Administrator
 */
public class Day024{


    public static void main(String[] args) {

        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3 ;

        Day024 day024 = new Day024();
        int[] ints = day024.maxSlidingWindow(nums, k);
        ArrayUtil.showArray(ints);

        System.out.println("-----------------------");

        int[] nums1 = {2,1,5,6,2,3};
        int[] nums2 = {1};
        int[] nums3 = {1,1};
        System.out.println(day024.largestRectangleArea(nums1));
        System.out.println(day024.largestRectangleArea(nums2));
        System.out.println(day024.largestRectangleArea(nums3));

    }

    /**
     * 239. 滑动窗口最大值
     * 解法:
     *
     * (1)窗口从左向右滑动时,使用双端队列按从大到小保存数组中的元素(保存的下标)
     * (2)向队尾添加元素时，若元素小于队尾元素时向队尾添加元素,若元素大于队尾元素则弹出队尾元素
     * (3)当窗口滑动时，判断队首元素下标是否过期,若过期则移除队首元素
     * (4)每次滑动窗口，队首元素就是窗口中的最大值
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();
        int[] result = new int[nums.length - k + 1];
        int resId = 0;
        for(int t = 0 ; t < nums.length ; t++){
            // 窗左侧位置下标
            int wh = t - k + 1;
            while(!deque.isEmpty() && nums[t] >= nums[deque.peekLast()]){
                deque.removeLast();
            }
            // 当前元素添加到队尾
            deque.addLast(t);
            // 窗口左侧位置大于0 代表有元素从窗口移除
            if(deque.peekFirst() < wh){
                deque.removeFirst();
            }
            if(wh >= 0){
                result[resId++] = nums[deque.peekFirst()];
            }
        }
        return result;
    }


    /**
     * 84. 柱状图中最大的矩形
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int[][] boundaries  = new int[heights.length][2];
        // 单调递增栈
        Stack<List<Integer>> stack = new Stack<>();
        for(int c = 0 ; c < heights.length ; c++){
            while(!stack.isEmpty() && heights[stack.peek().get(0)] > heights[c]){
                // 当前值比栈顶元素中list中的值小
                List<Integer> pop = stack.pop();
                int leftIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size()-1);
                for(int n : pop){
                    boundaries[n][0] = leftIndex;
                    boundaries[n][1] = c;
                }
            }
            // 当前值等于单调栈顶元素值，追加
            if(!stack.isEmpty() && heights[stack.peek().get(0).intValue()] == heights[c]){
                stack.peek().add(c);
            }else{
                List<Integer> list = new ArrayList<>();
                list.add(c);
                stack.push(list);
            }
        }

        // 栈中可能还有剩余元素需要找出它们的左右两侧比他们值小的元素对应下标
        while(!stack.isEmpty()){
            List<Integer> pop = stack.pop();
            int leftIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size()-1);
            for(int m : pop){
                boundaries[m][0] = leftIndex;
                boundaries[m][1] = heights.length;
            }
        }
        // 遍历boundaries 找出每个位置的 左右两侧的相邻更小值
        int maxArea = 0;
        for(int k = 0 ; k < boundaries.length ; k++){
            int area = heights[k] * (boundaries[k][1] ==  boundaries[k][0] ? 1 :(boundaries[k][1] - boundaries[k][0] - 1));
            if(area > maxArea) maxArea = area;
        }
        return maxArea;
    }

}
