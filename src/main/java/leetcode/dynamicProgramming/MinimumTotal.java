package leetcode.dynamicProgramming;


import java.util.List;

/**
 * 《120. 三角形最小路径和》
 *
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 *
 * 例如，给定三角形：
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 * 说明：
 *
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 *
 */
public class MinimumTotal {


    public static void main(String[] args) {
        MinimumTotal minimumTotal = new MinimumTotal();


    }


    /**
     * 状态转移方程： n层最小路径 = min(n-层路径 + 各自相对下一层的间隔)
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] minTotals = new int[triangle.size()];

        for(int i = 0 ; i < triangle.size() ; i++){
            List<Integer> integers = triangle.get(i);

            for(int j = integers.size()-1 ; j >= 0; j--){
                if(i == 0){
                    minTotals[i] = integers.get(i);
                }else{
                    if(j == integers.size()-1){
                        minTotals[j] = integers.get(j) + minTotals[j-1];
                    }else if(j == 0){
                        minTotals[j] = integers.get(j) + minTotals[j];
                    }else{
                        minTotals[j] = integers.get(j) + Math.min(minTotals[j],minTotals[j-1]);
                    }
                }
            }
        }
        return minTotals[triangle.size()-1];
    }
}
