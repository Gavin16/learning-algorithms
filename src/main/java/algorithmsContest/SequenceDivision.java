package algorithmsContest;

/**
 * @className: SequenceDivision
 * @description:
 *
 * 例2.6 问题描述
 * 例如，有一个序列{2,2,3,4,5,1}， 将其划分成3个连续的子序列 S1, S2,S3
 * 每个子序列最少有一个元素,要求使每个子序列的和的最大值最小.
 * 分法1: S1,S2,S3分别划分为(2,2,3),(4,5),(1), 子序列和分别为7，9，1 最大值为9
 * 分法2: S1,S2,S3分别划分为(2,2,3),(4),(5,1), 子序列和分别为7，4，6 最大值为7
 *
 * 在一次划分中,考虑一个x， 使x满足: 对任意的Si 都有 Si <= x, 也就是说，x 是所有Si中
 * 的最大值。题目要求找到这个最小的x, 这就是最大值最小化
 * 简单做法:
 * 枚举每个x,用贪心法每次从左向右尽量多划分元素，S(i) 不能超过x,划分的子序列不能超过m个.
 *
 * 二分法解法:
 * 在[max, sum]中查找满足条件的x, 其中max是序列中最大元素，sum 是所有元素的和
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2023/3/17
 */
public class SequenceDivision {


    public static void main(String[] args) {
        SequenceDivision instance = new SequenceDivision();
        int[] s = {2,2,3,4,5,1};
        System.out.println(instance.twoPointerSearch(s));
    }

    /**
     * 使用双指针实现, 子序列数量固定为3
     * 这里前提条件是 序列s中没有负数
     *
     * @param s
     * @return
     */
    public long twoPointerSearch(int[]s){
        int len = s.length;
        if(len < 3) return -1;
        // 构造sums数组
        long[] sums = new long[len];
        sums[0] = s[0];
        for(int i=1; i<len; i++){
            sums[i] = sums[i-1] + s[i];
        }
        long minMaxS = Long.MAX_VALUE;

        for(int i=0; i < len-2; i++){
            for(int j=i+1; j < s.length-1; j++){
                long s1 = sums[i];
                long s2 = sums[j] - sums[i];
                long s3 = sums[len-1] - sums[j];
                long max = Math.max(Math.max(s1, s2), s3);
                minMaxS = max < minMaxS ? max : minMaxS;
            }
        }
        return minMaxS;
    }
}
