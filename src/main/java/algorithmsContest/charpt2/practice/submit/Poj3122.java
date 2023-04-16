package algorithmsContest.charpt2.practice.submit;

/**
 * @className: Poj3122
 * @description:
 * 问题描述:
 * 主人过生日, m个人过来庆祝，有n块半径不同的圆形蛋糕，由m+1个人(加上主人)分
 * 要求每人的蛋糕必须一样重， 而且是一整块(不能是几个蛋糕碎块，也就是说，每个人的蛋糕都是从一块圆蛋糕中切下来的完整一块)
 * 问每个人能分到的最大蛋糕是多大？
 *
 * 输出: 对于每个输入，输出一行表示答案，保留4位小数
 * 注: 1 <= n, m <= 10000
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2023/3/18
 */
public class Poj3122 {


    double eps = 1e-5;

    public static void main(String[] args) {
        Poj3122 instance = new Poj3122();
        int m = 10;
        int[] r = {1,1,2,1,2};
        instance.cutCake(r, m);
    }

    private boolean check(double[] area, double mid, int m){
        int len = area.length;
        int cnt = 0;
        for(int i=0; i < len; i++){
            cnt += (int)(area[i]/mid);
        }
        if(cnt >= m) return true;
        else return false;
    }

    public void cutCake(int[] radius, int m){
        double pi = Math.PI;
        int n = radius.length;
        double[] area = new double[n];
        double maxA = 0;
        for(int i=0; i < n; i++){
            area[i] = pi * radius[i] * radius[i];
            if(area[i] > maxA) maxA = area[i];
        }

        double left = 0, right = maxA;
        while(right - left > eps){
//        for(int i=0; i < 50; i++){
            double mid = left + (right - left)/2;
            if(check(area, mid, m)){
                left = mid;
            }else right = mid;
        }
        System.out.printf("%.4f\n", left);
    }

}
