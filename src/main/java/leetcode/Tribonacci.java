package leetcode;

import java.util.HashMap;

/**
 * @ClassName: Tribonacci
 * @CopyRight: wufangmin
 * @Author: wufangmin
 * @Date: 2019/11/18 15:18
 * @Version: 1.0
 * @Description: 第 N 个泰波那契数
 * 《LeetCode 1137. 第 N 个泰波那契数》
 * 泰波那契序列 Tn 定义如下： 
 *
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 *
 * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
 *
 */
public class Tribonacci {

    static HashMap<Integer,Integer> map = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(trib(31));
    }

    /**
     * @param n
     * @return
     */
    public static int trib(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
        if(n == 2) return 1;

        if(map.containsKey(n)){
            return map.get(n);
        }else{
            int res =  trib(n-1) + trib(n-2) + trib(n-3);
            map.put(n,res);
            return res;
        }

    }
}
