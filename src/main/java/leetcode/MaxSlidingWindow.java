package leetcode;

/**
 *  《GeekTime -- practice day02》
 *
 * 《239. 滑动窗口最大值》
 *  给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口中的最大值。
 *
 *  
 *
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 *
 *
 *  提示：
 * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
 *
 * 进阶：
 * 你能在线性时间复杂度内解决此题吗？
 *
 */
public class MaxSlidingWindow {

    /**
     *  近似线性的解法
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        if(len == 0 || k <= 1) return nums;
        int[] res = new int[len- k + 1];

        int maxId = 0;
        for(int i = 0 ; i < len - k + 1 ; i++){
            if(i == 0 || maxId < i){
                // 找出下标从 i 开始的 k长度数组中的最大值
                int newMaxId = i;
                for(int j = i ; j < i+k ; j++){
                    if(nums[j] > nums[newMaxId]){
                        newMaxId = j;
                    }
                }
                maxId = newMaxId;
            }else{
                // 直接判断最右边的值是否大于窗口内maxId对应的值
                if(nums[i+k-1] > nums[maxId]){
                    maxId = i + k -1;
                }
            }
            res[i] = nums[maxId];
        }

        return res;
    }
}
