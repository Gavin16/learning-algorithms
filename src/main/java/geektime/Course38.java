package geektime;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Description: 分治算法练习题
 * (1) 对外部大文件排序
 * (2) 二维平面上有 n 个点，如何快速计算出两个距离最近的点对 （TODO）
 * (3) 有两个 n*n 的矩阵 A，B，如何快速求解两个矩阵的乘积 C=A*B？（TODO）
 */
public class Course38 {

    /**
     * 大文件外部排序：桶排序
     *
     *  基本做法:
     *  (1) 逐行读取磁盘大文件，根据数值范围，将该行记录写入指定的文件(如通过取值范围分文件存储)
     *  (2) 按文件标号从小到大逐个读取到内存排序，并将排好序的结果写入对应标号的磁盘文件
     *  (3) 按文件标号从小到大读取对应的文件，并将文件中的数据写入 sorted文件，最终sorted中的文件就是有序的数据集
     *
     *
     *  文件存放路径统一为: D:\DataCenter\algoData\sortScores\exam_scores.csv
     *  拆分后文件存放路径: D:\DataCenter\algoData\sortScores\segments
     *  最后排好序的文件统一存放在: D:\DataCenter\algoData\sortScores\sorted.csv
     */
    public static void main(String[] args) throws IOException {
//        splitToDataSegments();
        sortAndMerge();
    }

    private static String segmentsPath = "D:\\DataCenter\\algoData\\sortScores\\segments\\";
    private static String outputPath = "D:\\DataCenter\\algoData\\sortScores\\output\\";

    private static String origPath = "D:\\dataCenter\\algoData\\sortScores\\exam_scores.csv";

    private static final int fileNums = 250;


    /** 遍历拆分文件 */
    static void splitToDataSegments() throws IOException {
        // 设置虚拟机内存 : 让可用内存小于数据总大小？？
        //  -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
        File file = new File(origPath);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        // 得分分段的段长度
        int step = 750 / fileNums;
        // 创建包含 fileNums 个file文件的数组
        File[] segFiles = preGenSegmentFiles(fileNums);
        // 创建 fileNums 个 BufferedWriter

        // 遍历一遍原大文件，将数据分段保存到 segments 路径，分段方式 : [0-3),[3,6),[6,9) ... [747,750)
        String line;
        while(null != (line = br.readLine())){
            if(line.length() > 0){
                String[] stuScore = line.split(",");
                int score = Integer.parseInt(stuScore[stuScore.length-1]);

                // 对数据每3分 分一段,获取当前记录对应在File数组中的id
                int id = score == 0 ? 0 :(score-1)/step;
                File segFile = segFiles[id];
                FileWriter fw = new FileWriter(segFile,true);
                BufferedWriter bw = new BufferedWriter(fw);

                String toWrite = String.join(",",stuScore) + "\r\n";
                bw.write(toWrite,0,toWrite.length());
                bw.flush();

                fw.close();
                bw.close();
            }
        }
    }


    private static void sortAndMerge() throws IOException {
        File[] segFiles = preGenSegmentFiles(fileNums);
        String path = outputPath  + "sorted_scores.csv";

        BufferedWriter resBr = new BufferedWriter(new FileWriter(path));

        List<String[]> allScores = new ArrayList<>();
        // 分数段从高到低读取所有文件
        for(int j = segFiles.length-1 ; j >= 0 ; j --){
            BufferedReader br = new BufferedReader(new FileReader(segFiles[j]));

            // 读取所有行数据，做快速排序
            allScores.clear();
            String line;
            while(null != (line = br.readLine())){
                String[] scores = line.split(",");
                allScores.add(scores);
            }

            // 倒序排序分数段内的成绩
            Collections.sort(allScores, new Comparator<String[]>() {
                @Override
                public int compare(String[] o1, String[] o2) {
                    String s1 = o1[o1.length-1];
                    String s2 = o2[o2.length-1];

                    int n1 = Integer.parseInt(s1);
                    int n2 = Integer.parseInt(s2);
                    return n2 - n1;
                }
            });

            // 排序结果写入文件
            for(String[] arr : allScores){
                String record = String.join(",",arr) + "\r\n";
                resBr.write(record,0,record.length());
                resBr.flush();
            }

        }
    }


    private static File[] preGenSegmentFiles(int num) throws IOException {
        File[] segFiles = new File[num];
        String prefix = segmentsPath + "segment-";
        for(int i =0 ; i < segFiles.length ; i++){
            String fullName = prefix + i + ".csv";
            File file = new File(fullName);
            file.createNewFile();
            segFiles[i] = file;
        }
        return segFiles;
    }
}
