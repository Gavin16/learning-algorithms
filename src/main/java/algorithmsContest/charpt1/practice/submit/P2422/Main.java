package algorithmsContest.charpt1.practice.submit.P2422;

/**
 * @className: Main
 * @description:
 *
 * 良好的感觉
 *
 * KKK做了一个人体感觉分析器,每一天，人都有一个感受值Ai, Ai 越大表示感觉越舒适.
 * 在一段时间内[i,j]， 人的舒适程度定义为[i,j] 中最不舒服的哪一天的感受值 * [i,j] 中每一天感受值的和。
 * 现在给出kkk 在连续N天中的感受值。请问在那一段时间，kkk感受最舒适？
 *
 * 输入格式
 * 第一行为N, 代表数据记录的天数
 * 第二行N个整数, 代表每一天的感受值
 *
 * 输出格式
 * 一行,表示在最舒适的一段时间中的感受值
 *
 * 输入输出样例
 * 6
 * 3 1 6 4 5 2
 *
 * 输出
 * 60
 *
 * 说明/提示
 * KKK 最开心的一段时间是第3天到第5天，开心值：(6 + 4 + 5) * 4 = 60
 * 对于30% 的数据， 1 <= N <= 100
 * 对于70% 的数据， 1 <= N <= 2000
 * 对于100% 的数据， 1 <= N <= 1000_000, 感受值 1 <= 感受值 <= 1000_0000;
 *
 *
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2022/12/29
 */
public class Main {


    /**
     * TODO 考虑使用单调栈实现
     *
     * @param args
     */
    public static void main(String[] args) {

    }

}
