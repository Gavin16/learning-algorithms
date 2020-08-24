package leetCode.oneMoreHundred;

import java.util.Arrays;
import java.util.Comparator;

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
 *
 */
public class Day019 {

    public static void main(String[] args) {
        int[][] arrs = {{1,3},{2,6},{8,10},{15,18}};
        new Day019().merge(arrs);
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

        int[][] result = new int[intervals.length][intervals[0].length];

        int[] pre = intervals[0];
        for(int k = 1 ; k < intervals.length ; k++){

        }

        return result;
    }
}
