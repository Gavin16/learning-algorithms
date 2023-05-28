package algorithmsContest.charpt4;

/**
 * @className: SimilarStringGroup
 * @description:
 * 839. 相似字符串组
 *
 * 如果交换字符串 X 中的两个不同位置的字母，使得它和字符串 Y 相等，那么称 X 和 Y 两个字符串相似。
 * 如果这两个字符串本身是相等的，那它们也是相似的。
 *
 * 例如，"tars" 和 "rats" 是相似的 (交换 0 与 2 的位置)； "rats" 和 "arts" 也是相似的，
 * 但是 "star" 不与 "tars"，"rats"，或 "arts" 相似。总之，它们通过相似性形成了两个关联组：
 * {"tars", "rats", "arts"} 和 {"star"}。注意，"tars" 和 "arts" 是在同一组中，即使它们并不相似。
 * 形式上，对每个组而言，要确定一个单词在组中，只需要这个词和该组中至少一个单词相似。
 *
 * 给你一个字符串列表 strs。列表中的每个字符串都是 strs 中其它所有字符串的一个字母异位词。请问 strs 中有多少个相似字符串组？
 *
 * 示例 1：
 * 输入：strs = ["tars","rats","arts","star"]
 * 输出：2
 * 示例 2：
 *
 * 输入：strs = ["omv","ovm"]
 * 输出：1
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2023/5/25
 */
public class SimilarStringGroup {

    public static void main(String[] args) {
        SimilarStringGroup solution = new SimilarStringGroup();

        String[] str1 = {"tars","rats","arts","star"};
        System.out.println(solution.numSimilarGroups(str1));

        String[] str2 = {"omv","ovm"};
        System.out.println(solution.numSimilarGroups(str2));
    }

    int[] s;
    public int numSimilarGroups(String[] strs) {
        int len = strs.length;
        s = new int[len];
        for(int i = 0; i < len; i++) s[i] = i;

        for(int i = 0; i < len; i++){
           for(int j = i+1; j < len; j++){
               int si = findSet(i), sj = findSet(j);
               if(si == sj) continue;

               if(isSimilar(strs[i], strs[j])){
                   s[si] = sj;
               }
           }
        }
        int ans = 0;
        for(int i = 0; i < len; i++){
            if(i == s[i]) ans++;
        }
        return ans;
    }

    private boolean isSimilar(String str1, String str2){
        int cnt = 0;
        for(int i = 0; i < str1.length(); i++){
            if(str1.charAt(i) != str2.charAt(i)) cnt++;
            if(cnt > 2) return false;
        }
        return true;
    }

    private int findSet(int x){
        if(x != s[x]) s[x] = findSet(s[x]);
        return s[x];
    }


}
