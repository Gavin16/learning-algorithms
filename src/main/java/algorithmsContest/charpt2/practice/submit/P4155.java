package algorithmsContest.charpt2.practice.submit;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @className: P4155
 * @description:
 *
 * 问题描述:
 * 边境上有m个边防站围城一圈，顺时针编号为 1~m. 有n名战士, 每名战士常驻两个站点，能在两个站之间移动。
 * 局长有一个"国旗计划"， 让边防战士举着国旗环绕一圈。局长想知道至少需要多少战士才能完成"国旗计划"，
 * 并且他想知道在某个战士必须参加的情况下，至少需要多少名边防战士。
 *
 * 输入: 第1行输入两个正整数 n 和 m, 表示战士数量和边防站数量。 后面n行中，每行输入两个正整数，其中第i行的ci
 * 和 di 表示i 号边防战士常驻的两个边防站编号，沿顺时针从ci 到 di 边防站是他的移动区间。数据保证整个边境线是
 * 可被覆盖的。所有战士的移动区间互相不包含。
 *
 * 输出: 输出一行，包含n个正整数，其中第 j个正整数表示 j 号战士必须参加的前提下，至少需要多少名边防战士才能顺利
 * 完成国旗计划。
 *
 * 数据范围: n <= 2*10^5, m < 10^9, 1 <= ci, di <= m
 *
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2023/3/19
 */
public class P4155 {


    /**
     * 定义 go[][] 二维数组用来记录从不同位置出发经过某些次数的跳转后到达的位置
     * 例如: go[s][j]表示 从s经过2^j 个最远区间跳动之后到达的位置
     *
     * @param m
     * @param n
     * @param w
     * @return
     */
    public int[] planOfNationFlag(int m, int n, Warrior[] w){

        Arrays.sort(w, Comparator.comparingInt(o -> o.L));
        int n2 = n;
        // 拆环加倍成一条链(方便每一个位置作为起点时，后面都有n-1个位置)
        for(int i = 1; i <= n; i++){
            n2++;
            w[n2] = w[i];
            w[n2].L = w[i].L + m;
            w[n2].R = w[i].R + m;
        }
        // 贪心+倍增法计算所有位置的区间
        int N = 2*n + 1;
        int[][] go = new int[N][20];

        int next = 1;
        for(int i = 1; i <= n2; i++){
            while (next <= n2 && w[next].L <= w[i].R)
                next++;
            go[i][0] = next - 1;
        }
        for(int i = 1; (1 << i) <= n; ++i){
            for(int s = 1; s <= n2; s++){
                go[s][i] = go[go[s][i-1]][i-1];
            }
        }
        return null;
    }

    private


    class Warrior{
        int id, L, R;
    }

}
