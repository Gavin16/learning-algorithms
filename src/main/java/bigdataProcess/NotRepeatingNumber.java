package bigdataProcess;

import org.apache.poi.ss.formula.functions.Dec2Bin;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @className: NotRepeatingNumber
 * @description:
 *
 * 在 2.5 亿个整数中找出不重复的整数，内存不足以容纳这2.5亿个整数
 * 分析:
 * 2.5亿 * 4 = 1GB, 也即内存不足 1GB
 * 判断重复
 *
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2022/5/5
 */
public class NotRepeatingNumber{

    public static void main(String[] args) throws Exception{
        // 方案一
        // 将全量数据通过hash 打散近似等大小的拆分为 10个文件

        // 对10个文件中每个文件进行排序
        // 排序后的文件写入到新的数据文件中

        // 分别读取10个文件中每个文件中的数据，记录每个数出现的次数
        // 保留仅出现 1次的数据

        // 合并汇总所有文件对应的仅出现一次的数据
        // 从汇总后的数据中筛选出最后也只出现一次的数据

        // 方案二: 2-Bitmap
        int numTotal = 3000;
//        generateRandomNumber(numTotal);

        TwoBitMap twoBitMap = new TwoBitMap(1000);

        // 读取文件数据,并统计数量
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("datas/numbers.txt")));

        String line;
        while((line = bufferedReader.readLine()) != null){
            int num = Integer.parseInt(line);
            twoBitMap.setCntInc(num);
        }
        bufferedReader.close();

        //再读取一遍文件,找出所有只出现过一次的数字
        Set<Integer> set0 = new HashSet<>();
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        bufferedReader =  new BufferedReader(new FileReader("datas/numbers.txt"));
        while((line = bufferedReader.readLine()) != null){
            int num = Integer.parseInt(line);
            int cntVal = twoBitMap.getCntVal(Integer.parseInt(line));
            if(cntVal == 1){
                set1.add(num);
            }else if(cntVal == 3){
                set2.add(num);
            }else{
                set0.add(num);
            }
        }
        bufferedReader.close();

        System.out.println("仅出现过零次的数的个数为:" + set0.size());
        System.out.println("仅出现过一次的数的个数为:" + set1.size());
        System.out.println("仅出现过二次及以上的数个数为:" + set2.size());
        System.out.println(set1);
    }


    private static void generateRandomNumber(int total) throws Exception{
        Random random = new Random();
        File file = new File("datas/numbers.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

        for(int i = 0; i < total; i++){
            int number = random.nextInt(900) + 100;
            bufferedWriter.write(number + "\n");
        }

        bufferedWriter.close();
    }


    /**
     * 定义 TwoBitMap 类型
     *
     * 以2bit标识3个状态
     * 00 - 未出现过
     * 01 - 出现一次
     * 11 - 出现多次
     */
    static class TwoBitMap{
        private int eachIntCap = Integer.SIZE / 2;

        private int[] array;

        public TwoBitMap(int maxVal){
            int intNum = maxVal / eachIntCap + ((maxVal % eachIntCap) > 1 ? 1 : 0);
            array = new int[(int)Math.ceil(intNum)];

            for(int i = 0; i < array.length; i++){
                array[i] = 0;
            }
        }

        // 设置指定位置次数 + 1; 若判断已经出现了多次则不再调整
        public void setCntInc(int pos){
            int cntVal = getCntVal(pos);

            int intPos = pos >> 4;
            int twoBitPos = pos % eachIntCap;
            Integer intVal = array[intPos];

            // 计算增加1后新的计数值
            if(cntVal == 0){
                cntVal += 1;
            }else if(cntVal == 1){
                cntVal += 2;
            }
            // 新计算结果值写回
            int temp = cntVal;
            for(int j = 0; j < twoBitPos; j++){
                temp =  temp << 2;
            }
            temp = temp | intVal;
            array[intPos] = temp;
        }


        // 查询获取 pos 位置的当前值
        public int getCntVal(int pos){
            int intPos = pos >> 4;
            int twoBitPos = pos % eachIntCap;
            Integer intVal = array[intPos];

            // 右移获取2位的数值
            int temp = intVal;
            for(int i = 0; i < twoBitPos; i++){
                temp = temp >> 2;
            }
            int cntVal = temp & 0x03;
            return cntVal;
        }

    }
}
