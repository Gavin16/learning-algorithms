package algorithmsContest.charpt2.practice.submit;

import java.util.*;

/**
 * @className: BasicBinSearch
 * @description:
 *
 * 整数二分基本代码
 * (1) 找到x的前驱: 如果有x则找出第一次出现的x,如果没有x则找出小于x的最大值
 * (2) 找到x的后继: 如果有x则找到最后出现的x，如果没有x则找出第一个大于x的数位置
 *
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2023/2/23
 */
public class BasicBinSearch {


    public static void main(String[] args) {
        int[] a = {2,3,5,6,7,9,11,12};
        BasicBinSearch instance = new BasicBinSearch();
        System.out.println(instance.binSearchPrecursor(a,10));
        System.out.println(instance.binSearchSuccessor(a, 12));

        int[] arr = {3,4,6,2,6,3,7,8,12,11,9,13,10};
        System.out.println(instance.twoSum(arr, 17));

        System.out.println(instance.twoSumWithBinSearch(arr, 17));
    }

    /**
     * (1) 找到x的前驱
     * 若x存在于列表中，则返回列表中x第一次出现的下标
     * 若x在列表中不存在，则返回小于x的最大元素的下标
     *
     * @param a
     * @param x
     * @return
     */
    int binSearchPrecursor(int[]a, int x){
        int len = a.length;
        int left = 0, right = len - 1;

        if(a[left] > x) return -1;
        else if(a[right] < x) return len;

        while(left < right){
            int mid = left + ((right - left + 1) >> 1);
            if(a[mid] == x && (mid > 0 && a[mid - 1] < x)){
                right = mid;
                break;
            }else if(a[mid] >= x){
                right = mid - 1;
            }else{
                left = mid;
            }
        }
        return right;
    }

    /**
     * (2) 找到x或者x的后继
     * 若x存在重复元素,则返回最后一个x
     * 若有序列表中不存在x,则返回有序列表中大于x的最小元素
     *
     * a[mid] > x 时,设置  right = mid 可以让right位置的值一直大于a[x]
     * 这样循环结束的条件就变成了: a[mid] == x 或者 left == right
     * (对于 left==right left和right前一轮值相差1,相差大于2的话当前值还不能)
     * 对于a[left] == x 的情况，直接返回 mid就好
     * 而对于left==right 由于right不会靠向left, 因此都是left在向right靠近
     * 因此这个时候的left 一定是大于x的第一个值
     *
     * 注意: 这里利用了奇数除以2会忽略小数部分，因此即使出现left + 1 = right的情况
     * 下一轮 right = mid 操作实际上 right 也已经在往left 靠近了
     *
     * @param a
     * @param x
     * @return
     */
    int binSearchSuccessor(int[]a, int x){
        int len = a.length;
        int left = 0, right = len - 1;
        if(x < a[left]) return -1;
        else if(x > a[right]) return len;

        while(left < right){
            int mid = left + ((right - left) >> 1);
            if(a[mid] == x && (mid < len-1 && a[mid + 1] > x)){
                left = mid; break;
            }else if(a[mid] <= x) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    /**
     * 找出数组中所有和为sum的两个数
     * 不能出现重复的数对
     *
     * @param a
     * @param num
     * @return
     */
    List<List<Integer>> twoSum(int[]a, int num){
        Arrays.sort(a);
        Map<Integer, Integer> map = new HashMap<>();
        List<List<Integer>> list = new ArrayList<>();
        for(int n : a){
            if(map.containsKey(n)){
                int cnt = map.get(n);
                if(0 == cnt){
                    List<Integer> pair = new ArrayList<>();
                    pair.add(num - n); pair.add(n);
                    list.add(pair);
                }else{
                    map.put(n, cnt+1);
                }
            }else{
                map.put(num - n, 0);
            }
        }
        return list;
    }

    /**
     * 两数之和 使用二分查找实现
     * @param arr
     * @param num
     * @return
     */
    List<List<Integer>> twoSumWithBinSearch(int[] arr, int num){
        List<List<Integer>> list = new ArrayList<>();

        Arrays.sort(arr);
        int len = arr.length;
        for(int i = 0; i < arr.length; i++){
            while(i < len - 1 && arr[i] == arr[i+1]) i++;
            int curr = arr[i];

            int index = binSearch(arr, i + 1, len-1, num - curr);
            if(index >= 0){
                List<Integer> pair = new ArrayList<>();
                pair.add(curr); pair.add(num - curr);
                list.add(pair);
            }
        }
        return list;
    }

    /**
     * 在数组arr 中startId 到 endId 范围内
     * 找出 target 所在位置的下标
     *
     * @param arr
     * @param startId
     * @param endId
     * @param target
     * @return
     */
    private int binSearch(int[] arr, int startId, int endId, int target){
        int left = startId, right = endId;
        while(left <= right){
            int mid = left + ((right - left) >> 1);
            if(arr[mid] == target) return mid;
            else if(arr[mid] > target){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return -1;
    }
}
