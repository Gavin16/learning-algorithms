package leetcode.oneMoreHundred;

import datastruct.TreeNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date: 2020年8月29日
 * ==============================================================================
 * 617. 合并二叉树
 * ==============================================================================
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 *
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
 *
 * 示例 1:
 *
 * 输入:
 * 	Tree 1                     Tree 2
 *           1                         2
 *          / \                       / \
 *         3   2                     1   3
 *        /                           \   \
 *       5                             4   7
 * 输出:
 * 合并后的树:
 * 	     3
 * 	    / \
 * 	   4   5
 * 	  / \   \
 * 	 5   4   7
 * 注意: 合并必须从两个树的根节点开始。
 *
 * ==============================================================================
 * 560. 和为K的子数组
 * ==============================================================================
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 *
 * 示例 1 :
 *
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 说明 :
 *
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 *
 *
 */
public class Day020 {


    public static void main(String[] args) {

        int[] arr = {1,2,3,2,0,1,2};
        int[] arr1 = {1,1,1};
        int[] arr2 = {1};
//        int[] arr = {1,1,1};
        System.out.println(new Day020().subarraySum2(arr,5));
        System.out.println(new Day020().subarraySum2(arr,0));
        System.out.println(new Day020().subarraySum2(arr1,2));
        System.out.println(new Day020().subarraySum2(arr2,0));
    }


    /**
     * @Title: 617. 合并二叉树
     *
     * 二叉树中序变量, 对于两颗二叉树而言，若只有其中一颗二叉树存在分支子节点，则合并后的二叉树保留完整的分支子节点及之后的子节点，不再新建节点
     *
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(null == t1 || null == t2) return null == t1 ? t2 : t1;
        TreeNode newNode = new TreeNode(t1.val + t2.val);
        newNode.left = mergeTrees(t1.left,t2.left);
        newNode.right = mergeTrees(t1.right,t2.right);
        return newNode;
    }


    /**
     * @Title: 560. 和为K的子数组
     * @Version: 递归遍历 (超出时间限制)
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int cnt = 0;
        for(int i = 0 ; i < nums.length ; i++){
            cnt += dfs(nums,i,i,k,0,0);
        }
        return cnt;
    }

    private int dfs(int[] nums, int i,int j,int sum, int curr,int cnt) {
        if(j == nums.length){
            return curr == sum ? cnt+1 : cnt;
        }
        if(curr == sum){
            if(j > i){
                cnt += 1;
            }
        }
        curr += nums[j];
        return dfs(nums,i,j+1,sum,curr,cnt);
    }


    /**
     * @Title: 560. 和为K的子数组
     * @Version: 循环遍历 (时间击败 16% 的用户)
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum2(int[] nums, int k) {
        int cnt = 0;
        for(int i = 0 ; i < nums.length ; i++){
            int sum = 0;
            for(int j = i; j < nums.length ; j++){
                sum += nums[j];
                if(sum == k) cnt++;
            }
        }
        return cnt;
    }

    /**
     * @Title:  560. 和为K的子数组
     *
     *
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum3(int[] nums, int k) {
        int count = 0,pre = 0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        for(int n : nums){
            pre += n;
            if(map.containsKey(pre - k)){
                count += map.get(pre-k);
            }
            map.put(pre,map.getOrDefault(pre,0)+1);
        }
        return count;
    }






    /**
     * @Title: 40. 组合总和 II
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        return null;
    }
}
