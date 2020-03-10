package leetCode.dynamicProgramming;

public class MaxSubArray {

    public static void main(String[] args) {

        MaxSubArray obj = new MaxSubArray();
        int[] arr = {-2,1,-3,4,-1,2,1,-5,4};

        System.out.println(obj.maxSubArray(arr));

    }

    public int maxSubArray(int[] nums) {
        // 连续子树组最大值的寻找策略是：
        // (1) 遍历中保存连续数组的最大值
        // (2) 新元素加入数组后和 <= 0 则跳过该子数组
        if(nums == null || nums.length == 0) return 0;
        int sum = nums[0] , maxSum = sum;

        for(int i = 1 ; i < nums.length ; i++){
            if(nums[i] <= 0){
                if(nums[i] > sum) sum = nums[i];
                else{
                    sum += nums[i];
                }
            }else{
                if(sum < 0) sum = nums[i];
                else sum += nums[i];
            }

            if(sum > maxSum) maxSum = sum;
        }

        return maxSum;
    }
}
