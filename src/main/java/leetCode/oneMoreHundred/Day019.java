package leetCode.oneMoreHundred;

import utils.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Date: 2020年8月17日
 * ==============================================================================
 * 56. 合并区间
 * ==============================================================================
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: intervals = [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * ==============================================================================
 * 134. 加油站
 * ==============================================================================
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 *
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 *
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 *
 * 说明: 
 *
 * 如果题目有解，该答案即为唯一答案。
 * 输入数组均为非空数组，且长度相同。
 * 输入数组中的元素均为非负数。
 * 示例 1:
 *
 * 输入:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 *
 * 输出: 3
 *
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 * 示例 2:
 *
 * 输入:
 * gas  = [2,3,4]
 * cost = [3,4,3]
 *
 * 输出: -1
 *
 * 解释:
 * 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
 * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
 * 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 * 因此，无论怎样，你都不可能绕环路行驶一周。
 *
 *
 *
 *
 */
public class Day019 {

    public static void main(String[] args) {
        int[][] arrs = {{8,10},{1,3},{2,6},{15,18}};
        int[][] merge = new Day019().merge(arrs);

        for(int[] ele : merge){
            ArrayUtil.showArray(ele);
        }

        // 加油站
        int[] gas = {2,3,4};
        int[] cost = {3,4,3};

        System.out.println(new Day019().canCompleteCircuit(gas,cost));
    }


    /**
     * @Title: 56. 合并区间
     * @Version: 排序循环比较合并
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if(null == intervals || intervals.length == 0) return intervals;

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> result = new ArrayList<>();

        int[] temp = intervals[0];
        for(int k = 1 ; k < intervals.length ; k++){
            if(intervals[k][0] > temp[1]){
                result.add(temp);
                temp = intervals[k];
            }else{
                temp[0] = Math.min(temp[0],intervals[k][0]);
                temp[1] = Math.max(temp[1],intervals[k][1]);
            }
        }
        result.add(temp);

        return result.toArray(new int[result.size()][2]);
    }

    /**
     * @Title: 134. 加油站 (头条面试题)
     * 直观解法: 嵌套循环
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        for(int i = 0 ; i < gas.length ; i++){
            int k = i, totalGas = 0;
            while(totalGas >= 0){
                totalGas += gas[k] - cost[k];
                k = (k+1) % len;
                if(k == i && totalGas >= 0){
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * @Title： 134. 加油站 (头条面试题)
     * @Version: 分析简化解法
     *
     *  以输入 gas = 1,2,3,4,5; cost = 3,4,5,1,2 为例
     *
     *  首先需要明确一点的是，gas 中油的总量需要大于 cost 的总量，不然不管加油车从什么位置出发，它必然在某个位置点上会因为总油量不够而不能到达下一个加油站。
     *
     *  从前至后的循环遍历 gas 和 cost ; 以 totalGas 作为总加油的量，totalGas = gas[i] - cost[i],显然只需要保证totalGas的值大于0即可走到下一个加油站。
     *  假设第一次是在 i 位置发现 totalGas < 0 , 这意味着
     *  (1) 从 0 到 i 这段范围内， Σ gas(0~i)  < Σ cost(0~i)
     *  (2) 从 0 到 i-1 范围内，Σ gas(0~i-1) >= Σ cost(0~i-1)
     *  (3) 从总量上来看记 m1 = Σ cost(0~i) - Σ gas(0~i)
     *  若下一次再次在 j 的位置发现 totalGas < 0 ，这时 类似的有 也有是上面的三个关系，不同的是这次是从 i+1 的位置开始
     *
     *  以类似如上的分析可以发现
     *  即使我们在 [0,i],[i+1,j] … [k,l] 的范围内都有 Σ gas(a~b) < Σ cost(a~b)，分别记为m1,m2,…,ms , 只要我们确保从某一个位置开始，加油车可以一直走到最后的位置(下标为 len-1，len为数组长度)
     *
     *  假设是从 q 位置开始可以一直走到 len-1 下标的，那么就一定有  Σ gas(q~len-1) - Σ cost(q~len-1) >= 前面各段 m1+m2+… + ms 的总和(这里的前提条件是 gas总油量不小于 cost总量)
     *
     *  通过以上分析可知，对于gas总油量不小于 cost总油量的场景下，只要找到第一个下标能走到加油车最后的位置，改位置就是可以循环一圈的起始位置
     *
     *
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int gasLeft = 0;
        int len = gas.length;
        for(int i = 0 ; i < len ; i++){
            gasLeft += (gas[i] - cost[i]);
        }
        if(gasLeft < 0) return -1;
        int totalGas = 0,st = 0;
        for(int k = 0; k < len ; k++){
            totalGas += (gas[k] - cost[k]);
            if(totalGas < 0){
                totalGas = 0;
                st = k+1;
            }
        }
        return st < len ? st : -1;
    }

}
