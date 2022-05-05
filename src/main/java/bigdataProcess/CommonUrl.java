package bigdataProcess;


import java.io.*;
import java.util.*;

/**
 * @className: CommonUrl
 * @description:
 * 有a、b两个文件，各存放50亿个url，每个url各占64字节，内存限制是4G，让你找出a、b文件共同的url
 *
 * 每个url占用 64字节，50亿URL 大约占 50*64 = 320GB
 * 内存限制是 4GB
 *
 * 考虑将a,b 两块数据文件 根据 hash1(x)/1024 将数据划分到1024文件内，平均每个文件大小为 320M
 * 若出现数据倾斜(一个槽位[n]中数据大小超过320M, 则考虑使用使用hash2 算法对槽位[n]的数据进行再次哈希)
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2022/5/5
 */
public class CommonUrl {

    public static void main(String[] args) throws Exception {
        // 数据准备
//        dataPrepare();
        // 对数据做 hash 分槽位处理
        // 将数据分为100份, 每份超过 1000条URL时将对数据进行 再次划分
        File file1 = new File("datas/a.txt");
        File file2 = new File("datas/b.txt");

        splitDataIntoParts(file1, "datas/a");
        splitDataIntoParts(file2, "datas/b");

        // 读取相同后缀的 a,b 两个路径下的文件 比较是否存在相同的url
        // 如: 读取 a路径下i为 1 的文件，将它保存在内存的 HashSet 中
        // 然后读取 b路径下i 为1 的文件，比对 HashSet 中是否存在，若存在则说明该URL为共有URL

        Set<String> set = new HashSet<>();
        Set<String> commonUrls = new HashSet<>();
        for(int i = 0; i < 100; i++){
            File file_1 = new File("datas/a/part-" + i + ".txt");
            BufferedReader bufferedReader1 = new BufferedReader(new FileReader(file_1));
            bufferedReader1.lines().forEach(line ->{
                set.add(line);
            });

            File file_2 = new File("datas/b/part-" + i + ".txt");
            BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file_2));
            bufferedReader2.lines().forEach(line -> {
                if(set.contains(line)){
                    commonUrls.add(line);
                }
            });

            bufferedReader1.close();
            bufferedReader2.close();
        }

        System.out.println(commonUrls);
    }

    private static void splitDataIntoParts(File file, String savePath) throws Exception{
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        BufferedWriter[] bwa = new BufferedWriter[100];
        for(int i = 0 ; i < 100; i++){
            FileWriter fileWriter = new FileWriter(new File(savePath + "/part-" + i + ".txt"));
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bwa[i] = bufferedWriter;
        }

        br.lines().forEach(line ->{
            int hash = Math.abs(hash(line));
            int slot = hash % 100;
            try {
                if(Objects.nonNull(bwa[slot])){
                    bwa[slot].write(line);
                    bwa[slot].write("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        for(BufferedWriter bw : bwa){
            bw.close();
        }

        br.close();
    }


    // 计算指定key的hash值，并做自异或打散处理
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }


    /**
     * 数据准备
     *
     */
    private static void dataPrepare() throws IOException {
        FileWriter fileWriter1 = new FileWriter("datas/a.txt");
        FileWriter fileWriter2 = new FileWriter("datas/b.txt");

        BufferedWriter bufferedWriter1 = new BufferedWriter(fileWriter1);
        BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);

        // 文件写入 a.txt
        String prefix = "http://";
        for(int i = 0 ; i < 100_000; i++){
            String url = generateRandomUrl(prefix);
            bufferedWriter1.write(url);
        }
        bufferedWriter1.close();
        // 文件写入 b.txt
        for(int i = 0 ; i < 100_000; i++){
            String url = generateRandomUrl(prefix);
            bufferedWriter2.write(url);
        }
        bufferedWriter2.close();
    }


    private static String generateRandomUrl(String prefix){
        List<String> parts = new ArrayList<>();
        // URL包含三部分
        for(int i = 0; i < 2; i++){
            parts.add(getRandomString(4));
        }
        String url = prefix + String.join("/",parts) + "\n";
        return url;
    }


    private static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(26);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
