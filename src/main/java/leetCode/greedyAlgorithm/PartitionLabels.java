package leetCode.greedyAlgorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 《763. 划分字母区间》
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
 *
 * 示例 1:
 *
 * 输入: S = "ababcbacadefegdehijhklij"
 * 输出: [9,7,8]
 * 解释:
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 * 注意:
 *
 * S的长度在[1, 500]之间。
 * S只包含小写字母'a'到'z'。
 */
public class PartitionLabels {

    public static void main(String[] args) {

    }

    /**
     * 算法分析：
     *
     */
    static List<Integer> partitionLabels(String S) {
        //
        Map<Character,Integer> map = new HashMap<>();
        for(int i = 0 ; i < S.toCharArray().length ; i++){
            map.put(S.charAt(i),i);
        }

        return null;
    }
}
