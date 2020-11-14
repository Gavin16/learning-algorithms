package leetcode.oneMoreHundred;

import utils.ArrayUtil;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Date: 2020年7月13日
 * ==============================================================================
 *  739. 每日温度
 * ==============================================================================
 * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，
 * 至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 * ==============================================================================
 *
 */
public class Day006 {


    public static void main(String[] args) {
        int[] test = {1};
        int[] test2 = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] ints2 = dailyTemperatures(test2);
        ArrayUtil.showArray(ints2);

        int[] ints = dailyTemperatures2(test2);
        ArrayUtil.showArray(ints);
    }

    /**
     * @Title: 739. 每日温度
     * @Version: 版本1 暴力求解，时间复杂度高
     * @param T
     * @return
     */
    public static int[] dailyTemperatures(int[] T) {
        if(T.length == 1) return new int[1];

        int[] diff = new int[T.length-1];
        int[] highers = new int[T.length];
        for(int i=0, j = 0 ; j < T.length-1 ; j++){
            diff[i++] = T[j+1] - T[j];
        }

        for(int k = 0 ; k < T.length-1 ; k++){
            if(diff[k] > 0){
                highers[k] = 1;
            }else{
                int gap = 0,id = k;
                for(; id < diff.length ; id++){
                    gap += diff[id];
                    if(gap > 0){
                        break;
                    }
                }
                if(gap > 0){
                    highers[k] = id - k + 1;
                }else{
                    highers[k] = 0;
                }
            }
        }
        highers[T.length - 1] = 0;
        return highers;
    }


    /**
     * @Title: 739. 每日温度
     * @Version: 版本2 使用单调栈
     * @param T
     * @return
     */
    public static int[] dailyTemperatures2(int[] T) {
        if(T.length == 1){
            return new int[1];
        }
        Deque<Integer> stack = new LinkedList<>();
        int[] res = new int[T.length];
        for(int k = 0 ; k < T.length ; k++){
            while(!stack.isEmpty() && T[k] > T[stack.peek()]){
                Integer pop = stack.pop();
                res[pop] = k - pop;
            }
            stack.push(k);
        }
        return res;
    }
}
