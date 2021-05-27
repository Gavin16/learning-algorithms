package leetcode.oneMoreHundred;

import java.util.*;

/**
 * 49. 字母异位词分组
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * 示例:
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 */
public class Day038 {


    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strs));
    }


    /**
     * 使用数据记录每个字符出现的次数，并将数组转化为HashMap的key，用来保存记录
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        for(String str : strs){
            int[] cntArr = new int[26];
            for(char c : str.toCharArray()){
                cntArr[c - 'a'] += 1;
            }
            StringBuilder sb = new StringBuilder();

            for(int k = 0 ; k < cntArr.length ; k++){
                int c = cntArr[k];
                if(c == 0) continue;
                else if(c == 1) sb.append(k +'a');
                else sb.append(String.valueOf(c) + (k+'a'));
            }
            String key = sb.toString();
            if(map.containsKey(key)){
                List<String> strings = map.get(key);
                strings.add(str);
            }else{
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(key,list);
            }
        }
        return new ArrayList<>(map.values());
    }
}
