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
 *
 *
 * 477. 汉明距离总和
 *
 * 两个整数的 汉明距离 指的是这两个数字的二进制数对应位不同的数量。
 * 计算一个数组中，任意两个数之间汉明距离的总和。
 *
 * 示例:
 * 输入: 4, 14, 2
 * 输出: 6
 *
 * 解释: 在二进制表示中，4表示为0100，14表示为1110，2表示为0010。（这样表示是为了体现后四位之间关系）
 * 所以答案为：
 * HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
 * 注意:
 *
 * 数组中元素的范围为从0到10^9。
 * 数组的长度不超过10^4。
 *
 */
public class Day038 {


    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strs));
        System.out.println("------------------------");
        int[] nums = {2,4,14};
        System.out.println(totalHammingDistance1(nums));
    }


    /**
     * 两两比较将导致超时：时间复杂度O(N^2)
     */
    public static int totalHammingDistance(int[] nums) {
        int total = 0;
        for(int i = 0 ; i < nums.length ; i++){
            for(int j = i ; j < nums.length ; j++){
                total += hammingDistance(nums[i],nums[j]);
            }
        }
        return total;
    }


    private static int hammingDistance(int x, int y) {
        int res = x ^ y , dist = 0;
        while(res != 0){
            int bit = res & 1;
            dist = dist + bit;
            res = res >>> 1;
        }
        return dist;
    }

    /**
     * 由题设：数组中元素的范围为从0到10^9 < 2^30
     * 按位统计每一个数在该位 是0，还是1 ；
     *    假定 0 的个数为 a, 那么 1的个数为 n-a; 那么在该位上能贡献的汉明距就是 0 和 1 可能的组合数
     *    因此贡献量为  a*(n-a)
     *
     */
    public static int totalHammingDistance1(int[] nums) {
        int res = 0 , N = nums.length;
        for(int i = 0 ; i <= 30 ; i++){
            int c = 0;
            for(int num : nums){
                int curr = num >>> i;
                c += curr & 1;
            }
            res += c * (N - c);
        }
        return res;
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
