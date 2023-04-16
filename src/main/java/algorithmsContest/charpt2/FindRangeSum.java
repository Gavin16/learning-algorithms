package algorithmsContest.charpt2;

import lombok.Data;
import utils.ArrayUtil;

import java.util.Arrays;


/**
 * @className: FindRangeSum
 * @description:
 *
 * 尺取法: 同向扫描
 *
 * 例2.3 寻找区间和
 * 问题描述: 给定一个长度为 n 的数组 a[] 和一个数s, 在这个数组中找一个区间, 使这个区间的数组之和
 * 等于 s。输出区间的起点和终点位置。
 * 说明: 输入样例的第1行是 n = 15, 第2行使数组a[], 第3行是区间和 s = 6。 输出样例共有4种情况。
 * 输入样例:
 * 15
 * 6 1 2 3 4 6 4 2 8 9 10 11 12 13 14
 * 6
 * 输出样例:
 * 0 0
 * 1 3
 * 5 5
 * 6 7
 *
 * 例2.4 找相同数对
 * 问题描述: 给出一串数以及一个数字C, 要求计算出所有 A - B = C 的数对的个数
 * (不同位置的数字一样的数对算不同的数对)
 *
 * 数据范围:
 * 1 <= n <= 2*10^5; 所有输入数据绝对值小于 2^30
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2023/1/15
 */
@Data
public class FindRangeSum {

    public static void main(String[] args) {
        FindRangeSum instance = new FindRangeSum();
        int len = 15;
        int[] a = {6,1,2,3,4,6,4,2,8,9,10,11,12,13,14};
//        int rangSum = 6;
//        instance.findRangeSum(len, a, rangSum);
//        instance.removeArrayRepeat(a);

        instance.findPair(a, 2);
    }

    public void findRangeSum(int len, int[]a, int rangSum){
        int currSum = a[0];
        for(int j = 0, k = 0; k < len; ){
            if(currSum >= rangSum){
                if(currSum == rangSum)
                    System.out.printf("%d %d\n",j, k);
                currSum -= a[j++];
                if(j > k && j < len){
                    currSum = a[j];
                    k++;
                }
            }
            if(currSum < rangSum){
                k++;
                if(k < len) currSum += a[k];
            }
        }
    }

    /**
     * 移除数组中重复数字
     * 尺取法: 双指针同向扫描
     */
    public void removeArrayRepeat(int[] aa){
        Arrays.sort(aa);
        int len = aa.length;
        int i = 0, j = 0;
        while(j < len){
            if(aa[j] != aa[i]){
                i += 1;
                aa[i] = aa[j];
            }
            j++;
        }
        int[] res = Arrays.copyOf(aa, i+1);
        for(int n : res) System.out.printf(n + " ");
    }

    /**
     * 例2.4 找相同数对
     * 排序 + 三指针
     * 由于不同位置的数字一样的数对算不同的数对
     * 这里使用两个指针来代表相同值的区间
     *
     * @param a
     * @param c
     */
    public void findPair(int[] a, int c){
        Arrays.sort(a);
        ArrayUtil.showArray(a);
        int len = a.length;
        int cnt = 0;
        for(int i=0,j=0,k=0; i<len; i++){
            while(j<len && a[j] - a[i] < c) j++;
            while(k<len && a[k] - a[i] <= c) k++;
            if(j<len && a[j] - a[i] == c && a[k-1] - a[i] == c)
                cnt += (k - j);
        }
        System.out.println(cnt);
    }

}
