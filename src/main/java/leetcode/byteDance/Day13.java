package leetcode.byteDance;





import java.io.*;
import java.util.*;

public class Day13 {


    public static void main(String[] args) {
        Day13 day13 = new Day13();

//        int[] arr1 = {1,2,3};
//        int[] arr2 = {2,1,3};
//        int[] arr3 = {3,2,1};
//        int[] arr4 = {3,1,2};
//        int[] arr5 = {2,4,3,1};
//
//
//
//        day13.nextPermutation(arr1);
//        day13.nextPermutation(arr2);
//        day13.nextPermutation(arr3);
//        day13.nextPermutation(arr5);
//
//        ArrayUtil.showArray(arr1);
//        ArrayUtil.showArray(arr2);
//        ArrayUtil.showArray(arr3);
//        ArrayUtil.showArray(arr5);


//        int[][] arrs = {{1,3},{2,6},{8,10},{15,18}};
        int[][] arrs = {{1,4}};
        int[][] merge = day13.merge(arrs);
        for(int[] e : merge){
            System.out.println("[" + e[0] + "," + e[1] + "]");
        }
    }

    void sortBDSubjects() throws IOException {

        String path = this.getClass().getResource("/").getPath();
        FileInputStream fis1 = new FileInputStream(path + "/repeated50");
        FileInputStream fis2 = new FileInputStream(path + "/repeated250");

        BufferedReader br1 = new BufferedReader(new InputStreamReader(fis1));
        BufferedReader br2 = new BufferedReader(new InputStreamReader(fis2));


        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        String line = null;
        while((line = br1.readLine()) != null){
            if(!"".equals(line)){
                list1.add(line + "\r\n");
            }
        }
        line = null;
        while((line = br2.readLine()) != null){
            if(!"".equals(line)){
                list2.add(line + "\r\n");
            }
        }

        Collections.sort(list2, Comparator.comparing(o -> {
            String[] split = o.split("\\.");
            if(isNumber(split[0])){
                return Integer.parseInt(split[0]);
            }
            return Integer.MAX_VALUE;
        }));
        File sorted_repeated_50 = new File("sorted_repeated_50");
        File sorted_repeated_250 = new File("sorted_repeated_250");
        FileOutputStream fos1 = new FileOutputStream(sorted_repeated_50);
        FileOutputStream fos2 = new FileOutputStream(sorted_repeated_250);


        for(int i = 0; i < list1.size(); i++){
            String s = list1.get(i);
            if(null != s && !"".equals(s)){
                fos1.write(s.getBytes());
            }
        }


        for(int i = 0; i < list2.size(); i++){
            String s = list2.get(i);
            if(null != s && !"".equals(s)){
                fos2.write(s.getBytes());
            }
        }

        fos1.close();
        fos2.close();
        fis1.close();
        fis2.close();
    }

    private boolean isNumber(String s) {
        for(int i = 0; i < s.length(); i++){
            if(!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }


    /**
     * 31. 下一个排列
     * 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
     *
     * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
     * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，
     * 那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列
     * （即，其元素按升序排列）。
     *
     * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
     * 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
     * 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
     * 给你一个整数数组 nums ，找出 nums 的下一个排列。
     *
     * 必须 原地 修改，只允许使用额外常数空间。
     *
     * 示例 1：
     * 输入：nums = [1,2,3]
     * 输出：[1,3,2]
     * 示例 2：
     *
     * 输入：nums = [3,2,1]
     * 输出：[1,2,3]
     * 示例 3：
     *
     * 输入：nums = [1,1,5]
     * 输出：[1,5,1]
     *  
     * 提示：
     *
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 100
     *
     */
    public void nextPermutation(int[] nums) {
        if(nums.length == 1) return;
        int prev = -1, curr = 0;

        for(int i = 1 ; i < nums.length; i++){
            if(nums[i] > nums[i-1]){
                prev = i-1;
                curr = i;
            }
        }
        if(prev != -1 && curr != 0){
            // 找出需要与prev 交换的元素的位置
            int minBigger = curr;
            for(int k = curr; k < nums.length; k++){
                if(nums[k] > nums[prev] && nums[k] < nums[minBigger]){
                    minBigger = k;
                }
            }
            // 交换prev 与大于它的最小值两个元素位置
            int temp = nums[prev];
            nums[prev] = nums[minBigger];
            nums[minBigger] = temp;
        }
        Arrays.sort(nums,curr,nums.length);
    }


    /**
     * 74. 搜索二维矩阵
     * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
     *
     * 每行中的整数从左到右按升序排列。
     * 每行的第一个整数大于前一行的最后一个整数。
     *
     * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
     * 输出：true
     *
     * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
     * 输出：false
     *
     * 提示：
     *
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 100
     * -104 <= matrix[i][j], target <= 104
     *
     *
     * 从左下角到右上角，或者从右上角到左下角进行搜索
     * 以 左下角 --> 右上角 为例
     * (1) 若target 小于当前元素，则走到上一行; 若越界则返回
     * (2) 若target 大于当前元素, 则走到下一列; 若越界则返回
     *
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        for(int i = row - 1, j = 0; i >=0 && j < col; ){
            if(matrix[i][j] > target) i--;
            else if(matrix[i][j] < target) j++;
            else return true;
        }
        return false;
    }



    /**
     * 56. 合并区间
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
     * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
     *
     * 示例 1：
     *
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * 示例 2：
     *
     * 输入：intervals = [[1,4],[4,5]]
     * 输出：[[1,5]]
     * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
     *
     * 提示：
     *
     * 1 <= intervals.length <= 104
     * intervals[i].length == 2
     * 0 <= starti <= endi <= 104
     *
     */
    public int[][] merge(int[][] intervals) {
        // 二维数组以第一个元素对所有一维数组升序排序
        Arrays.sort(intervals,Comparator.comparing(a -> a[0]));
        List<int[]> list = new ArrayList<>();
        int start = intervals[0][0], end = intervals[0][1];
        for(int i = 1 ; i < intervals.length ; i++){
            int[] ele = intervals[i];
            // 两个区间比较存在以下几种情况
            // 1. end < ele[0]
            // 2. end >= ele[0] && end <= ele[1]
            // 3. end > ele[1]
            if(end < ele[0]){
                int[] arr = new int[]{start, end};
                list.add(arr);
                start = ele[0];
                end = ele[1];
            }else if(end >= ele[0] && end <= ele[1]){
                end = ele[1];
            }
        }
        list.add(new int[]{start, end});
        int[][] ans = list.toArray(new int[][]{});
        return ans;
    }


}
