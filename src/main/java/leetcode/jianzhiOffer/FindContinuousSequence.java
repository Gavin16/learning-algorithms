package leetcode.jianzhiOffer;

import org.apache.commons.compress.utils.Lists;
import utils.ArrayUtil;

import java.util.List;

/**
 *  剑指 Offer 57 - II
 *  输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 *
 *  序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 *  1 <= target <= 10^5
 *
 * @Author: Minsky
 * @Date: 2020/6/25 12:22
 * @Description:
 *

 */
public class FindContinuousSequence {


    public static void main(String[] args) {
        int[][] result = findContinuousSequence(10);
        for(int[] ele : result){
            ArrayUtil.showArray(ele);
        }
    }


    public static int[][] findContinuousSequence(int target) {
        int[][] result = new int[][]{};
        if(target <= 2) return result;

        List<int[]> list = Lists.newArrayList();
        int  idx = 0, maxLen = maxSeqLength(target);
        for (int i = 2 ; i <= maxLen ; i++){
            int incSum = getIncSeqSum(i);
            boolean isTheSeq = (target - incSum)% i == 0 ;
            if(isTheSeq){
                int base = (target - incSum)/i ;
                int[] sequence = genSeqByBaseAndLength(base,i);
                list.add(sequence);
            }
        }
        result = new int[list.size()][];
        for(int[] ele : list){
            result[idx++] = ele;
        }
        return result;
    }

    /**
     * 指定target 满足条件的序列的最大长度
     * @param n
     * @return
     */
    private static int maxSeqLength(int n){
        return (int)(Math.sqrt(1+8*n) + -1)/2;
    }

    /**
     * 根据起始元素的值及数组长度生成 公差为1的等差数列
     * @param base
     * @param i
     * @return
     */
    private static int[] genSeqByBaseAndLength(int base, int i) {
        int[] array = new int[i];
        for(int k = 0 ; k < i ; k++){
            array[k] = base + k +1;
        }
        return array;
    }

    /**
     * 计算递增序列(从1 到 i) 的序列和
     * @param i
     * @return
     */
    private static int getIncSeqSum(int i) {
        int sum = (i+1)*i/2;
        return sum;
    }
}
