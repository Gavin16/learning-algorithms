package algorithmsContest.charpt2.practice.submit;

import java.util.Arrays;

/**
 * @className: P1824
 * @description:
 *
 * 例2.8 进击的奶牛
 * 问题描述:
 * 在一根很长的直线上，指定n个坐标点(x1,x2,...,xn)。有c头牛，安排每头牛站在其中一个点(牛棚)上,
 * 这些牛喜欢打架,所以尽量距离远一些。求相邻的两头牛之间距离的最大值。
 *
 * 输入:
 * 第一行输入两个用空格隔开的数字n 和 c; 第2 ~ n+1行中，每行输入一个整数，表示每个点的坐标。
 * 输出:
 * 相邻两头牛之间距离能达到的最大值(注意每头牛放入哪个牛棚，并没有要求)
 *
 * 数据范围: 2 <= n <= 100_000, 0 <= xi <= 1000_000_000
 *
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2023/3/17
 */
public class P1824 {


    public static void main(String[] args) {
        P1824 instance = new P1824();

        int[] a = {};
        int n = a.length, c = 8;
        System.out.println(instance.maxBullDistance(a, n, c));
    }

    /**
     * 牛棚坐标列表中 n 个牛棚位置是预先确定好的
     * 若要放入c头牛, 事先牛棚固定好的位置中，判断相邻距离大于 dist 的牛棚个数是否超过c
     *
     * 若超过c 说明间隔为 dist 的情况下, 是可以容纳下所有的牛的
     * 若没有超过c 说明间隔为 dist 的情况下，不能容纳所有的牛
     *
     * 这个dist 具体最大能取多大可以通过二分查找方式确定下来:
     * (1) 若dist 太大，则采用二分法缩短距离
     * (2) 若dist 较小，则二分法增大dist
     * 最后得到的是二分查找中能放入c头牛的dist的最大值
     *
     * @param x
     * @param n
     * @param c
     * @return
     */
    public int maxBullDistance(int[] x, int n, int c){
        if(c > n) return -1;
        Arrays.sort(x);
        int maxPos = x[x.length - 1];
        int left = 0, right = maxPos;
        int maxDist = 0;
        while(left < right){
            int mid = left + ((right - left) >> 1);
            if(check(x,n,c,mid)){
                maxDist = mid;
                left = mid + 1;
            }else{
                // 取满足条件的最大值,需要right = mid
                right = mid;
            }
        }
        return maxDist;
    }

    /**
     * 判断x是否能容纳相邻距离为 dist 的c头牛
     * @param x
     * @param n
     * @param c
     * @param dist
     * @return
     */
    private boolean check(int[]x, int n, int c, int dist){
        int place = 0;
        int cnt = 0;
        for(int i=1; i < n; i++){
           if(x[i] - x[place] > dist){
                cnt++;
                place = i;
           }
        }
        if(cnt > c) return true;
        else return false;
    }
}
