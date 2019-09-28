package interviewQuestions;

/**
 * @Class: TwoStringCompare
 * @Description:
 *
 *  LAZADA 面试算法题：
 *      比较两个字符串是否包含相同的字符及字符个数
 *
 * @Author: Minsky
 * @Date: 2019/9/12 6:10
 * @Version: v1.0
 */
public class TwoStringCompare {


    public static void main(String[]args){
        String s1 = "AiubH";
        String s2 = "AiiHbue";

        System.out.println(isReconstruct(s1,s2));
    }


    /**
     * 已知 str1 和 str2中均为字母，比较str1 和 str2 是否包含相同的字符
     * @param str1
     * @param str2
     * @return
     */
    static boolean isReconstruct(String str1, String str2){
        long[] larr = new long[2];
        char[] ca1 = str1.toCharArray();
        char[] ca2 = str2.toCharArray();
        for(char c : ca1){
            larr[0] |= (1 << c - 'A');
        }
        for(char c : ca2){
            larr[1] |= (1 << c - 'A');
        }

        return (larr[0] ^ larr[1]) == 0;
    }
}
