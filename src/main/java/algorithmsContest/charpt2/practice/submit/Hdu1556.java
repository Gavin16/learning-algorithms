package algorithmsContest.charpt2.practice.submit;

import java.util.Scanner;

/**
 * @className: P1556
 * @description:
 *
 * 问题描述：
 *
 * N个气球排成一排,从左到右依次编号为 1,2,3,..., N. 每次给定两个整数L 和 R, 某人从气球L
 * 开始到气球R,依次给每个气球涂上依次颜色。但是N次以后他已经忘记了第i个气球已经涂过几次颜色了，
 * 你能帮他算出每个气球被涂过几次颜色吗?
 *
 * 输入:
 * 每个测试实例第1行，输入一个整数 N(N <= 100000); 接下来的N行中，每行输入两个整数L 和 R
 * (1 <= L <= R <= N)。当N=0时，输入结束。
 *
 * 输出：每个测试实例输出一行，包括N个整数，第i个数代表第i个气球总共被涂色的次数。
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2023/3/28
 */
public class Hdu1556 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int n = N;
        int[] diff = new int[N+2];
        while(n > 0){
            int L = scanner.nextInt();
            int R = scanner.nextInt();
            diff[L]++;
            diff[R+1]--;
            n--;
        }
        int[] a = new int[N + 2];
        for(int i = 1; i <= N; i++){
            a[i] = a[i-1] + diff[i];
            if(i < N) System.out.printf("%d ", diff[i]);
            else System.out.printf("%d\n", diff[i]);
        }
    }

}
