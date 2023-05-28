package algorithmsContest.charpt4;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: UnionSetPractice
 * @description: 并查集练习
 *
 * ---------------------------------------------------------------------------------------------------------------------
 * 130. 被围绕的区域
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 * 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 *
 * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
 * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 *
 * 示例 2：
 * 输入：board = [["X"]]
 * 输出：[["X"]]
 *
 * 提示：
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] 为 'X' 或 'O'
 *
 * ---------------------------------------------------------------------------------------------------------------------
 * 765. 情侣牵手
 * n 对情侣坐在连续排列的 2n 个座位上，想要牵到对方的手。
 * 人和座位由一个整数数组 row 表示，其中 row[i] 是坐在第 i 个座位上的人的 ID。情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)
 * 以此类推，最后一对是 (2n-2, 2n-1)。返回 最少交换座位的次数，以便每对情侣可以并肩坐在一起。
 * 每次交换可选择任意两人，让他们站起来交换座位。
 *
 * 示例 1:
 * 输入: row = [0,2,1,3]
 * 输出: 1
 * 解释: 只需要交换row[1]和row[2]的位置即可。
 *
 * 示例 2:
 * 输入: row = [3,2,0,1]
 * 输出: 0
 * 解释: 无需交换座位，所有的情侣都已经可以手牵手了。
 *
 * 提示:
 *
 * 2n == row.length
 * 2 <= n <= 30
 * n 是偶数
 * 0 <= row[i] < 2n
 * row 中所有元素均无重复
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2023/5/20
 */
public class UnionSetPractice {


    public static void main(String[] args) {
        UnionSetPractice instance = new UnionSetPractice();

//        char[][] board = {
//                {'X','X','X','X','O','X'},
//                {'X','O','O','X','X','x'},
//                {'X','X','O','X','O','X'},
//                {'X','O','X','X','O','X'},
//                {'X','X','O','X','X','O'},
//                {'X','X','X','O','X','X'},
//        };
//        instance.solve(board);
//        for(char[] row : board){
//            for(char c : row){
//                System.out.print(c);
//            }
//            System.out.println();
//        }

        // 765. 情侣牵手
        int[] row1 = {0,2,1,3};
        System.out.println(instance.minSwapsCouples(row1));

        int[] row2 = {3,2,0,1};
        System.out.println(instance.minSwapsCouples(row2));

        int[] row3 = {0,2,3,5,4,7,1,6};
        System.out.println(instance.minSwapsCouples(row3));
    }


    /**
     * 130. 被围绕的区域
     *
     * @param board
     */
    int[] set;
    int[] height;
    public void solve(char[][] board) {
        int r = board.length, c = board[0].length;
        int size = r * c + 1;
        initSet(size);
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(board[i][j] != 'O') continue;
                if(i == 0 || i == r-1 || j == 0 || j == c-1){
                    merge(node(i, j, c), size-1);
                }else{
                    if(board[i-1][j] == 'O') merge(node(i, j, c), node(i - 1, j, c));
                    if(board[i+1][j] == 'O') merge(node(i,j,c), node(i + 1, j, c));
                    if(board[i][j-1] == 'O') merge(node(i,j,c), node(i,j - 1, c));
                    if(board[i][j+1] == 'O') merge(node(i,j,c), node(i, j + 1, c));
                }
            }
        }
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(i == 0 || i == r-1 ||
                        j == 0 || j == c-1) continue;
                if(board[i][j] == 'O' && !isConnected(node(i,j,c), size-1)){
                    board[i][j] = 'X';
                }
            }
        }
    }

    private boolean isConnected(int a , int b){
        return find(a) == find(b);
    }

    private int node(int i, int j, int col){
        return i * col + j;
    }

    private void initSet(int size){
        set = new int[size];
        height = new int[size];
        for(int i = 0; i < size; i++){
            set[i] = i;
            height[i] = 0;
        }
    }

    // 找出所有节点的父节点
    private int find(int x){
        if(x != set[x]) set[x] = find(set[x]);
        return set[x];
    }

    private void merge(int a, int b){
        int aP = find(a);
        int bP = find(b);
        if(height[a] == height[b]){
            height[a] = height[a] + 1;
            set[bP] = aP;
        }else{
            if(height[a] < height[b]) set[aP] = bP;
            else set[bP] = aP;
        }
        if(aP != bP) set[aP] = set[bP];
    }

    public int minSwapsCouples(int[] row) {
        int len = row.length;
        initSet(len);
        Map<Integer, Integer> pair = new HashMap<>();
        for(int i = 0; i < len; i += 2) {
            merge(i, i + 1);
            pair.put(row[i], i);
            pair.put(row[i+1], i + 1);
        }
        int swapCnt = 0;
        for(int i = 0; i < len; i += 2){
            int s = row[i], t = row[i+1];
            if(!isConnected(s, t)){
                int obj = s % 2 == 0 ? s + 1 : s - 1;
                int objPos = pair.get(obj);
                // 交换 i+1 坐标与 objPos 坐标位置
                pair.put(row[i+1], objPos);
                pair.put(row[objPos], i+1);
                swap(i+1, objPos, row);
                swapCnt += 1;
            }
        }
        return swapCnt;
    }
    private void swap(int i, int j, int[] row){
        int tmp = row[j];
        row[j] = row[i];
        row[i] = tmp;
    }

}
