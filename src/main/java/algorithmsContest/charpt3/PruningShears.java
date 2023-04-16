package algorithmsContest.charpt3;

import java.util.*;

/**
 * @className: PruningShears
 * @description: 搜索枝剪
 * --------------------------------------------------------------------------------------------------------------------
 * 例3.2 跳蚱蜢
 * 问题描述:
 * 有9个盘子围成一个圆圈，其中8个盘子内装着8只蚱蜢，有一个是空的。
 * 把这些蚱蜢顺时针编号为1~8 ，每只蚱蜢都可以跳到相邻的空盘中，也可以再用点力。
 * 越过一个相邻的蚱蜢跳到空盘中。如果要使蚱蜢们的队形改为按照逆时针排列。并且保持空盘的位置不变
 * (也就是1和8互换，2和7互换，...) 至少要经过多少次跳跃？
 *
 * --------------------------------------------------------------------------------------------------------------------
 * 例3.3 catch that cow
 * 在一条直线上，奶牛在K位置，农夫在N位置， 农夫想要抓到牛，他有3种移动方法
 * 如果他在X位置， 他每次可以移动到 X-1, X+1, 2X的位置。问农夫最小要移动多少次才能从N到达K?
 *
 * 输入: 两个整数 N, K
 * 输出: 最少移动次数
 *
 * 提示: 0 <= N, K <= 100_000
 * --------------------------------------------------------------------------------------------------------------------
 * 例3.4 数字三角形
 * 问题描述:
 * 写出一个1~n的序列ai, 然后每次讲相邻两个数相加, 构成新的序列，在对新序列进行这样的操作，显然每次构成的序列都比上一次
 * 的序列长度少1。 直到只剩下一个数字为止。下面是一个列子;
 *
 * 现在倒着玩这个游戏，如果直到n, 知道最后得到的数字sum, 请求出最初序列ai,即为 1~n 的一个排列。
 * 若答案有多种可能，则输出字典序最小的那个。
 *
 * 提示: n <= 12, sum <= 12345
 *
 * 输入: 输入两个正整数 n 和 sum
 * 输出: 输出字典序最小的那个序列
 * --------------------------------------------------------------------------------------------------------------------
 * 例3.5 吃奶酪
 * 问题描述:
 * 房间里有n块奶酪，给出每块奶酪的坐标，一只老鼠要把它们都吃掉，它的初始坐标是(0,0). 问至少要跑多少距离? 1 <= n <= 15;
 *
 * 输入: 第一行输入一个整数， 表示奶酪的数量n; 第 2 - n+1 行中，每行输入两个实数， 第 i+1 行的实数标识第i块奶酪的坐标(xi,yi)
 * 输出: 输出一个实数，表示要跑的最远距离， 保留两位小数。
 *
 * --------------------------------------------------------------------------------------------------------------------
 * 例3.6 Tempter of the bone
 * 问题描述:
 * 一个迷宫有N * M 格，有一些格子是地板，能走；有一些格子是障碍，不能走。 给一个起点S 和 一个终点 D. 一只小狗从S出发，
 * 每步走一块地板，在每块地板不能停留，而且走过的地板都不能再走。给定一个T, 问小狗能正好走出T 步到达D吗？
 *
 * 输入: 有很多测试样例。每个测试中，第1行输入整数N, M, T(1 < N, M < 7, 0 < T <50).
 * 后面N行中，每行输入M个字符， 有这些字符可以输入： 'X': 墙， 'S': 起点； 'D': 终点； '.':地板。
 * 最后一行输入 '0 0 0' 表示输入结束。
 *
 * 输出: 每个测试，如果够能到达, 输出 YES, 否则输出 NO
 *
 *
 *
 * --------------------------------------------------------------------------------------------------------------------
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2023/4/16
 */
public class PruningShears {


    public static void main(String[] args) {
        PruningShears instance = new PruningShears();
        instance.LocustJump();

        // 例3.3
        int ans = instance.CatchThatCow(2, 18);
        System.out.println(ans);

    }



    private static final int MAX_RANGE = 100_000;
    /**
     * 例3.3 抓住那只牛
     * 搜索所有从 N 到 K的路径，找出最短的那条
     *
     * @param n
     * @param k
     * @return
     */
     public int CatchThatCow(int n, int k){
        int min = Integer.MAX_VALUE;
        Queue<CowNode> queue = new LinkedList<>();
        queue.offer(new CowNode(n, 0));

        while(!queue.isEmpty()){
            CowNode poll = queue.poll();
            int pos = poll.pos;
            int step = poll.step;

            if(pos == k){
                min = Math.min(step, min);
                continue;
            }
            if(step < min){
                if(pos + 1 <= MAX_RANGE){
                    queue.offer(new CowNode(pos + 1, step + 1));
                }
                if(pos - 1 >= 0){
                    queue.offer(new CowNode(pos - 1, step + 1));
                }
                if(2 * pos <= MAX_RANGE){
                    queue.offer(new CowNode(2 * pos, step + 1));
                }
            }
        }
        return min;
     }

     class CowNode{
         int pos;
         int step;
         CowNode(int pos, int step){
             this.pos = pos;
             this.step = step;
         }
     }


    class JumpNode{
        String code;
        int step;
        JumpNode(String code, int step){
            this.code = code;
            this.step = step;
        }
    }

    /**
     * 例3.1 跳蚱蜢
     * BFS 方式搜索
     * @return
     */
    public int LocustJump(){
        Queue<JumpNode> queue = new LinkedList<>();
        Set<String> codeSet = new HashSet<>();
        queue.offer(new JumpNode("012345678", 0));
        codeSet.add("012345678");

        int step = 0;
        char[] array;
        while(!queue.isEmpty()){
            JumpNode poll = queue.poll();
            String code = poll.code;
            step = poll.step;
            if(code.equals("087654321")){
                System.out.println("total steps: " + step);
                break;
            }

            int pos;
            for(pos = 0; pos < 10; pos++){
                if(code.charAt(pos) == '0') break;
            }
            // 蚱蜢每次跳在空盘附近只有四种跳法
            for(int j = pos - 2; j < pos + 2; j++){
                int k = (j + 9) % 9;
                if(k == pos) continue;
                // 空盘左右两边各两个位置往空盘跳
                array = code.toCharArray();
                char tmp = array[k];
                array[k] = array[pos];
                array[pos] = tmp;

                String newCode = String.valueOf(array);
                if(!codeSet.contains(newCode)){
                    codeSet.add(newCode);
                    queue.offer(new JumpNode(newCode, step + 1));
                }
            }
        }
        return step;
    }

    /**
     * 例3.4 数字三角形
     * @param n
     * @param sum
     * @return
     */
    public int[] DigitalTriangle(int n, int sum){
        return null;
    }
}
