package algorithmsContest.charpt4;

import utils.ArrayUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * @className: UnionSet
 * @description:
 * 例3.1 全球变暖 -- 使用并查集解决
 * ---------------------------------------------------------------------------------------------------------------------
 * 827. 最大人工岛
 * 给你一个大小为 n x n 二进制矩阵 grid 。最多 只能将一格 0 变成 1 。返回执行此操作后，grid 中最大的岛屿面积是多少？
 * 岛屿 由一组上、下、左、右四个方向相连的 1 形成。
 *
 * 示例 1:
 * 输入: grid = [[1, 0], [0, 1]]
 * 输出: 3
 * 解释: 将一格0变成1，最终连通两个小岛得到面积为 3 的岛屿。
 *
 * 示例 2:
 * 输入: grid = [[1, 1], [1, 0]]
 * 输出: 4
 * 解释: 将一格0变成1，岛屿的面积扩大为 4。
 * 示例 3:
 *
 * 输入: grid = [[1, 1], [1, 1]]
 * 输出: 4
 * 解释: 没有0可以让我们变成1，面积依然为 4。
 *  
 * 提示：
 *
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 500
 * grid[i][j] 为 0 或 1
 * ---------------------------------------------------------------------------------------------------------------------
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2023/5/16
 *
 */
public class UnionSet {


    public static void main(String[] args) {
//        char[][] islands = {
//                {'.','.','.','.','.','.','.'},
//                {'.','#','#','.','.','.','.'},
//                {'.','#','#','.','.','.','.'},
//                {'.','.','.','.','#','#','.'},
//                {'.','.','#','#','#','#','.'},
//                {'.','.','.','#','#','#','.'},
//                {'.','.','.','.','.','.','.'}
//        };
//        int cnt = .globalWorming(islands);
//        System.out.println(cnt);

        UnionSet  unionSet = new UnionSet();
        int[][] friends = {
                {1,2},
                {1,3},
                {2,4}
        };
        unionSet.mergeSetPathCompact(5, friends);
    }


    /**
     * 输入 islands 中, '#'代表陆地, '.' 代表海洋
     * 海水上涨后, 所有与海洋相邻的陆地都会被淹没. 求
     * 淹没后还剩余的岛屿数量?
     *
     * 输入数据保证第1行,第1列,第N行,第N列都是海洋
     * 计算返回海水淹没后还剩多少岛屿.
     *
     * @param islands
     * @return
     */
    public int globalWorming(char[][] islands){
        int rows = islands.length, cols = islands[0].length;
        int[] set = initSet(rows * cols);
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(islands[i][j] == '.') continue;
                int currPos = nodeNumber(i, j, cols);
                if(i > 0 && islands[i-1][j] == '#')
                    mergeSet(nodeNumber(i-1, j, cols), currPos, set);
                if(i < rows - 1 && islands[i+1][j] == '#')
                    mergeSet(nodeNumber(i+1, j, cols), currPos, set);
                if(j > 0 && islands[i][j-1] == '#')
                    mergeSet(nodeNumber(i, j-1, cols), currPos, set);
                if(j < cols - 1 && islands[i][j+1] == '#')
                    mergeSet(nodeNumber(i, j+1, cols), currPos, set);
            }
        }
        Set<Integer> unionSet = new HashSet<>();
        for(int k = 0; k < rows * cols; k++){
            if(k == set[k]) continue;
            int i = k / cols, j = k % cols;
            if(isInnerLand(i,j,rows,cols, islands) && !unionSet.contains(k)){
                unionSet.add(set[k]);
            }
        }
        return unionSet.size();
    }

    private boolean isInnerLand(int i, int j, int rows,
                                int cols, char[][] islands) {
        boolean isUp = (i > 0 && islands[i-1][j] == '#');
        boolean isDown = (i < rows - 1 && islands[i+1][j] == '#');
        boolean isLeft = (j > 0 && islands[i][j-1] == '#');
        boolean isRight = (j < cols && islands[i-1][j] == '#');
        return isUp && isDown && isLeft && isRight;
    }

    private int nodeNumber(int i, int j,int cols){
        return i * cols + j;
    }

    private void mergeSet(int x, int y, int[] set){
        int yVal = find(y, set);
        int xVal = find(x, set);
        if(xVal > yVal){
            set[yVal] = xVal;
        }else{
            set[xVal] = yVal;
        }
    }

    private int[] initSet(int n){
        int[] unionSet = new int[n];
        for(int i = 0; i < n; i++)
            unionSet[i] = i;
        return unionSet;
    }

    private int findSet(int t, int [] set){
        if(t != set[t]) set[t] = findSet(set[t], set);
        return set[t];
    }

    private int find(int t, int[] set){
        while(set[t] != t){
            set[t] = set[set[t]];
            t = set[t];
        }
        return set[t];
    }

    private boolean isConnected(int node1, int node2, int[] set){
        return find(node1, set) == find(node2, set);
    }


    private boolean posInBound(int i, int j, int rows, int cols){
        return i >= 0 && i < rows && j >= 0 && j < cols;
    }


    public void mergeSetPathCompact(int n, int[][] friends){
        int[] s = initSet(n + 1);
        for(int[] pair : friends){
            mergeSet(pair[0], pair[1], s);
        }
        // 每次都需要遍历一次才能压缩?
        // -- 不用压缩成所有联通区域都是相同父节点,通过find(a) , find(b) 判断即可
        ArrayUtil.showArray(s);
        System.out.println(isConnected(1,2,s));
        ArrayUtil.showArray(s);
    }


    /**
     * 827. 最大人工岛
     *
     * @param grid
     * @return
     */
    public int largestIsland(int[][] grid) {
        return -1;
    }





}
