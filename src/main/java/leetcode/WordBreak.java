package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 *
 *
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
 *
 * 示例 2：
 *
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
 *     注意，你可以重复使用字典中的单词。
 *
 * 示例 3：
 *
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 * 
 *
 * 提示：
 *
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s 和 wordDict[i] 仅有小写英文字母组成
 * wordDict 中的所有字符串 互不相同
 *
 */
public class WordBreak {


    public static void main(String[] args) {
        String s = "applepenapple";
        List<String> wordDict = Arrays.asList(new String[]{"apple", "pen"});
        WordBreak wordBreak = new WordBreak();

        System.out.println(wordBreak.wordBreak(s, wordDict));
    }



    /**
     * 动态规划实现
     * 假设字符串 s 中前i 位置是否都可以用字典标识记作 dp[i]
     * 记 j ∈ [0,i] 若 dp[j] = true
     * 则 dp[i] = dp[j] & check(j+1, i)  这也就是动态规划状态 dp数组的状态转移方程
     * 认为wordDict 包含空串，则有 dp[0] 为true
     * 为此需要定义 dp[] = new boolean[s.length() + 1]
     *
     * 最终字符串是否可以被 wordDict 表示结果保存在 dp[s.length()] 中
     *
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        Set<String> wordSet = new HashSet<>(wordDict);

        for(int i = 1 ; i <= s.length(); i++){
            for(int j = 0 ; j <= i ; j++){
                // s中第一个字符从0开始, substring 需要包含 j
                if(dp[j] && wordSet.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    /**
     * 使用字典树，通过将 s 的遍历时同时搜索字典树
     * 若搜索到字典树的叶子节点，则重新从根节点搜索下一部分
     * 若搜索到字典树的非叶子结点时发现不能匹配，则
     */
    public boolean wordBreak1(String s, List<String> wordDict){
        return Boolean.TRUE;
    }
}
