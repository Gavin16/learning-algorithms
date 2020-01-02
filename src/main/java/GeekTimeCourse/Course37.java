package GeekTimeCourse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *  使用贪心算法解决两个问题:
 *  (1) 在一个非负整数 a 中，我们希望从中移除 k 个数字，让剩下的数字值最小，如何选择移除哪 k 个数字呢？
 *
 *  (2) 假设有 n 个人等待被服务，但是服务窗口只有一个，每个人需要被服务的时间长度是不同的，如何安排被服务的先后顺序，才能让这 n 个人总的等待时间最短？
 *
 *  贪心算法解决问题思路：
 *  ①： 针对一组数据，我们定义了限制值和期望值，希望从中选出几个数据，在满足限制值的情况下，期望值最大
 *  ②：每次选择当前情况下，在对限制值同等贡献量的情况下，对期望值贡献最大的数据。
 *  ③：举几个例子看下贪心算法产生的结果是否是最优的
 *
 *
 */
public class Course37 {

    public static void main(String[] args) {
        int res = removeKNumbers(123758361, 3);
        System.out.println(res);
    }

    /**
     * n为非负整数
     * 限制条件:只能移除 K 个数值   期望： 移除K个数字后，剩下的数值最大
     * 每次都会移除一个数字,每次移除的数字确保都会让剩下的数值最大
     *      让一个数值最小，当然是数值的越高位越小越好，所以从高到低的检查各位数字，若发现更高位(更左边)的数字大于相邻右边的数字
     *      则移除该数字，可使最终的结果最大
     * 检查结果是否是最优的
     *  输入：123758361,3
     */
    static int removeKNumbers(int n,int k){
        List<Integer> nums = new ArrayList<>();

        int remain;
        while(n != 0){
            remain = n % 10;
            nums.add(0,remain);
            n = n/10;
        }
        int ret = 0,c = 0;
        // 移除k个数
        while(c < k){
            for(int i = 0 ; i < nums.size()-1; i++){
                if(nums.get(i) > nums.get(i+1)){
                    nums.remove(i);
                    break;
                }
            }
            c++;
        }
        // 求和
        for(int e : nums){
            ret = ret*10 + e;
        }

        return ret;
    }



}
