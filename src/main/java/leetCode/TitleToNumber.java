package leetCode;

/**
 * @ClassName: TitleToNumber
 * @CopyRight:
 * @Description: 《171. Excel表列序号》: Excel 列编号转数字
 * @Author: wufangmin
 * @Date: 2019/10/21 19:05
 * @Version:
 */
public class TitleToNumber {

    public static void main(String[] args) {

        String[] titleArr = {"A","B","AB"};
        for(String str : titleArr){
            System.out.println(titleToNumber(str));
        }
    }

    /**  */
    public static int titleToNumber(String s) {
        char[] cs = s.toCharArray();
        int val = 0;
        for(char c : cs){
            val = val*26  +  (c - 'A' + 1);
        }

        return val;
    }
}