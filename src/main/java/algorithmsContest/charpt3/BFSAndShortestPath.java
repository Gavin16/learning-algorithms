package algorithmsContest.charpt3;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @className: BFSAndShortestPath
 * @description: --------------------------------------------------------------------------------------------------------------------
 * 例3.15 迷宫
 * 问题描述:
 * 给出一个迷宫的平面图，其中标记为1的为障碍, 标记为0的为可以通行的地方。如下所示:
 * 010000
 * 000100
 * 001001
 * 110000
 * 迷宫的入口为左上角,出口为右下角，在迷宫中，只能从一个位置走到这个它的上，下，左，右 4个方向之一。对于上面的迷宫，从入口开始，
 * 可以按 URRURRDDDR 的顺序通过迷宫，一共10步。 对于一个更复杂的迷宫(30行50列)，请找出一种通过迷宫的方式，其使用的部署最小。
 * 在步数最少得前提下，请找出字典序最小的一个作为答案。
 * <p>
 * 注意: 在字典序中 D < L < R < U
 * <p>
 * --------------------------------------------------------------------------------------------------------------------
 * leetcode 407: 接雨水II
 * 给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
 *
 * 输入: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
 * 输出: 4
 * 解释: 下雨后，雨水将会被上图蓝色的方块中。总的接雨水量为1+2+1=4。
 *
 * 输入: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
 * 输出: 10
 *
 * 提示:
 *
 * m == heightMap.length
 * n == heightMap[i].length
 * 1 <= m, n <= 200
 * 0 <= heightMap[i][j] <= 2 * 104
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2023/5/5
 */
public class BFSAndShortestPath {


    public static void main(String[] args) {
        BFSAndShortestPath solution = new BFSAndShortestPath();

        // 例3.15
        char[][] maze = {
                {'0','1','0','0','0','0'},
                {'0','0','0','1','0','0'},
                {'0','0','1','0','0','1'},
                {'1','1','0','0','0','0'}
        };
        solution.solveMaze(maze);

        // LeetCode 407
//        int[][] heightMap = {{1,4,3,1,3,2},{3,2,1,3,2,4},{2,3,3,2,3,1}};
        int[][] heightMap = {{3,3,3,3,3},{3,2,2,2,3},{3,2,1,2,3},{3,2,2,2,3},{3,3,3,3,3}};
        int ans = solution.trapRainWater(heightMap);
        System.out.println(ans);
    }

    /**
     * 返回字典序最小的走法
     * 使用广度优先搜索解迷宫走法: 广度优先搜索由于搜索方式是逐层往外进行
     * 因此当遍历到出口位置时，一定是以最小的步数完成的搜索
     *
     * @param maze
     * @return
     */
    public void solveMaze(char[][] maze) {
        int r = maze.length, c = maze[0].length;
        boolean[][] visit = new boolean[r][c];

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0));
        visit[0][0] = true;
        char[][] pre = new char[r][c];

        char[] dc = {'D', 'L', 'R', 'U'};
        int[][] dirs = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};

        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            if (poll.x == r - 1 && poll.y == c - 1) {
                printPath(pre, r - 1, c - 1);
                System.out.println();
                return;
            }
            for(int i = 0 ; i < dirs.length; i++){
                int[] dir = dirs[i];
                int x = poll.x + dir[0], y = poll.y + dir[1];
                if(x < 0 || x > r - 1 || y < 0 || y > c-1){
                    continue;
                }
                if(visit[x][y] || maze[x][y] == '1'){
                    continue;
                }
                visit[x][y] = true;
                // 方向是按照字典序升序排列定义的，因此pre记录都是字典值最小的方向
                pre[x][y] = dc[i];
                queue.offer(new Node(x, y));
            }
        }
    }

    private void printPath(char[][] pre, int x, int y) {
        if(x == 0 && y == 0) return;
        if(pre[x][y] == 'D') printPath(pre, x - 1, y);
        if(pre[x][y] == 'L') printPath(pre, x, y + 1);
        if(pre[x][y] == 'R') printPath(pre, x, y - 1);
        if(pre[x][y] == 'U') printPath(pre, x + 1, y);
        System.out.printf("%c", pre[x][y]);
    }

    class Node {
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    /**
     * LeetCode 407: 接雨水II
     * 解法同 例3.13
     *
     * (1)将图形区域的边界放入优先队列
     * (2)每次取出高度最小的点 O
     * (3)找出该高度最小点O在边界内相邻的坐标 P
     *   满足
     *      (3.1) P 的高度p_h 小于 o_h, 那么P位置可以容纳雨水量为 o_h - p_h,同时修改P的高度为o_h,并将P加入优先队列
     *      (3.2) P 的高度p_h 大于等于 o_h, 那么将P 加入边界优先队列
     * (4) 记录所有已经作为边界加入优先队列的坐标，这样在对 O 上下左右扫扫描时，可以避免边界点重复入队
     * (5) 当优先队列中元素全部出队时，便完成了从外向内的搜索
     *
     * @param heightMap
     * @return
     */
    public int trapRainWater(int[][] heightMap) {
        int totalWaterAmt = 0;
        int r = heightMap.length, c = heightMap[0].length;
        boolean[][] visit = new boolean[r][c];
        PriorityQueue<TNode> queue = new PriorityQueue<>(Comparator.comparingInt(e -> e.h));

        enqueueEdgePoints(queue,heightMap, visit, r, c);

        int[][] around = {{1,0},{-1,0},{0,1},{0,-1}};
        while(!queue.isEmpty()){
            TNode poll = queue.poll();
            for(int[] dir : around){
                int x = poll.x + dir[0], y = poll.y + dir[1];
                if(checkBoundary(x, y, r, c) && !visit[x][y]){
                    if(heightMap[x][y] < poll.h){
                        totalWaterAmt += poll.h - heightMap[x][y];
                        queue.offer(new TNode(x, y, poll.h));
                    }else{
                        queue.offer(new TNode(x, y, heightMap[x][y]));
                    }
                    visit[x][y] = true;
                }
            }
        }
        return totalWaterAmt;
    }

    private void enqueueEdgePoints(PriorityQueue<TNode> queue, int[][] heightMap,
                                   boolean[][] visit, int r, int c) {
        for(int i = 0; i < r; i++){
            queue.offer(new TNode(i, 0, heightMap[i][0]));
            queue.offer(new TNode(i, c-1, heightMap[i][c-1]));
            visit[i][0] = true;
            visit[i][c-1] = true;
        }
        for(int j = 1; j < c-1; j++){
            queue.offer(new TNode(0, j, heightMap[0][j]));
            queue.offer(new TNode(r-1, j, heightMap[r-1][j]));
            visit[0][j] = true;
            visit[r-1][j] = true;
        }
    }

    private boolean checkBoundary(int x, int y, int r, int c){
        return (x < 0 || x >= r || y < 0 || y >= c) ? false : true;
    }

    class TNode{
        int x, y;
        int h;
        TNode(int x, int y, int h){
            this.x = x;
            this.y = y;
            this.h = h;
        }
    }

}
