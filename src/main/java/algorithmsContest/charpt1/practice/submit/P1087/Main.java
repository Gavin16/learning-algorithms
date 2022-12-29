package algorithmsContest.charpt1.practice.submit.P1087;

import java.util.Scanner;

/**
 * @className: Main
 * @description:
 *
 * FBI 树
 *
 * 题目描述
 * 我们可以把有"0" 和 "1" 组成的字符串分为三类: 全"0" 串称为 B串，全"1" 串称为I串，即含"0"又含"1"的串称为F串。
 * FBI 树是一种二叉树,它的节点类型也包括F节点， B节点 和 I节点三种。有一种长度为2^N 的"01"串S 可以构造出一棵FBI树T,递归的构造方法如下:
 * 1. T的根节点为R, 其类型与串S的类型相同
 * 2. 若串S的长度大于1，将串S从中间分开，分为等长的左右子串S1 和 S2; 由左子串S1构造R的左子树T1,由右子串S2构造R的右子树T2
 *
 * 输入格式:
 * 第一行是一个整数N(0 <= N <= 10)
 * 第二行是一个长度为 2^N 的"01"串
 *
 * 输出格式:
 * 一个字符串，即FBI树的后序遍历序列
 *
 * 输入样例:
 * 3
 * 10001011
 *
 * 输出
 * "IBFBBBFIBFIIIFF"
 *
 * 说明/提示
 * 对于 40% 的数据， N <= 2;
 * 对于全部的数据,  N <= 10.
 *
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2022/12/19
 */
public class Main {

    public static void main(String[] args) {
        Main instance = new Main();
        instance.fbiTree();
    }

    public void fbiTree(){
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String s = scanner.nextLine();
        char[] input = s.toCharArray();
        dfsPrint(input,0, (int) Math.pow(2,n) - 1);
    }

    public void dfsPrint(char[] chars, int s, int e){
        int mid = (s + e) / 2;
        if(s != e){
            dfsPrint(chars, s, mid);
            dfsPrint(chars,mid + 1, e);
        }
        int one = 0, zero = 0;
        for(int i = s; i <= e; i++){
            if(chars[i] == '0') zero++;
            else one++;
        }
        System.out.printf((zero > 0 && one > 0)
                        ? "F" : (zero > 0 ? "B" : "I"));
    }

}
