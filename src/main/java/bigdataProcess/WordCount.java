package bigdataProcess;

import org.apache.commons.compress.utils.Lists;

import java.util.*;

/**
 * @className: WordCount
 * @description:
 * 单机版 word count
 * 内存限制为1M:
 * 数据文件信息:
 *      大小: 1G
 *    词长度: 16 字节
 *    每行一个词
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2022/5/4
 */
public class WordCount {

    public static void main(String[] args) {
        // 读取源数据文件对每行 x 计算 hash(x) / 4096 = n,  将  x 保存到 x号文件

        // 遍历读取 0 - 4095 号文件，使用hashmap 统计文件中次出现次数，并将出现次数最多的 100个词
        // 将词及对应次数保存到文件新的文件中   cnt-n.txt

        // 读取统计后的 4096 个带统计次数的文件
        // 读取进来后，合并相同的词的统计次数，并最终汇总到一个 hash map 中

        // 对hash map 中的entry 倒序排序，取前100
        // 其中排序规则为 value 的值

        Map<String,Integer> cntMap = new HashMap<>();

        cntMap.put("hello", 101);
        cntMap.put("java", 66);
        cntMap.put("hehe", 50);
        cntMap.put("idea", 98);

        Set<Map.Entry<String, Integer>> entries = cntMap.entrySet();
        List<Map.Entry<String, Integer>> entryList = Lists.newArrayList();
        entryList.addAll(entries);

        entryList.sort((e1,e2) -> e2.getValue() -  e1.getValue());
        for(int i = 0 ; i < 2 ; i++){
            System.out.println(entryList.get(i));
        }
    }
}
