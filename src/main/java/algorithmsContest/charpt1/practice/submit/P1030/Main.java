package algorithmsContest.charpt1.practice.submit.P1030;

import java.util.Scanner;

/**
 * @className: Main
 * @description:
 *
 * P1030
 * 给出一棵二叉树的中序与后序排列。求出它的先序排列。（约定树结点用不同的大写字母表示，且二叉树的节点个数 \le 8≤8）
 *
 * 输入样例
 * BADC
 * BDCA
 * 输出样例
 * ABCD
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2022/12/20
 */
public class Main {


    public static void main(String[] args) {
        Main main = new Main();
        main.preOrderTree();
    }

    public void preOrderTree(){
        Scanner scanner = new Scanner(System.in);
        String inorderStr = scanner.nextLine();
        String postorderStr = scanner.nextLine();
        dfsPrint(inorderStr, postorderStr);
    }

    private void dfsPrint(String inorder, String postorder){
        char c = postorder.charAt(postorder.length() - 1);
        int i = inorder.indexOf(c);
        System.out.printf("%c", c);
        String left_in = inorder.substring(0,i);
        String left_post = postorder.substring(0,i);
        if(left_in.length() > 0) dfsPrint(left_in, left_post);
        String right_in = inorder.substring(i+1);
        String right_post = postorder.substring(i,postorder.length() - 1);
        if(right_in.length() > 0) dfsPrint(right_in, right_post);
    }
}
