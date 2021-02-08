package leetcode.oneMoreHundred;

/**
 * 字符串匹配: KMP 算法   O(N) 时间复杂度
 * 主串（输入字符串）: 长度为N
 * 模式串(用来匹配的字符串): 长度为M
 *
 * 字符串匹配需要满足: N >= M
 *
 * [1] 失败指针 next 数组
 * 针对模式串中某一个字符构造失败数组 next数组, next数组长度为 M ;
 * 数组中第i个元素 next[i] 代表模式串中第i个元素之前的元素中前缀子串和后缀子串相匹配的最大长度
 *
 * [2] 主串和模式串 利用失败指针进行匹配
 * 利用模式串和主串进行匹配，当模式串和主串匹配不上时，记模式串失败位置下标为y , 主串中失败字符对应下标为 x
 * 则有: 主串下一次与模式串进行匹配的下标位置不变仍为x , 同时模式串中下标y 跳转到模式串中 next[y] 的下标处；
 *       这样继续对next[y] 开始的模式串和 x-next[y] 开始的主串继续进行逐字符的匹配；
 *
 *
 * ==============================================================================
 *
 */
public class Day030 {

    /**
     * 返回主串 str中第一次出现 模式串 match 的下标位置，若无匹配子串则返回 -1
     * @param str
     * @param match
     * @return
     */
    public int getIndexOfFirstMatch(String str , String match){
        int n = str.length(), m = match.length();
        if(m > n) return -1;

        // 模式串失败指针
        char[] main = str.toCharArray();
        char[] pattern = match.toCharArray();
        int[] next = getNextArr(match);

        int x = 0 , y = 0;
        // 匹配过程
        while(x <= n  && y <= m){
            if(pattern[y] == main[x]){
                x++;
                y++;
            }else if(next[y] != -1){
                y = next[y];
            }else{
                x++;
            }
        }
        // 匹配结果3种可能：
        // (1) x <= n , y  越界  : 在主串中间位置匹配成功
        // (2) x 越界, y 越界 : 在主串末尾位置匹配成功
        // (3) x 越界， y <= m : 未匹配成功
        return y == m ? x - y : -1;
    }

    /**
     * 返回模式串的失败指针
     *
     * 失败指针:
     * 在模式串在主串中匹配时，当字符匹配失败时，模式串的前向跳转下标
     * 同时，失败指针数组中，模式串每个字符下标i 对应位置的失败指针就是[0 ~ i-1] 范围对应字符串的前缀和后缀相同的最大长度
     *
     * @param pattern
     * @return
     */
    private int[] getNextArr(String pattern){
        if(pattern.length() < 2) {return new int[]{-1}; }
        int len = pattern.length();
        char[] match = pattern.toCharArray();
        int[] next = new int[len];
        next[0] = -1;
        next[1] = 0;
        // 当前next 指针: 初始值为离 i 最近的next指针的值
        int cn = next[1];
        for(int i = 2 ; i < len ;){
            if(match[i] == match[cn]){
                next[i++] = ++cn;
            }else if(cn > 0){
                cn = next[cn];
            }else {
                next[i++] = 0;
            }
        }
        return next;
    }
}
