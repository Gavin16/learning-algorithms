package leetcode.greedyAlgorithm;

import java.util.ArrayList;
import java.util.List;

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
        String S = "eaaaabaaec";
        String S2 = "ababcbacadefegdehijhklij";
        System.out.println(partitionLabels(S));
        System.out.println(partitionLabels(S2));
    }

    /**
     * 算法分析：
     * 遍历所有字符最后出现的位置存map中，
     * 再次遍历S时，使用 headPos 和 maxLastPos 两个指针来标识最后各个
     *
     */
    static List<Integer> partitionLabels(String S) {
        // 可以使用数组替换map,提高执行效率
        int[] lastIds = new int[26];
        char[] chars = S.toCharArray();
        for(int i = 0 ; i <chars.length ; i++){
            lastIds[chars[i] - 'a'] = i;
        }

        List<Integer> list = new ArrayList<>();
        int headMaxId = lastIds[chars[0] - 'a'],preLastId = 0;
        for(int i = 0 ; i <chars.length ; i++){
            Integer id = lastIds[chars[i] - 'a'];
            if(i == headMaxId){
                list.add(i - preLastId + 1);
                headMaxId = (i == chars.length - 1) ? -1 : lastIds[chars[i+1]-'a'];
                preLastId = i+1;
            }else{
                if(id > headMaxId){
                    headMaxId = id;
                }
            }
        }
        return list;
    }
}
