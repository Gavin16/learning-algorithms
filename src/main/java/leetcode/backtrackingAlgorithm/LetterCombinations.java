package leetcode.backtrackingAlgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 *  《17. 电话号码的字母组合》
 *
 *  题目描述
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *
 * 示例:
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 *
 */
public class LetterCombinations {


    public static void main(String[] args) {
        List<String> strings = letterCombinations("23");

        System.out.println(strings);
    }

    private static String[][] n2c = {{"a","b","c"},{"d","e","f"},{"g","h","i"},{"j","k","l"},{"m","n","o"},{"p","q","r","s"},{"t","u","v"},{"w","x","y","z"}};

    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if(null == digits || digits.length() == 0 || digits.contains("1")) return res;
        char[] chars = digits.toCharArray();
        recursiveAddLetters(res,chars,0,"");
        return res;
    }


    private static void recursiveAddLetters(List<String> list, char[] digits , int di, String str){
        if(di == digits.length){
            list.add(str);
            return;
        }

        int ri = digits[di] - '2';
        for(int i = 0; i < n2c[ri].length; i++){
            recursiveAddLetters(list,digits,di+1,str + n2c[ri][i]);
        }
    }


}
