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
 *
 * ------------------------------------------------------------------------------------------------------------
 * 1631. 最小体力消耗路径
 *
 * 你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row, col) 的高度。
 * 一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。
 * 你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
 * 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
 * 请你返回从左上角走到右下角的最小 体力消耗值 。
 *
 * 输入：heights = [[1,2,2],[3,8,2],[5,3,5]]
 * 输出：2
 * 解释：路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。
 * 这条路径比路径 [1,2,2,2,5] 更优，因为另一条路径差值最大值为 3 。
 *
 * 输入：heights = [[1,2,3],[3,8,4],[5,3,5]]
 * 输出：1
 * 解释：路径 [1,2,3,4,5] 的相邻格子差值绝对值最大为 1 ，比路径 [1,3,5,3,5] 更优。
 *
 * 输入：heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * 输出：0
 * 解释：上图所示路径不需要消耗任何体力。
 *
 * 提示：
 *
 * rows == heights.length
 * columns == heights[i].length
 * 1 <= rows, columns <= 100
 * 1 <= heights[i][j] <= 106
 *
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

//        long[] minDist = instance.dijkstra1(adjList, 6);
//        for(int k = 0; k < minDist.length; k++){
//            if(k == minDist.length - 1) System.out.println(minDist[k]);
//            else System.out.printf("%s,", minDist[k]);
//        }

        long[] longs = instance.dijkstra2(adjTab, 6);
        for(int k = 0; k < longs.length; k++){
            if(k == longs.length - 1) System.out.println(longs[k]);
            else System.out.printf("%s,", longs[k]);
        }

        // 1631. 最小体力消耗路径
        int[][] heights = {
                {1, 2, 2},
                {3, 8, 2},
                {5, 3, 5}
        };
        int[][] heights1 = {
                {1,2,1,1,1},
                {1,2,1,2,1},
                {1,2,1,2,1},
                {1,2,1,2,1},
                {1,1,1,2,1},
        };
        int[][] heights2 = {
                {1, 2, 3},
                {3, 8, 4},
                {5, 3, 5}
        };
//        [[4,3,4,10,5,5,9,2],[10,8,2,10,9,7,5,6],[5,8,10,10,10,7,4,2],[5,1,3,1,1,3,1,9],[6,4,10,6,10,9,4,6]]
        int[][] heights3 = {
                {4, 3, 4, 10, 5,5,9,2},
                {10,8, 2, 10, 9,7,5,6},
                {5, 8, 10,10,10,7,4,2},
                {5, 1, 3,  1, 1,3,1,9},
                {6, 4,10,  6,10,9,4,6}
        };

        int ans = instance.minimumEffortPath(heights3);
        System.out.println(ans);


//        int ans = instance.minimumEffortPath(heights);
//        System.out.println(ans);
//        int ans1 = instance.minimumEffortPath(heights1);
//        System.out.println(ans1);
//        int ans2 = instance.minimumEffortPath(heights2);
//        System.out.println(ans2);
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


    /**
     * Dijkstra 使用数组实现 时间复杂度O(n^2)
     * 当图的边数远远大于节点数时，优先使用邻接矩阵表示图
     *
     * @param adjTab
     * @param n
     * @return
     */
    public long[] dijkstra2(int[][] adjTab, int n) {
        long[] dist = new long[n];
        boolean[] used = new boolean[n];
        long INF = Long.MAX_VALUE / 2;

        Arrays.fill(dist, INF);
        dist[0] = 0;

        for(int i = 0; i < n; i++){
            // 找出距离1(0)号节点距离最小的点
            int k = -1;
            for(int j = 0; j < n; j++){
                if(!used[j] && (k == -1 || dist[k] > dist[j])){
                    k = j;
                }
            }
            used[k] = true;
            // 用最小值更新所有与最小值节点相邻的点到1号节点的距离
            for(int s = 0; s < n; s++){
                if(!used[s] && adjTab[k][s] > 0){
                    dist[s] = Math.min(dist[k] + adjTab[k][s], dist[s]);
                }
            }
        }
        for(int t = 0; t < n; t++){
            if(dist[t] >= INF) dist[t] = -1;
        }
        return dist;
    }


    /**
     * 1631. 最小体力消耗路径
     *
     * (1) 根据宫格构造图的邻接表
     * (2) 根据链接表
     * @param heights
     * @return
     */
    public int minimumEffortPath(int[][] heights) {
        int rows = heights.length, cols = heights[0].length;
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int[] dist = new int[rows * cols];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        boolean[] visit = new boolean[rows * cols];
        PriorityQueue<int[]> queue = new PriorityQueue<>(
                Comparator.comparingInt(arr -> arr[2]));

        queue.offer(new int[]{0, 0, 0});
        int finishId = rows * cols - 1;

        while(!queue.isEmpty()){
            int[] poll = queue.poll();
            int x = poll[0], y = poll[1], d = poll[2];
            int id = x * cols + y;
            if(id == finishId) break;
            if(visit[id]) continue;
            visit[id] = true;

            for(int i = 0; i < dir.length; i++){
                int next_x = x + dir[i][0];
                int next_y = y + dir[i][1];
                int next_id = next_x * cols + next_y;
                if(next_x >= 0 && next_x < rows && next_y >=0 && next_y < cols &&
                         Math.max(d, Math.abs(heights[x][y] - heights[next_x][next_y])) < dist[next_id]){
                    dist[next_id] = Math.max(d, Math.abs(heights[x][y]-heights[next_x][next_y]));
                    queue.offer(new int[]{next_x, next_y, dist[next_id]});
                }
            }
        }
        return dist[rows * cols - 1];
    }

}
