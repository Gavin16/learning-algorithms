package leetCode.oneMoreHundred;

/**
 * @Date: 2020年7月14日
 * ==============================================================================
 * 55. 跳跃游戏
 * ==============================================================================
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。
 *
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 * ==============================================================================
 */
public class Day007 {


    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        int[] nums1 = {3,2,1,0,4};
        System.out.println(canJump2(nums));
        System.out.println(canJump2(nums1));
        System.out.println(canJump3(nums1));
    }


    /**
     * @Title: 55. 跳跃游戏
     * @Version: 版本1 判断是否能够到达最后一个位置,只需要找到一条路径即可 : 时间复杂度太高，无法通过
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        if(nums.length == 1) return true;
        return recusiveJump(nums,0,nums.length-1);
    }

    private static boolean recusiveJump(int[] nums, int currId, int lastId) {
        if(nums[currId] + currId >= lastId){
            return true;
        }else{
            boolean canRearch = false;
            for(int i =1 ; i < nums[currId] ; i++){
                canRearch = canRearch || recusiveJump(nums,currId+i,lastId);
                if(canRearch) break;
            }
            return canRearch;
        }
    }


    /**
     * @Title: 55. 跳跃游戏
     * @Version: 版本2 无论怎么跳,要到达最后位置必然需要经过前面所有位置的能跳过的最远位置，记录最远位置
     * @param nums
     * @return
     */
    public static boolean canJump2(int[] nums){
        int k = nums[0];
        for(int i = 0 ; i < nums.length ; i++){
            if(i > k) return false;
            k = Math.max(k,i+nums[i]);
        }
        return true;
    }


    /**
     * @Title: 55. 跳跃游戏
     * @Version: 版本3 迭代求下标0位置开始能达到的最远位置
     * @return
     */
    public static boolean canJump3(int[] nums){
        int maxId = 0;
        for(int i = 0 ; i < nums.length ; i++){
            if(i <= maxId && i + nums[i] > maxId){
                maxId = i + nums[i];
            }
        }
        return maxId >= nums.length-1;
    }
}
