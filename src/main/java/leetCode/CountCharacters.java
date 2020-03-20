package leetCode;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 《1160. 拼写单词》
 *给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。
 *
 * 假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。
 *
 * 注意：每次拼写时，chars 中的每个字母都只能用一次。
 *
 * 返回词汇表 words 中你掌握的所有单词的 长度之和。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：words = ["cat","bt","hat","tree"], chars = "atach"
 * 输出：6
 * 解释：
 * 可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。
 *
 */
public class CountCharacters {

    public static void main(String[] args) {
        CountCharacters count = new CountCharacters();
        String[] words = {"cat","bt","hat","tree"};
        String chars = "atach";
        System.out.println(count.countCharacters(words,chars));
    }


    /**
     * 用数组实现字符的统计存储
     *
     */
    public int countCharacters1(String[] words, String chars) {
        int[] arrTemp = new int[26];
        for(int i = 0 ; i < chars.length() ; i++){
            arrTemp[chars.charAt(i)-'a'] += 1;
        }

        int len = 0;
        for(String word : words){
            boolean flag = true;
            int[] arrWord = new int[26];
            for(char ch : word.toCharArray()){
                int id = ch - 'a';
                if(arrWord[id] == arrTemp[id]){
                    flag = false;
                    break;
                }
                arrWord[id] += 1;
            }
            if(flag) len += word.length();
        }

        return len;
    }


    public int countCharacters(String[] words, String chars) {
        Map<Character,Integer> map =getCharCountMap(chars);
        int totalLen = 0 ;
        for(String word : words){

            Map<Character,Integer> wordMap = getCharCountMap(word);
            Set<Character> wordChars = wordMap.keySet();
            if(wordChars.size() > wordMap.keySet().size()) continue;
            int id = 0;
            for(char ch : wordChars){
                if(!map.containsKey(ch) || wordMap.get(ch) > map.get(ch)){
                    break;
                }
                id++;
            }
            if(id < wordChars.size()) continue;
            totalLen += word.length();
        }
        return totalLen;
    }

    private Map<Character,Integer> getCharCountMap(String str){
        Map<Character,Integer> map = new HashMap<>();
        for(char ch : str.toCharArray()){
            if(!map.containsKey(ch)){
                map.put(ch,1);
            }else{
                map.put(ch,map.get(ch) + 1);
            }
        }
        return map;
    }
}
