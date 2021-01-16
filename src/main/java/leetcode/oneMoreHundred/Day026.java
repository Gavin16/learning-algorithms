package leetcode.oneMoreHundred;

import utils.ArrayUtil;

import java.util.Arrays;
import java.util.Comparator;

/**
 * ==============================================================================
 *《56. 合并区间》 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例2:
 *
 * 输入: intervals = [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * ==============================================================================
 *  两个字符串最长公共子序列问题
 *
 *
 *
 * ==============================================================================
 */
public class Day026 {


    public static void main(String[] args) {

        int[][] test = {{1,2},{3,9},{8,10},{15,18}};
        int[][] merge = new Day026().merge(test);
        for(int[] ele : merge){
            ArrayUtil.showArray(ele);
        }

    }

    /**
     * 合并区间
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if(null == intervals || intervals.length == 0) return intervals;
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int cid = 0;
        int[] row = intervals[0];
        for(int k = 1 ; k < intervals.length ; k++){
            if(intervals[k][0] <= row[1]){
                row[1] = Math.max(intervals[k][1],row[1]);
            }else{
                intervals[cid++] = row;
                row = intervals[k];
            }
        }
        intervals[cid] = row;
        return Arrays.copyOf(intervals,cid+1);
    }
}
