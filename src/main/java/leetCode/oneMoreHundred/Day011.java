package leetCode.oneMoreHundred;

import utils.ArrayUtil;

import java.util.*;

/**
 * @Date：2020年7月23日
 * ==============================================================================
 * 56. 合并区间
 * ==============================================================================
 * 给出一个区间的集合，请合并所有重叠的区间
 *
 * 示例 1:
 *
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * ==============================================================================
 */
public class Day011 {


    public static void main(String[] args) {
        int[][] dim2Arr = {{1,3},{2,6},{8,10},{15,18}};
        int[][] dim2Arr1 = {{1,4},{4,5}};
        int[][] dim2Arr2 = {{2,3},{4,5},{6,7},{8,9},{1,10}};
        int[][] merge = merge(dim2Arr2);
        for(int[] arr : merge){
            ArrayUtil.showArray(arr);
        }
    }



    /**
     * @title: 56. 合并区间
     * @version: 版本1
     * @version:
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        if(intervals.length < 2) return intervals;

        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> arrList = new ArrayList<>();
        int[] temp = new int[2];
        temp[0] = intervals[0][0];
        temp[1] = intervals[0][1];
        for(int i = 1 ; i < intervals.length ; i++){
            if(intervals[i][0] <= temp[1]){
                temp[1] = Math.max(temp[1],intervals[i][1]);
            }else{
                int[] res = Arrays.copyOf(temp,2);
                arrList.add(res);
                temp[0] = intervals[i][0];
                temp[1] = intervals[i][1];
            }
        }
        int[] res = Arrays.copyOf(temp,2);
        arrList.add(res);

        int[][] ret = new int[arrList.size()][2];
        arrList.toArray(ret);
        return ret;
    }
}
