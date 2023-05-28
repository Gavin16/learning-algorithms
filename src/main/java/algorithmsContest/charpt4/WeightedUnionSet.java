package algorithmsContest.charpt4;

/**
 * @className: WeightedUnionSet
 * @description: 带权并查集
 *
 * 例4.2 How many answers are wrong
 * 问题描述: 给出区间[a,b] 区间和为 v。输入m组数据，每输入一组，判断此组条件
 * 是否与前面冲突，最后输出与前面冲突的数据的个数. 例如，先给出区间[1,5], 区间和为
 * 100; 再给出区间[1,2], 区间和为200， 肯定有冲突。
 *
 * 输入: 第1行输入两个整数n和m(1 <= n <= 200_000, 1 <= m <= 40_000),表示n个整数，
 * m组数据。第2~m+1 行中，每行输入3个整数 ai,bi,vi, 表示[ai,bi]区间和为vi.
 * 0 < ai <= bi <= n.
 *
 * 输出: 输出一个整数，表示冲突数据个数。
 *
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2023/5/22
 */
public class WeightedUnionSet {

    final int N = 200_010;

    int[] s;
    int[] d;
    int ans = 0;

    void init_set(){
        s = new int[N];
        d = new int[N];
        for(int i = 0; i < N; i++){
            s[i] = i;
            d[i] = 0;
        }
    }

    int find_set(int x){
        if(x != s[x]){
            int t = s[x];
            s[x] = find_set(s[x]);
            d[x] += d[t];
        }
        return s[x];
    }

    void merge_set(int a, int b, int v){
        int rootA = find_set(a), rootB = find_set(b);
        if(rootA == rootB){
            if(d[a] - d[b] != v) ans++;
        }else{
            s[rootA] = rootB;
            d[rootA] = d[b] - d[a] + v;
        }
    }

    public int wrongAnswerCount(int[][] inputs){
        init_set();
        for(int[] rangeSum : inputs){
            int a = rangeSum[0];
            int b = rangeSum[1];
            int v = rangeSum[2];
            merge_set(a, b, v);
        }
        return ans;
    }

    public static void main(String[] args) {
        WeightedUnionSet unionSet = new WeightedUnionSet();
        int[][] inputs = {
                {1, 5, 100},
                {1, 2, 200}
        };
        int res = unionSet.wrongAnswerCount(inputs);
        System.out.println(res);
    }
}
