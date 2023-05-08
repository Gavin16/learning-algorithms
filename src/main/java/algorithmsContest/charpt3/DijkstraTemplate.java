package algorithmsContest.charpt3;

import java.util.*;

/**
 * @className: DijkstraTemplate
 * @description: 例3.20 最短路径
 * 问题描述: 给出一个图，求点1到其他所有点的最短路径
 * 输入: 第1行输入n 和 m, n为点的数量， m 为边的数量；第2 ~ m+1 行中，
 * 每行输入3个整数: u,v,w ；表示u 和 v 之间存在一条长度为 w 的单向边。
 * 1 <= n <= 3*10^5, 1 <= m <= 10^6, 1 <= u,v <= n, 1 <= w <= 10^9。
 * <p>
 * 输出: 共输出n个数，分别表示从1点到1~n 点的最短距离。两两之间用空格隔开。
 * 如果无法到达则输出 -1。
 * <p>
 * <p>
 * 分析: n 与 m 的规模接近, 图相对稀疏，考虑使用邻接表来表示。
 * @version: 1.0
 * @author: minsky
 * @date: 2023/5/7
 */
public class DijkstraTemplate {


    public static void main(String[] args) {
        DijkstraTemplate instance = new DijkstraTemplate();

        int[][] adjTab = {
                { 0,  6,  3, -1, -1, -1},
                { 6,  0,  2,  5, -1, -1},
                { 3,  2,  0,  3,  4, -1},
                {-1,  5,  3,  0,  1,  7},
                {-1, -1,  4,  1,  0,  3},
                {-1, -1, -1,  7,  3,  0}
        };
        // 链接表转邻接矩阵
        List<List<AdjNode>> adjList = new ArrayList<>();
        for(int i = 0 ; i < adjTab.length ; i++){
            int[] adj = adjTab[i];
            List<AdjNode> neighbors = new ArrayList<>();
            for(int j = 0 ; j < adj.length; j++){
                if(adj[j] > 0) neighbors.add(new AdjNode(i, j, adj[j]));
            }
            adjList.add(neighbors);
        }

        long[] minDist = instance.dijkstra1(adjList, 6);
        for(int k = 0; k < minDist.length; k++){
            if(k == minDist.length - 1) System.out.println(minDist[k]);
            else System.out.printf("%s,", minDist[k]);
        }
    }

    /**
     * dijkstra 使用优先队列实现
     *
     * @param adjList 图的链接表表示
     * @param n       图中节点的个数
     * @return
     */
    public long[] dijkstra1(List<List<AdjNode>> adjList, int n) {
        long[] dist = new long[n];
        boolean[] used = new boolean[n];
        PriorityQueue<AdjNode> queue = new PriorityQueue<>(Comparator.comparingLong(e -> e.dist));
        // id = 节点编号 - 1
        queue.offer(new AdjNode(0, 0, 0));
        initDist(dist);

        while (!queue.isEmpty()) {
            AdjNode poll = queue.poll();
            int id = poll.id;
            List<AdjNode> adjNodes = adjList.get(id);
            if (null == adjNodes || adjNodes.size() == 0 || used[id]) continue;
            used[id] = true;
            for (int i = 0; i < adjNodes.size(); i++) {
                AdjNode adjNode = adjNodes.get(i);
                if (dist[id] + adjNode.dist < dist[adjNode.id]) {
                    long newDist = dist[id] + adjNode.dist;
                    dist[adjNode.id] = newDist;
                    queue.offer(new AdjNode(0, adjNode.id, newDist));
                }
            }
        }
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] >= (Long.MAX_VALUE / 2)) {
                dist[i] = -1;
            }
        }
        return dist;
    }

    private void initDist(long[] dist) {
        long INF = Long.MAX_VALUE / 2;
        Arrays.fill(dist, INF);
        dist[0] = 0;
    }

    static class AdjNode {
        int src;
        int id;
        long dist;

        AdjNode(int src, int id, long dist) {
            this.src = src;
            this.id = id;
            this.dist = dist;
        }
    }

}
