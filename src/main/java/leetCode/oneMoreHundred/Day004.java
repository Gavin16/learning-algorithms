package leetCode.oneMoreHundred;

import java.util.List;

/**
 *
 * @Date: 2020年7月4日
 * ==============================================================================
 * 136. 只出现一次的数字
 * ==============================================================================
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * ==============================================================================
 * 448. 找到所有数组中消失的数字
 * ==============================================================================
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 *
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 *
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 * ==============================================================================
 *
 */
public class Day004 {

    public static void main(String[] args) {
        int[] testArr = {2,2,1};
        System.out.println(singleNumber(testArr));


    }

    /**
     * @Title: 136. 只出现一次的数字
     * @Version: 版本1 使用O(1) 外部空间实现
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        int root = 0;
        for(int n : nums){
            root ^= n;
        }
        return root;
    }


    /**
     * @Title: 448. 找到所有数组中消失的数字
     * @Version:
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        return null;
    }
}
