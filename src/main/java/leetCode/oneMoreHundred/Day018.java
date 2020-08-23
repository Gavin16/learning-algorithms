package leetCode.oneMoreHundred;

import java.util.*;

/**
 * @Date: 2020年8月17日
 * ==============================================================================
 * 621. 任务调度器
 * ==============================================================================
 * 给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。
 * 任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
 *
 * 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 *
 * 你需要计算完成所有任务所需要的最短时间。
 *
 *  
 *
 * 示例 ：
 *
 * 输入：tasks = ["A","A","A","B","B","B"], n = 2
 * 输出：8
 * 解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
 *      在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
 *  
 *
 * 提示：
 *
 * 任务的总个数为 [1, 10000]。
 * n 的取值范围为 [0, 100]。
 * ==============================================================================
 */
public class Day018 {


    public static void main(String[] args) {
        char[] tasks = {'A','B','A','B','A','B','A'};
        System.out.println(new Day018().leastInterval(tasks,2));

        System.out.println(new Day018().leastInterval2(tasks,2));

        System.out.println(new Day018().leastInterval3(tasks,2));
    }


    /**
     * @Title: 621. 任务调度器
     * @Version: 版本1 数组排序解法
     *
     * (1) 任务输入数组为 'A' 到 ‘Z’ 范围内的字符, 很容易的想到可以使用长度为16的数组来统计不同任务的个数
     * 而不是使用 散列
     *
     * (2) 当 n 小于任务种类数时,需要有一种策略提前把出现次数较多的任务先执行 否则到最后就会出现出现次数较多的少数几个(假设为k个)任务
     * 每次执行需要占用 n+1 的执行时间，单个轮次就造成了 (n+1 - k) 的时间浪费; 现在这样就很有可能不是最短执行时间了。
     * 因此 每次执行完一轮(n+1个时间单位)后,就重新对任务出现次数做一次排序，然后优先执行出现次数最高的n+1个任务(如果还存在n+1个的话)
     * 执行完之后对应的任务所在槽位值-1；这样做可以确保每次执行都是n+1 的时间范围内多执行任务 得到执行时间也就是最短的执行时间。
     *
     * 需要注意的是: 一开始数组下标时可以和任务对应起来的，但是经过数组排序之后下标就不再和任务对应了；
     * 但是对于统计执行时间而言,每个时间上执行的是什么任务并不影响结果。
     *
     * (3) 同类任务间隔长度为 n 意味着每 n+1 个时间范围内不能出现重复的任务，但是由于 n的取值范围为[0-100],
     * n 可能大于26，因此除非所有任务都执行完了，从一开始就有可能有需要有"待命" 的空闲时间出现在 时间长度为(n+1)
     * 的轮询中
     *
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        int[] taskMap = new int[26];
        for(char c : tasks){
            taskMap[c-'A'] += 1;
        }
        int minTime = 0;
        Arrays.sort(taskMap);
        while(taskMap[25] > 0){
            int i = 0;
            while(i <= n){
                if(i <= 25 && taskMap[25 - i] > 0){
                    taskMap[25 - i] -= 1;
                }else if(taskMap[25] == 0){
                    // 针对最后一次循环时只要执行完最后一个任务,就应该结束计数
                    break;
                }
                i++;
                minTime++;
            }
            Arrays.sort(taskMap);
        }
        return minTime;
    }

    /**
     * @Title: 621. 任务调度器
     * @Version: 版本2 优先队列解法
     *
     * 利用优先队列大顶堆的堆化排序的功能替代手动排序
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval2(char[] tasks, int n) {
        if(null == tasks || tasks.length == 0) return 0;
        int[] map = new int[26];
        for(char ch : tasks){
            map[ch - 'A'] += 1;
        }

        PriorityQueue<Integer> queue = new PriorityQueue(Comparator.reverseOrder());
        for(int cnt : map){
            if(cnt > 0) queue.add(cnt);
        }

        int time = 0;
        while(!queue.isEmpty()){
            int i = 0;
            List<Integer> list = new ArrayList<>();
            while(i <= n){
                if(!queue.isEmpty() && queue.peek() > 0){
                    int poll = queue.poll();
                    if(poll > 1) list.add(poll - 1);
                }else if(list.size() == 0){
                    break;
                }
                i++;
                time++;
            }
            queue.addAll(list);
        }
        return time;
    }


    /**
     * @Title: 621. 任务调度器
     * @Version: 版本3 分析优化解法
     *
     * 考虑对任务组做排序后，一定存在一个出现次数最多的任务
     * 假定 A出现的次数最多达到了 m次 ， 那么可以确定的是
     * 总执行时间至少为 (m-1)*(n+1) + 1 对应剩余的空闲时间最多为  (m-1)*n
     * 接下来分情况讨论如何将剩下的空余时间分配给其它任务：
     *
     * (1) 对于出现次数第二多的任务 如果次数也为 m ,那么 第m轮多执行的次数相应的要再+1 ，同时空闲时间 (m-1)*n 内
     *  相应的减去 m-1 的空闲时间
     * (2) 对于出现次数等于 m-1 的任务，该任务整好占据空闲时间的所有轮数(m-1轮)
     * (3) 对于出现次数小于 m-1 的任务，假设出现了 p 次，该任务执行完之后可以 将剩下的 m-1 - p 的空闲时间再留给后续
     *
     * 以此类推，最终所有的任务都可以在空闲时间内分配完,并且总的执行时间 为(m-1)*(n-1)+1 再加上其它需要最后一轮执行的相应的任务数
     *
     * 值得需要注意的是，上面的分析只考虑了 n+1 >= 任务种数  的情况，对于 n+1 < 任务种数 的情况，譬如： 任务有三类 'A','B','C'
     * n = 1 的情况； 以此为例假设 'A'出现了 3次， 'B' 出现了 2次， 'C'出现了 2 次
     *
     * 这时 (m-1)*n = 2*1 = 2 ，空闲时间为 2显然不够任务 'B','C' 来完成分配，这时就需要在执行的后面增加对应的执行轮数来完成任务了
     *
     * 之前的假设是CPU 执行m轮就能完成任务,现在的情况是：
     * A : 3, B : 2, C : 2 ； 一共七个任务，当n=1 的时候，至少需要执行 4轮，7 个时间单位才能完成；
     * 那么这种 n+1 < 任务种数的情况 能否归结到上述分析的情况(1) 中呢？ 答案是不可以， 但是
     *
     * 但是对于 超出n+1 范围的任务,我们可以通过在每轮后面加上该任务，这样就变成了什么样了呢？
     * 以 A:3,B:3,C:3; n = 1 为例； 若我们严格按照每轮执行n+1 = 2个任务的方式来分配， 执行的过程为：AB|AB|AB|C*|C*|C 总时间为 11
     * 那你一定会说,这个可以通过诸如: AB|AC|BC|AB|C 的方式来优化，总时间又到了 9， 但是这种优化显然不是那么的直观，实现起来的复杂度不可控。
     *
     * 其实只要再回过头看一下题目的要求，题目要求是"相同任务之间必须要有时间长度为n 的冷却期"，这里并没有说冷却期不能大于n，因此通过在每轮后面
     * 再加上多出来的任务也完全符合要求, 这样执行的过程就变成了： ABC|ABC|ABC 总次数为 9 , 爽歪歪，对于 n+1 < 总次数的情况也考虑进来了。
     *
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval3(char[] tasks, int n) {
        int[] taskMap = new int[26];
        for(char c : tasks) taskMap[c - 'A'] ++;

        Arrays.sort(taskMap);
        int m = taskMap[25], id = 24;
        int time = (m-1)*(n+1) + 1,spare = (m-1)*n;
        while(id >= 0){
            int curr = taskMap[id--];
            if(curr == m){
                curr--; time++;
            }
            int add = spare - curr > 0 ? 0 : Math.abs(spare - curr);
            spare = spare - curr > 0 ? spare - curr : 0;
            time += add;
        }

        return time;
    }


}
