package algorithmsContest.charpt2.practice.submit;

/**
 * @className: P3382
 * @description:
 *
 * 模板三分法
 * 问题描述:
 * 给出一个n次函数, 保证在区间[l,r]内存在一点x, 使函数在[l, x]上单调递增，[x,r]上单调递减。试求出x的值
 *
 * 输入: 第1行依次输入一个正整数 N, 两个实数l和r, 第二行输入N+1个实数，依次表示该N次函数从高到低各项的系数。
 * 输出： 四舍五入保留5位小数
 *
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2023/3/18
 */
public class P3382 {

    public static void main(String[] args) {
        P3382 instance = new P3382();
        double[] a = {5,4,-1};
        double l = 1, r = 3;

        System.out.printf("%.5f\n", instance.findExtremePoint(a, l, r));
    }

    // double[] a 为多项式系数，按照a[0]存0阶，a[1]存1阶，a[n] 存n阶方式进行存储
    private double func(double x, double[]a){
        double y = 0;
        for(int i=a.length -1; i >= 0; i--){
            y = y * x + a[i];
        }
        return y;
    }

    private double eps = 1e-6;

    public double findExtremePoint(double[]a, double l, double r){
        double left = l, right = r;
        while(right - left > eps){
            double mid1 = left + ((right - left)/3);
            double mid2 = right - ((right - left)/3);
            if(func(mid1, a) < func(mid2, a)){
                left = mid1;
            }else{
                right = mid2;
            }
        }
        return left;
    }


    /**
     * 例2.11 三分法求极值
     * 问题描述: 在直角坐标系中，有一条抛物线 y = a*x^2 + b*x + c 和 一个点P(x,y)
     * 求点P到抛物线的最短距离 d;
     *
     * 其中 -200 <= a, b, c, x, y <= 200
     *
     * (1) 对抛物线求导，若点P到抛物线的上一点构成向量和导数向量垂直，则该点即为距离最短的d点
     * (2) 根据抛物线开口方向及P点位置，判断是否存在[l,r]区间，在该区间上，抛物线上的点到P点的距离满足
     *     在[l,x] 单调递增 [x,r] 区段上单调递减； 或者在[l,x]上单调递减在[x,r]区段上单调递增
     *
     * @param a
     * @param x
     * @param y
     * @return
     */
    public double distToParabola(int[] a, int x, int y){
        return -1;
    }


}
