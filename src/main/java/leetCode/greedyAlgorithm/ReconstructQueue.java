package leetCode.greedyAlgorithm;

import java.util.*;

/**
 * 《406. 根据身高重建队列》 标签： 贪心算法
 *
 * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
 *
 * 注意：
 * 总人数少于1100人。
 *
 * 示例
 *
 * 输入:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *
 * 输出:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 *
 *
 */
public class ReconstructQueue {

    public static void main(String[] args) {
        int[][] people = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};

        int[][] ints = reconstructQueue(people);
        System.out.println(ints);
    }

    /**
     *
     *
     *
     *  考虑大家身高都一样的情况，实际队列顺序就是 每个人的下标()递增顺序
     *
     *  思路： 按上述排序方式，优先处理身高高的人，然后再处理身高较矮的人，较矮的人需要插入到整个队列中； 这样做带来两个好处
     *  (1) 身高高的人按下标(前面不矮于自己的人数) 递增排序后，这样同等身高的人的顺序
     *  (2) 身高矮的人在插入时，直接以自己的下标为基准，插入到队列中对应的位置，这时即使在队列中处于身高较高人的前面，但是并未改变它的逆序数
     *
     *
     */
    static int[][] reconstructQueue(int[][] people) {
        if(null == people) return null;
        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);

        List<int[]>  list = new LinkedList<>();
        for(int[] p : people){
            list.add(p[1],p);
        }

        return list.toArray(new int[people.length][2]);
    }
}
