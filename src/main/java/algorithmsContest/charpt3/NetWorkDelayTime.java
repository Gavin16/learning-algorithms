package algorithmsContest.charpt3;

import java.util.*;

/**
 * @className: NetWorkDelayTime
 * @description:
 *
 * 743. 网络延迟时间
 *
 * 有 n 个网络节点，标记为 1 到 n。
 * 给你一个列表 times，表示信号经过 有向 边的传递时间。
 * times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点，
 * wi 是一个信号从源节点传递到目标节点的时间。
 *
 * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？
 * 如果不能使所有节点收到信号，返回 -1 。
 *
 *
 * 输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 * 输出：2
 *
 * 示例 2：
 * 输入：times = [[1,2,1]], n = 2, k = 1
 * 输出：1
 *
 * 示例 3：
 * 输入：times = [[1,2,1]], n = 2, k = 2
 * 输出：-1
 *
 * 提示：
 *
 * 1 <= k <= n <= 100
 * 1 <= times.length <= 6000
 * times[i].length == 3
 * 1 <= ui, vi <= n
 * ui != vi
 * 0 <= wi <= 100
 * 所有 (ui, vi) 对都 互不相同（即，不含重复边）
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2023/5/4
 */
public class NetWorkDelayTime {


    public static void main(String[] args) {

        NetWorkDelayTime solution = new NetWorkDelayTime();

//        int[][] times = {{2,1,1}, {2,3,1}, {3,4,1}};
//        int n = 4, k = 2;

//        int[][] times = {{1,2,1}};
//        int n = 2, k = 1;

//        int[][] times = {{1,2,1}};
//        int n = 2, k = 2;

        int[][] times = {{1,2,1},{2,3,2},{1,3,4}};
        int n = 3, k = 1;

        int ans = solution.networkDelayTime3(times, n, k);
        System.out.println(ans);
    }


    /**
     * 使用邻接矩阵表示图
     * 使用广度优先搜索进行图的遍历: 结果并非最小时间(使用深度优先搜索优化)
     * @param times
     * @param n
     * @param k
     * @return
     */
    public int networkDelayTime1(int[][] times, int n, int k) {
        byte[][] neighbors = new byte[n][n];

        for(int i = 0; i < times.length; i++){
            int[] time = times[i];
            int r = time[0], c = time[1];
            neighbors[r-1][c-1] = (byte)time[2];
        }

        int layerMaxTm = 0;
        // 从 k 节点开始遍历
        Queue<Node> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        queue.add(new Node(k-1, 0));
        set.add(k-1);
        while(!queue.isEmpty()){
            List<Node> layer = new ArrayList<>();
            while(!queue.isEmpty()){
                layer.add(queue.poll());
            }
            List<Node> currNeighbors = new ArrayList<>();
            for(Node node : layer){
                int no = node.no;
                int time = node.time;
                layerMaxTm = time > layerMaxTm ? time : layerMaxTm;

                for(int j = 0; j < n; j++){
                    if(neighbors[no][j] > 0 && !set.contains(j)){
                        int newTime = time + neighbors[no][j];
                        currNeighbors.add(new Node(j, newTime));
                        set.add(j);
                    }
                }
            }
            for(Node nb : currNeighbors){
                queue.offer(nb);
            }
        }
        return set.size() < n ? -1 : layerMaxTm;
    }
    class Node{
        int no;
        int time;
        Node(int no, int time){
            this.no = no;
            this.time = time;
        }
    }




    /**
     * 使用单源最短路径算法(dijkstra 算法)
     * 从k节点发出的信号，若所有节点都接收到了，耗费时间为:
     *  从k节点发送信号到达其他所有节点最短路径的最大值
     *
     * 因此可以转化为 单源最短路径 问题进行求解
     *
     * @param times
     * @param n
     * @param k
     * @return
     */
    public int networkDelayTime2(int[][] times, int n, int k) {
        int[][] g = new int[n][n];
        final int INF = Integer.MAX_VALUE / 2;
        for(int[] row : g){
            Arrays.fill(row, INF);
        }
        for(int[] time : times){
            int i = time[0], j = time[1];
            g[i-1][j-1] = time[2];
        }

        int[] dist = new int[n];
        boolean[] used = new boolean[n];
        Arrays.fill(dist, INF);
        dist[k - 1] = 0;

        for(int i = 0 ; i < n; i++){
            int x = -1;
            for(int y = 0; y < n; y++){
                if(!used[y] && (x == -1 || dist[y] < dist[x])){
                    x = y;
                }
            }
            used[x] = true;
            for(int y = 0; y < n; y++){
                dist[y] = Math.min(dist[y], dist[x] + g[x][y]);
            }
        }
        int asInt = Arrays.stream(dist).max().getAsInt();
        return asInt == INF ? -1 : asInt;
    }


    /**
     * @param times
     * @param n
     * @param k
     * @return
     */
    public int networkDelayTime3(int[][]times, int n, int k){
        int[][] graph = new int[n][n];
        final int INF = Integer.MAX_VALUE / 2;
        for(int[] row : graph) Arrays.fill(row, INF);
        // 初始化邻接矩阵
        for(int[] edge : times){
            int s = edge[0], t = edge[1];
            graph[s-1][t-1] = edge[2];
        }

        boolean[] used = new boolean[n];
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[k - 1] = 0;

        // 循环确定每一个元素的最短距离
        for(int i = 0; i < n; i++){
            int s = -1;
            for(int t = 0; t < n; t++){
                if(!used[t] && (s == -1 || dist[t] < dist[s])){
                    s = t;
                }
            }
            // 当前 s 为未确定的节点中,距离最短的
            used[s] = true;
            // 用 s 去更新所有从到原位置最短距离还未确定的节点
            for(int t = 0; t < n; t++){
                if(!used[t]){
                    dist[t] = Math.min(dist[t], dist[s] + graph[s][t]);
                }
            }
        }
        int max = dist[0];
        for(int e : dist) max = Math.max(max, e);
        return max == INF ? -1 : max;
    }

}
