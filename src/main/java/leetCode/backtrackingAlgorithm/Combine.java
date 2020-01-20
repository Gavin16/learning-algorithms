package leetCode.backtrackingAlgorithm;

import javax.jnlp.IntegrationService;
import java.util.ArrayList;
import java.util.List;

/**
 * 《77. 组合》
 *
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例:
 *
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 */
public class Combine {

    public static void main(String[] args) {
//       System.out.println(combine(5,2));
        int[] nums = {1,2,3};
        System.out.println(subsets(nums));
    }


    private static List<List<Integer>> results = new ArrayList<>();

    public static List<List<Integer>> combine(int n, int k) {
        if(n == 0 || k == 0) return results;
        List<Integer> list = new ArrayList<>();
        recursiveSearch(list,n,1,k,0);
        return results;
    }


    private static void recursiveSearch(List<Integer> list ,int n , int dep , int k, int cnt){
        if(cnt == k){
            List<Integer> res = new ArrayList<>(list);
            results.add(res);
            return ;
        }
        // 深度遍历不超过组合的选取范围
        if(dep <= n){
            list.add(dep);
            recursiveSearch(list, n, dep+1, k, cnt+1);
            list.remove(list.size()-1);

            // 遍历枝减, 如果cnt 计数太小， n - dep < k - cnt 则放弃搜索
            if(n - dep >= k - cnt){
                recursiveSearch(list,n,dep+1,k,cnt);
            }
        }
    }


    /**
     *
     * 《78. 子集》
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     *
     * 说明：解集不能包含重复的子集。
     *
     * 示例:
     *
     * 输入: nums = [1,2,3]
     * 输出:
     * [
     *   [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     *
     */
    public static List<List<Integer>> subsets(int[] nums) {
        if(null == nums || nums.length == 0) return new ArrayList<>();
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        dfsFindSubsets(lists,subset,nums,0);
        return lists;
    }



    private static void dfsFindSubsets(List<List<Integer>>lists , List<Integer> subset, int[]nums, int k){
        if(k == nums.length){
            lists.add(new ArrayList<>(subset));
            return;
        }

        subset.add(nums[k]);
        dfsFindSubsets(lists,subset,nums,k+1);
        subset.remove(subset.size()-1);

        dfsFindSubsets(lists, subset, nums, k+1);
    }
}
