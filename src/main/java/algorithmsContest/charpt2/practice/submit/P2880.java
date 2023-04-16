package algorithmsContest.charpt2.practice.submit;

import utils.ArrayUtil;

/**
 * @className: P2880
 * @description:
 *
 * 问题描述:
 * 给定一个包含n个整数的数列和q个区间查询, 查询区间内最大值和最小值的差值。
 *
 * 输入: 第1行输入两个整数n 和 q;接下来n行中，每行输入一个整数hi; 再后面q行中，
 * 每行输入两个整数a 和 b, 表示一个区间查询
 *
 * 输出: 对每个区间查询, 返回区间内最大值和最小值的差
 *
 * 数据范围: 1 <= n <= 5*10^4, 1 <= q <= 1.8 * 10^5, 1 <= a <= b <= n
 *
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2023/3/26
 */
public class P2880 {

    /**
     * maxST[s][k] 代表从s位置作为左边界, 区间长度为2^k范围内的最大值
     * minST[s][k] 代表从s位置作为左边界, 区间长度为2^k范围内的最小值
     *
     * 存在如下递推关系式:
     * maxST[s][k] = max(maxST[s][k-1], maxST[s + 2^(k-1)][k-1])
     * 相当于区间[s, s+2^k] 范围内的最大值，等于 [s, s+2^(k-1)][s + 2^(k-1), s + 2^k]
     * 两个区间内的最大值中的更大那个。
     *
     * 若查询取件范围[L,R] 的区间长度不是 2的整数次方， 可以对 log2(R-L) 浮点数最取整处理
     * 假设 i = floor(log2(R-L));
     * 那么对于[L,R] 范围内的最大值，可以通过 [L, L+2^(i)] 和 [R-2^(i), R] 两个区间的最大值确定
     *
     * 实际对数列arr进行 长度分别为1, 2, 4, 8, 16, ... 2^k 小区间拆分
     * 对于max[s][k] , s 的最大取值为数组长度
     *
     *
     *
     * 区间最小值有类似处理，依次类推即可。
     */
    int[][] maxST;
    int[][] minST;

    public static void main(String[] args) {
        int[]arr = {4,7,9,6,3,6,4,8,7,5};
        int[][] ranges = {{1,3},{2,5},{1,10},{8,10},{7,8},{5,5}};

        P2880 instance = new P2880();

        int[] res = instance.rangeMaxGap(arr, ranges);
        ArrayUtil.showArray(res);
    }

    /**
     * 查询数列arr 中, q次查询(每次范围为 L, R)对应范围中最大值与最小值的差
     * @param arr
     * @param queryRange
     * @return
     */
    public int[] rangeMaxGap(int[] arr , int[][] queryRange){
        int queryTimes = queryRange.length;
        int[] res = new int[queryTimes];
        if(arr.length == 1) return res;
        // 构造maxST 和 minST
        initSparseTables(arr);
        // 根据查询范围数组 queryRange
        // 搜索maxST 和 minST 构造res
        int i = 0;
        for(int[] query : queryRange){
            int L = query[0];
            int R = query[1];
            if(L == R){
                res[i++] = 0;
                continue;
            }
            int gap = R - L + 1;
            int p = (int) Math.floor(Math.log(gap) / Math.log(2));
            // [L, L + offset], [R - offset, R]
            // 需要注意当 R - L + 1 = 2^p 时， maxST[R - (1 << p)][p] 实际区间范围为
            // [R - 2^p, R - 2^p -1] 区间不合法，因此对 maxST[R - (1 << p)][p]其实位置做1个偏移
            // 写为 maxST[R-(1 << p)+1][p] 同时前后区间依然连续或者重叠
            int max = Math.max(maxST[L][p], maxST[R - (1 << p) + 1][p]);
            int min = Math.min(minST[L][p], minST[R - (1 << p) + 1][p]);
            res[i++] = max - min;
        }
        return res;
    }

    private void initSparseTables(int[] arr){
        int len = arr.length;
        int k = (int) Math.floor(Math.log(len) / Math.log(2));
        maxST = new int[len + 1][k+1];
        minST = new int[len + 1][k+1];
        for(int i = 1; i <= len;  i++){
            maxST[i][0] = arr[i-1];
            minST[i][0] = arr[i-1];
        }
        // 根据递推公式计算 所有maxST 和 minST的值
        for(int t = 1; t <= k; t++){
            for(int s = 1; s + (1 << t) <= len + 1; s++){
                maxST[s][t] = Math.max(maxST[s][t-1], maxST[s + (1 << (t-1))][t-1]);
                minST[s][t] = Math.min(minST[s][t-1], minST[s + (1 << (t-1))][t-1]);
            }
        }
    }

}
