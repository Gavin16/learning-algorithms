package leetCode;

import utils.ArrayUtil;

import java.util.Arrays;

/**
 * @Class: RelativeSortArray
 * @Description:
 *
 *   LeetCode 1122
 *
 *   给你两个数组，arr1 和 arr2，
 *   arr2 中的元素各不相同
 *   arr2 中的每个元素都出现在 arr1 中
 *   对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。
 *   未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 *
 *
 *  示例：
 *  输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 *  输出：[2,2,2,1,4,3,3,9,6,7,19]
 *
 * @Author: Minsky
 * @Date: 2019/7/28 16:53
 * @Version: v1.0
 */
public class RelativeSortArray {

    public static void main(String[]args){
        int[] arr1 = new int[]{2,3,1,3,2,4,6,7,9,2,19};
        int[] arr2 = new int[]{2,1,4,3,9,6};

        int[] res = relativeSortArray(arr1,arr2);
        ArrayUtil.showArray(res);
    }

    /**
     * 方法一：使用双重遍历实现
     * @param arr1
     * @param arr2
     * @return
     */
    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        int k = 0;
        for(int i = 0 ; i < arr2.length ; i++){

            for(int j = k ; j < arr1.length ; j++){
                if(arr1[j] == arr2[i]){
                    int swap = arr1[j];
                    arr1[j] = arr1[k];
                    arr1[k++] = swap;
                }
            }
        }
        // 对剩余部分升序排序
        Arrays.sort(arr1,k,arr1.length);

        return arr1;
    }
}
