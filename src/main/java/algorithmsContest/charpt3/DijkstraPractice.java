package algorithmsContest.charpt3;

import java.util.*;

/**
 * @className: DijkstraPractice
 * @description: Dijkstra 练习题
 *
 * ------------------------------------------------------------------------------------------------------------
 * 787. K 站中转内最便宜的航班
 * 有 n 个城市通过一些航班连接。给你一个数组 flights ，其中 flights[i] = [fromi, toi, pricei] ，
 * 表示该航班都从城市 fromi 开始，以价格 pricei 抵达 toi。现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，
 * 你的任务是找到出一条最多经过 k 站中转的路线，使得从 src 到 dst 的 价格最便宜 ，并返回该价格。 如果不存在这样的路线，则输出 -1。
 * 
 * 输入:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * 输出: 200
 * 
 * 输入:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 0
 * 输出: 500
 * 
 * 提示：
 * 
 * 1 <= n <= 100
 * 0 <= flights.length <= (n * (n - 1) / 2)
 * flights[i].length == 3
 * 0 <= fromi, toi < n
 * fromi != toi
 * 1 <= pricei <= 104
 * 航班没有重复，且不存在自环
 * 0 <= src, dst, k < n
 * src != dst
 * 
 * ------------------------------------------------------------------------------------------------------------
 * 1928. 规定时间内到达终点的最小花费
 *
 * 一个国家有 n 个城市，城市编号为 0 到 n - 1 ，题目保证 所有城市 都由双向道路 连接在一起 。道路由二维整数数组 edges 表示，
 * 其中 edges[i] = [xi, yi, time_i] 表示城市 xi 和 yi 之间有一条双向道路，耗费时间为 time_i 分钟。
 * 两个城市之间可能会有多条耗费时间不同的道路，但是不会有道路两头连接着同一座城市。
 * 
 * 每次经过一个城市时，你需要付通行费。通行费用一个长度为 n 且下标从 0 开始的整数数组 passingFees 表示，
 * 其中passingFees[j]是你经过城市 j 需要支付的费用。一开始，你在城市 0 ，你想要在 maxTime 分钟以内(包含 maxTime 分钟)到达城市n-1 
 * 旅行的 费用 为你经过的所有城市 通行费之和 （包括 起点和终点城市的通行费）。
 * 给你 maxTime，edges 和 passingFees ，请你返回完成旅行的 最小费用 ，如果无法在 maxTime 分钟以内完成旅行，请你返回 -1 。
 * 
 * 示例 1：
 * 输入：maxTime = 30, edges = [[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]], passingFees = [5,1,2,20,20,3]
 * 输出：11
 * 解释：最优路径为 0 -> 1 -> 2 -> 5 ，总共需要耗费 30 分钟，需要支付 11 的通行费。
 * 
 * 示例 2：
 * 输入：maxTime = 29, edges = [[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]], passingFees = [5,1,2,20,20,3]
 * 输出：48
 * 解释：最优路径为 0 -> 3 -> 4 -> 5 ，总共需要耗费 26 分钟，需要支付 48 的通行费。
 * 你不能选择路径 0 -> 1 -> 2 -> 5 ，因为这条路径耗费的时间太长。
 * 
 * 示例 3：
 * 
 * 输入：maxTime = 25, edges = [[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]], passingFees = [5,1,2,20,20,3]
 * 输出：-1
 * 解释：无法在 25 分钟以内从城市 0 到达城市 5 。
 * 
 * 提示：
 * 1 <= maxTime <= 1000
 * n == passingFees.length
 * 2 <= n <= 1000
 * n - 1 <= edges.length <= 1000
 * 0 <= xi, yi <= n - 1
 * 1 <= time_i <= 1000
 * 1 <= passingFees[j] <= 1000 
 * 图中两个节点之间可能有多条路径。
 * 图中不含有自环。
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2023/5/13
 */
public class DijkstraPractice {


    public static void main(String[] args) {
        DijkstraPractice instance = new DijkstraPractice();
//        int n1 = 3, src1 = 0, dst1 = 2, k1 = 1;
//        int[][] edges1 = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
//
//        int n2 = 3, src2 = 0, dst2 = 2, k2 = 0;
//        int[][] edges2 = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
//
//        int n3 = 5, src3 = 2, dst3 = 1, k3 = 1;
//        int[][] edges3 = {{4, 1, 1}, {1, 2, 3}, {0, 3, 2}, {0, 4, 10}, {3, 1, 1}, {1, 4, 3}};
//
//        int n4 = 5, src4 = 0, dst4 = 2, k4 = 2;
//        int[][] edges4 = {{0, 1, 5}, {1, 2, 5}, {0, 3, 2}, {3, 1, 2}, {1, 4, 1}, {4, 2, 1}};

//        int cost1 = instance.findCheapestPrice1(n1, edges1, src1, dst1, k1);
//        int cost2 = instance.findCheapestPrice1(n2, edges2, src2, dst2, k2);
//        int cost3 = instance.findCheapestPrice1(n3, edges3, src3, dst3, k3);
//        int cost4 = instance.findCheapestPrice1(n4, edges4, src4, dst4, k4);
//        System.out.println(cost1);
//        System.out.println(cost2);
//        System.out.println(cost3);
//        System.out.println(cost4);


        // 1928. 规定时间内到达终点的最小花费
        int maxTime = 25;
        int[][] edges = {{0,1,10},{1,2,10},{2,5,10},{0,3,1},{3,4,10},{4,5,15}};
        int[] passingFees = {5,1,2,20,20,3};
        int ans = instance.minCost(maxTime, edges, passingFees);
        System.out.println(ans);

    }


    /**
     * 787. K 站中转内最便宜的航班
     * 
     * 使用邻接表描述图
     * 定义Node节点记录中转次数及费用
     * 
     * PS: 该版本执行效率太低,需要优化
     *
     * @param n
     * @param flights
     * @param src
     * @param dst
     * @param k
     * @return
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[][] dist = new int[k + 1][n];
        boolean[][] visit = new boolean[k + 1][n];

        Map<Integer, List<CityNode>> adjMap = new HashMap<>();
        for (int[] edge : flights) {
            List<CityNode> adjNodes = adjMap.getOrDefault(edge[0], new ArrayList<>());
            if (adjNodes.isEmpty()) adjMap.put(edge[0], adjNodes);
            adjNodes.add(new CityNode(edge[1], 0, edge[2]));
        }

        for (int i = 0; i <= k; i++) {
            int[] row = dist[i];
            Arrays.fill(row, Integer.MAX_VALUE);
            dist[i][src] = 0;
        }

        PriorityQueue<CityNode> queue = new PriorityQueue<>(
                Comparator.comparingInt(n2 -> n2.cost)
        );
        queue.offer(new CityNode(src, 0, 0));

        while (!queue.isEmpty()) {
            CityNode poll = queue.poll();
            int id = poll.id, cnt = poll.stepCnt, cost = poll.cost;

            if (cnt > k || visit[cnt][id]) continue;

            List<CityNode> cityNodes = adjMap.get(id);
            if (null == cityNodes || cityNodes.size() == 0) continue;
            for (CityNode node : cityNodes) {
                dist[cnt][node.id] = Math.min(dist[cnt][node.id], cost + node.cost);
                queue.offer(new CityNode(node.id, cnt + 1, cost + node.cost));
            }
            visit[cnt][id] = true;
        }
        int minDst = Integer.MAX_VALUE;
        for (int j = 0; j <= k; j++) {
            minDst = Math.min(minDst, dist[j][dst]);
        }
        return minDst == Integer.MAX_VALUE ? -1 : minDst;
    }

    static class CityNode {
        int id;
        int stepCnt;
        int cost;

        CityNode(int id, int cnt, int cost) {
            this.id = id;
            this.stepCnt = cnt;
            this.cost = cost;
        }
    }


    /**
     * 787. K 站中转内最便宜的航班
     * 动态规划解法
     * 定义 dp[i][j] 代表搭乘i次航班，到达ID为j的城市所需要的最小花费
     * i ∈[0, k+1], j ∈ [0, n-1]
     * dp[i][j] 满足如下递推关系:
     * dp[i][j] = Math.min (dp[i][j], dp[i-1][p] + g[p][j])
     * 其中g[p][j] 为从p城市直到j城市的花费
     *
     * @param n
     * @param flights
     * @param src
     * @param dst
     * @param k
     * @return
     */
    public int findCheapestPrice1(int n, int[][] flights, int src, int dst, int k) {
        int[][] dp = new int[k + 2][n];
        int INF = 100 * 10_000 + 1;
        for (int i = 0; i <= k + 1; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][src] = 0;

        for (int t = 1; t <= k + 1; t++) {
            for (int[] edge : flights) {
                int i = edge[0], j = edge[1], cost = edge[2];
                // 这里INF不能使用Integer.MAX_VALUE,否则会溢出
                dp[t][j] = Math.min(dp[t][j], dp[t - 1][i] + cost);
            }
        }
        int min = INF;
        for (int t = 1; t <= k + 1; t++) {
            min = Math.min(min, dp[t][dst]);
        }
        return min == INF ? -1 : min;
    }


    /**
     * 1928. 规定时间内到达终点的最小花费
     *
     * 状态转移方程
     * dp[t][j] = Math.min(dp[t][j], dp[t-time_cost][i] + passingFees[j])
     * 其中 time_cost 为从城市i到城市j的时间花费
     *
     * 注意: edge 为双向道路,因此实际边的数量为edge.length * 2; 该解法不能满足要求
     * @param maxTime
     * @param edges
     * @param passingFees
     * @return
     */
    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        final int INF = 1000 * 1000 + 1;
        int N = passingFees.length;

        int[][] fees = new int[maxTime+1][N];
        for(int i = 0; i <= maxTime; i++){
            Arrays.fill(fees[i], INF);
        }
        fees[0][0] = passingFees[0];

        // 邻接边转Map处理
        Map<Integer, List<int[]>> map = new HashMap<>();
        for(int[] edge : edges){
            int i = edge[0], j = edge[1], time = edge[2];
            List<int[]> orDefault = map.getOrDefault(i, new ArrayList<>());
            if(orDefault.isEmpty()) map.put(i, orDefault);
            orDefault.add(new int[]{j, time});
        }

        for(int t = 0; t <= maxTime; t++){
            for(int i = 0; i < N; i++){
                if(fees[t][i] < INF){
                    List<int[]> adjList = map.getOrDefault(i, new ArrayList<>());
                    for(int[] adj : adjList){
                        int j = adj[0], time = adj[1];
                        if(t + time <= maxTime){
                            int next_t = t + time;
                            fees[next_t][j] =
                                    Math.min(fees[next_t][j], fees[t][i] + passingFees[j]);
                        }
                    }
                }
            }
        }
        int minCost = INF;
        for(int t = 0; t <= maxTime; t++){
            minCost = Math.min(fees[t][N-1], minCost);
        }
        return minCost == INF ? -1 :  minCost;
    }

    /**
     * 1928. 规定时间内到达终点的最小花费
     *
     * 同时更新两条边
     *
     * @param maxTime
     * @param edges
     * @param passingFees
     * @return
     */
    public int minCost1(int maxTime, int[][] edges, int[] passingFees) {
        int INF = 1000 * 1000 + 1;
        int N = passingFees.length;
        int[][] fees = new int[maxTime + 1][N];
        for(int i = 0; i <= maxTime; i++)
            Arrays.fill(fees[i], INF);
        fees[0][0] = passingFees[0];

        for(int t = 0; t <= maxTime; t++){
            for(int[] edge : edges){
                int i = edge[0], j = edge[1], time = edge[2];
                if(time <= t){
                    fees[t][j] = Math.min(fees[t][j], fees[t-time][i] + passingFees[j]);
                    fees[t][i] = Math.min(fees[t][i], fees[t-time][j] + passingFees[i]);
                }
            }
        }
        int minCost = INF;
        for(int k = 0; k <= maxTime; k++){
            minCost = Math.min(minCost, fees[k][N-1]);
        }
        return minCost == INF ? -1 : minCost;
    }
}
