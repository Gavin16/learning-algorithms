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
 *
 */
public class Day019 {

    public static void main(String[] args) {
        int[][] arrs = {{8,10},{1,3},{2,6},{15,18}};
        int[][] merge = new Day019().merge(arrs);

        for(int[] ele : merge){
            ArrayUtil.showArray(ele);
        }

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
}
