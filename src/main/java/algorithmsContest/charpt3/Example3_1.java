package algorithmsContest.charpt3;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @className: Example3_1
 * @description: 全球变暖
 * 问题描述: 有一张 N*N 像素的照片, "." 表示海洋， "#" 表示陆地，如下所示
 * <p>
 * . . . . . . .
 * . # # . . . .
 * . # # . . . .
 * . . . . # # .
 * . . # # # # .
 * . . . # # # .
 * . . . . . . .
 * <p>
 * 其中上，下，左，右4个方向上连在一起的一片陆地组成一座岛屿，如上面就有两座岛屿。
 * 由于全球变乱导致海面上升，岛屿边缘一个像素的范围会被海水淹没。如果一块陆地像素与
 * 海洋相邻,它就会被淹没。例如，上述海域未来会变成
 * <p>
 * . . . . . . .
 * . . . . . . .
 * . . . . . . .
 * . . . . . . .
 * . . . . # . .
 * . . . . . . .
 * . . . . . . .
 * <p>
 * 请计算，照片中有多少岛屿会被完全淹没？
 *
 * 注意: 输入数据第1行，第1列，第N行和第N列均为海洋区域
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2023/4/9
 */
public class Example3_1 {

    public static void main(String[] args) {
        byte[][] map = {
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '#', '#', '.', '#', '#', '.'},
                {'.', '#', '#', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '#', '#', '.'},
                {'.', '.', '#', '.', '#', '#', '.'},
                {'.', '#', '.', '#', '#', '#', '.'},
                {'.', '.', '.', '.', '.', '.', '.'}
        };
        int N = map.length;
        visit = new byte[N][N];

        int ans = 0;
        Example3_1 instance = new Example3_1();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(visit[i][j] == 0 && map[i][j] == '#'){
                    int res = instance.dfs(map, i, j);
//                    int res = instance.bfs(map, i, j);
                    ans += res > 0 ? 0 : 1;
                }
            }
        }
        System.out.println(ans);
    }

    private final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};


    static byte[][] visit;

    /**
     * 深度优先搜索(x, y)坐标点所在岛屿中，是否存在高地
     * 若存在返回1, 否则返回 0
     */
    private int dfs(byte[][] map, int x, int y) {
        visit[x][y] = 1;
        int cnt = 0;
        if (map[x][y - 1] == '#' && map[x][y + 1] == '#'
                && map[x + 1][y] == '#' && map[x - 1][y] == '#') {
            cnt += 1;
        }

        for (int[] dir : dirs) {
            int xNext = x + dir[0], yNext = y + dir[1];
            if (visit[xNext][yNext] == 1) continue;
            if (map[xNext][yNext] == '#') {
                cnt += dfs(map, xNext, yNext);
            }
        }
        return cnt > 0 ? 1 : 0;
    }



    Deque<int[]> queue = new LinkedList<>();

    /**
     * 广度优先搜索
     */
    private int bfs(byte[][]map, int i, int j){
        queue.add(new int[]{i , j});
        int cnt = 0;
        while(queue.size() > 0){
            int[] pop = queue.pop();
            int x = pop[0], y = pop[1];
            visit[x][y] = 1;

            if(map[x+1][y] == '#' && map[x-1][y]== '#'
                    && map[x][y+1] == '#' && map[x][y-1] == '#'){
                cnt += 1;
            }

            for(int[] dir : dirs){
                int xNext = x + dir[0], yNext = y + dir[1];
                if(visit[xNext][yNext] == 0 && map[xNext][yNext] == '#'){
                    queue.add(new int[]{xNext, yNext});
                }
            }
        }
        return cnt > 0 ? 1 : 0;
    }

}
