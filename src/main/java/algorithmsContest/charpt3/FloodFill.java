package algorithmsContest.charpt3;

import org.apache.commons.math3.ode.nonstiff.GillFieldIntegrator;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @className: FloodFill
 * @description: 洪水填充
 * <p>
 * <p>
 * --------------------------------------------------------------------------------------------------------------------
 * 例3.12  Red And Blank
 * 问题描述：
 * 有一个长方形的房间，铺着方形瓷砖。每块瓷砖都是红色或者黑色。
 * 一个人站在黑色的瓷砖上，他可以按照上，下，左，右 方向移动到相邻的瓷砖。但是他不能在
 * 红砖上移动。他只能在黑砖上移动。编程计算他可以达到的黑色瓷砖的数量。
 * <p>
 * 输入: 第1行输入两个正整数 W 和 H, 分别表示 x 方向和 y方向上的瓷砖数量， w 和 H均不超过20.
 * 下面输入H行， 每行包含 W 个字符。 每个字符标识一块瓷砖的颜色。
 * 字符 '.' 表示黑色瓷砖; '#' 表示红色瓷砖; '@' 代表黑色瓷砖上的人， 在数据集中只出现一次。
 * <p>
 * 输出: 输出一个数字, 表示从初始瓷砖能到达的瓷砖总数量(包括起点)。
 * <p>
 * --------------------------------------------------------------------------------------------------------------------
 * 例3.13 The wedding juicer
 * 问题描述：
 * 在地面上用砖块修占地面积为 W*H 的建筑， 3 <= W <= 300, 3 <= H <= 300;
 * 每 1*1 的单位地面上有一块砖， 第 i 块砖的高度为 Bi, 1 <= B <= 1000_000_000,
 * 砖与砖之间紧密贴合。 计算这块地面能容纳多大的水量。
 * @version: 1.0
 * @author: minsky
 * @date: 2023/4/16
 */
public class FloodFill {


    public static void main(String[] args) {
        // 例3.12
        char[][] room = {
                {'.', '#', '#', '.', '#', '#', '.', '.', '#'},
                {'#', '#', '.', '#', '.', '.', '#', '#', '.'},
                {'.', '#', '#', '#', '.', '.', '#', '.', '.'},
                {'#', '.', '#', '#', '#', '.', '#', '.', '#'},
                {'.', '#', '.', '#', '#', '@', '#', '.', '#'},
                {'#', '.', '#', '.', '.', '.', '.', '#', '#'},
                {'#', '#', '.', '#', '#', '.', '#', '.', '.'},
                {'#', '#', '.', '.', '#', '.', '.', '#', '#'},
        };

        FloodFill floodFill = new FloodFill();
        int cnt = floodFill.RedAndBlack(room);
        System.out.println(cnt);

        // 例3.13
        int[][] area = {
                {3,5,4,3,3},
                {4,2,1,3,3},
                {5,1,4,2,3},
                {4,2,1,3,5},
                {3,4,5,6,3},
        };
        int[][] area1 = {
                {3,4,5},
                {4,1,6},
                {5,4,5},
                {5,5,6}
        };

        long vol = floodFill.waterVolume(area, 5, 5);
        System.out.println(vol);
    }

    /**
     * 深度优先搜索实现
     *
     * @param room
     * @return
     */
    private int blackCnt = 0;

    public int RedAndBlack(char[][] room) {
        int r = room.length, c = room[0].length;

        boolean[][] visit = new boolean[r][c];
        int x = 0, y = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (room[i][j] == '@') {
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        dfsArriveBlack(room, visit, r, c, x, y);
        return blackCnt;
    }

    private void dfsArriveBlack(char[][] room, boolean[][] visit, int r, int c, int x, int y) {
        if (!check(x, y, r, c) || visit[x][y] || room[x][y] == '#') {
            return;
        }
        visit[x][y] = true;
        blackCnt += 1;

        dfsArriveBlack(room, visit, r, c, x + 1, y);
        dfsArriveBlack(room, visit, r, c, x - 1, y);
        dfsArriveBlack(room, visit, r, c, x, y + 1);
        dfsArriveBlack(room, visit, r, c, x, y - 1);
    }

    private boolean check(int x, int y, int r, int c) {
        if (x >= 0 && x < r && y >= 0 && y < c) return true;
        return false;
    }
    /**
     * 例3.13
     * @param area
     * @return
     */
    private long waterVolume(int[][] area, int w, int h) {
        long totalVolume = 0;
        PriorityQueue<Grid> queue = new PriorityQueue<>(
                Comparator.comparingInt(e -> e.b));
        boolean[][] boundary = new boolean[h][w];
        addEdgeToQueue(area, w, h, queue,boundary);
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!queue.isEmpty()) {
            Grid poll = queue.poll();
            for(int[] dir : dirs){
                int x = poll.x + dir[0], y = poll.y + dir[1];
                if(inBoundary(x, y , w, h) && area[x][y] > 0
                        && !boundary[x][y]){
                    if(area[x][y] < poll.b){
                        totalVolume += poll.b - area[x][y];
                        queue.offer(new Grid(x,y, poll.b));
                    }else{
                        queue.offer(new Grid(x,y, area[x][y]));
                    }
                    boundary[x][y] = true;
                    area[poll.x][poll.y] = -1;
                }
            }
        }
        return totalVolume;
    }
    private boolean inBoundary(int x, int y, int w, int h){
        if(x >= 0 && x < h && y >= 0 && y < w) return true;
        return false;
    }
    private void addEdgeToQueue(int[][] area, int w, int h,
                                PriorityQueue<Grid> queue,boolean[][] bnd) {
        for (int i = 0; i < h; i++){
            queue.offer(new Grid(i, 0, area[i][0]));
            bnd[i][0] = true;
        }
        for (int j = 1; j < w; j++){
            queue.offer(new Grid(0, j, area[0][j]));
            bnd[0][j] = true;
        }
        for (int i = 1; i < h; i++){
            queue.offer(new Grid(i, w - 1, area[i][w - 1]));
            bnd[i][w-1] = true;
        }
        for (int j = 1; j < w - 1; j++){
            queue.offer(new Grid(h - 1, j, area[h - 1][j]));
            bnd[h-1][j] = true;
        }
    }
    class Grid {
        int x, y;
        int b;
        Grid(int x, int y, int b) {
            this.x = x;
            this.y = y;
            this.b = b;
        }
    }


}
