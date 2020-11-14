package leetcode.backtrackingAlgorithm;

import java.util.*;

/**
 * 《39. 组合总和》
 *
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 *
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *
 */
public class CombinationSum {


    public static void main(String[] args) {
        int[] nums = {2,3,6,7};

        int[] data = {10,1,2,7,6,1,5};
        int[] data1 = {2,5,2,1,2};
//        System.out.println(combinationSum1(nums,7));
        System.out.println(combinationSum2(data,8));
//        System.out.println(combinationSum2(data1,5));
    }


    /**
     *  数组中的数值可以重复出现
     *
     *  所有数字（包括 target）都是正整数
     *
     *  对数组做排序处理,然后
     */
    public static List<List<Integer>> combinationSum1(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(null == candidates || candidates.length == 0) return res;

        Arrays.sort(candidates);
        recursiveCombinSum(res,new ArrayList<>() , candidates,target,0);
        return res;
    }

    /**
     *
     * @param result
     * @param combin 可能的某个组合，递归到边界条件时才能知道该组合是否满足条件(添加到result中)
     * @param candidates
     * @param target
     * @param index
     * @Version: v2
     */
    private static void recursiveCombinSum(List<List<Integer>> result ,List<Integer> combin ,
                                           int[] candidates, int target ,int index){
        if(target == 0){
            result.add(new ArrayList<>(combin));
            return;
        }

        for(int i = index ; i < candidates.length && target >= candidates[i] ; i++){
            int n = candidates[i];
            combin.add(n);
            recursiveCombinSum(result,combin,candidates,target - n,i);
            combin.remove(combin.size() - 1);
        }
    }


    /**
     *  candidates 中的每个数字在每个组合中只能使用一次
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(null == candidates || candidates.length == 0) return res;

        Arrays.sort(candidates);
        recursiveCombinSum2(res,new ArrayList<>(),candidates,target,0);
        return res;
    }

    /**
     *
     * @param result
     * @param combin
     * @param candidates
     * @param target
     * @param cid
     * @version: V2
     */
    private static void recursiveCombinSum2(List<List<Integer>> result ,List<Integer> combin , int[] candidates,
                                            int target,int cid){
        if(target == 0){
            result.add(new ArrayList<>(combin));
            return;
        }

        if(cid == candidates.length) return;

        // 想要跳过的是这个值，而不是该位置
        if(target >= candidates[cid]){
            combin.add(candidates[cid]);
            recursiveCombinSum2(result,combin,candidates,target-candidates[cid],cid+1);
            combin.remove(combin.size()-1);
            int nextValId = getNextValId(candidates,cid);
            recursiveCombinSum2(result,combin,candidates,target,nextValId);
        }
    }

    private static int getNextValId(int[] candidates, int cid) {
        for(int j = cid; j < candidates.length ; j++){
            if(candidates[j] != candidates[cid]) return j;
        }
        return candidates.length;
    }
}