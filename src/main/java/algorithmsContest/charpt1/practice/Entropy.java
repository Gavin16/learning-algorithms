package algorithmsContest.charpt1.practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @className: Entropy
 * @description: 1.4 哈夫曼编码
 * @version: 1.0
 * @author: minsky
 * @date: 2022/12/18
 */
public class Entropy {

    public static void main(String[] args) {
        Entropy instance = new Entropy();
        instance.calculateEntropy();
    }

    /**
     * 问题描述:
     * 输入一个字符串，分别用普通的ASCII码和哈弗曼编码，输出编码前后的长度，并输出压缩比
     * 输入:
     * AAAAABCD
     * HTE_CAT_IN_THE_HAT
     * END
     *
     */
    public void calculateEntropy(){
        Scanner scanner = new Scanner(System.in);
        String str;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        while(!(str = scanner.nextLine()).equals("END")){
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            int cnt = 1;
            for(int i = 0; i < chars.length - 1; i++){
                if(chars[i] != chars[i+1]){
                    queue.offer(cnt);
                    cnt = 1;
                }else cnt ++;
            }
            queue.offer(cnt);
            int ans = 0;
            if(queue.size() == 1) ans = chars.length;
            while(queue.size() > 1){
                int a = queue.poll();
                int b = queue.poll();
                queue.offer(a + b);
                ans += (a + b);
            }
            queue.poll();
            System.out.printf("%d %d %.1f\n",
                    chars.length * 8, ans, (double)(chars.length * 8) / ans);
        }
    }
}
