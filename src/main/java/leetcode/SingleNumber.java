package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @Class: SingleNumber
 * @Description:
 *
 * 《137. 只出现一次的数字 II》
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 *
 * 说明：
 *
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * @Author: Minsky
 * @Date: 2019/9/28 9:08
 * @Version: v1.0
 */
public class SingleNumber {

    public static void main(String[]args){

        int[] input = new int[]{0,1,0,1,0,1,29,29,29,99};
        System.out.println(findTarget1(input));
    }


    /**
     * 求和的方式； 数据量大一点就超出表示范围
     * @param nums
     * @return
     */
    public static int findTarget(int[] nums){

        // Set对出现过的所有元素去重
        Set<Integer> eles = new HashSet<>();
        long sumAll = 0;
        for(int i : nums){
            sumAll += i;
            eles.add(i);
        }

        // 遍历所有元素求和
        long sum = 0;
        for(Integer e : eles){
            sum += e;
        }

        return (int)(3*sum - sumAll)/2;
    }

    /**
     *  使用两个整数(ab)保存出现次数
     *  当出现1次数 a=0,b=1
     *  当出现2次时 a=1,b=0
     *  当出现3次时 a=0,b=0
     *  即可找出该出现1次的数; 类似的若该数出现2次也可以找出来
     * @param nums
     * @return
     */
    public static int findTarget1(int[] nums){
        int a = 0, b = 0;
        for(int x : nums){
            b = b ^ x & (~a);
            a = a ^ x & (~b);
        }

        return b;
    }
}
