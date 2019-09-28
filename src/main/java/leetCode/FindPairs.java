package leetCode;

import java.util.HashSet;

/**
 * @Class: FindPairs
 * @Description:
 *
 * 《LeetCode 532》
 *  给定一个整数数组和一个整数 k, 你需要在数组里找到不同的 k-diff 数对。
 *  这里将 k-diff 数对定义为一个整数对 (i, j), 其中 i 和 j 都是数组中的数字，且两数之差的绝对值是 k.
 *
 *
 * @Author: Minsky
 * @Date: 2019/9/26 6:17
 * @Version: v1.0
 */
public class FindPairs {

    public static void main(String[]args){
        int[] arr = new int[]{3,1,4,1,5};

        System.out.println(findPairs(arr,2));
    }

    public static int findPairs(int[] nums, int k) {
        HashSet<Integer> diff =  new HashSet<>();
        HashSet<Integer> saw =  new HashSet<>();

        if(k < 0) return -1;

        for(int i : nums){
            if(saw.contains(i+k))
                diff.add(i);
            if(saw.contains(i-k))
                diff.add(i);

            saw.add(i);
        }
        return diff.size();
    }

}
