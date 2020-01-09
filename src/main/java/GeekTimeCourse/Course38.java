package GeekTimeCourse;

/**
 * @Description: 分治算法练习题
 * (1) 对外部大文件排序
 * (2) 二维平面上有 n 个点，如何快速计算出两个距离最近的点对
 * (3) 有两个 n*n 的矩阵 A，B，如何快速求解两个矩阵的乘积 C=A*B？
 */
public class Course38 {

    public static void main(String[] args) {

    }

    /**
     * 大文件外部排序：桶排序
     *
     *  基本做法:
     *  (1) 逐行读取磁盘大文件，根据数值范围，将该行记录写入指定的文件(如通过取值范围分文件存储)
     *  (2) 按文件标号从小到大逐个读取到内存排序，并将排好序的结果写入对应标号的磁盘文件
     *  (3) 按文件标号从小到大读取对应的文件，并将文件中的数据写入 sorted文件，最终sorted中的文件就是有序的数据集
     *
     *
     *  文件存放路径统一为: D:\DataCenter\algoData\exam_scores.csv
     *  拆分后文件存放路径: D:\DataCenter\algoData\segments
     *  最后排好序的文件统一存放在: D:\DataCenter\algoData\sorted.csv
     */
    static void bigDataOuterSort(){
        // 设置虚拟机内存 : 让可用内存小于数据总大小？？

        // 读取数据，对数据分片写入磁盘 : 考虑磁盘IO的效率问题. 是否可以做到磁盘的顺序读写？？

        // 读取各个文件排序后写回该文件

        // 将各个文件合并成大的有序文件
    }
}
