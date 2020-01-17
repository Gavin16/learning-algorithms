package leetCode.backtrackingAlgorithm;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * 《22. 括号生成》
 *
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 *
 * 例如，给出 n = 3，生成结果为：
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 */
public class GenerateParenthesis {

    public static void main(String[] args) {
        List<String> strings = generateParenthesis(3);
        System.out.println(strings);
    }

    private static BitSet bits = new BitSet();

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if(n < 1) return result;
        recursiveGen(result,n,0,0,0);
        return result;
    }

    private static void recursiveGen(List<String> res, int n, int left, int right,int id){

    }
}
