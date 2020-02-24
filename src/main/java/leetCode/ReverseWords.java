package leetCode;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 *
 * 《GeekTime -- practice day03》
 *  《151. 翻转字符串里的单词》
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 *
 * 示例 1：
 *
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * 示例 2：
 *
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 *
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *  
 *
 * 说明：
 *
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 */
public class ReverseWords {

    public static void main(String[] args) {

        String str = "GC  is the basic  platform";
        String[] s = str.split("\\s+");

        String subStr = str.substring(0,2);

        List<String> collect = Arrays.stream(s).filter(e -> !e.equals("")).collect(Collectors.toList());

//        System.out.println(reverseWords(str));
        System.out.println(reverseWords1(str));
        System.out.println(reverseWords2(str));

    }

    public static String reverseWords(String s) {
        if(s.trim().length() == 0) return "";
        String[] sa = s.trim().split("\\s+");

        int i = 0 , j = sa.length - 1;
        while(i <= j){
            String temp = sa[i];
            sa[i] = sa[j];
            sa[j] = temp;
            i++;j--;
        }
        return String.join(" ",sa);
    }


    /**
     *
     * 先替换多个空格为单个，再拆分
     */
    public static String reverseWords2(String s){
        String target = s.trim();
        String[] words = target.replaceAll("\\s+", " ").split(" ");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ",words);
    }


    /**
     * 递归实现
     */
    public static String reverseWords1(String s){
        if(s.trim().length() == 0) return "";

        StringBuilder sb = new StringBuilder();
        dfsWords(sb,s);
        return sb.toString().trim();
    }

    /**
     *  递归找出字符串中的单词
     */
    public static void dfsWords(StringBuilder sb , String s){
        if(s.length() == 0) return;
        int i = s.indexOf(" ");
        if(i >= 0){
            dfsWords(sb,s.substring(i).trim());
            sb.append(s.substring(0,i)).append(" ");
        }else{
            sb.append(s).append(" ");
        }
    }

}
