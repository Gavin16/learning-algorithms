package leetcode;

/**
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * 返回矩阵中 省份 的数量。
 *
 * 输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * 输出：2
 *
 * 输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * 输出：3
 */
public class FindCircleNum {

    /**
     * 深度优先搜索
     */
    public int findCircleNum(int[][] isConnected) {
        int len = isConnected.length;
        boolean[] isVisited = new boolean[len];
        int circles = 0;
        for(int i = 0 ; i < len ; i++){
            if(!isVisited[i]){
                dfs(isConnected, isVisited, len, i);
                circles++;
            }
        }
        return circles;
    }

    public void dfs(int[][] isConnected, boolean[] isVisited, int len, int i){
        for(int j = 0 ; j < len; j++){
            if(isConnected[i][j] == 1 && !isVisited[j]){
                isVisited[j] = true;
                dfs(isConnected, isVisited, len , j);
            }
        }
    }


    public static void main(String[] args) {
        int[][] isConnected = {{1,0,0,1,0},
                                {0,1,0,0,0},
                                {0,0,1,0,0},
                                {1,0,0,1,1},
                                {0,0,0,1,1}};
        FindCircleNum instance = new FindCircleNum();
        int circleNum = instance.findCircleNum(isConnected);
        System.out.println(circleNum);
    }
}
