package leetcode.oneMoreHundred;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import utils.ArrayUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;

/**
 *
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
 *
 * @author Administrator
 */
public class Day024{


    public static void main(String[] args) {

//        int[] nums = {1,3,-1,-3,5,3,6,7};
//        int k = 3 ;
//        3,3,5,5,6,7
        int[] nums = {9,11};
        int k = 1;


        Day024 day024 = new Day024();
        int[] ints = day024.maxSlidingWindow(nums, k);
        ArrayUtil.showArray(ints);
    }

    /**
     * 滑动窗解法:
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
}
