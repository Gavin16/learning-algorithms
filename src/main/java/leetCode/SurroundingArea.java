package leetCode;

/**
 *  《130. 被围绕的区域》
 *
 *  给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 *
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 * 示例:
 *
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 *
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 解释:
 *
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
 * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。
 * 如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 *
 */
public class SurroundingArea {



    /**
     * 暴力破解,对每一个值为'O'的元素判断它是否能达到边界或者本身就在边界上
     *  若不在且不能通过'O'达到边界，则置为 'X'
     */
    static void surroundingArea(char[][] board){
    }
}
