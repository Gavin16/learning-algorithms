package leetcode.oneMoreHundred;

/**
 * 深度优先搜索: 3/10 ~ 5/10
 *
 * 《45. 跳跃游戏 II》
 * 给你一个非负整数数组nums ，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 假设你总是可以到达数组的最后一个位置
 *
 * 示例 1:
 *
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *     从下标为 0 跳到下标为 1 的位置，跳1步，然后跳3步到达数组的最后一个位置。
 *
 * 示例 2:
 *
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 *
 * 提示:
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 1000
 *
 */
public class Day045 {

    /**
     * 深度优先搜索: 使用单个状态保存最小步数
     * >>> 超出时间限制
     */
    public int jump(int[] nums) {
        if(null == nums || nums.length <= 1) return 0;
        int lastIndex = nums.length - 1;

        dfs(nums, 0, 0, lastIndex);
        return minSteps;
    }

    private int minSteps = Integer.MAX_VALUE;

    private void dfs(int[]nums , int steps, int  pos, int lastIndex){
        if(pos < lastIndex){
            for(int i = 1; i <= nums[pos] ; i++){
                if(pos + i <= lastIndex){
                    dfs(nums, steps+1, pos+i, lastIndex);
                }
            }
        }else if(pos == lastIndex){
            minSteps = Math.min(minSteps, steps);
        }
    }

    /**
     * 贪心算法
     * 从左向右遍历,若每次找出第一个能跳到最后元素的位置，可以认为每次跳的距离是最长的
     * 总长度固定，这种跳法也可以认为是跳的步数最少的。
     *
     */
    public int jump1(int[] nums) {
        int lastPos = nums.length-1;
        int steps = 0;
        while(lastPos > 0){
            for(int i = 0 ; i < lastPos ; i++){
                if(i + nums[i] >= lastPos){
                    lastPos = i;
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }



    /**
     *
     * 如果我们「贪心」地进行正向查找，每次找到可到达的最远位置，就可以在线性时间内得到最少的跳跃次数。
     * 例如，对于数组 [2,3,1,2,4,2,3]，初始位置是下标 0，从下标 0 出发，最远可到达下标 2。下标 0 可到达的位置中，
     * 下标 1 的值是 3，从下标 1 出发可以达到更远的位置，因此第一步到达下标 1。
     * 从下标 1 出发，最远可到达下标 4。下标 1 可到达的位置中，下标 4 的值是 4 ，从下标 4 出发可以达到更远的位置，因此第二步到达下标 4。
     *
     */
    public int jump2(int[] nums) {
        int len = nums.length;
        int end = 0, maxPos = 0;
        int steps = 0;
        for(int i = 0 ; i < len - 1; i++){
            maxPos = Math.max(maxPos, i + nums[i]);
            if(end == i){
                end = maxPos;
                steps++;
            }
        }
        return steps;
    }


    public static void main(String[] args) {
//        int[]nums = {5,6,4,4,6,9,4,4,7,4,4,8,2,6,8,1,5,9,6,5,2,7,9,7,9,6,9,4,1,6,8,8,4,4,2,0,3,8,5};
//        int[] nums = {2,3,0,1,4};
        int[] nums =  {2,3,1,2,4,2,3};
        Day045 instance = new Day045();
        System.out.println(instance.jump1(nums));
    }
}
