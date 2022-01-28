package leetcode;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串""。
 *
 *
 * 示例 1：
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 *
 * 示例 2：
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 *
 * 提示：
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 *
 */
public class LongestCommonPrefix {


    public String longestCommonPrefix(String[] strs) {
        int minLen = Integer.MAX_VALUE;
        for(String str : strs){
            if(str.length() < minLen){
                minLen = str.length();
            }
        }
        int i = 0;
        for( ; i < minLen ; i++){
            char c = strs[0].charAt(i);
            for(String str : strs){
                if(str.charAt(i) != c){
                    return strs[0].substring(0,i);
                }
            }
        }
        return strs[0].substring(0,i);
    }


    public static void main(String[] args) {
        String[] strs = {"dog","racecar","car"};
        String[] strs1 = {"flower","flow","flight"};
        LongestCommonPrefix inst = new LongestCommonPrefix();
        System.out.println(inst.longestCommonPrefix(strs1));
    }

}
