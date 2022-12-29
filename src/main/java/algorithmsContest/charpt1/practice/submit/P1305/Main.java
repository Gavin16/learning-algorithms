package algorithmsContest.charpt1.practice.submit.P1305;

import java.util.Scanner;

/**
 * @className: Main
 * @description:
 *
 * 新二叉树
 * 输入一串二叉树, 输出其前序遍历
 * 输入格式
 * 第一行为二叉树的节点数 n (1 <= n <= 26)
 * 后面n行，每一个字母为节点，后两个字母分别为其左右儿子。 特别地，数据保证第一行读入的节点
 * 必为根节点。
 * 空节点用 "*" 表示
 *
 * 输出格式: 二叉树的前序遍历
 *
 * 输入样式:
 * 6
 * abc
 * bdi
 * cj*
 * d**
 * i**
 * j**
 * 输出样式：
 * abdicj
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2022/12/19
 */
public class Main {

    public static void main(String[] args) {

        Main instance = new Main();
        instance.newBinaryTree();
    }

    public void newBinaryTree(){
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine());

        String[] nodes = new String[26];

        String first =scanner.nextLine();
        nodes[first.charAt(0) - 'a'] = first;
        for(int i = 0; i < num - 1; i++){
            String node = scanner.nextLine();
            nodes[node.charAt(0) - 'a'] = node;
        }
        dfsPrint(nodes, first);
    }

    private void dfsPrint(String[] nodes, String current){
        char c = current.charAt(0);
        char c1 = current.charAt(1);
        char c2 = current.charAt(2);

        System.out.printf("%c",c);
        if(c1 != '*'){
            dfsPrint(nodes, nodes[c1 - 'a']);
        }
        if(c2 != '*'){
            dfsPrint(nodes, nodes[c2 - 'a']);
        }
    }
}
