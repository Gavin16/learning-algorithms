package algorithmsContest.charpt1.practice.submit.P1229;

import java.util.Scanner;

/**
 * @className: Main
 * @description:
 *
 * 遍历问题
 *
 * 输入格式
 * 输A数据共两行，第一行表示该二叉树的前序遍历结果s1，第二行表示该二叉树的后序遍历结果s2。
 *
 * 输出格式
 * 输出可能的中序遍历序列的总数，结果不超过长整型数。
 *
 * 输入
 * abc
 * cba
 *
 * 输出
 * 4
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2022/12/20
 */
public class Main {


    /**
     * 分析：
     * 对于满二叉树，已知前序遍历和后序遍历
     * 它对应的中序遍历是固定的，因为对于满二叉树的每个子树
     *    >> 当选定后序遍历最后节点作为父节点后，剩余的节点一定满足有相同数量的左右子树节点数
     *    >> 如果这时如果继续递归分解，最后前序和后序遍历只剩下一个节点，对于这种情况，是可以唯一确认中序遍历的
     *
     * 但是当树中存在只包含一个子节点的组合时，如前序 bc, 后序cb ， 父节点可以确定是b,但是子节点可以出现在左子树
     * 和右子树上，因此每次出现这种对时，对应的中序遍历可能性就 乘以 2 、
     *
     * 最终二叉树的中序遍历树 = 2 ^ n, 其中 n 为含单个子节点的节点数
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inorder = scanner.nextLine();
        String postorder = scanner.nextLine();

        int ans = 0;
        for(int i = 0 ; i < inorder.length() - 1; i++){
            for(int j = 1 ; j < postorder.length(); j++){
                if(inorder.charAt(i) == postorder.charAt(j)
                        && inorder.charAt(i+1) == postorder.charAt(j-1)){
                    ans += 1;
                }
            }
        }
        System.out.printf("" + (1 << ans));
    }


}
